package switchtwentytwenty.project.interfaceadaptor.icontroller.account;

import org.springframework.http.ResponseEntity;


public interface IGetListOfAccountsController {

    ResponseEntity<Object> getListOfAccountsController(String personId);
}
