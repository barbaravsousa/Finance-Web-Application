package switchtwentytwenty.project.dto.indto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AddEmailInDTOTest {

    @Test
    @DisplayName("Test dto and lombok getters  - successful case")
    void createDTOandGets() {
        //arrange

        String emailToAddExpected = "tiagoSecond@gmail.com";

        //act
        AddEmailInDTO dto = new AddEmailInDTO();
        dto.setEmailToAdd("tiagoSecond@gmail.com");

        String emailToaddResult = dto.getEmailToAdd();

        //assert
        assertNotNull(dto);

        assertEquals(emailToaddResult, emailToAddExpected);

    }

    @Test
    @DisplayName("Test equals  - successful case")
    void testEquals() {
        //arrange
        AddEmailInDTO dto = new AddEmailInDTO("tiago@gmail.com");
        AddEmailInDTO secondDto = new AddEmailInDTO("tiago@gmail.com");

        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test equals  - successful case")
    void testEqualsWithSameDTO() {
        //arrange
        AddEmailInDTO dto = new AddEmailInDTO("tiago@gmail.com");
        //act
        boolean result = dto.equals(dto);
        //assert
        assertTrue(result);
    }


    @Test
    @DisplayName("Test equals with different person  - Unsuccessful case")
    void testDiferentDtos() {
        AddEmailInDTO dto = new AddEmailInDTO("tiago@gmail.com");

        AddEmailInDTO secondDto = new AddEmailInDTO("tiago2@gmail.com");

        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals with different person  - Unsuccessful case")
    void testDiferentPersonIdOnDtos() {
        AddEmailInDTO dto = new AddEmailInDTO("tiago@gmail.com");

        AddEmailInDTO secondDto = new AddEmailInDTO("tiagoTT@gmail.com");

        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals with a null  - Unsuccessful case")
    void testEqualsOnNullDto() {
        AddEmailInDTO dto = new AddEmailInDTO("tiago@gmail.com");
        //act
        boolean result = dto.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals on diferent objects  - successful case")
    void testEqualsOnADifferentObject() {
        //arrange
        AddEmailInDTO dto = new AddEmailInDTO("tiago@gmail.com");

        BigDecimal n = new BigDecimal(12);

        //act
        boolean result = dto.equals(n);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test same hash code case  - successful case")
    void testHashCodeOnDtos() {
        AddEmailInDTO dto = new AddEmailInDTO("tiago@gmail.com");

        AddEmailInDTO secondDto = new AddEmailInDTO("tiago@gmail.com");
        //act
        int dtoHashCode = dto.hashCode();
        int secondDtoHashCode = secondDto.hashCode();
        //assert
        assertEquals(dtoHashCode, secondDtoHashCode);
    }

    @Test
    @DisplayName("Test same hash code case  - Unsuccessful case")
    void testDiferentHashCodeOnDtos() {
        //arrange
        AddEmailInDTO dto = new AddEmailInDTO("tiago@gmail.com");
        AddEmailInDTO secondDto = new AddEmailInDTO("tiagoTT@gmail.com");
        //act
        int dtoHashCode = dto.hashCode();
        int secondDtoHashCode = secondDto.hashCode();
        //assert
        assertNotEquals(dtoHashCode, secondDtoHashCode);
    }


}