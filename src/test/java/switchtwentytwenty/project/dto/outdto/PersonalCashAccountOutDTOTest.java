package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PersonalCashAccountOutDTOTest {

    @Test
    void getAccountDesignation() {
        //arrange
        String designation = "football match";
        String balance = "25";
        String accountID = UUID.randomUUID().toString();
        PersonalCashAccountOutDTO dto = new PersonalCashAccountOutDTO(accountID, designation, balance);
        //act
        String result = dto.getAccountDesignation();
        //assert
        assertEquals(designation, result);

    }
    @Test
    void getAccountID() {
        //arrange
        String designation = "My account";
        String balance = "25";
        String accountID = UUID.randomUUID().toString();
        PersonalCashAccountOutDTO dto = new PersonalCashAccountOutDTO(accountID, designation, balance);
        //act
        String result = dto.getAccountID();
        //assert
        assertEquals(accountID, result);

    }

    @Test
    void getAccountBalance() {
        //arrange
        String designation = "football match";
        String balance = "25";
        String accountID = UUID.randomUUID().toString();
        PersonalCashAccountOutDTO dto = new PersonalCashAccountOutDTO(accountID, designation, balance);
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
        String balance = "25";
        String accountID = UUID.randomUUID().toString();
        PersonalCashAccountOutDTO dto = new PersonalCashAccountOutDTO(accountID, designation, balance);

        //act
        PersonalCashAccountOutDTO sameDto = dto;

        //assert
        assertEquals(dto, sameDto);
    }

    @Test
    @DisplayName("Identical object")
    void identicalObject() {
        //arrange
        String designation = "football match";
        String balance = "25";
        String accountID = UUID.randomUUID().toString();
        PersonalCashAccountOutDTO dto = new PersonalCashAccountOutDTO(accountID, designation, balance);

        //act
        PersonalCashAccountOutDTO sameDto = new PersonalCashAccountOutDTO(accountID, designation, balance);

        //assert
        assertEquals(dto, sameDto);
    }

    @Test
    @DisplayName("Identical object")
    void differentObject() {
        //arrange
        String designation = "football match";
        String designation2 = "football match";
        String balance = "25";
        String balance2 = "30";
        String accountID = UUID.randomUUID().toString();
        String accountID2 = UUID.randomUUID().toString();
        PersonalCashAccountOutDTO dto = new PersonalCashAccountOutDTO(accountID, designation, balance);

        //act
        PersonalCashAccountOutDTO differentDto = new PersonalCashAccountOutDTO(accountID2, designation2, balance2);

        //assert
        assertNotEquals(dto, differentDto);
    }

    @Test
    @DisplayName("Test equals with different dtos - successfully")
    void equalsDifferentTest() {

        //arrange
        String designation = "football match";
        String designation2 = "football match";
        String balance = "25";
        String balance2 = "30";
        String accountID = UUID.randomUUID().toString();
        String accountID2 = UUID.randomUUID().toString();

        PersonalCashAccountOutDTO dto = new PersonalCashAccountOutDTO(accountID, designation, balance);
        PersonalCashAccountOutDTO differentDto = new PersonalCashAccountOutDTO(accountID2, designation2, balance2);
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
        String balance = "25";
        String accountID = UUID.randomUUID().toString();

        PersonalCashAccountOutDTO dto = new PersonalCashAccountOutDTO(accountID, designation, balance);

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
        String balance = "25";
        String accountID = UUID.randomUUID().toString();

        PersonalCashAccountOutDTO dto = new PersonalCashAccountOutDTO(accountID, designation, balance);
        //act
        boolean result = dto.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same hash code")
    void sameHashCode() {
        //arrange
        String designation = "football match";
        String balance = "25";
        String accountID = UUID.randomUUID().toString();
        PersonalCashAccountOutDTO dto = new PersonalCashAccountOutDTO(accountID, designation, balance);
        PersonalCashAccountOutDTO sameDto = new PersonalCashAccountOutDTO(accountID, designation, balance);

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
        String balance = "25";
        String balance2 = "30";
        String accountID = UUID.randomUUID().toString();
        String accountID2 = UUID.randomUUID().toString();
        PersonalCashAccountOutDTO dto = new PersonalCashAccountOutDTO(accountID, designation, balance);
        PersonalCashAccountOutDTO otherDto = new PersonalCashAccountOutDTO(accountID2, designation2, balance2);

        //act
        int hash1 = dto.hashCode();
        int hash2 = otherDto.hashCode();

        //assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Test equals different account ID")
    void equalsDifferentAccountIds() {

        //arrange
        String designation = "My account";
        String balance = "25";
        String accountID = UUID.randomUUID().toString();
        String accountID2 = UUID.randomUUID().toString();

        PersonalCashAccountOutDTO dto = new PersonalCashAccountOutDTO(accountID, designation, balance);
        PersonalCashAccountOutDTO dto2 = new PersonalCashAccountOutDTO(accountID2, designation, balance);
        //act
        boolean result = dto.equals(dto2);
        //assert
        assertFalse(result);
    }
    @Test
    @DisplayName("Test equals different account ID")
    void equalsDifferentDesignations() {

        //arrange
        String designation = "My account";
        String designation2 = "My secondary account";
        String balance = "25";
        String accountID = UUID.randomUUID().toString();

        PersonalCashAccountOutDTO dto = new PersonalCashAccountOutDTO(accountID, designation, balance);
        PersonalCashAccountOutDTO dto2 = new PersonalCashAccountOutDTO(accountID, designation2, balance);
        //act
        boolean result = dto.equals(dto2);
        //assert
        assertFalse(result);
    }
    @Test
    @DisplayName("Test equals different account ID")
    void equalsDifferentBalances() {

        //arrange
        String designation = "My account";
        String balance = "25";
        String balance2 = "95";
        String accountID = UUID.randomUUID().toString();

        PersonalCashAccountOutDTO dto = new PersonalCashAccountOutDTO(accountID, designation, balance);
        PersonalCashAccountOutDTO dto2 = new PersonalCashAccountOutDTO(accountID, designation, balance2);
        //act
        boolean result = dto.equals(dto2);
        //assert
        assertFalse(result);
    }
}
