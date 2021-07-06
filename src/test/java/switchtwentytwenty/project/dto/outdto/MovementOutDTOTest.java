package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class MovementOutDTOTest {

    @Test
    @DisplayName("Create and get all DTO' attribute")
    void createAndGetDTO() {
        //arrange
        float amount = 20.50f;
        String accountID = UUID.randomUUID().toString();
        String movementType = Constants.DEBIT;
        String date = "2021-03-01";
        String description = "Dildo";
        String category = "Food";
        float balance = 100f;
        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(amount)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDescription(description)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balance)
                .build();

        //act
        double amountResult = dto.getAmount();
        String accountIDResult = dto.getAccountID();
        String movementTypeResult = dto.getMovementType();
        String dateResult = dto.getDate();
        String descriptionResult = dto.getDescription();
        String categoryResult = dto.getCategory();
        double balanceResult = dto.getBalanceToThisDate();

        //assert
        assertEquals(amount, amountResult);
        assertEquals(accountID, accountIDResult);
        assertEquals(movementType, movementTypeResult);
        assertEquals(date, dateResult);
        assertEquals(description, descriptionResult);
        assertEquals(description, descriptionResult);
        assertEquals(category, categoryResult);
        assertEquals(balance, balanceResult);
    }

    @Test
    @DisplayName("Same Object")
    void sameObject() {
        //arrange
        double amount = 2000;
        String accountID = UUID.randomUUID().toString();
        String movementType = Constants.CREDIT;
        String date = "2020-07-14";
        String category = UUID.randomUUID().toString();
        double balanceToThisDate = 10000;
        String description = "Macbook";

        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(amount)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        //act
        MovementOutDTO otherDto = dto;

        //assert
        assertEquals(otherDto, dto);
    }

    @Test
    @DisplayName("Identical Object")
    void identicalObject() {
        //arrange
        double amount = 2000;
        String accountID = UUID.randomUUID().toString();
        String movementType = Constants.CREDIT;
        String date = "2020-07-14";
        String category = UUID.randomUUID().toString();
        double balanceToThisDate = 10000;
        String description = "Macbook";

        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(amount)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        //act
        MovementOutDTO otherDto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(amount)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        //assert
        assertEquals(otherDto, dto);
    }

    @Test
    @DisplayName("Different Object")
    void differentObject() {
        //arrange
        double amount = 2000;
        String accountID = UUID.randomUUID().toString();
        String movementType = Constants.CREDIT;
        String date = "2020-07-14";
        String category = UUID.randomUUID().toString();
        double balanceToThisDate = 10000;
        double otherBalanceToThisDate = 11000;
        String description = "Macbook";

        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(amount)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        //act
        MovementOutDTO otherDto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(amount)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(otherBalanceToThisDate)
                .withDescription(description)
                .build();

        //assert
        assertNotEquals(otherDto, dto);
    }

    @Test
    @DisplayName("Different Class")
    void differentClass() {
        //arrange
        double amount = 2000;
        String accountID = UUID.randomUUID().toString();
        String movementType = Constants.CREDIT;
        String date = "2020-07-14";
        String category = UUID.randomUUID().toString();
        double balanceToThisDate = 10000;
        String description = "Macbook";

        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(amount)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        //act
        BigDecimal bigDecimal = BigDecimal.ONE;

        //assert
        assertNotEquals(bigDecimal, dto);
    }

    @Test
    @DisplayName("Null Object")
    void nullObject() {
        //arrange
        double amount = 2000;
        String accountID = UUID.randomUUID().toString();
        String movementType = Constants.CREDIT;
        String date = "2020-07-14";
        String category = UUID.randomUUID().toString();
        double balanceToThisDate = 10000;
        String description = "Macbook";

        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(amount)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        //act
        MovementOutDTO otherDto = null;

        //assert
        assertNotEquals(otherDto, dto);
    }

    @Test
    @DisplayName("Different Hash Code")
    void differentHashCode() {
        //arrange
        double amount = 2000;
        String accountID = UUID.randomUUID().toString();
        String movementType = Constants.CREDIT;
        String date = "2020-07-14";
        String category = UUID.randomUUID().toString();
        double balanceToThisDate = 10000;
        double otherBalanceToThisDate = 11000;
        String description = "Macbook";

        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(amount)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        MovementOutDTO otherDto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(amount)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(otherBalanceToThisDate)
                .withDescription(description)
                .build();

        //act
        int hash1 = dto.hashCode();
        int hash2 = otherDto.hashCode();

        //assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Same Hash Code")
    void sameHashCode() {
        //arrange
        double amount = 2000;
        String accountID = UUID.randomUUID().toString();
        String movementType = Constants.CREDIT;
        String date = "2020-07-14";
        String category = UUID.randomUUID().toString();
        double balanceToThisDate = 10000;
        String description = "Macbook";

        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(amount)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        MovementOutDTO otherDto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(amount)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        //act
        int hash1 = dto.hashCode();
        int hash2 = otherDto.hashCode();

        //assert
        assertEquals(hash1, hash2);
    }


    @Test
    @DisplayName("Test equals with null")
    void OutMovementDTOEqualsWithNullTest() {
        //arrange
        double value = 10.0;
        String accountID = UUID.randomUUID().toString();
        String movementType = Constants.DEBIT;
        String date = "2002-10-02";
        String category = UUID.randomUUID().toString();
        double balanceToThisDate = 1000;
        String description = "trips";

        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(value)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        //act
        boolean result = dto.equals(null);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals with a different object")
    void OutMovementDTOEqualsWithADifferentObject() {
        //arrange
        double value = 10.0;
        String accountID = UUID.randomUUID().toString();
        String movementType = Constants.DEBIT;
        String date = "2002-10-02";
        String category = UUID.randomUUID().toString();
        double balanceToThisDate = 1000;
        String description = "trips";

        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(value)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();
        BigDecimal number = new BigDecimal(10);

        //act
        boolean result = dto.equals(number);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals with a different dates")
    void OutMovementDTOEqualsWithADifferentDates() {
        //arrange
        double value = 10.0;
        String accountID = UUID.randomUUID().toString();
        String movementType = Constants.DEBIT;
        String date = "2002-10-02";
        String secondDate = "2002-11-02";
        String category = UUID.randomUUID().toString();
        double balanceToThisDate = 1000;
        String description = "trips";

        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(value)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        MovementOutDTO secondDto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(value)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(secondDate)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        //act
        boolean result = dto.equals(secondDto);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals with a different values")
    void OutMovementDTOEqualsWithADifferentAmounts() {
        //arrange
        double value = 10.0;
        double secondValue = 11;
        String accountID = UUID.randomUUID().toString();
        String movementType = Constants.DEBIT;
        String date = "2002-10-02";
        String category = UUID.randomUUID().toString();
        double balanceToThisDate = 1000;
        String description = "trips";

        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(value)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        MovementOutDTO secondDto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(secondValue)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        //act
        boolean result = dto.equals(secondDto);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals with a different movement values")
    void OutMovementDTOEqualsWithADifferentMovementTypes() {
        //arrange
        double value = 10.0;
        String accountID = UUID.randomUUID().toString();
        String movementType = Constants.DEBIT;
        String secondMovementType = Constants.CREDIT;
        String date = "2002-10-02";
        String category = UUID.randomUUID().toString();
        double balanceToThisDate = 1000;
        String description = "trips";

        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(value)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        MovementOutDTO secondDto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(value)
                .withAccountID(accountID)
                .withMovementType(secondMovementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        //act
        boolean result = dto.equals(secondDto);

        //assert
        assertFalse(result);
    }


    @Test
    @DisplayName("Test equals with a different categories")
    void OutMovementDTOEqualsWithADifferentCategories() {
        //arrange
        double value = 10.0;
        String accountID = UUID.randomUUID().toString();
        String movementType = Constants.DEBIT;
        String secondMovementType = Constants.CREDIT;
        String date = "2002-10-02";
        String category = UUID.randomUUID().toString();
        String secondCategory = UUID.randomUUID().toString();
        double balanceToThisDate = 1000;
        String description = "trips";

        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(value)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        MovementOutDTO secondDto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(value)
                .withAccountID(accountID)
                .withMovementType(secondMovementType)
                .withDate(date)
                .withCategory(secondCategory)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        //act
        boolean result = dto.equals(secondDto);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals with a different balances")
    void OutMovementDTOEqualsWithADifferentBalance() {
        //arrange
        double value = 10.0;
        String accountID = UUID.randomUUID().toString();
        String movementType = Constants.DEBIT;
        String secondMovementType = Constants.CREDIT;
        String date = "2002-10-02";
        String category = UUID.randomUUID().toString();
        String secondCategory = UUID.randomUUID().toString();
        double balanceToThisDate = 1000;
        double secondBalance = 1001;
        String description = "trips";


        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(value)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        MovementOutDTO secondDto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(value)
                .withAccountID(accountID)
                .withMovementType(secondMovementType)
                .withDate(date)
                .withCategory(secondCategory)
                .withBalanceToThisDate(secondBalance)
                .withDescription(description)
                .build();

        //act
        boolean result = dto.equals(secondDto);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals with a different balances")
    void OutMovementDTOEqualsWithADifferentDescription() {
        //arrange
        double value = 10.0;
        String accountID = UUID.randomUUID().toString();
        String movementType = Constants.DEBIT;
        String secondMovementType = Constants.CREDIT;
        String date = "2002-10-02";
        String category = UUID.randomUUID().toString();
        String secondCategory = UUID.randomUUID().toString();
        double balanceToThisDate = 1000;
        double secondBalance = 1001;
        String description = "trips";
        String secondDescription = "cashTrips";

        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(value)
                .withAccountID(accountID)
                .withMovementType(movementType)
                .withDate(date)
                .withCategory(category)
                .withBalanceToThisDate(balanceToThisDate)
                .withDescription(description)
                .build();

        MovementOutDTO secondDto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(value)
                .withAccountID(accountID)
                .withMovementType(secondMovementType)
                .withDate(date)
                .withCategory(secondCategory)
                .withBalanceToThisDate(secondBalance)
                .withDescription(secondDescription)
                .build();

        //act
        boolean result = dto.equals(secondDto);

        //assert
        assertFalse(result);
    }


}
