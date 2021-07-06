package switchtwentytwenty.project.domain.share.familydata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.share.id.FamilyID;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FamilyNameTest {

    @Test
    @DisplayName("Both objects have the same value")
    void bothObjectsHaveTheSameValue() {
        //arrange
        boolean result;
        String name = "Turing";
        FamilyName familyName1 = new FamilyName(name);
        FamilyName familyName2 = new FamilyName(name);
        //act
        result = familyName1.equals(familyName2);
        //assert
        assertTrue(result);
        assertEquals(familyName1, familyName2);
    }

    @Test
    @DisplayName("The objects dont have the same value")
    void objectWithDifferentValues() {
        //arrange
        boolean result;
        String name1 = "Turing";
        String name2 = "Neumann";
        FamilyName familyName1 = new FamilyName(name1);
        FamilyName familyName2 = new FamilyName(name2);
        //act
        result = familyName1.equals(familyName2);
        //assert
        assertFalse(result);
        assertNotEquals(familyName1, familyName2);
    }

    @Test
    @DisplayName("Invalid constructor argument: null")
    void invalidConstructorArgumentsNull() {
        //arrange
        String name = null;
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new FamilyName(name);
        });
    }

    @Test
    @DisplayName("Invalid constructor argument: empty")
    void invalidConstructorArgumentsEmpty() {
        //arrange
        String name = "";
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new FamilyName(name);
        });
    }

    @Test
    @DisplayName("Invalid constructor argument: just spaces")
    void invalidConstructorArgumentsJustSpaces() {
        //arrange
        String name = "     ";
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new FamilyName(name);
        });
    }

    @Test
    @DisplayName("Invalid name: special characters")
    void invalidNameSpecialCharacters() {
        //arrange
        String name = "Turing!";
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new FamilyName(name);
        });
    }

    @Test
    @DisplayName("Invalid name: numbers")
    void invalidNameNumbers() {
        //arrange
        String name = "Tur1ng";
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            new FamilyName(name);
        });
    }

    @Test
    @DisplayName("Valid name: apostrophes")
    void validNameApostrophes() {
        //arrange
        String result;
        String name = "O'Turing";
        FamilyName familyName;
        //act
        familyName = new FamilyName(name);
        result = familyName.toString();
        //assert
        assertEquals(name, result);
    }

    @Test
    @DisplayName("Valid name: accents")
    void validNameAccents() {
        //arrange
        String result;
        String name = "Türing";
        FamilyName familyName;
        //act
        familyName = new FamilyName(name);
        result = familyName.toString();
        //assert
        assertEquals(name, result);
    }

    @Test
    @DisplayName("Same hash code")
    void sameHashCode() {
        //arrange
        int hashCode1;
        int hashCode2;
        String name1 = "Turing";
        String name2 = "Turing";
        FamilyName familyName1 = new FamilyName(name1);
        FamilyName familyName2 = new FamilyName(name2);
        //act
        hashCode1 = familyName1.hashCode();
        hashCode2 = familyName2.hashCode();
        //assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Not same hash code")
    void notSameHashCode() {
        //arrange
        int hashCode1;
        int hashCode2;
        String name1 = "Turing";
        String name2 = "Neumann";
        FamilyName familyName1 = new FamilyName(name1);
        FamilyName familyName2 = new FamilyName(name2);
        //act
        hashCode1 = familyName1.hashCode();
        hashCode2 = familyName2.hashCode();
        //assert
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Compare same objects")
    void compareEqualObjects() {
        //arrange
        String name = "Türing";
        FamilyName familyName1;
        FamilyName familyName2;
        //act
        familyName1 = new FamilyName(name);
        familyName2 = familyName1;
        //assert
        assertEquals(familyName1, familyName2);
    }

    @Test
    @DisplayName("Compare object with null")
    void compareObjectWithNull() {
        //arrange
        String name = "Türing";
        FamilyName familyName1;
        FamilyName familyName2;
        //act
        familyName1 = new FamilyName(name);
        familyName2 = null;
        //assert
        assertNotEquals(familyName1, familyName2);
    }

    @Test
    @DisplayName("Compare object with different instance")
    void compareObjectWithDifferentInstance() {
        //arrange
        String name = "Türing";
        UUID id = UUID.randomUUID();
        FamilyName familyName;
        FamilyID familyID;
        //act
        familyName = new FamilyName(name);
        familyID = new FamilyID(id);
        //assert
        assertNotEquals(familyName, familyID);
    }
}