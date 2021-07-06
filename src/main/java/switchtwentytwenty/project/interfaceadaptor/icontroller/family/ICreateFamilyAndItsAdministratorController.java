package switchtwentytwenty.project.interfaceadaptor.icontroller.family;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.FamilyAndAdminInDTO;

public interface ICreateFamilyAndItsAdministratorController {

    /**
     * Start family and create administrator
     *
     * @param info - family and admin information
     * @return family and admin information and links
     */
    ResponseEntity<Object> startFamily(FamilyAndAdminInDTO info);
}
