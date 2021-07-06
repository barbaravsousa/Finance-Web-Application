package switchtwentytwenty.project.datamodel.assembler;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.datamodel.AddressJPA;
import switchtwentytwenty.project.datamodel.EmailJPA;
import switchtwentytwenty.project.datamodel.PersonJPA;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.list.EmailList;
import switchtwentytwenty.project.domain.share.persondata.*;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.todomaindto.PersonJpaToDomainDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PersonDomainDataAssemblerIT {

    @Test
    void personAssemblerToDataWithSeveralEmails() throws Exception {
        //arrange
        PersonDomainDataAssembler assembler = new PersonDomainDataAssembler();
        String phoneNumber = "221345654";
        Email personID = new Email("indy@gmail.com");
        PersonName name = new PersonName("Indy");
        BirthDate birthDate = new BirthDate("1965-12-23");
        Address address = new Address("Dragons street", "23", "2345-234","Porto", "Portugal");
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        VAT vat = new VAT("261323482");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumber telephoneNumber = new TelephoneNumber(phoneNumber);
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(telephoneNumber);
        PersonVoDTO personVoDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, personID, familyID, ledgerID);
        Person person = PersonFactory.create(personVoDTO);

        Email otherEmail1 = new Email("other1@gmail.com");
        Email otherEmail2 = new Email("other2@gmail.com");
        Email otherEmail3 = new Email("other3@gmail.com");
        person.addEmail(otherEmail1);
        person.addEmail(otherEmail2);
        person.addEmail(otherEmail3);
        //act
        PersonJPA personJPA = assembler.toData(person);
        Person newPerson = PersonFactory.create(assembler.toDomain(personJPA));
        //assert
        assertNotNull(personJPA);
        assertEquals(newPerson.getTelephoneNumbers(), telephoneNumberList);
    }

    @Test
    void personAssemblerToDomainWithSeveralEmails() throws Exception {
        //arrange
        PersonDomainDataAssembler assembler = new PersonDomainDataAssembler();
        String mainEmail = "indy@gmail.com";
        String name = "Indy Jones";
        String vat = "261323482";
        String birthDate = "1965-12-23";
        String familyID = UUID.randomUUID().toString();
        String ledgerID = UUID.randomUUID().toString();
        PersonJPA personJPA = new PersonJPA(mainEmail,name,vat,birthDate,familyID,ledgerID);

        String houseNumber = "23";
        String street = "Rua das Flores";
        String country = "Portugal";
        String city = "Porto";
        String zipCode = "2345-234";

        AddressJPA addressJPA = new AddressJPA(houseNumber,street,city,zipCode,country,personJPA);

        personJPA.setAddress(addressJPA);

        String otherEmail1 = "other1@gmail.com";
        String otherEmail2 = "other2@gmail.com";
        String otherEmail3 = "other3@gmail.com";

        EmailJPA otherEmailJPA1 = new EmailJPA(otherEmail1,personJPA);
        EmailJPA otherEmailJPA2 = new EmailJPA(otherEmail2,personJPA);
        EmailJPA otherEmailJPA3 = new EmailJPA(otherEmail3,personJPA);

        personJPA.addEmail(otherEmailJPA1);
        personJPA.addEmail(otherEmailJPA2);
        personJPA.addEmail(otherEmailJPA3);

        Email otherEmail_1 = new Email(otherEmail1);
        Email otherEmail_2 = new Email(otherEmail2);
        Email otherEmail_3 = new Email(otherEmail3);
        //act
        PersonJpaToDomainDTO person = assembler.toDomain(personJPA);
        //assert
        assertNotNull(person);
        EmailList list = person.getOtherEmails();
        assertTrue(list.contains(otherEmail_1));
        assertTrue(list.contains(otherEmail_2));
        assertTrue(list.contains(otherEmail_3));
    }
}