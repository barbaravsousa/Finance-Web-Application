package switchtwentytwenty.project.domain.share.id;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CategoryIDTest {

    @Test
    @DisplayName("Both objects have the same value")
    void testBothObjectsHaveTheSameValue() {
        //arrange
        boolean result;
        String id = UUID.randomUUID().toString();
        CategoryID categoryID1 = new CategoryID(id);
        CategoryID categoryID2 = new CategoryID(id);
        //act
        result = categoryID1.equals(categoryID2);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Both objects have the same value: equals")
    void testBothObjectsHaveTheSameValueEquals() {
        //arrange
        boolean result;
        String id = UUID.randomUUID().toString();
        CategoryID categoryID1 = new CategoryID(id);
        CategoryID categoryID2 = new CategoryID(id);
        //act
        result = categoryID1.equals(categoryID2);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("The objects dont have the same value")
    void testObjectWithDifferentValues() {
        //arrange
        boolean result;
        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        CategoryID categoryID1 = new CategoryID(id1);
        CategoryID categoryID2 = new CategoryID(id2);
        //act
        result = categoryID1.equals(categoryID2);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same hash code")
    void testSameHashCode() {
        //arrange
        int hashCode1;
        int hashCode2;
        String id1 = UUID.randomUUID().toString();
        String id2 = id1;
        CategoryID categoryID1 = new CategoryID(id1);
        CategoryID categoryID2 = new CategoryID(id2);
        //act
        hashCode1 = categoryID1.hashCode();
        hashCode2 = categoryID2.hashCode();
        //assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Not same hash code")
    void testNotSameHashCode() {
        //arrange
        int hashCode1;
        int hashCode2;
        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        CategoryID categoryID1 = new CategoryID(id1);
        CategoryID categoryID2 = new CategoryID(id2);
        //act
        hashCode1 = categoryID1.hashCode();
        hashCode2 = categoryID2.hashCode();
        //assert
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Equal method: different objects")
    void testNotSameObject() {
        //arrange
        String id = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(id);
        CategoryDesignation categoryDesignation = new CategoryDesignation("I am hungry");
        //act

        //assert
        assertNotEquals(categoryID, categoryDesignation);
    }

    @Test
    @DisplayName("Equal method: same objects")
    void testSameObject() {
        //arrange
        String id = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(id);
        //act

        //assert
        assertEquals(categoryID, categoryID);
    }
}