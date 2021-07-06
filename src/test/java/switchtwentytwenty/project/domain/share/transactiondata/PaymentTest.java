package switchtwentytwenty.project.domain.share.transactiondata;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PaymentTest {

    @Test
    @DisplayName("Between dates")
    void betweenDates() throws Exception {
        //arrange
        boolean result;

        TransactionDate startDate = new TransactionDate("1999-12-12");
        TransactionDate endDate = new TransactionDate("2021-01-01");

        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDescription transactionDescription = new TransactionDescription("Hello");
        TransactionDate transactionDate = new TransactionDate("2020-06-01");
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(20000));
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-06-02 10:10:11"));
        AccountID accountID = new AccountID(UUID.randomUUID());
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(200));

        Payment payment = new Payment.PaymentBuilder()
                .withBaseTransaction(categoryID, transactionDescription, transactionDate, balance, systemDateEntry)
                .withDebitMovement(accountID, amount)
                .build();

        //act
        result = payment.isBetweenDates(startDate, endDate);

        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Not between dates")
    void notBetweenDates() throws Exception {
        //arrange
        boolean result;

        TransactionDate startDate = new TransactionDate("2021-01-02");
        TransactionDate endDate = new TransactionDate("2021-01-10");

        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDescription transactionDescription = new TransactionDescription("Hello");
        TransactionDate transactionDate = new TransactionDate("2021-01-01");
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(20000));
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-06-02 10:10:10"));
        AccountID accountID = new AccountID(UUID.randomUUID());
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(200));

        Payment payment = new Payment.PaymentBuilder()
                .withBaseTransaction(categoryID, transactionDescription, transactionDate, balance, systemDateEntry)
                .withDebitMovement(accountID, amount)
                .build();

        //act
        result = payment.isBetweenDates(startDate, endDate);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test getters")
    void testGetter() throws Exception {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDescription transactionDescription = new TransactionDescription("Hello");
        TransactionDate transactionDate = new TransactionDate("2021-01-01");
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(20000));
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-06-02 10:10:10"));
        AccountID accountID = new AccountID(UUID.randomUUID());
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(200));
        MovementType movementType = new MovementType(Constants.DEBIT);
        Movement movement = new Movement(accountID, movementType, amount);

        Payment payment = new Payment.PaymentBuilder()
                .withBaseTransaction(categoryID, transactionDescription, transactionDate, balance, systemDateEntry)
                .withDebitMovement(accountID, amount)
                .build();

        //act
        CategoryID categoryIDResult = payment.getCategoryID();
        TransactionDate transactionDateResult = payment.getDate();
        SystemDateEntry systemDateEntryResult = payment.getSystemDateEntry();
        MoneyValue balanceResult = payment.getBalanceToThisDate();
        AccountID accountIDResult = payment.getAccountID();
        TransactionDescription descriptionResult = payment.getDescription();
        MoneyValue amountResult = payment.getMyAmount(accountID);
        MoneyValue amountResult2 = payment.getAmount();
        MovementType movementTypeResult = payment.getMovementType();
        Movement movementResult = payment.getDebitMovement();

        //assert
        assertEquals(categoryIDResult, categoryID);
        assertEquals(transactionDate, transactionDateResult);
        assertEquals(systemDateEntryResult, systemDateEntry);
        assertEquals(balanceResult, balance);
        assertEquals(accountID, accountIDResult);
        assertEquals(descriptionResult, transactionDescription);
        assertEquals(amount, amountResult);
        assertEquals(amount, amountResult2);
        assertEquals(movementType, movementTypeResult);
        assertEquals(movement, movementResult);
    }

    @Test
    @DisplayName("Get my movement type")
    void getMyMovementType() throws Exception {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDescription transactionDescription = new TransactionDescription("Hello");
        TransactionDate transactionDate = new TransactionDate("2021-01-01");
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(20000));
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-06-02 10:10:10"));
        AccountID accountID = new AccountID(UUID.randomUUID());
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(200));

        Payment payment = new Payment.PaymentBuilder()
                .withBaseTransaction(categoryID, transactionDescription, transactionDate, balance, systemDateEntry)
                .withDebitMovement(accountID, amount)
                .build();
        //act
        MovementType movementType = payment.getMyMovementType(accountID);

        //assert
        assertEquals(Constants.DEBIT,movementType.toString());
    }

    @Test
    @DisplayName("Get my amount")
    void getMyAmount() throws Exception{
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDescription transactionDescription = new TransactionDescription("Hello");
        TransactionDate transactionDate = new TransactionDate("2021-01-01");
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(20000));
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-06-02 10:10:10"));
        AccountID accountID = new AccountID(UUID.randomUUID());
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(200));

        Payment payment = new Payment.PaymentBuilder()
                .withBaseTransaction(categoryID, transactionDescription, transactionDate, balance, systemDateEntry)
                .withDebitMovement(accountID, amount)
                .build();

        AccountID otherAccountID = new AccountID(UUID.randomUUID());

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> payment.getMyAmount(otherAccountID));
    }

    @Test
    @DisplayName("Get my movement type - not may payment")
    void getMyMovementTypeNotMyPayment() throws Exception{
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDescription transactionDescription = new TransactionDescription("Hello");
        TransactionDate transactionDate = new TransactionDate("2021-01-01");
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(20000));
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-06-02 10:10:10"));
        AccountID accountID = new AccountID(UUID.randomUUID());
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(200));

        Payment payment = new Payment.PaymentBuilder()
                .withBaseTransaction(categoryID, transactionDescription, transactionDate, balance, systemDateEntry)
                .withDebitMovement(accountID, amount)
                .build();

        AccountID otherAccountID = new AccountID(UUID.randomUUID());
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> payment.getMyMovementType(otherAccountID));
    }

    @Test
    @DisplayName("Is from my account")
    void isFromMyAccount() throws Exception {
        //arrange
        boolean result;

        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDescription transactionDescription = new TransactionDescription("Hello");
        TransactionDate transactionDate = new TransactionDate("2021-01-01");
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(20000));
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-06-02 10:10:10"));
        AccountID accountID = new AccountID(UUID.randomUUID());
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(200));

        Payment payment = new Payment.PaymentBuilder()
                .withBaseTransaction(categoryID, transactionDescription, transactionDate, balance, systemDateEntry)
                .withDebitMovement(accountID, amount)
                .build();
        //act
        result = payment.isFromAccount(accountID);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Is not from my account")
    void isNotFromMyAccount() throws Exception {
        //arrange
        boolean result;

        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDescription transactionDescription = new TransactionDescription("Hello");
        TransactionDate transactionDate = new TransactionDate("2021-01-01");
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(20000));
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-06-02 10:10:10"));
        AccountID accountID = new AccountID(UUID.randomUUID());
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(200));

        Payment payment = new Payment.PaymentBuilder()
                .withBaseTransaction(categoryID, transactionDescription, transactionDate, balance, systemDateEntry)
                .withDebitMovement(accountID, amount)
                .build();

        AccountID otherAccountID = new AccountID(UUID.randomUUID());

        //act
        result = payment.isFromAccount(otherAccountID);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Get originAccountID - UnsupportedOperationException")
    void getOriginAccountID() throws Exception {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDescription transactionDescription = new TransactionDescription("Hello");
        TransactionDate transactionDate = new TransactionDate("2021-01-01");
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(20000));
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-06-02 10:10:10"));
        AccountID accountID = new AccountID(UUID.randomUUID());
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(200));

        Payment payment = new Payment.PaymentBuilder()
                .withBaseTransaction(categoryID, transactionDescription, transactionDate, balance, systemDateEntry)
                .withDebitMovement(accountID, amount)
                .build();

        //act and assert
        assertThrows(UnsupportedOperationException.class, () -> payment.getOriginAccountID());
    }

    @Test
    @DisplayName("Get destinationAccountID - UnsupportedOperationException")
    void getDestinationAccountID() throws Exception {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDescription transactionDescription = new TransactionDescription("Hello");
        TransactionDate transactionDate = new TransactionDate("2021-01-01");
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(20000));
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-06-02 10:10:10"));
        AccountID accountID = new AccountID(UUID.randomUUID());
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(200));

        Payment payment = new Payment.PaymentBuilder()
                .withBaseTransaction(categoryID, transactionDescription, transactionDate, balance, systemDateEntry)
                .withDebitMovement(accountID, amount)
                .build();

        //act and assert
        assertThrows(UnsupportedOperationException.class, () -> payment.getDestinationAccountID());
    }

    @Test
    @DisplayName("Is transfer - false")
    void isTransfer() throws Exception {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDescription transactionDescription = new TransactionDescription("Hello");
        TransactionDate transactionDate = new TransactionDate("2021-01-01");
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(20000));
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-06-02 10:10:10"));
        AccountID accountID = new AccountID(UUID.randomUUID());
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(200));

        Payment payment = new Payment.PaymentBuilder()
                .withBaseTransaction(categoryID, transactionDescription, transactionDate, balance, systemDateEntry)
                .withDebitMovement(accountID, amount)
                .build();

        //act
        boolean result = payment.isTransfer();

        //assert
        assertFalse(result);
    }
}
