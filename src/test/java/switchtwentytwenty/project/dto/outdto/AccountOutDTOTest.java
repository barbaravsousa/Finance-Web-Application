package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountOutDTOTest {

    @Test
    @DisplayName("Create a valid AccountOutDTO")
    void createOutAccountDto() {
        //arrange
        AccountOutDTO dto = new AccountOutDTO("jac5","cashAccount");
        // act
        //assert
        assertNotNull(dto);
    }

    @Test
    @DisplayName("Test get account ID")
    void getAccountID() {
        //arrange
        AccountOutDTO dto = new AccountOutDTO("jac5","cashAccount");
        String expected = "jac5";
        // act
        String result = dto.getAccountID();
        //assert
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Test get account ID")
    void getDesignation() {
        //arrange
        AccountOutDTO dto = new AccountOutDTO("jac5","cashAccount");
        String expected = "cashAccount";
        // act
        String result = dto.getDesignation();
        //assert
        assertEquals(expected,result);
    }


    @Test
    @DisplayName("Test Equals for the same dto")
    void testEqualsForTheSameDTO() {
        //arrange
        AccountOutDTO dto = new AccountOutDTO("jac5","cashAccount");
        // act
        boolean result = dto.equals(dto);
        //assert
        assertTrue(result);
    }


    @Test
    @DisplayName("Test Equals for different objects ")
    void testEqualsForDiferentObjects() {
        //arrange
        AccountOutDTO dto = new AccountOutDTO("jac5","cashAccount");
        BigDecimal numbers = new BigDecimal(10);
        // act
        boolean result = dto.equals(numbers);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals for different objects ")
    void testEqualsForDiferentDtos() {
        //arrange
        AccountOutDTO dto = new AccountOutDTO("jac5","cashAccount");
        AccountOutDTO secondDto = new AccountOutDTO("jac","cashAccount");
        // act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals for different objects ")
    void testEqualsForDiferentDesignation() {
        //arrange
        AccountOutDTO dto = new AccountOutDTO("jac5","cashAccount");
        AccountOutDTO secondDto = new AccountOutDTO("jac5","cash");
        // act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals for similar dtos ")
    void testEqualsForSimilarDtos() {
        //arrange
        AccountOutDTO dto = new AccountOutDTO("jac5","cashAccount");
        AccountOutDTO secondDto = new AccountOutDTO("jac5","cashAccount");
        // act
        boolean result = dto.equals(secondDto);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test Hash Codes for similar dtos ")
    void testHashCodeForSimilarDtos() {
        //arrange
        AccountOutDTO dto = new AccountOutDTO("jac5","cashAccount");
        AccountOutDTO secondDto = new AccountOutDTO("jac5","cashAccount");
        // act
        int firstHashCode = dto.hashCode();
        int secondHashCode = secondDto.hashCode();
        //assert
        assertEquals(firstHashCode,secondHashCode);
    }

    @Test
    @DisplayName("Test Hash Codes for different dtos ")
    void testHashCodeForDifferentDtos() {
        //arrange
        AccountOutDTO dto = new AccountOutDTO("jac53","cashAccount");
        AccountOutDTO secondDto = new AccountOutDTO("jac5","cashAccount");
        // act
        int firstHashCode = dto.hashCode();
        int secondHashCode = secondDto.hashCode();
        //assert
        assertNotEquals(firstHashCode,secondHashCode);
    }













}