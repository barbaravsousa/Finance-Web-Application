package switchtwentytwenty.project.interfaceadaptor.implcontroller.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.Application;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.applicationservice.irepository.IAccountRepository;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.account.*;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.*;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.account.ICheckChildCashAccountBalanceController;
import java.math.BigDecimal;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Application.class)
class CheckChildCashAccountBalanceControllerIT {

    @Autowired
    IAccountRepository accountRepository;
    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    IPersonRepository personRepository;
    @Autowired
    IFamilyAndMemberService familyAndMemberService;
    @Autowired
    ICheckChildCashAccountBalanceController checkChildCashAccountBalanceController;

    @Test
    void checkChildCashAccountBalance() throws Exception{
        //arrange
        double initialAmount = 50;
        String designation = "Cash";

        //Create family
        String adminID = "martymcfly@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID,new LedgerID(UUID.randomUUID()),new Email(adminID),new FamilyName("Costa"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add Parent to the repository
        TelephoneNumberList parentTelephoneNumberList = new TelephoneNumberList();
        parentTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email parentID = new Email(adminID);
        Address address = new Address("Hill Valley", "25", "4125-886", "Porto", "Portugal");
        BirthDate parentBirthDate = new BirthDate("1968-01-22");
        PersonName parentName = new PersonName("Marty McFly");
        VAT parentVat = new VAT("123456789");
        PersonVoDTO personVoDTO = new PersonVoDTO(parentName, parentBirthDate, parentVat, address, parentTelephoneNumberList, parentID, familyID, new LedgerID(UUID.randomUUID()));
        Person parent = PersonFactory.create(personVoDTO);
        personRepository.save(parent);

        //Add Child to the repository
        TelephoneNumberList childTelephoneNumberList = new TelephoneNumberList();
        childTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email childID = new Email("george@hotmail.com");
        BirthDate childBirthDate = new BirthDate("1968-01-22");
        PersonName childName = new PersonName("Marty McFly");
        VAT childVat = new VAT("123456789");

        PersonVoDTO childDTO = new PersonVoDTO(childName, childBirthDate, childVat, address, childTelephoneNumberList, childID, familyID, new LedgerID(UUID.randomUUID()));
        Person child = PersonFactory.create(childDTO);
        personRepository.save(child);

        //Create Child's cash account
        MoneyValue initialAmountValue = new MoneyValue(new BigDecimal(initialAmount));
        AccountDesignation initialAccountDesignation = new AccountDesignation(designation);
        AccountID childAccountID = new AccountID(UUID.randomUUID());
        Account childCashAccount = AccountFactory.createCashAccount(childAccountID, initialAccountDesignation,initialAmountValue);
        child.addAccountID(childAccountID);
        accountRepository.save(childCashAccount);

        //Create Parent-Child Relationship
        String parentDenomination = Constants.PARENT;
        familyAndMemberService.createFamilyRelation(parentID.toString(), childID.toString(), familyID.toString(), parentDenomination);

        //act
        ResponseEntity<Object> result = checkChildCashAccountBalanceController.checkChildCashAccountBalance(parentID.toString(),childID.toString(),childAccountID.toString());
        int expected = 200;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    void checkChildCashAccountBalance_PersonIsNotTheParent() throws Exception{
        //arrange
        double initialAmount = 50;
        String designation = "Cash";

        //Create family
        String adminID = "martymcfly@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(familyUUID),new LedgerID(UUID.randomUUID()),new Email(adminID),new FamilyName("Costa"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add Parent to the repository
        TelephoneNumberList cousinTelephoneNumberList = new TelephoneNumberList();
        cousinTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email cousinID = new Email(adminID);
        Address address = new Address("Hill Valley", "25", "4125-886", "Porto", "Portugal");
        BirthDate cousinBirthDate = new BirthDate("1968-01-22");
        PersonName cousinName = new PersonName("Marty McFly");
        VAT cousinVat = new VAT("123456789");

        PersonVoDTO cousinDTO = new PersonVoDTO(cousinName, cousinBirthDate, cousinVat, address, cousinTelephoneNumberList, cousinID, familyID, new LedgerID(UUID.randomUUID()));
        Person cousin = PersonFactory.create(cousinDTO);
        personRepository.save(cousin);

        //Add Child to the repository
        TelephoneNumberList childTelephoneNumberList = new TelephoneNumberList();
        childTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email childID = new Email("george@hotmail.com");
        BirthDate childBirthDate = new BirthDate("1968-01-22");
        PersonName childName = new PersonName("Marty McFly");
        VAT childVat = new VAT("123456789");

        PersonVoDTO childDTO = new PersonVoDTO(childName, childBirthDate, childVat, address, childTelephoneNumberList, childID, familyID, new LedgerID(UUID.randomUUID()));
        Person child = PersonFactory.create(childDTO);
        personRepository.save(child);

        //Create Child's cash account
        MoneyValue initialAmountValue = new MoneyValue(new BigDecimal(initialAmount));
        AccountDesignation initialAccountDesignation = new AccountDesignation(designation);
        AccountID childAccountID = new AccountID(UUID.randomUUID());
        Account childCashAccount =  AccountFactory.createCashAccount(childAccountID, initialAccountDesignation,initialAmountValue);
        child.addAccountID(childAccountID);
        accountRepository.save(childCashAccount);

        //Create Parent-Child Relationship
        String cousinDenomination = Constants.COUSIN;
        familyAndMemberService.createFamilyRelation(cousinID.toString(), childID.toString(), familyID.toString(), cousinDenomination);


        //act
        ResponseEntity<Object> result = checkChildCashAccountBalanceController.checkChildCashAccountBalance(cousinID.toString(),childID.toString(),childAccountID.toString());
        int expected = 400;

        //assert
        assertEquals(expected, result.getStatusCodeValue());

    }

    @Test
    void checkChildCashAccountBalance_WrongAccount() throws Exception{
        //arrange
        double initialAmount = 50;
        String designation = "Cash";

        //Create family
        String adminID = "martymcfly@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(familyUUID),new LedgerID(UUID.randomUUID()),new Email(adminID),new FamilyName("Costa"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add Parent to the repository
        TelephoneNumberList parentTelephoneNumberList = new TelephoneNumberList();
        parentTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email parentID = new Email(adminID);
        Address address = new Address("Hill Valley", "25", "4125-886", "Porto", "Portugal");
        BirthDate parentBirthDate = new BirthDate("1968-01-22");
        PersonName parentName = new PersonName("Marty McFly");
        VAT parentVat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(parentName, parentBirthDate, parentVat, address, parentTelephoneNumberList, parentID, familyID, new LedgerID(UUID.randomUUID()));
        Person parent = PersonFactory.create(personVoDTO);
        personRepository.save(parent);

        //Add Child to the repository
        TelephoneNumberList childTelephoneNumberList = new TelephoneNumberList();
        childTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email childID = new Email("george@hotmail.com");
        BirthDate childBirthDate = new BirthDate("1968-01-22");
        PersonName childName = new PersonName("Marty McFly");
        VAT childVat = new VAT("123456789");

        PersonVoDTO childDTO = new PersonVoDTO(childName, childBirthDate, childVat, address, childTelephoneNumberList, childID, familyID, new LedgerID(UUID.randomUUID()));
        Person child = PersonFactory.create(childDTO);
        personRepository.save(child);

        //Create Child's cash account
        MoneyValue initialAmountValue = new MoneyValue(new BigDecimal(initialAmount));
        AccountDesignation initialAccountDesignation = new AccountDesignation(designation);
        AccountID childAccountID = new AccountID(UUID.randomUUID());
        Account childCashAccount =  AccountFactory.createCashAccount(childAccountID, initialAccountDesignation,initialAmountValue);
        child.addAccountID(childAccountID);
        accountRepository.save(childCashAccount);

        //Create Child's Credit Account
        AccountDesignation initialCreditAccountDesignation = new AccountDesignation(designation);
        AccountID childCreditAccountID = new AccountID(UUID.randomUUID());
        Account childCreditAccount = AccountFactory.createBankAccount(childCreditAccountID, initialCreditAccountDesignation, Constants.CREDIT_ACCOUNT_TYPE);
        child.addAccountID(childCreditAccountID);
        accountRepository.save(childCreditAccount);

        //Create Parent-Child Relationship
        String parentDenomination = Constants.PARENT;
        familyAndMemberService.createFamilyRelation(parentID.toString(), childID.toString(), familyID.toString(), parentDenomination);


        //act
        ResponseEntity<Object> result = checkChildCashAccountBalanceController.checkChildCashAccountBalance(parentID.toString(),childID.toString(),childCreditAccountID.toString());
        int expected = 200;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }
}