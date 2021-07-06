package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IPersonService;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.*;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.outdto.UserProfileOutDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidEmailException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceIT {

    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    IPersonRepository personRepository;
    @Autowired
    IPersonService personService;

    @BeforeEach
    public void before(){
        personRepository.deleteAll();
    }


    @Test
    @DisplayName("Add an Email to my Account: successful case")
    void addEmailToAccount() throws Exception {

        //arrange
        personRepository.deleteAll();
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658541"));
        String personId = "tiago@gmail.com";
        Email personID = new Email(personId);
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-01-22");
        PersonName name = new PersonName("Alan Turing");
        VAT vat = new VAT("276841603");

        PersonVoDTO personVoDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, personID, familyID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(personVoDTO);
        personRepository.save(person);



        String emailToAdd = "tiagoTwo@gmail.com";
        Email firstToAdd = new Email(emailToAdd);
        person.addEmail(firstToAdd);
        personRepository.save(person);

        String secondEmailToAdd = "tiagoThree@gmail.com";
        Email secondToAdd = new Email(secondEmailToAdd);


        //act
        boolean secondResult = personService.addEmailToProfile(personId,secondEmailToAdd);
        Person newPerson = personRepository.findByID(personID);
        boolean emailIsPresent = newPerson.containsEmail(secondToAdd);

        //assert
       assertTrue(secondResult);
        assertTrue(emailIsPresent);

    }

    @Test
    @DisplayName("Add an Email that Already Exists to my Account: Unsuccessful case")
    void addAnExistingEmailToAccount() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658541"));
        String personIDString = "bones@gmail.com";
        Email personID = new Email(personIDString);
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-01-22");
        PersonName name = new PersonName("Alan Turing");
        VAT vat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, personID, familyID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(personVoDTO);
        personRepository.save(person);

        String emailToAdd = "bones@gmail.com";

        //act e assert
        assertThrows(IllegalArgumentException.class, () -> personService.addEmailToProfile(personIDString, emailToAdd));
    }

    @Test
    @DisplayName("Add an Email To an Account That does not exist - Unsuccessful case")
    void addEmailToANonExistingAccount() {
        //arrange
        String myID = "bones@gmail.com";
        String emailToAdd = "bonesThree@gmail.com";
        //act e assert
        assertThrows(ElementNotFoundException.class, () -> personService.addEmailToProfile(myID, emailToAdd));
    }

    @Test
    @DisplayName("Add an Email that Already Exists in the system to my Account: Unsuccessful case")
    void addAnExistingEmailToMyAccount() throws Exception {
        //arrange
        FamilyID family1ID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email personID = new Email("alan_turing@hotmail.com");
        Address address1 = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate1 = new BirthDate("1995-01-22");
        PersonName name1 = new PersonName("Alan Turing");
        VAT vat1 = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(name1, birthDate1, vat1, address1, telephoneNumberList1, personID, family1ID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(personVoDTO);
        personRepository.save(person);

        FamilyID family2ID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList2 = new TelephoneNumberList();
        telephoneNumberList2.add(new TelephoneNumber("225658542"));
        String kinIDString = "john_neumanng@hotmail.com";
        Email kinID = new Email(kinIDString);
        Address address2 = new Address("Rua Velha", "2", "4125-100", "Porto", "Portugal");
        BirthDate birthDate2 = new BirthDate("1915-01-01");
        PersonName name2 = new PersonName("John von Neumann");
        VAT vat2 = new VAT("267222327");

        PersonVoDTO kinDTO = new PersonVoDTO(name2, birthDate2, vat2, address2, telephoneNumberList2, kinID, family2ID,
                new LedgerID(UUID.randomUUID()));
        Person kin = PersonFactory.create(kinDTO);
        personRepository.save(kin);

        String repeatedEmail = "john_neumanng@hotmail.com";

        //act
        assertThrows(IllegalArgumentException.class, () -> personService.addEmailToProfile(kinIDString, repeatedEmail));
    }


    @Test
    @DisplayName("Delete an email from my profile")
    void deleteEmailFromProfile() throws Exception {
        //arrange
        FamilyID family1ID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email personID = new Email("alan_turing@hotmail.com");
        Address address1 = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate1 = new BirthDate("1995-01-22");
        PersonName name1 = new PersonName("Alan Turing");
        VAT vat1 = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(name1, birthDate1, vat1, address1, telephoneNumberList1, personID, family1ID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(personVoDTO);
        personRepository.save(person);

        Email firstEmail = new Email("alanPescaria@gmail.com");
        Email secondEmail = new Email("alan_Rancho@gmail.com");

        //act
        person.addEmail(firstEmail);
        person.addEmail(secondEmail);
        personRepository.save(person);
        boolean result = personService.deleteEmailFromProfile(person.getID().toString(),secondEmail.toString());
        Person newPerson = personRepository.findByID(personID);

        boolean emailIsPresent = newPerson.containsEmail(secondEmail);
        //assert
        assertTrue(result);
        assertFalse(emailIsPresent);
    }


    @Test
    @DisplayName("Delete an invalid email from my profile : Unsuccessful case")
    void deleteAnInvalidEmailFromProfile() throws Exception {
        //arrange
        FamilyID family1ID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email personID = new Email("alan_turing@hotmail.com");
        Address address1 = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate1 = new BirthDate("1995-01-22");
        PersonName name1 = new PersonName("Alan Turing");
        VAT vat1 = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(name1, birthDate1, vat1, address1, telephoneNumberList1, personID, family1ID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(personVoDTO);
        personRepository.save(person);

        //act and assert
        assertThrows(InvalidEmailException.class, () -> personService.deleteEmailFromProfile(person.getID().toString(),"hdasyukfgadsf"));

    }

    @Test
    @DisplayName("Delete an email from a none existent profile : Unsuccessful case")
    void deleteAnEmailFromANoneExistentProfile() throws Exception {
        //arrange
        FamilyID family1ID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email personID = new Email("alan_turing@hotmail.com");
        Address address1 = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate1 = new BirthDate("1995-01-22");
        PersonName name1 = new PersonName("Alan Turing");
        VAT vat1 = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(name1, birthDate1, vat1, address1, telephoneNumberList1, personID, family1ID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(personVoDTO);

        Email firstEmail = new Email("alanPescaria@gmail.com");


        //act and assert
        assertThrows(ElementNotFoundException.class, () -> personService.deleteEmailFromProfile(person.getID().toString(),firstEmail.toString()));

    }

    @Test
    @DisplayName("Delete an email from an invalid person: Unsuccessful case")
    void deleteAnEmailFromAnInvalidProfile() throws Exception {
        //arrange
        FamilyID family1ID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email personID = new Email("alan_turing@hotmail.com");
        Address address1 = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate1 = new BirthDate("1995-01-22");
        PersonName name1 = new PersonName("Alan Turing");
        VAT vat1 = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(name1, birthDate1, vat1, address1, telephoneNumberList1, personID, family1ID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(personVoDTO);

        Email firstEmail = new Email("alanPescaria@gmail.com");


        //act and assert
        assertThrows(InvalidEmailException.class, () -> personService.deleteEmailFromProfile("fjha98sdnui",firstEmail.toString()));

    }


    @Test
    @DisplayName("Delete a none existent email from my profile: Unsuccessful case")
    void deleteAnEmailThatDoesNotExistOnProfile() throws Exception {
        //arrange
        FamilyID family1ID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email personID = new Email("alan_turing@hotmail.com");
        Address address1 = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate1 = new BirthDate("1995-01-22");
        PersonName name1 = new PersonName("Alan Turing");
        VAT vat1 = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(name1, birthDate1, vat1, address1, telephoneNumberList1, personID, family1ID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(personVoDTO);
        personRepository.save(person);

        Email firstEmail = new Email("alanPescaria@gmail.com");
        Email secondEmail = new Email("alan_Rancho@gmail.com");

        //act
        person.addEmail(firstEmail);
        personRepository.save(person);
        assertThrows(IllegalArgumentException.class, () -> personService.deleteEmailFromProfile(person.getID().toString(),secondEmail.toString()));


    }












    @Test
    @DisplayName("Add an invalid Email To an Account- Unsuccessful case")
    void addAnInvalidEmailToAAccount() {
        //arrange
        String myID = "bones@gmail.com";
        String emailToAdd = "bonesTgmail.com";
        //act
        assertThrows(InvalidEmailException.class, () -> personService.addEmailToProfile(myID, emailToAdd));
    }

    @Test
    @DisplayName("Get user profile : no person found")
    void getUserProfileFailure() {
        //arrange
        //act - assert
        assertThrows(Exception.class, () -> personService.getUserProfile("noname@gmail.com"));
    }

    @Test
    @DisplayName("Get User: Successful case")
    void getProfileUser() throws Exception {
        //arrange

        FamilyID familyID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658541"));
        String personId = "bonesF@gmail.com";
        Email personID = new Email(personId);
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-01-22");
        PersonName name = new PersonName("Alan Turing");
        VAT vat = new VAT("276841603");

        PersonVoDTO personVoDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, personID, familyID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(personVoDTO);
        personRepository.save(person);


        List<String> telephoneNumberListSecond = new ArrayList<>();
        telephoneNumberListSecond.add("225658541");
        UserProfileOutDTO dto = new UserProfileOutDTO.UserProfileDTOBuilder()
                .withName("Alan Turing")
                .withVat("276841603")
                .withBirthDate("1995-01-22")
                .withHouseNumber("25")
                .withStreet("Rua Nova")
                .withCity("Porto")
                .withCountry("Portugal")
                .withZipCode("4125-886")
                .withTelephoneNumbers(telephoneNumberListSecond)
                .withMainEmail("bonesF@gmail.com")
                .withOtherEmails(null)
                .build();

        //act
        UserProfileOutDTO result = personService.getUserProfile(personId);

        assertEquals(result, dto);
    }
}

