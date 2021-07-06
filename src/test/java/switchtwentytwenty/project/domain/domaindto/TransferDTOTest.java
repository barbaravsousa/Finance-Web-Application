package switchtwentytwenty.project.domain.domaindto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.dto.toservicedto.TransferDTO;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransferDTOTest {

    @Test
    @DisplayName("Test to get senderId")
    void testToGetSenderId() {
        //arrange
        String senderId = "P-001";
        String receiverId = "P-002";
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String categoryId = "C-001";
        String description = "Transfer money";
        String date = "03/03/2021";

        TransferDTO transferBetweenMembersDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId)
                .receiverId(receiverId)
                .originAccountId(originAccount)
                .destinationAccountId(destinationAccount)
                .amount(amount)
                .categoryId(categoryId)
                .description(description)
                .date(date)
                .build();
        //act
        String result = transferBetweenMembersDTO.getSenderID();
        //assert
        assertEquals(result, senderId);
    }

    @Test
    @DisplayName("Test to get receiverId")
    void testToGetReceiverId() {
        //arrange
        String senderId = "P-001";
        String receiverId = "P-002";
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String categoryId = "C-001";
        String description = "Transfer money";
        String date = "03/03/2021";

        TransferDTO transferBetweenMembersDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId)
                .receiverId(receiverId)
                .originAccountId(originAccount)
                .destinationAccountId(destinationAccount)
                .amount(amount)
                .categoryId(categoryId)
                .description(description)
                .date(date)
                .build();
        //act
        String result = transferBetweenMembersDTO.getReceiverID();
        //assert
        assertEquals(result, receiverId);
    }

    @Test
    @DisplayName("Test to get originAccountId")
    void testToGetOriginAccountId() {
        //arrange
        String senderId = "P-001";
        String receiverId = "P-002";
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String categoryId = "C-001";
        String description = "Transfer money";
        String date = "03/03/2021";

        TransferDTO transferBetweenMembersDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId)
                .receiverId(receiverId)
                .originAccountId(originAccount)
                .destinationAccountId(destinationAccount)
                .amount(amount)
                .categoryId(categoryId)
                .description(description)
                .date(date)
                .build();
        //act
        String result = transferBetweenMembersDTO.getOriginAccountID();
        //assert
        assertEquals(result, originAccount);
    }

    @Test
    @DisplayName("Test to get destinationAccountId")
    void testToGetDestinationAccountId() {
        //arrange
        String senderId = "P-001";
        String receiverId = "P-002";
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String categoryId = "C-001";
        String description = "Transfer money";
        String date = "03/03/2021";

        TransferDTO transferBetweenMembersDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId)
                .receiverId(receiverId)
                .originAccountId(originAccount)
                .destinationAccountId(destinationAccount)
                .amount(amount)
                .categoryId(categoryId)
                .description(description)
                .date(date)
                .build();
        //act
        String result = transferBetweenMembersDTO.getDestinationAccountID();
        //assert
        assertEquals(result, destinationAccount);
    }

    @Test
    @DisplayName("Test to get amount")
    void testToGetAmount() {
        //arrange
        String senderId = "P-001";
        String receiverId = "P-002";
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String categoryId = "C-001";
        String description = "Transfer money";
        String date = "03/03/2021";

        TransferDTO transferBetweenMembersDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId)
                .receiverId(receiverId)
                .originAccountId(originAccount)
                .destinationAccountId(destinationAccount)
                .amount(amount)
                .categoryId(categoryId)
                .description(description)
                .date(date)
                .build();
        //act
        double result = transferBetweenMembersDTO.getAmount();
        //assert
        assertEquals(result, amount);
    }

    @Test
    @DisplayName("Test to get categoryID")
    void testToGetCategoryID() {
        //arrange
        String senderId = "P-001";
        String receiverId = "P-002";
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String categoryId = "C-001";
        String description = "Transfer money";
        String date = "03/03/2021";

        TransferDTO transferBetweenMembersDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId)
                .receiverId(receiverId)
                .originAccountId(originAccount)
                .destinationAccountId(destinationAccount)
                .amount(amount)
                .categoryId(categoryId)
                .description(description)
                .date(date)
                .build();
        //act
        String result = transferBetweenMembersDTO.getCategoryID();
        //assert
        assertEquals(result, categoryId);
    }

    @Test
    @DisplayName("Test to get description")
    void testToGetDescription() {
        //arrange
        String senderId = "P-001";
        String receiverId = "P-002";
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String categoryId = "C-001";
        String description = "Transfer money";
        String date = "03/03/2021";

        TransferDTO transferBetweenMembersDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId)
                .receiverId(receiverId)
                .originAccountId(originAccount)
                .destinationAccountId(destinationAccount)
                .amount(amount)
                .categoryId(categoryId)
                .description(description)
                .date(date)
                .build();
        //act
        String result = transferBetweenMembersDTO.getDescription();
        //assert
        assertEquals(result, description);
    }

    @Test
    @DisplayName("Test to get date")
    void testToGetDate() {
        //arrange
        String senderId = "P-001";
        String receiverId = "P-002";
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String categoryId = "C-001";
        String description = "Transfer money";
        String date = "03/03/2021";

        TransferDTO transferBetweenMembersDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId)
                .receiverId(receiverId)
                .originAccountId(originAccount)
                .destinationAccountId(destinationAccount)
                .amount(amount)
                .categoryId(categoryId)
                .description(description)
                .date(date)
                .build();
        //act
        String result = transferBetweenMembersDTO.getDate();
        //assert
        assertEquals(result, date);
    }

    @Test
    @DisplayName("Test to set senderId")
    void testToSetSenderId() {
        //arrange
        String senderId = "P-001";
        String receiverId = "P-002";
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String categoryId = "C-001";
        String description = "Transfer money";
        String date = "03/03/2021";

        TransferDTO transferBetweenMembersDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId)
                .receiverId(receiverId)
                .originAccountId(originAccount)
                .destinationAccountId(destinationAccount)
                .amount(amount)
                .categoryId(categoryId)
                .description(description)
                .date(date)
                .build();

        //act
        transferBetweenMembersDTO.setSenderID("P-003");
        String expected = "P-003";
        String result = transferBetweenMembersDTO.getSenderID();

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test to set receiverId")
    void testToSetReceiverId() {
        //arrange
        String senderId = "P-001";
        String receiverId = "P-002";
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String categoryId = "C-001";
        String description = "Transfer money";
        String date = "03/03/2021";

        TransferDTO transferBetweenMembersDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId)
                .receiverId(receiverId)
                .originAccountId(originAccount)
                .destinationAccountId(destinationAccount)
                .amount(amount)
                .categoryId(categoryId)
                .description(description)
                .date(date)
                .build();

        //act
        transferBetweenMembersDTO.setReceiverID("P-003");
        String expected = "P-003";
        String result = transferBetweenMembersDTO.getReceiverID();

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test to set originAccountId")
    void testToSetOriginAccountId() {
        //arrange
        String senderId = "P-001";
        String receiverId = "P-002";
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String categoryId = "C-001";
        String description = "Transfer money";
        String date = "03/03/2021";

        TransferDTO transferBetweenMembersDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId)
                .receiverId(receiverId)
                .originAccountId(originAccount)
                .destinationAccountId(destinationAccount)
                .amount(amount)
                .categoryId(categoryId)
                .description(description)
                .date(date)
                .build();

        //act
        transferBetweenMembersDTO.setOriginAccountID("P-003");
        String expected = "P-003";
        String result = transferBetweenMembersDTO.getOriginAccountID();

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test to set destinationAccountId")
    void testToSetDestinationAccountId() {
        //arrange
        String senderId = "P-001";
        String receiverId = "P-002";
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String categoryId = "C-001";
        String description = "Transfer money";
        String date = "03/03/2021";

        TransferDTO transferBetweenMembersDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId)
                .receiverId(receiverId)
                .originAccountId(originAccount)
                .destinationAccountId(destinationAccount)
                .amount(amount)
                .categoryId(categoryId)
                .description(description)
                .date(date)
                .build();

        //act
        transferBetweenMembersDTO.setDestinationAccountID("P-003");
        String expected = "P-003";
        String result = transferBetweenMembersDTO.getDestinationAccountID();

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test to set amount")
    void testToSetAmount() {
        //arrange
        String senderId = "P-001";
        String receiverId = "P-002";
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String categoryId = "C-001";
        String description = "Transfer money";
        String date = "03/03/2021";

        TransferDTO transferBetweenMembersDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId)
                .receiverId(receiverId)
                .originAccountId(originAccount)
                .destinationAccountId(destinationAccount)
                .amount(amount)
                .categoryId(categoryId)
                .description(description)
                .date(date)
                .build();

        //act
        transferBetweenMembersDTO.setAmount(100);
        double expected = 100;
        double result = transferBetweenMembersDTO.getAmount();

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test to set categoryId")
    void testToSetCategoryId() {
        //arrange
        String senderId = "P-001";
        String receiverId = "P-002";
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String categoryId = "C-001";
        String description = "Transfer money";
        String date = "03/03/2021";

        TransferDTO transferBetweenMembersDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId)
                .receiverId(receiverId)
                .originAccountId(originAccount)
                .destinationAccountId(destinationAccount)
                .amount(amount)
                .categoryId(categoryId)
                .description(description)
                .date(date)
                .build();

        //act
        transferBetweenMembersDTO.setCategoryID("C-003");
        String expected = "C-003";
        String result = transferBetweenMembersDTO.getCategoryID();

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test to set descriptionId")
    void testToSetDescriptionId() {
        //arrange
        String senderId = "P-001";
        String receiverId = "P-002";
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String categoryId = "C-001";
        String description = "Transfer money";
        String date = "03/03/2021";

        TransferDTO transferBetweenMembersDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId)
                .receiverId(receiverId)
                .originAccountId(originAccount)
                .destinationAccountId(destinationAccount)
                .amount(amount)
                .categoryId(categoryId)
                .description(description)
                .date(date)
                .build();

        //act
        transferBetweenMembersDTO.setDescription("D-003");
        String expected = "D-003";
        String result = transferBetweenMembersDTO.getDescription();

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test to set date")
    void testToSetDate() {
        //arrange
        String senderId = "P-001";
        String receiverId = "P-002";
        String originAccount = "A-001";
        String destinationAccount = "A-002";
        double amount = 50;
        String categoryId = "C-001";
        String description = "Transfer money";
        String date = "03/03/2021";

        TransferDTO transferBetweenMembersDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId)
                .receiverId(receiverId)
                .originAccountId(originAccount)
                .destinationAccountId(destinationAccount)
                .amount(amount)
                .categoryId(categoryId)
                .description(description)
                .date(date)
                .build();

        //act
        transferBetweenMembersDTO.setDate("20/03/2021");
        String expected = "20/03/2021";
        String result = transferBetweenMembersDTO.getDate();

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("HashCode test")
    void testEqualsHashCode() {

        //arrange
        String senderId1 = "P-001";
        String receiverId1 = "P-002";
        String originAccount1 = "A-001";
        String destinyAccount1 = "A-002";
        double amount1 = 50;
        String categoryId1 = "C-001";
        String description1 = "Transfer money";
        String date1 = "03/03/2021";

        String senderId2 = "P-001";
        String receiverId2 = "P-002";
        String originAccount2 = "A-001";
        String destinyAccount2 = "A-002";
        double amount2 = 50;
        String categoryId2 = "C-001";
        String description2 = "Transfer money";
        String date2 = "03/03/2021";

        String senderId3 = "P-005";
        String receiverId3 = "P-006";
        String originAccount3 = "A-005";
        String destinyAccount3 = "A-006";
        double amount3 = 50;
        String categoryId3 = "C-001";
        String description3 = "Transfer money";
        String date3 = "03/03/2021";

        //act
        TransferDTO transferBetweenMembersDTO1 = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId1).
                        receiverId(receiverId1).
                        originAccountId(originAccount1).
                        destinationAccountId(destinyAccount1).
                        amount(amount1).
                        categoryId(categoryId1).
                        description(description1).
                        date(date1).
                        build();

        TransferDTO transferBetweenMembersDTO2 = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId2).
                        receiverId(receiverId2).
                        originAccountId(originAccount2).
                        destinationAccountId(destinyAccount2).
                        amount(amount2).
                        categoryId(categoryId2).
                        description(description2).
                        date(date2).
                        build();


        TransferDTO transferBetweenMembersDTO3 = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId3).
                        receiverId(receiverId3).
                        originAccountId(originAccount3).
                        destinationAccountId(destinyAccount3).
                        amount(amount3).
                        categoryId(categoryId3).
                        description(description3).
                        date(date3).
                        build();

        //assert
        assertEquals(transferBetweenMembersDTO1, transferBetweenMembersDTO2);
        assertEquals(transferBetweenMembersDTO1.hashCode(), transferBetweenMembersDTO2.hashCode());
        assertNotEquals(transferBetweenMembersDTO1.hashCode(), transferBetweenMembersDTO3.hashCode());
    }

    @Test
    @DisplayName("Same TransferBetweenMembersDTO")
    void sameTransferBetweenMembersDTO() {

        //arrange
        String senderId1 = "P-001";
        String receiverId1 = "P-002";
        String originAccount1 = "A-001";
        String destinyAccount1 = "A-002";
        double amount1 = 50;
        String categoryId1 = "C-001";
        String description1 = "Transfer money";
        String date1 = "03/03/2021";

        String senderId2 = "P-001";
        String receiverId2 = "P-002";
        String originAccount2 = "A-001";
        String destinyAccount2 = "A-002";
        double amount2 = 50;
        String categoryId2 = "C-001";
        String description2 = "Transfer money";
        String date2 = "03/03/2021";

        //act
        TransferDTO transferBetweenMembersDTO1 = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId1).
                        receiverId(receiverId1).
                        originAccountId(originAccount1).
                        destinationAccountId(destinyAccount1).
                        amount(amount1).
                        categoryId(categoryId1).
                        description(description1).
                        date(date1).
                        build();

        TransferDTO transferBetweenMembersDTO2 = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId2).
                        receiverId(receiverId2).
                        originAccountId(originAccount2).
                        destinationAccountId(destinyAccount2).
                        amount(amount2).
                        categoryId(categoryId2).
                        description(description2).
                        date(date2).
                        build();

        //assert
        assertEquals(transferBetweenMembersDTO1, transferBetweenMembersDTO2);
    }

    @Test
    @DisplayName("Not same TransferBetweenMembersDTO")
    void notSameTransferBetweenMembersDTO() {

        //arrange
        String senderId1 = "P-001";
        String receiverId1 = "P-002";
        String originAccount1 = "A-001";
        String destinyAccount1 = "A-002";
        double amount1 = 50;
        String categoryId1 = "C-001";
        String description1 = "Transfer money";
        String date1 = "03/03/2021";

        String senderId2 = "P-009";
        String receiverId2 = "P-002";
        String originAccount2 = "A-001";
        String destinyAccount2 = "A-002";
        double amount2 = 50;
        String categoryId2 = "C-001";
        String description2 = "Transfer money";
        String date2 = "03/03/2021";

        //act
        TransferDTO transferBetweenMembersDTO1 = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId1).
                        receiverId(receiverId1).
                        originAccountId(originAccount1).
                        destinationAccountId(destinyAccount1).
                        amount(amount1).
                        categoryId(categoryId1).
                        description(description1).
                        date(date1).
                        build();

        TransferDTO transferBetweenMembersDTO2 = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId2).
                        receiverId(receiverId2).
                        originAccountId(originAccount2).
                        destinationAccountId(destinyAccount2).
                        amount(amount2).
                        categoryId(categoryId2).
                        description(description2).
                        date(date2).
                        build();

        //assert
        assertNotEquals(transferBetweenMembersDTO1, transferBetweenMembersDTO2);
    }

    @Test
    @DisplayName("Not equal objects")
    void viewRelationDTONotEqualToCategory() {
        //arrange
        String id = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(id);
        CategoryDesignation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);

        String senderId1 = "P-001";
        String receiverId1 = "P-002";
        String originAccount1 = "A-001";
        String destinyAccount1 = "A-002";
        double amount1 = 50;
        String categoryId1 = "C-001";
        String description1 = "Transfer money";
        String date1 = "03/03/2021";

        TransferDTO transferBetweenMembersDTO1 = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId1).
                        receiverId(receiverId1).
                        originAccountId(originAccount1).
                        destinationAccountId(destinyAccount1).
                        amount(amount1).
                        categoryId(categoryId1).
                        description(description1).
                        date(date1).
                        build();

        //act
        boolean result = transferBetweenMembersDTO1.equals(category);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same instance")
    void sameInstance() {
        //arrange
        String senderId1 = "P-001";
        String receiverId1 = "P-002";
        String originAccount1 = "A-001";
        String destinyAccount1 = "A-002";
        double amount1 = 50;
        String categoryId1 = "C-001";
        String description1 = "Transfer money";
        String date1 = "03/03/2021";

        TransferDTO transferBetweenMembersDTO1 = new TransferDTO.TransferDTOBuilder()
                .senderId(senderId1).
                        receiverId(receiverId1).
                        originAccountId(originAccount1).
                        destinationAccountId(destinyAccount1).
                        amount(amount1).
                        categoryId(categoryId1).
                        description(description1).
                        date(date1).
                        build();

        TransferDTO sameTranferBetweenMebersDTO = transferBetweenMembersDTO1;

        //assert
        assertEquals(transferBetweenMembersDTO1, sameTranferBetweenMebersDTO);
    }
}