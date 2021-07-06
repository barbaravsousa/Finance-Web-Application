package switchtwentytwenty.project.interfaceadaptor.icontroller.account;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.exception.InvalidAccountOwner;

public interface IGetCashAccountBalanceController {

    ResponseEntity<Object> getCashAccountBalance(String accountHolderId, String cashAccountId) throws InvalidAccountOwner;
}
