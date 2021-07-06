package switchtwentytwenty.project.domain.share.transactiondata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;

import java.util.UUID;

class MovementTypeTest {

    @Test
    @DisplayName("Create valid movement type")
    void createValidMovementType() throws Exception {
        //arrange
        MovementType movementType;
        //act
        movementType = new MovementType(Constants.DEBIT);
        //assert
        assertNotNull(movementType);
    }

    @Test
    @DisplayName("Create valid movement type")
    void createValidMovementTypeUppercase() throws Exception {
        //arrange
        String type = "CREDIT";
        MovementType movementType;
        //act
        movementType = new MovementType(type);
        //assert
        assertNotNull(movementType);
    }

    @Test
    @DisplayName("Create valid movement type")
    void createValidMovementTypeUppercaseAndLowercase() throws Exception {
        //arrange
        String type = "cREdIT";
        MovementType movementType;
        //act
        movementType = new MovementType(type);
        //assert
        assertNotNull(movementType);
    }

    @Test
    @DisplayName("Create invalid movement type")
    void createInvalidMovementType() {
        //arrange
        String wrongType = "Super credit";
        //act and assert
        assertThrows(InvalidMovementTypeException.class, () -> {
            new MovementType(wrongType);
        });
    }

    @Test
    @DisplayName("Create invalid movement type - empty")
    void createInvalidMovementTypeEmpty() {
        //arrange
        String wrongType = "   ";
        //act and assert
        assertThrows(InvalidMovementTypeException.class, () -> {
            new MovementType(wrongType);
        });
    }

    @Test
    @DisplayName("Create invalid movement type - null")
    void createInvalidMovementTypeNull() {
        //arrange
        String wrongType = null;
        //act and assert
        assertThrows(NullPointerException.class, () -> {
            new MovementType(wrongType);
        });
    }

    @Test
    @DisplayName("To string")
    void testToString() throws Exception {
        //arrange
        MovementType movementType = new MovementType(Constants.DEBIT);
        String expected = "debit";

        //act and assert
        String result = movementType.toString();

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("HashCode test")
    public void testEqualsHashCode() throws Exception {
        //arrange
        MovementType movementType1 = new MovementType(Constants.DEBIT);
        MovementType movementType2 = new MovementType(Constants.DEBIT);
        MovementType movementType3 = new MovementType(Constants.CREDIT);

        //act
        //assert
        assertEquals(movementType1, movementType2);
        assertEquals(movementType1.hashCode(), movementType2.hashCode());
        assertNotEquals(movementType1.hashCode(), movementType3.hashCode());
    }

    @Test
    @DisplayName("Same movement type")
    public void sameTransferBetweenMembersDTO() throws Exception {
        //arrange
        MovementType movementType1 = new MovementType(Constants.DEBIT);
        MovementType movementType2 = new MovementType(Constants.DEBIT);

        //act
        //assert
        assertEquals(movementType1, movementType2);
    }

    @Test
    @DisplayName("Not same movement type")
    public void notSameTransferBetweenMembersDTO() throws Exception {
        //arrange
        MovementType movementType1 = new MovementType(Constants.DEBIT);
        MovementType movementType2 = new MovementType(Constants.CREDIT);

        //act
        //assert
        assertNotEquals(movementType1, movementType2);
    }

    @Test
    @DisplayName("Not equal objects")
    public void viewRelationDTONotEqualToCategory() throws Exception {
        //arrange
        String id = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(id);
        CategoryDesignation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);

        MovementType movementType = new MovementType(Constants.DEBIT);

        //act
        boolean result = movementType.equals(category);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same instance")
    public void sameInstance() throws Exception {
        //arrange
        MovementType movementType = new MovementType(Constants.DEBIT);
        MovementType sameMovementType = movementType;

        //assert
        assertEquals(movementType, sameMovementType);
    }
}
