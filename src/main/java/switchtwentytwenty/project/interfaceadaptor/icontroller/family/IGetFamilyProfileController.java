package switchtwentytwenty.project.interfaceadaptor.icontroller.family;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

public interface IGetFamilyProfileController {

    /**
     * Get Family Profile information
     *
     * @param familyID - family ID
     * @return family profile: name, registration date, administratorID, administrator name.
     */
    ResponseEntity<Object> getFamilyProfile(HttpServletRequest request, @PathVariable("id") String familyID);
}
