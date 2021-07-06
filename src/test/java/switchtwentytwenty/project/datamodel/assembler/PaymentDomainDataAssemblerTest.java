package switchtwentytwenty.project.datamodel.assembler;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.datamodel.PaymentJPA;
import switchtwentytwenty.project.domain.share.transactiondata.SystemDateEntry;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PaymentDomainDataAssemblerTest {


    @Test
    void toDomain() throws Exception {
        PaymentDomainDataAssembler paymentDomainDataAssembler = new PaymentDomainDataAssembler();

        PaymentJPA paymentJPA = new PaymentJPA.PaymentJPABuilder()
                .withDescription("Payment")
                .withDate("2021-05-10")
                .withBalance(100)
                .withAmount(60)
                .withCategoryID(UUID.randomUUID().toString())
                .withAccountID(UUID.randomUUID().toString())
                .withSystemEntryDate(new SystemDateEntry(new Date()).toString())
                .build();

        Transaction payment = paymentDomainDataAssembler.toDomain(paymentJPA);

        assertNotNull(payment);
    }
}