package switchtwentytwenty.project.dto.indto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonInDTOTest {

    @Test
    @DisplayName("Test constructor")
    void testConstructor() {
        //act
        PersonInDTO personInDTO = new PersonInDTO();
        //assert
        assertNotNull(personInDTO);
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
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("225463859");
        String password = "I want to be rich";

        PersonInDTO personInDTO = new PersonInDTO();
        personInDTO.setName(name);
        personInDTO.setBirthDate(birthDate);
        personInDTO.setVat(vat);
        personInDTO.setHouseNumber(houseNumber);
        personInDTO.setStreet(street);
        personInDTO.setCity(city);
        personInDTO.setCountry(country);
        personInDTO.setZipCode(zipCode);
        personInDTO.setPhoneNumbers(phoneNumbers);
        personInDTO.setPassword(password);

        //act
        String resultName = personInDTO.getName();
        String resultBirthDate = personInDTO.getBirthDate();
        String resultVat = personInDTO.getVat();
        String resultHouseNumber = personInDTO.getHouseNumber();
        String resultStreet = personInDTO.getStreet();
        String resultCity = personInDTO.getCity();
        String resultCountry = personInDTO.getCountry();
        String resultZipCode = personInDTO.getZipCode();
        List<String> resultPhoneNumbers = personInDTO.getPhoneNumbers();
        String resultPassword =personInDTO.getPassword();
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
        assertEquals(password,resultPassword);
    }
}