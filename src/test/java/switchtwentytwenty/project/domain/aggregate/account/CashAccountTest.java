package switchtwentytwenty.project.domain.aggregate.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.transactiondata.Movement;
import switchtwentytwenty.project.domain.share.transactiondata.MovementType;
import switchtwentytwenty.project.exception.AccountNotCreatedException;
import switchtwentytwenty.project.exception.DepositNotPossibleException;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;
import switchtwentytwenty.project.exception.WithdrawNotPossibleException;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CashAccountTest {

    @Test
    public void successCreatingNewCashAccount() throws Exception {

        UUID cashAccount = UUID.randomUUID();

        AccountID cashAccountID = new AccountID(cashAccount);

        MoneyValue initialAmount = new MoneyValue(new BigDecimal(30));

        AccountDesignation accountDesignation = new AccountDesignation("cash");

        CashAccount firstCashAccount = new CashAccount(cashAccountID, initialAmount, accountDesignation);

        assertNotNull(firstCashAccount);

    }

    @Test
    public void failureCreatingNewCashAccount_NegativeAmount() {

        UUID cashAccount = UUID.randomUUID();

        AccountID cashAccountID = new AccountID(cashAccount);

        MoneyValue initialAmount = new MoneyValue(new BigDecimal(-10));

        AccountDesignation accountDesignation = new AccountDesignation("cash");

        assertThrows(AccountNotCreatedException.class,() ->  new CashAccount(cashAccountID, initialAmount, accountDesignation));

    }

    @Test
    public void failureCreatingNewCashAccount_InvalidDesignation(){


        assertThrows(IllegalArgumentException.class, () -> new AccountDesignation("c@sh"));

    }

    @Test
    @DisplayName("Failure withdraw: negative amount ")
    public void failureWithdraw_NegativeAmount() throws Exception {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(10));
        MoneyValue withdrawAmount = new MoneyValue(new BigDecimal(-100));
        UUID cashAccountID = UUID.randomUUID();

        AccountID accountID = new AccountID(cashAccountID);

        AccountDesignation designation = new AccountDesignation("Food");

        CashAccount cashAccount = new CashAccount(accountID, initialAmount, designation);
        //act and assert
        assertThrows(WithdrawNotPossibleException.class, () -> cashAccount.withdraw(withdrawAmount));
    }

    @Test
    @DisplayName("Withdraw a positive amount")
    public void withdrawAPositiveAmount() throws Exception {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(10));
        MoneyValue withdrawAmount = new MoneyValue(new BigDecimal(100));
        UUID cashAccountID = UUID.randomUUID();

        AccountID accountID = new AccountID(cashAccountID);

        AccountDesignation designation = new AccountDesignation("Food");

        CashAccount cashAccount = new CashAccount(accountID, initialAmount, designation);
        //act and assert
        assertThrows(WithdrawNotPossibleException.class, () -> cashAccount.withdraw(withdrawAmount));
    }

    @Test
    @DisplayName("Withdraw successfully")
    public void withdraw() throws Exception {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(10));
        MoneyValue withdrawAmount = new MoneyValue(new BigDecimal(5));
        UUID cashAccountID = UUID.randomUUID();

        AccountID accountID = new AccountID(cashAccountID);
        AccountDesignation designation = new AccountDesignation("Food");
        CashAccount cashAccount = new CashAccount(accountID, initialAmount, designation);
        MoneyValue expected = new MoneyValue(BigDecimal.valueOf(5));
        //act
        cashAccount.withdraw(withdrawAmount);
        MoneyValue balance = cashAccount.getBalance();
        //assert

        assertEquals(expected, balance);
    }

    @Test
    @DisplayName("Withdraw successfully")
    public void withdrawAllTheMoney() throws Exception {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(10));
        MoneyValue withdrawAmount = new MoneyValue(new BigDecimal(10));
        UUID cashAccountID = UUID.randomUUID();

        AccountID accountID = new AccountID(cashAccountID);
        AccountDesignation designation = new AccountDesignation("Food");
        CashAccount cashAccount = new CashAccount(accountID, initialAmount, designation);
        MoneyValue expected = new MoneyValue(BigDecimal.valueOf(0));
        //act
        cashAccount.withdraw(withdrawAmount);
        MoneyValue balance = cashAccount.getBalance();
        //assert

        assertEquals(expected, balance);
    }

    @Test
    public void depositInvalidAmount() throws Exception {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(100));
        MoneyValue amount = new MoneyValue(new BigDecimal(-30));
        AccountDesignation designation = new AccountDesignation("Food");
        UUID cashAccountID = UUID.randomUUID();

        AccountID accountID = new AccountID(cashAccountID);
        CashAccount cashAccount = new CashAccount(accountID, initialAmount, designation);

        assertThrows(DepositNotPossibleException.class, () -> cashAccount.deposit(amount));

    }

    @Test
    public void depositValidAmount() throws Exception {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(100));
        MoneyValue amount = new MoneyValue(new BigDecimal(30));
        AccountDesignation designation = new AccountDesignation("Food");
        UUID cashAccountID = UUID.randomUUID();

        AccountID accountID = new AccountID(cashAccountID);
        CashAccount cashAccount = new CashAccount(accountID, initialAmount, designation);
        MoneyValue expected = new MoneyValue(BigDecimal.valueOf(130));

        //Act
        cashAccount.deposit(amount);
        MoneyValue balance = cashAccount.getBalance();
        //assert

        assertEquals(expected, balance);


    }

    @Test
    public void isCashAccount() throws Exception {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(100));
        AccountDesignation designation = new AccountDesignation("Food");
        UUID cashAccountID = UUID.randomUUID();

        AccountID accountID = new AccountID(cashAccountID);
        CashAccount cashAccount = new CashAccount(accountID, initialAmount, designation);

        //Act
        boolean result = cashAccount.isCashAccount();

        //assert

        assertTrue(result);
    }

    @Test
    @DisplayName("Is same designation ")
    public void isSameDesignation() throws Exception {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(10));
        AccountDesignation designation = new AccountDesignation("Food");
        UUID cashAccountID = UUID.randomUUID();
        AccountDesignation accountDesignation = new AccountDesignation("Food");

        AccountID accountID = new AccountID(cashAccountID);
        CashAccount cashAccount = new CashAccount(accountID, initialAmount, designation);
        boolean result;
        //act
        result = cashAccount.isSameDesignation(accountDesignation);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Is not same designation ")
    public void isNotSameDesignation() throws Exception {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(10));
        AccountDesignation designation = new AccountDesignation("Food");
        UUID cashAccountID = UUID.randomUUID();
        AccountDesignation notSameDesignation = new AccountDesignation("Beer");

        AccountID accountID = new AccountID(cashAccountID);
        CashAccount cashAccount = new CashAccount(accountID, initialAmount, designation);
        boolean result;
        //act
        result = cashAccount.isSameDesignation(notSameDesignation);
        //assert
        assertFalse(result);
    }
    @Test
    @DisplayName("Is same Instance")
    public void isSameInstance() throws Exception {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(10));
        AccountDesignation designation = new AccountDesignation("Food");
        UUID cashAccountID = UUID.randomUUID();

        AccountID accountID = new AccountID(cashAccountID);
        CashAccount cashAccount = new CashAccount(accountID, initialAmount, designation);
        //act
        boolean result = cashAccount.equals(cashAccount);
        //assert
        assertTrue(result);
        assertEquals(cashAccount.hashCode(), cashAccount.hashCode());
    }

    @Test
    @DisplayName("Is not equals : null")
    public void isNotEquals_Null() throws Exception {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(10));
        AccountDesignation designation = new AccountDesignation("Food");
        UUID cashAccountID = UUID.randomUUID();

        AccountID accountID = new AccountID(cashAccountID);
        CashAccount cashAccount = new CashAccount(accountID,initialAmount, designation);
        //act
        boolean result = cashAccount.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Is not same Object")
    public void isNotSameObject() throws Exception {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(10));
        AccountDesignation designation = new AccountDesignation("Food");
        UUID cashAccountID = UUID.randomUUID();

        AccountID accountID = new AccountID(cashAccountID);
        CashAccount cashAccount = new CashAccount(accountID, initialAmount, designation);
        UUID cashAccountID1 = UUID.randomUUID();

        AccountID accountID1 = new AccountID(cashAccountID1);
        CashAccount cashAccount1 = new CashAccount(accountID1, initialAmount, designation);
        boolean result;
        //act
        result = cashAccount.equals(cashAccount1);
        //assert
        assertFalse(result);
        assertNotEquals(cashAccount.hashCode(), cashAccount1.hashCode());
    }

    @Test
    @DisplayName("Is the same cash account")
    public void equalAccount() throws Exception {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(10));
        UUID cashAccountID = UUID.randomUUID();
        AccountID accountID = new AccountID(cashAccountID);
        AccountDesignation designation = new AccountDesignation("Food");
        AccountDesignation designation1 = new AccountDesignation("Beer");

        CashAccount cashAccount1 = new CashAccount(accountID, initialAmount, designation);
        CashAccount cashAccount2 = new CashAccount(accountID, initialAmount, designation1);
        boolean result;
        //act
        result = cashAccount1.equals(cashAccount2);
        //assert
        assertTrue(result);
        assertEquals(cashAccount1.hashCode(), cashAccount2.hashCode());
    }

    @Test
    @DisplayName("Is not the same cash account")
    public void notEqualAccount() throws Exception {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(10));
        UUID cashAccountID = UUID.randomUUID();
        AccountID accountID = new AccountID(cashAccountID);
        UUID cashAccountID1 = UUID.randomUUID();
        AccountID accountID1 = new AccountID(cashAccountID1);
        AccountDesignation designation = new AccountDesignation("Food");

        CashAccount cashAccount1 = new CashAccount(accountID, initialAmount, designation);
        CashAccount cashAccount2 = new CashAccount(accountID1, initialAmount, designation);

        boolean result;
        //act
        result = cashAccount1.equals(cashAccount2);
        //assert
        assertFalse(result);
        assertNotEquals(cashAccount1.hashCode(), cashAccount2.hashCode());
    }

    @Test
    @DisplayName("Is not the same cash account")
    public void notTheSameAccountType() throws AccountNotCreatedException {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(10));
        UUID cashAccountID = UUID.randomUUID();
        AccountID accountID = new AccountID(cashAccountID);
        UUID cashAccountID1 = UUID.randomUUID();
        AccountID accountID1 = new AccountID(cashAccountID1);
        AccountDesignation designation = new AccountDesignation("Food");

        CashAccount cashAccount1 = new CashAccount(accountID, initialAmount, designation);
        BankSavingsAccount cashAccount2 = new BankSavingsAccount(accountID1, designation);

        boolean result;
        //act
        result = cashAccount1.equals(cashAccount2);
        //assert
        assertFalse(result);
        assertNotEquals(cashAccount1.hashCode(), cashAccount2.hashCode());
    }


    @Test
    @DisplayName("Is not the same cash account")
    public void sameAccount() throws Exception {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(10));
        UUID cashAccountID = UUID.randomUUID();
        AccountID accountID = new AccountID(cashAccountID);
        AccountDesignation designation = new AccountDesignation("Food");

        CashAccount cashAccount1 = new CashAccount(accountID, initialAmount, designation);
        CashAccount cashAccount2 = new CashAccount(accountID, initialAmount, designation);

        boolean result;
        //act
        result = cashAccount1.sameValueAs(cashAccount2);
        //assert
        assertTrue(result);
        assertEquals(cashAccount1.hashCode(), cashAccount2.hashCode());
    }

    @Test
    @DisplayName("Is not the same cash account")
    public void notSameAccount() throws Exception {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(10));
        UUID cashAccountID = UUID.randomUUID();
        AccountID accountID = new AccountID(cashAccountID);
        AccountDesignation designation = new AccountDesignation("Food");
        AccountDesignation designation1 = new AccountDesignation("Beer");

        CashAccount cashAccount1 = new CashAccount(accountID, initialAmount, designation);
        CashAccount cashAccount2 = new CashAccount(accountID, initialAmount, designation1);

        boolean result;
        //act
        result = cashAccount1.sameValueAs(cashAccount2);
        //assert
        assertFalse(result);
        assertEquals(cashAccount1.hashCode(), cashAccount2.hashCode());
    }

    @Test
    void getAccountTypeCashAccount() throws Exception {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation designation = new AccountDesignation("my account");
        MoneyValue amount = new MoneyValue(new BigDecimal(100));
        Account account = new CashAccount(accountID,amount,designation);
        String expected = Constants.CASH_ACCOUNT_TYPE;
        //act
        String result = account.getAccountType();
        //assert
        assertEquals(expected,result);
    }


    @Test
    @DisplayName("The cash has the same ID")
    public void hasSameID() throws Exception {

        //arrange
        AccountID cashAccountID = new AccountID(UUID.randomUUID());

        MoneyValue initialAmount = new MoneyValue(new BigDecimal(30));

        AccountDesignation accountDesignation = new AccountDesignation("cash");

        CashAccount cashAccount = new CashAccount(cashAccountID, initialAmount, accountDesignation);
        CashAccount differentCashAccount = new CashAccount(cashAccountID, initialAmount, accountDesignation);
        //act
        boolean result= differentCashAccount.hasSameID(cashAccount.getID());
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("The cash account does not have the same id")
    public void doesNotHaveTheSameID() throws Exception {

        //arrange
        AccountID cashAccountID = new AccountID(UUID.randomUUID());

        MoneyValue initialAmount = new MoneyValue(new BigDecimal(30));

        AccountDesignation accountDesignation = new AccountDesignation("cash");

        CashAccount cashAccount = new CashAccount(cashAccountID, initialAmount, accountDesignation);
        AccountID differentCashAccountID = new AccountID(UUID.randomUUID());
        CashAccount differentCashAccount = new CashAccount(differentCashAccountID, initialAmount, accountDesignation);
        //act
        boolean result = differentCashAccount.hasSameID(cashAccount.getID());
        //assert
        assertFalse(result);
    }


    @Test
    @DisplayName("The cash account does have the same movement type")
    void testWithdraw() throws AccountNotCreatedException, WithdrawNotPossibleException, InvalidMovementTypeException {
        //arrange
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(10));
        MoneyValue withdrawAmount = new MoneyValue(new BigDecimal(5));
        UUID cashAccountID = UUID.randomUUID();

        AccountID accountID = new AccountID(cashAccountID);
        AccountDesignation designation = new AccountDesignation("Food");
        CashAccount cashAccount = new CashAccount(accountID, initialAmount, designation);
        MoneyValue expected = new MoneyValue(BigDecimal.valueOf(5));
        //act

        MovementType result = cashAccount.withdraw(withdrawAmount);
        MovementType movementEx = new MovementType(Constants.DEBIT);
        //assert

        assertEquals(result, movementEx);
    }

    @Test
    @DisplayName("Test deposit generate an credit movement")
    public void testDeposit_ReturnCreditMovement() throws Exception {
        //arrange
        AccountID cashAccountID = new AccountID(UUID.randomUUID());
        MoneyValue initialAmount = new MoneyValue(new BigDecimal(30));
        AccountDesignation accountDesignation = new AccountDesignation("cash");
        CashAccount cashAccount = new CashAccount(cashAccountID, initialAmount, accountDesignation);
        MoneyValue amountToDeposit = new MoneyValue(BigDecimal.valueOf(20));
        String movementType = cashAccount.deposit(amountToDeposit).toString();
        //act
        boolean result = movementType.equals(Constants.CREDIT);
        //assert
        assertTrue(result);
    }
}
