package switchtwentytwenty.project.interfaceadaptor.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentytwenty.project.datamodel.assembler.LedgerDomainDataAssembler;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.ledger.LedgerFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata.Payment;
import switchtwentytwenty.project.domain.share.transactiondata.SystemDateEntry;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;
import switchtwentytwenty.project.interfaceadaptor.repository.jpa.LedgerJPARepository;
import switchtwentytwenty.project.util.Util;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LedgerRepositoryIT {

    @Autowired
    LedgerRepository repository;
    @Autowired
    LedgerJPARepository jpaRepository;
    @Autowired
    LedgerDomainDataAssembler ledgerAssembler;

    @Test
    void saveListOfPaymentWithDifferentAccounts() throws Exception {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        TransactionDescription description = new TransactionDescription("payment");
        TransactionDate date = new TransactionDate("2020-12-23");
        MoneyValue balance = new MoneyValue(new BigDecimal(100));
        SystemDateEntry systemDateEntry = new SystemDateEntry(Util.stringToDate("2021-03-12", Constants.MOVEMENT_DATE_FORMAT));
        AccountID accountID1 = new AccountID(UUID.randomUUID());
        AccountID accountID2 = new AccountID(UUID.randomUUID());
        AccountID accountID3 = new AccountID(UUID.randomUUID());
        MoneyValue amount = new MoneyValue(new BigDecimal(40));
        Payment payment1 = new Payment.PaymentBuilder()
                .withBaseTransaction(categoryID,description,date,balance,systemDateEntry)
                .withDebitMovement(accountID1,amount)
                .build();

        Payment payment2 = new Payment.PaymentBuilder()
                .withBaseTransaction(categoryID,description,date,balance,systemDateEntry)
                .withDebitMovement(accountID2,amount)
                .build();
        Payment payment3 = new Payment.PaymentBuilder()
                .withBaseTransaction(categoryID,description,date,balance,systemDateEntry)
                .withDebitMovement(accountID3,amount)
                .build();


        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        Ledger ledger = LedgerFactory.create(ledgerID);
        ledger.addTransaction(payment1);
        ledger.addTransaction(payment2);
        ledger.addTransaction(payment3);
        //act
        repository.save(ledger);
        //assert
        Ledger ledgerSaved = repository.findByID(ledgerID);
        assertNotNull(ledgerSaved);
    }
}

