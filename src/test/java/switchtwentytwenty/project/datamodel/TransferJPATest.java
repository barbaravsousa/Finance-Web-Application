package switchtwentytwenty.project.datamodel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TransferJPATest {

    @Test
    @DisplayName("Test getters")
    void testGetter() {
        //arrange
        String originAccountID = "A-001";
        String destinationAccountID = "A-002";
        String categoryID = "C-001";
        String description = "Sauerkraut";
        String date = "2020-01-01";
        String systemEntryDate = "2020-01-02";
        double balance = 400;
        double amount = 400;
        LedgerJPA ledgerJPA = new LedgerJPA();

        TransferJPA transferJPA = new TransferJPA.TransferJPABuilder()
                .withOriginAccountID(originAccountID)
                .withDestinationAccountID(destinationAccountID)
                .withCategoryID(categoryID)
                .withDescription(description)
                .withDate(date)
                .withSystemDateEntry(systemEntryDate)
                .withBalance(balance)
                .withAmount(amount)
                .withLedgerJPA(ledgerJPA)
                .build();

        //act
        String originAccountIDResult = transferJPA.getId().getOriginAccountID();
        String destinationAccountResult = transferJPA.getId().getDestinationAccountID();
        String categoryIDResult = transferJPA.getId().getCategoryID();
        String dateResult = transferJPA.getId().getDate();
        String systemEntryDateResult = transferJPA.getId().getSystemDateEntry();
        double balanceResult = transferJPA.getId().getBalance();
        double amountResult = transferJPA.getId().getAmount();
        LedgerJPA ledgerJPAResult = transferJPA.getLedgerJPA();

        //assert
        assertEquals(originAccountID, originAccountIDResult);
        assertEquals(destinationAccountID, destinationAccountResult);
        assertEquals(categoryID, categoryIDResult);
        assertEquals(date, dateResult);
        assertEquals(systemEntryDate, systemEntryDateResult);
        assertEquals(balance, balanceResult);
        assertEquals(amount, amountResult);
        assertEquals(ledgerJPA, ledgerJPAResult);
    }
}
