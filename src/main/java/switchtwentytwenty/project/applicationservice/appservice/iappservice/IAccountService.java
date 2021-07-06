package switchtwentytwenty.project.applicationservice.appservice.iappservice;

import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.dto.outdto.AccountOutDTO;
import switchtwentytwenty.project.dto.outdto.FamilyCashAccountOutDTO;
import switchtwentytwenty.project.dto.outdto.PersonalCashAccountOutDTO;
import switchtwentytwenty.project.exception.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


public interface IAccountService {

    /**
     * Creates a family relation between two persons.
     *
     * @param personIDString - person
     * @param cashAmount     - initial amount
     * @param designation    - designation of the created account
     */
    PersonalCashAccountOutDTO createPersonalCashAccount(String personIDString, double cashAmount, String designation) throws IOException, InvalidEmailException, ElementNotFoundException,
            AccountNotCreatedException, InvalidDateException, InvalidVATException, InvalidPersonNameException;

    /**
     * Creates a family relation between two persons.
     *
     * @param familyID    - family
     * @param cashAmount  - initial amount
     * @param designation - designation of the created account
     * @return
     */
    FamilyCashAccountOutDTO createFamilyCashAccount(String familyID, String adminID, double cashAmount, String designation) throws ElementNotFoundException, IOException,
            AccountNotCreatedException, InvalidEmailException, InvalidRelationTypeException, InvalidDateException, InvalidVATException, InvalidPersonNameException;


    AccountOutDTO addBankAccount(String designation, String personIDString, String accountType)
            throws ElementNotFoundException, InvalidEmailException, AccountNotCreatedException, InvalidDateException, InvalidVATException, InvalidPersonNameException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException;

    /**
     * Get balance of cahs account
     *
     * @param holderID  - account holder
     * @param accountID - account ID
     * @return balance of the account
     * @throws ElementNotFoundException
     * @throws InvalidEmailException
     * @throws InvalidAccountOwner
     * @throws AccountNotCreatedException
     * @throws IOException
     * @throws InvalidRelationTypeException
     * @throws InvalidDateException
     * @throws InvalidVATException
     * @throws InvalidPersonNameException
     * @throws InstantiationException
     */
    MoneyValue getCashAccountBalance(String holderID, String accountID)
            throws ElementNotFoundException, InvalidEmailException, InvalidAccountOwner, AccountNotCreatedException, IOException, InvalidRelationTypeException, InvalidDateException, InvalidVATException, InvalidPersonNameException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    /**
     * Get balance of child's cash account
     *
     * @param parentID  - parent ID
     * @param childID   - child ID
     * @param accountID - accountID
     * @return balance of account
     * @throws ElementNotFoundException
     * @throws InvalidEmailException
     * @throws AccountNotCreatedException
     * @throws IOException
     * @throws InvalidRelationTypeException
     * @throws InvalidDateException
     * @throws InvalidVATException
     * @throws InvalidPersonNameException
     * @throws InstantiationException
     */
    MoneyValue checkChildCashAccountBalance(String parentID, String childID, String accountID)
            throws ElementNotFoundException, InvalidEmailException, AccountNotCreatedException, IOException, InvalidRelationTypeException, InvalidDateException, InvalidVATException, InvalidPersonNameException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    /**
     * Allows to check an account balance.
     *
     * @param accountID - account id.
     * @param holderID  - holder id.
     * @return The account balance.
     * @throws InvalidEmailException    - if holder id invalid.
     * @throws ElementNotFoundException - if holder or account not found.
     * @throws InvalidAccountOwner      - if account does not belong to this holder.
     */
    MoneyValue checkAccountBalance(String accountID, String holderID)
            throws InvalidEmailException, ElementNotFoundException, InvalidAccountOwner, AccountNotCreatedException, InvalidDateException, InvalidVATException, InvalidPersonNameException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException;


    /**
     * Allows to see all the accounts that the person have
     *
     * @param personID
     * @return
     * @throws InvalidEmailException
     * @throws InvalidDateException
     * @throws ElementNotFoundException
     * @throws InvalidVATException
     * @throws InvalidPersonNameException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws AccountNotCreatedException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     */
    List<PersonalCashAccountOutDTO> getListOfAccounts(String personID) throws InvalidEmailException, InvalidDateException, ElementNotFoundException, InvalidVATException, InvalidPersonNameException, NoSuchMethodException, InstantiationException, AccountNotCreatedException, IOException, IllegalAccessException, InvocationTargetException, ClassNotFoundException;


    /**
     * Return DTO with designation and balance of the family cash account.
     *
     * @param username - payload http request user's name
     * @param role     - user's role in the system
     * @return DTO with account designation and account balance
     * @throws InvalidEmailException        - invalid email
     * @throws UserEmailNotFoundException   - email not found in the system
     * @throws InvalidDateException         - invalid  date
     * @throws ElementNotFoundException     - element not found in the system repositories
     * @throws InvalidVATException          - invalid vat
     * @throws InvalidPersonNameException   - invalid person name
     * @throws IOException                  - IO exception configuration file missing
     * @throws InvalidRelationTypeException - invalid relation type
     * @throws AccountNotCreatedException   - account was not created
     */
    FamilyCashAccountOutDTO getFamilyCashAccount(String username, String role) throws InvalidEmailException, UserEmailNotFoundException, InvalidDateException, ElementNotFoundException, InvalidVATException, InvalidPersonNameException, IOException, InvalidRelationTypeException, AccountNotCreatedException;

}
