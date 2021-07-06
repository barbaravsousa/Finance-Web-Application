package switchtwentytwenty.project.interfaceadaptor.implcontroller.transaction;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAuthorizationService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ILedgerIDGenerator;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ILedgerService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ITransactionService;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.LedgerIDGenerator;
import switchtwentytwenty.project.applicationservice.irepository.*;
import switchtwentytwenty.project.autentication.ERole;
import switchtwentytwenty.project.autentication.SignupDTO;
import switchtwentytwenty.project.autentication.UserRepository;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.account.AccountFactory;
import switchtwentytwenty.project.domain.aggregate.account.CashAccount;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.ledger.LedgerFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.domaindto.FamilyAndAdministratorDomainDTO;
import switchtwentytwenty.project.domain.domainservice.FamilyAndAdminFactory;
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
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.dto.toservicedto.PaymentDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.transaction.IGetFamilyLedgerMovementsController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetFamilyLedgerMovementsControllerTest {


    @Autowired
    IGetFamilyLedgerMovementsController getFamilyLedgerMovementsController;
    @Autowired
    ILedgerService ledgerService;
    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    ITransactionService transactionService;
    @Autowired
    ICategoryRepository categoryRepository;
    @Autowired
    IAccountRepository accountRepository;
    @Autowired
    ILedgerRepository ledgerRepository;
    @Autowired
    IPersonRepository personRepository;
    @Autowired
    ILedgerIDGenerator ledgerIDGenerator;

    @BeforeEach
    void init(){

        personRepository.deleteAll();
        familyRepository.deleteAll();
    }


    @Test
    void getFamilyLegderMovementsEmptyList() throws Exception
    {
        //arrange
        //create family
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email email = new Email("homer_simpson@gmail.com");
        FamilyName familyName = new FamilyName("Simpson");
        LedgerID ledgerID= ledgerIDGenerator.generate();
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID,ledgerID, email, familyName);
        Ledger familyLegder = LedgerFactory.create(ledgerID);
        Family family = FamilyFactory.create(familyDTO);

        familyRepository.save(family);
        ledgerRepository.save(familyLegder);

        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("cash");
        MoneyValue moneyValue = new MoneyValue(new BigDecimal("10"));
        Account familyCashAccount = AccountFactory.createCashAccount(accountID,accountDesignation,moneyValue);
        accountRepository.save(familyCashAccount);
        family.addAccountID(accountID);
        familyRepository.save(family);

        //act
        ResponseEntity<Object> result =  getFamilyLedgerMovementsController.getListOfFamilyLedgerMovements(familyID.toString());
        Object resultList = result.getBody();

        //assert
        assertEquals(200,result.getStatusCodeValue());
        assertEquals(Collections.emptyList(), resultList);

    }



    @Test
    void getLegderMovementsFromAFamilyWithOutAnAccount() throws Exception
    {
        //arrange
        //create family
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email email = new Email("homer_simpson@gmail.com");
        FamilyName familyName = new FamilyName("Simpson");
        LedgerID ledgerID= ledgerIDGenerator.generate();
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID,ledgerID, email, familyName);
        Ledger familyLegder = LedgerFactory.create(ledgerID);
        Family family = FamilyFactory.create(familyDTO);

        familyRepository.save(family);
        ledgerRepository.save(familyLegder);


        //act
        ResponseEntity<Object> result =  getFamilyLedgerMovementsController.getListOfFamilyLedgerMovements(familyID.toString());

        //assert
        assertEquals(422,result.getStatusCodeValue());

    }

    @Test
    void getFamilyLegderMovementsFromANonExistentFamily() throws Exception
    {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        //act
        ResponseEntity<Object> result =  getFamilyLedgerMovementsController.getListOfFamilyLedgerMovements(familyID.toString());

        //assert
        assertEquals(422,result.getStatusCodeValue());

    }





}