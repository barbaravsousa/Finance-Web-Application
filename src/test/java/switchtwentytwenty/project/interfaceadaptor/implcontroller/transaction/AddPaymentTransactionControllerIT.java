package switchtwentytwenty.project.interfaceadaptor.implcontroller.transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ITransactionService;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.AuthorizationService;
import switchtwentytwenty.project.applicationservice.irepository.*;
import switchtwentytwenty.project.autentication.*;
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
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.*;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.indto.PaymentInDTO;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AddPaymentTransactionControllerIT {

    //Services required
    @Autowired
    IPersonRepository personRepository;
    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    ILedgerRepository ledgerRepository;
    @Autowired    //classe que vai receber o mock
    AddPaymentTransactionController controller;
    @Autowired
    IAccountRepository accountRepository;
    @Autowired
    ICategoryRepository categoryRepository;
    @Mock       //class ao que vou fazer mock
    HttpServletRequest request;
    @Mock
    Principal principal;
    @Autowired
    AuthorizationService authorizationService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ITransactionService transactionService;

    @BeforeEach
    public void before() {
        personRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("add 1 payment successfully")
    void addOnePaymentTransaction() throws Exception {
        //arrange
        //create family and administrator ledger
        LedgerID personLedgerID = new LedgerID(UUID.randomUUID());
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger personLedger = LedgerFactory.create(personLedgerID);
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        ledgerRepository.save(personLedger);
        ledgerRepository.save(familyLedger);
        //create family and administrator
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email adminID = new Email("jones@gmail.com");
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, adminID, new FamilyName("Bones"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        Address address = new Address("Dragons Street", "23", "2345-453", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1965-03-23");
        VAT vat = new VAT("292965001");
        PersonName personName = new PersonName("Indy Jones");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();

        PersonVoDTO voPersonDTO = new PersonVoDTO(personName, birthDate, vat, address, telephoneNumberList, adminID, familyID, personLedgerID);
        Person admin = PersonFactory.create(voPersonDTO);
        personRepository.save(admin);

        //create admin account
        AccountID accountID = new AccountID(UUID.randomUUID());
        MoneyValue initialAmount = new MoneyValue(new BigDecimal("300"));
        AccountDesignation accountDesignation = new AccountDesignation("my cash account");
        Account account = AccountFactory.createCashAccount(accountID, accountDesignation, initialAmount);
        accountRepository.save(account);
        Person person = personRepository.findByID(adminID);
        person.addAccountID(accountID);
        personRepository.save(person);

        //create user
        String username = "admin";
        String password = "123";
        String roleJPA = "ROLE_ADMIN";
        Set<String> role = new HashSet<>();
        role.add(roleJPA);
        SignupDTO signupDTO = new SignupDTO(username,adminID.toString(),password,familyID.toString(),role);
        authorizationService.registerUser(signupDTO);

        //create category
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);

        //payment dto
        PaymentInDTO paymentDTO = new PaymentInDTO();
        paymentDTO.setAmount(50);
        paymentDTO.setCategoryID(categoryID.toString());
        paymentDTO.setDesignation("dinner");
        paymentDTO.setPersonID(adminID.toString());
        paymentDTO.setDate("2021-01-09");
        //mock request
        Mockito.doReturn(principal).when(request).getUserPrincipal();
        Mockito.doReturn(username).when(principal).getName();

        //act
        ResponseEntity<Object> result = controller.addPaymentTransaction(request,accountID.toString(), paymentDTO);
        //assert
        assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("add 2 payments successfully")
    void addTwoPaymentTransaction() throws Exception {
        //arrange
        //create family and administrator ledger
        LedgerID personLedgerID = new LedgerID(UUID.randomUUID());
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger personLedger = LedgerFactory.create(personLedgerID);
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        ledgerRepository.save(personLedger);
        ledgerRepository.save(familyLedger);
        //create family and administrator
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email adminID = new Email("jones@gmail.com");
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, adminID, new FamilyName("Bones"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        Address address = new Address("Dragons Street", "23", "2345-453", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1965-03-23");
        VAT vat = new VAT("292965001");
        PersonName personName = new PersonName("Indy Jones");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();

        PersonVoDTO voPersonDTO = new PersonVoDTO(personName, birthDate, vat, address, telephoneNumberList, adminID, familyID, personLedgerID);
        Person admin = PersonFactory.create(voPersonDTO);
        personRepository.save(admin);
        //create admin account
        AccountID accountID = new AccountID(UUID.randomUUID());
        MoneyValue initialAmount = new MoneyValue(new BigDecimal("100"));
        AccountDesignation accountDesignation = new AccountDesignation("my cash account");
        Account account = AccountFactory.createCashAccount(accountID, accountDesignation, initialAmount);
        accountRepository.save(account);
        Person person = personRepository.findByID(adminID);
        person.addAccountID(accountID);
        personRepository.save(person);
        //create category
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);

        //create user
        String username = "admin";
        String password = "123";
        String roleJPA = "ROLE_ADMIN";
        Set<String> role = new HashSet<>();
        role.add(roleJPA);
        SignupDTO signupDTO = new SignupDTO(username,adminID.toString(),password,familyID.toString(),role);
        authorizationService.registerUser(signupDTO);

        //payment dto
        PaymentInDTO paymentDTO1 = new PaymentInDTO();
        paymentDTO1.setAmount(70);
        paymentDTO1.setCategoryID(categoryID.toString());
        paymentDTO1.setDesignation("dinner");
        paymentDTO1.setPersonID(adminID.toString());
        paymentDTO1.setDate("2020-12-12");
        PaymentInDTO paymentDTO2 = new PaymentInDTO();
        paymentDTO2.setAmount(30);
        paymentDTO2.setCategoryID(categoryID.toString());
        paymentDTO2.setDesignation("dinner");
        paymentDTO2.setPersonID(adminID.toString());
        paymentDTO2.setDate("2020-12-12");
        //mock request
        Mockito.doReturn(principal).when(request).getUserPrincipal();
        Mockito.doReturn(username).when(principal).getName();
        //act
        ResponseEntity<Object> result1 = controller.addPaymentTransaction(request,accountID.toString(), paymentDTO1);
        ResponseEntity<Object> result2 = controller.addPaymentTransaction(request,accountID.toString(), paymentDTO2);
        //assert
        assertEquals(201, result1.getStatusCodeValue());
        assertEquals(201, result2.getStatusCodeValue());
    }

    @Test
    @DisplayName("not enough money for the second payments successfully")
    void addOnlyOnePaymentTransactionAndErrorOntheSecond() throws Exception {
        //arrange
        //create family and administrator ledger
        LedgerID personLedgerID = new LedgerID(UUID.randomUUID());
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger personLedger = LedgerFactory.create(personLedgerID);
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        ledgerRepository.save(personLedger);
        ledgerRepository.save(familyLedger);
        //create family and administrator
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email adminID = new Email("jones@gmail.com");
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, adminID, new FamilyName("Bones"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        Address address = new Address("Dragons Street", "23", "2345-453", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1965-03-23");
        VAT vat = new VAT("292965001");
        PersonName personName = new PersonName("Indy Jones");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        PersonVoDTO voPersonDTO = new PersonVoDTO(personName, birthDate, vat, address, telephoneNumberList, adminID, familyID, personLedgerID);
        Person admin = PersonFactory.create(voPersonDTO);
        personRepository.save(admin);
        //create user
        String username = "admin";
        String password = "123";
        String roleJPA = "ROLE_ADMIN";
        Set<String> role = new HashSet<>();
        role.add(roleJPA);
        SignupDTO signupDTO = new SignupDTO(username,adminID.toString(),password,familyID.toString(),role);
        authorizationService.registerUser(signupDTO);
        //create admin account
        AccountID accountID = new AccountID(UUID.randomUUID());
        MoneyValue initialAmount = new MoneyValue(new BigDecimal("100"));
        AccountDesignation accountDesignation = new AccountDesignation("my cash account");
        Account account = AccountFactory.createCashAccount(accountID, accountDesignation, initialAmount);
        accountRepository.save(account);
        Person person = personRepository.findByID(adminID);
        person.addAccountID(accountID);
        personRepository.save(person);
        //create category
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        //mock request
        Mockito.doReturn(principal).when(request).getUserPrincipal();
        Mockito.doReturn(username).when(principal).getName();
        //payment dto
        PaymentInDTO paymentDTO1 = new PaymentInDTO();
        paymentDTO1.setAmount(70);
        paymentDTO1.setCategoryID(categoryID.toString());
        paymentDTO1.setDesignation("dinner");
        paymentDTO1.setPersonID(adminID.toString());
        paymentDTO1.setDate("2020-12-12");
        PaymentInDTO paymentDTO2 = new PaymentInDTO();
        paymentDTO2.setAmount(31);
        paymentDTO2.setCategoryID(categoryID.toString());
        paymentDTO2.setDesignation("dinner");
        paymentDTO2.setPersonID(adminID.toString());
        paymentDTO2.setDate("2020-12-12");
        //act
        ResponseEntity<Object> result1 = controller.addPaymentTransaction(request,accountID.toString(), paymentDTO1);
        ResponseEntity<Object> result2 = controller.addPaymentTransaction(request,accountID.toString(), paymentDTO2);
        //assert
        assertEquals(201, result1.getStatusCodeValue());
        assertEquals(400, result2.getStatusCodeValue());
    }

    @Test
    @DisplayName("account doesn't exist")
    void addPaymentTransactionThroughAnAccountThatDoesNotExist() throws Exception {
        //arrange
        //create family and administrator ledger
        LedgerID personLedgerID = new LedgerID(UUID.randomUUID());
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger personLedger = LedgerFactory.create(personLedgerID);
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        ledgerRepository.save(personLedger);
        ledgerRepository.save(familyLedger);
        //create family and administrator
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email adminID = new Email("jones@gmail.com");
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, adminID, new FamilyName("Bones"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        Address address = new Address("Dragons Street", "23", "2345-453", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1965-03-23");
        VAT vat = new VAT("292965001");
        PersonName personName = new PersonName("Indy Jones");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        PersonVoDTO voPersonDTO = new PersonVoDTO(personName, birthDate, vat, address, telephoneNumberList, adminID, familyID, personLedgerID);
        Person admin = PersonFactory.create(voPersonDTO);
        personRepository.save(admin);
        //create user
        String username = "admin";
        String password = "123";
        String roleJPA = "ROLE_ADMIN";
        Set<String> role = new HashSet<>();
        role.add(roleJPA);
        SignupDTO signupDTO = new SignupDTO(username,adminID.toString(),password,familyID.toString(),role);
        authorizationService.registerUser(signupDTO);
        //create admin account
        AccountID accountID = new AccountID(UUID.randomUUID());
        MoneyValue initialAmount = new MoneyValue(new BigDecimal("100"));
        AccountDesignation accountDesignation = new AccountDesignation("my cash account");
        Account account = AccountFactory.createCashAccount(accountID, accountDesignation, initialAmount);
        accountRepository.save(account);
        Person person = personRepository.findByID(adminID);
        person.addAccountID(accountID);
        personRepository.save(person);
        //create category
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        //payment dto
        PaymentInDTO paymentDTO1 = new PaymentInDTO();
        paymentDTO1.setAmount(70);
        paymentDTO1.setCategoryID(categoryID.toString());
        paymentDTO1.setDesignation("dinner");
        paymentDTO1.setPersonID(adminID.toString());
        paymentDTO1.setDate("2020-12-12");
        //mock request
        Mockito.doReturn(principal).when(request).getUserPrincipal();
        Mockito.doReturn(username).when(principal).getName();
        //act
        ResponseEntity<Object> result1 = controller.addPaymentTransaction(request,UUID.randomUUID().toString(), paymentDTO1);
        //assert
        assertEquals(400, result1.getStatusCodeValue());
    }

    @Test
    @DisplayName("add payment with a non cash account")
    void addPaymentTransaction_notCashAccount() throws Exception {
        //arrange
        //create family and administrator ledger
        LedgerID personLedgerID = new LedgerID(UUID.randomUUID());
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        Ledger personLedger = LedgerFactory.create(personLedgerID);
        Ledger familyLedger = LedgerFactory.create(familyLedgerID);
        ledgerRepository.save(personLedger);
        ledgerRepository.save(familyLedger);
        //create family and administrator
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email adminID = new Email("jones@gmail.com");
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, adminID, new FamilyName("Bones"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        Address address = new Address("Dragons Street", "23", "2345-453", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1965-03-23");
        VAT vat = new VAT("292965001");
        PersonName personName = new PersonName("Indy Jones");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        PersonVoDTO voPersonDTO = new PersonVoDTO(personName, birthDate, vat, address, telephoneNumberList, adminID, familyID, personLedgerID);
        Person admin = PersonFactory.create(voPersonDTO);
        personRepository.save(admin);
        //create user
        String username = "admin";
        String password = "123";
        String roleJPA = "ROLE_ADMIN";
        Set<String> role = new HashSet<>();
        role.add(roleJPA);
        SignupDTO signupDTO = new SignupDTO(username,adminID.toString(),password,familyID.toString(),role);
        authorizationService.registerUser(signupDTO);
        //create admin account
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("my cash account");
        Account account = AccountFactory.createBankAccount(accountID, accountDesignation, Constants.BANK_SAVINGS_ACCOUNT_TYPE);
        accountRepository.save(account);
        Person person = personRepository.findByID(adminID);
        person.addAccountID(accountID);
        personRepository.save(person);
        //create category
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        categoryRepository.save(category);
        //payment dto
        PaymentInDTO paymentDTO = new PaymentInDTO();
        paymentDTO.setAmount(50);
        paymentDTO.setCategoryID(categoryID.toString());
        paymentDTO.setDesignation("dinner");
        paymentDTO.setPersonID(adminID.toString());
        paymentDTO.setDate("2020-12-12");
        //mock request
        Mockito.doReturn(principal).when(request).getUserPrincipal();
        Mockito.doReturn(username).when(principal).getName();
        //act
        ResponseEntity<Object> result = controller.addPaymentTransaction(request,accountID.toString(), paymentDTO);
        //assert
        assertEquals(400, result.getStatusCodeValue());
    }
}


