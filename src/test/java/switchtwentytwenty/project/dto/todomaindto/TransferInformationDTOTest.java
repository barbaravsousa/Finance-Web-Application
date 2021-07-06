package switchtwentytwenty.project.dto.todomaindto;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.transactiondata.MovementType;
import switchtwentytwenty.project.domain.share.transactiondata.SystemDateEntry;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;
import switchtwentytwenty.project.util.Util;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransferInformationDTOTest {

    @Test
    void createDTOSuccessfully() throws Exception {
        //arrange
        MoneyValue amount = new MoneyValue(new BigDecimal(200));
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        MovementType credit = new MovementType(Constants.CREDIT);
        MovementType debit = new MovementType(Constants.DEBIT);
        TransactionDate date = new TransactionDate("2020-12-28");
        TransactionDescription description = new TransactionDescription("school materials");
        AccountID destinationAccountID = new AccountID(UUID.randomUUID());
        AccountID originAccountID = new AccountID(UUID.randomUUID());
        SystemDateEntry systemDate = new SystemDateEntry(Util.stringToDate("2020-12-23","yyyy-MM-dd"));
        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                                        .withAmount(amount)
                                        .withCategory(categoryID)
                                        .withCredit(credit)
                                        .withDate(date)
                                        .withDebit(debit)
                                        .withDescription(description)
                                        .withDestinationAccountID(destinationAccountID)
                                        .withOriginAccountID(originAccountID)
                                        .withSystemDateEntry(systemDate)
                                        .build();
        //act & assert
        assertNotNull(dto.getAmount());
        assertNotNull(dto.getCategoryID());
        assertNotNull(dto.getCredit());
        assertNotNull(dto.getDate());
        assertNotNull(dto.getDebit());
        assertNotNull(dto.getDescription());
        assertNotNull(dto.getDestinationAccountID());
        assertNotNull(dto.getOriginAccountID());
        assertNotNull(dto.getSystemDateEntry());
    }

}