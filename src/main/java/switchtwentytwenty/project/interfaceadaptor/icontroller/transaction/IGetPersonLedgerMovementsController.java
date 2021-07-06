package switchtwentytwenty.project.interfaceadaptor.icontroller.transaction;

import org.springframework.http.ResponseEntity;

public interface IGetPersonLedgerMovementsController {

    /**
     * Get list of ledger movements.
     *
     * @return list of ledger movements.
     */
    ResponseEntity<Object> getListOfPersonLedgerMovements(String personID);
}
