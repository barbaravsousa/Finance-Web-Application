package switchtwentytwenty.project.dto.toservicedto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PersonDTOTest {

    @Test
    @DisplayName("Test constructor")
    void testConstructor() {
        //act
        PersonDTO personDTO = new PersonDTO();
        //assert
        assertNotNull(personDTO);
    }

    @Test
    @DisplayName("Test getters")
    void testGetters() {
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
        String familyID= UUID.randomUUID().toString();

        PersonDTO personDTO = new PersonDTO.PersonDTOBuilder()
                .withBirthDate(birthDate)
                .withCity(city)
                .withCountry(country)
                .withEmail(email)
                .withFamilyID(familyID)
                .withName(name)
                .withVat(vat)
                .withHouseNumber(houseNumber)
                .withStreet(street)
                .withZipCode(zipCode)
                .withPhoneNumbers(phoneNumbers)
                .build();

        //act
        String resultName = personDTO.getName();
        String resultBirthDate = personDTO.getBirthDate();
        String resultVat = personDTO.getVat();
        String resultHouseNumber = personDTO.getHouseNumber();
        String resultStreet = personDTO.getStreet();
        String resultCity = personDTO.getCity();
        String resultCountry = personDTO.getCountry();
        String resultZipCode = personDTO.getZipCode();
        List<String> resultPhoneNumbers = personDTO.getPhoneNumbers();
        String resultFamilyID = personDTO.getFamilyID();
        //assert
        assertEquals(name, resultName);
        assertEquals(birthDate, resultBirthDate);
        assertEquals(vat, resultVat);
        assertEquals(houseNumber, resultHouseNumber);
        assertEquals(street, resultStreet);
        assertEquals(city, resultCity);
        assertEquals(country, resultCountry);
        assertEquals(zipCode, resultZipCode);
        assertEquals(phoneNumbers, resultPhoneNumbers);
        assertEquals(familyID,resultFamilyID);
    }


}