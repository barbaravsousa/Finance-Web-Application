package switchtwentytwenty.project.dto.outdto;

import static org.junit.jupiter.api.Assertions.*;
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
import switchtwentytwenty.project.domain.share.transactiondata.Transfer;
import switchtwentytwenty.project.dto.todomaindto.TransferInformationDTO;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class MovementOutDTOMapperTest {

    @Test
    @DisplayName("To data transfer object")
    void toDataTransferObject() throws ParseException, InvalidMovementTypeException {
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
        MovementOutDTO movementDTO = MovementOutDTOMapper.toDTO(transfer, originAccountID);
        String movementTypeResult = movementDTO.getMovementType();
        String accountIDResult = movementDTO.getAccountID();
        double amountResult = movementDTO.getAmount();
        String dateResult = movementDTO.getDate();
        String categoryResult = movementDTO.getCategory();
        double balanceResult = movementDTO.getBalanceToThisDate();
        String descriptionResult = movementDTO.getDescription();

        //assert
        assertEquals(Constants.DEBIT,movementTypeResult);
        assertEquals(accountIDResult, originAccountID.toString());
        assertEquals(amountResult, amount.floatValue());
        assertEquals(dateResult, date.toString());
        assertEquals(categoryResult, categoryID.toString());
        assertEquals(balanceResult, balance.floatValue());
        assertEquals(descriptionResult, description.toString());
    }

}
