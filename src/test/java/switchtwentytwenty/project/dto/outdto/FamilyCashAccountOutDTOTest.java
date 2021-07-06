package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FamilyCashAccountOutDTOTest {

    @Test
    void getBalance() {
        //arrange
        String designation = "football match";
        String balance = "25";
        FamilyCashAccountOutDTO dto = new FamilyCashAccountOutDTO(designation, balance);
        //act
        String result = dto.getAccountDesignation();
        //assert
        assertEquals(designation, result);

    }


    @Test
    void getAccountDesignation() {
        //arrange
        String designation = "football match";
        String balance = "25";
        FamilyCashAccountOutDTO dto = new FamilyCashAccountOutDTO(designation, balance);
        //act
        String result = dto.getBalance();
        //assert
        assertEquals(balance, result);


    }

    @Test
    @DisplayName("Same object")
    void sameObject() {
        //arrange
        String designation = "football match";
        String accountID = UUID.randomUUID().toString();
        FamilyCashAccountOutDTO dto = new FamilyCashAccountOutDTO(accountID, designation);

        //act
        FamilyCashAccountOutDTO sameDto = dto;

        //assert
        assertEquals(dto, sameDto);
    }

    @Test
    @DisplayName("Identical object")
    void identicalObject() {
        //arrange
        String designation = "football match";
        String accountID = UUID.randomUUID().toString();
        FamilyCashAccountOutDTO dto = new FamilyCashAccountOutDTO(accountID, designation);

        //act
        FamilyCashAccountOutDTO sameDto = new FamilyCashAccountOutDTO(accountID, designation);

        //assert
        assertEquals(dto, sameDto);
    }

    @Test
    @DisplayName("Identical object")
    void differentObject() {
        //arrange
        String designation = "football match";
        String designation2 = "football match";
        String accountID = UUID.randomUUID().toString();
        String accountID2 = UUID.randomUUID().toString();
        FamilyCashAccountOutDTO dto = new FamilyCashAccountOutDTO(accountID, designation);

        //act
        FamilyCashAccountOutDTO differentDto = new FamilyCashAccountOutDTO(accountID2, designation2);

        //assert
        assertNotEquals(dto, differentDto);
    }

    @Test
    @DisplayName("Test equals with different dtos - successfully")
    void equalsDifferentTest() {

        //arrange
        String designation = "football match";
        String designation2 = "football match";
        String accountID = UUID.randomUUID().toString();
        String accountID2 = UUID.randomUUID().toString();

        FamilyCashAccountOutDTO dto = new FamilyCashAccountOutDTO(accountID, designation);
        FamilyCashAccountOutDTO differentDto = new FamilyCashAccountOutDTO(accountID2, designation2);
        //act
        boolean result = dto.equals(differentDto);
        //assert
        assertFalse(result);
    }
    @Test
    @DisplayName("Test equals with same dtos - successfully")
    void equalsSameDTOsTest() {

        //arrange
        String designation = "football match";
        String accountID = UUID.randomUUID().toString();

        FamilyCashAccountOutDTO dto = new FamilyCashAccountOutDTO(accountID, designation);

        //act
        boolean result = dto.equals(dto);
        //assert
        assertTrue(result);
    }
    @Test
    @DisplayName("Test equals with different dtos - successfully")
    void equalsNullTest() {

        //arrange
        String designation = "football match";
        String accountID = UUID.randomUUID().toString();

        FamilyCashAccountOutDTO dto = new FamilyCashAccountOutDTO(accountID, designation);
        //act
        boolean result = dto.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals with different dtos - different designation")
    void equalsDifferentDesignation() {

        //arrange
        String designation = "My Account";
        String designation2 = "My Magnificent Account";
        String balance = "50";
        FamilyCashAccountOutDTO dto = new FamilyCashAccountOutDTO(designation, balance);
        FamilyCashAccountOutDTO dto2 = new FamilyCashAccountOutDTO(designation2, balance);
        //act
        boolean result = dto.equals(dto2);
        //assert
        assertFalse(result);
    }
    @Test
    @DisplayName("Test equals with different dtos - different balance")
    void equalsDifferentBalance() {

        //arrange
        String designation = "My Account";
        String balance = "50";
        String balance2 = "100";
        FamilyCashAccountOutDTO dto = new FamilyCashAccountOutDTO(designation, balance);
        FamilyCashAccountOutDTO dto2 = new FamilyCashAccountOutDTO(designation, balance2);
        //act
        boolean result = dto.equals(dto2);
        //assert
        assertFalse(result);
    }


    @Test
    @DisplayName("Same hash code")
    void sameHashCode() {
        //arrange
        String designation = "football match";
        String accountID = UUID.randomUUID().toString();
        FamilyCashAccountOutDTO dto = new FamilyCashAccountOutDTO(accountID, designation);
        FamilyCashAccountOutDTO sameDto = new FamilyCashAccountOutDTO(accountID, designation);

        //act
        int hashCode1 = dto.hashCode();
        int hashCode2 = sameDto.hashCode();

        //assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Different hash codes")
    void differentHashCodes() {
        //arrange
        String designation = "football match";
        String designation2 = "football match";
        String accountID = UUID.randomUUID().toString();
        String accountID2 = UUID.randomUUID().toString();
        FamilyCashAccountOutDTO dto = new FamilyCashAccountOutDTO(accountID, designation);
        FamilyCashAccountOutDTO otherDto = new FamilyCashAccountOutDTO(accountID2, designation2);

        //act
        int hash1 = dto.hashCode();
        int hash2 = otherDto.hashCode();

        //assert
        assertNotEquals(hash1, hash2);
    }
}


