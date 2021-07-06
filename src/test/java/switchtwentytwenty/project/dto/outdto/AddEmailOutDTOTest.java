package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AddEmailOutDTOTest {

    @Test
    @DisplayName("Create a AddEmailDTO successfully")
    void createAOutAddEmailDTOSuccess() {

        //act
        AddEmailOutDTO dto = new AddEmailOutDTO();
        //assert
        assertNotNull(dto);
    }


    @Test
    void getPersonIDTest() {
        //assert
        String expected = "tiago@gmail.com";
        String personId = "tiago@gmail.com";
        String differentID = "tiago2@gmail.com";

        AddEmailOutDTO dto = new AddEmailOutDTO(personId);
        //act
        String result = dto.getPersonID();
        //assert
        assertEquals(expected, result);
        assertNotEquals(differentID, result);

    }


    @Test
    void setPersonIDTest() {
        //arrange
        String expected = "tiago2@gmail.com";
        String personId = "tiago@gmail.com";

        AddEmailOutDTO dto = new AddEmailOutDTO(personId);
        //act
        dto.setPersonID("tiago2@gmail.com");
        String result = dto.getPersonID();
        //assert
        assertEquals(expected, result);

    }


    @Test
    @DisplayName("Verify two equal dto successfully")
    void equalsSameDToTest() {

        //arrange
        String personId = "tiago@gmail.com";

        AddEmailOutDTO dto = new AddEmailOutDTO(personId);
        //act
        boolean result = dto.equals(dto);
        //assert
        assertTrue(result);
    }


    @Test
    @DisplayName("Test equals with null- successfully")
    void equalsNullTest() {

        //arrange

        String personId = "tiago@gmail.com";

        AddEmailOutDTO dto = new AddEmailOutDTO(personId);
        //act
        boolean result = dto.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Verify two equals for two dtos unsuccessfully")
    void testEqualsForDiferentDTos() {

        //arrange

        String personId = "tiago@gmail.com";

        AddEmailOutDTO dto = new AddEmailOutDTO(personId);

        String secondPersonId = "tiagoRe@gmail.com";

        AddEmailOutDTO secondDto = new AddEmailOutDTO(secondPersonId);

        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Verify equals for diferent objects - successfully")
    void testEqualsForDiferentObjects() {

        //arrange
        String personId = "tiago@gmail.com";

        AddEmailOutDTO dto = new AddEmailOutDTO(personId);

        BigDecimal number = new BigDecimal(10);

        //act
        boolean result = dto.equals(number);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Verify two similar dto - successfully")
    void testEqualsForSimilarDTo() {

        //arrange
        String personId = "tiago@gmail.com";

        AddEmailOutDTO dto = new AddEmailOutDTO(personId);

        String secondPersonId = "tiago@gmail.com";

        AddEmailOutDTO secondDto = new AddEmailOutDTO(secondPersonId);
        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Verify hash code for different dto - successfully")
    void testHashCodeForDifferentDTo() {

        //arrange

        String personId = "tiago@gmail.com";

        AddEmailOutDTO dto = new AddEmailOutDTO(personId);


        String secondPersonId = "tiagoR@gmail.com";

        AddEmailOutDTO secondDto = new AddEmailOutDTO(secondPersonId);
        //act
        int hashCodeDto = dto.hashCode();
        int hashCodeSecondDto = secondDto.hashCode();

        //assert
        assertNotEquals(hashCodeDto, hashCodeSecondDto);
    }

    @Test
    @DisplayName("Verify hash code for similar dto - successfully")
    void testHashCodeForSimilarDTo() {

        //arrange

        String personId = "tiago@gmail.com";

        AddEmailOutDTO dto = new AddEmailOutDTO(personId);


        String secondPersonId = "tiago@gmail.com";

        AddEmailOutDTO secondDto = new AddEmailOutDTO(secondPersonId);
        //act
        int hashCodeDto = dto.hashCode();
        int hashCodeSecondDto = secondDto.hashCode();

        //assert
        assertEquals(hashCodeDto, hashCodeSecondDto);
    }


}