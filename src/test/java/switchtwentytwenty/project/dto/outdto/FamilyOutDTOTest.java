package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FamilyOutDTOTest {

    @Test
    void getName() {
        //arrange
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";

        FamilyOutDTO dto = new FamilyOutDTO(name, familyID );
        //act

        String result = dto.getName();

        //assert
        assertEquals(name,result);


    }

    @Test
    void getFamilyID() {
        //arrange
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";

        FamilyOutDTO dto = new FamilyOutDTO(name, familyID );
        //act

        String result = dto.getFamilyID();

        //assert
        assertEquals(familyID,result);

    }

    @Test
    void testEqualsTrueTwoIdenticalDTOS() {
        //arrange
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";

        FamilyOutDTO dto = new FamilyOutDTO(name, familyID );
        FamilyOutDTO dto2 = new FamilyOutDTO(name, familyID );
        //act

        boolean result = dto.equals(dto2);

        //assert
        assertTrue(result);

    }
    @Test
    void testSameDTO() {
        //arrange
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";

        FamilyOutDTO dto = new FamilyOutDTO(name, familyID );
        //act

        boolean result = dto.equals(dto);

        //assert
        assertTrue(result);

    }

    @Test
    void testEqualsTrueTwoDifferentDTOS() {
        //arrange
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";

        String familyID2 = UUID.randomUUID().toString();
        String name2 = "fonseca";

        FamilyOutDTO dto = new FamilyOutDTO(name, familyID );
        FamilyOutDTO dto2 = new FamilyOutDTO(name2, familyID2 );
        //act

        boolean result = dto.equals(dto2);

        //assert
        assertFalse(result);

    }

    @Test
    void testEqualsTrueTwoDifferentFamilyIdDTOS() {

        //arrange
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";

        String familyID2 = UUID.randomUUID().toString();


        FamilyOutDTO dto = new FamilyOutDTO(name, familyID );
        FamilyOutDTO dto2 = new FamilyOutDTO(name, familyID2 );
        //act

        boolean result = dto.equals(dto2);

        //assert
        assertFalse(result);

    }
    @Test
    void testEqualsTrueTwoDifferentNameDTOS() {

        //arrange
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";
        String name2= "Mariana";


        FamilyOutDTO dto = new FamilyOutDTO(name, familyID );
        FamilyOutDTO dto2 = new FamilyOutDTO(name2, familyID );
        //act

        boolean result = dto.equals(dto2);

        //assert
        assertFalse(result);

    }

    @Test
    @DisplayName("Test null - successfully")
    void equalsNullTest() {

        //arrange
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";

        FamilyOutDTO dto = new FamilyOutDTO(name, familyID);
        //act
        boolean result = dto.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals different object")
    void equalDifferentObject() {

        //arrange
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";
        FamilyOutDTO dto = new FamilyOutDTO(name, familyID);
        AccountDesignation accountDesignation =new AccountDesignation("My Account");
        //act
        boolean result = dto.equals(accountDesignation);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same hash code")
    void sameHashCode() {
        //arrange
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";
        FamilyOutDTO dto = new FamilyOutDTO(name, familyID);
        FamilyOutDTO sameDto = new FamilyOutDTO(name, familyID);

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
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";
        String familyID2 = UUID.randomUUID().toString();
        String name2 = "constantino";
        FamilyOutDTO dto = new FamilyOutDTO(name, familyID);
        FamilyOutDTO differentDto = new FamilyOutDTO(name2, familyID2);

        //act
        int hash1 = dto.hashCode();
        int hash2 = differentDto.hashCode();

        //assert
        assertNotEquals(hash1, hash2);
    }
}

