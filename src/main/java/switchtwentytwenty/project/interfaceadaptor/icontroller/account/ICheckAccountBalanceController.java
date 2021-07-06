package switchtwentytwenty.project.interfaceadaptor.icontroller.account;

import org.springframework.http.ResponseEntity;

public interface ICheckAccountBalanceController {

    /**
     * Allows to check account balance.
     * @param holderID - holder's ID.
     * @param accountID - account's ID.
     * @return An response entity with account balance or an error message.
     */
    ResponseEntity<Object> checkAccountBalance(String holderID, String accountID);
}
