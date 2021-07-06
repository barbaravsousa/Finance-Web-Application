package switchtwentytwenty.project.interfaceadaptor.icontroller.member;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.DeleteEmailInDto;

public interface IDeleteEmailFromProfileController {

    ResponseEntity<Object> deleteEmailFromProfile(String personID, DeleteEmailInDto emailToDelete);
}
