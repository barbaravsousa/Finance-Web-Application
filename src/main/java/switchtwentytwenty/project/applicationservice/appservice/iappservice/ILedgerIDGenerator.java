package switchtwentytwenty.project.applicationservice.appservice.iappservice;

import switchtwentytwenty.project.domain.share.id.LedgerID;

public interface ILedgerIDGenerator {

    /**
     * Generate Ledger ID
     *
     * @return ledger ID
     */
    LedgerID generate();
}
