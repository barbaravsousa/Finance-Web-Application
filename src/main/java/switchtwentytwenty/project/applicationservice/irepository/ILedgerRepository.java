package switchtwentytwenty.project.applicationservice.irepository;

import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;

import java.text.ParseException;

public interface ILedgerRepository {

    /**
     * Find element in LedgerRepository by ID.
     *
     * @param id - Account's identification
     * @return element with same ID
     */
    Ledger findByID(LedgerID id) throws InvalidMovementTypeException, ParseException, ElementNotFoundException;

    /**
     * Adds an Ledger instance to the repository.
     *
     * @param entity - Entity instance
     */
    void save(Ledger entity);

    /**
     * Verifies if the id exists in the repository.
     *
     * @param id - entity's id
     * @return false, if id isn't used in the app
     */
    boolean existsByID(LedgerID id);

}
