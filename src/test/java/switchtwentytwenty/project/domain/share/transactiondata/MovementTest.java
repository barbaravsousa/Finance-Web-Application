package switchtwentytwenty.project.domain.share.transactiondata;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;

import java.math.BigDecimal;
import java.util.UUID;

public class MovementTest {

    @Test
    @DisplayName("Not equal objects")
    public void movementNotEqualToFamily() throws Exception {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        MovementType movementType = new MovementType(Constants.CREDIT);
        MoneyValue value = new MoneyValue(new BigDecimal(100));
        Movement movement = new Movement(accountID, movementType, value);
        Email administratorID = new Email("alan_turing@gmail.com");
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        FamilyName familyName = new FamilyName("Turing");
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID,new LedgerID(UUID.randomUUID()),administratorID,familyName);
        Family family = FamilyFactory.create(familyDTO);
        boolean result;
        //act
        result = movement.equals(family);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("same instance")
    public void sameInstance() throws Exception {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        MovementType movementType = new MovementType(Constants.CREDIT);
        MoneyValue value = new MoneyValue(new BigDecimal(100));
        Movement movement = new Movement(accountID, movementType, value);
        Movement sameMovement = movement;
        boolean result;
        //act
        result = movement.equals(sameMovement);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Compare to null")
    public void compareToNull() throws Exception {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        MovementType movementType = new MovementType(Constants.CREDIT);
        MoneyValue value = new MoneyValue(new BigDecimal(100));
        Movement movement = new Movement(accountID, movementType, value);
        boolean result;
        //act
        result = movement.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Equal movements")
    void equalMovements() throws Exception {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        MovementType type = new MovementType(Constants.DEBIT);
        MoneyValue amount = new MoneyValue(BigDecimal.ONE);

        Movement movement = new Movement(accountID, type, amount);

        //act
        Movement sameMovement = new Movement(accountID, type, amount);

        //assert
        assertEquals(sameMovement, movement);
    }

    @Test
    @DisplayName("identical instance")
    public void identicalInstance() throws Exception {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        MovementType movementType = new MovementType(Constants.CREDIT);
        MoneyValue value = new MoneyValue(new BigDecimal(100));
        Movement movement = new Movement(accountID, movementType, value);
        Movement sameMovement = new Movement(accountID, movementType, value);
        boolean result;
        //act
        result = movement.equals(sameMovement);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("different instance")
    public void differentObjects() throws Exception {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountID otherAccountID = new AccountID(UUID.randomUUID());
        MovementType movementType = new MovementType(Constants.CREDIT);
        MoneyValue value = new MoneyValue(new BigDecimal(100));
        Movement movement = new Movement(accountID, movementType, value);
        Movement sameMovement = new Movement(otherAccountID, movementType, value);
        boolean result;
        //act
        result = movement.equals(sameMovement);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("different instance")
    public void differentObjects_differentType() throws Exception {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        MovementType movementType = new MovementType(Constants.CREDIT);
        MovementType movementType2 = new MovementType(Constants.DEBIT);
        MoneyValue value = new MoneyValue(new BigDecimal(100));
        Movement movement = new Movement(accountID, movementType, value);
        Movement sameMovement = new Movement(accountID, movementType2, value);
        boolean result;
        //act
        result = movement.equals(sameMovement);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("different instance")
    public void differentObjects_differentvALUE() throws Exception {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        MovementType movementType = new MovementType(Constants.CREDIT);
        MoneyValue value = new MoneyValue(new BigDecimal(100));
        MoneyValue value2 = new MoneyValue(new BigDecimal(500));
        Movement movement = new Movement(accountID, movementType, value);
        Movement sameMovement = new Movement(accountID, movementType, value2);
        boolean result;
        //act
        result = movement.equals(sameMovement);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("HashCode test")
    public void testEqualsHashCode() throws Exception {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        MovementType type1 = new MovementType(Constants.DEBIT);
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(50));
        MovementType type2 = new MovementType(Constants.CREDIT);

        //act
        Movement movement1 = new Movement(accountID, type1, amount);
        Movement movement2 = new Movement(accountID, type1, amount);
        Movement movement3 = new Movement(accountID, type2, amount);

        //assert
        assertEquals(movement1, movement2);
        assertEquals(movement1.hashCode(), movement2.hashCode());
        assertNotEquals(movement1.hashCode(), movement3.hashCode());
    }

    @Test
    @DisplayName("Check if is from teh account - sucess")
    void isFromAccount() throws InvalidMovementTypeException {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        MovementType type = new MovementType(Constants.DEBIT);
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(50));

        Movement movement = new Movement(accountID,type,amount);

        //act
        boolean result = movement.isFromAccount(accountID);
        //assert
        assertTrue(result);

    }

    @Test
    @DisplayName("Check if is from the account - unsucessful")
    void isNotFromAccount() throws InvalidMovementTypeException {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountID accountID1 = new AccountID(UUID.randomUUID());
        MovementType type = new MovementType(Constants.DEBIT);
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(50));

        Movement movement = new Movement(accountID,type,amount);

        //act
        boolean result = movement.isFromAccount(accountID1);
        //assert
        assertFalse(result);

    }



}
