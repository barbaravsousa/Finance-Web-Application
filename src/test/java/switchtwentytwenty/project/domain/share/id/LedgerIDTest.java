package switchtwentytwenty.project.domain.share.id;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class LedgerIDTest {

    @Test
    @DisplayName("Same LedgerID")
    void sameLedgerID() {
        //arrange
        UUID id = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(id);
        //act
        LedgerID sameLedgerID = ledgerID;
        //assert
        assertEquals(sameLedgerID, ledgerID);
    }

    @Test
    @DisplayName("Identical LedgerID")
    void identicalLedgerID() {
        //arrange
        UUID id = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(id);
        //act
        LedgerID sameLedgerID = new LedgerID(id);
        //assert
        assertEquals(sameLedgerID, ledgerID);
    }

    @Test
    @DisplayName("Null LedgerID")
    void nullLedgerID() {
        //arrange
        UUID id = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(id);
        //act
        LedgerID otherLedgerID = null;
        boolean result = ledgerID.equals(otherLedgerID);
        //assert
        assertFalse(result);
        assertNotEquals(ledgerID, otherLedgerID);
    }

    @Test
    @DisplayName("Different LedgerID")
    void differentLedgerID() {
        //arrange
        UUID id = UUID.randomUUID();
        UUID otherId = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(id);
        //act
        LedgerID differentLedger = new LedgerID(otherId);
        //assert
        assertNotEquals(differentLedger, ledgerID);
    }
}
