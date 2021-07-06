package switchtwentytwenty.project.domain.share.id;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FamilyIDTest {

    @Test
    @DisplayName("Both objects have the same value")
    void bothObjectsHaveTheSameValue() {
        //arrange
        boolean result;
        UUID id = UUID.randomUUID();
        FamilyID familyID1 = new FamilyID(id);
        FamilyID familyID2 = new FamilyID(id);
        //act
        result = familyID1.equals(familyID2);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Both objects have the same value: equals")
    void bothObjectsHaveTheSameValueEquals() {
        //arrange
        boolean result;
        UUID id = UUID.randomUUID();
        FamilyID familyID1 = new FamilyID(id);
        FamilyID familyID2 = new FamilyID(id);
        //act
        result = familyID1.equals(familyID2);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("The objects dont have the same value")
    void objectWithDifferentValues() {
        //arrange
        boolean result;
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        FamilyID familyID1 = new FamilyID(id1);
        FamilyID familyID2 = new FamilyID(id2);
        //act
        result = familyID1.equals(familyID2);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Invalid constructor argument: null")
    void invalidConstructorArgumentNull() {
        //arrange
        //act and assert
        assertThrows(NullPointerException.class, () -> {
            new FamilyID(null);
        });
    }

    @Test
    @DisplayName("Same hash code")
    void sameHashCode() {
        //arrange
        int hashCode1;
        int hashCode2;
        UUID id1 = UUID.randomUUID();
        UUID id2 = id1;
        FamilyID familyID1 = new FamilyID(id1);
        FamilyID familyID2 = new FamilyID(id2);
        //act
        hashCode1 = familyID1.hashCode();
        hashCode2 = familyID2.hashCode();
        //assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Not same hash code")
    void NotSameHashCode() {
        //arrange
        int hashCode1;
        int hashCode2;
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        FamilyID familyID1 = new FamilyID(id1);
        FamilyID familyID2 = new FamilyID(id2);
        //act
        hashCode1 = familyID1.hashCode();
        hashCode2 = familyID2.hashCode();
        //assert
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Compare FamilyId with null")
    void compareInstanceWithNull() {
        //arrange
        boolean result;
        UUID id1 = UUID.randomUUID();
        FamilyID familyID1 = new FamilyID(id1);
        FamilyID familyID2 = null;
        //act
        result = familyID1.equals(familyID2);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Compare FamilyId with different class")
    void compareInstanceWithDifferentClass() {
        //arrange
        boolean result;
        UUID id1 = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id1);
        FamilyName familyName = new FamilyName("Turing");
        //act
        result = familyID.equals(familyName);
        //assert
        assertFalse(result);
    }
}