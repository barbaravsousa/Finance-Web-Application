package switchtwentytwenty.project.domain.share.designation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.id.CategoryID;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountDesignationTest {

    @Test
    @DisplayName("Create a designation successfully")
    void createDesignationSuccessfully() {
        //arrange
        String initialDesignation = "My Account";

        //act
        Designation designation = new AccountDesignation(initialDesignation);

        //assert
        assertNotNull(designation);
    }

    @Test
    @DisplayName("Create a designation successfully excluding spaces")
    void createDesignationSuccessfullyExcludingSpaces() {
        //arrange
        String initialDesignation = "      My          Account    ";

        //act
        Designation designation = new AccountDesignation(initialDesignation);

        //assert
        assertNotNull(designation);
    }

    @Test
    @DisplayName("Create a designation successfully with more than one word")
    void createDesignationSuccessfullyWithMoreThanOneWord() {
        //arrange
        String initialDesignation = "My         Account";

        //act
        Designation designation = new AccountDesignation(initialDesignation);

        //assert
        assertNotNull(designation);
    }

    @Test
    @DisplayName("Create a designation with invalid characters")
    void createDesignationWithInvalidCharacters() {
        //arrange
        String designation = "My@Account";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AccountDesignation(designation));

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
        Exception exception = assertThrows(NullPointerException.class, () -> new AccountDesignation(designation));

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
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AccountDesignation(designation));

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
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AccountDesignation(designation));

        //assert
        String exceptionMessage = exception.getMessage();
        String expectedMessage = "Category must have at least 3 characters";
        assertEquals(exceptionMessage, expectedMessage);
    }

    @Test
    @DisplayName("Equals: true")
    void testEquals_True() {
        //arrange
        Designation designation1 = new AccountDesignation("My Account");
        Designation designation2 = new AccountDesignation("My Account");
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
        Designation designation1 = new AccountDesignation("My Account");
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
        Designation designation1 = new AccountDesignation("My Account");
        Designation designation2 = new AccountDesignation("Your Account");
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
        Designation designation = new AccountDesignation("My Account");
        CategoryDesignation categoryDesignation = new CategoryDesignation("My Account");
        String categoryId = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryId);
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        boolean result;
        //act
        result = designation.equals(category);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Hash Code: true")
    void testHashCode_True() {
        Designation designation = new AccountDesignation("My Account");
        assertEquals(designation.hashCode(), designation.hashCode());
    }

    @Test
    @DisplayName("Hash Code: false")
    void testHashCode_False() {
        Designation designation1 = new AccountDesignation("My Account");
        Designation designation2 = new AccountDesignation("Your Account");
        assertNotEquals(designation1.hashCode(), designation2.hashCode());
    }

    @Test
    @DisplayName("Compare with null")
    void compareWithNull() {
        //arrange
        Designation designation = new AccountDesignation("My Account");
        boolean result;

        //act
        result = designation.equals(null);

        //assert
        assertFalse(result);
    }
}