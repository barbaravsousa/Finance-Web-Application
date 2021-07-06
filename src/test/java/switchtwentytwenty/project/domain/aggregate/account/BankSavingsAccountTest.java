package switchtwentytwenty.project.domain.aggregate.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.id.AccountID;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class BankSavingsAccountTest {

    @Test
    @DisplayName("Same Object")
    void sameObject() {
        //arrange
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        BankSavingsAccount account = new BankSavingsAccount(id, designation);
        //act
        BankSavingsAccount sameAccount = account;
        //assert
        assertEquals(account, sameAccount);
    }

    @Test
    @DisplayName("Identical Object")
    void identicalObject() {
        //arrange
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        BankSavingsAccount account = new BankSavingsAccount(id, designation);
        //act
        BankSavingsAccount sameAccount = new BankSavingsAccount(id, designation);
        //assert
        assertEquals(account, sameAccount);
    }

    @Test
    @DisplayName("Null Object")
    void nullObject() {
        //arrange
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        BankSavingsAccount account = new BankSavingsAccount(id, designation);
        //act
        BankSavingsAccount nullAccount = null;
        //assert
        assertNotEquals(account, nullAccount);
    }

    @Test
    @DisplayName("Different Object")
    void differentObject() {
        //arrange
        AccountID id = new AccountID(UUID.randomUUID());
        AccountID differentID = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        AccountDesignation differentDesignation = new AccountDesignation("Other Account");
        BankSavingsAccount account = new BankSavingsAccount(id, designation);
        //act
        BankSavingsAccount differentAccount = new BankSavingsAccount(differentID, differentDesignation);
        //assert
        assertNotEquals(account, differentAccount);
    }

    @Test
    @DisplayName("Different Class")
    void differentClass() {
        //arrange
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        BankSavingsAccount account = new BankSavingsAccount(id, designation);
        //act
        BigDecimal bigDecimal = BigDecimal.ONE;
        //assert
        assertNotEquals(account, bigDecimal);
    }

    @Test
    @DisplayName("Same Hash Code")
    void sameHashCode() {
        //arrange
        int hash1;
        int hash2;
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        BankSavingsAccount account = new BankSavingsAccount(id, designation);
        BankSavingsAccount sameAccount = new BankSavingsAccount(id, designation);
        //act
        hash1 = account.hashCode();
        hash2 = sameAccount.hashCode();
        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Not Same Hash Code")
    void notSameHashCode() {
        //arrange
        int hash1;
        int hash2;
        AccountID id = new AccountID(UUID.randomUUID());
        AccountID differentID = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        AccountDesignation differentDesignation = new AccountDesignation("Other Account");
        BankSavingsAccount account = new BankSavingsAccount(id, designation);
        BankSavingsAccount differentAccount = new BankSavingsAccount(differentID, differentDesignation);
        //act
        hash1 = account.hashCode();
        hash2 = differentAccount.hashCode();
        //assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    @DisplayName("As Same Value")
    void asSameValue() {
        //arrange
        boolean result;
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        BankSavingsAccount account = new BankSavingsAccount(id, designation);
        //act
        BankSavingsAccount sameAccount = new BankSavingsAccount(id, designation);
        result = account.sameValueAs(sameAccount);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Not same values")
    void notSameValues() {
        //arrange
        boolean result;
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        AccountDesignation differentDesignation = new AccountDesignation("Other Account");
        BankSavingsAccount account = new BankSavingsAccount(id, designation);
        //act
        BankSavingsAccount differentAccount = new BankSavingsAccount(id, differentDesignation);
        result = account.sameValueAs(differentAccount);
        //assert
        assertFalse(result);
        assertTrue(account.hasSameID(differentAccount.getID()));
    }

    @Test
    @DisplayName("Not Same ID")
    void notSameID() {
        //arrange
        boolean result;
        AccountID id = new AccountID(UUID.randomUUID());
        AccountID differentID = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        AccountDesignation differentDesignation = new AccountDesignation("Other Account");
        BankSavingsAccount account = new BankSavingsAccount(id, designation);
        //act
        BankSavingsAccount differentAccount = new BankSavingsAccount(differentID, differentDesignation);
        result = account.hasSameID(differentAccount.getID());
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Has Same designation")
    void HasSameDesignation() {
        //arrange
        boolean result;
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        AccountDesignation differentDesignation = new AccountDesignation("My Account");
        BankSavingsAccount account = new BankSavingsAccount(id, designation);
        //act
        result = account.isSameDesignation(differentDesignation);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Account does not have Same designation")
    void DoesNotHaveSameDesignation() {
        //arrange
        boolean result;
        AccountID id = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("My Account");
        AccountDesignation differentDesignation = new AccountDesignation("New Account");
        BankSavingsAccount account = new BankSavingsAccount(id, designation);
        //act
        result = account.isSameDesignation(differentDesignation);
        //assert
        assertFalse(result);
    }

    @Test
    public void isCashAccount() {
        //arrange

        AccountDesignation designation = new AccountDesignation("Bank Savings");
        UUID bankSavingsID = UUID.randomUUID();

        AccountID accountID = new AccountID(bankSavingsID);
        BankSavingsAccount bankSavingsAccount = new BankSavingsAccount(accountID, designation);

        //Act
        boolean result = bankSavingsAccount.isCashAccount();

        //assert

        assertFalse(result);
    }


    @Test
    @DisplayName("Failure withdraw: unsupported Operation")
    void failureToWithdraw() {
        AccountDesignation designation = new AccountDesignation("Bank Savings");
        UUID bankSavingsID = UUID.randomUUID();

        AccountID accountID = new AccountID(bankSavingsID);
        BankSavingsAccount bankSavingsAccount = new BankSavingsAccount(accountID, designation);

        MoneyValue money = new MoneyValue(new BigDecimal(50));
        Exception exception = assertThrows(UnsupportedOperationException.class,
                () -> bankSavingsAccount.withdraw(money));

        String exceptionMessage = exception.getMessage();
        String expectedMessage = "This operation is not supported in this account.";

        assertEquals(exceptionMessage, expectedMessage);
    }

    @Test
    @DisplayName("Failure to deposit: unsupported operation")
    void failureToDeposit() {
        AccountDesignation designation = new AccountDesignation("Bank Savings");
        UUID bankSavingsID = UUID.randomUUID();

        AccountID accountID = new AccountID(bankSavingsID);
        BankSavingsAccount bankSavingsAccount = new BankSavingsAccount(accountID, designation);


        MoneyValue money = new MoneyValue(new BigDecimal(50));
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> bankSavingsAccount.deposit(money));

        String exceptionMessage = exception.getMessage();
        String expectedMessage = "This operation is not supported in this account.";

        assertEquals(exceptionMessage, expectedMessage);
    }

    @Test
    void getAccountTypeBankSavingsAccount() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("my account");
        Account account = new BankSavingsAccount(accountID,designation);
        String expected = Constants.BANK_SAVINGS_ACCOUNT_TYPE;
        //act
        String result = account.getAccountType();
        //assert
        assertEquals(expected,result);
    }
}
