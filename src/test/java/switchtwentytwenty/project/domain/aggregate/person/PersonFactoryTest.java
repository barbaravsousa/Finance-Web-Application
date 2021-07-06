package switchtwentytwenty.project.domain.aggregate.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.share.id.*;
import switchtwentytwenty.project.domain.share.list.EmailList;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.todomaindto.PersonJpaToDomainDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PersonFactoryTest {

    @Test
    @DisplayName("Test creation of person with an PersonVoDTO")
    void createPerson_WithVOPersonDTO() throws Exception {
        //arrange
        Email email = new Email("email@gmail.com");
        PersonName personName = new PersonName("Joaquina");
        BirthDate birthDate = new BirthDate("2020-03-02");
        VAT vat = new VAT("123456789");
        Address address = new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal");
        List<String> list = new ArrayList<>();
        list.add("225498653");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList(list);
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        PersonVoDTO personVoDTO = new PersonVoDTO(personName, birthDate, vat, address, telephoneNumberList, email, familyID, ledgerID);
        //act
        Person person = PersonFactory.create(personVoDTO);
        //assert
        assertNotNull(person);
        assertEquals(person.getID(), email);
        assertEquals(person.getName(), personName);
        assertEquals(person.getBirthDate(), birthDate);
        assertEquals(person.getVat(), vat);
        assertEquals(person.getAddress(), address);
        assertEquals(person.getTelephoneNumbers(), telephoneNumberList);
        assertEquals(person.getFamilyID(), familyID);
        assertEquals(person.getLedgerID(), ledgerID);
    }

    @Test
    @DisplayName("Test creation of person with an PersonJpaToDomainDTO")
    void createPerson_WithJpaToDomainPersonDTO() throws Exception {
        //arrange
        Email email = new Email("email@gmail.com");
        PersonName personName = new PersonName("Joaquina");
        BirthDate birthDate = new BirthDate("2020-03-02");
        VAT vat = new VAT("123456789");
        Address address = new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal");
        List<String> list = new ArrayList<>();
        list.add("225498653");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList(list);
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        AccountIDList accountIDList = new AccountIDList();
        accountIDList.add(new AccountID(UUID.randomUUID()));
        EmailList otherEmails = new EmailList();
        otherEmails.add(new Email("anotherOne@gmail.com"));
        PersonJpaToDomainDTO personJpaToDomainDTO = new PersonJpaToDomainDTO(personName, birthDate, vat, address, telephoneNumberList, email,
                familyID, ledgerID, otherEmails, accountIDList);
        //act
        Person person = PersonFactory.create(personJpaToDomainDTO);
        //assert
        assertNotNull(person);
        assertEquals(person.getID(), email);
        assertEquals(person.getName(), personName);
        assertEquals(person.getBirthDate(), birthDate);
        assertEquals(person.getVat(), vat);
        assertEquals(person.getAddress(), address);
        assertEquals(person.getTelephoneNumbers(), telephoneNumberList);
        assertEquals(person.getFamilyID(), familyID);
        assertEquals(person.getLedgerID(), ledgerID);
        assertEquals(person.getAccountIDList(), accountIDList);
        assertEquals(person.getOtherEmails(), otherEmails);
    }

    @Test
    @DisplayName("Failure create person with an PersonVoDTO: empty")
    void failureCreatePerson_WithEmptyVOPersonDTO(){
        //arrange
        PersonVoDTO personVoDTO = new PersonVoDTO();
        //act - assert
        assertThrows(NullPointerException.class, ()-> PersonFactory.create(personVoDTO));
    }
}