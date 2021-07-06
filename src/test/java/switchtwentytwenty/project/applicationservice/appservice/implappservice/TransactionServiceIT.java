
package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ITransactionService;
import switchtwentytwenty.project.applicationservice.irepository.IAccountRepository;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.ILedgerRepository;
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
import switchtwentytwenty.project.domain.constant.Constants;
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
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.dto.toservicedto.TransferDTO;
import switchtwentytwenty.project.exception.WithdrawNotPossibleException;
import switchtwentytwenty.project.interfaceadaptor.repository.CategoryRepository;
import switchtwentytwenty.project.interfaceadaptor.repository.PersonRepository;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TransactionServiceIT {

    @Autowired
    AccountService accountService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    IAccountRepository accountRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ITransactionService transactionService;
    @Autowired
    ILedgerRepository ledgerRepository;
    @Autowired
    IFamilyAndMemberService familyAndMemberService;

    @BeforeEach
    public void before() {
        personRepository.deleteAll();
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
        FamilyName familyName = new FamilyName("Churchill");
        Email personID = new Email(adminID);
        double initialFamilyCashAmount = 100;
        double initialPersonCashAmount = 50;
        String designation = "Cash";

        FamilyVoDTO familyVoDTO = new FamilyVoDTO(familyID, familyLedgerID, personID, familyName);

        Family family = FamilyFactory.create(familyVoDTO);
        familyRepository.save(family);

        //Add family member
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658541"));
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-01-02");
        PersonName name = new PersonName("Winston Churchill");
        VAT vat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, personID, family.getID(),
                personLedgerID);
        Person person = PersonFactory.create(personVoDTO);
        personRepository.save(person);

        //Create family cash account
        MoneyValue familyAmountValue = new MoneyValue(new BigDecimal(initialFamilyCashAmount));
        AccountDesignation familyAccountDesignation = new AccountDesignation(designation);
        AccountID familyAccountID = new AccountID(UUID.randomUUID());
        Account familyCashAccount = AccountFactory.createCashAccount(familyAccountID, familyAccountDesignation, familyAmountValue);
        family.addAccountID(familyAccountID);
        this.accountRepository.save(familyCashAccount);
        familyRepository.save(family);

        //Create person cash account
        MoneyValue personAmountValue = new MoneyValue(new BigDecimal(initialPersonCashAmount));
        AccountDesignation personAccountDesignation = new AccountDesignation(designation);
        AccountID personAccountID = new AccountID(UUID.randomUUID());
        Account personCashAccount = AccountFactory.createCashAccount(personAccountID, personAccountDesignation, personAmountValue);
        person.addAccountID(personAccountID);
        this.accountRepository.save(personCashAccount);
        personRepository.save(person);

        //Create categoryID, description, date and amount to transfer
       String catID = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(catID);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        TransactionDescription transactionDescription = new TransactionDescription("Transaction");
        TransactionDate date = new TransactionDate("2021-03-21");

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(familyID.toString())
                .receiverId(adminID)
                .originAccountId(familyAccountID.toString())
                .destinationAccountId(personAccountID.toString())
                .amount(70)
                .description(transactionDescription.toString())
                .categoryId(categoryID.toString())
                .date(date.toString())
                .build();

        accountRepository.save(familyCashAccount);
        accountRepository.save(personCashAccount);

        //act
        transactionService.transferBetweenCashAccounts(transferDTO);

        MoneyValue finalFamilyAmount = accountRepository.findByID(familyAccountID).getBalance();
        MoneyValue finalPersonAmount = accountRepository.findByID(personAccountID).getBalance();

        MoneyValue expected1 = new MoneyValue(BigDecimal.valueOf(30.0));
        MoneyValue expected2 = new MoneyValue(BigDecimal.valueOf(120.0));

        //assert
        assertEquals(expected1, finalFamilyAmount);
        assertEquals(expected2, finalPersonAmount);
    }

    @Test
    @DisplayName("Test to transfer an invalid amount from family cash account to person cash account")
    void testToTransferAnInvalidAmountFromFamilyToPersonCashAccount() throws Exception {
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
        Email personID = new Email(adminID);
        FamilyName familyName = new FamilyName("Churchill");
        double initialFamilyCashAmount = 100;
        double initialPersonCashAmount = 50;
        String designation = "Cash";

        FamilyVoDTO familyVoDTO = new FamilyVoDTO(familyID, familyLedgerID, personID, familyName);
        Family family = FamilyFactory.create(familyVoDTO);

        familyRepository.save(family);

        //Add family member
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658541"));
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-01-02");
        PersonName name = new PersonName("Winston Churchill");
        VAT vat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, personID, family.getID(),
                personLedgerID);
        Person person = PersonFactory.create(personVoDTO);

        personRepository.save(person);

        //Create family cash account
        MoneyValue familyAmountValue = new MoneyValue(new BigDecimal(initialFamilyCashAmount));
        AccountDesignation familyAccountDesignation = new AccountDesignation(designation);
        AccountID familyAccountID = new AccountID(UUID.randomUUID());
        Account familyCashAccount = AccountFactory.createCashAccount(familyAccountID, familyAccountDesignation, familyAmountValue);
        family.addAccountID(familyAccountID);
        this.accountRepository.save(familyCashAccount);
        familyRepository.save(family);

        //Create person cash account
        MoneyValue personAmountValue = new MoneyValue(new BigDecimal(initialPersonCashAmount));
        AccountDesignation personAccountDesignation = new AccountDesignation(designation);
        AccountID personAccountID = new AccountID(UUID.randomUUID());
        Account personCashAccount = AccountFactory.createCashAccount(personAccountID, personAccountDesignation, personAmountValue);
        person.addAccountID(personAccountID);
        this.accountRepository.save(personCashAccount);
        personRepository.save(person);

        //Create categoryID, description, date and amount to transfer
        String catID = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(catID);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        TransactionDescription transactionDescription = new TransactionDescription("Transaction");
        TransactionDate date = new TransactionDate("2021-03-21");

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(familyID.toString())
                .receiverId(adminID)
                .originAccountId(familyAccountID.toString())
                .destinationAccountId(personAccountID.toString())
                .amount(-50)
                .description(transactionDescription.toString())
                .categoryId(categoryID.toString())
                .date(date.toString())
                .build();

        accountRepository.save(familyCashAccount);
        accountRepository.save(personCashAccount);

        //act
        //assert
        assertThrows(WithdrawNotPossibleException.class, () -> transactionService.transferBetweenCashAccounts(transferDTO));
    }

    @Test
    @DisplayName("Test to transfer an amount from family cash account to person cash account but person does not belong to the family")
    void testToTransferAnAmountFromFamilyToAPersonWhoDoesNotBelongToFamily() throws Exception {
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
        Email personID = new Email(adminID);
        double initialFamilyCashAmount = 100;
        double initialPersonCashAmount = 50;
        String designation = "Cash";
        FamilyName familyName = new FamilyName("Churchill");

        FamilyVoDTO familyVoDTO = new FamilyVoDTO(familyID, familyLedgerID, personID, familyName);
        Family family = FamilyFactory.create(familyVoDTO);
        familyRepository.save(family);

        //Add a person to the system but not to the family
        UUID familyUUID1 = UUID.randomUUID();
        FamilyID familyID1 = new FamilyID(familyUUID1);
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658541"));
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-01-02");
        PersonName name = new PersonName("Winston Churchill");
        VAT vat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, personID, familyID1,
                personLedgerID);
        Person person = PersonFactory.create(personVoDTO);
        personRepository.save(person);

        //Create family cash account
        MoneyValue familyAmountValue = new MoneyValue(new BigDecimal(initialFamilyCashAmount));
        AccountDesignation familyAccountDesignation = new AccountDesignation(designation);
        AccountID familyAccountID = new AccountID(UUID.randomUUID());
        Account familyCashAccount = AccountFactory.createCashAccount(familyAccountID, familyAccountDesignation, familyAmountValue);
        family.addAccountID(familyAccountID);
        this.accountRepository.save(familyCashAccount);
        familyRepository.save(family);

        //Create person cash account
        MoneyValue personAmountValue = new MoneyValue(new BigDecimal(initialPersonCashAmount));
        AccountDesignation personAccountDesignation = new AccountDesignation(designation);
        AccountID personAccountID = new AccountID(UUID.randomUUID());
        Account personCashAccount = AccountFactory.createCashAccount(personAccountID, personAccountDesignation, personAmountValue);
        person.addAccountID(personAccountID);
        this.accountRepository.save(personCashAccount);
        personRepository.save(person);

        //Create categoryID, description, date and amount to transfer
        String catID = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(catID);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        TransactionDescription transactionDescription = new TransactionDescription("Transaction");
        TransactionDate date = new TransactionDate("2021-03-21");

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(familyID.toString())
                .receiverId(adminID)
                .originAccountId(familyAccountID.toString())
                .destinationAccountId(personAccountID.toString())
                .amount(50)
                .description(transactionDescription.toString())
                .categoryId(categoryID.toString())
                .date(date.toString())
                .build();

        accountRepository.save(familyCashAccount);
        accountRepository.save(personCashAccount);

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> transactionService.transferBetweenCashAccounts(transferDTO));
    }

    @Test
    @DisplayName("Test to transfer an amount from family cash account to person cash account but they are not the accounts holders")
    void testToTransferAnAmountFromFamilyToAPersonWhoIsNotTheAccountHolder() throws Exception {
        //arrange
        //Create Ledgers
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        LedgerID personLedgerID = new LedgerID(UUID.randomUUID());
        Ledger personLedger = LedgerFactory.create(personLedgerID);
        LedgerID personLedgerID2 = new LedgerID(UUID.randomUUID());
        Ledger personLedger2 = LedgerFactory.create(personLedgerID2);
        ledgerRepository.save(familyLedger);
        ledgerRepository.save(personLedger);
        ledgerRepository.save(personLedger2);

        //Create family
        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        Email personID = new Email(adminID);
        FamilyName familyName = new FamilyName("Churchill");
        double initialFamilyCashAmount = 100;
        double initialPersonCashAmount = 50;
        String designation = "Cash";

        FamilyVoDTO familyVoDTO = new FamilyVoDTO(familyID, familyLedgerID, personID, familyName);
        Family family = FamilyFactory.create(familyVoDTO);
        familyRepository.save(family);

        //Add a family member
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658541"));
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-01-02");
        PersonName name = new PersonName("Winston Churchill");
        VAT vat = new VAT("123456789");

        PersonVoDTO personVoDTO1 = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, personID, family.getID(),
                personLedgerID);
        Person person1 = PersonFactory.create(personVoDTO1);
        personRepository.save(person1);

        //Add a family member
        TelephoneNumberList telephoneNumberList2 = new TelephoneNumberList();
        telephoneNumberList2.add(new TelephoneNumber("225658541"));
        Email personID2 = new Email("tomtom@hotmail.com");
        Address address2 = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate2 = new BirthDate("1905-01-09");
        PersonName name2 = new PersonName("Tom Churchill");
        VAT vat2 = new VAT("245072659");

        PersonVoDTO personVoDTO2 = new PersonVoDTO(name2, birthDate2, vat2, address2, telephoneNumberList2, personID2, family.getID(),
                personLedgerID2);
        Person person2 = PersonFactory.create(personVoDTO2);
        personRepository.save(person2);

        //Create family cash account
        MoneyValue familyAmountValue = new MoneyValue(new BigDecimal(initialFamilyCashAmount));
        AccountDesignation familyAccountDesignation = new AccountDesignation(designation);
        AccountID familyAccountID = new AccountID(UUID.randomUUID());
        Account familyCashAccount = AccountFactory.createCashAccount(familyAccountID, familyAccountDesignation, familyAmountValue);
        family.addAccountID(familyAccountID);
        this.accountRepository.save(familyCashAccount);
        familyRepository.save(family);

        //Create person1 cash account
        MoneyValue personAmountValue = new MoneyValue(new BigDecimal(initialPersonCashAmount));
        AccountDesignation personAccountDesignation = new AccountDesignation(designation);
        AccountID personAccountID = new AccountID(UUID.randomUUID());
        Account personCashAccount = AccountFactory.createCashAccount(personAccountID, personAccountDesignation, personAmountValue);
        person1.addAccountID(personAccountID);
        this.accountRepository.save(personCashAccount);
        personRepository.save(person1);

        //Create person2 cash account
        MoneyValue personAmountValue2 = new MoneyValue(new BigDecimal(initialPersonCashAmount));
        AccountDesignation personAccountDesignation2 = new AccountDesignation(designation);
        AccountID personAccountID2 = new AccountID(UUID.randomUUID());
        Account personCashAccount2 = AccountFactory.createCashAccount(personAccountID2, personAccountDesignation2, personAmountValue2);
        person2.addAccountID(personAccountID2);
        this.accountRepository.save(personCashAccount2);
        personRepository.save(person2);

        //Create categoryID, description, date and amount to transfer
        String catID = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(catID);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        TransactionDescription transactionDescription = new TransactionDescription("Transaction");
        TransactionDate date = new TransactionDate("2021-03-21");

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(familyID.toString())
                .receiverId(adminID)
                .originAccountId(familyAccountID.toString())
                .destinationAccountId(personAccountID2.toString())
                .amount(50)
                .description(transactionDescription.toString())
                .categoryId(categoryID.toString())
                .date(date.toString())
                .build();

        accountRepository.save(familyCashAccount);
        accountRepository.save(personCashAccount);

        //act
        //assert
        assertThrows(IllegalArgumentException.class, () -> transactionService.transferBetweenCashAccounts(transferDTO));
    }

    @Test
    @DisplayName("Test to transfer a valid amount from a family's member cash account to another family's member cash account")
    void testToTransferAnAmountFromPersonToAPerson() throws Exception {
        //arrange
        //Create Ledgers
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        LedgerID martyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger martyLedger = LedgerFactory.create(martyLedgerID);
        LedgerID georgeLedgerID = new LedgerID(UUID.randomUUID());
        Ledger georgeLedger = LedgerFactory.create(georgeLedgerID);
        ledgerRepository.save(familyLedger);
        ledgerRepository.save(martyLedger);
        ledgerRepository.save(georgeLedger);
        double initialMartyCashAmount = 50;
        double initialGeorgeCashMount = 40;
        String martyDesignation = "Marty's cash account";
        String georgeDesignation = "George's cash account";

        //Create family
        String adminID = "marty_mcfly@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        Email martyID = new Email(adminID);
        FamilyName familyName = new FamilyName("McFly");

        FamilyVoDTO familyVoDTO = new FamilyVoDTO(familyID, familyLedgerID, martyID, familyName);
        Family family = FamilyFactory.create(familyVoDTO);
        familyRepository.save(family);

        //Add a family member
        TelephoneNumberList martyTelephoneNumberList = new TelephoneNumberList();
        martyTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate martyBirthDate = new BirthDate("1968-01-02");
        PersonName martyName = new PersonName("Marty McFly");
        VAT martyVat = new VAT("123456789");

        PersonVoDTO personVoDTO1 = new PersonVoDTO(martyName, martyBirthDate, martyVat, address, martyTelephoneNumberList, martyID, family.getID(),
                martyLedgerID);
        Person marty = PersonFactory.create(personVoDTO1);
        personRepository.save(marty);

        //Add a family member
        TelephoneNumberList georgeTelephoneNumberList = new TelephoneNumberList();
        georgeTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email georgeID = new Email("tomtom@hotmail.com");
        BirthDate georgeBirthDate = new BirthDate("1939-01-09");
        PersonName georgeName = new PersonName("George McFly");
        VAT georgeVat = new VAT("245072659");

        PersonVoDTO personVoDTO2 = new PersonVoDTO(georgeName, georgeBirthDate, georgeVat, address, georgeTelephoneNumberList,
                georgeID, family.getID(), georgeLedgerID);
        Person george = PersonFactory.create(personVoDTO2);
        personRepository.save(george);

        //Create marty's cash account
        MoneyValue martyAmountValue = new MoneyValue(new BigDecimal(initialMartyCashAmount));
        AccountDesignation martyAccountDesignation = new AccountDesignation(martyDesignation);
        AccountID martyAccountID = new AccountID(UUID.randomUUID());
        Account martyCashAccount = AccountFactory.createCashAccount(martyAccountID, martyAccountDesignation, martyAmountValue);
        marty.addAccountID(martyAccountID);
        this.accountRepository.save(martyCashAccount);
        personRepository.save(marty);

        //Create george's cash account
        MoneyValue georgeAmountValue = new MoneyValue(new BigDecimal(initialGeorgeCashMount));
        AccountDesignation georgeAccountDesignation = new AccountDesignation(georgeDesignation);
        AccountID georgeAccountID = new AccountID(UUID.randomUUID());
        Account georgeCashAccount = AccountFactory.createCashAccount(georgeAccountID, georgeAccountDesignation, georgeAmountValue);
        george.addAccountID(georgeAccountID);
        this.accountRepository.save(georgeCashAccount);
        personRepository.save(george);

        //Create categoryID, description, date and amount to transfer
        String catID = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(catID);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        TransactionDescription transactionDescription = new TransactionDescription("Transaction");
        TransactionDate date = new TransactionDate("2021-03-21");

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(martyID.toString())
                .receiverId(georgeID.toString())
                .originAccountId(martyAccountID.toString())
                .destinationAccountId(georgeAccountID.toString())
                .amount(20)
                .description(transactionDescription.toString())
                .categoryId(categoryID.toString())
                .date(date.toString())
                .build();

        accountRepository.save(martyCashAccount);
        accountRepository.save(georgeCashAccount);

        MoneyValue expectedMarty = new MoneyValue(BigDecimal.valueOf(30.0));
        MoneyValue expectedGeorge = new MoneyValue(BigDecimal.valueOf(60.0));

        //act
        transactionService.transferBetweenCashAccounts(transferDTO);

        MoneyValue finalMartyAmount = accountRepository.findByID(martyAccountID).getBalance();
        MoneyValue finalGeorgeAmount = accountRepository.findByID(georgeAccountID).getBalance();

        //assert
        assertEquals(expectedMarty, finalMartyAmount);
        assertEquals(expectedGeorge, finalGeorgeAmount);
    }

    @Test
    @DisplayName("Test to transfer an invalid amount from a family's member cash account to another family's member cash account")
    void testToTransferAnInvalidAmountFromPersonToAPerson() throws Exception {
        //arrange
        //Create Ledgers
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        LedgerID martyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger martyLedger = LedgerFactory.create(martyLedgerID);
        LedgerID georgeLedgerID = new LedgerID(UUID.randomUUID());
        Ledger georgeLedger = LedgerFactory.create(georgeLedgerID);
        ledgerRepository.save(familyLedger);
        ledgerRepository.save(martyLedger);
        ledgerRepository.save(georgeLedger);
        double initialMartyCashAmount = 50;
        double initialGeorgeCashMount = 40;
        String martyDesignation = "Marty's cash account";
        String georgeDesignation = "George's cash account";

        //Create family
        String adminID = "marty_mcfly@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        Email martyID = new Email(adminID);
        FamilyName familyName = new FamilyName("McFly");

        FamilyVoDTO familyVoDTO = new FamilyVoDTO(familyID, familyLedgerID, martyID, familyName);
        Family family = FamilyFactory.create(familyVoDTO);
        familyRepository.save(family);

        //Add a family member
        TelephoneNumberList martyTelephoneNumberList = new TelephoneNumberList();
        martyTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate martyBirthDate = new BirthDate("1968-01-02");
        PersonName martyName = new PersonName("Marty McFly");
        VAT martyVat = new VAT("123456789");

        PersonVoDTO personVoDTO1 = new PersonVoDTO(martyName, martyBirthDate, martyVat, address, martyTelephoneNumberList, martyID, family.getID(),
                martyLedgerID);
        Person marty = PersonFactory.create(personVoDTO1);
        personRepository.save(marty);

        //Add a family member
        TelephoneNumberList georgeTelephoneNumberList = new TelephoneNumberList();
        georgeTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email georgeID = new Email("tomtom@hotmail.com");
        BirthDate georgeBirthDate = new BirthDate("1939-01-09");
        PersonName georgeName = new PersonName("George McFly");
        VAT georgeVat = new VAT("245072659");

        PersonVoDTO personVoDTO2 = new PersonVoDTO(georgeName, georgeBirthDate, georgeVat, address, georgeTelephoneNumberList,
                georgeID, family.getID(), georgeLedgerID);
        Person george = PersonFactory.create(personVoDTO2);
        personRepository.save(george);

        //Create marty's cash account
        MoneyValue martyAmountValue = new MoneyValue(new BigDecimal(initialMartyCashAmount));
        AccountDesignation martyAccountDesignation = new AccountDesignation(martyDesignation);
        AccountID martyAccountID = new AccountID(UUID.randomUUID());
        Account martyCashAccount = AccountFactory.createCashAccount(martyAccountID, martyAccountDesignation, martyAmountValue);
        marty.addAccountID(martyAccountID);
        this.accountRepository.save(martyCashAccount);
        personRepository.save(marty);

        //Create george's cash account
        MoneyValue georgeAmountValue = new MoneyValue(new BigDecimal(initialGeorgeCashMount));
        AccountDesignation georgeAccountDesignation = new AccountDesignation(georgeDesignation);
        AccountID georgeAccountID = new AccountID(UUID.randomUUID());
        Account georgeCashAccount = AccountFactory.createCashAccount(georgeAccountID, georgeAccountDesignation, georgeAmountValue);
        george.addAccountID(georgeAccountID);
        this.accountRepository.save(georgeCashAccount);
        personRepository.save(george);

        //Create categoryID, description, date and amount to transfer
        String catID = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(catID);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        TransactionDescription transactionDescription = new TransactionDescription("Transaction");
        TransactionDate date = new TransactionDate("2021-03-21");

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(martyID.toString())
                .receiverId(georgeID.toString())
                .originAccountId(martyAccountID.toString())
                .destinationAccountId(georgeAccountID.toString())
                .amount(-20)
                .description(transactionDescription.toString())
                .categoryId(categoryID.toString())
                .date(date.toString())
                .build();

        accountRepository.save(martyCashAccount);
        accountRepository.save(georgeCashAccount);

        //act
        //assert
        assertThrows(WithdrawNotPossibleException.class, () -> transactionService.transferBetweenCashAccounts(transferDTO));
    }

    @Test
    @DisplayName("Test to transfer a valid amount from a person's cash account to a person who does not belong to the same family")
    void testToTransferAValidAmountFromPersonToAPersonWhoIsNotInTheSameFamily() throws Exception {
        //arrange
        //Create Ledgers
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        LedgerID familyLedgerID2 = new LedgerID(UUID.randomUUID());
        Ledger familyLedger2 = LedgerFactory.create(familyLedgerID2);
        LedgerID martyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger martyLedger = LedgerFactory.create(martyLedgerID);
        LedgerID georgeLedgerID = new LedgerID(UUID.randomUUID());
        Ledger georgeLedger = LedgerFactory.create(georgeLedgerID);
        ledgerRepository.save(familyLedger);
        ledgerRepository.save(martyLedger);
        ledgerRepository.save(georgeLedger);
        ledgerRepository.save(familyLedger2);
        double initialMartyCashAmount = 50;
        double initialGeorgeCashMount = 40;
        String martyDesignation = "Marty's cash account";
        String georgeDesignation = "George's cash account";

        //Create the first family
        String adminID = "marty_mcfly@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        Email martyID = new Email(adminID);
        FamilyName familyName = new FamilyName("McFly");

        FamilyVoDTO familyVoDTO1 = new FamilyVoDTO(familyID, familyLedgerID, martyID, familyName);
        Family family1 = FamilyFactory.create(familyVoDTO1);
        familyRepository.save(family1);

        //Create the second family
        String adminID2 = "tomtom@hotmail.com";
        UUID familyUUID2 = UUID.randomUUID();
        Email georgeID = new Email("tomtom@hotmail.com");
        FamilyID familyID2 = new FamilyID(familyUUID2);
        FamilyName familyName2 = new FamilyName("Tannen");

        FamilyVoDTO familyVoDTO2 = new FamilyVoDTO(familyID2, familyLedgerID2, georgeID, familyName2);
        Family family2 = FamilyFactory.create(familyVoDTO2);
        familyRepository.save(family2);

        //Add a family member
        TelephoneNumberList martyTelephoneNumberList = new TelephoneNumberList();
        martyTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate martyBirthDate = new BirthDate("1968-01-02");
        PersonName martyName = new PersonName("Marty McFly");
        VAT martyVat = new VAT("123456789");

        PersonVoDTO personVoDTO1 = new PersonVoDTO(martyName, martyBirthDate, martyVat, address, martyTelephoneNumberList, martyID, family1.getID(),
                martyLedgerID);
        Person marty = PersonFactory.create(personVoDTO1);
        personRepository.save(marty);

        //Add a family member
        TelephoneNumberList georgeTelephoneNumberList = new TelephoneNumberList();
        georgeTelephoneNumberList.add(new TelephoneNumber("225658541"));
        BirthDate georgeBirthDate = new BirthDate("1939-01-09");
        PersonName georgeName = new PersonName("George McFly");
        VAT georgeVat = new VAT("245072659");


        PersonVoDTO personVoDTO2 = new PersonVoDTO(georgeName, georgeBirthDate, georgeVat, address, georgeTelephoneNumberList,
                georgeID, family2.getID(), georgeLedgerID);
        Person george = PersonFactory.create(personVoDTO2);
        personRepository.save(george);

        //Create marty's cash account
        MoneyValue martyAmountValue = new MoneyValue(new BigDecimal(initialMartyCashAmount));
        AccountDesignation martyAccountDesignation = new AccountDesignation(martyDesignation);
        AccountID martyAccountID = new AccountID(UUID.randomUUID());
        Account martyCashAccount = AccountFactory.createCashAccount(martyAccountID, martyAccountDesignation, martyAmountValue);
        marty.addAccountID(martyAccountID);
        this.accountRepository.save(martyCashAccount);
        personRepository.save(marty);

        //Create george's cash account
        MoneyValue georgeAmountValue = new MoneyValue(new BigDecimal(initialGeorgeCashMount));
        AccountDesignation georgeAccountDesignation = new AccountDesignation(georgeDesignation);
        AccountID georgeAccountID = new AccountID(UUID.randomUUID());
        Account georgeCashAccount = AccountFactory.createCashAccount(georgeAccountID, georgeAccountDesignation, georgeAmountValue);
        george.addAccountID(georgeAccountID);
        this.accountRepository.save(georgeCashAccount);
        personRepository.save(george);

        //Create categoryID, description, date and amount to transfer
        String catID = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(catID);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        TransactionDescription transactionDescription = new TransactionDescription("Transaction");
        TransactionDate date = new TransactionDate("2021-03-21");

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(martyID.toString())
                .receiverId(georgeID.toString())
                .originAccountId(martyAccountID.toString())
                .destinationAccountId(georgeAccountID.toString())
                .amount(20)
                .description(transactionDescription.toString())
                .categoryId(categoryID.toString())
                .date(date.toString())
                .build();

        accountRepository.save(martyCashAccount);
        accountRepository.save(georgeCashAccount);

        //act
        //assert
        assertThrows(IllegalArgumentException.class, () -> transactionService.transferBetweenCashAccounts(transferDTO));
    }

    @Test
    @DisplayName("Test to transfer a valid amount from a person's cash account to another person, but they are not the account holders")
    void testToTransferAValidAmountFromPersonToAPersonWhoIsNotTheAccountHolder() throws Exception {
        //arrange
        //Create Ledgers
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        LedgerID martyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger martyLedger = LedgerFactory.create(martyLedgerID);
        LedgerID georgeLedgerID = new LedgerID(UUID.randomUUID());
        Ledger georgeLedger = LedgerFactory.create(georgeLedgerID);
        ledgerRepository.save(familyLedger);
        ledgerRepository.save(martyLedger);
        ledgerRepository.save(georgeLedger);
        double initialMartyCashAmount = 50;
        double initialGeorgeCashMount = 40;
        String martyDesignation = "Marty's cash account";
        String georgeDesignation = "George's cash account";

        //Create a family
        String adminID = "marty_mcfly@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        Email martyID = new Email(adminID);
        FamilyName familyName = new FamilyName("McFly");

        FamilyVoDTO familyVoDTO = new FamilyVoDTO(familyID, familyLedgerID, martyID, familyName);
        Family family = FamilyFactory.create(familyVoDTO);
        familyRepository.save(family);

        //Add a family member
        TelephoneNumberList martyTelephoneNumberList = new TelephoneNumberList();
        martyTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate martyBirthDate = new BirthDate("1968-01-02");
        PersonName martyName = new PersonName("Marty McFly");
        VAT martyVat = new VAT("123456789");

        PersonVoDTO personVoDTO1 = new PersonVoDTO(martyName, martyBirthDate, martyVat, address, martyTelephoneNumberList, martyID, family.getID(),
                martyLedgerID);
        Person marty = PersonFactory.create(personVoDTO1);
        personRepository.save(marty);

        //Add a family member
        TelephoneNumberList georgeTelephoneNumberList = new TelephoneNumberList();
        georgeTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email georgeID = new Email("tomtom@hotmail.com");
        BirthDate georgeBirthDate = new BirthDate("1939-01-09");
        PersonName georgeName = new PersonName("George McFly");
        VAT georgeVat = new VAT("245072659");

        PersonVoDTO personVoDTO2 = new PersonVoDTO(georgeName, georgeBirthDate, georgeVat, address, georgeTelephoneNumberList,
                georgeID, family.getID(), georgeLedgerID);
        Person george = PersonFactory.create(personVoDTO2);
        personRepository.save(george);

        //Create marty's cash account
        MoneyValue martyAmountValue = new MoneyValue(new BigDecimal(initialMartyCashAmount));
        AccountDesignation martyAccountDesignation = new AccountDesignation(martyDesignation);
        AccountID martyAccountID = new AccountID(UUID.randomUUID());
        Account martyCashAccount = AccountFactory.createCashAccount(martyAccountID, martyAccountDesignation, martyAmountValue);
        marty.addAccountID(martyAccountID);
        this.accountRepository.save(martyCashAccount);
        personRepository.save(marty);

        //Create george's cash account
        MoneyValue georgeAmountValue = new MoneyValue(new BigDecimal(initialGeorgeCashMount));
        AccountDesignation georgeAccountDesignation = new AccountDesignation(georgeDesignation);
        AccountID georgeAccountID = new AccountID(UUID.randomUUID());
        Account georgeCashAccount = AccountFactory.createCashAccount(georgeAccountID, georgeAccountDesignation, georgeAmountValue);
        marty.addAccountID(georgeAccountID);
        this.accountRepository.save(georgeCashAccount);
        personRepository.save(george);

        //Create categoryID, description, date and amount to transfer
        String catID = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(catID);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        TransactionDescription transactionDescription = new TransactionDescription("Transaction");
        TransactionDate date = new TransactionDate("2021-03-21");

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(martyID.toString())
                .receiverId(georgeID.toString())
                .originAccountId(martyAccountID.toString())
                .destinationAccountId(georgeAccountID.toString())
                .amount(20)
                .description(transactionDescription.toString())
                .categoryId(categoryID.toString())
                .date(date.toString())
                .build();

        accountRepository.save(martyCashAccount);
        accountRepository.save(georgeCashAccount);

        //act
        //assert
        assertThrows(IllegalArgumentException.class, () -> transactionService.transferBetweenCashAccounts(transferDTO));
    }

    @Test
    @DisplayName("Test to transfer a valid amount from a person's cash account to an account that is not cash account")
    void testToTransferAValidAmountFromPersonToAnNonCashAccount() throws Exception {
        //arrange
        //Create Ledgers
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        LedgerID martyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger martyLedger = LedgerFactory.create(martyLedgerID);
        LedgerID georgeLedgerID = new LedgerID(UUID.randomUUID());
        Ledger georgeLedger = LedgerFactory.create(georgeLedgerID);
        ledgerRepository.save(familyLedger);
        ledgerRepository.save(martyLedger);
        ledgerRepository.save(georgeLedger);
        double initialMartyCashAmount = 50;
        double initialGeorgeCashMount = 40;
        String martyDesignation = "Marty's cash account";
        String georgeDesignation = "George's saving account";

        //Create a family
        String adminID = "marty_mcfly@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        Email martyID = new Email(adminID);
        FamilyName familyName = new FamilyName("McFly");

        FamilyVoDTO familyVoDTO = new FamilyVoDTO(familyID, familyLedgerID, martyID, familyName);
        Family family = FamilyFactory.create(familyVoDTO);
        familyRepository.save(family);

        //Add a family member
        TelephoneNumberList martyTelephoneNumberList = new TelephoneNumberList();
        martyTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate martyBirthDate = new BirthDate("1968-01-02");
        PersonName martyName = new PersonName("Marty McFly");
        VAT martyVat = new VAT("123456789");

        PersonVoDTO personVoDTO1 = new PersonVoDTO(martyName, martyBirthDate, martyVat, address, martyTelephoneNumberList, martyID, family.getID(),
                martyLedgerID);
        Person marty = PersonFactory.create(personVoDTO1);
        personRepository.save(marty);

        //Add a family member
        TelephoneNumberList georgeTelephoneNumberList = new TelephoneNumberList();
        georgeTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email georgeID = new Email("tomtom@hotmail.com");
        BirthDate georgeBirthDate = new BirthDate("1939-01-09");
        PersonName georgeName = new PersonName("George McFly");
        VAT georgeVat = new VAT("245072659");

        PersonVoDTO personVoDTO2 = new PersonVoDTO(georgeName, georgeBirthDate, georgeVat, address, georgeTelephoneNumberList,
                georgeID, family.getID(), georgeLedgerID);
        Person george = PersonFactory.create(personVoDTO2);
        personRepository.save(george);

        //Create marty's cash account
        MoneyValue martyAmountValue = new MoneyValue(new BigDecimal(initialMartyCashAmount));
        AccountDesignation martyAccountDesignation = new AccountDesignation(martyDesignation);
        AccountID martyAccountID = new AccountID(UUID.randomUUID());
        Account martyCashAccount = AccountFactory.createCashAccount(martyAccountID, martyAccountDesignation, martyAmountValue);
        marty.addAccountID(martyAccountID);
        this.accountRepository.save(martyCashAccount);
        personRepository.save(marty);

        //Create george's bank saving account
        AccountDesignation georgeAccountDesignation = new AccountDesignation(georgeDesignation);
        AccountID georgeAccountID = new AccountID(UUID.randomUUID());
        Account georgeSavingAccount = AccountFactory.createBankAccount(georgeAccountID, georgeAccountDesignation, Constants.BANK_SAVINGS_ACCOUNT_TYPE);
        george.addAccountID(georgeAccountID);
        this.accountRepository.save(georgeSavingAccount);
        personRepository.save(george);

        //Create categoryID, description, date and amount to transfer
        String catID = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(catID);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        TransactionDescription transactionDescription = new TransactionDescription("Transaction");
        TransactionDate date = new TransactionDate("2021-03-21");

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(martyID.toString())
                .receiverId(georgeID.toString())
                .originAccountId(martyAccountID.toString())
                .destinationAccountId(georgeAccountID.toString())
                .amount(20)
                .description(transactionDescription.toString())
                .categoryId(categoryID.toString())
                .date(date.toString())
                .build();

        accountRepository.save(martyCashAccount);
        accountRepository.save(georgeSavingAccount);

        //act
        //assert
        assertThrows(UnsupportedOperationException.class, () -> transactionService.transferBetweenCashAccounts(transferDTO));
    }
}