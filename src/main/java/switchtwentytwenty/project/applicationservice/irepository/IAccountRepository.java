package switchtwentytwenty.project.applicationservice.irepository;

import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.exception.AccountNotCreatedException;
import switchtwentytwenty.project.exception.ElementNotFoundException;

public interface IAccountRepository {

    /**
     * Find element in AccountRepository by ID.
     *
     * @param id - Account's identification
     * @return element with same ID
     */
    Account findByID(AccountID id) throws ElementNotFoundException, AccountNotCreatedException;

    /**
     * Adds an Account instance to the repository.
     *
     * @param entity - Entity instance
     */
    void save(Account entity);

    /**
     * Verifies if the id exists in the repository.
     *
     * @param id - entity's id
     * @return false, if id isn't used in the app
     */
    boolean existsByID(AccountID id);
}


