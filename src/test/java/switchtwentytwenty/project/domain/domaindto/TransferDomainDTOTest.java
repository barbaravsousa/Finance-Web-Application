package switchtwentytwenty.project.domain.domaindto;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransferDomainDTOTest {

    @Test
    void createTransferDTO_getAmount() throws Exception {
        //arrange
        MoneyValue amount = new MoneyValue(new BigDecimal(200));
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDate date = new TransactionDate("2021-01-30");
        TransactionDescription description = new TransactionDescription("payment");
        AccountID destinationAccount = new AccountID(UUID.randomUUID());
        AccountID originAccount = new AccountID(UUID.randomUUID());
        Email receiverID = new Email("indy@gmail.com");
        String senderid = "0809a3de-b277-11eb-8529-0242ac130003";
        TransferDomainDTO dto = new TransferDomainDTO.TransferBuilder()
                                    .withAmount(amount)
                                    .withCategoryID(categoryID)
                                    .withDate(date)
                                    .withDescription(description)
                                    .withDestinationAccountID(destinationAccount)
                                    .withOriginAccountID(originAccount)
                                    .withReceiverID(receiverID)
                                    .withSenderID(senderid)
                                    .build();
        MoneyValue expected = new MoneyValue(new BigDecimal(200));
        //act
        MoneyValue result = dto.getAmount();
        //assert
        assertEquals(expected,result);
    }

    @Test
    void createTransferDTO_getCategoryID() throws Exception {
        //arrange
        MoneyValue amount = new MoneyValue(new BigDecimal(200));
        CategoryID categoryID = new CategoryID(UUID.fromString("5dd44422-b277-11eb-8529-0242ac130003").toString());
        TransactionDate date = new TransactionDate("2021-01-30");
        TransactionDescription description = new TransactionDescription("payment");
        AccountID destinationAccount = new AccountID(UUID.randomUUID());
        AccountID originAccount = new AccountID(UUID.randomUUID());
        Email receiverID = new Email("indy@gmail.com");
        String senderid = "0809a3de-b277-11eb-8529-0242ac130003";
        TransferDomainDTO dto = new TransferDomainDTO.TransferBuilder()
                .withAmount(amount)
                .withCategoryID(categoryID)
                .withDate(date)
                .withDescription(description)
                .withDestinationAccountID(destinationAccount)
                .withOriginAccountID(originAccount)
                .withReceiverID(receiverID)
                .withSenderID(senderid)
                .build();
        CategoryID expected = new CategoryID(UUID.fromString("5dd44422-b277-11eb-8529-0242ac130003").toString());
        //act
        CategoryID result = dto.getCategoryID();
        //assert
        assertEquals(expected,result);
    }

    @Test
    void createTransferDTO_getDate() throws Exception {
        //arrange
        MoneyValue amount = new MoneyValue(new BigDecimal(200));
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDate date = new TransactionDate("2021-01-30");
        TransactionDescription description = new TransactionDescription("payment");
        AccountID destinationAccount = new AccountID(UUID.randomUUID());
        AccountID originAccount = new AccountID(UUID.randomUUID());
        Email receiverID = new Email("indy@gmail.com");
        String senderid = "0809a3de-b277-11eb-8529-0242ac130003";
        TransferDomainDTO dto = new TransferDomainDTO.TransferBuilder()
                .withAmount(amount)
                .withCategoryID(categoryID)
                .withDate(date)
                .withDescription(description)
                .withDestinationAccountID(destinationAccount)
                .withOriginAccountID(originAccount)
                .withReceiverID(receiverID)
                .withSenderID(senderid)
                .build();
        TransactionDate expected = new TransactionDate("2021-01-30");
        //act
        TransactionDate result = dto.getDate();
        //assert
        assertEquals(expected,result);
    }

    @Test
    void createTransferDTO_getDescription() throws Exception {
        //arrange
        MoneyValue amount = new MoneyValue(new BigDecimal(200));
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDate date = new TransactionDate("2021-01-30");
        TransactionDescription description = new TransactionDescription("payment");
        AccountID destinationAccount = new AccountID(UUID.randomUUID());
        AccountID originAccount = new AccountID(UUID.randomUUID());
        Email receiverID = new Email("indy@gmail.com");
        String senderid = "0809a3de-b277-11eb-8529-0242ac130003";
        TransferDomainDTO dto = new TransferDomainDTO.TransferBuilder()
                .withAmount(amount)
                .withCategoryID(categoryID)
                .withDate(date)
                .withDescription(description)
                .withDestinationAccountID(destinationAccount)
                .withOriginAccountID(originAccount)
                .withReceiverID(receiverID)
                .withSenderID(senderid)
                .build();
        TransactionDescription expected = new TransactionDescription("payment");
        //act
        TransactionDescription result = dto.getDescription();
        //assert
        assertEquals(expected,result);
    }

    @Test
    void createTransferDTO_getDestinationAccountID() throws Exception {
        //arrange
        MoneyValue amount = new MoneyValue(new BigDecimal(200));
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDate date = new TransactionDate("2021-01-30");
        TransactionDescription description = new TransactionDescription("payment");
        AccountID destinationAccount = new AccountID(UUID.fromString("5dd44422-b277-11eb-8529-0242ac130003"));
        AccountID originAccount = new AccountID(UUID.randomUUID());
        Email receiverID = new Email("indy@gmail.com");
        String senderid = "0809a3de-b277-11eb-8529-0242ac130003";
        TransferDomainDTO dto = new TransferDomainDTO.TransferBuilder()
                .withAmount(amount)
                .withCategoryID(categoryID)
                .withDate(date)
                .withDescription(description)
                .withDestinationAccountID(destinationAccount)
                .withOriginAccountID(originAccount)
                .withReceiverID(receiverID)
                .withSenderID(senderid)
                .build();
        AccountID expected = new AccountID(UUID.fromString("5dd44422-b277-11eb-8529-0242ac130003"));
        //act
        AccountID result = dto.getDestinationAccountID();
        //assert
        assertEquals(expected,result);
    }

    @Test
    void createTransferDTO_getOriginAccountID() throws Exception {
        //arrange
        MoneyValue amount = new MoneyValue(new BigDecimal(200));
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDate date = new TransactionDate("2021-01-30");
        TransactionDescription description = new TransactionDescription("payment");
        AccountID originAccount = new AccountID(UUID.fromString("5dd44422-b277-11eb-8529-0242ac130003"));
        AccountID destinationAccount = new AccountID(UUID.randomUUID());
        Email receiverID = new Email("indy@gmail.com");
        String senderid = "0809a3de-b277-11eb-8529-0242ac130003";
        TransferDomainDTO dto = new TransferDomainDTO.TransferBuilder()
                .withAmount(amount)
                .withCategoryID(categoryID)
                .withDate(date)
                .withDescription(description)
                .withDestinationAccountID(destinationAccount)
                .withOriginAccountID(originAccount)
                .withReceiverID(receiverID)
                .withSenderID(senderid)
                .build();
        AccountID expected = new AccountID(UUID.fromString("5dd44422-b277-11eb-8529-0242ac130003"));
        //act
        AccountID result = dto.getOriginAccountID();
        //assert
        assertEquals(expected,result);
    }

    @Test
    void createTransferDTO_getReceiverID() throws Exception {
        //arrange
        MoneyValue amount = new MoneyValue(new BigDecimal(200));
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDate date = new TransactionDate("2021-01-30");
        TransactionDescription description = new TransactionDescription("payment");
        AccountID originAccount = new AccountID(UUID.randomUUID());
        AccountID destinationAccount = new AccountID(UUID.randomUUID());
        Email receiverID = new Email("indy@gmail.com");
        String senderid = "0809a3de-b277-11eb-8529-0242ac130003";
        TransferDomainDTO dto = new TransferDomainDTO.TransferBuilder()
                .withAmount(amount)
                .withCategoryID(categoryID)
                .withDate(date)
                .withDescription(description)
                .withDestinationAccountID(destinationAccount)
                .withOriginAccountID(originAccount)
                .withReceiverID(receiverID)
                .withSenderID(senderid)
                .build();
        Email expected = new Email("indy@gmail.com");
        //act
        Email result = dto.getReceiverID();
        //assert
        assertEquals(expected,result);
    }

    @Test
    void createTransferDTO_getSenderID() throws Exception {
        //arrange
        MoneyValue amount = new MoneyValue(new BigDecimal(200));
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDate date = new TransactionDate("2021-01-30");
        TransactionDescription description = new TransactionDescription("payment");
        AccountID originAccount = new AccountID(UUID.randomUUID());
        AccountID destinationAccount = new AccountID(UUID.randomUUID());
        Email receiverID = new Email("indy@gmail.com");
        String senderid = "0809a3de-b277-11eb-8529-0242ac130003";
        TransferDomainDTO dto = new TransferDomainDTO.TransferBuilder()
                .withAmount(amount)
                .withCategoryID(categoryID)
                .withDate(date)
                .withDescription(description)
                .withDestinationAccountID(destinationAccount)
                .withOriginAccountID(originAccount)
                .withReceiverID(receiverID)
                .withSenderID(senderid)
                .build();
        String expected = "0809a3de-b277-11eb-8529-0242ac130003";
        //act
        String result = dto.getSenderID();
        //assert
        assertEquals(expected,result);
    }
}