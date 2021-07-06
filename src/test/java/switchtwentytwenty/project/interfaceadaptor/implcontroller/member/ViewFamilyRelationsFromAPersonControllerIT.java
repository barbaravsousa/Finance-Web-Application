package switchtwentytwenty.project.interfaceadaptor.implcontroller.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.familydata.RelationType;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.*;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.member.IViewFamilyRelationsFromAPerson;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
class ViewFamilyRelationsFromAPersonControllerIT {

    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    IPersonRepository personRepository;
    @Autowired
    IFamilyAndMemberService familyAndMemberService;
    @Autowired
    IViewFamilyRelationsFromAPerson viewFamilyRelationsFromAPerson;

    @BeforeEach
    public void before(){
        personRepository.deleteAll();
    }

    @Test
    @DisplayName("Get an empty family relation list from a person if the person has no relations")
    void getAnEmptyFamilyRelationListFromAPerson() throws Exception {
        //arrange
        FamilyName familyNameSimpson = new FamilyName("Simpson");
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("220058942"));
        Email adminEmailSimpson = new Email("homer_simpson@gmail.com");
        Address address = new Address("Springfield", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1961-01-05");
        PersonName name = new PersonName("Homer Simpson");
        VAT vat = new VAT("245014314");

        PersonVoDTO personVoDTO = new PersonVoDTO(name,birthDate,vat,address,telephoneNumberList,adminEmailSimpson,familyID,new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(personVoDTO);
        personRepository.save(person);

        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID,new LedgerID(UUID.randomUUID()),adminEmailSimpson,familyNameSimpson);
        Family family = FamilyFactory.create(familyDTO);

        familyRepository.save(family);

        //act
        ResponseEntity<Object> result = viewFamilyRelationsFromAPerson.getFamilyRelationByPersonID(person.getID().toString());
        int expected = 200;

        // assert
        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Get family relation list from a person")
    void getFamilyRelationListFromAPerson() throws Exception{
        //arrange
        FamilyName familyNameMcFly = new FamilyName("McFly");
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        //Create admin and add to person repository
        TelephoneNumberList martyNumberList = new TelephoneNumberList();
        martyNumberList.add(new TelephoneNumber("225658541"));
        Email adminEmailMcFly = new Email("marty_mcfly3@gmail.com");
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate martyBirthDate = new BirthDate("1995-01-22");
        PersonName martyName = new PersonName("Marty McFly");
        VAT martyVat = new VAT("245014314");

        PersonVoDTO personVoDTO = new PersonVoDTO(martyName,martyBirthDate,martyVat,address,martyNumberList,adminEmailMcFly,familyID,new LedgerID(UUID.randomUUID()));
        Person marty = PersonFactory.create(personVoDTO);
        personRepository.save(marty);

        //Create family and add to family repository
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID,new LedgerID(UUID.randomUUID()),adminEmailMcFly,familyNameMcFly);
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add new family members, George and Loraine
        TelephoneNumberList georgeNumberList = new TelephoneNumberList();
        martyNumberList.add(new TelephoneNumber("225658441"));
        Email georgeID = new Email("george_mcfly4@sapo.pt");
        BirthDate georgeBirthDate = new BirthDate("1995-01-22");
        PersonName georgeName = new PersonName("George McFly");
        VAT georgeVat = new VAT("299259080");

        PersonVoDTO georgeDTO = new PersonVoDTO(georgeName,georgeBirthDate,georgeVat,address,georgeNumberList,georgeID,familyID,new LedgerID(UUID.randomUUID()));
        Person george = PersonFactory.create(georgeDTO);
        personRepository.save(george);

        TelephoneNumberList loraineNumberList = new TelephoneNumberList();
        martyNumberList.add(new TelephoneNumber("225658541"));
        Email loraineID = new Email("loraine_mcfly@sapo.pt");
        BirthDate loraineBirthDate = new BirthDate("1995-01-22");
        PersonName loraineName = new PersonName("Loraine McFly");
        VAT loraineVat = new VAT("251767574");

        PersonVoDTO loraineDTO = new PersonVoDTO(loraineName,loraineBirthDate,loraineVat,address,loraineNumberList,loraineID,familyID,new LedgerID(UUID.randomUUID()));
        Person loraine = PersonFactory.create(loraineDTO);
        personRepository.save(loraine);

        RelationType relationMartyGeorge = RelationType.getInstance(Constants.CHILD);
        RelationType relationMartyLoraine = RelationType.getInstance(Constants.CHILD);
        RelationType relationGeorgeLoraine = RelationType.getInstance(Constants.CHILD);

        family.createFamilyRelation(adminEmailMcFly, georgeID, relationMartyGeorge);
        family.createFamilyRelation(loraineID, adminEmailMcFly, relationMartyLoraine);
        family.createFamilyRelation(georgeID, loraineID, relationGeorgeLoraine);
        familyRepository.save(family);

        //act
        ResponseEntity<Object> result = viewFamilyRelationsFromAPerson.getFamilyRelationByPersonID("marty_mcfly3@gmail.com");

        // assert
        assertEquals(200, result.getStatusCodeValue());
    }
}
