package switchtwentytwenty.project.interfaceadaptor.implcontroller.transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ITransactionService;
import switchtwentytwenty.project.applicationservice.irepository.*;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.account.AccountFactory;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.ledger.LedgerFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.*;
import switchtwentytwenty.project.domain.share.persondata.*;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;
import switchtwentytwenty.project.dto.indto.TransferInDTO;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.transaction.ITransferBetweenMembersCashController;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest()
class TransferBetweenTwoCashAccountsControllerIT {

    @Autowired
    IAccountService accountService;
    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    IAccountRepository accountRepository;
    @Autowired
    IPersonRepository personRepository;
    @Autowired
    ICategoryRepository categoryRepository;
    @Autowired
    ITransactionService transactionService;
    @Autowired
    ILedgerRepository ledgerRepository;
    @Autowired
    IFamilyAndMemberService familyAndMemberService;
    @Autowired
    ITransferBetweenMembersCashController transferBetweenMembersCashController;

    @BeforeEach
    public void before() {
        personRepository.deleteAll();
    }

    @Test
    @DisplayName("Test to transfer a valid amount from member cash account to another member cash account")
    void testToTransferAValidAmountFromAMemberToAnotherMemberCashAccount() throws Exception {
        //arrange
        double initialFamilyCashAmount = 100;
        double initialPersonCashAmount = 50;
        String designation = "Cash";

        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        LedgerID martyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger martyLedger = LedgerFactory.create(martyLedgerID);
        LedgerID georgeLedgerID = new LedgerID(UUID.randomUUID());
        Ledger georgeLedger = LedgerFactory.create(georgeLedgerID);
        ledgerRepository.save(martyLedger);
        ledgerRepository.save(georgeLedger);
        ledgerRepository.save(familyLedger);

        //Create family
        String adminID = "martymcfly@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, new Email(adminID), new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add Marty to the repository
        TelephoneNumberList martyTelephoneNumberList = new TelephoneNumberList();
        martyTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email martyID = new Email(adminID);
        Address address = new Address("Hill Valley", "25", "4125-886", "Porto", "Portugal");
        BirthDate martyBirthDate = new BirthDate("1968-01-22");
        PersonName martyName = new PersonName("Marty McFly");
        VAT martyVat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(martyName, martyBirthDate, martyVat, address, martyTelephoneNumberList, martyID, familyID, martyLedgerID);
        Person marty = PersonFactory.create(personVoDTO);
        personRepository.save(marty);

        //Add George to the repository
        TelephoneNumberList georgeTelephoneNumberList = new TelephoneNumberList();
        georgeTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email georgeID = new Email("george@hotmail.com");
        BirthDate georgeBirthDate = new BirthDate("1968-01-22");
        PersonName georgeName = new PersonName("Marty McFly");
        VAT georgeVat = new VAT("123456789");

        PersonVoDTO georgeDTO = new PersonVoDTO(georgeName, georgeBirthDate, georgeVat, address, georgeTelephoneNumberList, georgeID, familyID, georgeLedgerID);
        Person george = PersonFactory.create(georgeDTO);
        personRepository.save(george);

        //Create Marty's cash account
        MoneyValue martyAmountValue = new MoneyValue(new BigDecimal(initialFamilyCashAmount));
        AccountDesignation martyAccountDesignation = new AccountDesignation(designation);
        AccountID martyAccountID = new AccountID(UUID.randomUUID());
        Account martyCashAccount = AccountFactory.createCashAccount(martyAccountID, martyAccountDesignation, martyAmountValue);
        marty.addAccountID(martyAccountID);
        this.accountRepository.save(martyCashAccount);

        //Create George's cash account
        MoneyValue georgeAmountValue = new MoneyValue(new BigDecimal(initialPersonCashAmount));
        AccountDesignation georgeAccountDesignation = new AccountDesignation(designation);
        AccountID georgeAccountID = new AccountID(UUID.randomUUID());
        Account georgeCashAccount = AccountFactory.createCashAccount(georgeAccountID, georgeAccountDesignation, georgeAmountValue);
        george.addAccountID(georgeAccountID);
        this.accountRepository.save(georgeCashAccount);

        //Create categoryID, description, date and amount to transfer
        String catID = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(catID);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        TransactionDescription transactionDescription = new TransactionDescription("Transaction");
        TransactionDate date = new TransactionDate("2021-03-21");

        TransferInDTO info = new TransferInDTO();
        info.setSenderId("martymcfly@gmail.com");
        info.setReceiverId("george@hotmail.com");
        info.setOriginAccountId(martyAccountID.toString());
        info.setDestinationAccountId(georgeAccountID.toString());
        info.setAmount(70);
        info.setCategoryId(categoryID.toString());
        info.setDescription(transactionDescription.toString());
        info.setDate(date.toString());

        familyRepository.save(family);
        personRepository.save(marty);
        personRepository.save(george);
        this.accountRepository.save(martyCashAccount);
        this.accountRepository.save(georgeCashAccount);

        //act
        ResponseEntity<Object> result = transferBetweenMembersCashController.transferBetweenTwoCashAccounts(info);
        int expected = 201;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test to transfer an invalid amount from member cash account to another member cash account")
    void testToTransferAnInvalidAmountFromAMemberToAnotherMemberCashAccount() throws Exception {
        //arrange
        double initialFamilyCashAmount = 100;
        double initialPersonCashAmount = 50;
        String designation = "Cash";

        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        LedgerID martyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger martyLedger = LedgerFactory.create(martyLedgerID);
        LedgerID georgeLedgerID = new LedgerID(UUID.randomUUID());
        Ledger georgeLedger = LedgerFactory.create(georgeLedgerID);
        ledgerRepository.save(martyLedger);
        ledgerRepository.save(georgeLedger);
        ledgerRepository.save(familyLedger);

        //Create family
        String adminID = "martymcfly@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, new Email(adminID), new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add Marty to the repository
        TelephoneNumberList martyTelephoneNumberList = new TelephoneNumberList();
        martyTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email martyID = new Email(adminID);
        Address address = new Address("Hill Valley", "25", "4125-886", "Porto", "Portugal");
        BirthDate martyBirthDate = new BirthDate("1968-01-22");
        PersonName martyName = new PersonName("Marty McFly");
        VAT martyVat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(martyName, martyBirthDate, martyVat, address, martyTelephoneNumberList, martyID, familyID, martyLedgerID);
        Person marty = PersonFactory.create(personVoDTO);
        personRepository.save(marty);

        //Add George to the repository
        TelephoneNumberList georgeTelephoneNumberList = new TelephoneNumberList();
        georgeTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email georgeID = new Email("george@hotmail.com");
        BirthDate georgeBirthDate = new BirthDate("1968-01-22");
        PersonName georgeName = new PersonName("Marty McFly");
        VAT georgeVat = new VAT("123456789");

        PersonVoDTO georgeDTO = new PersonVoDTO(georgeName, georgeBirthDate, georgeVat, address, georgeTelephoneNumberList, georgeID, familyID, georgeLedgerID);
        Person george = PersonFactory.create(georgeDTO);
        personRepository.save(george);

        //Create Marty's cash account
        MoneyValue martyAmountValue = new MoneyValue(new BigDecimal(initialFamilyCashAmount));
        AccountDesignation martyAccountDesignation = new AccountDesignation(designation);
        AccountID martyAccountID = new AccountID(UUID.randomUUID());
        Account martyCashAccount = AccountFactory.createCashAccount(martyAccountID, martyAccountDesignation, martyAmountValue);
        marty.addAccountID(martyAccountID);
        this.accountRepository.save(martyCashAccount);

        //Create George's cash account
        MoneyValue georgeAmountValue = new MoneyValue(new BigDecimal(initialPersonCashAmount));
        AccountDesignation georgeAccountDesignation = new AccountDesignation(designation);
        AccountID georgeAccountID = new AccountID(UUID.randomUUID());
        Account georgeCashAccount = AccountFactory.createCashAccount(georgeAccountID, georgeAccountDesignation, georgeAmountValue);
        george.addAccountID(georgeAccountID);
        this.accountRepository.save(georgeCashAccount);

        //Create categoryID, description, date and amount to transfer
        String catID = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(catID);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        TransactionDescription transactionDescription = new TransactionDescription("Transaction");
        TransactionDate date = new TransactionDate("2021-03-21");

        TransferInDTO info = new TransferInDTO();
        info.setSenderId("martymcfly@gmail.com");
        info.setReceiverId("george@hotmail.com");
        info.setOriginAccountId(martyAccountID.toString());
        info.setDestinationAccountId(georgeAccountID.toString());
        info.setAmount(-70);
        info.setCategoryId(categoryID.toString());
        info.setDescription(transactionDescription.toString());
        info.setDate(date.toString());

        familyRepository.save(family);
        personRepository.save(marty);
        personRepository.save(george);
        this.accountRepository.save(martyCashAccount);
        this.accountRepository.save(georgeCashAccount);

        //act
        ResponseEntity<Object> result = transferBetweenMembersCashController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }


    @Test
    @DisplayName("Test to transfer a valid amount from member cash account to a person who is not in the family")
    void testToTransferAnValidAmountFromFamilyToPersonCashAccount() throws Exception {
        //arrange
        double initialFamilyCashAmount = 100;
        double initialPersonCashAmount = 50;
        String designation = "Cash";

        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        LedgerID martyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger martyLedger = LedgerFactory.create(martyLedgerID);
        LedgerID georgeLedgerID = new LedgerID(UUID.randomUUID());
        Ledger georgeLedger = LedgerFactory.create(georgeLedgerID);
        ledgerRepository.save(martyLedger);
        ledgerRepository.save(georgeLedger);
        ledgerRepository.save(familyLedger);

        //Create family
        String adminID = "martymcfly@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, new Email(adminID), new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add Marty to the repository
        TelephoneNumberList martyTelephoneNumberList = new TelephoneNumberList();
        martyTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email martyID = new Email(adminID);
        Address address = new Address("Hill Valley", "25", "4125-886", "Porto", "Portugal");
        BirthDate martyBirthDate = new BirthDate("1968-01-22");
        PersonName martyName = new PersonName("Marty McFly");
        VAT martyVat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(martyName, martyBirthDate, martyVat, address, martyTelephoneNumberList, martyID, familyID, martyLedgerID);
        Person marty = PersonFactory.create(personVoDTO);
        personRepository.save(marty);

        //Add George to the repository
        TelephoneNumberList georgeTelephoneNumberList = new TelephoneNumberList();
        georgeTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email georgeID = new Email("george@hotmail.com");
        BirthDate georgeBirthDate = new BirthDate("1968-01-22");
        PersonName georgeName = new PersonName("Marty McFly");
        VAT georgeVat = new VAT("123456789");

        PersonVoDTO georgeDTO = new PersonVoDTO(georgeName, georgeBirthDate, georgeVat, address, georgeTelephoneNumberList, georgeID, familyID, georgeLedgerID);
        Person george = PersonFactory.create(georgeDTO);
        personRepository.save(george);

        //Create Marty's cash account
        MoneyValue martyAmountValue = new MoneyValue(new BigDecimal(initialFamilyCashAmount));
        AccountDesignation martyAccountDesignation = new AccountDesignation(designation);
        AccountID martyAccountID = new AccountID(UUID.randomUUID());
        Account martyCashAccount = AccountFactory.createCashAccount(martyAccountID, martyAccountDesignation, martyAmountValue);
        marty.addAccountID(martyAccountID);
        this.accountRepository.save(martyCashAccount);

        //Create George's cash account
        MoneyValue georgeAmountValue = new MoneyValue(new BigDecimal(initialPersonCashAmount));
        AccountDesignation georgeAccountDesignation = new AccountDesignation(designation);
        AccountID georgeAccountID = new AccountID(UUID.randomUUID());
        Account georgeCashAccount = AccountFactory.createCashAccount(georgeAccountID, georgeAccountDesignation, georgeAmountValue);
        george.addAccountID(georgeAccountID);
        this.accountRepository.save(georgeCashAccount);

        //Create categoryID, description, date and amount to transfer
        String catID = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(catID);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        TransactionDescription transactionDescription = new TransactionDescription("Transaction");
        TransactionDate date = new TransactionDate("2021-03-21");

        TransferInDTO info = new TransferInDTO();
        info.setSenderId("martymcfly@gmail.com");
        info.setReceiverId("george@hotmail.com");
        info.setOriginAccountId(martyAccountID.toString());
        info.setDestinationAccountId(georgeAccountID.toString());
        info.setAmount(-70);
        info.setCategoryId(categoryID.toString());
        info.setDescription(transactionDescription.toString());
        info.setDate(date.toString());

        familyRepository.save(family);
        personRepository.save(marty);
        personRepository.save(george);
        this.accountRepository.save(martyCashAccount);
        this.accountRepository.save(georgeCashAccount);

        //act
        ResponseEntity<Object> result = transferBetweenMembersCashController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test to transfer a valid amount from family cash account to person cash account")
    void testToTransferAValidAmountFromFamilyToPersonCashAccount() throws Exception {
        //arrange
        //Create Ledgers
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        LedgerID personLedgerID = new LedgerID(UUID.randomUUID());
        Ledger personLedger = LedgerFactory.create(personLedgerID);
        ledgerRepository.save(familyLedger);
        ledgerRepository.save(personLedger);

        //Create family
        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        double initialFamilyCashAmount = 100;
        double initialPersonCashAmount = 50;
        String designation = "Cash";
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, new Email(adminID), new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add family member
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658541"));
        Email personID = new Email(adminID);
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-01-22");
        PersonName name = new PersonName("Winston Churchill");
        VAT vat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, personID, familyID, personLedgerID);
        Person person = PersonFactory.create(personVoDTO);

        personRepository.save(person);

        //Create family cash account
        MoneyValue familyAmountValue = new MoneyValue(new BigDecimal(initialFamilyCashAmount));
        AccountDesignation familyAccountDesignation = new AccountDesignation(designation);
        AccountID familyAccountID = new AccountID(UUID.randomUUID());
        Account familyCashAccount = AccountFactory.createCashAccount(familyAccountID, familyAccountDesignation, familyAmountValue);
        family.addAccountID(familyAccountID);
        this.accountRepository.save(familyCashAccount);

        //Create person cash account
        MoneyValue personAmountValue = new MoneyValue(new BigDecimal(initialPersonCashAmount));
        AccountDesignation personAccountDesignation = new AccountDesignation(designation);
        AccountID personAccountID = new AccountID(UUID.randomUUID());
        Account personCashAccount = AccountFactory.createCashAccount(personAccountID, personAccountDesignation, personAmountValue);
        person.addAccountID(personAccountID);
        this.accountRepository.save(personCashAccount);

        //Create categoryID, description, date and amount to transfer
        String catID = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(catID);
        CategoryDesignation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        TransactionDescription transactionDescription = new TransactionDescription("Transaction");
        TransactionDate date = new TransactionDate("2021-03-21");

        TransferInDTO transferInDTO = new TransferInDTO();
        transferInDTO.setSenderId(familyID.toString());
        transferInDTO.setReceiverId(adminID);
        transferInDTO.setOriginAccountId(familyAccountID.toString());
        transferInDTO.setDestinationAccountId(personAccountID.toString());
        transferInDTO.setAmount(70);
        transferInDTO.setDescription(transactionDescription.toString());
        transferInDTO.setCategoryId(categoryID.toString());
        transferInDTO.setDate(date.toString());

        familyRepository.save(family);
        personRepository.save(person);
        this.accountRepository.save(familyCashAccount);
        this.accountRepository.save(personCashAccount);

        //act
        ResponseEntity<Object> result = transferBetweenMembersCashController.transferBetweenTwoCashAccounts(transferInDTO);
        int expected = 201;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test to transfer an invalid amount from family cash account to person cash account")
    void testToTransferAnInValidAmountFromFamilyToPersonCashAccount() throws Exception {
        //arrange
        //Create Ledgers
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        LedgerID personLedgerID = new LedgerID(UUID.randomUUID());
        Ledger personLedger = LedgerFactory.create(personLedgerID);
        ledgerRepository.save(familyLedger);
        ledgerRepository.save(personLedger);

        //Create family
        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        double initialFamilyCashAmount = 100;
        double initialPersonCashAmount = 50;
        String designation = "Cash";
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, new Email(adminID), new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add family member
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658541"));
        Email personID = new Email(adminID);
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-01-22");
        PersonName name = new PersonName("Winston Churchill");
        VAT vat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, personID, familyID, personLedgerID);
        Person person = PersonFactory.create(personVoDTO);

        personRepository.save(person);

        //Create family cash account
        MoneyValue familyAmountValue = new MoneyValue(new BigDecimal(initialFamilyCashAmount));
        AccountDesignation familyAccountDesignation = new AccountDesignation(designation);
        AccountID familyAccountID = new AccountID(UUID.randomUUID());
        Account familyCashAccount = AccountFactory.createCashAccount(familyAccountID, familyAccountDesignation, familyAmountValue);
        family.addAccountID(familyAccountID);
        this.accountRepository.save(familyCashAccount);

        //Create person cash account
        MoneyValue personAmountValue = new MoneyValue(new BigDecimal(initialPersonCashAmount));
        AccountDesignation personAccountDesignation = new AccountDesignation(designation);
        AccountID personAccountID = new AccountID(UUID.randomUUID());
        Account personCashAccount = AccountFactory.createCashAccount(personAccountID, personAccountDesignation, personAmountValue);
        person.addAccountID(personAccountID);
        this.accountRepository.save(personCashAccount);

        //Create categoryID, description, date and amount to transfer
        String catID = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(catID);
        CategoryDesignation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        TransactionDescription transactionDescription = new TransactionDescription("Transaction");
        TransactionDate date = new TransactionDate("2021-03-21");

        TransferInDTO transferInDTO = new TransferInDTO();
        transferInDTO.setSenderId(familyID.toString());
        transferInDTO.setReceiverId(adminID);
        transferInDTO.setOriginAccountId(familyAccountID.toString());
        transferInDTO.setDestinationAccountId(personAccountID.toString());
        transferInDTO.setAmount(-70);
        transferInDTO.setDescription(transactionDescription.toString());
        transferInDTO.setCategoryId(categoryID.toString());
        transferInDTO.setDate(date.toString());

        familyRepository.save(family);
        personRepository.save(person);
        this.accountRepository.save(familyCashAccount);
        this.accountRepository.save(personCashAccount);

        //act
        ResponseEntity<Object> result = transferBetweenMembersCashController.transferBetweenTwoCashAccounts(transferInDTO);
        int expected = 400;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test to transfer a valid amount from family's cash account to a person who does not belong to the family")
    void testToTransferAnInValidAmountFromFamilyToPersonWhoDoesNotBelongToFamily() throws Exception {
        //arrange
        //Create Ledgers
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        LedgerID personLedgerID = new LedgerID(UUID.randomUUID());
        Ledger personLedger = LedgerFactory.create(personLedgerID);
        ledgerRepository.save(familyLedger);
        ledgerRepository.save(personLedger);

        //Create family
        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        double initialFamilyCashAmount = 100;
        double initialPersonCashAmount = 50;
        String designation = "Cash";
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, new Email(adminID), new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add family member
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658541"));
        Email personID = new Email(adminID);
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-01-22");
        PersonName name = new PersonName("Winston Churchill");
        VAT vat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, personID, familyID, personLedgerID);
        Person person = PersonFactory.create(personVoDTO);
        personRepository.save(person);

        //Create family cash account
        MoneyValue familyAmountValue = new MoneyValue(new BigDecimal(initialFamilyCashAmount));
        AccountDesignation familyAccountDesignation = new AccountDesignation(designation);
        AccountID familyAccountID = new AccountID(UUID.randomUUID());
        Account familyCashAccount = AccountFactory.createCashAccount(familyAccountID, familyAccountDesignation, familyAmountValue);
        family.addAccountID(familyAccountID);
        this.accountRepository.save(familyCashAccount);

        //Create person cash account
        MoneyValue personAmountValue = new MoneyValue(new BigDecimal(initialPersonCashAmount));
        AccountDesignation personAccountDesignation = new AccountDesignation(designation);
        AccountID personAccountID = new AccountID(UUID.randomUUID());
        Account personCashAccount = AccountFactory.createCashAccount(personAccountID, personAccountDesignation, personAmountValue);
        person.addAccountID(personAccountID);
        this.accountRepository.save(personCashAccount);

        //Create categoryID, description, date and amount to transfer
        String catID = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(catID);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        TransactionDescription transactionDescription = new TransactionDescription("Transaction");
        TransactionDate date = new TransactionDate("2021-03-21");

        TransferInDTO transferInDTO = new TransferInDTO();
        transferInDTO.setSenderId(familyID.toString());
        transferInDTO.setReceiverId(adminID);
        transferInDTO.setOriginAccountId(familyAccountID.toString());
        transferInDTO.setDestinationAccountId(personAccountID.toString());
        transferInDTO.setAmount(-70);
        transferInDTO.setDescription(transactionDescription.toString());
        transferInDTO.setCategoryId(categoryID.toString());
        transferInDTO.setDate(date.toString());

        familyRepository.save(family);
        personRepository.save(person);
        this.accountRepository.save(familyCashAccount);
        this.accountRepository.save(personCashAccount);

        //act
        ResponseEntity<Object> result = transferBetweenMembersCashController.transferBetweenTwoCashAccounts(transferInDTO);
        int expected = 400;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }
}
