package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileOutDTOTest {

    @Test
    void outUserProfileDTOTest() {
        List<String> telephoneNumberListSecond = new ArrayList<>();
        telephoneNumberListSecond.add("225658541");
        List<String> otherEmails = new ArrayList<>();
        otherEmails.add("teste@gmail.com");
        UserProfileOutDTO result = new UserProfileOutDTO.UserProfileDTOBuilder()
                .withName("Alan Turing")
                .withVat("276841603")
                .withBirthDate("1995-01-22")
                .withHouseNumber("25")
                .withStreet("Rua Nova")
                .withCity("Porto")
                .withCountry("Portugal")
                .withZipCode("4125-886")
                .withTelephoneNumbers(telephoneNumberListSecond)
                .withMainEmail("bonesF@gmail.com")
                .withOtherEmails(otherEmails)
                .build();

        String nameExp = "Alan Turing";
        String vatExp = "276841603";
        String birthExp = "1995-01-22";
        String houseNumberExp = "25";
        String streetExp = "Rua Nova";
        String cityExp = "Porto";
        String countryExp = "Portugal";
        String zipCodeExp = "4125-886";
        List<String> telephoneExp = new ArrayList<>();
        telephoneExp.add("225658541");
        String mainEmailExp = "bonesF@gmail.com";
        List<String> otherEmailsExp = new ArrayList<>();
        otherEmailsExp.add("teste@gmail.com");

        assertEquals(nameExp, result.getName());
        assertEquals(vatExp, result.getVat());
        assertEquals(birthExp, result.getBirthDate());
        assertEquals(houseNumberExp, result.getHouseNumber());
        assertEquals(streetExp, result.getStreet());
        assertEquals(cityExp, result.getCity());
        assertEquals(countryExp, result.getCountry());
        assertEquals(zipCodeExp, result.getZipCode());
        assertEquals(telephoneExp, result.getTelephoneNumbers());
        assertEquals(mainEmailExp, result.getMainEmail());
        assertEquals(otherEmailsExp, result.getOtherEmails());
    }
    @Test
    void outUserProfileDTOTestFailure() {
        List<String> telephoneNumberListSecond = new ArrayList<>();
        telephoneNumberListSecond.add("225658541");
        UserProfileOutDTO result = new UserProfileOutDTO.UserProfileDTOBuilder()
                .withName("Alan Turing")
                .withVat("276841603")
                .withBirthDate("1995-01-22")
                .withHouseNumber("25")
                .withStreet("Rua Nova")
                .withCity("Porto")
                .withCountry("Portugal")
                .withZipCode("4125-886")
                .withTelephoneNumbers(telephoneNumberListSecond)
                .withMainEmail("bonesF@gmail.com")
                .withOtherEmails(null)
                .build();

        String nameExp = "Alan Turing";
        String vatExp = "276841603";
        String birthExp = "1995-01-20";
        String houseNumberExp = "30";
        String streetExp = "Rua Porto";
        String cityExp = "Porto";
        String countryExp = "Portugal";
        String zipCodeExp = "4125-886";
        List<String> telephoneExp = new ArrayList<>();
        telephoneExp.add("225658541");
        String mainEmailExp = "bonesF@gmail.com";

        assertEquals(nameExp, result.getName());
        assertEquals(vatExp, result.getVat());
        assertNotEquals(birthExp, result.getBirthDate());
        assertNotEquals(houseNumberExp, result.getHouseNumber());
        assertNotEquals(streetExp, result.getStreet());
        assertEquals(cityExp, result.getCity());
        assertEquals(countryExp, result.getCountry());
        assertEquals(zipCodeExp, result.getZipCode());
        assertEquals(telephoneExp, result.getTelephoneNumbers());
        assertEquals(mainEmailExp, result.getMainEmail());
    }
}