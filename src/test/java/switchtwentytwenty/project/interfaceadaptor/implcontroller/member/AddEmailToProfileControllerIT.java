package switchtwentytwenty.project.interfaceadaptor.implcontroller.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IPersonService;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.*;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.outdto.AddEmailOutDTO;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.dto.indto.AddEmailInDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.member.IAddEmailToProfileController;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddEmailToProfileControllerIT {

    @Autowired
    IAddEmailToProfileController controller;
    @Autowired
    IPersonService personService;
    @Autowired
    IPersonRepository personRepository;
    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    IFamilyAndMemberService familyAndMemberService;

    @BeforeEach
    public void before(){
        personRepository.deleteAll();
    }

    @Test
    void addEmailToProfile() throws Exception {

        //arrange
        String personId = "bones@gmail.com";
        String emailToAdd = "bonesTwo@gmail.com";

        AddEmailInDTO info = new AddEmailInDTO(emailToAdd);

        //Create family
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, new Email(personId), new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add Marty to the repository
        LedgerID martyLedgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumberList martyTelephoneNumberList = new TelephoneNumberList();
        martyTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email martyID = new Email(personId);
        Address address = new Address("Hill Valley", "25", "4125-886", "Porto", "Portugal");
        BirthDate martyBirthDate = new BirthDate("1968-01-22");
        PersonName martyName = new PersonName("Marty McFly");
        VAT martyVat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(martyName, martyBirthDate, martyVat, address, martyTelephoneNumberList, martyID, familyID, martyLedgerID);
        Person marty = PersonFactory.create(personVoDTO);
        personRepository.save(marty);

        AddEmailOutDTO expectedAddEmailOutDTO = new AddEmailOutDTO(marty.getID().toString());
        ResponseEntity expected = new ResponseEntity(expectedAddEmailOutDTO,HttpStatus.OK);

        //act
        ResponseEntity<Object> result = controller.addEmailToProfile(personId, info);
        personRepository.save(marty);

        //assert
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(expected,result);
    }

    @Test
    void addNoneEmailToProfile() throws Exception {
        // arrange
        String personId = "bones@gmail.com";
        String emailToAdd = " ";
        AddEmailInDTO info = new AddEmailInDTO(emailToAdd);


        //Create family
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, new Email(personId), new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add Marty to the repository
        LedgerID martyLedgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumberList martyTelephoneNumberList = new TelephoneNumberList();
        martyTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email martyID = new Email(personId);
        Address address = new Address("Hill Valley", "25", "4125-886", "Porto", "Portugal");
        BirthDate martyBirthDate = new BirthDate("1968-01-22");
        PersonName martyName = new PersonName("Marty McFly");
        VAT martyVat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(martyName, martyBirthDate, martyVat, address, martyTelephoneNumberList, martyID, familyID, martyLedgerID);
        Person marty = PersonFactory.create(personVoDTO);
        personRepository.save(marty);


        //act
        ResponseEntity<Object> result = controller.addEmailToProfile(personId, info);

        //assert
        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    void addInvalidEmailToProfile() throws Exception{

        //arrange
        String personId = "bones@gmail.com";
        String invalidEmailToAdd = "bonesTwogmail.com";
        AddEmailInDTO info = new AddEmailInDTO(invalidEmailToAdd);

        //Create family
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, new Email(personId), new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add Marty to the repository
        LedgerID martyLedgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumberList martyTelephoneNumberList = new TelephoneNumberList();
        martyTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email martyID = new Email(personId);
        Address address = new Address("Hill Valley", "25", "4125-886", "Porto", "Portugal");
        BirthDate martyBirthDate = new BirthDate("1968-01-22");
        PersonName martyName = new PersonName("Marty McFly");
        VAT martyVat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(martyName, martyBirthDate, martyVat, address, martyTelephoneNumberList, martyID, familyID, martyLedgerID);
        Person marty = PersonFactory.create(personVoDTO);
        personRepository.save(marty);


        //act
        ResponseEntity<Object> result = controller.addEmailToProfile(personId, info);

        //assert
        assertEquals(400, result.getStatusCodeValue());
    }


    @Test
    void addEmailToInvalidProfile() throws Exception{

        //arrange
        String personId = null;
        String invalidEmailToAdd = "bonesTwogmail.com";

        AddEmailInDTO info = new AddEmailInDTO(invalidEmailToAdd);

        //act
        ResponseEntity<Object> result = controller.addEmailToProfile(personId, info);

        //assert
        assertEquals(400, result.getStatusCodeValue());

    }

    @Test
    void addRepeatedEmailToProfile()throws Exception {

        //arrange
        String personId = "bonesTwo@gmail.com";
        String invalidEmailToAdd = "bonesTwo@gmail.com";
        AddEmailInDTO info = new AddEmailInDTO(invalidEmailToAdd);


        //Create family
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, new Email(personId), new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add Marty to the repository
        LedgerID martyLedgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumberList martyTelephoneNumberList = new TelephoneNumberList();
        martyTelephoneNumberList.add(new TelephoneNumber("225658541"));
        Email martyID = new Email(personId);
        Address address = new Address("Hill Valley", "25", "4125-886", "Porto", "Portugal");
        BirthDate martyBirthDate = new BirthDate("1968-01-22");
        PersonName martyName = new PersonName("Marty McFly");
        VAT martyVat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(martyName, martyBirthDate, martyVat, address, martyTelephoneNumberList, martyID, familyID, martyLedgerID);
        Person marty = PersonFactory.create(personVoDTO);
        personRepository.save(marty);

        //act
        ResponseEntity<Object> result = controller.addEmailToProfile(personId, info);
        //assert
        assertEquals(400, result.getStatusCodeValue());

    }
}


