package switchtwentytwenty.project.domain.share.id;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountIDTest {

    @Test
    @DisplayName("Test equals with different AccountIDs - successfully")
    void equalsDifferentTest() {

        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountID accountID2 = new AccountID(UUID.randomUUID());
        //act
        boolean result = accountID.equals(accountID2);
        //assert
        assertFalse(result);
    }
    @Test
    @DisplayName("Test equals with same AccountIDs - successfully")
    void equalsSameAccountIDsTest() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        //act
        boolean result = accountID.equals(accountID);
        //assert
        assertTrue(result);
    }
    @Test
    @DisplayName("Test equals with nulls - successfully")
    void equalsNullTest() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        //act
        boolean result = accountID.equals(null);
        //assert
        assertFalse(result);
    }
}