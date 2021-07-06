package switchtwentytwenty.project.domain.share.designation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.id.CategoryID;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDesignationTest {

    @Test
    @DisplayName("Create a designation successfully")
    void createDesignationSuccessfully() {
        //arrange
        String initialDesignation = "Food";

        //act
        Designation designation = new CategoryDesignation(initialDesignation);

        //assert
        assertNotNull(designation);
    }

    @Test
    @DisplayName("Create a designation successfully excluding spaces")
    void createDesignationSuccessfullyExcludingSpaces() {
        //arrange
        String initialDesignation = "      Food    ";

        //act
        Designation designation = new CategoryDesignation(initialDesignation);

        //assert
        assertNotNull(designation);
    }

    @Test
    @DisplayName("Create a designation successfully with more than one word")
    void createDesignationSuccessfullyWithMoreThanOneWord() {
        //arrange
        String initialDesignation = "Fast  Food";

        //act
        Designation designation = new CategoryDesignation(initialDesignation);

        //assert
        assertNotNull(designation);
    }

    @Test
    @DisplayName("Create a designation with invalid characters")
    void createDesignationWithInvalidCharacters() {
        //arrange
        String designation = "Food@Mac";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new CategoryDesignation(designation));

        //assert
        String exceptionMessage = exception.getMessage();
        String expectedMessage = "Numeric characters and special characters are not allowed";
        assertEquals(exceptionMessage, expectedMessage);
    }

    @Test
    @DisplayName("Create a null designation")
    void createNullDesignation() {
        //arrange
        String designation = null;

        //act
        Exception exception = assertThrows(NullPointerException.class, () -> new CategoryDesignation(designation));

        //assert
        String exceptionMessage = exception.getMessage();
        String expectedMessage = "Category must not be null";
        assertEquals(exceptionMessage, expectedMessage);
    }

    @Test
    @DisplayName("Create a designation with less than less than 3 characters")
    void createDesignationWithLessThanThreeCharacters() {
        //arrange
        String designation = "Id";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new CategoryDesignation(designation));

        //assert
        String exceptionMessage = exception.getMessage();
        String expectedMessage = "Category must have at least 3 characters";
        assertEquals(exceptionMessage, expectedMessage);
    }

    @Test
    @DisplayName("Create a designation with less than less than 3 characters excluding spaces")
    void createDesignationWithLessThanThreeCharactersExcludingSpaces() {
        //arrange
        String designation = "        Id       ";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new CategoryDesignation(designation));

        //assert
        String exceptionMessage = exception.getMessage();
        String expectedMessage = "Category must have at least 3 characters";
        assertEquals(exceptionMessage, expectedMessage);
    }

    @Test
    @DisplayName("Equals: true")
    void testEquals_True() {
        //arrange
        Designation designation1 = new CategoryDesignation("Food");
        Designation designation2 = new CategoryDesignation("Food");
        boolean result;

        //act
        result = designation1.equals(designation2);

        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Same Instance")
    void sameInstance() {
        //arrange
        Designation designation1 = new CategoryDesignation("Food");
        Designation designation2 = designation1;
        boolean result;

        //act
        result = designation1.equals(designation2);

        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Equals: false")
    void testEquals_False() {
        //arrange
        Designation designation1 = new CategoryDesignation("Food");
        Designation designation2 = new CategoryDesignation("Car");
        boolean result;

        //act
        result = designation1.equals(designation2);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Not equal objects")
    void designationNotEqualToCategory() {
        //arrange
        CategoryDesignation designation = new CategoryDesignation("Food");
        String categoryId = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryId);
        Category category = CategoryFactory.create(designation, categoryID,null);
        boolean result;
        //act
        result = designation.equals(category);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Hash Code: true")
    void testHashCode_True() {
        Designation designation = new CategoryDesignation("Food");
        assertEquals(designation.hashCode(), designation.hashCode());
    }

    @Test
    @DisplayName("Hash Code: false")
    void testHashCode_False() {
        Designation designation1 = new CategoryDesignation("Food");
        Designation designation2 = new CategoryDesignation("Car");
        assertNotEquals(designation1.hashCode(), designation2.hashCode());
    }

    @Test
    @DisplayName("Compare with null")
    public void compareWithNull() {
        //arrange
        Designation designation = new CategoryDesignation("Food");
        boolean result;

        //act
        result = designation.equals(null);

        //assert
        assertFalse(result);
    }
}
