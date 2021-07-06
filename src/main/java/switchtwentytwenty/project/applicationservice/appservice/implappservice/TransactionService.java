package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAuthorizationService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ITransactionService;
import switchtwentytwenty.project.applicationservice.irepository.*;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.domaindto.TransferDomainDTO;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.*;
import switchtwentytwenty.project.domain.share.transactiondata.*;
import switchtwentytwenty.project.dto.outdto.PaymentOutDTO;
import switchtwentytwenty.project.dto.outdto.TransferOutDTO;
import switchtwentytwenty.project.dto.todomaindto.TransferInformationDTO;
import switchtwentytwenty.project.dto.toservicedto.PaymentDTO;
import switchtwentytwenty.project.dto.toservicedto.TransferDTO;
import switchtwentytwenty.project.dto.toservicedto.TransferDTOMapper;
import switchtwentytwenty.project.exception.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService implements ITransactionService {


    //Attributes
    @Autowired
    private final IPersonRepository personRepository;
    @Autowired
    private final IFamilyRepository familyRepository;
    @Autowired
    private final IAccountRepository accountRepository;
    @Autowired
    private final ILedgerRepository ledgerRepository;
    @Autowired
    private final ICategoryRepository categoryRepository;
    @Autowired
    private final IAuthorizationService authorizationService;

    @Transactional(rollbackFor = Exception.class)
    public TransferOutDTO transferBetweenCashAccounts(TransferDTO transferDTO) throws InvalidEmailException, ParseException, ElementNotFoundException, InvalidRelationTypeException, InstantiationException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InvalidDateException, InvalidVATException, InvocationTargetException, WithdrawNotPossibleException, InvalidPersonNameException, IOException, AccountNotCreatedException, DepositNotPossibleException, InvalidMovementTypeException {

        TransferDomainDTO transferDomainDTO;
        transferDomainDTO = TransferDTOMapper.mapToTransferDomainDTO(transferDTO);

        TransferOutDTO transferOutDTO;

        try {
            //See if the sender id is from a person or a family.
            new Email(transferDomainDTO.getSenderID());
            transferOutDTO = transferBetweenMembers(transferDomainDTO);

        } catch (ElementNotFoundException | InvalidEmailException exception) {
            transferOutDTO = transferBetweenFamilyAndMember(transferDomainDTO);

        }
        return transferOutDTO;
    }

    /**
     * @param transferBetweenFamilyAndMemberDTO - TransferDTO with the information that is necessary to make the transfer between family's cash account and a family member's cash account
     * @return an TransferOutDTO with some information
     * @throws InvalidEmailException        - email's error
     * @throws ParseException               - parse's error
     * @throws ElementNotFoundException     - element not found error
     * @throws InvalidVATException          - vat's error
     * @throws InvalidDateException         - date's error
     * @throws InvalidPersonNameException   - person's name error
     * @throws InstantiationException       - instantiation's error
     * @throws AccountNotCreatedException   - account's error
     * @throws DepositNotPossibleException  - deposit's error
     * @throws IOException                  - io's error
     * @throws WithdrawNotPossibleException - withdraw's error
     * @throws InvalidRelationTypeException - relation's error
     * @throws InvalidMovementTypeException - movement's error
     */
    @NotNull
    private TransferOutDTO transferBetweenFamilyAndMember(TransferDomainDTO transferBetweenFamilyAndMemberDTO) throws InvalidEmailException, ParseException, ElementNotFoundException, InvalidVATException, InvalidDateException, InvalidPersonNameException, InstantiationException, AccountNotCreatedException, DepositNotPossibleException, IOException, WithdrawNotPossibleException, InvalidRelationTypeException, InvalidMovementTypeException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        FamilyID familyID = new FamilyID(UUID.fromString(transferBetweenFamilyAndMemberDTO.getSenderID()));
        Email receiverID = transferBetweenFamilyAndMemberDTO.getReceiverID();

        if (isMemberFromFamily(familyID, receiverID) && areAccountHolders(transferBetweenFamilyAndMemberDTO)) {

            transferBetweenTwoCashAccounts(transferBetweenFamilyAndMemberDTO);
            return new TransferOutDTO(transferBetweenFamilyAndMemberDTO.getOriginAccountID().toString(), transferBetweenFamilyAndMemberDTO.getDestinationAccountID().toString(), transferBetweenFamilyAndMemberDTO.getDate().toString(), transferBetweenFamilyAndMemberDTO.getAmount().toDouble());

        } else {
            throw new IllegalArgumentException("Is not possible to make this transfer");
        }
    }

    /**
     * @param transferBetweenMemberAndMemberDTO - TransferDTO with the information that is necessary to make the transfer between the cash accounts of two family members
     * @return an TransferOutDTO with some information
     * @throws InvalidEmailException        - email's error
     * @throws ParseException               - parse's error
     * @throws AccountNotCreatedException   - account's error
     * @throws ElementNotFoundException     - element not found error
     * @throws InstantiationException       - instantiation's error
     * @throws DepositNotPossibleException  - deposit's error
     * @throws InvalidDateException         - date's error
     * @throws InvalidVATException          - vat's error
     * @throws InvalidPersonNameException   - person's name error
     * @throws InvalidRelationTypeException - relation's error
     * @throws InvalidMovementTypeException - movement's error
     * @throws WithdrawNotPossibleException - withdraw's error
     * @throws IOException                  - io's error
     */
    @NotNull
    private TransferOutDTO transferBetweenMembers(TransferDomainDTO transferBetweenMemberAndMemberDTO) throws ParseException, AccountNotCreatedException, ElementNotFoundException, InstantiationException, DepositNotPossibleException, InvalidDateException, InvalidVATException, InvalidPersonNameException, InvalidRelationTypeException, InvalidMovementTypeException, WithdrawNotPossibleException, IOException, InvalidEmailException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        Email senderID = new Email(transferBetweenMemberAndMemberDTO.getSenderID());
        Email receiverID = transferBetweenMemberAndMemberDTO.getReceiverID();

        if (areFromSameFamily(senderID, receiverID) && areAccountHolders(transferBetweenMemberAndMemberDTO)) {

            transferBetweenTwoCashAccounts(transferBetweenMemberAndMemberDTO);
            return new TransferOutDTO(transferBetweenMemberAndMemberDTO.getOriginAccountID().toString(), transferBetweenMemberAndMemberDTO.getDestinationAccountID().toString(), transferBetweenMemberAndMemberDTO.getDate().toString(), transferBetweenMemberAndMemberDTO.getAmount().toDouble());

        } else {
            throw new IllegalArgumentException("Is not possible to make this transfer");
        }
    }

    /**
     * Transfer money between two cash accounts
     *
     * @param transferDomainDTO - TransferDTO with the information that is necessary to make the transfer between the cash accounts of two family members
     * @throws ElementNotFoundException     - element not found error
     * @throws InvalidMovementTypeException - movement's error
     * @throws DepositNotPossibleException  - deposit's error
     * @throws WithdrawNotPossibleException - withdraw's error
     * @throws InvalidDateException         - date's error
     * @throws InvalidEmailException        - email's error
     * @throws InvalidVATException          - vat's error
     * @throws InvalidPersonNameException   - person's name error
     * @throws IOException                  - io's error
     * @throws InvalidRelationTypeException - relation's error
     * @throws AccountNotCreatedException   - account's error
     * @throws InstantiationException       - instantiation's error
     */
    private void transferBetweenTwoCashAccounts(TransferDomainDTO transferDomainDTO) throws ElementNotFoundException, InvalidMovementTypeException, DepositNotPossibleException, WithdrawNotPossibleException, InvalidDateException, InvalidEmailException, InvalidVATException, InvalidPersonNameException, IOException, InvalidRelationTypeException, AccountNotCreatedException, ParseException {

        Ledger senderLedger = getSenderLedger(transferDomainDTO.getSenderID());

        Person receiver = personRepository.findByID(transferDomainDTO.getReceiverID());
        LedgerID receiverLedgerID = receiver.getLedgerID();
        Ledger receiverLedger = ledgerRepository.findByID(receiverLedgerID);

        Account originAccount = accountRepository.findByID(transferDomainDTO.getOriginAccountID());
        Account destinationAccount = accountRepository.findByID(transferDomainDTO.getDestinationAccountID());

        CategoryID categoryID = transferDomainDTO.getCategoryID();

        if (areCashAccounts(transferDomainDTO.getOriginAccountID(), transferDomainDTO.getDestinationAccountID())) {
            MovementType debit = debitMovement(originAccount, transferDomainDTO.getAmount());
            MovementType credit = creditMovement(destinationAccount, transferDomainDTO.getAmount());

            TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder()
                    .withAmount(transferDomainDTO.getAmount())
                    .withCategory(categoryID)
                    .withCredit(credit)
                    .withDate(transferDomainDTO.getDate())
                    .withDebit(debit)
                    .withDescription(transferDomainDTO.getDescription())
                    .withDestinationAccountID(transferDomainDTO.getDestinationAccountID())
                    .withOriginAccountID(transferDomainDTO.getOriginAccountID())
                    .build();

            Transaction transferSenderAccount = new Transfer(dto, originAccount.getBalance());
            senderLedger.addTransaction(transferSenderAccount);
            ledgerRepository.save(senderLedger);
            accountRepository.save(originAccount);

            Transaction transferReceiverAccount = new Transfer(dto, destinationAccount.getBalance());
            receiverLedger.addTransaction(transferReceiverAccount);
            ledgerRepository.save(receiverLedger);
            accountRepository.save(destinationAccount);

        } else {
            throw new UnsupportedOperationException("Not cash account");
        }
    }


    /**
     * Get sender's ledger
     *
     * @param senderIDString - string with the information about the sender ID
     * @return sender's ledger
     * @throws InvalidEmailException        - email's error
     * @throws InvalidDateException         - date's error
     * @throws InvalidVATException          - vat's error
     * @throws InvalidPersonNameException   - person's name error
     * @throws InvalidMovementTypeException - movement's error
     * @throws ParseException               - parse's error
     * @throws InvalidRelationTypeException - relation's error
     * @throws IOException                  - io's error
     * @throws ElementNotFoundException     - element not found error
     */
    private Ledger getSenderLedger(String senderIDString) throws InvalidEmailException, InvalidDateException, InvalidVATException, InvalidPersonNameException, InvalidMovementTypeException, ParseException, InvalidRelationTypeException, IOException, ElementNotFoundException {
        Ledger senderLedger;

        try {
            Email senderID = new Email(senderIDString);
            Person sender = personRepository.findByID(senderID);
            senderLedger = ledgerRepository.findByID(sender.getLedgerID());
        } catch (InvalidEmailException | ElementNotFoundException exception) {
            FamilyID senderID = new FamilyID(UUID.fromString(senderIDString));
            Family sender = familyRepository.findByID(senderID);
            senderLedger = ledgerRepository.findByID(sender.getLedgerID());
        }

        return senderLedger;
    }

    /**
     * Verifies if the origin account belongs to the sender and if the destination account belongs to the receiver
     *
     * @param transferDomainDTO - dto
     * @return true is the origin account belongs to the sender and if the destination account belongs to the receiver, return false otherwise
     * @throws ElementNotFoundException     - element not found error
     * @throws InvalidEmailException        - email's error
     * @throws InvalidRelationTypeException - relation's error
     * @throws IOException                  - io's error
     * @throws InvalidDateException         - date's error
     * @throws InvalidVATException          - vat's error
     * @throws InvalidPersonNameException   - person's name error
     */
    private boolean areAccountHolders(TransferDomainDTO transferDomainDTO) throws ElementNotFoundException, InvalidEmailException, InvalidRelationTypeException, IOException, InvalidDateException, InvalidVATException, InvalidPersonNameException {
        boolean result;

        try {
            Email senderID = new Email(transferDomainDTO.getSenderID());
            Person sender = personRepository.findByID(senderID);
            Person receiver = personRepository.findByID(transferDomainDTO.getReceiverID());
            AccountID originAccountID = transferDomainDTO.getOriginAccountID();
            AccountID destinationAccountID = transferDomainDTO.getDestinationAccountID();
            result = sender.isMyAccount(originAccountID) && receiver.isMyAccount(destinationAccountID);
        } catch (InvalidEmailException | ElementNotFoundException exception) {
            Family sender = familyRepository.findByID(new FamilyID(UUID.fromString(transferDomainDTO.getSenderID())));
            Person receiver = personRepository.findByID(transferDomainDTO.getReceiverID());
            AccountID originAccountID = transferDomainDTO.getOriginAccountID();
            AccountID destinationAccountID = transferDomainDTO.getDestinationAccountID();
            result = sender.isMyAccount(originAccountID) && receiver.isMyAccount(destinationAccountID);
        }
        return result;
    }

    /**
     * Get debit movement type
     *
     * @param account - account
     * @param money   - amount
     * @return movement type debit
     * @throws InvalidMovementTypeException - movement's error
     * @throws WithdrawNotPossibleException - withdraw's error
     */
    private MovementType debitMovement(Account account, MoneyValue money) throws InvalidMovementTypeException, WithdrawNotPossibleException {
        MovementType debit = new MovementType(Constants.DEBIT);
        if (account.isCashAccount()) {
            debit = account.withdraw(money);

        }
        return debit;
    }

    /**
     * Get credit movement type
     *
     * @param account - account
     * @param money   - amount
     * @return movement type debit
     * @throws InvalidMovementTypeException - movement's error
     * @throws DepositNotPossibleException  - withdraw's error
     */
    private MovementType creditMovement(Account account, MoneyValue money) throws InvalidMovementTypeException, DepositNotPossibleException {
        MovementType credit = new MovementType(Constants.CREDIT);
        if (account.isCashAccount()) {

            credit = account.deposit(money);

        }
        return credit;
    }

    /**
     * Verifies if the person belongs to the family
     *
     * @param familyID - family ID
     * @param memberID - member ID
     * @return true if the person belons to the family, return false otherwise
     * @throws ElementNotFoundException   - element not found error
     * @throws InvalidDateException       - date's error
     * @throws InvalidEmailException      - email's error
     * @throws InvalidVATException        - vat's error
     * @throws InvalidPersonNameException - person's name error
     */
    private boolean isMemberFromFamily(FamilyID familyID, Email memberID) throws ElementNotFoundException, InvalidDateException, InvalidEmailException, InvalidVATException, InvalidPersonNameException {
        return familyID.equals(personRepository.findByID(memberID).getFamilyID());
    }

    /**
     * Verifies if the two persons belong to the same family
     *
     * @param senderID   - sender ID
     * @param receiverID - receiver ID
     * @return true if the two persons belong to the same family, return false otherwise
     * @throws InvalidDateException       - date's error
     * @throws ElementNotFoundException   - element not found error
     * @throws InvalidEmailException      - email's error
     * @throws InvalidVATException        - vat's error
     * @throws InvalidPersonNameException - person's name error
     */
    private boolean areFromSameFamily(Email senderID, Email receiverID) throws InvalidDateException, ElementNotFoundException, InvalidEmailException, InvalidVATException, InvalidPersonNameException {
        Person sender = personRepository.findByID(senderID);
        Person receiver = personRepository.findByID(receiverID);
        FamilyID senderFamilyID = sender.getFamilyID();
        FamilyID receiverFamilyID = receiver.getFamilyID();
        return senderFamilyID.equals(receiverFamilyID);
    }

    /**
     * Verifies if both accounts are cash accounts
     *
     * @param sender   - sender
     * @param receiver - receiver
     * @return true if both accounts are cash accounts, return false otherwise
     * @throws ElementNotFoundException   - element not found error
     * @throws AccountNotCreatedException - account's error
     * @throws InstantiationException     - instantiation's error
     */
    private boolean areCashAccounts(AccountID sender, AccountID receiver) throws ElementNotFoundException, AccountNotCreatedException {
        return accountRepository.findByID(sender).isCashAccount() && accountRepository.findByID(receiver).isCashAccount();
    }

    /**
     * Add payment transaction
     */
    @Transactional(rollbackFor = Exception.class)
    public PaymentOutDTO addPaymentTransaction(PaymentDTO dto, String user) throws InvalidEmailException, InvalidDateException, ElementNotFoundException, InvalidVATException, InvalidPersonNameException, InvalidMovementTypeException, ParseException, WithdrawNotPossibleException, AccountNotCreatedException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, UserEmailNotFoundException {
        if (authorizationService.accessAccountAuthorization(user, dto.getAccountID())) {
            Email personID = authorizationService.getPersonIDOfUser(user);
            AccountID accountID = new AccountID(UUID.fromString(dto.getAccountID()));
            Account account = accountRepository.findByID(accountID);
            CategoryID categoryID = new CategoryID(dto.getCategoryID());
            Person person = personRepository.findByID(personID);
            LedgerID ledgerID = person.getLedgerID();
            Ledger personLedger = ledgerRepository.findByID(ledgerID);
            Category category = categoryRepository.findByID(categoryID);
            if (account.isCashAccount() && category.belongsToFamily(person.getFamilyID())) {
                MoneyValue amount = new MoneyValue(BigDecimal.valueOf(dto.getAmount()));
                TransactionDate date = new TransactionDate(dto.getDate());
                TransactionDescription description = new TransactionDescription(dto.getDesignation());
                MoneyValue balance = balanceAfterTransaction(account, amount);
                Transaction payment = new Payment.PaymentBuilder()
                        .withBaseTransaction(categoryID, description, date, balance, null)
                        .withDebitMovement(accountID, amount)
                        .build();
                account.withdraw(amount);
                personLedger.addTransaction(payment);
                accountRepository.save(account);
                ledgerRepository.save(personLedger);
                return new PaymentOutDTO(description.toString(), accountID.toString(), category.toString(), amount.toDouble(), date.toString());
            }
            throw new UnsupportedOperationException("The account isn't a cash account");
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Simulate balance after transaction
     *
     * @return balance after transaction
     */
    private MoneyValue balanceAfterTransaction(Account account, MoneyValue transactionAmount) {
        MoneyValue currentAccountBalance = account.getBalance();
        MoneyValue balance;
        balance = currentAccountBalance.subtract(transactionAmount);
        return balance;
    }
}
