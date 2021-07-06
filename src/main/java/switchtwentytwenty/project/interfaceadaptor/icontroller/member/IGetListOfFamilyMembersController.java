package switchtwentytwenty.project.interfaceadaptor.icontroller.member;

import org.springframework.http.ResponseEntity;

public interface IGetListOfFamilyMembersController {

    /**
     * Get list of the family members of a certain family.
     *
     * @param familyID - family ID
     * @return a list with the family members
     */
    ResponseEntity<Object> getListOfFamilyMembers(String familyID);

}
