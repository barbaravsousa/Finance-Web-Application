package switchtwentytwenty.project.domain.aggregate.ledger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.dto.todomaindto.TransferInformationDTO;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.*;
import switchtwentytwenty.project.domain.share.transactiondata.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LedgerIT {

    @Test
    @DisplayName("Zero transactions")
    void getListOfMovementsBetweenDatesZeroTransactions() throws Exception {
        //arrange
        UUID ledgerIDUUID = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(ledgerIDUUID);
        Ledger ledger = new Ledger(ledgerID);

        UUID accountIDUUID = UUID.randomUUID();
        AccountID accountID = new AccountID(accountIDUUID);

        TransactionDate startDate = new TransactionDate("2021-04-01");
        TransactionDate endDate = new TransactionDate("2021-04-05");
        int result;
        int expected = 0;

        //act
        List<Transaction> list = ledger.getListOfMovementsBetweenDates(accountID, startDate, endDate);
        result = list.size();
        //assert
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Get one transaction")
    void getListOfMovementsBetweenDatesGetOneTransactions() throws Exception {
        //arrange
        UUID ledgerIDUUID = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(ledgerIDUUID);
        Ledger ledger = new Ledger(ledgerID);

        UUID accountIDUUID = UUID.randomUUID();
        AccountID accountID = new AccountID(accountIDUUID);

        TransactionDate startDate = new TransactionDate("2021-04-01");
        TransactionDate endDate = new TransactionDate("2021-04-05");
        TransactionDate betweenDate = new TransactionDate("2021-04-02");

        int result;
        int expected = 1;

        MoneyValue moneyValue = new MoneyValue(new BigDecimal("110"));
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());

        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withOriginAccountID(accountID)
                .withDestinationAccountID(new AccountID(UUID.randomUUID()))
                .withCategory(categoryID)
                .withDate(betweenDate)
                .withCredit(new MovementType(Constants.CREDIT))
                .withDebit(new MovementType(Constants.DEBIT))
                .withDescription(new TransactionDescription("Cromos"))
                .withAmount(new MoneyValue(new BigDecimal(50)))
                .build();

        Transfer transferOne = new Transfer(dto, moneyValue);

        //act
        ledger.addTransaction(transferOne);
        List<Transaction> list = ledger.getListOfMovementsBetweenDates(accountID, startDate, endDate);
        result = list.size();
        //assert
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("No transactions between date")
    void getListOfMovementsBetweenDatesNoTransactionsBetweenDates() throws Exception {
        //arrange
        UUID ledgerIDUUID = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(ledgerIDUUID);
        Ledger ledger = new Ledger(ledgerID);

        UUID accountIDUUID = UUID.randomUUID();
        AccountID accountID = new AccountID(accountIDUUID);

        TransactionDate startDate = new TransactionDate("2021-04-01");
        TransactionDate endDate = new TransactionDate("2021-04-05");
        TransactionDate outOfDate = new TransactionDate("2021-04-07");

        int result;
        int expected = 0;

        MoneyValue moneyValue = new MoneyValue(new BigDecimal("110"));
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());

        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withOriginAccountID(accountID)
                .withDestinationAccountID(new AccountID(UUID.randomUUID()))
                .withCategory(categoryID)
                .withDate(outOfDate)
                .withCredit(new MovementType(Constants.CREDIT))
                .withDebit(new MovementType(Constants.DEBIT))
                .withDescription(new TransactionDescription("Cromos"))
                .withAmount(new MoneyValue(new BigDecimal(50)))
                .build();

        Transfer transferOne = new Transfer(dto, moneyValue);

        //act
        ledger.addTransaction(transferOne);
        List<Transaction> list = ledger.getListOfMovementsBetweenDates(accountID, startDate, endDate);
        result = list.size();

        //assert
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Get two transaction")
    void getListOfMovementsBetweenDatesGetTwoTransactions() throws Exception {
        //arrange
        UUID ledgerIDUUID = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(ledgerIDUUID);
        Ledger ledger = new Ledger(ledgerID);

        UUID accountIDUUID = UUID.randomUUID();
        AccountID accountID = new AccountID(accountIDUUID);

        TransactionDate startDate = new TransactionDate("2021-04-01");
        TransactionDate endDate = new TransactionDate("2021-04-05");
        TransactionDate betweenDateOne = new TransactionDate("2021-04-02");
        TransactionDate betweenDateTwo = new TransactionDate("2021-04-01");
        TransactionDate outOfDate = new TransactionDate("2021-04-07");

        int result;
        int expected = 2;

        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());

        MoneyValue moneyValue = new MoneyValue(new BigDecimal("110"));

        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withOriginAccountID(accountID)
                .withDestinationAccountID(new AccountID(UUID.randomUUID()))
                .withCategory(categoryID)
                .withDate(betweenDateOne)
                .withCredit(new MovementType(Constants.CREDIT))
                .withDebit(new MovementType(Constants.DEBIT))
                .withDescription(new TransactionDescription("Cromos"))
                .withAmount(new MoneyValue(new BigDecimal(50)))
                .build();

        Transfer transferOne = new Transfer(dto, moneyValue);

        TransferInformationDTO dto2 = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withOriginAccountID(accountID)
                .withDestinationAccountID(new AccountID(UUID.randomUUID()))
                .withCategory(categoryID)
                .withDate(betweenDateTwo)
                .withCredit(new MovementType(Constants.CREDIT))
                .withDebit(new MovementType(Constants.DEBIT))
                .withDescription(new TransactionDescription("Cromos"))
                .withAmount(new MoneyValue(new BigDecimal(10)))
                .build();

        Transfer transferTwo = new Transfer(dto2, moneyValue);

        TransferInformationDTO dto3 = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withOriginAccountID(accountID)
                .withDestinationAccountID(new AccountID(UUID.randomUUID()))
                .withCategory(categoryID)
                .withDate(outOfDate)
                .withCredit(new MovementType(Constants.CREDIT))
                .withDebit(new MovementType(Constants.DEBIT))
                .withDescription(new TransactionDescription("Cromos"))
                .withAmount(new MoneyValue(new BigDecimal(20)))
                .build();

        Transfer transferThree = new Transfer(dto3, moneyValue);

        //act
        ledger.addTransaction(transferOne);
        ledger.addTransaction(transferTwo);
        ledger.addTransaction(transferThree);

        List<Transaction> list = ledger.getListOfMovementsBetweenDates(accountID, startDate, endDate);
        result = list.size();
        //assert
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Get list of payments")
    void getListOfPayments() throws Exception {
        //arrange
        UUID ledgerIDUUID = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(ledgerIDUUID);
        Ledger ledger = new Ledger(ledgerID);

        UUID accountIDUUID = UUID.randomUUID();
        AccountID accountID = new AccountID(accountIDUUID);

        TransactionDate transactionDate1 = new TransactionDate("2021-04-01");
        TransactionDescription transactionDescription1 = new TransactionDescription("PaymentOne");
        MoneyValue balance1 = new MoneyValue(BigDecimal.valueOf(200));
        MoneyValue moneyValue1 = new MoneyValue(BigDecimal.valueOf(100));
        CategoryID categoryID1 = new CategoryID(UUID.randomUUID().toString());

        int result;
        int expected = 1;

        Transaction payment1 = new Payment.PaymentBuilder()
                .withDebitMovement(accountID, moneyValue1)
                .withBaseTransaction(categoryID1, transactionDescription1, transactionDate1, balance1, null)
                .build();

        //act
        ledger.addTransaction(payment1);
        List<Payment> list = ledger.getPaymentList();
        result = list.size();

        //assert
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("HashCode test")
    void testEqualsHashCode() {
        //arrange
        LedgerID ledgerID1 = new LedgerID(UUID.randomUUID());
        Ledger ledger1 = new Ledger(ledgerID1);

        Ledger ledger2 = new Ledger(ledgerID1);

        LedgerID ledgerID3 = new LedgerID(UUID.randomUUID());
        Ledger ledger3 = new Ledger(ledgerID3);

        //act
        //assert
        assertEquals(ledger1, ledger2);
        assertEquals(ledger1.hashCode(), ledger2.hashCode());
        assertNotEquals(ledger1.hashCode(), ledger3.hashCode());
    }

    @Test
    @DisplayName("Same ledger")
    void sameLedger() {
        //arrange
        LedgerID ledgerID1 = new LedgerID(UUID.randomUUID());
        Ledger ledger1 = new Ledger(ledgerID1);

        Ledger ledger2 = new Ledger(ledgerID1);

        //act
        //assert
        assertEquals(ledger1, ledger2);
    }

    @Test
    @DisplayName("Not same ledger")
    void notSameLedger() {
        //arrange
        LedgerID ledgerID1 = new LedgerID(UUID.randomUUID());
        Ledger ledger1 = new Ledger(ledgerID1);

        LedgerID ledgerID2 = new LedgerID(UUID.randomUUID());
        Ledger ledger2 = new Ledger(ledgerID2);

        //act
        //assert
        assertNotEquals(ledger1, ledger2);
    }

    @Test
    @DisplayName("Not equal objects")
    void ledgerNotEqualToCategory() {
        //arrange
        String id = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(id);
        CategoryDesignation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);

        LedgerID ledgerID1 = new LedgerID(UUID.randomUUID());
        Ledger ledger1 = new Ledger(ledgerID1);
        //act
        boolean result = ledger1.equals(category);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same instance")
    void sameInstance() {
        //arrange
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        Ledger ledger = new Ledger(ledgerID);

        Ledger sameLedger = ledger;

        //assert
        assertEquals(ledger, sameLedger);
    }
}
