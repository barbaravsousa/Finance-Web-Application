package switchtwentytwenty.project.applicationservice.appservice.iappservice;

import switchtwentytwenty.project.dto.outdto.FamilyRelationOutDTO;
import switchtwentytwenty.project.dto.outdto.PersonOutDTO;
import switchtwentytwenty.project.dto.outdto.ViewRelationOutDTO;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTO;
import switchtwentytwenty.project.dto.toservicedto.PersonDTO;
import switchtwentytwenty.project.dto.outdto.FamilyAndAdminOutDTO;
import switchtwentytwenty.project.exception.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

public interface IFamilyAndMemberService {

    /**
     * Creates a family relation between two persons.
     *
     * @param personEmail      - person id
     * @param kinEmail         - kin id
     * @param familyIdentifier - family id
     * @param relationTypeName - relation that links person to kin
     * @return Data Transfer Object
     */
    Optional<FamilyRelationOutDTO> createFamilyRelation(String personEmail, String kinEmail, String familyIdentifier, String relationTypeName) throws IOException, InvalidRelationTypeException, InvalidEmailException, ElementNotFoundException, InvalidDateException, InvalidVATException, InvalidPersonNameException, ParseException, InvalidMovementTypeException, AccountNotCreatedException, InstantiationException;

    /**
     * Get list of family relations from a person given his/her ID
     *
     * @param personID - person ID
     * @return a list of family relations from a person
     * @throws ElementNotFoundException - person or family not found
     */
    ViewRelationOutDTO getFamilyRelationByPersonID(String personID) throws InvalidEmailException, ElementNotFoundException, InvalidDateException, InvalidVATException, InvalidPersonNameException, IOException, InvalidRelationTypeException, InvalidMovementTypeException, ParseException, AccountNotCreatedException, InstantiationException;

    /**
     * Allows to add a new family member.
     *
     * @param personDTO Receives the personDTO.
     * @return Out person dto.
     * @throws InvalidDateException           Throws invalid date exception.
     * @throws InvalidVATException            Throws invalid vat exception.
     * @throws InvalidEmailException          Throws invalid email exception.
     * @throws PersonAlreadyInSystemException Throws person already in system exception if main email already used in app or vat already used in
     *                                        family.
     */
    PersonOutDTO addFamilyMember(PersonDTO personDTO)
            throws InvalidDateException, InvalidVATException, InvalidEmailException, PersonAlreadyInSystemException, ElementNotFoundException,
            InvalidPersonNameException, BusinessErrorMessage;

    /**
     * Start Family and add its administrator
     *
     * @param dto - family and administrator dto
     * @throws PersonAlreadyInSystemException
     * @throws InvalidEmailException
     * @throws InvalidDateException
     * @throws InvalidVATException
     * @throws InvalidPersonNameException
     */
    FamilyAndAdminOutDTO startFamily(FamilyAndAdminDTO dto)
            throws PersonAlreadyInSystemException, InvalidEmailException, InvalidDateException, InvalidVATException, InvalidPersonNameException, BusinessErrorMessage;
}
