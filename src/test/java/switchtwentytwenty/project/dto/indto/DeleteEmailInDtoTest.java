package switchtwentytwenty.project.dto.indto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteEmailInDtoTest {

    @Test
    void createAValidDTOAndSetEmail()
    {
        DeleteEmailInDto dto = new DeleteEmailInDto();
        dto.setEmailToDelete("tiago@gmail.com");
        String expected = "tiago@gmail.com";
        //act
        String result = dto.getEmailToDelete();
        //assert
        assertNotNull(dto);
        assertEquals(expected,result);
    }

    @Test
    void testEqualsForSameDTO()
    {
        //arrange
        DeleteEmailInDto dto = new DeleteEmailInDto();
        dto.setEmailToDelete("tiago@gmail.com");
        //act
        boolean result = dto.equals(dto);
        //assert
        assertTrue(result);
    }

    @Test
    void testEqualsForDifferentObjects()
    {
        //arrange
        DeleteEmailInDto dto = new DeleteEmailInDto();
        dto.setEmailToDelete("tiago@gmail.com");
        double value = 10;
        //act
        boolean result = dto.equals(value);
        //assert
        assertFalse(result);
    }


    @Test
    void testEqualsForDifferentDTOS()
    {
        //arrange
        DeleteEmailInDto dto = new DeleteEmailInDto("tiago@gmail.com");
        DeleteEmailInDto secondDto = new DeleteEmailInDto("tiago2@gmail.com");
        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }


    @Test
    void testEqualsForSimilarDTOS()
    {
        //arrange
        DeleteEmailInDto dto = new DeleteEmailInDto("tiago@gmail.com");
        DeleteEmailInDto secondDto = new DeleteEmailInDto("tiago@gmail.com");
        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertTrue(result);
    }

    @Test
    void testHashCodeForSimilarDTOS()
    {
        //arrange
        DeleteEmailInDto dto = new DeleteEmailInDto("tiago@gmail.com");
        DeleteEmailInDto secondDto = new DeleteEmailInDto("tiago@gmail.com");
        //act
        int hashCodeDto = dto.hashCode();
        int hashCodeSecondDto = secondDto.hashCode();
        //assert
        assertEquals(hashCodeDto,hashCodeSecondDto);
    }

    @Test
    void testHashCodeForDifferentDTOS()
    {
        //arrange
        DeleteEmailInDto dto = new DeleteEmailInDto("tiago@gmail.com");
        DeleteEmailInDto secondDto = new DeleteEmailInDto("tiago4@gmail.com");
        //act
        int hashCodeDto = dto.hashCode();
        int hashCodeSecondDto = secondDto.hashCode();
        //assert
        assertNotEquals(hashCodeDto,hashCodeSecondDto);
    }












}