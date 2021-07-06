package switchtwentytwenty.project.domain.aggregate.category;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;

import switchtwentytwenty.project.domain.share.id.FamilyID;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @Test
    @DisplayName("Create a root custom category")
    void createRootCustomCategory() {
        //arrange
        UUID familyid = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyid);
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        Designation categoryDesignation = new CategoryDesignation("Food");
        //act
        Category category = new Custom(categoryDesignation, categoryID, null, familyID);
        //assert
        Assertions.assertNotNull(category);
    }

    @Test
    @DisplayName("Create a root standard category")
    void createRootStandardCategory() {
        //arrange
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        Designation categoryDesignation = new CategoryDesignation("Food");
        //act
        Category category = new Standard(categoryDesignation, categoryID, null);
        //assert
        Assertions.assertNotNull(category);
    }

    @Test
    @DisplayName("Get standard category Id successfully")
    void getStandardCategoryIdSuccessfully() {
        //arrange
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = new Standard(categoryDesignation, categoryID, null);

        CategoryID expected = new CategoryID(categoryid);

        //act
        CategoryID result = category.getID();

        //assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get standard category Id unsuccessfully")
    void getStandardCategoryIdUnsuccessfully() {
        //arrange
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = new Standard(categoryDesignation, categoryID, null);
        String categoryidexpected = UUID.randomUUID().toString();
        CategoryID expected = new CategoryID(categoryidexpected);

        //act
        CategoryID result = category.getID();

        //assert
        Assertions.assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Get custom category Id successfully")
    void getCustomCategoryIdSuccessfully() {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = new Custom(categoryDesignation, categoryID, null, familyID);

        CategoryID expected = new CategoryID(categoryid);

        //act
        CategoryID result = category.getID();

        //assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get custom category Id unsuccessfully")
    void getCustomCategoryIdUnsuccessfully() {
        //arrange
        UUID id1 = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id1);
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = new Custom(categoryDesignation, categoryID, null, familyID);
        String id2 = UUID.randomUUID().toString();
        CategoryID expected = new CategoryID(id2);

        //act
        CategoryID result = category.getID();

        //assert
        Assertions.assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Verify if custom category is standard")
    void verifyIfCustomCategoryIsStandard() {
        //arrange
        UUID familyid = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyid);
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = new Custom(categoryDesignation, categoryID, null, familyID);

        //act
        boolean result = category.isStandard();

        //assert
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Verify if standard category is standard")
    void verifyIfStandardCategoryIsStandard() {
        //arrange
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = new Standard(categoryDesignation, categoryID, null);

        //act
        boolean result = category.isStandard();

        //assert
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Verify if custom category is custom")
    void verifyIfCustomCategoryIsCustom() {
        //arrange
        UUID familyid = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyid);
        String categoryid = UUID.randomUUID().toString();
        Designation categoryDesignation = new CategoryDesignation("Food");
        CategoryID categoryID = new CategoryID(categoryid);
        Category category = new Custom(categoryDesignation, categoryID, null, familyID);

        //act
        boolean result = !category.isStandard();

        //assert
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Verify if standard category is custom")
    void verifyIfStandardCategoryIsCustom() {
        //arrange
        UUID categoryid = UUID.randomUUID();
        CategoryID categoryID = new CategoryID(categoryid.toString());
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = new Standard(categoryDesignation, categoryID, null);

        //act
        boolean result = !category.isStandard();

        //assert
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Verify if standard category is the same identity as other custom category")
    void verifyIfStandardCategoryIsSameAsOtherCustomCategory() {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category1 = new Standard(categoryDesignation, categoryID, null);
        Category category2 = new Custom(categoryDesignation, categoryID, null, familyID);

        //act
        boolean result = category1.sameValueAs(category2);

        //assert
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Verify if custom category has the same identity as other standard category")
    void verifyIfCustomCategoryIsSameAsOtherStandardCategory() {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category1 = new Custom(categoryDesignation, categoryID, null, familyID);
        Category category2 = new Standard(categoryDesignation, categoryID, null);

        //act
        boolean result = category1.sameValueAs(category2);

        //assert
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Verify if custom category has the same identity as other custom category")
    void verifyIfCustomCategoryHasSameAsOtherCustomCategory() {
        //arrange
        String categoryid1 = UUID.randomUUID().toString();
        CategoryID categoryID1 = new CategoryID(categoryid1);
        Designation categoryDesignation = new CategoryDesignation("Food");
        FamilyID familyID1 = new FamilyID(UUID.randomUUID());
        Category category1 = new Custom(categoryDesignation, categoryID1, null, familyID1);

        String categoryid2 = UUID.randomUUID().toString();
        FamilyID familyID2 = new FamilyID(UUID.randomUUID());
        CategoryID categoryID2 = new CategoryID(categoryid2);
        Category category2 = new Custom(categoryDesignation, categoryID2, null, familyID2);

        //act
        boolean result = category1.sameValueAs(category2);

        //assert
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Verify if custom category has the same identity as other standard category")
    void verifyIfStandardCategoryHasSameAsOtherStandardCategory() {
        //arrange
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category1 = new Standard(categoryDesignation, categoryID, null);
        Category category2 = new Standard(categoryDesignation, categoryID, null);

        //act
        boolean result = category1.sameValueAs(category2);

        //assert
        Assertions.assertTrue(result);
    }


    @Test
    @DisplayName("Verify if standard category has the same identity as other standard category")
    void verifyIfStandardCategoryDontHasSameAsOtherStandardCategory() {
        //arrange
        String categoryid1 = UUID.randomUUID().toString();
        CategoryID categoryID1 = new CategoryID(categoryid1);
        CategoryDesignation categoryDesignation1 = new CategoryDesignation("Food");
        Category category1 = new Standard(categoryDesignation1, categoryID1, null);
        CategoryDesignation categoryDesignation2 = new CategoryDesignation("Meds");
        String categoryid2 = UUID.randomUUID().toString();
        CategoryID categoryID2 = new CategoryID(categoryid2);
        Category category2 = new Standard(categoryDesignation2, categoryID2, null);

        //act
        boolean result = category1.sameValueAs(category2);

        //assert
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Hash Code: true")
    void testHashCode_True() {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Designation categoryDesignation = new CategoryDesignation("Food");
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        //act
        Custom category = new Custom(categoryDesignation, categoryID, null, familyID);

        //assert
        assertEquals(category.hashCode(), category.hashCode());
    }

    @Test
    @DisplayName("Hash Code: false")
    void testHashCode_False() {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        CategoryID categoryID1 = new CategoryID(UUID.randomUUID().toString());

        CategoryID categoryID2 = new CategoryID(UUID.randomUUID().toString());
        Designation food = new CategoryDesignation("Food");
        Designation car = new CategoryDesignation("car");

        Custom category1 = new Custom(food, categoryID1, null, familyID);
        Custom category2 = new Custom(car, categoryID2, null, familyID);
        //act

        //assert
        assertNotEquals(category1.hashCode(), category2.hashCode());
    }

    @Test
    @DisplayName("Equal method: different object")
    void testEqualsDifferentObject() {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = new Custom(categoryDesignation, categoryID, null, familyID);
        //act

        //assert
        assertNotEquals(category, categoryDesignation);
    }

    @Test
    @DisplayName("Equal method: same object")
    void testEqualsSameObject() {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = new Custom(categoryDesignation, categoryID, null, familyID);
        //act

        //assert
        assertEquals(category, category);
    }

    @Test
    @DisplayName("Custom Category belongs to family")
    void testFamilyCustomCategory() {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = new Custom(categoryDesignation, categoryID, null, familyID);
        //act
        boolean result = category.belongsToFamily(familyID);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Custom Category doesn't belongs to family")
    void testNotFamilyCustomCategory() {
        //arrange
        UUID id1 = UUID.randomUUID();
        FamilyID familyID1 = new FamilyID(id1);
        UUID id2 = UUID.randomUUID();
        FamilyID familyID2 = new FamilyID(id2);
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = new Custom(categoryDesignation, categoryID, null, familyID1);
        //act
        boolean result = category.belongsToFamily(familyID2);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Standard Category belongs to family")
    void testFamilyStandardCategory() {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        Designation categoryDesignation = new CategoryDesignation("Food");
        Category category = new Standard(categoryDesignation, categoryID, null);
        boolean result = category.belongsToFamily(familyID);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Standard Category has same Designation")
    void hasSameDesignation() {

        //arrange
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        CategoryID parentID = new CategoryID(UUID.randomUUID().toString());
        Designation categoryDesignation = new CategoryDesignation("Food");
        //act
        Category category = new Standard(categoryDesignation, categoryID, parentID);
        //assert
        Designation designation = new CategoryDesignation("Food");
        boolean result = category.hasSameDesignation(designation);
        assertTrue(result);
    }

    @Test
    @DisplayName("Standard Category does not have the same Designation")
    void doesNotHaveTheSameDesignation() {

        //arrange
        String categoryid = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(categoryid);
        CategoryID parentID = new CategoryID(UUID.randomUUID().toString());
        Designation categoryDesignation = new CategoryDesignation("Food");
        //act
        Category category = new Standard(categoryDesignation, categoryID, parentID);
        //assert
        Designation designation = new CategoryDesignation("Groceries");
        boolean result = category.hasSameDesignation(designation);
        assertFalse(result);
    }

    @Test
    @DisplayName("Custom category: has same designation")
    void customCategoryHasTheSameCategory() {
        //arrange
        UUID familyid = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyid);
        String categoryid = UUID.randomUUID().toString();
        Designation categoryDesignation = new CategoryDesignation("Food");
        CategoryID categoryID = new CategoryID(categoryid);
        CategoryID parentID = new CategoryID(UUID.randomUUID().toString());
        Category category = new Custom(categoryDesignation, categoryID, parentID, familyID);
        Designation designation = new CategoryDesignation("Food");
        //act
        boolean result = category.hasSameDesignation(designation);

        //assert
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Custom category: has same designation")
    void customCategoryDoesNotHaveTheSameDesignation() {
        //arrange
        UUID familyid = UUID.randomUUID();
        FamilyID familyID = new FamilyID(familyid);
        String categoryid = UUID.randomUUID().toString();
        Designation categoryDesignation = new CategoryDesignation("Food");
        CategoryID categoryID = new CategoryID(categoryid);
        CategoryID parentID = new CategoryID(UUID.randomUUID().toString());
        Category category = new Custom(categoryDesignation, categoryID, parentID, familyID);
        Designation designation = new CategoryDesignation("Food");
        //act
        boolean result = category.hasSameDesignation(designation);

        //assert
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Failure to get familyID: IllegalArgumentException")
    void failureToGetFamilyID() {
        CategoryDesignation designation = new CategoryDesignation("Food");
        String id = UUID.randomUUID().toString();

        CategoryID categoryID = new CategoryID(id);
        Category standardCategory = new Standard(designation, categoryID, null);


        Exception exception = assertThrows(IllegalArgumentException.class, () -> standardCategory.getFamilyID());

        String exceptionMessage = exception.getMessage();
        String expectedMessage = "Does not apply to Standard Categories";
        //assert
        assertEquals(exceptionMessage, expectedMessage);
    }

    @Test
    @DisplayName("Category has the same ID as another category")
    void hasSameID() {

        Designation designation = new CategoryDesignation("Food");
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Category standardCategory = new Standard(designation, categoryID, null);
        Category anotherStandardCategory = new Standard(designation, categoryID, null);

        boolean result = standardCategory.hasSameID(anotherStandardCategory.getID());
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Category has the same ID as another category: null id")
    void hasSameIDNullID() {
        //arrange
        Designation designation = new CategoryDesignation("Food");
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Category standardCategory = new Standard(designation, categoryID, null);


        //act
        Exception exception = assertThrows(NullPointerException.class, () -> standardCategory.hasSameID(null));

        String exceptionMessage = exception.getMessage();
        String expectedMessage = "Category ID is null";
        //assert
        assertEquals(exceptionMessage, expectedMessage);
    }

    @Test
    @DisplayName("Category does not have the same ID as another category")
    void doesNotHaveTheSameID() {
        //arrange
        Designation designation = new CategoryDesignation("Food");
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Category standardCategory = new Standard(designation, categoryID, null);

        CategoryID categoryID2 = new CategoryID(UUID.randomUUID().toString());
        Category anotherStandardCategory = new Standard(designation, categoryID2, null);


        //act
        boolean result = standardCategory.hasSameID(anotherStandardCategory.getID());
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Different object: object has different ID")
    void differentObject() {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Designation designation = new CategoryDesignation("Food");
        Category standardCategory = new Standard(designation, categoryID, null);
        //act
        CategoryID differentCategoryID = new CategoryID(UUID.randomUUID().toString());
        Category differentStandardCategory = new Standard(designation, differentCategoryID, null);
        //assert
        assertNotEquals(standardCategory, differentStandardCategory);

    }

    @Test
    @DisplayName("Equals: Same object")
    void equals() {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Designation designation = new CategoryDesignation("Food");
        Category standardCategory = new Standard(designation, categoryID, null);
        //act
        Category sameStandardCategory = new Standard(designation, categoryID, null);
        //assert
        assertEquals(standardCategory, sameStandardCategory);

    }

    @Test
    @DisplayName("Equals: Different category object")
    void notEquals() {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Designation designation = new CategoryDesignation("Food");
        Category standardCategory = new Standard(designation, categoryID, null);
        //act

        CategoryID differentCategoryID = new CategoryID(UUID.randomUUID().toString());
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Category customCategory = new Custom(designation, differentCategoryID, null, familyID);
        //assert
        assertNotEquals(standardCategory, customCategory);

    }

    @Test
    @DisplayName("Equals: Different object is null")
    void notEquals_Null() {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Designation designation = new CategoryDesignation("Food");
        Category standardCategory = new Standard(designation, categoryID, null);
        //act
        boolean result = standardCategory.equals(null);
        //assert
        assertFalse(result);

    }

    @Test
    @DisplayName("Equals method: Custom Category")
    void EqualsCustomCategory() {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Designation designation = new CategoryDesignation("Food");
        FamilyID familyID1 = new FamilyID(UUID.randomUUID());
        Category customCategory = new Custom(designation, categoryID, null, familyID1);
        //act
        Category customCategory2 = new Custom(designation, categoryID, null, familyID1);
        //assert
        assertEquals(customCategory, customCategory2);

    }

    @Test
    @DisplayName("Equals method: Custom Category different family ids")
    void NotEqualsCustomCategory() {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Designation designation = new CategoryDesignation("Food");
        FamilyID familyID1 = new FamilyID(UUID.randomUUID());
        Category customCategory = new Custom(designation, categoryID, null, familyID1);
        //act
        FamilyID familyID2 = new FamilyID(UUID.randomUUID());
        Category customCategory2 = new Custom(designation, categoryID, null, familyID2);
        //assert
        assertNotEquals(customCategory, customCategory2);

    }

    @Test
    @DisplayName("Same value as: Base category with no parentID")
    void BaseCategorySameValueAs() {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Designation designation = new CategoryDesignation("Food");
        CategoryID parentID = new CategoryID(UUID.randomUUID().toString());
        Category standardCategory = new Standard(designation, categoryID, parentID);
        //act

        Category standardCategory2 = new Standard(designation, categoryID, parentID);
        //assert
        boolean result = standardCategory.sameValueAs(standardCategory2);
        assertTrue(result);

    }

    @Test
    @DisplayName("Same value as: different parentIds")
    void BaseCategorySameValueAsDifferentParentIds() {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Designation designation = new CategoryDesignation("Food");
        CategoryID parentID = new CategoryID(UUID.randomUUID().toString());
        Category standardCategory = new Standard(designation, categoryID, parentID);
        //act

        Category standardCategory2 = new Standard(designation, categoryID, null);
        //assert
        boolean result = standardCategory.sameValueAs(standardCategory2);
        assertFalse(result);

    }

    @Test
    @DisplayName("Same value as: same parentIDs, different designations and ids")
    void BaseCategorySameValueAsSameParentIds() {
        //arrange
        CategoryID categoryID1 = new CategoryID(UUID.randomUUID().toString());
        Designation designation1 = new CategoryDesignation("Food");
        CategoryID categoryID2 = new CategoryID(UUID.randomUUID().toString());
        Designation designation2 = new CategoryDesignation("Transportation");
        CategoryID parentID = new CategoryID(UUID.randomUUID().toString());
        Category standardCategory = new Standard(designation1, categoryID2, parentID);
        //act

        Category standardCategory2 = new Standard(designation2, categoryID2, parentID);
        //assert
        boolean result = standardCategory.sameValueAs(standardCategory2);
        assertFalse(result);

    }

    @Test
    @DisplayName("Same value as: custom categories with different family ids")
    void CustomCategorySameValueAsDifferentFamilyIds() {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Designation designation = new CategoryDesignation("Food");
        FamilyID familyID1 = new FamilyID(UUID.randomUUID());
        FamilyID familyID2 = new FamilyID(UUID.randomUUID());
        Category customCategory = new Custom(designation, categoryID, null, familyID1);
        //act
        Category customCategory2 = new Custom(designation, categoryID, null, familyID2);
        //assert
        boolean result = customCategory.sameValueAs(customCategory2);
        assertFalse(result);

    }

    @Test
    @DisplayName("Same value as: custom categories with different ids")
    void CustomCategorySameValueAsDifferentIds() {
        //arrange
        CategoryID categoryID1 = new CategoryID(UUID.randomUUID().toString());
        CategoryID categoryID2 = new CategoryID(UUID.randomUUID().toString());
        Designation designation = new CategoryDesignation("Food");
        FamilyID familyID = new FamilyID(UUID.randomUUID());

        Category customCategory = new Custom(designation, categoryID1, null, familyID);
        //act
        Category customCategory2 = new Custom(designation, categoryID2, null, familyID);
        //assert
        boolean result = customCategory.sameValueAs(customCategory2);
        assertFalse(result);

    }

    @Test
    @DisplayName("Same value as: custom categories with different designations")
    void CustomCategorySameValueAsDifferentDesignations() {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Designation designation1 = new CategoryDesignation("Food");
        Designation designation2 = new CategoryDesignation("Trips");
        FamilyID familyID = new FamilyID(UUID.randomUUID());

        Category customCategory = new Custom(designation1, categoryID, null, familyID);
        //act
        Category customCategory2 = new Custom(designation2, categoryID, null, familyID);
        //assert
        boolean result = customCategory.sameValueAs(customCategory2);
        assertFalse(result);

    }

    @Test
    @DisplayName("Same value as: custom categories with different Parent ids")
    void CustomCategorySameValueAsDifferentParentIds() {
        //arrange
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());
        Designation designation = new CategoryDesignation("Food");
        CategoryID parentID1 = new CategoryID(UUID.randomUUID().toString());
        CategoryID parentID2 = new CategoryID(UUID.randomUUID().toString());
        FamilyID familyID = new FamilyID(UUID.randomUUID());

        Category customCategory = new Custom(designation, categoryID, parentID1, familyID);
        //act
        Category customCategory2 = new Custom(designation, categoryID, parentID2, familyID);
        //assert
        boolean result = customCategory.sameValueAs(customCategory2);
        assertFalse(result);

    }

}

