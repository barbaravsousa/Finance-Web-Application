package switchtwentytwenty.project.domain.share.list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.*;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.exception.InvalidDateException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidPersonNameException;
import switchtwentytwenty.project.exception.InvalidVATException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonListTest {

    @Test
    @DisplayName("Get size of person list")
    void getSizeOfPersonList() throws InvalidDateException, InvalidPersonNameException, InvalidVATException, InvalidEmailException {
        //arrange
        int result;
        int expected = 2;

        PersonList personList = new PersonList();

        FamilyID familyID = new FamilyID(UUID.randomUUID());
        TelephoneNumber telephones = new TelephoneNumber("225658541");
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-03-22");
        BirthDate otherBirthDate = new BirthDate("1988-01-08");
        Email id = new Email("albertina@gmail.com");
        PersonName name = new PersonName("Albertina");
        VAT vat = new VAT("123456789");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(telephones);
        PersonVoDTO albertinaDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, id, familyID, ledgerID);
        Person albertina = PersonFactory.create(albertinaDTO);
        PersonVoDTO alanDTO = new PersonVoDTO(name, otherBirthDate, vat, address, telephoneNumberList, id, familyID, ledgerID);
        Person alan = PersonFactory.create(alanDTO);

        //act
        personList.add(albertina);
        personList.add(alan);

        result = personList.size();

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get person list: empty")
    void getPersonList_Empty() {
        //arrange
        PersonList personList = new PersonList();
        List<Person> expected = new ArrayList<>();
        //act
        List<Person> result = personList.getPersonList();
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get person list: one element")
    void getPersonList_OneElement() throws Exception {
        //arrange
        PersonList personList = new PersonList();
        List<Person> expected = new ArrayList<>();

        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-03-22");
        Email id = new Email("albertina@gmail.com");
        PersonName name = new PersonName("Albertina");
        VAT vat = new VAT("123456789");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        PersonVoDTO dto = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, id, familyID, ledgerID);
        Person person = PersonFactory.create(dto);

        personList.add(person);
        expected.add(person);

        //act
        List<Person> result = personList.getPersonList();
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get person list: two elements")
    void getPersonList_TwoElements() throws Exception {
        //arrange
        PersonList personList = new PersonList();
        List<Person> expected = new ArrayList<>();

        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-03-22");
        Email id = new Email("albertina@gmail.com");
        PersonName name = new PersonName("Albertina");
        VAT vat = new VAT("123456789");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        PersonVoDTO dto = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, id, familyID, ledgerID);
        Person person = PersonFactory.create(dto);
        Person otherPerson = PersonFactory.create(dto);

        personList.add(person);
        personList.add(otherPerson);
        expected.add(person);
        expected.add(otherPerson);

        //act
        List<Person> result = personList.getPersonList();
        //assert
        assertEquals(expected, result);
    }
}
