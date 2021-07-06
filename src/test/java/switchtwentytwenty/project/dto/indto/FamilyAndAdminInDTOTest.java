package switchtwentytwenty.project.dto.indto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FamilyAndAdminInDTOTest {

    @Test
    void inFamilyAndAdminDTO_getBirthDate(){
        //arrange
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("91090909234");
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setPersonName("Indy");
        dto.setZipCode("2345-456");
        dto.setVat("224256360");
        dto.setStreet("Rua dos dragões");
        dto.setHouseNumber("23");
        dto.setFamilyName("Jones");
        dto.setEmail("indy@gmail.com");
        dto.setCountry("Porto");
        dto.setCity("Portugal");
        dto.setPhoneNumbers(phoneNumbers);
        dto.setBirthDate("1966-09-12");
        dto.setDescription("addFamily");
        //act
        String result = dto.getBirthDate();
        //assert
        assertEquals("1966-09-12",result);
    }

    @Test
    void inFamilyAndAdminDTO_getPersonName(){
        //arrange
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("91090909234");
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setPersonName("Indy");
        dto.setZipCode("2345-456");
        dto.setVat("224256360");
        dto.setStreet("Rua dos dragões");
        dto.setHouseNumber("23");
        dto.setFamilyName("Jones");
        dto.setEmail("indy@gmail.com");
        dto.setCountry("Porto");
        dto.setCity("Portugal");
        dto.setPhoneNumbers(phoneNumbers);
        dto.setBirthDate("1966-09-12");
        dto.setDescription("addFamily");
        //act
        String result = dto.getPersonName();
        //assert
        assertEquals("Indy",result);
    }

    @Test
    void inFamilyAndAdminDTO_getzipCode(){
        //arrange
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("91090909234");
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setPersonName("Indy");
        dto.setZipCode("2345-456");
        dto.setVat("224256360");
        dto.setStreet("Rua dos dragões");
        dto.setHouseNumber("23");
        dto.setFamilyName("Jones");
        dto.setEmail("indy@gmail.com");
        dto.setCountry("Porto");
        dto.setCity("Portugal");
        dto.setPhoneNumbers(phoneNumbers);
        dto.setBirthDate("1966-09-12");
        dto.setDescription("addFamily");
        //act
        String result = dto.getZipCode();
        //assert
        assertEquals("2345-456",result);
    }

    @Test
    void inFamilyAndAdminDTO_getVat(){
        //arrange
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("91090909234");
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setPersonName("Indy");
        dto.setZipCode("2345-456");
        dto.setVat("224256360");
        dto.setStreet("Rua dos dragões");
        dto.setHouseNumber("23");
        dto.setFamilyName("Jones");
        dto.setEmail("indy@gmail.com");
        dto.setCountry("Porto");
        dto.setCity("Portugal");
        dto.setPhoneNumbers(phoneNumbers);
        dto.setBirthDate("1966-09-12");
        dto.setDescription("addFamily");
        //act
        String result = dto.getVat();
        //assert
        assertEquals("224256360",result);
    }

    @Test
    void inFamilyAndAdminDTO_getStreet(){
        //arrange
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("91090909234");
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setPersonName("Indy");
        dto.setZipCode("2345-456");
        dto.setVat("224256360");
        dto.setStreet("Rua dos dragões");
        dto.setHouseNumber("23");
        dto.setFamilyName("Jones");
        dto.setEmail("indy@gmail.com");
        dto.setCountry("Porto");
        dto.setCity("Portugal");
        dto.setPhoneNumbers(phoneNumbers);
        dto.setBirthDate("1966-09-12");
        dto.setDescription("addFamily");
        //act
        String result = dto.getStreet();
        //assert
        assertEquals("Rua dos dragões",result);
    }

    @Test
    void inFamilyAndAdminDTO_getHouseNumber(){
        //arrange
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("91090909234");
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setPersonName("Indy");
        dto.setZipCode("2345-456");
        dto.setVat("224256360");
        dto.setStreet("Rua dos dragões");
        dto.setHouseNumber("23");
        dto.setFamilyName("Jones");
        dto.setEmail("indy@gmail.com");
        dto.setCountry("Porto");
        dto.setCity("Portugal");
        dto.setPhoneNumbers(phoneNumbers);
        dto.setBirthDate("1966-09-12");
        dto.setDescription("addFamily");
        //act
        String result = dto.getHouseNumber();
        //assert
        assertEquals("23",result);
    }

    @Test
    void inFamilyAndAdminDTO_getFamilyName(){
        //arrange
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("91090909234");
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setPersonName("Indy");
        dto.setZipCode("2345-456");
        dto.setVat("224256360");
        dto.setStreet("Rua dos dragões");
        dto.setHouseNumber("23");
        dto.setFamilyName("Jones");
        dto.setEmail("indy@gmail.com");
        dto.setCountry("Porto");
        dto.setCity("Portugal");
        dto.setPhoneNumbers(phoneNumbers);
        dto.setBirthDate("1966-09-12");
        dto.setDescription("addFamily");
        //act
        String result = dto.getFamilyName();
        //assert
        assertEquals("Jones",result);
    }

    @Test
    void inFamilyAndAdminDTO_getEmail(){
        //arrange
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("91090909234");
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setPersonName("Indy");
        dto.setZipCode("2345-456");
        dto.setVat("224256360");
        dto.setStreet("Rua dos dragões");
        dto.setHouseNumber("23");
        dto.setFamilyName("Jones");
        dto.setEmail("indy@gmail.com");
        dto.setCountry("Porto");
        dto.setCity("Portugal");
        dto.setPhoneNumbers(phoneNumbers);
        dto.setBirthDate("1966-09-12");
        dto.setDescription("addFamily");
        //act
        String result = dto.getEmail();
        //assert
        assertEquals("indy@gmail.com",result);
    }

    @Test
    void inFamilyAndAdminDTO_getCountry(){
        //arrange
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("91090909234");
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setPersonName("Indy");
        dto.setZipCode("2345-456");
        dto.setVat("224256360");
        dto.setStreet("Rua dos dragões");
        dto.setHouseNumber("23");
        dto.setFamilyName("Jones");
        dto.setEmail("indy@gmail.com");
        dto.setCountry("Porto");
        dto.setCity("Portugal");
        dto.setPhoneNumbers(phoneNumbers);
        dto.setBirthDate("1966-09-12");
        dto.setDescription("addFamily");
        //act
        String result = dto.getCountry();
        //assert
        assertEquals("Porto",result);
    }

    @Test
    void inFamilyAndAdminDTO_getCity(){
        //arrange
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("91090909234");
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setPersonName("Indy");
        dto.setZipCode("2345-456");
        dto.setVat("224256360");
        dto.setStreet("Rua dos dragões");
        dto.setHouseNumber("23");
        dto.setFamilyName("Jones");
        dto.setEmail("indy@gmail.com");
        dto.setCountry("Portugal");
        dto.setCity("Porto");
        dto.setPhoneNumbers(phoneNumbers);
        dto.setBirthDate("1966-09-12");
        dto.setDescription("addFamily");
        //act
        String result = dto.getCity();
        //assert
        assertEquals("Porto",result);
    }

    @Test
    void inFamilyAndAdminDTO_getDescription(){
        //arrange
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("91090909234");
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setPersonName("Indy");
        dto.setZipCode("2345-456");
        dto.setVat("224256360");
        dto.setStreet("Rua dos dragões");
        dto.setHouseNumber("23");
        dto.setFamilyName("Jones");
        dto.setEmail("indy@gmail.com");
        dto.setCountry("Porto");
        dto.setCity("Portugal");
        dto.setPhoneNumbers(phoneNumbers);
        dto.setBirthDate("1966-09-12");
        dto.setDescription("addFamily");
        //act
        String result = dto.getDescription();
        //assert
        assertEquals("addFamily",result);
    }

    @Test
    void inFamilyAndAdminDTO_getphoneNumbers(){
        //arrange
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("91090909234");
        phoneNumbers.add("91827373777");
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setPersonName("Indy");
        dto.setZipCode("2345-456");
        dto.setVat("224256360");
        dto.setStreet("Rua dos dragões");
        dto.setHouseNumber("23");
        dto.setFamilyName("Jones");
        dto.setEmail("indy@gmail.com");
        dto.setCountry("Porto");
        dto.setCity("Portugal");
        dto.setPhoneNumbers(phoneNumbers);
        dto.setBirthDate("1966-09-12");
        dto.setDescription("addFamily");
        //act
        List<String> result = dto.getPhoneNumbers();
        //assert
        assertTrue(result.contains("91090909234"));
        assertTrue(result.contains("91827373777"));
    }
    @Test
    void inFamilyAndAdminDTO_getUserNameAndPassword(){
        //arrange
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("91090909234");
        phoneNumbers.add("91827373777");
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setPersonName("Indy");
        dto.setZipCode("2345-456");
        dto.setVat("224256360");
        dto.setStreet("Rua dos dragões");
        dto.setHouseNumber("23");
        dto.setFamilyName("Jones");
        dto.setEmail("indy@gmail.com");
        dto.setCountry("Porto");
        dto.setCity("Portugal");
        dto.setPhoneNumbers(phoneNumbers);
        dto.setBirthDate("1966-09-12");
        dto.setDescription("addFamily");
        dto.setUsername("indy");
        dto.setPassword("indy98");
        //act
        String resultUser = dto.getUsername();
        String resultPassword = dto.getPassword();
        //assert
        assertEquals("indy",resultUser);
        assertEquals("indy98",resultPassword);

    }

}