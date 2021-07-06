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

import static org.junit.jupiter.api.Assertions.*;

class EmailListTest {

    @Test
    @DisplayName("person doesn't contain other email")
    void containsEmail() throws InvalidEmailException, InvalidVATException, InvalidDateException, InvalidPersonNameException {
        //arrange
        Email email = new Email("bones@gmail.com");
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        VAT vat = new VAT("239026080");
        Address address = new Address("Ivy Street", "22", "2345-234", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1999-12-10");
        PersonName name = new PersonName("John Bones");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        PersonVoDTO personVoDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, email, familyID, ledgerID);
        Person person = PersonFactory.create(personVoDTO);
        Email newEmail = new Email("bones2@gmail.com");
        person.addEmail(newEmail);
        Email otherEmail = new Email("other@gmail.com");
        //act
        boolean result1 = person.containsEmail(otherEmail);
        boolean result2 = person.containsEmail(newEmail);
        //assert
        assertFalse(result1);
        assertTrue(result2);
    }

    @Test
    @DisplayName("Get String email list")
    void getStringEmailList() throws InvalidEmailException {

        //arrange
        String firstEmailToAdd = "tiago@gmail.com";
        String secondEmailToAdd = "tiagoS@gmail.com";
        EmailList emailList = new EmailList();
        Email firstEmail = new Email(firstEmailToAdd);
        Email secondEmail = new Email(secondEmailToAdd);

        emailList.add(firstEmail);
        emailList.add(secondEmail);

        List<String> expectedList = new ArrayList<>();
        expectedList.add(firstEmailToAdd);
        expectedList.add(secondEmailToAdd);

        //act
        List<String> resultList = emailList.toStringList();

        //assert
        assertEquals(expectedList.size(), resultList.size());
        assertNotSame(emailList, expectedList);
        assertEquals(expectedList, resultList);
    }

    @Test
    @DisplayName("Get String email list from an empty List")
    void getStringEmailFromAnEmptyList() {

        //arrange
        EmailList emailList = new EmailList();

        List<String> expectedList = new ArrayList<>();

        //act
        List<String> resultList = emailList.toStringList();

        //assert
        assertEquals(expectedList.size(), resultList.size());
        assertNotSame(expectedList, resultList);
        assertEquals(expectedList, resultList);
    }

    @Test
    void remove() throws Exception {
        //assert
        String firstEmailToAdd = "tiago@gmail.com";
        String secondEmailToAdd = "tiagoS@gmail.com";
        EmailList emailList = new EmailList();
        Email firstEmail = new Email(firstEmailToAdd);
        Email secondEmail = new Email(secondEmailToAdd);

        //act
        emailList.add(firstEmail);
        emailList.add(secondEmail);
        boolean result = emailList.remove(secondEmail);
        boolean removedEmailIsPresent = emailList.contains(secondEmail);
        boolean firstEmailIsPresent = emailList.contains(firstEmail);
        //assert
        assertFalse(removedEmailIsPresent);
        assertTrue(firstEmailIsPresent);
        assertTrue(result);
    }


    @Test
    void removeANullEmailFromTheList() throws Exception {
        //assert
        String firstEmailToAdd = "tiago@gmail.com";
        String secondEmailToAdd = "tiagoS@gmail.com";
        EmailList emailList = new EmailList();
        Email firstEmail = new Email(firstEmailToAdd);
        Email secondEmail = new Email(secondEmailToAdd);
        //act
        emailList.add(firstEmail);
        emailList.add(secondEmail);
        boolean result = emailList.remove(null);
        //assert
        assertFalse(result);
    }


}