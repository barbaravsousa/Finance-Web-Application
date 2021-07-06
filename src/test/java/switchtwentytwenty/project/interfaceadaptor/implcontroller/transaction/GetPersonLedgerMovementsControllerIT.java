package switchtwentytwenty.project.interfaceadaptor.implcontroller.transaction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.AuthorizationService;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.TransactionService;
import switchtwentytwenty.project.autentication.SignupDTO;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.account.AccountFactory;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.domaindto.PersonAndLedgerDomainDTO;
import switchtwentytwenty.project.domain.domainservice.PersonAndLedgerFactory;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.*;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.outdto.LedgerMovementOutDTO;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.dto.toservicedto.PaymentDTO;
import switchtwentytwenty.project.dto.toservicedto.TransferDTO;
import switchtwentytwenty.project.interfaceadaptor.repository.*;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GetPersonLedgerMovementsControllerIT {

    @Autowired
    GetPersonLedgerMovementsController controller;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    FamilyRepository familyRepository;
    @Autowired
    TransactionService transactionService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    LedgerRepository ledgerRepository;
    @Autowired
    AuthorizationService authorizationService;


    @Test
    @DisplayName("Get list of ledger movements: empty list")
    void getLedgerMovements_EmptyList() throws Exception {
        //arrange
        //create family
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email email = new Email("homer_simpson@gmail.com");
        FamilyName familyName = new FamilyName("Simpson");
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, new LedgerID(UUID.randomUUID()), email, familyName);
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //create member and ledger
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        Address address = new Address("Springfield", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1961-01-05");
        PersonName name = new PersonName("Homer Simpson");
        VAT vat = new VAT("245014314");
        PersonVoDTO voPersonDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, email, familyID,
                ledgerID);
        PersonAndLedgerDomainDTO personAndLedger = PersonAndLedgerFactory.create(voPersonDTO);
        personRepository.save(personAndLedger.getPerson());
        ledgerRepository.save(personAndLedger.getLedger());


        //act
        ResponseEntity<Object> result = controller.getListOfPersonLedgerMovements(email.toString());
        HttpStatus status = result.getStatusCode();
        Object resultList = result.getBody();
        //assert
        assertEquals(HttpStatus.OK, status);
        assertEquals(Collections.emptyList(), resultList);
    }

    @Test
    @DisplayName("Get list of ledger movements: with payment")
    void getLedgerMovements_WithPayment() throws Exception {
        //arrange
        //create family
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email email = new Email("homer_simpson@gmail.com");
        FamilyName familyName = new FamilyName("Simpson");
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, new LedgerID(UUID.randomUUID()), email, familyName);
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //create member and ledger
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        Address address = new Address("Springfield", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1961-01-05");
        PersonName name = new PersonName("Homer Simpson");
        VAT vat = new VAT("245014314");
        PersonVoDTO voPersonDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, email, familyID,
                ledgerID);
        PersonAndLedgerDomainDTO personAndLedger = PersonAndLedgerFactory.create(voPersonDTO);
        Person person = personAndLedger.getPerson();
        personRepository.save(person);
        ledgerRepository.save(personAndLedger.getLedger());
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        authorizationService.registerUser(new SignupDTO("homer", "homer_simpson@gmail.com", "admin", familyID.toString(), roles));

        //create account
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Conta");
        MoneyValue initialAmount = new MoneyValue(BigDecimal.valueOf(56.20));
        Account cashAccount = AccountFactory.createCashAccount(accountID, accountDesignation, initialAmount);
        person.addAccountID(accountID);
        this.accountRepository.save(cashAccount);
        this.personRepository.save(person);

        //create category
        Category category = CategoryFactory.create(new CategoryDesignation("food"), new CategoryID(UUID.randomUUID().toString()),
                new CategoryID(UUID.randomUUID().toString()));

        categoryRepository.save(category);

        //create payment
        PaymentDTO paymentDTO = new PaymentDTO(email.toString(), "tomatoes", accountID.toString(), category.getID().toString(), 10.25, "2020-05-01");
        transactionService.addPaymentTransaction(paymentDTO, "homer");

        //expected list
        List<LedgerMovementOutDTO> expected = new ArrayList<>();
        LedgerMovementOutDTO expectedPayment = new LedgerMovementOutDTO();
        expectedPayment.setDate("2020-05-01");
        expectedPayment.setMovementType("PAYMENT");
        expectedPayment.setAmount(10.25);
        expectedPayment.setSenderAccount("Conta");
        expectedPayment.setDescription("Tomatoes");
        expectedPayment.setCategory("Food");
        expectedPayment.setBalanceToThisDate(45.95);
        expected.add(expectedPayment);

        //act
        ResponseEntity<Object> result = controller.getListOfPersonLedgerMovements(email.toString());
        HttpStatus status = result.getStatusCode();
        Object resultList = result.getBody();
        //assert
        assertEquals(HttpStatus.OK, status);
        assertEquals(expected, resultList);
    }

    @Test
    @DisplayName("Get list of ledger movements: with debit transfer")
    void getLedgerMovements_WithDebitTransfer() throws Exception {
        //arrange
        //create family
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email email = new Email("homer_simpson@gmail.com");
        FamilyName familyName = new FamilyName("Simpson");
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, new LedgerID(UUID.randomUUID()), email, familyName);
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //create member and ledger
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        Address address = new Address("Springfield", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1961-01-05");
        PersonName name = new PersonName("Homer Simpson");
        VAT vat = new VAT("245014314");
        PersonVoDTO voPersonDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, email, familyID,
                ledgerID);
        PersonAndLedgerDomainDTO personAndLedger = PersonAndLedgerFactory.create(voPersonDTO);
        Person person = personAndLedger.getPerson();
        personRepository.save(person);
        ledgerRepository.save(personAndLedger.getLedger());

        //create other member and ledger
        Email otherEmail = new Email("bart@gmail.com");
        PersonName otherName = new PersonName("Bart Simpson");
        VAT otherVat = new VAT("123456789");
        PersonVoDTO otherVoPersonDTO = new PersonVoDTO(otherName, birthDate, otherVat, address, telephoneNumberList, otherEmail, familyID,
                new LedgerID(UUID.randomUUID()));
        PersonAndLedgerDomainDTO otherPersonAndLedger = PersonAndLedgerFactory.create(otherVoPersonDTO);
        Person otherPerson = otherPersonAndLedger.getPerson();
        personRepository.save(otherPerson);
        ledgerRepository.save(otherPersonAndLedger.getLedger());

        //create account
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        MoneyValue initialAmount = new MoneyValue(BigDecimal.valueOf(56.20));
        Account cashAccount = AccountFactory.createCashAccount(accountID, accountDesignation, initialAmount);
        person.addAccountID(accountID);
        this.accountRepository.save(cashAccount);
        this.personRepository.save(person);

        //create other account
        AccountID otherAccountID = new AccountID(UUID.randomUUID());
        AccountDesignation otherAccountDesignation = new AccountDesignation("Other Account");
        MoneyValue otherInitialAmount = new MoneyValue(BigDecimal.valueOf(521.25));
        Account otherCashAccount = AccountFactory.createCashAccount(otherAccountID, otherAccountDesignation, otherInitialAmount);
        otherPerson.addAccountID(otherAccountID);
        this.accountRepository.save(otherCashAccount);
        this.personRepository.save(otherPerson);

        //create category
        Category category = CategoryFactory.create(new CategoryDesignation("food"), new CategoryID(UUID.randomUUID().toString()),
                new CategoryID(UUID.randomUUID().toString()));

        categoryRepository.save(category);

        //create transfer
        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(email.toString())
                .receiverId(otherEmail.toString())
                .originAccountId(accountID.toString())
                .destinationAccountId(otherAccountID.toString())
                .amount(25.26)
                .description("pay tomatoes")
                .categoryId(category.getID().toString())
                .date("2020-01-01")
                .build();

        transactionService.transferBetweenCashAccounts(transferDTO);

        //expected list
        List<LedgerMovementOutDTO> expected = new ArrayList<>();
        LedgerMovementOutDTO expectedPayment = new LedgerMovementOutDTO();
        expectedPayment.setDate("2020-01-01");
        expectedPayment.setMovementType("DEBIT_TRANSFER");
        expectedPayment.setAmount(25.26);
        expectedPayment.setFamilyMember("Bart Simpson");
        expectedPayment.setSenderAccount("Account");
        expectedPayment.setReceiverAccount("Other Account");
        expectedPayment.setDescription("Pay Tomatoes");
        expectedPayment.setCategory("Food");
        expectedPayment.setBalanceToThisDate(30.94);
        expected.add(expectedPayment);

        //act
        ResponseEntity<Object> result = controller.getListOfPersonLedgerMovements(email.toString());
        HttpStatus status = result.getStatusCode();
        Object resultList = result.getBody();
        //assert
        assertEquals(HttpStatus.OK, status);
        assertEquals(expected, resultList);
    }

    @Test
    @DisplayName("Get list of ledger movements: with credit transfer")
    void getLedgerMovements_WithCreditTransfer() throws Exception {
        //arrange
        //create family
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email email = new Email("homer_simpson@gmail.com");
        FamilyName familyName = new FamilyName("Simpson");
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, new LedgerID(UUID.randomUUID()), email, familyName);
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //create member and ledger
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        Address address = new Address("Springfield", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1961-01-05");
        PersonName name = new PersonName("Homer Simpson");
        VAT vat = new VAT("245014314");
        PersonVoDTO voPersonDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, email, familyID,
                ledgerID);
        PersonAndLedgerDomainDTO personAndLedger = PersonAndLedgerFactory.create(voPersonDTO);
        Person person = personAndLedger.getPerson();
        personRepository.save(person);
        ledgerRepository.save(personAndLedger.getLedger());

        //create other member and ledger
        Email otherEmail = new Email("bart@gmail.com");
        PersonName otherName = new PersonName("Bart Simpson");
        VAT otherVat = new VAT("123456789");
        PersonVoDTO otherVoPersonDTO = new PersonVoDTO(otherName, birthDate, otherVat, address, telephoneNumberList, otherEmail, familyID,
                new LedgerID(UUID.randomUUID()));
        PersonAndLedgerDomainDTO otherPersonAndLedger = PersonAndLedgerFactory.create(otherVoPersonDTO);
        Person otherPerson = otherPersonAndLedger.getPerson();
        personRepository.save(otherPerson);
        ledgerRepository.save(otherPersonAndLedger.getLedger());

        //create account
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        MoneyValue initialAmount = new MoneyValue(BigDecimal.valueOf(56.20));
        Account cashAccount = AccountFactory.createCashAccount(accountID, accountDesignation, initialAmount);
        person.addAccountID(accountID);
        this.accountRepository.save(cashAccount);
        this.personRepository.save(person);

        //create other account
        AccountID otherAccountID = new AccountID(UUID.randomUUID());
        AccountDesignation otherAccountDesignation = new AccountDesignation("Other Account");
        MoneyValue otherInitialAmount = new MoneyValue(BigDecimal.valueOf(521.25));
        Account otherCashAccount = AccountFactory.createCashAccount(otherAccountID, otherAccountDesignation, otherInitialAmount);
        otherPerson.addAccountID(otherAccountID);
        this.accountRepository.save(otherCashAccount);
        this.personRepository.save(otherPerson);

        //create category
        Category category = CategoryFactory.create(new CategoryDesignation("food"), new CategoryID(UUID.randomUUID().toString()),
                new CategoryID(UUID.randomUUID().toString()));

        categoryRepository.save(category);

        //create transfer
        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(otherEmail.toString())
                .receiverId(email.toString())
                .originAccountId(otherAccountID.toString())
                .destinationAccountId(accountID.toString())
                .amount(25.26)
                .description("pay tomatoes")
                .categoryId(category.getID().toString())
                .date("2020-01-01")
                .build();

        transactionService.transferBetweenCashAccounts(transferDTO);

        //expected list
        List<LedgerMovementOutDTO> expected = new ArrayList<>();
        LedgerMovementOutDTO expectedPayment = new LedgerMovementOutDTO();
        expectedPayment.setDate("2020-01-01");
        expectedPayment.setMovementType("CREDIT_TRANSFER");
        expectedPayment.setAmount(25.26);
        expectedPayment.setFamilyMember("Bart Simpson");
        expectedPayment.setSenderAccount("Other Account");
        expectedPayment.setReceiverAccount("Account");
        expectedPayment.setDescription("Pay Tomatoes");
        expectedPayment.setCategory("Food");
        expectedPayment.setBalanceToThisDate(81.46);
        expected.add(expectedPayment);

        //act
        ResponseEntity<Object> result = controller.getListOfPersonLedgerMovements(email.toString());
        HttpStatus status = result.getStatusCode();
        Object resultList = result.getBody();
        //assert
        assertEquals(HttpStatus.OK, status);
        assertEquals(expected, resultList);
    }
}