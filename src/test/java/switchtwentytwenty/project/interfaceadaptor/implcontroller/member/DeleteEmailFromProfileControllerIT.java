package switchtwentytwenty.project.interfaceadaptor.implcontroller.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import switchtwentytwenty.project.dto.indto.DeleteEmailInDto;
import switchtwentytwenty.project.dto.outdto.DeleteEmailOutDTO;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.member.IDeleteEmailFromProfileController;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeleteEmailFromProfileControllerIT {


    @Autowired
    IDeleteEmailFromProfileController deleteEmailController;
    @Autowired
    IPersonService personService;
    @Autowired
    IPersonRepository personRepository;
    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    IFamilyAndMemberService familyAndMemberService;

    @BeforeEach
    public void before() {
        personRepository.deleteAll();
    }

    @Test
    @DisplayName("Delete an email from my profile")
    void deleteEmailFromProfile() throws Exception {

        //arrange
        String personId = "bones@gmail.com";
        String emailToAdd = "bonesTwo@gmail.com";
        Email emailAdded = new Email(emailToAdd);


        DeleteEmailInDto info = new DeleteEmailInDto(emailToAdd);


        //Create family
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, new Email(personId), new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add william to the repository
        LedgerID willianLedgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658541"));
        Email williamID = new Email(personId);
        Address address = new Address("Hill Valley", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1968-01-22");
        PersonName williamName = new PersonName("William");
        VAT martyVat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(williamName, birthDate, martyVat, address, telephoneNumberList, williamID, familyID, willianLedgerID);
        Person willian = PersonFactory.create(personVoDTO);
        personRepository.save(willian);
        willian.addEmail(emailAdded);
        personRepository.save(willian);

        DeleteEmailOutDTO expectedOutAddEmailDTO = new DeleteEmailOutDTO(willian.getID().toString());
        ResponseEntity expected = new ResponseEntity(expectedOutAddEmailDTO, HttpStatus.OK);

        //act
        ResponseEntity<Object> result = deleteEmailController.deleteEmailFromProfile(personId, info);
        personRepository.save(willian);

        //assert
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Delete an email that does not exist on profile")
    void deleteAnEmailThatDoesNotExistOnProfile() throws Exception {

        //arrange
        String personId = "bones@gmail.com";
        String emailToAdd = "bonesTwo@gmail.com";


        DeleteEmailInDto info = new DeleteEmailInDto(emailToAdd);

        //Create family
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, new Email(personId), new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add Marty to the repository
        LedgerID willianLedgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658541"));
        Email willianID = new Email(personId);
        Address address = new Address("Hill Valley", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1968-01-22");
        PersonName willianName = new PersonName("William");
        VAT willianVat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(willianName, birthDate, willianVat, address, telephoneNumberList, willianID, familyID, willianLedgerID);
        Person willian = PersonFactory.create(personVoDTO);
        personRepository.save(willian);

        DeleteEmailOutDTO expectedOutAddEmailDTO = new DeleteEmailOutDTO(willian.getID().toString());
        ResponseEntity expected = new ResponseEntity(expectedOutAddEmailDTO, HttpStatus.OK);


        //act
        ResponseEntity<Object> result = deleteEmailController.deleteEmailFromProfile(personId, info);
        personRepository.save(willian);

        //assert
        assertEquals(400, result.getStatusCodeValue());

    }

    @Test
    @DisplayName("Delete an email from a person that does not exist")
    void deleteAnEmailFromAPersonThatDoesNotExist() throws Exception {

        //arrange
        String personId = "bones@gmail.com";
        String emailToAdd = "bonesTwo@gmail.com";


        DeleteEmailInDto info = new DeleteEmailInDto(emailToAdd);

        //Create family
        LedgerID familyLedgerID = new LedgerID(UUID.randomUUID());
        UUID familyUUID = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyUUID);
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, familyLedgerID, new Email(personId), new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add Marty to the repository
        LedgerID willianLedgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658541"));
        Email willianID = new Email(personId);
        Address address = new Address("Hill Valley", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1968-01-22");
        PersonName martyName = new PersonName("William");
        VAT willianVat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(martyName, birthDate, willianVat, address, telephoneNumberList, willianID, familyID, willianLedgerID);
        Person willian = PersonFactory.create(personVoDTO);

        DeleteEmailOutDTO expectedOutAddEmailDTO = new DeleteEmailOutDTO(willian.getID().toString());

        //act
        ResponseEntity<Object> result = deleteEmailController.deleteEmailFromProfile(personId, info);

        //assert
        assertEquals(400, result.getStatusCodeValue());
    }


}


