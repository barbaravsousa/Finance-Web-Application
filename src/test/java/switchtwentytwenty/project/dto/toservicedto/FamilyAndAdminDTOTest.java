package switchtwentytwenty.project.dto.toservicedto;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FamilyAndAdminDTOTest {

    @Test
    void createFamilyAndAdminDTOSuccessfully(){

        //arrange
        String birthDate = "1992-12-12";
        String city = "Porto";
        String country = "Portugal";
        String email = "indy@gmail.com";
        String familyName = "Jones";
        String houseNumber = "23";
        String personName = "Indy";
        String street = "Dragons Street";
        String vat = "261323482";
        String zipCode = "2345-234";
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("223423567");
        String username = "DrJones";
        String password = "1234";

        FamilyAndAdminDTO dto = new FamilyAndAdminDTO(personName,birthDate,vat,houseNumber,street,
                city,country,zipCode,phoneNumbers,email,familyName, username, password);
        //act & assert
        assertEquals(dto.getBirthDate(), birthDate);
        assertEquals(dto.getCity(), city);
        assertEquals(dto.getCountry(), country);
        assertEquals(dto.getEmail(), email);
        assertTrue(phoneNumbers.contains("223423567"));
        assertEquals(dto.getPhoneNumbers(), phoneNumbers);
        assertEquals(dto.getFamilyName(), familyName);
        assertEquals(dto.getHouseNumber(), houseNumber);
        assertEquals(dto.getPersonName(), personName);
        assertEquals(dto.getStreet(), street);
        assertEquals(dto.getVat(), vat);
        assertEquals(dto.getZipCode(), zipCode);
    }

}