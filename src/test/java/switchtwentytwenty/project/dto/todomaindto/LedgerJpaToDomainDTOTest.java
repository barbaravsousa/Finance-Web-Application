package switchtwentytwenty.project.dto.todomaindto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LedgerJpaToDomainDTOTest {

    @Test
    @DisplayName("Test to get ledgerID")
    void testToGetLedgerId() {
        //arrange
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        LedgerJpaToDomainDTO ledgerJpaToDomainDTO = new LedgerJpaToDomainDTO(ledgerID);
        //act
        LedgerID result = ledgerJpaToDomainDTO.getId();
        //assert
        assertEquals(ledgerID, result);
    }

    @Test
    @DisplayName("Test to get transaction list")
    void testToGetTransactionList() {
        //arrange
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        List<Transaction> transactionList = new ArrayList<>();
        LedgerJpaToDomainDTO ledgerJpaToDomainDTO = new LedgerJpaToDomainDTO(ledgerID);
        //act
        List<Transaction> result = ledgerJpaToDomainDTO.getTransactionList();
        //assert
        assertEquals(transactionList, result);
    }

    @Test
    @DisplayName("HashCode test")
    void testEqualsHashCode() {
        //arrange
        LedgerID ledgerID1 = new LedgerID(UUID.randomUUID());
        LedgerID ledgerID2 = new LedgerID(UUID.randomUUID());
        //act
        LedgerJpaToDomainDTO ledgerJpaToDomainDTO1 = new LedgerJpaToDomainDTO(ledgerID1);
        LedgerJpaToDomainDTO ledgerJpaToDomainDTO2 = new LedgerJpaToDomainDTO(ledgerID1);
        LedgerJpaToDomainDTO ledgerJpaToDomainDTO3 = new LedgerJpaToDomainDTO(ledgerID2);
        //assert
        assertEquals(ledgerJpaToDomainDTO1, ledgerJpaToDomainDTO2);
        assertEquals(ledgerJpaToDomainDTO1.hashCode(), ledgerJpaToDomainDTO2.hashCode());
        assertNotEquals(ledgerJpaToDomainDTO1.hashCode(), ledgerJpaToDomainDTO3.hashCode());
    }

    @Test
    @DisplayName("Same LedgerJpaToDomainDTO")
    void sameJpaToDomainLedgerDTO() {
        //arrange
        LedgerID ledgerID1 = new LedgerID(UUID.randomUUID());
        //act
        LedgerJpaToDomainDTO ledgerJpaToDomainDTO1 = new LedgerJpaToDomainDTO(ledgerID1);
        LedgerJpaToDomainDTO ledgerJpaToDomainDTO2 = new LedgerJpaToDomainDTO(ledgerID1);
        //assert
        assertEquals(ledgerJpaToDomainDTO1, ledgerJpaToDomainDTO2);
    }

    @Test
    @DisplayName("Not same LedgerJpaToDomainDTO")
    void notSameJpaToDomainLedgerDTO() {
        //arrange
        LedgerID ledgerID1 = new LedgerID(UUID.randomUUID());
        LedgerID ledgerID2 = new LedgerID(UUID.randomUUID());
        //act
        LedgerJpaToDomainDTO ledgerJpaToDomainDTO1 = new LedgerJpaToDomainDTO(ledgerID1);
        LedgerJpaToDomainDTO ledgerJpaToDomainDTO2 = new LedgerJpaToDomainDTO(ledgerID2);
        //assert
        assertNotEquals(ledgerJpaToDomainDTO1, ledgerJpaToDomainDTO2);
    }

    @Test
    @DisplayName("Not equal objects")
    void jpaToDomainLedgerDTONotEqualToCategory() {
        //arrange
        String id = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(id);
        CategoryDesignation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        //act
        LedgerJpaToDomainDTO ledgerJpaToDomainDTO = new LedgerJpaToDomainDTO(ledgerID);
        //act
        boolean result = ledgerJpaToDomainDTO.equals(category);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same instance")
    void sameInstance() {
        //arrange
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        LedgerJpaToDomainDTO ledgerJpaToDomainDTO = new LedgerJpaToDomainDTO(ledgerID);
        //act
        LedgerJpaToDomainDTO sameLedgerJpaToDomainDTO = ledgerJpaToDomainDTO;
        //assert
        assertEquals(sameLedgerJpaToDomainDTO, ledgerJpaToDomainDTO);
    }
}