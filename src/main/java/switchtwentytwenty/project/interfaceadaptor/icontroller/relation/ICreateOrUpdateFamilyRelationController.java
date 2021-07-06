package switchtwentytwenty.project.interfaceadaptor.icontroller.relation;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.FamilyRelationInDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidDateException;

public interface ICreateOrUpdateFamilyRelationController {

    /**
     * Creates a family relation between two persons.
     *
     * @param familyIdentifier - family identifier
     * @param info
     * @return
     */
    ResponseEntity<Object> createOrUpdateFamilyRelation(String familyIdentifier, FamilyRelationInDTO info) throws InvalidDateException, ElementNotFoundException;

}
