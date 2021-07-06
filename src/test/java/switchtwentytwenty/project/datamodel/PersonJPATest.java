package switchtwentytwenty.project.datamodel;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PersonJPATest {

    @Test
    void addEmail() {
        //arrange
        String mainEmail = "tiago@gmail.com";
        String name = "tiago";
        String vat = "248625357";
        String birthDate = "1970-02-04";
        String familyID = UUID.randomUUID().toString();
        String ledgerID = UUID.randomUUID().toString();
        String otherEmail = "constantino@gmail.com";

        PersonJPA personJPA = new PersonJPA(mainEmail, name, vat, birthDate, familyID, ledgerID);
        EmailJPA emailJPA = new EmailJPA(otherEmail,personJPA);

        //act
        personJPA.addEmail(emailJPA);
        List<EmailJPA> list = personJPA.getOtherEmails();
        boolean result = list.contains(emailJPA);
        //assert
        assertTrue(result);
    }

    @Test
    void addPhoneNumbers() {
        //arrange
        String mainEmail = "tiago@gmail.com";
        String name = "tiago";
        String vat = "248625357";
        String birthDate = "1970-02-04";
        String familyID = UUID.randomUUID().toString();
        String ledgerID = UUID.randomUUID().toString();
        String otherEmail = "constantino@gmail.com";
        String number = "298765897";

        PersonJPA personJPA = new PersonJPA(mainEmail, name, vat, birthDate, familyID, ledgerID);
        TelephoneNumberJPA telephoneNumberJPA = new TelephoneNumberJPA(number,personJPA);

        //act
        personJPA.addTelephoneNumber(telephoneNumberJPA);
        List<TelephoneNumberJPA> list = personJPA.getTelephoneNumbers();
        boolean result = list.contains(telephoneNumberJPA);
        //assert
        assertTrue(result);
    }
}