package switchtwentytwenty.project.interfaceadaptor.implcontroller.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.CategoryIDGenerator;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.CategoryService;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.dto.indto.CustomCategoryInDTO;
import switchtwentytwenty.project.exception.InvalidDateException;
import switchtwentytwenty.project.interfaceadaptor.repository.CategoryRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AddCategoryToFamilyTreeControllerIT {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    AddCategoryToFamilyTreeController controller;
    @Autowired
    CategoryIDGenerator categoryIDGenerator;

    @Test
    @DisplayName("Add root custom category : 200")
    void addCategoryToFamilyTree() throws InvalidDateException {
        //arrange

        FamilyID familyID = new FamilyID(UUID.randomUUID());
        String famID = familyID.toString();
        CustomCategoryInDTO custom = new CustomCategoryInDTO("Gas", null);

        //act
        ResponseEntity<Object> result = controller.addCategoryToFamilyTree(custom,famID);
        int expected = 200;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Add child custom category : 200")
    void addCategoryToFamilyTreeSuccess() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        String famID = familyID.toString();

        Designation designationRoot = new CategoryDesignation("Food");
        CategoryID rootId = categoryIDGenerator.generate();
        Category rootCategory = CategoryFactory.create(designationRoot, rootId, null,familyID);
        categoryRepository.save(rootCategory);
        String parseRootId = rootId.toString();
        CustomCategoryInDTO custom = new CustomCategoryInDTO("Fruit", parseRootId);

        //act
        ResponseEntity<Object> result = controller.addCategoryToFamilyTree(custom,famID);
        int expected = 200;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }
    @Test
    @DisplayName("Add parent custom category : 200")
    void addCategoryRootToFamilyTreeSuccess() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        String famID = familyID.toString();

        Designation designationRoot = new CategoryDesignation("Food");
        CategoryID rootId = categoryIDGenerator.generate();
        Category rootCategory = CategoryFactory.create(designationRoot, rootId, null,familyID);
        categoryRepository.save(rootCategory);
        CustomCategoryInDTO custom = new CustomCategoryInDTO("Fruit", null);

        //act
        ResponseEntity<Object> result = controller.addCategoryToFamilyTree(custom,famID);
        int expected = 200;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Add parent custom category : 200")
    void addInvalidCategoryNameToFamilyTreeFailure() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        String famID = familyID.toString();

        Designation designationRoot = new CategoryDesignation("Food");
        CategoryID rootId = categoryIDGenerator.generate();
        Category rootCategory = CategoryFactory.create(designationRoot, rootId, null,familyID);
        categoryRepository.save(rootCategory);
        CustomCategoryInDTO custom = new CustomCategoryInDTO("F6a7sfdt", null);

        //act
        ResponseEntity<Object> result = controller.addCategoryToFamilyTree(custom,famID);
        int expected = 400;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Add null category : 400")
    void addNullCategoryToFamilyTreeFailure() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        String famID = familyID.toString();

        Designation designationRoot = new CategoryDesignation("Food");
        CategoryID rootId = categoryIDGenerator.generate();
        Category rootCategory = CategoryFactory.create(designationRoot, rootId, null,familyID);
        categoryRepository.save(rootCategory);
        CustomCategoryInDTO custom = new CustomCategoryInDTO(null, null);

        //act
        ResponseEntity<Object> result = controller.addCategoryToFamilyTree(custom,famID);
        int expected = 400;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Add null category : 400")
    void addRootCategoryWithSameDesignationToFamilyTreeFailure() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        String famID = familyID.toString();

        Designation designationRoot = new CategoryDesignation("Food");
        CategoryID rootId = categoryIDGenerator.generate();
        Category rootCategory = CategoryFactory.create(designationRoot, rootId, null,familyID);
        categoryRepository.save(rootCategory);
        CustomCategoryInDTO custom = new CustomCategoryInDTO("Food", null);

        //act
        ResponseEntity<Object> result = controller.addCategoryToFamilyTree(custom,famID);
        int expected = 400;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }

}