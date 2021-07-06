package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountIDGenerator;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.applicationservice.irepository.IAccountRepository;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.account.AccountFactory;
import switchtwentytwenty.project.domain.aggregate.account.CashAccount;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.AccountIDList;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.list.PersonList;
import switchtwentytwenty.project.dto.outdto.AccountOutDTO;
import switchtwentytwenty.project.dto.outdto.FamilyCashAccountOutDTO;
import switchtwentytwenty.project.dto.outdto.PersonalCashAccountOutDTO;
import switchtwentytwenty.project.exception.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    //Attributes

    @Autowired
    private final IAccountRepository accountRepository;
    @Autowired
    private final IPersonRepository personRepository;
    @Autowired
    private final IFamilyRepository familyRepository;
    @Autowired
    private final IAccountIDGenerator accountIDGenerator;
    @Autowired
    private final AuthorizationService authorizationService;


    //Getter and Setters


    //Business Methods

    /**
     * Create a person's cash account
     *
     * @param personIDString - person ID
     * @param cashAmount     - initial amount
     * @param designation    - designation of the created account
     * @return true, if cash account added successfully
     * @throws ElementNotFoundException - element not found in repository
     * @throws InvalidEmailException    - invalid email
     */
    @Transactional(rollbackFor = Exception.class)
    public PersonalCashAccountOutDTO createPersonalCashAccount(String personIDString, double cashAmount, String designation) throws
            ElementNotFoundException, InvalidEmailException, AccountNotCreatedException, InvalidDateException, InvalidVATException, InvalidPersonNameException {

        Email personID = new Email(personIDString);
        Person person = personRepository.findByID(personID);
        CashAccount cashAccount = createCashAccount(cashAmount, designation);
        AccountID accountID = cashAccount.getID();
        person.addAccountID(accountID);
        this.accountRepository.save(cashAccount);
        this.personRepository.save(person);

        return new PersonalCashAccountOutDTO(cashAccount.getID().toString(), cashAccount.getDesignation().toString(), cashAccount.getBalance().toString());
    }

    /**
     * Create a family cash account
     *
     * @param adminID     - family ID
     * @param cashAmount  - initial amount
     * @param designation - designation of the created account
     * @return true, if cash account added successfully
     * @throws ElementNotFoundException - element not found in repository
     */
    @Transactional(rollbackFor = Exception.class)
    public FamilyCashAccountOutDTO createFamilyCashAccount(String familyID, String adminID, double cashAmount, String designation) throws ElementNotFoundException,
            AccountNotCreatedException, InvalidEmailException, IOException, InvalidRelationTypeException {
        Email administratorID = new Email(adminID);
        FamilyID parseFamilyID = new FamilyID(UUID.fromString(familyID));
        Family family = familyRepository.findByID(parseFamilyID);
        if (!(family.isAdministrator(administratorID))) {
            throw new IllegalArgumentException("You don't have admin permission to create a Family Cash Account");
        }
        if (family.hasCashAccount()) {
            throw new IllegalArgumentException("This family already has an account");
        }
        CashAccount cashAccount = createCashAccount(cashAmount, designation);
        AccountID accountID = cashAccount.getID();
        family.addAccountID(accountID);
        this.accountRepository.save(cashAccount);
        this.familyRepository.save(family);

        return new FamilyCashAccountOutDTO(cashAccount.getDesignation().toString(), cashAccount.getBalance().toString());
    }

    /**
     * @param cashAmount  -  initial amount set by the the Person or Family administrator
     * @param designation -  designation set by the the Person or Family administrator
     * @return -  the cash account created
     * @throws AccountNotCreatedException - if account not created.
     */
    private CashAccount createCashAccount(double cashAmount, String designation) throws AccountNotCreatedException {
        MoneyValue amountValue = new MoneyValue(BigDecimal.valueOf(cashAmount));
        AccountDesignation accountCategoryDesignation = new AccountDesignation(designation);
        AccountID accountID = accountIDGenerator.generate();
        return (CashAccount) AccountFactory.createCashAccount(accountID, accountCategoryDesignation, amountValue);
    }

    /**
     * Method to get the cash account balance
     *
     * @param adminId   - administrator identifier
     * @param accountId - account identifier
     * @return optional
     * @throws ElementNotFoundException - Account not found
     * @throws InvalidEmailException    - Invalid email
     */
    public MoneyValue getCashAccountBalance(String adminId, String accountId)
            throws ElementNotFoundException, InvalidEmailException, IOException, InvalidRelationTypeException, InvalidDateException,
            InvalidVATException, InvalidPersonNameException, AccountNotCreatedException {

        Email administratorID = new Email(adminId);
        AccountID cashAccountID = new AccountID(UUID.fromString(accountId));
        Account account = accountRepository.findByID(cashAccountID);

        if (!account.getAccountType().equals(Constants.CASH_ACCOUNT_TYPE)) {
            throw new IllegalArgumentException("Not a cash account");
        }

        if (hasAdminPermissionsOnTheAccount(administratorID, cashAccountID)) {
            return account.getBalance();
        }

        throw new IllegalArgumentException("Balance can not be checked");
    }

    /**
     * Verifies if the person is a family administrator and if the account belongs to any member of his family
     *
     * @param adminID   - Person identifier
     * @param accountID - account identifier
     * @return boolean
     * @throws ElementNotFoundException - person or family not found in system
     */

    private boolean hasAdminPermissionsOnTheAccount(Email adminID, AccountID accountID) throws ElementNotFoundException, InvalidRelationTypeException, IOException, InvalidEmailException, InvalidDateException, InvalidVATException, InvalidPersonNameException {

        Person admin = personRepository.findByID(adminID);
        FamilyID familyID = admin.getFamilyID();
        Family family = familyRepository.findByID(familyID);

        //Chek if I am the administrator
        if (!(family.isAdministrator(adminID))) {
            return false;
        }

        //Check if families cash account
        if (family.ownsCashAccount(accountID)) {
            return true;
        }

        //Check if relatives cash account
        PersonList relatives = personRepository.findByFamilyID(familyID);
        return relatives.ownsAccount(accountID);

    }

    /**
     * Get balance of child's cash account
     *
     * @param parentID  - parent ID
     * @param childID   - child ID
     * @param accountID - accountID
     * @return balance of account
     */
    public MoneyValue checkChildCashAccountBalance(String parentID, String childID, String accountID)
            throws ElementNotFoundException, InvalidEmailException, IOException, InvalidRelationTypeException, InvalidDateException,
            InvalidVATException, InvalidPersonNameException, AccountNotCreatedException {
        UUID id = UUID.fromString(accountID);
        AccountID parseAccountID = new AccountID(id);
        Account account = accountRepository.findByID(parseAccountID);
        Email parentID1 = new Email(parentID);
        Email childID1 = new Email(childID);
        Person parent = personRepository.findByID(parentID1);
        FamilyID familyID = parent.getFamilyID();
        Family family = familyRepository.findByID(familyID);
        if (!family.isMyChild(parentID1, childID1)) {
            throw new IllegalArgumentException("Relation not founded");
        }
        return account.getBalance();
    }

    /**
     * Add an bank account to the list of accounts (savings account, current account or credit card account)
     *
     * @param designation    designation of the account. Given by the user
     * @param personIDString holder's ID
     * @param accountType    type of bank account the user wishes to add (savings account, current account or credit card account)
     * @return The dto with account data.
     * @throws ElementNotFoundException - if holder not found.
     * @throws InvalidEmailException    - if holder's id (email) invalid.
     */
    @Transactional(rollbackFor = Exception.class)
    public AccountOutDTO addBankAccount(String designation, String personIDString, String accountType)
            throws ElementNotFoundException, InvalidEmailException, AccountNotCreatedException, InvalidDateException, InvalidVATException,
            InvalidPersonNameException {
        Email parseHolderID = new Email(personIDString);
        AccountDesignation parseDesignation = new AccountDesignation(designation);
        Person person = this.personRepository.findByID(parseHolderID);
        if (!checkUniqueDesignation(parseDesignation, person)) {
            throw new AccountNotCreatedException("The account designation is already used");
        }
        AccountID bankAccountID = accountIDGenerator.generate();
        Account account = AccountFactory.createBankAccount(bankAccountID, parseDesignation, accountType);
        this.accountRepository.save(account);
        person.addAccountID(bankAccountID);
        this.personRepository.save(person);
        return new AccountOutDTO(bankAccountID.toString(), designation);
    }

    /**
     * Checks if the account designation is not yet associated with another account by the same holder.
     *
     * @param designation - account designation.
     * @param holder      - holder.
     * @return True if the designation has not yet been used in another account by the same holder.
     * @throws ElementNotFoundException - if any account of the holder does not exist.
     */
    private boolean checkUniqueDesignation(Designation designation, Person holder) throws ElementNotFoundException, AccountNotCreatedException {
        AccountIDList accountIDs = holder.getAccountIDList();
        for (int index = 0; index < accountIDs.size(); index++) {
            Account account = accountRepository.findByID(accountIDs.getID(index));
            if (account.isSameDesignation(designation)) {
                return false;
            }
        }
        return true;
    }

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
    public MoneyValue checkAccountBalance(String accountID, String holderID)
            throws InvalidEmailException, ElementNotFoundException, InvalidAccountOwner, InvalidDateException, InvalidVATException, InvalidPersonNameException, AccountNotCreatedException {
        Email parseHolderID = new Email(holderID);
        Person holder = this.personRepository.findByID(parseHolderID);
        AccountID parseAccountID = new AccountID(UUID.fromString(accountID));
        if (!holder.isMyAccount(parseAccountID)) {
            throw new InvalidAccountOwner("Account does not belong to this holder");
        }
        Account account = this.accountRepository.findByID(parseAccountID);
        return account.getBalance();
    }

    /**
     * Method to return all accounts from a person, it returns only the accountID and designation
     *
     * @param personID - personID.
     * @return The all accounts from a person.
     */
    public List<PersonalCashAccountOutDTO> getListOfAccounts(String personID) throws InvalidEmailException, InvalidDateException, ElementNotFoundException, InvalidVATException, InvalidPersonNameException, AccountNotCreatedException {
        Email email = new Email(personID);
        Person person = this.personRepository.findByID(email);
        List<PersonalCashAccountOutDTO> list = new ArrayList<>();
        List<String> accountIDList = person.getAccountIDList().idToString();
        for (String accountId : accountIDList) {
            AccountID accountID = new AccountID(UUID.fromString(accountId));
            Account account = this.accountRepository.findByID(accountID);
            PersonalCashAccountOutDTO dto = new PersonalCashAccountOutDTO(account.getID().toString(), account.getDesignation().toString(), account.getBalance().toString());
            list.add(dto);
        }
        return list;
    }

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
    public FamilyCashAccountOutDTO getFamilyCashAccount(String username, String role) throws InvalidEmailException, UserEmailNotFoundException, InvalidDateException, ElementNotFoundException, InvalidVATException, InvalidPersonNameException, IOException, InvalidRelationTypeException, AccountNotCreatedException {
        boolean hasAccess = this.authorizationService.accessFamilyCashAccountAuthorization(role);
        if (hasAccess) {
            //get family cash account ID trough person's familyID and family's cashAccountID
            Email adminID = this.authorizationService.getPersonIDOfUser(username);
            Person admin = this.personRepository.findByID(adminID);
            FamilyID familyID = admin.getFamilyID();
            Family family = this.familyRepository.findByID(familyID);
            Optional<AccountID> familyCashAccountID = family.getCashAccountID();
            //see if the is already a cash account created
            if (familyCashAccountID.isPresent()) {
                Account account = this.accountRepository.findByID(familyCashAccountID.get());
                String balance = account.getBalance().toString();
                String designation = account.getDesignation().toString();
                return new FamilyCashAccountOutDTO(designation, balance);
            }
            throw new ElementNotFoundException("Cash account wasn't created yet.");
        }
        throw new AuthorizationServiceException("User is not the family administrator.");
    }


}
