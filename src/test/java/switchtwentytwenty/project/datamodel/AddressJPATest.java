package switchtwentytwenty.project.datamodel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentytwenty.project.interfaceadaptor.repository.PersonRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AddressJPATest {

    @Autowired
    PersonRepository personRepository;

    @Test
    @DisplayName("Test getters")
    void testGetter() {
        //arrange

        //address JPA
        String houseNumber = "23";
        String street = "Street";
        String city = "London";
        String zipCode = "3000";
        String country = "UK";

        //person JPA
        String mainEmail = "margaret_howard@hotmail.com";
        String name = "Alan Turing";
        String vat = "247652962";
        String birthDate = "1988-02-01";
        String familyID = UUID.randomUUID().toString();
        String ledgerID = UUID.randomUUID().toString();

        PersonJPA personJPA = new PersonJPA(mainEmail, name, vat, birthDate, familyID, ledgerID);
        AddressJPA addressJPA = new AddressJPA(houseNumber, street, city, zipCode, country, personJPA);
        personJPA.setAddress(addressJPA);

        //act
        PersonJPA returnPersonJPA = addressJPA.getPersonJPA();
        String mainEmailResult = returnPersonJPA.getMainEmail();
        String nameResult = returnPersonJPA.getName();
        String vatResult = returnPersonJPA.getVat();
        String birthDateResult = returnPersonJPA.getBirthDate();
        String familyIDResult = returnPersonJPA.getFamilyID();
        String ledgerIDResult = returnPersonJPA.getLedgerID();

        List<TelephoneNumberJPA> phoneListResult = returnPersonJPA.getTelephoneNumbers();
        List<EmailJPA> otherEmailResult = returnPersonJPA.getOtherEmails();
        List<AccountIDJPA> accountIDJPAResult = returnPersonJPA.getAccountIDList();

        //assert
        assertEquals(mainEmail, mainEmailResult);
        assertEquals(name, nameResult);
        assertEquals(vat, vatResult);
        assertEquals(birthDate, birthDateResult);
        assertEquals(familyID, familyIDResult);
        assertEquals(ledgerID, ledgerIDResult);
        assertTrue(phoneListResult.isEmpty());
        assertTrue(otherEmailResult.isEmpty());
        assertTrue(accountIDJPAResult.isEmpty());
    }

    @Test
    @DisplayName("Test getters")
    void testNotNullAddressJPA() {
        //arrange

        //address JPA
        String houseNumber = "23";
        String street = "Street";
        String city = "London";
        String zipCode = "3000";
        String country = "UK";

        //person JPA
        String mainEmail = "margaret_howard@hotmail.com";
        String name = "Alan Turing";
        String vat = "247652962";
        String birthDate = "1988-02-01";
        String familyID = UUID.randomUUID().toString();
        String ledgerID = UUID.randomUUID().toString();

        //act
        PersonJPA personJPA = new PersonJPA(mainEmail, name, vat, birthDate, familyID, ledgerID);
        AddressJPA addressJPA = new AddressJPA(houseNumber, street, city, zipCode, country, personJPA);

        //assert
        assertNotNull(personJPA);
        assertNotNull(addressJPA);


    }


}
