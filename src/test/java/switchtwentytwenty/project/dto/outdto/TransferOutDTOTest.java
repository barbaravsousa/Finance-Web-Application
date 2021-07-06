package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.id.CategoryID;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransferOutDTOTest {

    @Test
    @DisplayName("Test to get originAccountID")
    void testToGetOriginAccountId() {
        //arrange
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String date = "03/03/2021";

        TransferOutDTO transferOutDTO = new TransferOutDTO(originAccount, destinationAccount, date, amount);

        //act
        String result = transferOutDTO.getOriginAccountID();
        //assert
        assertEquals(result, originAccount);
    }

    @Test
    @DisplayName("Test to get destinationAccountID")
    void testToGetDestinationAccountId() {
        //arrange
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String date = "03/03/2021";

        TransferOutDTO transferOutDTO = new TransferOutDTO(originAccount, destinationAccount, date, amount);

        //act
        String result = transferOutDTO.getDestinationAccountID();
        //assert
        assertEquals(result, destinationAccount);
    }

    @Test
    @DisplayName("Test to get date")
    void testToGetDate() {
        //arrange
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String date = "03/03/2021";

        TransferOutDTO transferOutDTO = new TransferOutDTO(originAccount, destinationAccount, date, amount);

        //act
        String result = transferOutDTO.getDate();
        //assert
        assertEquals(result, date);
    }

    @Test
    @DisplayName("Test to get amount")
    void testToGetAmount() {
        //arrange
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String date = "03/03/2021";

        TransferOutDTO transferOutDTO = new TransferOutDTO(originAccount, destinationAccount, date, amount);

        //act
        double result = transferOutDTO.getAmount();
        //assert
        assertEquals(result, amount);
    }

    @Test
    @DisplayName("HashCode test")
    public void testEqualsHashCode() {
        //arrange
        String originAccount1 = "A-001";
        String destinationAccount1 = "A-002";
        double amount1 = 50;
        String date1 = "03/03/2021";

        String originAccount2 = "A-001";
        String destinyAccount2 = "A-002";
        double amount2 = 50;
        String date2 = "03/03/2021";

        String originAccount3 = "A-005";
        String destinyAccount3 = "A-006";
        double amount3 = 50;
        String date3 = "03/03/2021";

        //act
        TransferOutDTO transferOutDTO1 = new TransferOutDTO(originAccount1, destinationAccount1, date1, amount1);
        TransferOutDTO transferOutDTO2 = new TransferOutDTO(originAccount2, destinyAccount2, date2, amount2);
        TransferOutDTO transferOutDTO3 = new TransferOutDTO(originAccount3, destinyAccount3, date3, amount3);

        //assert
        assertEquals(transferOutDTO1, transferOutDTO2);
        assertEquals(transferOutDTO1.hashCode(), transferOutDTO2.hashCode());
        assertNotEquals(transferOutDTO1.hashCode(), transferOutDTO3.hashCode());
    }

    @Test
    @DisplayName("Same TransferOutDTO")
    public void sameOutTransferDTO() {
        //arrange
        String originAccount1 = "A-001";
        String destinationAccount1 = "A-002";
        double amount1 = 50;
        String date1 = "03/03/2021";

        String originAccount2 = "A-001";
        String destinyAccount2 = "A-002";
        double amount2 = 50;
        String date2 = "03/03/2021";

        //act
        TransferOutDTO transferOutDTO1 = new TransferOutDTO(originAccount1, destinationAccount1, date1, amount1);

        TransferOutDTO transferOutDTO2 = new TransferOutDTO(originAccount2, destinyAccount2, date2, amount2);

        //assert
        assertEquals(transferOutDTO1, transferOutDTO2);
    }

    @Test
    @DisplayName("Not same TransferOutDTO")
    public void notSameOutTransferDTO() {
        //arrange
        String originAccount1 = "A-001";
        String destinyAccount1 = "A-002";
        double amount1 = 50;
        String date1 = "03/03/2021";

        String originAccount2 = "A-003";
        String destinyAccount2 = "A-002";
        double amount2 = 50;
        String date2 = "03/03/2021";

        //act
        TransferOutDTO transferOutDTO1 = new TransferOutDTO(originAccount1, destinyAccount1, date1, amount1);

        TransferOutDTO transferOutDTO2 = new TransferOutDTO(originAccount2, destinyAccount2, date2, amount2);

        //assert
        assertNotEquals(transferOutDTO1, transferOutDTO2);
    }

    @Test
    @DisplayName("Not equal objects")
    public void outTransferDTONotEqualToCategory() {
        //arrange
        String id = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(id);
        CategoryDesignation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID,null);

        String originAccount1 = "A-001";
        String destinyAccount1 = "A-002";
        double amount1 = 50;
        String date1 = "03/03/2021";

        TransferOutDTO transferOutDTO1 = new TransferOutDTO(originAccount1, destinyAccount1, date1, amount1);

        //act
        boolean result = transferOutDTO1.equals(category);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same instance")
    public void sameInstance() {
        //arrange
        String originAccount1 = "A-001";
        String destinyAccount1 = "A-002";
        double amount1 = 50;
        String date1 = "03/03/2021";

        TransferOutDTO transferOutDTO1 = new TransferOutDTO(originAccount1, destinyAccount1, date1, amount1);

        TransferOutDTO sameTransferOutDTO = transferOutDTO1;

        //assert
        assertEquals(sameTransferOutDTO, transferOutDTO1);
    }
}