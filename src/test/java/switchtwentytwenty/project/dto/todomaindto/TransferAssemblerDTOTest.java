package switchtwentytwenty.project.dto.todomaindto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.transactiondata.MovementType;
import switchtwentytwenty.project.domain.share.transactiondata.SystemDateEntry;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;
import switchtwentytwenty.project.dto.todomaindto.TransferAssemblerDTO;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferAssemblerDTOTest {

    @Test
    @DisplayName("Test getters")
    void testGetters() throws InvalidMovementTypeException, ParseException {
        //arrange
        AccountID originAccountID = new AccountID(UUID.randomUUID());
        AccountID destinationAccountID = new AccountID(UUID.randomUUID());
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        MovementType debit = new MovementType(Constants.DEBIT);
        MovementType credit = new MovementType(Constants.DEBIT);
        TransactionDate date = new TransactionDate("2020-01-01");
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(100));
        TransactionDescription description = new TransactionDescription("Apple");
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2020-01-02 23:20:59"));

        TransferAssemblerDTO transferAssemblerDTO = new TransferAssemblerDTO.TransferAssemblerDTOBuilder()
                .withOriginAccountID(originAccountID)
                .withDestinationAccountID(destinationAccountID)
                .withCategoryID(categoryID)
                .withDebit(debit)
                .withCredit(credit)
                .withAmount(amount)
                .withDate(date)
                .withDescription(description)
                .withSystemDateEntry(systemDateEntry)
                .build();

        //act
        AccountID originAccountIDResult = transferAssemblerDTO.getOriginAccountID();
        AccountID destinationAccountIDResult = transferAssemblerDTO.getDestinationAccountID();
        CategoryID categoryIDResult = transferAssemblerDTO.getCategoryID();
        MovementType debitResult = transferAssemblerDTO.getDebit();
        MovementType creditResult = transferAssemblerDTO.getCredit();
        TransactionDate dateResult = transferAssemblerDTO.getDate();
        MoneyValue amountResult = transferAssemblerDTO.getAmount();
        TransactionDescription descriptionResult = transferAssemblerDTO.getDescription();
        SystemDateEntry systemDateEntryResult = transferAssemblerDTO.getSystemDateEntry();

        //assert
        assertEquals(originAccountID, originAccountIDResult);
        assertEquals(destinationAccountID, destinationAccountIDResult);
        assertEquals(categoryID, categoryIDResult);
        assertEquals(debit, debitResult);
        assertEquals(credit, creditResult);
        assertEquals(date, dateResult);
        assertEquals(amount, amountResult);
        assertEquals(description, descriptionResult);
        assertEquals(systemDateEntry, systemDateEntryResult);
    }
}