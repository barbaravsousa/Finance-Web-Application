package switchtwentytwenty.project.dto.outdto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;

import java.math.BigDecimal;
import java.util.UUID;

public class FamilyRelationOutDTOTest {

    @Test
    @DisplayName("Test getters")
    void testGetters() {
        //arrange
        String personID = UUID.randomUUID().toString();
        String kinID = UUID.randomUUID().toString();
        String relationType = Constants.CHILD;

        FamilyRelationOutDTO dto = new FamilyRelationOutDTO(personID, kinID, relationType);
        //act

        String personIDResult = dto.getPersonID();
        String kinIDResult = dto.getKinID();
        String relationTypeResult = dto.getRelationType();

        //assert
        assertEquals(personID, personIDResult);
        assertEquals(kinID, kinIDResult);
        assertEquals(relationType, relationTypeResult);
    }

    @Test
    @DisplayName("Same object")
    void sameObject() {
        //arrange
        String personID = "alan_turing@gmail.com";
        String kinID = "margarte_hamilton@hotmail.com";
        String relationType = Constants.CHILD;
        FamilyRelationOutDTO dto = new FamilyRelationOutDTO(personID, kinID, relationType);

        //act
        FamilyRelationOutDTO sameDto = dto;

        //assert
        assertEquals(dto, sameDto);
    }

    @Test
    @DisplayName("Identical object")
    void identicalObject() {
        //arrange
        String personID = "alan_turing@gmail.com";
        String kinID = "margarte_hamilton@hotmail.com";
        String relationType = Constants.CHILD;
        FamilyRelationOutDTO dto = new FamilyRelationOutDTO(personID, kinID, relationType);

        //act
        FamilyRelationOutDTO sameDto = new FamilyRelationOutDTO(personID, kinID, relationType);

        //assert
        assertEquals(dto, sameDto);
    }

    @Test
    @DisplayName("Different classes")
    void differentClasses() {
        //arrange
        String personID = "alan_turing@gmail.com";
        String kinID = "margarte_hamilton@hotmail.com";
        String relationType = Constants.CHILD;
        FamilyRelationOutDTO dto = new FamilyRelationOutDTO(personID, kinID, relationType);

        //act
        BigDecimal bigDecimal = BigDecimal.ONE;
        boolean result = dto.equals(bigDecimal);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Null object")
    void nullObject() {
        //arrange
        String personID = "alan_turing@gmail.com";
        String kinID = "margarte_hamilton@hotmail.com";
        String relationType = Constants.CHILD;
        FamilyRelationOutDTO dto = new FamilyRelationOutDTO(personID, kinID, relationType);

        //act
        FamilyRelationOutDTO otherDto = null;
        boolean result = dto.equals(otherDto);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Different objects")
    void differentObjects() {
        //arrange
        String personID = "alan_turing@gmail.com";
        String kinID = "margarte_hamilton@hotmail.com";
        String otherKinID = "john_newmann@hotmail.com";
        String relationType = Constants.CHILD;
        FamilyRelationOutDTO dto = new FamilyRelationOutDTO(personID, kinID, relationType);

        //act
        FamilyRelationOutDTO otherDto = new FamilyRelationOutDTO(personID, otherKinID, relationType);
        boolean result = dto.equals(otherDto);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Identical object but different relation")
    void identicalObjectButDifferentRelation() {
        //arrange
        String personID = "alan_turing@gmail.com";
        String kinID = "margarte_hamilton@hotmail.com";
        String relationType = Constants.CHILD;
        String otherRelationType = Constants.PARTNER;
        FamilyRelationOutDTO dto = new FamilyRelationOutDTO(personID, kinID, relationType);

        //act
        FamilyRelationOutDTO sameDto = new FamilyRelationOutDTO(personID, kinID, otherRelationType);

        //assert
        assertEquals(dto, sameDto);
    }

    @Test
    @DisplayName("Same hash code")
    void sameHashCode() {
        //arrange
        String personID = "alan_turing@gmail.com";
        String kinID = "margarte_hamilton@hotmail.com";
        String relationType = Constants.CHILD;
        FamilyRelationOutDTO dto = new FamilyRelationOutDTO(personID, kinID, relationType);
        FamilyRelationOutDTO sameDto = new FamilyRelationOutDTO(personID, kinID, relationType);

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
        String personID = "alan_turing@gmail.com";
        String kinID = "margarte_hamilton@hotmail.com";
        String otherKinID = "john_newmann@hotmail.com";
        String relationType = Constants.CHILD;
        FamilyRelationOutDTO dto = new FamilyRelationOutDTO(personID, kinID, relationType);
        FamilyRelationOutDTO otherDto = new FamilyRelationOutDTO(personID, otherKinID, relationType);

        //act
        int hash1 = dto.hashCode();
        int hash2 = otherDto.hashCode();

        //assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Test equals with null")
    void testEqualsWithNull() {
        //arrange
        String personID = "alan_turing@gmail.com";
        String kinID = "margarte_hamilton@hotmail.com";
        String relationType = Constants.CHILD;
        FamilyRelationOutDTO dto = new FamilyRelationOutDTO(personID, kinID, relationType);

        //act
        boolean result = dto.equals(null);

        //assert
        assertFalse(result);

    }

    @Test
    @DisplayName("Different Equals with different objects")
    void testEqualsWithDifferentObjects() {
        //arrange
        String personID = "alan_turing@gmail.com";
        String kinID = "margarte_hamilton@hotmail.com";
        String relationType = Constants.CHILD;
        FamilyRelationOutDTO dto = new FamilyRelationOutDTO(personID, kinID, relationType);

        BigDecimal number = new BigDecimal(10);

        //act
        boolean result = dto.equals(number);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Different Equals with different personId")
    void testEqualsWithDifferentPersonID() {
        //arrange
        String personID = "alan_turing@gmail.com";
        String kinID = "margarte_hamilton@hotmail.com";
        String relationType = Constants.CHILD;
        FamilyRelationOutDTO dto = new FamilyRelationOutDTO(personID, kinID, relationType);
        String secondPersonID = "alanturing@gmail.com";
        FamilyRelationOutDTO secondDto = new FamilyRelationOutDTO(secondPersonID, kinID, relationType);

        //act
        boolean result = dto.equals(secondDto);

        //assert
        assertFalse(result);
    }

}
