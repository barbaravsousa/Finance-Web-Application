package switchtwentytwenty.project.domain.share.transactiondata;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.dto.todomaindto.TransferInformationDTO;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class TransferTest {

    @Test
    @DisplayName("Get my movement type - origin")
    void getMyMovementTypeOrigin() throws Exception {
        //arrange
        MovementType result;

        TransactionDescription description = new TransactionDescription("Hello");
        TransactionDate date = new TransactionDate("2020-04-02");
        MoneyValue amount = new MoneyValue(BigDecimal.ONE);
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        AccountID originAccountID = new AccountID(UUID.randomUUID());
        AccountID destinationAccountID = new AccountID(UUID.randomUUID());
        MovementType debit = new MovementType(Constants.DEBIT);
        MovementType credit = new MovementType(Constants.CREDIT);
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-04-03 10:10:10"));
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(200));

        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withDescription(description)
                .withDate(date)
                .withAmount(amount)
                .withCategory(categoryID)
                .withOriginAccountID(originAccountID)
                .withDestinationAccountID(destinationAccountID)
                .withDebit(debit)
                .withCredit(credit)
                .withSystemDateEntry(systemDateEntry)
                .build();

        Transfer transfer = new Transfer(dto, balance);

        //act
        result = transfer.getMyMovementType(originAccountID);

        //assert
        assertEquals(result, debit);
    }

    @Test
    @DisplayName("Get my movement type - destination")
    void getMyMovementTypeDestination() throws  Exception{
        //arrange
        MovementType result;

        TransactionDescription description = new TransactionDescription("Hello");
        TransactionDate date = new TransactionDate("2020-04-02");
        MoneyValue amount = new MoneyValue(BigDecimal.ONE);
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        AccountID originAccountID = new AccountID(UUID.randomUUID());
        AccountID destinationAccountID = new AccountID(UUID.randomUUID());
        MovementType debit = new MovementType(Constants.DEBIT);
        MovementType credit = new MovementType(Constants.CREDIT);
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-04-03 10:10:10"));
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(200));

        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withDescription(description)
                .withDate(date)
                .withAmount(amount)
                .withCategory(categoryID)
                .withOriginAccountID(originAccountID)
                .withDestinationAccountID(destinationAccountID)
                .withDebit(debit)
                .withCredit(credit)
                .withSystemDateEntry(systemDateEntry)
                .build();

        Transfer transfer = new Transfer(dto, balance);

        //act
        result = transfer.getMyMovementType(destinationAccountID);

        //assert
        assertEquals(result, credit);
    }

    @Test
    @DisplayName("Get my movement type - strange account")
    void getMyMovementTypeStrangeAccount() throws Exception  {
        //arrange
        TransactionDescription description = new TransactionDescription("Hello");
        TransactionDate date = new TransactionDate("2020-04-02");
        MoneyValue amount = new MoneyValue(BigDecimal.ONE);
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        AccountID originAccountID = new AccountID(UUID.randomUUID());
        AccountID destinationAccountID = new AccountID(UUID.randomUUID());
        MovementType debit = new MovementType(Constants.DEBIT);
        MovementType credit = new MovementType(Constants.CREDIT);
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-04-03 10:10:10"));
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(200));

        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withDescription(description)
                .withDate(date)
                .withAmount(amount)
                .withCategory(categoryID)
                .withOriginAccountID(originAccountID)
                .withDestinationAccountID(destinationAccountID)
                .withDebit(debit)
                .withCredit(credit)
                .withSystemDateEntry(systemDateEntry)
                .build();

        Transfer transfer = new Transfer(dto, balance);

        AccountID strangeAccount = new AccountID(UUID.randomUUID());

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> transfer.getMyMovementType(strangeAccount));

    }

    @Test
    @DisplayName("Get my amount - strange account")
    void getMyAmountStrangeAccount() throws Exception {
        //arrange
        TransactionDescription description = new TransactionDescription("Hello");
        TransactionDate date = new TransactionDate("2020-04-02");
        MoneyValue amount = new MoneyValue(BigDecimal.ONE);
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        AccountID originAccountID = new AccountID(UUID.randomUUID());
        AccountID destinationAccountID = new AccountID(UUID.randomUUID());
        MovementType debit = new MovementType(Constants.DEBIT);
        MovementType credit = new MovementType(Constants.CREDIT);
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-04-03 10:10:10"));
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(200));

        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withDescription(description)
                .withDate(date)
                .withAmount(amount)
                .withCategory(categoryID)
                .withOriginAccountID(originAccountID)
                .withDestinationAccountID(destinationAccountID)
                .withDebit(debit)
                .withCredit(credit)
                .withSystemDateEntry(systemDateEntry)
                .build();

        Transfer transfer = new Transfer(dto, balance);

        AccountID strangeAccount = new AccountID(UUID.randomUUID());

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> transfer.getMyAmount(strangeAccount));
    }

    @Test
    @DisplayName("Get my amount - destination")
    void getMyAmountDestination() throws Exception{
        //arrange
        MoneyValue result;

        TransactionDescription description = new TransactionDescription("Hello");
        TransactionDate date = new TransactionDate("2020-04-02");
        MoneyValue amount = new MoneyValue(BigDecimal.ONE);
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        AccountID originAccountID = new AccountID(UUID.randomUUID());
        AccountID destinationAccountID = new AccountID(UUID.randomUUID());
        MovementType debit = new MovementType(Constants.DEBIT);
        MovementType credit = new MovementType(Constants.CREDIT);
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-04-03 10:10:10"));
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(200));

        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withDescription(description)
                .withDate(date)
                .withAmount(amount)
                .withCategory(categoryID)
                .withOriginAccountID(originAccountID)
                .withDestinationAccountID(destinationAccountID)
                .withDebit(debit)
                .withCredit(credit)
                .withSystemDateEntry(systemDateEntry)
                .build();

        Transfer transfer = new Transfer(dto, balance);

        //act
        result = transfer.getMyAmount(destinationAccountID);

        //assert
        assertEquals(result, amount);
    }

    @Test
    @DisplayName("Get my amount - origin")
    void getMyAmountOrigin() throws Exception {
        //arrange
        MoneyValue result;

        TransactionDescription description = new TransactionDescription("Hello");
        TransactionDate date = new TransactionDate("2020-04-02");
        MoneyValue amount = new MoneyValue(BigDecimal.ONE);
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        AccountID originAccountID = new AccountID(UUID.randomUUID());
        AccountID destinationAccountID = new AccountID(UUID.randomUUID());
        MovementType debit = new MovementType(Constants.DEBIT);
        MovementType credit = new MovementType(Constants.CREDIT);
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-04-03 10:10:10"));
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(200));

        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withDescription(description)
                .withDate(date)
                .withAmount(amount)
                .withCategory(categoryID)
                .withOriginAccountID(originAccountID)
                .withDestinationAccountID(destinationAccountID)
                .withDebit(debit)
                .withCredit(credit)
                .withSystemDateEntry(systemDateEntry)
                .build();

        Transfer transfer = new Transfer(dto, balance);

        //act
        result = transfer.getMyAmount(originAccountID);

        //assert
        assertEquals(result, amount);
    }

    @Test
    @DisplayName("Get balance")
    void getBalance() throws Exception{
        //arrange
        MoneyValue result;

        TransactionDescription description = new TransactionDescription("Hello");
        TransactionDate date = new TransactionDate("2020-04-02");
        MoneyValue amount = new MoneyValue(BigDecimal.ONE);
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        AccountID originAccountID = new AccountID(UUID.randomUUID());
        AccountID destinationAccountID = new AccountID(UUID.randomUUID());
        MovementType debit = new MovementType(Constants.DEBIT);
        MovementType credit = new MovementType(Constants.CREDIT);
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-04-03 10:10:10"));
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(200));

        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withDescription(description)
                .withDate(date)
                .withAmount(amount)
                .withCategory(categoryID)
                .withOriginAccountID(originAccountID)
                .withDestinationAccountID(destinationAccountID)
                .withDebit(debit)
                .withCredit(credit)
                .withSystemDateEntry(systemDateEntry)
                .build();

        Transfer transfer = new Transfer(dto, balance);

        //act
        result = transfer.getBalance();

        //assert
        assertEquals(balance, result);
    }

    @Test
    @DisplayName("Get accountID - UnsupportedOperationException")
    void getAccountID() throws Exception {
        //arrange
        TransactionDescription description = new TransactionDescription("Hello");
        TransactionDate date = new TransactionDate("2020-04-02");
        MoneyValue amount = new MoneyValue(BigDecimal.ONE);
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        AccountID originAccountID = new AccountID(UUID.randomUUID());
        AccountID destinationAccountID = new AccountID(UUID.randomUUID());
        MovementType debit = new MovementType(Constants.DEBIT);
        MovementType credit = new MovementType(Constants.CREDIT);
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-04-03 10:10:10"));
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(200));

        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withDescription(description)
                .withDate(date)
                .withAmount(amount)
                .withCategory(categoryID)
                .withOriginAccountID(originAccountID)
                .withDestinationAccountID(destinationAccountID)
                .withDebit(debit)
                .withCredit(credit)
                .withSystemDateEntry(systemDateEntry)
                .build();

        Transfer transfer = new Transfer(dto, balance);

        //act and assert
        assertThrows(UnsupportedOperationException.class, () -> transfer.getAccountID());
    }

    @Test
    @DisplayName("Is transfer from origin account")
    void isFromDebitAccount() throws Exception{

        //arrange
        TransactionDescription description = new TransactionDescription("Hello");
        TransactionDate date = new TransactionDate("2020-04-02");
        MoneyValue amount = new MoneyValue(BigDecimal.ONE);
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        AccountID originAccountID = new AccountID(UUID.randomUUID());
        AccountID destinationAccountID = new AccountID(UUID.randomUUID());
        MovementType debit = new MovementType(Constants.DEBIT);
        MovementType credit = new MovementType(Constants.CREDIT);
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-04-03 10:10:10"));
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(200));

        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withDescription(description)
                .withDate(date)
                .withAmount(amount)
                .withCategory(categoryID)
                .withOriginAccountID(originAccountID)
                .withDestinationAccountID(destinationAccountID)
                .withDebit(debit)
                .withCredit(credit)
                .withSystemDateEntry(systemDateEntry)
                .build();

        Transfer transfer = new Transfer(dto, balance);

        //act
        boolean result = transfer.isFromAccount(originAccountID);

        //assert
        assertTrue(result);

    }

    @Test
    @DisplayName("Is transfer from origin account")
    void isFromCreditAccount() throws Exception{

        //arrange
        TransactionDescription description = new TransactionDescription("Hello");
        TransactionDate date = new TransactionDate("2020-04-02");
        MoneyValue amount = new MoneyValue(BigDecimal.ONE);
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        AccountID originAccountID = new AccountID(UUID.randomUUID());
        AccountID destinationAccountID = new AccountID(UUID.randomUUID());
        MovementType debit = new MovementType(Constants.DEBIT);
        MovementType credit = new MovementType(Constants.CREDIT);
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-04-03 10:10:10"));
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(200));

        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withDescription(description)
                .withDate(date)
                .withAmount(amount)
                .withCategory(categoryID)
                .withOriginAccountID(originAccountID)
                .withDestinationAccountID(destinationAccountID)
                .withDebit(debit)
                .withCredit(credit)
                .withSystemDateEntry(systemDateEntry)
                .build();

        Transfer transfer = new Transfer(dto, balance);

        //act
        boolean result = transfer.isFromAccount(destinationAccountID);

        //assert
        assertTrue(result);
    }


    @Test
    @DisplayName("Is transfer from origin account")
    void isFromNoneAccount() throws Exception{

        //arrange
        TransactionDescription description = new TransactionDescription("Hello");
        TransactionDate date = new TransactionDate("2020-04-02");
        MoneyValue amount = new MoneyValue(BigDecimal.ONE);
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        AccountID originAccountID = new AccountID(UUID.randomUUID());
        AccountID destinationAccountID = new AccountID(UUID.randomUUID());
        MovementType debit = new MovementType(Constants.DEBIT);
        MovementType credit = new MovementType(Constants.CREDIT);
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-04-03 10:10:10"));
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(200));

        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withDescription(description)
                .withDate(date)
                .withAmount(amount)
                .withCategory(categoryID)
                .withOriginAccountID(originAccountID)
                .withDestinationAccountID(destinationAccountID)
                .withDebit(debit)
                .withCredit(credit)
                .withSystemDateEntry(systemDateEntry)
                .build();

        Transfer transfer = new Transfer(dto, balance);

        AccountID noneAccount = new AccountID(UUID.randomUUID());
        //act
        boolean result = transfer.isFromAccount(noneAccount);

        //assert
        assertFalse(result);
    }


}
