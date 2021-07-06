package switchtwentytwenty.project.applicationservice.appservice.iappservice;

import switchtwentytwenty.project.domain.share.id.AccountID;

public interface IAccountIDGenerator {

    /**
     * Generates an AccountID and returns it
     *
     * @return returns the generated ID
     */
    AccountID generate();
}
