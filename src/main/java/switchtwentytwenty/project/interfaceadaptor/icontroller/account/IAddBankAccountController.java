package switchtwentytwenty.project.interfaceadaptor.icontroller.account;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.CreateAccountInDTO;

public interface IAddBankAccountController {

    /**
     * Allows the user to add a bank account.
     * @param info - CreateAccountInDTO with account data.
     * @return An response entity with data of created account or an error message.
     */
    ResponseEntity<Object> addBankAccount(CreateAccountInDTO info);
}
