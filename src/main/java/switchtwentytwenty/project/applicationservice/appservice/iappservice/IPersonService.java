package switchtwentytwenty.project.applicationservice.appservice.iappservice;

import switchtwentytwenty.project.dto.outdto.PersonOutDTO;
import switchtwentytwenty.project.dto.outdto.UserProfileOutDTO;
import switchtwentytwenty.project.exception.*;

import java.util.List;

public interface IPersonService {

    /**
     * Adds an email to my account
     *
     * @param personID
     * @param emailToInput
     * @return
     * @throws ElementNotFoundException - person not found
     * @throws InvalidEmailException
     */
    boolean addEmailToProfile(String personID, String emailToInput) throws InvalidEmailException, ElementNotFoundException, InvalidDateException, InvalidVATException, InvalidPersonNameException;

    /**
     * Get's user's profile information
     *
     * @param - personId
     * @return person's profile information
     * @throws InvalidEmailException
     */
    UserProfileOutDTO getUserProfile(String personId) throws InvalidEmailException, ElementNotFoundException, InvalidDateException, InvalidVATException, InvalidPersonNameException;

    /**
     * Gets list with all the family members of a certain family.
     *
     * @param familyID- family ID
     * @return list with members of a certain family
     * @throws InvalidDateException       in case the Date is invalid
     * @throws InvalidEmailException      in case the email is invalid
     * @throws InvalidVATException        in case the VAT number is invalid
     * @throws InvalidPersonNameException in case the Name is invalid
     */
    List<PersonOutDTO> getListOfFamilyMembers(String familyID) throws InvalidDateException, InvalidEmailException, InvalidVATException, InvalidPersonNameException;


    /**
     * Method to remove an email from a profile
     * @param personId
     * @param otherEmail
     * @return
     * @throws InvalidEmailException
     * @throws InvalidDateException
     * @throws ElementNotFoundException
     * @throws InvalidVATException
     * @throws InvalidPersonNameException
     */
    boolean deleteEmailFromProfile(String personId, String otherEmail) throws InvalidEmailException, InvalidDateException, ElementNotFoundException, InvalidVATException, InvalidPersonNameException;


    }

