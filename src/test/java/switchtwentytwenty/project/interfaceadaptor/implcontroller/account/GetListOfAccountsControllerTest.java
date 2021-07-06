package switchtwentytwenty.project.interfaceadaptor.implcontroller.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.applicationservice.irepository.IAccountRepository;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.account.AccountFactory;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.*;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.outdto.PersonalCashAccountOutDTO;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTO;
import switchtwentytwenty.project.dto.outdto.AccountOutDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.account.IGetListOfAccountsController;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetListOfAccountsControllerTest {

    @Autowired
    IGetListOfAccountsController controller;

    @Autowired
    IPersonRepository personRepository;

    @Autowired
    IFamilyRepository familyRepository;

    @Autowired
    IAccountRepository accountRepository;

    @Autowired
    IFamilyAndMemberService familyAndMemberService;

    @Autowired
    IAccountService accountService;


    @BeforeEach
    public void before() {
        personRepository.deleteAll();

    }


    @Test
    void getListOfAccountsController() throws Exception {
        //arrange
        double initialAmount = 50;
        String designation = "Cash";

        String michaelId = "michael@gmail.com";
        UUID familyID = UUID.randomUUID();
        double cashAmount = 102;

        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email(michaelId),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        TelephoneNumberList secondTelephoneNumberList = new TelephoneNumberList();
        secondTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email michaelID = new Email(michaelId);
        BirthDate michaelBirthDate = new BirthDate("1996-01-22");
        PersonName michaelName = new PersonName("Michael Turing");
        VAT vat1 = new VAT("232098018");

        PersonVoDTO secondVoPersonDTO = new PersonVoDTO(michaelName,michaelBirthDate,vat1,address,secondTelephoneNumberList,michaelID,family.getID(),new LedgerID(UUID.randomUUID()));
        Person michael= PersonFactory.create(secondVoPersonDTO);
        personRepository.save(michael);

        MoneyValue initialAmountValue = new MoneyValue(new BigDecimal(initialAmount));
        AccountDesignation initialAccountDesignation = new AccountDesignation(designation);
        AccountID accountID = new AccountID(UUID.randomUUID());
        Account account = AccountFactory.createCashAccount(accountID, initialAccountDesignation, initialAmountValue);
        michael.addAccountID(accountID);
        personRepository.save(michael);
        accountRepository.save(account);



        PersonalCashAccountOutDTO firstAccount = new PersonalCashAccountOutDTO(account.getID().toString(), account.getDesignation().toString(),account.getBalance().toString());
        List<PersonalCashAccountOutDTO> expectedList = new ArrayList();
        expectedList.add(firstAccount);

        ResponseEntity expected = new ResponseEntity(expectedList, HttpStatus.OK);

        //act
        ResponseEntity<Object> result = controller.getListOfAccountsController(michael.getID().toString());

        //assert
        assertEquals(expected, result);
    }


    @Test
    void getListOfAccountsFromANonExistingPerson() throws Exception {
        //arrange
        double initialAmount = 50;
        String designation = "Cash";

        String personId = "bones@gmail.com";

        //act
        ResponseEntity<Object> result = controller.getListOfAccountsController(personId);
        int expected = 422;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }


    @Test
    void getListOfAccountsThatDontExist() throws Exception {
        //arrange
        //arrange
        double initialAmount = 50;
        String designation = "Cash";

        String michaelId = "michael@gmail.com";
        UUID familyID = UUID.randomUUID();
        double cashAmount = 102;

        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email(michaelId),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        TelephoneNumberList secondTelephoneNumberList = new TelephoneNumberList();
        secondTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email michaelID = new Email(michaelId);
        BirthDate michaelBirthDate = new BirthDate("1996-01-22");
        PersonName michaelName = new PersonName("Michael Turing");
        VAT vat1 = new VAT("232098018");

        PersonVoDTO secondVoPersonDTO = new PersonVoDTO(michaelName,michaelBirthDate,vat1,address,secondTelephoneNumberList,michaelID,family.getID(),new LedgerID(UUID.randomUUID()));
        Person michael= PersonFactory.create(secondVoPersonDTO);
        personRepository.save(michael);

        MoneyValue initialAmountValue = new MoneyValue(new BigDecimal(initialAmount));
        AccountDesignation initialAccountDesignation = new AccountDesignation(designation);
        AccountID accountID = new AccountID(UUID.randomUUID());
        Account account = AccountFactory.createCashAccount(accountID, initialAccountDesignation, initialAmountValue);
        michael.addAccountID(accountID);
        personRepository.save(michael);

        int expected = 422;
        //act
        ResponseEntity<Object> result = controller.getListOfAccountsController(michael.getID().toString());

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }




    @Test
    void getListOfAPersonThatDoesNotHaveAccountsController() throws Exception {
        //arrange
        double initialAmount = 50;
        String designation = "Cash";

        String michaelId = "michael@gmail.com";
        UUID familyID = UUID.randomUUID();
        double cashAmount = 102;

        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email(michaelId),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        TelephoneNumberList secondTelephoneNumberList = new TelephoneNumberList();
        secondTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email michaelID = new Email(michaelId);
        BirthDate michaelBirthDate = new BirthDate("1996-01-22");
        PersonName michaelName = new PersonName("Michael Turing");
        VAT vat1 = new VAT("232098018");

        PersonVoDTO secondVoPersonDTO = new PersonVoDTO(michaelName,michaelBirthDate,vat1,address,secondTelephoneNumberList,michaelID,family.getID(),new LedgerID(UUID.randomUUID()));
        Person michael= PersonFactory.create(secondVoPersonDTO);
        personRepository.save(michael);

        MoneyValue initialAmountValue = new MoneyValue(new BigDecimal(initialAmount));
        AccountDesignation initialAccountDesignation = new AccountDesignation(designation);
        AccountID accountID = new AccountID(UUID.randomUUID());
        Account account = AccountFactory.createCashAccount(accountID, initialAccountDesignation, initialAmountValue);


        accountRepository.save(account);



        PersonalCashAccountOutDTO firstAccount = new PersonalCashAccountOutDTO(account.getID().toString(), account.getDesignation().toString(),account.getBalance().toString());
        List<PersonalCashAccountOutDTO> expectedList = new ArrayList();


        ResponseEntity expected = new ResponseEntity(expectedList, HttpStatus.OK);

        //act
        ResponseEntity<Object> result = controller.getListOfAccountsController(michael.getID().toString());

        //assert
        assertEquals(expected, result);
    }

}