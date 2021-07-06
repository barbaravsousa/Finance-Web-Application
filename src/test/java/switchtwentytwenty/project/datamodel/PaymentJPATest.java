package switchtwentytwenty.project.datamodel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaymentJPATest {

    @Test
    @DisplayName("Test getters")
    void testGetters() {
        //arrange
        String categoryID = "C-001";
        String description = "Fuel";
        String date = "2020-02-20";
        String systemEntryDate = "2020-02-21";
        double balance = 2500;
        String accountID = "A-001";
        double amount = 500;
        LedgerJPA ledgerJPA = new LedgerJPA();

        PaymentJPA paymentJPA = new PaymentJPA.PaymentJPABuilder()
                .withCategoryID(categoryID)
                .withDescription(description)
                .withDate(date)
                .withSystemEntryDate(systemEntryDate)
                .withBalance(balance)
                .withAccountID(accountID)
                .withAmount(amount)
                .withLedgerJPA(ledgerJPA)
                .build();

        //act
        String categoryIDResult = paymentJPA.getId().getCategoryID();
        String descriptionResult = paymentJPA.getId().getDescription();
        String dateResult = paymentJPA.getId().getDate();
        String systemEntryDateResult = paymentJPA.getId().getSystemDateEntry();
        double balanceResult = paymentJPA.getId().getBalance();
        String accountIDResult = paymentJPA.getId().getAccountID();
        double amountResult = paymentJPA.getId().getAmount();
        LedgerJPA ledgerJPAResult = paymentJPA.getLedgerJPA();

        //assert
        assertEquals(categoryID, categoryIDResult);
        assertEquals(description, descriptionResult);
        assertEquals(date, dateResult);
        assertEquals(systemEntryDate, systemEntryDateResult);
        assertEquals(balance, balanceResult);
        assertEquals(accountID, accountIDResult);
        assertEquals(amount, amountResult);
        assertEquals(ledgerJPA, ledgerJPAResult);
    }
}
