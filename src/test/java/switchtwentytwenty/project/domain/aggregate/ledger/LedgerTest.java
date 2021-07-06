package switchtwentytwenty.project.domain.aggregate.ledger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.dto.todomaindto.TransferInformationDTO;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;
import switchtwentytwenty.project.domain.share.transactiondata.Transfer;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class LedgerTest {


    @Test
    @DisplayName("Get Ledger ID")
    void getLedgerID() {
        //arrange
        UUID id = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(id);
        Ledger ledger = new Ledger(ledgerID);
        //act
        LedgerID result = ledger.getID();
        //assert
        assertEquals(ledgerID, result);
    }

    @Test
    @DisplayName("Ledger has the same ID - True")
    void ledgerHasSameIDTrue() {
        //arrange
        UUID id = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(id);
        Ledger ledger1 = new Ledger(ledgerID);
        //act
        boolean result = ledger1.hasSameID(ledgerID);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ledger has the same ID - False")
    void ledgerHasSameIDFalse() {
        //arrange
        UUID id = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(id);
        LedgerID ledgerID2 = new LedgerID(id2);
        Ledger ledger = new Ledger(ledgerID);
        //act
        boolean result = ledger.hasSameID(ledgerID2);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ledger has the same value")
    void ledgerHasTheSameValue() {
        //arrange
        UUID id = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(id);
        Ledger ledger1 = new Ledger(ledgerID);
        Ledger ledger2 = ledger1;
        //act
        boolean result = ledger1.sameValueAs(ledger2);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ledger does not have the same value")
    void ledgerDoesNotHaveTheSameValue() {
        //arrange
        UUID id = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(id);
        Ledger ledger1 = new Ledger(ledgerID);
        Ledger ledger2 = new Ledger(ledgerID);
        //act
        boolean result = ledger1.sameValueAs(ledger2);
        //assert
        assertFalse(result);
    }
    @Test
    @DisplayName("Ledger does not have the same value: different IDs")
    void ledgerDoesNotHaveTheSameValueDifferentIDs() {
        //arrange
        UUID id = UUID.randomUUID();
        UUID id2=UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(id);
        LedgerID ledgerID2 = new LedgerID(id2);
        Ledger ledger1 = new Ledger(ledgerID);
        Ledger ledger2 = new Ledger(ledgerID2);
        //act
        boolean result = ledger1.sameValueAs(ledger2);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ledger does not have the same value: different transactions")
    void ledgerDoesNotHaveTheSameValueDifferentTransactionLists() throws ParseException {
        //arrange
        UUID id = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(id);
        Ledger ledger1 = new Ledger(ledgerID);
        Ledger ledger2 = new Ledger(ledgerID);
        MoneyValue amount = new MoneyValue(new BigDecimal(50));
        MoneyValue balance = new MoneyValue(new BigDecimal(100));

        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withAmount(amount)
                .build();

        Transaction transaction = new Transfer(dto, balance);
        ledger1.addTransaction(transaction);
        //act
        boolean result = ledger1.sameValueAs(ledger2);
        //assert
        assertFalse(result);
    }


    @Test
    @DisplayName("Ledger has the same identity")
    void familyHasSameIdentity() {
        //arrange
        UUID id = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(id);
        Ledger ledger1 = new Ledger(ledgerID);
        Ledger ledger2 = new Ledger(ledgerID);
        //act
        boolean result = ledger1.equals(ledger2);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ledger does not have the same identity")
    void ledgerDoesNotHaveSameIdentity() {
        //arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        LedgerID ledgerID1 = new LedgerID(id1);
        LedgerID ledgerID2 = new LedgerID(id2);
        Ledger ledger1 = new Ledger(ledgerID1);
        Ledger ledger2 = new Ledger(ledgerID2);
        //act
        boolean result = ledger1.equals(ledger2);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same hashCode")
    void sameHashCode() {
        //arrange
        int hashCode1;
        int hashCode2;
        UUID id = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(id);
        Ledger ledger1 = new Ledger(ledgerID);
        Ledger ledger2 = new Ledger(ledgerID);
        //act
        hashCode1 = ledger1.hashCode();
        hashCode2 = ledger2.hashCode();
        //assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Not the same hashCode")
    void notSameHashCode() {
        //arrange
        int hashCode1;
        int hashCode2;
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        LedgerID ledgerID1 = new LedgerID(id1);
        LedgerID ledgerID2 = new LedgerID(id2);
        Ledger ledger1 = new Ledger(ledgerID1);
        Ledger ledger2 = new Ledger(ledgerID2);
        //act
        hashCode1 = ledger1.hashCode();
        hashCode2 = ledger2.hashCode();
        //assert
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Add transaction - true")
    void addTransactionTrue() throws Exception {
        //arrange
        UUID ledgerIDString = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(ledgerIDString);
        Ledger ledger = new Ledger(ledgerID);

        MoneyValue amount = new MoneyValue(new BigDecimal(50));
        MoneyValue balance = new MoneyValue(new BigDecimal(100));

        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withAmount(amount)
                .build();

        Transaction transaction = new Transfer(dto, balance);

        //act
        boolean result = ledger.addTransaction(transaction);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Add transaction - false")
    void addTransactionFalse(){
        //arrange
        UUID ledgerIDString = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(ledgerIDString);
        Ledger ledger = new Ledger(ledgerID);

        Transaction transaction = null;

        //act
        boolean result = ledger.addTransaction(transaction);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same Ledger")
    void sameLedger() {
        //arrange
        LedgerID id = new LedgerID(UUID.randomUUID());
        Ledger ledger = new Ledger(id);

        //act
        Ledger sameLedger = ledger;

        //assert
        assertEquals(sameLedger, ledger);
    }

    @Test
    @DisplayName("Identical Ledger")
    void identicalLedger() {
        //arrange
        LedgerID id = new LedgerID(UUID.randomUUID());
        Ledger ledger = new Ledger(id);

        //act
        Ledger sameLedger = new Ledger(id);

        //assert
        assertEquals(sameLedger, ledger);
    }

    @Test
    @DisplayName("Different Ledgers")
    void differentLedgers() {
        //arrange
        LedgerID id = new LedgerID(UUID.randomUUID());
        LedgerID otherId = new LedgerID(UUID.randomUUID());
        Ledger ledger = new Ledger(id);

        //act
        Ledger otherLedger = new Ledger(otherId);

        //assert
        assertNotEquals(otherLedger, ledger);
    }

    @Test
    @DisplayName("Different Classes")
    void differentClasses() {
        //arrange
        LedgerID id = new LedgerID(UUID.randomUUID());
        Ledger ledger = new Ledger(id);

        //act
        BigDecimal bigDecimal = BigDecimal.ONE;

        //assert
        assertNotEquals(bigDecimal, ledger);
    }

    @Test
    @DisplayName("Null Object")
    void nullObject() {
        //arrange
        LedgerID id = new LedgerID(UUID.randomUUID());
        Ledger ledger = new Ledger(id);

        //act
        Ledger otherLedger = null;

        //assert
        assertNotEquals(otherLedger, ledger);
    }

    @Test
    @DisplayName("Different hash code")
    void differentHashCode() {
        //arrange
        LedgerID id = new LedgerID(UUID.randomUUID());
        LedgerID otherId = new LedgerID(UUID.randomUUID());
        Ledger ledger = new Ledger(id);
        Ledger otherLedger = new Ledger(otherId);

        //act
        int hash1 = ledger.hashCode();
        int hash2 = otherLedger.hashCode();

        //assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Identical hash code")
    void identicalHashCode() {
        //arrange
        LedgerID id = new LedgerID(UUID.randomUUID());
        Ledger ledger = new Ledger(id);
        Ledger sameLedger = new Ledger(id);

        //act
        int hash1 = ledger.hashCode();
        int hash2 = sameLedger.hashCode();

        //assert
        assertEquals(hash1, hash2);
    }
}
