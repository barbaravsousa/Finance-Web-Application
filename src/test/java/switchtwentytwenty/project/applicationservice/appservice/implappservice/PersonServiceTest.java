package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.list.PersonList;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.outdto.PersonOutDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {


    @InjectMocks
    PersonService personService;

    @Mock
    IPersonRepository personRepository;

    @Test
    @DisplayName("Add Email - successful case")
    void addEmailSuccessfully() throws Exception {
        //arrange
        String emailToAdd = "tiagoA@gmail.com";
        String personId = "tiago@gmail.com";
        Person person = Mockito.mock(Person.class);
        Email email = Mockito.mock(Email.class);
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        doNothing().when(person).addEmail(Mockito.any(Email.class));
        doNothing().when(personRepository).save(Mockito.any(Person.class));
        //act
        boolean result = personService.addEmailToProfile(personId, emailToAdd);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Add Email - Unsuccessful case")
    void addEmailUnSuccessfully() throws Exception {
        //arrange
        String emailToAdd = "tiago@gmail.com";
        String personId = "tiago@gmail.com";
        Person person = Mockito.mock(Person.class);
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        doThrow(IllegalArgumentException.class).when(person).addEmail(Mockito.any(Email.class));
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> personService.addEmailToProfile(personId, emailToAdd));
    }

    @Test
    @DisplayName("delete Email - successful case")
    void deleteEmailSuccessfully() throws Exception {
        //arrange
        String emailToAdd = "tiagoA@gmail.com";
        String personId = "tiago@gmail.com";
        Person person = Mockito.mock(Person.class);
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        when(person.removeEmail(Mockito.any(Email.class))).thenReturn(true);
        doNothing().when(personRepository).save(Mockito.any(Person.class));
        //act
        boolean result = personService.deleteEmailFromProfile(personId, emailToAdd);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("delete Email - Unsuccessful case")
    void deleteAnEmailThatDoesNotExists() throws Exception {
        //arrange
        String emailToAdd = "tiagoA@gmail.com";
        String personId = "tiago@gmail.com";
        Person person = Mockito.mock(Person.class);
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        doThrow(IllegalArgumentException.class).when(person).removeEmail(Mockito.any(Email.class));
        //act e assert
        assertThrows(IllegalArgumentException.class, () -> personService.deleteEmailFromProfile(personId, emailToAdd));

    }







    @Test
    @DisplayName("Get List Of Family Members: empty")
    void getListOfFamilyMembers_Empty() throws Exception {
        //arrange
        String familyID = UUID.randomUUID().toString();
        when(personRepository.findByFamilyID(Mockito.any(FamilyID.class))).thenReturn(new PersonList());
        //act
        List<PersonOutDTO> result = personService.getListOfFamilyMembers(familyID);
        //assert
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    @DisplayName("Get List Of Family Members: one element")
    void getListOfFamilyMembers_OneElement() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email email = new Email("email@gmail.com");
        PersonName personName = new PersonName("Joaquina");
        BirthDate birthDate = new BirthDate("2020-03-02");
        VAT vat = new VAT("123456789");
        Address address = new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal");
        List<String> list = new ArrayList<>();
        list.add("225498653");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList(list);
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        PersonVoDTO personVoDTO = new PersonVoDTO(personName, birthDate, vat, address, telephoneNumberList, email, familyID, ledgerID);
        Person person = PersonFactory.create(personVoDTO);

        PersonList personList = new PersonList();
        personList.add(person);

        List<PersonOutDTO> expected = new ArrayList<>();
        PersonOutDTO personOutDTO = new PersonOutDTO(person.getName().toString(), person.getMainEmail().toString(), person.getFamilyID().toString());
        expected.add(personOutDTO);

        when(personRepository.findByFamilyID(Mockito.any(FamilyID.class))).thenReturn(personList);
        //act
        List<PersonOutDTO> result = personService.getListOfFamilyMembers(familyID.toString());
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get List Of Family Members: several elements")
    void getListOfFamilyMembers_SeveralElements() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email email = new Email("email@gmail.com");
        Email otherEmail = new Email("other@gmail.com");
        PersonName personName = new PersonName("Joaquina");
        PersonName otherPersonName = new PersonName("Ricardina");
        BirthDate birthDate = new BirthDate("2020-03-02");
        VAT vat = new VAT("123456789");
        Address address = new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal");
        List<String> list = new ArrayList<>();
        list.add("225498653");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList(list);
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        PersonVoDTO personVoDTO = new PersonVoDTO(personName, birthDate, vat, address, telephoneNumberList, email, familyID, ledgerID);
        PersonVoDTO otherPersonVoDTO = new PersonVoDTO(otherPersonName, birthDate, vat, address, telephoneNumberList, otherEmail, familyID, ledgerID);
        Person person = PersonFactory.create(personVoDTO);
        Person otherPerson = PersonFactory.create(otherPersonVoDTO);

        PersonList personList = new PersonList();
        personList.add(person);
        personList.add(otherPerson);

        List<PersonOutDTO> expected = new ArrayList<>();
        PersonOutDTO personOutDTO = new PersonOutDTO(person.getName().toString(), person.getMainEmail().toString(), person.getFamilyID().toString());
        PersonOutDTO otherPersonOutDTO = new PersonOutDTO(otherPerson.getName().toString(), otherPerson.getMainEmail().toString(),
                otherPerson.getFamilyID().toString());
        expected.add(personOutDTO);
        expected.add(otherPersonOutDTO);

        when(personRepository.findByFamilyID(Mockito.any(FamilyID.class))).thenReturn(personList);
        //act
        List<PersonOutDTO> result = personService.getListOfFamilyMembers(familyID.toString());
        //assert
        assertEquals(expected, result);
    }

}
