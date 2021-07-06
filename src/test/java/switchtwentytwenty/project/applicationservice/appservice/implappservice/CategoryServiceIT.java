package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.dto.outdto.*;
import switchtwentytwenty.project.dto.toservicedto.CustomCategoryDTO;
import switchtwentytwenty.project.exception.DesignationNotPossibleException;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceIT {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryIDGenerator categoryIDGenerator;

    @BeforeEach
    public void before() {
        categoryRepository.deleteAll();
    }

    @Test
    @DisplayName("Get list of family categories")
    void getListOfFamilyCategories() {
        //arrange

        int listSizeExpected = 4;
        int listSizeResult;

        String energy = "Energy";
        String transportation = "Transportation";
        String electricity = "Electricity";
        String bus = "Bus";
        String car = "Car";
        String children = "Children";

        FamilyID familyID = new FamilyID(UUID.randomUUID());
        FamilyID otherFamilyID = new FamilyID(UUID.randomUUID());

        // #1 standard category
        Designation designationRoot1 = new CategoryDesignation(energy);
        CategoryID rootId1 = categoryIDGenerator.generate();
        Category rootCategory1 = CategoryFactory.create(designationRoot1, rootId1, null);
        categoryRepository.save(rootCategory1);

        // #2 standard category
        Designation designationRoot2 = new CategoryDesignation(transportation);
        CategoryID rootId2 = categoryIDGenerator.generate();
        Category rootCategory2 = CategoryFactory.create(designationRoot2, rootId2, null);
        categoryRepository.save(rootCategory2);

        // #1 custom category
        Designation designationCustom1 = new CategoryDesignation(electricity);
        CategoryID customId1 = categoryIDGenerator.generate();
        Category customCategory1 = CategoryFactory.create(designationCustom1, customId1, rootId1, familyID);
        categoryRepository.save(customCategory1);

        // #2 custom category
        Designation designationCustom2 = new CategoryDesignation(bus);
        CategoryID customId2 = categoryIDGenerator.generate();
        Category customCategory2 = CategoryFactory.create(designationCustom2, customId2, rootId2, familyID);
        categoryRepository.save(customCategory2);

        // #3 custom category other family
        Designation designationCustom3 = new CategoryDesignation(car);
        CategoryID customId3 = categoryIDGenerator.generate();
        Category customCategory3 = CategoryFactory.create(designationCustom3, customId3, rootId2, otherFamilyID);
        categoryRepository.save(customCategory3);

        // #4 custom category other family
        Designation designationCustom4 = new CategoryDesignation(children);
        CategoryID customId4 = categoryIDGenerator.generate();
        Category customCategory4 = CategoryFactory.create(designationCustom4, customId4, null, otherFamilyID);
        categoryRepository.save(customCategory4);

        //act
        List<CategoryOutDTO> dtoList = categoryService.getListOfFamilyCategories(familyID.toString());
        listSizeResult = dtoList.size();

        List<String> designationList = new ArrayList<>();
        for (CategoryOutDTO dto : dtoList) {
            designationList.add(dto.getDesignation());
        }

        boolean resultEnergy = designationList.contains(energy);
        boolean resultTransportation = designationList.contains(transportation);
        boolean resultElectricity = designationList.contains(electricity);
        boolean resultBus = designationList.contains(bus);
        boolean resultCar = designationList.contains(car);
        boolean resultChildren = designationList.contains(children);

        //TODO ricardo
        boolean result = listSizeExpected <= listSizeResult;

        //assert
        //assertEquals(listSizeResult, listSizeExpected);
        assertTrue(result);
        assertTrue(resultEnergy);
        assertTrue(resultTransportation);
        assertTrue(resultElectricity);
        assertTrue(resultBus);
        assertFalse(resultCar);
        assertFalse(resultChildren);
    }

    @Test
    @DisplayName("Get list of family categories - only standard")
    void getListOfFamilyCategoriesOnlyStandard() {
        //arrange
        int listSizeExpected = 2;
        int listSizeResult;

        String energy = "Energy";
        String transportation = "Transportation";
        String electricity = "Electricity";
        String bus = "Bus";
        String car = "Car";
        String children = "Children";

        FamilyID familyID = new FamilyID(UUID.randomUUID());
        FamilyID otherFamilyID = new FamilyID(UUID.randomUUID());

        // #1 standard category
        Designation designationRoot1 = new CategoryDesignation(energy);
        CategoryID rootId1 = categoryIDGenerator.generate();
        Category rootCategory1 = CategoryFactory.create(designationRoot1, rootId1, null);
        categoryRepository.save(rootCategory1);

        // #2 standard category
        Designation designationRoot2 = new CategoryDesignation(transportation);
        CategoryID rootId2 = categoryIDGenerator.generate();
        Category rootCategory2 = CategoryFactory.create(designationRoot2, rootId2, null);
        categoryRepository.save(rootCategory2);

        // #1 custom category
        Designation designationCustom1 = new CategoryDesignation(electricity);
        CategoryID customId1 = categoryIDGenerator.generate();
        Category customCategory1 = CategoryFactory.create(designationCustom1, customId1, rootId1, otherFamilyID);
        categoryRepository.save(customCategory1);

        // #2 custom category
        Designation designationCustom2 = new CategoryDesignation(bus);
        CategoryID customId2 = categoryIDGenerator.generate();
        Category customCategory2 = CategoryFactory.create(designationCustom2, customId2, rootId2, otherFamilyID);
        categoryRepository.save(customCategory2);

        // #3 custom category other family
        Designation designationCustom3 = new CategoryDesignation(car);
        CategoryID customId3 = categoryIDGenerator.generate();
        Category customCategory3 = CategoryFactory.create(designationCustom3, customId3, rootId2, otherFamilyID);
        categoryRepository.save(customCategory3);

        // #4 custom category other family
        Designation designationCustom4 = new CategoryDesignation(children);
        CategoryID customId4 = categoryIDGenerator.generate();
        Category customCategory4 = CategoryFactory.create(designationCustom4, customId4, null, otherFamilyID);
        categoryRepository.save(customCategory4);

        //act
        List<CategoryOutDTO> dtoList = categoryService.getListOfFamilyCategories(familyID.toString());
        listSizeResult = dtoList.size();

        List<String> designationList = new ArrayList<>();
        for (CategoryOutDTO dto : dtoList) {
            designationList.add(dto.getDesignation());
        }

        boolean resultEnergy = designationList.contains(energy);
        boolean resultTransportation = designationList.contains(transportation);
        boolean resultElectricity = designationList.contains(electricity);
        boolean resultBus = designationList.contains(bus);
        boolean resultCar = designationList.contains(car);
        boolean resultChildren = designationList.contains(children);

        //TODO ricardo
        boolean result = listSizeExpected <= listSizeResult;

        //assert
        //assertEquals(listSizeResult, listSizeExpected);
        assertTrue(result);
        assertTrue(resultEnergy);
        assertTrue(resultTransportation);
        assertFalse(resultElectricity);
        assertFalse(resultBus);
        assertFalse(resultCar);
        assertFalse(resultChildren);
    }

    @Test
    @DisplayName("Get Standard Categories Tree : success")
    void getStandardCategoriesTree() throws Exception {
        //arrange
        Designation designationRoot = new CategoryDesignation("Food");
        CategoryID rootId = categoryIDGenerator.generate();
        Category rootCategory = CategoryFactory.create(designationRoot, rootId, null);
        categoryRepository.save(rootCategory);
        String parseRootId = rootId.toString();
        categoryService.createChildStandardCategory("Fruit", parseRootId);
        categoryService.createChildStandardCategory("Meat", parseRootId);
        categoryService.createChildStandardCategory("Fish", parseRootId);

        CategoryTreeOutDTO categoryTreeOutDTO = new CategoryTreeOutDTO("Food");
        CategoryTreeOutDTO categoryTreeOutDTO1 = new CategoryTreeOutDTO("Fruit");
        CategoryTreeOutDTO categoryTreeOutDTO2 = new CategoryTreeOutDTO("Meat");
        CategoryTreeOutDTO categoryTreeOutDTO3 = new CategoryTreeOutDTO("Fish");
        categoryTreeOutDTO.addChildTree(categoryTreeOutDTO1);
        categoryTreeOutDTO.addChildTree(categoryTreeOutDTO2);
        categoryTreeOutDTO.addChildTree(categoryTreeOutDTO3);
        List<CategoryTreeOutDTO> expected = new ArrayList<>();
        expected.add(categoryTreeOutDTO);

        //act
        List<CategoryTreeOutDTO> standardTree = categoryService.getStandardCategoriesTree();

        //assert
        assertEquals(1, standardTree.size());
        assertEquals(expected, standardTree);
    }

    @Test
    @DisplayName("Get Standard Categories tree : no categories")
    void getStandardCategoryTreeFailure() {
        //arrange
        CategoryTreeOutDTO categoryTreeOutDTO = new CategoryTreeOutDTO("Food");
        CategoryTreeOutDTO categoryTreeOutDTO1 = new CategoryTreeOutDTO("Fruit");
        CategoryTreeOutDTO categoryTreeOutDTO2 = new CategoryTreeOutDTO("Meat");
        CategoryTreeOutDTO categoryTreeOutDTO3 = new CategoryTreeOutDTO("Fish");
        categoryTreeOutDTO.addChildTree(categoryTreeOutDTO1);
        categoryTreeOutDTO.addChildTree(categoryTreeOutDTO2);
        categoryTreeOutDTO.addChildTree(categoryTreeOutDTO3);
        List<CategoryTreeOutDTO> expected = new ArrayList<>();
        expected.add(categoryTreeOutDTO);

        //act
        List<CategoryTreeOutDTO> standardTree = categoryService.getStandardCategoriesTree();

        assertNotEquals(expected, standardTree);
    }

    @Test
    @DisplayName("Create root custom category success")
    void createRootCustomCategorySuccess() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        CustomCategoryDTO dto = new CustomCategoryDTO("Bills", null, familyID.toString());

        //act
        CustomCategoryOutDTO customRoot = categoryService.createRootCustomCategory(dto);

        CustomCategoryOutDTO customCategoryDTO = new CustomCategoryOutDTO("Bills");

        //assert
        assertEquals(customCategoryDTO.getDesignation(), customRoot.getDesignation());
        assertNotNull(customCategoryDTO);
        assertNotNull(customRoot);
    }

    @Test
    @DisplayName("Create root custom category success")
    void createAnInvalidRootCustomCategorySuccess() {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        CustomCategoryDTO dto = new CustomCategoryDTO("Bi23red2lls", null, familyID.toString());

        // act assert
        assertThrows(IllegalArgumentException.class, () -> categoryService.createRootCustomCategory(dto));
    }

    @Test
    @DisplayName("Create a null root custom category success")
    void createNullRootCustomCategorySuccess() {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        CustomCategoryDTO dto = new CustomCategoryDTO(null, null, familyID.toString());

        // act assert
        assertThrows(NullPointerException.class, () -> categoryService.createRootCustomCategory(dto));
    }

    @Test
    @DisplayName("Create root custom category success")
    void createRootCustomCategoryWithSameDesignationSuccess() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        CustomCategoryDTO dto = new CustomCategoryDTO("Bills", null, familyID.toString());

        //act
        CustomCategoryOutDTO customRoot = categoryService.createRootCustomCategory(dto);

        //assert
        assertNotNull(customRoot);
        assertThrows(DesignationNotPossibleException.class, () -> categoryService.createRootCustomCategory(dto));
    }

    @Test
    @DisplayName("Create child custom category : success")
    void createCustomChildCategory(){
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        String famID = familyID.toString();

        Designation designationRoot = new CategoryDesignation("Food");
        CategoryID rootId = categoryIDGenerator.generate();
        Category rootCategory = CategoryFactory.create(designationRoot, rootId, null, familyID);
        categoryRepository.save(rootCategory);
        String parseRootId = rootId.toString();
        CustomCategoryDTO dto = new CustomCategoryDTO("Fruit", parseRootId, famID);

        //act
        CustomCategoryOutDTO customCategoryDTO = categoryService.createChildCustomCategory(dto);

        //assert
        assertNotNull(customCategoryDTO);
    }

    @Test
    @DisplayName("Create child custom category : Unsuccessful")
    void createInvalidCustomChildCategory() {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        String famID = familyID.toString();

        Designation designationRoot = new CategoryDesignation("Food");
        CategoryID rootId = categoryIDGenerator.generate();
        Category rootCategory = CategoryFactory.create(designationRoot, rootId, null, familyID);
        categoryRepository.save(rootCategory);
        String parseRootId = rootId.toString();
        CustomCategoryDTO dto = new CustomCategoryDTO("sa67d", parseRootId, famID);

        //act and act
        assertThrows(IllegalArgumentException.class, () -> categoryService.createChildCustomCategory(dto));
    }

    @Test
    @DisplayName("Create null child custom category : Unsuccessful")
    void createANullCustomChildCategory(){
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        String famID = familyID.toString();

        Designation designationRoot = new CategoryDesignation("Food");
        CategoryID rootId = categoryIDGenerator.generate();
        Category rootCategory = CategoryFactory.create(designationRoot, rootId, null, familyID);
        categoryRepository.save(rootCategory);
        String parseRootId = rootId.toString();
        CustomCategoryDTO dto = new CustomCategoryDTO(null, parseRootId, famID);

        //act and act
        assertThrows(NullPointerException.class, () -> categoryService.createChildCustomCategory(dto));
    }

    @Test
    @DisplayName("Create child custom category - same designation as CustomCategoryOutDTO")
    void sameDesignationAsOutDTO() {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Designation rootDesignation = new CategoryDesignation("Roupa");
        CategoryID rootID = new CategoryID(UUID.randomUUID().toString());
        Designation childDesignation = new CategoryDesignation("Roupa Criança");
        CategoryID childID = new CategoryID(UUID.randomUUID().toString());

        //act
        Category rootCustom = CategoryFactory.create(rootDesignation, rootID, null, familyID);
        categoryRepository.save(rootCustom);
        Category childCustom = CategoryFactory.create(childDesignation, childID, rootID, familyID);
        categoryRepository.save(childCustom);

        CustomCategoryOutDTO customCategoryDTO = new CustomCategoryOutDTO("Roupa Criança");
        String result = childCustom.getDesignation().toString();

        //assert
        assertEquals(customCategoryDTO.getDesignation(), childCustom.getDesignation().toString());
        assertNotNull(customCategoryDTO);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Same designation as CategoryTreeOutDTO")
    void sameDesignationAsOutTreeDTO() {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Designation rootDesignation = new CategoryDesignation("Bills");
        CategoryID rootID = new CategoryID(UUID.randomUUID().toString());

        //act
        Category rootCustom = CategoryFactory.create(rootDesignation, rootID, null, familyID);
        categoryRepository.save(rootCustom);

        CategoryTreeOutDTO treeDTO = new CategoryTreeOutDTO("Bills");

        //assert
        assertEquals(treeDTO.getDesignation(), rootCustom.getDesignation().toString());
        assertNotNull(treeDTO);
    }

    @Test
    @DisplayName("Same designation as child categories - tree DTO")
    void sameDesignationAsChildCategoriesDTO() {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Designation rootDesignation = new CategoryDesignation("Roupa");
        CategoryID rootID = new CategoryID(UUID.randomUUID().toString());
        Designation childDesignation = new CategoryDesignation("Roupa Criança");
        CategoryID childID = new CategoryID(UUID.randomUUID().toString());

        //act
        Category rootCustom = CategoryFactory.create(rootDesignation, rootID, null, familyID);
        categoryRepository.save(rootCustom);
        Category childCustom = CategoryFactory.create(childDesignation, childID, rootID, familyID);
        categoryRepository.save(childCustom);

        CategoryTreeOutDTO treeDTO = new CategoryTreeOutDTO("Roupa");
        CategoryTreeOutDTO childTreeDTO = new CategoryTreeOutDTO("Roupa Criança");
        treeDTO.addChildTree(childTreeDTO);

        //assert
        assertEquals(childTreeDTO.getDesignation(), childCustom.getDesignation().toString());
        assertNotNull(childTreeDTO);

    }

    @Test
    @DisplayName("Create child standard category : success")
    void createChildStandardCategory() throws Exception {
        //arrange

        Designation designationRoot = new CategoryDesignation("Food");
        CategoryID rootId = categoryIDGenerator.generate();
        Category root = CategoryFactory.create(designationRoot, rootId, null);
        categoryRepository.save(root);
        String parseRootId = rootId.toString();

        //act
        StandardCategoryOutDTO standard = categoryService.createChildStandardCategory("Fruit", parseRootId);


        //assert
        assertNotNull(standard);
    }

    @Test
    @DisplayName("Get Standard Tree - Success")
    void getStandardCategoriesTreeSuccess() throws Exception {
        Designation rootDesignation = new CategoryDesignation("Despesas");
        CategoryID rootID = categoryIDGenerator.generate();
        Category catRoot = CategoryFactory.create(rootDesignation, rootID, null);
        categoryRepository.save(catRoot);
        categoryService.createChildStandardCategory("Gas", rootID.toString());
        categoryService.createChildStandardCategory("Eletricidade", rootID.toString());
        categoryService.createChildStandardCategory("Água", rootID.toString());

        CategoryTreeOutDTO treeRoot = new CategoryTreeOutDTO(rootDesignation.toString());
        String expected = rootDesignation.toString();

        assertEquals(expected, treeRoot.getDesignation());
    }

    @Test
    void getChildCategoriesListEmpty() throws ElementNotFoundException, DesignationNotPossibleException {
        Designation rootDesignation = new CategoryDesignation("Despesas");
        Designation childDesignation = new CategoryDesignation("Gas");
        CategoryID rootID = categoryIDGenerator.generate();
        CategoryID childID = categoryIDGenerator.generate();
        Category catRoot = CategoryFactory.create(rootDesignation, rootID, null);
        categoryRepository.save(catRoot);

        Category childStandard = CategoryFactory.create(childDesignation, childID, rootID);
        categoryRepository.save(childStandard);

        categoryService.createChildStandardCategory("Natural", childID.toString());

        List<CategoryTreeOutDTO> categoriesList = categoryService.getStandardCategoriesTree();

        int expected = 1;

        assertEquals(expected, categoriesList.size());

    }

    @Test
    void getStandardChildCategories() throws ElementNotFoundException, DesignationNotPossibleException {
        //arrange
        CategoryID parentID = new CategoryID(UUID.randomUUID().toString());
        Designation parentDesignation = new CategoryDesignation("Contas");
        Category parent = CategoryFactory.create(parentDesignation, parentID, null);
        categoryRepository.save(parent);

        FamilyID famID = new FamilyID(UUID.randomUUID());
        CustomCategoryDTO dto = new CustomCategoryDTO("Eletricidade", parentID.toString(), famID.toString());


        categoryService.createChildStandardCategory("Gás", parentID.toString());
        categoryService.createChildStandardCategory("Água", parentID.toString());
        categoryService.createChildCustomCategory(dto);

        List<CategoryTreeOutDTO> categories = categoryService.getStandardCategoriesTree();
        List<CategoryTreeOutDTO> children = categories.get(0).getChildren();
        int expected = 2;

        assertEquals(expected, children.size());
    }


    @Test
    @DisplayName("Get category tree with multiple child categories")
    void constructTreeWithMultipleCategoryChildren() throws Exception {
        //arrange
        Designation parentDesignation = new CategoryDesignation("Food");
        CategoryID Id = new CategoryID(UUID.randomUUID().toString());
        Category parentCategory = CategoryFactory.create(parentDesignation, Id, null);
        categoryRepository.save(parentCategory);
        categoryService.createChildStandardCategory("Dinner", Id.toString());
        categoryService.createChildStandardCategory("Lunch", Id.toString());
        categoryService.createChildStandardCategory("Breakfast", Id.toString());

        //act
        List<CategoryTreeOutDTO> result = categoryService.getStandardCategoriesTree();
        //assert
        assertNotNull(result);
        for (CategoryTreeOutDTO categoryTreeDTO : result) {
            assertNotNull(categoryTreeDTO);
        }
    }

    @Test
    @DisplayName("Get List of standard categories")
    void getListOfStandardCategories() {
        //arrange
        List<StandardCategoryOutDTO> outList = new ArrayList<>();
        Designation parentDesignation = new CategoryDesignation("Abacaxi");
        CategoryID Id = new CategoryID(UUID.randomUUID().toString());
        Category parentCategory = CategoryFactory.create(parentDesignation, Id, null);
        categoryRepository.save(parentCategory);

        //act
        StandardCategoryOutDTO standardCategoryOutDTO = StandardCategoryOutDTOMapper.toDTO(parentCategory);
        outList.add(standardCategoryOutDTO);
        List<StandardCategoryOutDTO> result = categoryService.getListOfStandardCategories();

        //assert
        assertEquals(result, outList);
        assertNotNull(result);
    }


    @Test
    @DisplayName("Get List of standard categories")
    void getListOfAllStandardCategories() {
        //arrange
        List<StandardCategoryOutDTO> outList = new ArrayList<>();
        Designation parentDesignation = new CategoryDesignation("Car");
        CategoryID Id = new CategoryID(UUID.randomUUID().toString());
        Category parentCategory = CategoryFactory.create(parentDesignation, Id, null);
        categoryRepository.save(parentCategory);


        //act
        StandardCategoryOutDTO standardCategoryOutDTO = StandardCategoryOutDTOMapper.toDTO(parentCategory);

        outList.add(standardCategoryOutDTO);

        List<StandardCategoryOutDTO> result = categoryService.getListOfAllStandardCategories();

        //assert
        //assertEquals(result, outList);
        assertNotNull(result);
    }




    @Test
    @DisplayName("Get the right category id")
    void getTheRightCategoryID() throws ElementNotFoundException {
        //arrange
        Designation parentDesignation = new CategoryDesignation("Food");
        CategoryID Id = new CategoryID(UUID.randomUUID().toString());
        Category parentCategory = CategoryFactory.create(parentDesignation, Id, null);
        categoryRepository.save(parentCategory);
        //act
        CategoryOutDTO dto = categoryService.getCategoryByID(Id.toString());
        String designation = dto.getDesignation();
        String id = dto.getId();
        //assert
        assertEquals(designation, parentDesignation.toString());
        assertEquals(id, Id.toString());
    }

    @Test
    @DisplayName("Get the right category id after adding second one")
    void getTheRightCategoryIDAfterAddingSecondCategory() throws ElementNotFoundException, DesignationNotPossibleException {
        //arrange
        Designation parentDesignation = new CategoryDesignation("Food");
        CategoryID Id = new CategoryID(UUID.randomUUID().toString());
        Category parentCategory = CategoryFactory.create(parentDesignation, Id, null);
        categoryRepository.save(parentCategory);
        categoryService.createChildStandardCategory("Dinner", Id.toString());
        //act
        CategoryOutDTO dto = categoryService.getCategoryByID(Id.toString());
        String designation = dto.getDesignation();
        String id = dto.getId();
        //assert
        assertEquals(designation, parentDesignation.toString());
        assertEquals(id, Id.toString());
    }

    @Test
    @DisplayName("Get category that doesn't exist")
    void getCategoryThatDontExist() {
        //act and assert
        assertThrows(ElementNotFoundException.class, () -> categoryService.getCategoryByID("191"));
    }

    @Test
    @DisplayName("Create root standard category successfully")
    void createRootStandardCategorySuccess() throws Exception {
        //arrange
        String designation ="Food";
        //act
        StandardCategoryOutDTO standardRoot = categoryService.createRootStandardCategory(designation);

        //assert
        assertNotNull(standardRoot);
    }

    @Test
    @DisplayName("Create root standard category failure: same designation")
    void createStandardRootCategoryFailure() throws Exception {
        //arrange
        String designation ="Transportation";
        //act
        categoryService.createRootStandardCategory(designation);


        //assert
        assertThrows(DesignationNotPossibleException.class, () -> categoryService.createRootStandardCategory("Transportation"));
    }


    @Test
    @DisplayName("Create child standard category failure : same designation as sister category")
    void createChildStandardCategoryFailure1() throws Exception {
        //arrange

        Designation designationRoot = new CategoryDesignation("Food");
        CategoryID rootId = categoryIDGenerator.generate();
        Category rootCategory = CategoryFactory.create(designationRoot, rootId, null);
        categoryRepository.save(rootCategory);
        String parseRootId = rootId.toString();

        //act
        categoryService.createChildStandardCategory("Fruit", parseRootId);

        //assert
        assertThrows(DesignationNotPossibleException.class, () -> categoryService.createChildStandardCategory("Fruit", parseRootId));
    }

}