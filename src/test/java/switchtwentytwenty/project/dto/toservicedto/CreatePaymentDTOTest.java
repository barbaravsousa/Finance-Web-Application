package switchtwentytwenty.project.dto.toservicedto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.id.CategoryID;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CreatePaymentDTOTest {


    @Test
    @DisplayName("Test to get familyID")
    void testToGetFamilyId() {
        //arrange
        String familyId = "P-001";
        String personId = "P-002";
        String accountID = "A-001";
        double amount = 50;
        String description = "Transfer money";
        String date = "03/03/2021";
        String categoryId = "C-001";

        CreatePaymentDTO createPaymentDTO = new CreatePaymentDTO.PaymentBuilder()
                .familyID(familyId)
                .personID(personId)
                .accountID(accountID)
                .amount(amount)
                .description(description)
                .date(date)
                .categoryID(categoryId)
                .build();

        //act
        String result = createPaymentDTO.getFamilyID();
        //assert
        assertEquals(familyId, result);
    }

    @Test
    @DisplayName("Test to get personID")
    void testToGetPersonId() {
        //arrange
        String familyId = "P-001";
        String personId = "P-002";
        String accountID = "A-001";
        double amount = 50;
        String description = "Transfer money";
        String date = "03/03/2021";
        String categoryId = "C-001";

        CreatePaymentDTO createPaymentDTO = new CreatePaymentDTO.PaymentBuilder()
                .familyID(familyId)
                .personID(personId)
                .accountID(accountID)
                .amount(amount)
                .description(description)
                .date(date)
                .categoryID(categoryId)
                .build();

        //act
        String result = createPaymentDTO.getPersonID();
        //assert
        assertEquals(personId, result);
    }

    @Test
    @DisplayName("Test to get AccountID")
    void testToGetAccountId() {
        //arrange
        String familyId = "P-001";
        String personId = "P-002";
        String accountID = "A-001";
        double amount = 50;
        String description = "Transfer money";
        String date = "03/03/2021";
        String categoryId = "C-001";

        CreatePaymentDTO createPaymentDTO = new CreatePaymentDTO.PaymentBuilder()
                .familyID(familyId)
                .personID(personId)
                .accountID(accountID)
                .amount(amount)
                .description(description)
                .date(date)
                .categoryID(categoryId)
                .build();

        //act
        String result = createPaymentDTO.getAccountID();
        //assert
        assertEquals(accountID, result);
    }

    @Test
    @DisplayName("Test to get amount")
    void testToGetAmount() {
        //arrange
        String familyId = "P-001";
        String personId = "P-002";
        String accountID = "A-001";
        double amount = 50;
        String description = "Transfer money";
        String date = "03/03/2021";
        String categoryId = "C-001";

        CreatePaymentDTO createPaymentDTO = new CreatePaymentDTO.PaymentBuilder()
                .familyID(familyId)
                .personID(personId)
                .accountID(accountID)
                .amount(amount)
                .description(description)
                .date(date)
                .categoryID(categoryId)
                .build();

        //act
        double result = createPaymentDTO.getAmount();
        //assert
        assertEquals(amount, result);
    }

    @Test
    @DisplayName("Test to get description")
    void testToGetDescription() {
        //arrange
        String familyId = "P-001";
        String personId = "P-002";
        String accountID = "A-001";
        double amount = 50;
        String description = "Transfer money";
        String date = "03/03/2021";
        String categoryId = "C-001";

        CreatePaymentDTO createPaymentDTO = new CreatePaymentDTO.PaymentBuilder()
                .familyID(familyId)
                .personID(personId)
                .accountID(accountID)
                .amount(amount)
                .description(description)
                .date(date)
                .categoryID(categoryId)
                .build();

        //act
        String result = createPaymentDTO.getDescription();
        //assert
        assertEquals(description, result);
    }

    @Test
    @DisplayName("Test to get date")
    void testToGetDate() {
        //arrange
        String familyId = "P-001";
        String personId = "P-002";
        String accountID = "A-001";
        double amount = 50;
        String description = "Transfer money";
        String date = "03/03/2021";
        String categoryId = "C-001";

        CreatePaymentDTO createPaymentDTO = new CreatePaymentDTO.PaymentBuilder()
                .familyID(familyId)
                .personID(personId)
                .accountID(accountID)
                .amount(amount)
                .description(description)
                .date(date)
                .categoryID(categoryId)
                .build();

        //act
        String result = createPaymentDTO.getDate();
        //assert
        assertEquals(date, result);
    }

    @Test
    @DisplayName("Test to get categoryID")
    void testToGetCategoryID() {
        //arrange
        String familyId = "P-001";
        String personId = "P-002";
        String accountID = "A-001";
        double amount = 50;
        String description = "Transfer money";
        String date = "03/03/2021";
        String categoryId = "C-001";

        CreatePaymentDTO createPaymentDTO = new CreatePaymentDTO.PaymentBuilder()
                .familyID(familyId)
                .personID(personId)
                .accountID(accountID)
                .amount(amount)
                .description(description)
                .date(date)
                .categoryID(categoryId)
                .build();

        //act
        String result = createPaymentDTO.getCategoryID();
        //assert
        assertEquals(categoryId, result);
    }


    @Test
    @DisplayName("HashCode test")
    void testEqualsHashCode() {

        //arrange
        String familyId1 = "P-001";
        String personId1 = "P-002";
        String accountID1 = "A-001";
        double amount1 = 50;
        String description1 = "Transfer money";
        String date1 = "03/03/2021";
        String categoryId1 = "C-001";

        String familyId2 = "P-001";
        String personId2 = "P-002";
        String accountID2 = "A-001";
        double amount2 = 50;
        String description2 = "Transfer money";
        String date2 = "03/03/2021";
        String categoryId2 = "C-001";

        String familyId3 = "P-005";
        String personId3 = "P-002";
        String accountID3 = "A-001";
        double amount3 = 50;
        String description3 = "Transfer money";
        String date3 = "03/03/2021";
        String categoryId3 = "C-001";

        //act
        CreatePaymentDTO createPaymentDTO1 = new CreatePaymentDTO.PaymentBuilder()
                .familyID(familyId1)
                .personID(personId1)
                .accountID(accountID1)
                .amount(amount1)
                .description(description1)
                .date(date1)
                .categoryID(categoryId1)
                .build();

        CreatePaymentDTO createPaymentDTO2 = new CreatePaymentDTO.PaymentBuilder()
                .familyID(familyId2)
                .personID(personId2)
                .accountID(accountID2)
                .amount(amount2)
                .description(description2)
                .date(date2)
                .categoryID(categoryId2)
                .build();

        CreatePaymentDTO createPaymentDTO3 = new CreatePaymentDTO.PaymentBuilder()
                .familyID(familyId3)
                .personID(personId3)
                .accountID(accountID3)
                .amount(amount3)
                .description(description3)
                .date(date3)
                .categoryID(categoryId3)
                .build();

        //assert
        assertEquals(createPaymentDTO1, createPaymentDTO2);
        assertEquals(createPaymentDTO1.hashCode(), createPaymentDTO2.hashCode());
        assertNotEquals(createPaymentDTO1.hashCode(), createPaymentDTO3.hashCode());
    }

    @Test
    @DisplayName("Same CreatePaymentDTO")
    void sameCreatePaymentDTO() {

        //arrange
        String familyId1 = "P-001";
        String personId1 = "P-002";
        String accountID1 = "A-001";
        double amount1 = 50;
        String description1 = "Transfer money";
        String date1 = "03/03/2021";
        String categoryId1 = "C-001";

        String familyId2 = "P-001";
        String personId2 = "P-002";
        String accountID2 = "A-001";
        double amount2 = 50;
        String description2 = "Transfer money";
        String date2 = "03/03/2021";
        String categoryId2 = "C-001";

        //act
        CreatePaymentDTO createPaymentDTO1 = new CreatePaymentDTO.PaymentBuilder()
                .familyID(familyId1)
                .personID(personId1)
                .accountID(accountID1)
                .amount(amount1)
                .description(description1)
                .date(date1)
                .categoryID(categoryId1)
                .build();

        CreatePaymentDTO createPaymentDTO2 = new CreatePaymentDTO.PaymentBuilder()
                .familyID(familyId2)
                .personID(personId2)
                .accountID(accountID2)
                .amount(amount2)
                .description(description2)
                .date(date2)
                .categoryID(categoryId2)
                .build();

        //assert
        assertEquals(createPaymentDTO1, createPaymentDTO2);
    }

    @Test
    @DisplayName("Not same CreatePaymentDTO")
    void notSameCreatePaymentDTO() {

        //arrange
        String familyId1 = "P-001";
        String personId1 = "P-002";
        String accountID1 = "A-001";
        double amount1 = 50;
        String description1 = "Transfer money";
        String date1 = "03/03/2021";
        String categoryId1 = "C-001";

        String familyId2 = "P-005";
        String personId2 = "P-002";
        String accountID2 = "A-001";
        double amount2 = 50;
        String description2 = "Transfer money";
        String date2 = "03/03/2021";
        String categoryId2 = "C-001";

        //act
        CreatePaymentDTO createPaymentDTO1 = new CreatePaymentDTO.PaymentBuilder()
                .familyID(familyId1)
                .personID(personId1)
                .accountID(accountID1)
                .amount(amount1)
                .description(description1)
                .date(date1)
                .categoryID(categoryId1)
                .build();

        CreatePaymentDTO createPaymentDTO2 = new CreatePaymentDTO.PaymentBuilder()
                .familyID(familyId2)
                .personID(personId2)
                .accountID(accountID2)
                .amount(amount2)
                .description(description2)
                .date(date2)
                .categoryID(categoryId2)
                .build();

        //assert
        assertNotEquals(createPaymentDTO1, createPaymentDTO2);
    }

    @Test
    @DisplayName("Not equal objects")
    void createPaymentDTONotEqualToCategory() {
        //arrange
        String id = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(id);
        CategoryDesignation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);

        String familyId1 = "P-001";
        String personId1 = "P-002";
        String accountID1 = "A-001";
        double amount1 = 50;
        String description1 = "Transfer money";
        String date1 = "03/03/2021";
        String categoryId1 = "C-001";

        CreatePaymentDTO createPaymentDTO1 = new CreatePaymentDTO.PaymentBuilder()
                .familyID(familyId1)
                .personID(personId1)
                .accountID(accountID1)
                .amount(amount1)
                .description(description1)
                .date(date1)
                .categoryID(categoryId1)
                .build();

        //act
        boolean result = createPaymentDTO1.equals(category);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same instance")
    void sameInstance() {
        //arrange
        String familyId1 = "P-001";
        String personId1 = "P-002";
        String accountID1 = "A-001";
        double amount1 = 50;
        String description1 = "Transfer money";
        String date1 = "03/03/2021";
        String categoryId1 = "C-001";

        CreatePaymentDTO createPaymentDTO1 = new CreatePaymentDTO.PaymentBuilder()
                .familyID(familyId1)
                .personID(personId1)
                .accountID(accountID1)
                .amount(amount1)
                .description(description1)
                .date(date1)
                .categoryID(categoryId1)
                .build();

        CreatePaymentDTO sameCreatePaymentDTO = createPaymentDTO1;

        //assert
        assertEquals(createPaymentDTO1, sameCreatePaymentDTO);
    }
}
