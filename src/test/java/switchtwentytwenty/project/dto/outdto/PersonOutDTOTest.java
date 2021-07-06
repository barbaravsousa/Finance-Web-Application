
package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PersonOutDTOTest {

    @Test
    @DisplayName("Get main email")
    void getDescription() {
        //arrange

        PersonOutDTO dto = new PersonOutDTO("Maria","maria@gmail.com","asdf");
        String getMainMail= "maria@gmail.com";
        String getName = "Maria";
        String getFamilyId = "asdf";
        //act
        String firstResult = dto.getMainEmail();
        String secondResult = dto.getName();
        String thirdResult = dto.getFamilyID();
        //assert
        assertEquals(getMainMail,firstResult);
        assertEquals(getName,secondResult);
        assertEquals(getFamilyId,thirdResult);


    }

    @Test
    @DisplayName("Test Equals for the same dto")
    void testEqualsForTheSameDto() {
        //arrange
        PersonOutDTO dto = new PersonOutDTO("Maria","maria@gmail.com","asdf");
        //act
        boolean result = dto.equals(dto);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test Equals for the null case")
    void testEqualsForNull() {
        //arrange
        PersonOutDTO dto = new PersonOutDTO("Maria","maria@gmail.com","asdf");
        //act
        boolean result = dto.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals for the different objects")
    void testEqualsDifferentObject() {
        //arrange
        PersonOutDTO dto = new PersonOutDTO("Maria","maria@gmail.com","asdf");
        BigDecimal number = new BigDecimal(10);
        //act
        boolean result = dto.equals(number);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals for the dtos with different names")
    void testEqualsDifferentNames() {
        //arrange
        PersonOutDTO dto = new PersonOutDTO("Maria","maria@gmail.com","asdf");
        PersonOutDTO secondDto = new PersonOutDTO("Miguel","maria@gmail.com","asdf");
        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals for dtos with different emails")
    void testEqualsDifferentMainEmails() {
        //arrange
        PersonOutDTO dto = new PersonOutDTO("Maria","maria@gmail.com","asdf");
        PersonOutDTO secondDto = new PersonOutDTO("Maria","mariaSecond@gmail.com","asdf");
        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals for dtos with different familyIds")
    void testEqualsDiferentFamilyIds() {
        //arrange
        PersonOutDTO dto = new PersonOutDTO("Maria","maria@gmail.com","asdf");
        PersonOutDTO secondDto = new PersonOutDTO("Maria","mariaSecond@gmail.com","asdf2");
        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }




    @Test
    @DisplayName("Test Equals for similar dtos")
    void testEqualsTheSimilarDtos() {
        //arrange
        PersonOutDTO dto = new PersonOutDTO("Maria","maria@gmail.com","asdf");
        PersonOutDTO secondDto = new PersonOutDTO("Maria","maria@gmail.com","asdf");
        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertTrue(result);
    }
    @Test
    @DisplayName("Test Equals for different dtos")
    void testEqualsDifferentDtos() {
        //arrange
        PersonOutDTO dto = new PersonOutDTO("Maria","maria@gmail.com","asdf");
        PersonOutDTO secondDto = new PersonOutDTO("Joana","joana@gmail.com","assdsfffffffffffff");
        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }


    @Test
    void testHashCodeForSameDTOs() {

        //arrange
        PersonOutDTO dto = new PersonOutDTO("Maria","maria@gmail.com","asdf");
        PersonOutDTO secondDto = new PersonOutDTO("Maria","maria@gmail.com","asdf");
        //act
        int hashCodeDto = dto.hashCode();
        int hashCodeSecondDto = secondDto.hashCode();

        //assert
        assertEquals(hashCodeDto,hashCodeSecondDto);
    }

    @Test
    void testHashCodeForDifferentDTOs() {

        //arrange
        PersonOutDTO dto = new PersonOutDTO("Maria","maria@gmail.com","asdf");
        PersonOutDTO secondDto = new PersonOutDTO("MariaF","maria@gmail.com","asdf");
        //act
        int hashCodeDto = dto.hashCode();
        int hashCodeSecondDto = secondDto.hashCode();

        //assert
        assertNotEquals(hashCodeDto,hashCodeSecondDto);
    }

    @Test
    @DisplayName("Test Equals for similar dtos")
    void testEqualsTheSimilarDtosSameEmail() {
        //arrange
        PersonOutDTO dto = new PersonOutDTO("Maria","maria@gmail.com","asdf");
        PersonOutDTO secondDto = new PersonOutDTO("Maria","maria@gmail.com","asdf");
        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertTrue(result);
    }


}
