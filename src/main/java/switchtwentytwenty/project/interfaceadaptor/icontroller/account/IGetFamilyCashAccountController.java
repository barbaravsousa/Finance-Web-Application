package switchtwentytwenty.project.interfaceadaptor.icontroller.account;

import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;

public interface IGetFamilyCashAccountController {

    /**
     * Obtain the list of accounts
     *
     * @param request Http request information
     * @return Http response
     */
    ResponseEntity<Object> getFamilyCashAccount(HttpServletRequest request);

}
