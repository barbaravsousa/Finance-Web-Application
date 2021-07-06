package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.id.Email;

import static org.junit.jupiter.api.Assertions.*;

class DeleteEmailOutDTOTest {

    @Test
    @DisplayName("Create a dto and set and get personID")
    void createValidDto() throws Exception {
        //arrange
        DeleteEmailOutDTO dto = new DeleteEmailOutDTO();
        String personIdExpected = "tiago@gmail.com";
        //act
        dto.setPersonID("tiago@gmail.com");
        String result = dto.getPersonID();
        //assert
        assertNotNull(dto);
        assertEquals(personIdExpected, result);
    }

    @Test
    @DisplayName("Test equals for the same dto")
    void equalsForSameDto() throws Exception {
        //arrange
        DeleteEmailOutDTO dto = new DeleteEmailOutDTO("tiago@gmail.com");
        //act
        boolean result = dto.equals(dto);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test equals for different objects")
    void equalsForDifferentObjects() throws Exception {
        //arrange
        DeleteEmailOutDTO dto = new DeleteEmailOutDTO("tiago@gmail.com");
        double value = 10;
        //act
        boolean result = dto.equals(value);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals for similiar objects")
    void equalsSimilarDtos() throws Exception {
        //arrange
        DeleteEmailOutDTO dto = new DeleteEmailOutDTO("tiago@gmail.com");
        DeleteEmailOutDTO secondDto = new DeleteEmailOutDTO("tiago@gmail.com");
        DeleteEmailOutDTO thirdDto = new DeleteEmailOutDTO("tiago1@gmail.com");

        //act
        boolean result = dto.equals(secondDto);
        boolean secondResult = dto.equals(thirdDto);
        //assert
        assertFalse(secondResult);
        assertTrue(result);
    }

    @Test
    @DisplayName("Verify hash code for different dto - successfully")
    void testHashCodeForDifferentDTo() {

        //arrange

        DeleteEmailOutDTO dto = new DeleteEmailOutDTO("tiago1@gmail.com");
        DeleteEmailOutDTO secondDto = new DeleteEmailOutDTO("tiago@gmail.com");
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
        DeleteEmailOutDTO dto = new DeleteEmailOutDTO("tiago@gmail.com");
        DeleteEmailOutDTO secondDto = new DeleteEmailOutDTO("tiago@gmail.com");

        //act
        int hashCodeDto = dto.hashCode();
        int hashCodeSecondDto = secondDto.hashCode();

        //assert
        assertEquals(hashCodeDto, hashCodeSecondDto);
    }


}