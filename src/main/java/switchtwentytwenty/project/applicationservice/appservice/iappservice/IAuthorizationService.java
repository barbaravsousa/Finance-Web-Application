package switchtwentytwenty.project.applicationservice.appservice.iappservice;

import switchtwentytwenty.project.autentication.SignupDTO;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.exception.*;


public interface IAuthorizationService {
    /**
     * register user in database
     *
     * @param signUpRequest
     * @throws BusinessErrorMessage
     */
    void registerUser(SignupDTO signUpRequest) throws BusinessErrorMessage;

    /**
     * get user identification (personID) from database
     *
     * @param username - username
     * @return person's ID - email
     * @throws InvalidEmailException
     * @throws UserEmailNotFoundException
     */
    Email getPersonIDOfUser(String username) throws InvalidEmailException, UserEmailNotFoundException;

    /**
     * Authorization method to access an account
     *
     * @param username  - username from token
     * @param accountId - account ID involved in operation
     * @return true, if Authorized
     * @throws InvalidEmailException
     * @throws UserEmailNotFoundException
     * @throws InvalidDateException
     * @throws ElementNotFoundException
     * @throws InvalidVATException
     * @throws InvalidPersonNameException
     */
    boolean accessAccountAuthorization(String username, String accountId) throws InvalidEmailException, UserEmailNotFoundException, InvalidDateException, ElementNotFoundException, InvalidVATException, InvalidPersonNameException;

    /**
     * Defines access to family cash account.
     * Just accessible to the family administrator.
     *
     * @param role - user's role in the system
     * @return true if access is authorized
     */
    boolean accessFamilyCashAccountAuthorization(String role);

    /**
     * get user role from token information
     *
     * @param role - user's role in the system
     * @return user's role
     */
    String getRole(String role);

    /**
     * get family ID from username
     *
     * @param username - username
     * @return family id's user
     * @throws UserEmailNotFoundException
     */
    String getFamilyID(String username) throws UserEmailNotFoundException;
}