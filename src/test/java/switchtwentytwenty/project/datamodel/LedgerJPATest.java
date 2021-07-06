package switchtwentytwenty.project.datamodel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LedgerJPATest {

    @Test
    @DisplayName("Test getters")
    void testGetter() {
        //arrange
        String id = "001";

        PaymentJPA paymentJPA1 = new PaymentJPA();
        PaymentJPA paymentJPA2 = new PaymentJPA();
        int paymentListSize = 2;

        TransferJPA transferJPA1 = new TransferJPA();
        TransferJPA transferJPA2 = new TransferJPA();
        int transferListSize = 2;

        LedgerJPA ledgerJPA = new LedgerJPA(id);

        ledgerJPA.addPayment(paymentJPA1);
        ledgerJPA.addPayment(paymentJPA2);
        ledgerJPA.addTransfer(transferJPA1);
        ledgerJPA.addTransfer(transferJPA2);

        //act
        String idResult = ledgerJPA.getId();
        int paymentListSizeResult = ledgerJPA.getPaymentJPAList().size();
        int transferListSizeResult = ledgerJPA.getTransferJPAList().size();

        //assert
        assertEquals(id, idResult);
        assertEquals(paymentListSize, paymentListSizeResult);
        assertEquals(transferListSize, transferListSizeResult);
    }

    @Test
    @DisplayName("Test to add payment")
    void testAddPayment() {
        //arrange
        String id = "001";

        PaymentJPA paymentJPA1 = new PaymentJPA();
        PaymentJPA paymentJPA2 = new PaymentJPA();
        int paymentListSize = 2;

        LedgerJPA ledgerJPA = new LedgerJPA(id);

        ledgerJPA.addPayment(paymentJPA1);
        ledgerJPA.addPayment(paymentJPA2);

        //act
        int paymentListSizeResult = ledgerJPA.getPaymentJPAList().size();

        //assert
        assertEquals(paymentListSize, paymentListSizeResult);
    }

    @Test
    @DisplayName("Test to add transfer")
    void testAddTransfer() {
        //arrange
        String id = "001";

        TransferJPA transferJPA1 = new TransferJPA();
        TransferJPA transferJPA2 = new TransferJPA();
        int transferListSize = 2;

        LedgerJPA ledgerJPA = new LedgerJPA(id);

        ledgerJPA.addTransfer(transferJPA1);
        ledgerJPA.addTransfer(transferJPA2);

        //act
        int transferListSizeResult = ledgerJPA.getTransferJPAList().size();

        //assert
        assertEquals(transferListSize, transferListSizeResult);
    }

    @Test
    @DisplayName("Use no argument constructor")
    void noArgumentsConstructor() {
        //arrange
        LedgerJPA ledgerJPA = new LedgerJPA();
        String result;

        //act
        result = ledgerJPA.getId();

        //assert
        assertNull(result);
    }
}
