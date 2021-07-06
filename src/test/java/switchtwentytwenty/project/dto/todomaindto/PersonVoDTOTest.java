package switchtwentytwenty.project.dto.todomaindto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.*;
import switchtwentytwenty.project.domain.share.persondata.address.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PersonVoDTOTest {

    @Test
    @DisplayName("Test getters")
    void testGetters() throws Exception {
        //arrange
        String name = "Constantino";
        String birthDate = "2020-05-01";
        String vat = "123456789";
        String houseNumber = "25";
        String street = "Rua Nova";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4521-856";
        String email = "constantino@dolado.com";
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("225463859");
        BirthDate birthDate1 = new BirthDate(birthDate);
        Address address = new Address(street, houseNumber, zipCode, city, country);
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        VAT vat1 = new VAT(vat);
        TelephoneNumberList telephonelist = new TelephoneNumberList(phoneNumbers);
        PersonName name1 = new PersonName(name);
        Email email1 = new Email(email);

        PersonVoDTO PersonVoDTO = new PersonVoDTO(name1, birthDate1, vat1, address, telephonelist, email1, familyID, ledgerID);

        //act
        PersonName resultName = PersonVoDTO.getName();
        BirthDate resultBirthDate = PersonVoDTO.getBirthDate();
        VAT resultVat = PersonVoDTO.getVat();
        Email resultEmail = PersonVoDTO.getEmail();
        Address resultAddress = PersonVoDTO.getAddress();
        TelephoneNumberList resultPhoneNumbers = PersonVoDTO.getPhoneNumbers();
        FamilyID resultFamilyID = PersonVoDTO.getFamilyID();
        LedgerID resultLedgerID = PersonVoDTO.getLedgerID();
        //assert
        assertEquals(name1, resultName);
        assertEquals(birthDate1, resultBirthDate);
        assertEquals(email1, resultEmail);
        assertEquals(address, resultAddress);
        assertEquals(vat1, resultVat);
        assertEquals(ledgerID, resultLedgerID);
        assertEquals(telephonelist, resultPhoneNumbers);
        assertEquals(familyID, resultFamilyID);
    }
}
