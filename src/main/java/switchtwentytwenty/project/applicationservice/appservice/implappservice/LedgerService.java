package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAuthorizationService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ILedgerService;
import switchtwentytwenty.project.applicationservice.irepository.*;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;
import switchtwentytwenty.project.dto.outdto.LedgerMovementOutDTO;
import switchtwentytwenty.project.dto.outdto.LedgerMovementOutMapper;
import switchtwentytwenty.project.dto.outdto.MovementOutDTO;
import switchtwentytwenty.project.dto.outdto.MovementOutDTOMapper;
import switchtwentytwenty.project.exception.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LedgerService implements ILedgerService {

    //Attributes

    @Autowired
    private final IPersonRepository personRepository;

    @Autowired
    private final ILedgerRepository ledgerRepository;

    @Autowired
    private final IAccountRepository accountRepository;

    @Autowired
    private final ICategoryRepository categoryRepository;

    @Autowired
    private final IFamilyRepository familyRepository;

    @Autowired
    private final IAuthorizationService authorizationService;


    //Constructor Methods

    //Getter and Setters

    //Business Methods

    /**
     * Gets all the movements from a given account and between a certain dates.
     *
     * @param username  - person identifier
     * @param accountIDString - account identifier
     * @param startDateString - initial date
     * @param endDateString   - final date
     * @throws ParseException           - Wrong date format
     * @throws InvalidEmailException    - invalid email
     * @throws ElementNotFoundException - person not found in system
     */
    @Override
    public List<MovementOutDTO> getListOfMovementsBetweenDates(String username, String accountIDString, String startDateString,
            String endDateString) throws ParseException, InvalidEmailException, ElementNotFoundException, InvalidDateException, InvalidVATException,
            InvalidPersonNameException, InvalidMovementTypeException, UserEmailNotFoundException {
        if (authorizationService.accessAccountAuthorization(username, accountIDString)) { // Authorization
            // initialize value objects
            Email personID = authorizationService.getPersonIDOfUser(username);
            Person accountHolder = personRepository.findByID(personID);
            AccountID accountID = new AccountID(UUID.fromString(accountIDString));
            TransactionDate startDate = new TransactionDate(startDateString);
            TransactionDate endDate = new TransactionDate(endDateString);
            LedgerID myLedgerID = accountHolder.getLedgerID();

            Ledger myLedger = ledgerRepository.findByID(myLedgerID);
            List<Transaction> transactionBetweenDates = myLedger.getListOfMovementsBetweenDates(accountID, startDate, endDate);
            return MovementOutDTOMapper.toDTOList(transactionBetweenDates, accountID);
        } else {
            throw new IllegalArgumentException("Invalid account ID.");
        }
    }

    /**
     * Get list of all ledger movements.
     * @param personID - personID.
     * @return The list of movements.
     * @throws InvalidEmailException - if invalid email.
     * @throws InvalidDateException - if invalid date.
     * @throws ElementNotFoundException - if element not found.
     * @throws InvalidVATException - if vat invalid.
     * @throws InvalidPersonNameException - if person name invalid.
     * @throws InvalidMovementTypeException - if movement type invalid.
     * @throws ParseException - if error occurs on parse.
     * @throws AccountNotCreatedException - if account not created.
     */
    public List<LedgerMovementOutDTO> getListOfPersonLedgerMovements(String personID)
            throws InvalidEmailException, InvalidDateException, ElementNotFoundException, InvalidVATException, InvalidPersonNameException,
            InvalidMovementTypeException, ParseException, AccountNotCreatedException {

        Email parsePersonID = new Email(personID);
        Person holder = personRepository.findByID(parsePersonID);
        Ledger ledger = ledgerRepository.findByID(holder.getLedgerID());
        List<AccountID> holderAccounts = holder.getAccountIDList().getList();

        return getListOfAllMovements(ledger, holderAccounts);
    }

    /**
     * Get list of all ledger movements.
     * @param familyID - familyID.
     * @return The list of movements.
     * @throws InvalidEmailException - if invalid email.
     * @throws InvalidDateException - if invalid date.
     * @throws ElementNotFoundException - if element not found.
     * @throws InvalidVATException - if vat invalid.
     * @throws InvalidPersonNameException - if person name invalid.
     * @throws InvalidMovementTypeException - if movement type invalid.
     * @throws ParseException - if error occurs on parse.
     * @throws AccountNotCreatedException - if account not created.
     */
    public List<LedgerMovementOutDTO> getListOfFamilyLedgerMovements(String familyID)
            throws ElementNotFoundException, InvalidEmailException, InvalidRelationTypeException, IOException, InvalidMovementTypeException,
            ParseException, InvalidDateException, AccountNotCreatedException, InvalidVATException, InvalidPersonNameException {

        FamilyID parseFamilyID = new FamilyID(UUID.fromString(familyID));
        Family family = familyRepository.findByID(parseFamilyID);
        Ledger ledger = ledgerRepository.findByID(family.getLedgerID());
        List<AccountID> holderAccounts = new ArrayList<>();
        Optional<AccountID> familyCashAccount = family.getCashAccountID();
        if (!familyCashAccount.isPresent()) {
            throw new InvalidEmailException("Family without cash account");
        }
        holderAccounts.add(familyCashAccount.get());

        return getListOfAllMovements(ledger, holderAccounts);
    }


    /**
     * Get list of all ledger movements.
     * @param ledger - holder ledger.
     * @param holderAccounts - holder accounts.
     * @return The list of movements.
     * @throws InvalidEmailException - if invalid email.
     * @throws InvalidDateException - if invalid date.
     * @throws ElementNotFoundException - if element not found.
     * @throws InvalidVATException - if vat invalid.
     * @throws InvalidPersonNameException - if person name invalid.
     * @throws AccountNotCreatedException - if account not created.
     */
    private List<LedgerMovementOutDTO> getListOfAllMovements(Ledger ledger, List<AccountID> holderAccounts)
            throws InvalidEmailException, InvalidDateException, ElementNotFoundException, InvalidVATException, InvalidPersonNameException,
            AccountNotCreatedException {

        List<Transaction> list = ledger.getTransactionList();

        List<LedgerMovementOutDTO> result = new ArrayList<>();

        for (Transaction transaction : list) {
            String origin = getSenderAccount(transaction);
            String destination = getReceiverAccount(transaction);
            String familyMember = getFamilyMember(holderAccounts, transaction);
            String type = getTransactionType(holderAccounts, transaction);
            String category = getTransactionCategory(transaction);

            LedgerMovementOutDTO dto = LedgerMovementOutMapper.toDTO(transaction, familyMember, origin, destination, type, category);
            result.add(dto);
        }
        return result;
    }

    /**
     * Get transaction category designation.
     * @param transaction - transaction.
     * @return The category designation involved in this transaction.
     * @throws ElementNotFoundException - if category not found.
     */
    private String getTransactionCategory(Transaction transaction) throws ElementNotFoundException {
        Category category = categoryRepository.findByID(transaction.getCategoryID());
        Designation categoryDesignation = category.getDesignation();
        return categoryDesignation.toString();
    }

    /**
     * Get family member that interacts with user in this transaction.
     * @param holderAccounts - holder accounts.
     * @param transaction - transaction.
     * @return The family member involved in this transaction.
     * @throws InvalidEmailException - if invalid email.
     * @throws InvalidDateException - if invalid date.
     * @throws InvalidVATException - if vat invalid.
     * @throws InvalidPersonNameException - if person name invalid.
     */
    private String getFamilyMember(List<AccountID> holderAccounts, Transaction transaction)
            throws InvalidDateException, InvalidEmailException, InvalidVATException,
            InvalidPersonNameException {
        String familyMember = null;
        if (transaction.isTransfer()) {
            AccountID originAccountID = transaction.getOriginAccountID();
            AccountID destinationAccountID = transaction.getDestinationAccountID();
            if (holderAccounts.contains(originAccountID)) {
                try {
                    familyMember = personRepository.findByAccountID(destinationAccountID).getName().toString();
                } catch (ElementNotFoundException exception) {
                    return null;
                }
            } else {
                try {
                    familyMember = personRepository.findByAccountID(originAccountID).getName().toString();
                } catch (ElementNotFoundException exception) {
                    return null;
                }
            }
        }
        return familyMember;
    }


    /**
     * Get sender account.
     * @param transaction - transaction.
     * @return The sender account.
     * @throws ElementNotFoundException - if element not found.
     * @throws AccountNotCreatedException - if account not created.
     */
    private String getSenderAccount(Transaction transaction)
            throws ElementNotFoundException, AccountNotCreatedException {
        String origin;
        if (transaction.isTransfer()) {
            AccountID originAccountID = transaction.getOriginAccountID();
            origin = accountRepository.findByID(originAccountID).getDesignation().toString();
        } else {
            origin = accountRepository.findByID(transaction.getAccountID()).getDesignation().toString();
        }
        return origin;
    }

    /**
     * Get receiver account.
     * @param transaction - transaction.
     * @return The receiver account.
     * @throws ElementNotFoundException - if element not found.
     * @throws AccountNotCreatedException - if account not created.
     */
    private String getReceiverAccount(Transaction transaction)
            throws ElementNotFoundException, AccountNotCreatedException {
        String destination = null;
        if (transaction.isTransfer()) {
            AccountID destinationAccountID = transaction.getDestinationAccountID();
            destination = accountRepository.findByID(destinationAccountID).getDesignation().toString();
        }
        return destination;
    }

    /**
     * Get transaction type (DEBIT_TRANSFER / CREDIT_TRANSFER / PAYMENT)
     * @param holderAccounts - holder accounts.
     * @param transaction - transaction.
     * @return The transaction type.
     */
    private String getTransactionType(List<AccountID> holderAccounts, Transaction transaction) {
        String type;
        if (transaction.isTransfer()) {
            AccountID originAccountID = transaction.getOriginAccountID();
            if (holderAccounts.contains(originAccountID)) {
                type = "DEBIT_TRANSFER";
            } else {
                type = "CREDIT_TRANSFER";
            }
        } else {
            type = "PAYMENT";
        }
        return type;
    }


}
