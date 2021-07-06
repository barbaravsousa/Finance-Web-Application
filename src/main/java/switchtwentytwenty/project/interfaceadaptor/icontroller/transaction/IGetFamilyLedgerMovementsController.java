package switchtwentytwenty.project.interfaceadaptor.icontroller.transaction;

import org.springframework.http.ResponseEntity;

public interface IGetFamilyLedgerMovementsController {

    /**
     * Get list of ledger movements.
     *
     * @return list of ledger movements.
     */
    ResponseEntity<Object> getListOfFamilyLedgerMovements(String personID);
}
