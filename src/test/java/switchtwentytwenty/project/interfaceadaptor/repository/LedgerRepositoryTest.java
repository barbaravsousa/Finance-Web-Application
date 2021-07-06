package switchtwentytwenty.project.interfaceadaptor.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.datamodel.FamilyJPA;
import switchtwentytwenty.project.datamodel.LedgerJPA;
import switchtwentytwenty.project.datamodel.PaymentJPA;
import switchtwentytwenty.project.datamodel.assembler.LedgerDomainDataAssembler;
import switchtwentytwenty.project.datamodel.assembler.PaymentDomainDataAssembler;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata.Payment;
import switchtwentytwenty.project.domain.share.transactiondata.SystemDateEntry;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;
import switchtwentytwenty.project.domain.share.transactiondata.Transfer;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;
import switchtwentytwenty.project.interfaceadaptor.repository.jpa.LedgerJPARepository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LedgerRepositoryTest {

    @InjectMocks
    LedgerRepository ledgerRepository;
    @Mock
    LedgerJPARepository jpaRepository;
    @Mock
    LedgerDomainDataAssembler ledgerDomainDataAssembler;
    @Mock
    PaymentDomainDataAssembler paymentDomainDataAssembler;

    @Test
    void existsByIDTrue() {
        //arrange
        when(jpaRepository.existsById(Mockito.anyString())).thenReturn(true);
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        //act
        boolean result = ledgerRepository.existsByID(ledgerID);
        //assert
        assertTrue(result);
    }

    @Test
    void existsByIDFalse() {
        //arrange
        when(jpaRepository.existsById(Mockito.anyString())).thenReturn(false);
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        //act
        boolean result = ledgerRepository.existsByID(ledgerID);
        //assert
        assertFalse(result);
    }

    @Test
    void findByID(){
        //arrange
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        //act & assert
        assertThrows(ElementNotFoundException.class,()->ledgerRepository.findByID(ledgerID));
    }

    @Test
    @DisplayName("Save ledger with payments and no transfers")
    void testSave_WithPaymentsAndNoTransfers() throws ParseException, InvalidMovementTypeException {
        //arrange
        Ledger ledger = Mockito.mock(Ledger.class);
        LedgerJPA ledgerJPA = Mockito.mock(LedgerJPA.class);
        List<Payment> paymentList = new ArrayList<>();
        List<Transfer> transferList = new ArrayList<>();
        Payment payment = new Payment.PaymentBuilder()
                .withBaseTransaction(new CategoryID(UUID.randomUUID().toString()), new TransactionDescription("Payment"), new TransactionDate("2021-05-07"), new MoneyValue(BigDecimal.valueOf(100)), new SystemDateEntry(new Date()))
                .withDebitMovement(new AccountID(UUID.randomUUID()), new MoneyValue(BigDecimal.valueOf(50)))
                .build();
        PaymentJPA paymentJPA;
        when(ledgerDomainDataAssembler.toData(Mockito.any(Ledger.class))).thenReturn(ledgerJPA);
        when(ledger.getPaymentList()).thenReturn(paymentList);
        when(ledger.getTransferList()).thenReturn(transferList);
        paymentJPA = paymentDomainDataAssembler.toData(payment, ledgerJPA);
        ledgerJPA.addPayment(paymentJPA);
        //act
        ledgerRepository.save(ledger);
        //assert
        verify(jpaRepository).save(Mockito.any(LedgerJPA.class));
    }
}