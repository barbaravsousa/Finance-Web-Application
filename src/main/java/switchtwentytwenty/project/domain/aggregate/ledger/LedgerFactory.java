package switchtwentytwenty.project.domain.aggregate.ledger;

import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;
import switchtwentytwenty.project.dto.todomaindto.LedgerJpaToDomainDTO;

import java.util.List;

public class LedgerFactory {

    //Constructor methods.

    /**
     * Constructor.
     */
    private LedgerFactory() {}

    /**
     * Returns a Ledger instance.
     *
     * @param ledgerJpaToDomainDTO - Dto with the necessary information to instantiate a Ledger.
     * @return Ledger instance
     */
    public static Ledger create(LedgerJpaToDomainDTO ledgerJpaToDomainDTO) {
        LedgerID ledgerID = ledgerJpaToDomainDTO.getId();
        List<Transaction> transactionList = ledgerJpaToDomainDTO.getTransactionList();

        Ledger ledger = new Ledger(ledgerID);

        ledger.addAllTransaction(transactionList);

        return ledger;
    }

    /**
     * Returns a Ledger instance.
     *
     * @param ledgerID - Ledger identifier
     * @return Ledger instance
     */
    public static Ledger create(LedgerID ledgerID) {
        return new Ledger(ledgerID);
    }
}
