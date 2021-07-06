package switchtwentytwenty.project.dto.todomaindto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.id.AccountID;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountJpaToDomainDTOTest {

    @Test
    @DisplayName("Equals: same instance")
    void testEquals_SameInstance() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        AccountJpaToDomainDTO dto = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType, amount);
        //act - assert
        assertEquals(dto, dto);
    }

    @Test
    @DisplayName("Equals: null")
    void testEquals_Null() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        AccountJpaToDomainDTO dto = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType, amount);
        //act
        assertNotEquals(null, dto);
    }

    @Test
    @DisplayName("Equals: other class type")
    void testEquals_OtherClassType() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        AccountJpaToDomainDTO dto = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType, amount);
        //act
        assertNotEquals("", dto);
    }

    @Test
    @DisplayName("Equals: true")
    void testEquals_True() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        AccountJpaToDomainDTO dto = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType, amount);
        AccountJpaToDomainDTO other = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType, amount);
        //act
        boolean result = dto.equals(other);
        //arrange
        assertTrue(result);
    }

    @Test
    @DisplayName("Equals: false")
    void testEquals_False() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        String otherAccountType = Constants.CREDIT_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        AccountJpaToDomainDTO dto = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType, amount);
        AccountJpaToDomainDTO other = new AccountJpaToDomainDTO(accountID, accountDesignation, otherAccountType, amount);
        //act
        boolean result = dto.equals(other);
        //arrange
        assertFalse(result);
    }

    @Test
    @DisplayName("Equals: false")
    void testEquals_DifferentAccountID() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountID secondAccountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        AccountJpaToDomainDTO dto = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType, amount);
        AccountJpaToDomainDTO other = new AccountJpaToDomainDTO(secondAccountID, accountDesignation, accountType, amount);
        //act
        boolean result = dto.equals(other);
        //arrange
        assertFalse(result);
    }


    @Test
    @DisplayName("Equals: false")
    void testEquals_DifferentAccountDesignation() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        AccountDesignation secondAccountDesignation = new AccountDesignation("second Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        AccountJpaToDomainDTO dto = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType, amount);
        AccountJpaToDomainDTO other = new AccountJpaToDomainDTO(accountID, secondAccountDesignation, accountType, amount);
        //act
        boolean result = dto.equals(other);
        //arrange
        assertFalse(result);
    }

    @Test
    @DisplayName("Equals: false")
    void testEquals_DifferentCashAmount() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        AccountDesignation secondAccountDesignation = new AccountDesignation("second Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        MoneyValue secondAmount = new MoneyValue(BigDecimal.valueOf(28.26));
        AccountJpaToDomainDTO dto = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType, amount);
        AccountJpaToDomainDTO other = new AccountJpaToDomainDTO(accountID, secondAccountDesignation, accountType, secondAmount);
        //act
        boolean result = dto.equals(other);
        //arrange
        assertFalse(result);
    }

    @Test
    @DisplayName("Equals: false")
    void testEquals_ForNull() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        AccountJpaToDomainDTO dto = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType, amount);
        //act
        boolean result = dto.equals(null);
        //arrange
        assertFalse(result);
    }

    @Test
    @DisplayName("Equals: false")
    void testEquals_ForDifferentObjects() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        AccountJpaToDomainDTO dto = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType, amount);
        double value = 20;
        //act
        boolean result = dto.equals(value);
        //arrange
        assertFalse(result);
    }

    @Test
    @DisplayName("Equals: false")
    void testEquals_ForDifferentCashAmount() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        MoneyValue secondAmount = new MoneyValue(BigDecimal.valueOf(30));
        AccountJpaToDomainDTO dto = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType, amount);
        AccountJpaToDomainDTO other = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType, secondAmount);
        //act
        boolean result = dto.equals(other);
        //arrange
        assertFalse(result);
    }


    @Test
    @DisplayName("HashCode: true")
    void testHashCode_True() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        AccountJpaToDomainDTO dto = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType, amount);
        AccountJpaToDomainDTO other = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType, amount);
        //act
        int hashCode = dto.hashCode();
        int otherHashCode = other.hashCode();
        //arrange
        assertEquals(otherHashCode, hashCode);
    }

    @Test
    @DisplayName("HashCode: false")
    void testHashCode_False() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        String otherAccountType = Constants.CREDIT_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        AccountJpaToDomainDTO dto = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType, amount);
        AccountJpaToDomainDTO other = new AccountJpaToDomainDTO(accountID, accountDesignation, otherAccountType, amount);
        //act
        int hashCode = dto.hashCode();
        int otherHashCode = other.hashCode();
        //arrange
        assertNotEquals(otherHashCode, hashCode);
    }

    @Test
    @DisplayName("Failure get cash amount")
    void failureGetCashAmount() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        AccountJpaToDomainDTO dto = new AccountJpaToDomainDTO(accountID, accountDesignation, accountType);
        //act - assert
        assertThrows(NullPointerException.class, dto::getCashAmount);
    }
}