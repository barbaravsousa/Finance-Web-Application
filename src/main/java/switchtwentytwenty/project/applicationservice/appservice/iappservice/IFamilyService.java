package switchtwentytwenty.project.applicationservice.appservice.iappservice;

import switchtwentytwenty.project.dto.outdto.FamilyOutDTO;
import switchtwentytwenty.project.dto.outdto.FamilyProfileOutDTO;
import switchtwentytwenty.project.dto.outdto.SystemRelationsOutDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;

import java.io.IOException;
import java.util.List;

public interface IFamilyService {

    /**
     * Get family profile
     * @param familyID - family ID
     * @return family profile: name, registration date, administratorID, administrator name.
     */
    FamilyProfileOutDTO getFamilyProfile(String familyID) throws ElementNotFoundException, InvalidEmailException, InvalidRelationTypeException, IOException;

    /**
     * Get the list of all possible relations in the system
     * @return an SystemRelationsOutDTO
     */
    SystemRelationsOutDTO getSystemRelations();

    List<FamilyOutDTO> getListOfFamilies() throws InvalidRelationTypeException, IOException, InvalidEmailException;

}
