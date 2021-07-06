package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.ApplicationContextProvider;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryIDGenerator;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.applicationservice.irepository.ICategoryRepository;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.dto.outdto.*;
import switchtwentytwenty.project.dto.toservicedto.CustomCategoryDTO;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;
import switchtwentytwenty.project.exception.DesignationNotPossibleException;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.repository.http.irepository.IExternalCategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {

    //Attributes

    @Autowired
    private final ICategoryRepository categoryRepository;
    @Autowired
    private final ICategoryIDGenerator categoryIDGenerator;
    @Autowired
    private final ApplicationContextProvider applicationContext;

    //Business Methods

    /**
     * Create Root Standard Category
     *
     * @param designation - category's designation
     * @throws DesignationNotPossibleException when designation already exists
     */
    public StandardCategoryOutDTO createRootStandardCategory(String designation) throws DesignationNotPossibleException {
        Designation parseCategoryDesignation = new CategoryDesignation(designation);
        if (categoryRepository.containsRootDesignation(parseCategoryDesignation)) {
            throw new DesignationNotPossibleException();
        }
        CategoryID categoryID = categoryIDGenerator.generate();
        Category rootCategory = CategoryFactory.create(parseCategoryDesignation, categoryID, null);
        Category responseRootCategory = categoryRepository.save(rootCategory);
        return new StandardCategoryOutDTO(responseRootCategory.getDesignation().toString(), responseRootCategory.getID().toString(), null);
    }

    /**
     * Create Child Standard Category
     *
     * @param designation category's designation
     * @param parentID    - parentID
     */
    public StandardCategoryOutDTO createChildStandardCategory(String designation, String parentID) throws ElementNotFoundException, DesignationNotPossibleException {
        Designation parseCategoryDesignation = new CategoryDesignation(designation);
        CategoryID parentCategoryID = new CategoryID(parentID);
        Category parentCategory = categoryRepository.findByID(parentCategoryID);
        if (parentCategory.isStandard() && categoryRepository.containsDesignationWithSameParent(parentCategoryID, parseCategoryDesignation)) {
            throw new DesignationNotPossibleException();
        }
        CategoryID categoryID = categoryIDGenerator.generate();
        Category childCategory = CategoryFactory.create(parseCategoryDesignation, categoryID, parentCategoryID);
        Category responseChildCategory = categoryRepository.save(childCategory);
        return new StandardCategoryOutDTO(responseChildCategory.getDesignation().toString(), responseChildCategory.getID().toString(), responseChildCategory.getParentID().toString());
    }

    /**
     * Creates a root custom category.
     *
     * @param dto - custom category dto
     * @return custom category DTO
     * @throws DesignationNotPossibleException if the designation already exists
     */
    public CustomCategoryOutDTO createRootCustomCategory(CustomCategoryDTO dto) throws DesignationNotPossibleException {
        FamilyID familyId = new FamilyID(UUID.fromString(dto.getFamilyID()));
        Designation parseDesignation = new CategoryDesignation(dto.getDesignation());
        if (categoryRepository.containsRootDesignation(parseDesignation)) {
            throw new DesignationNotPossibleException();
        }

        CategoryID categoryID = categoryIDGenerator.generate();
        Category rootCustomCategory = CategoryFactory.create(parseDesignation, categoryID, null, familyId);
        Category responseRootCustom = categoryRepository.save(rootCustomCategory);
        return new CustomCategoryOutDTO(responseRootCustom.getDesignation().toString());
    }

    /**
     * Creates a child custom category.
     *
     * @param dto - custom category dto
     * @return a custom category DTO
     * @throws NullPointerException     if the ID of the parent doesn't exist
     * @throws IllegalArgumentException if the designation already exists
     */
    public CustomCategoryOutDTO createChildCustomCategory(CustomCategoryDTO dto) {
        FamilyID categoryFamilyID = new FamilyID(UUID.fromString(dto.getFamilyID()));
        CategoryID parentCategoryID = new CategoryID(dto.getParentID());
        Designation parseDesignation = new CategoryDesignation(dto.getDesignation());

        CategoryID categoryID = categoryIDGenerator.generate();
        Category childCustomCategory = CategoryFactory.create(parseDesignation, categoryID, parentCategoryID, categoryFamilyID);
        Category responseChildCustom = categoryRepository.save(childCustomCategory);
        return new CustomCategoryOutDTO(responseChildCustom.getDesignation().toString());
    }

    /**
     * Iterates over the list of standard categories where parent is null,creates a tree and adds it to a new list of trees.
     *
     * @return list with all the category trees dtos of the system
     */
    public List<CategoryTreeOutDTO> getStandardCategoriesTree() {
        List<Category> standardCategories = categoryRepository.getStandardCategories();
        List<CategoryTreeOutDTO> trees = new ArrayList<>();

        for (Category category : standardCategories) {
            if (category.getParentID() == null) {
                CategoryTreeOutDTO rootTree = new CategoryTreeOutDTO(category.getDesignation().toString());
                constructTree(rootTree, category, true);
                trees.add(rootTree);
            }
        }
        return trees;
    }

    /**
     * Method that iterates over a list of categories with the same parent, creates a tree and adds the child trees.
     *
     * @param tree         - Out category tree dto
     * @param baseCategory - category
     * @return category tree dto
     */
    private CategoryTreeOutDTO constructTree(CategoryTreeOutDTO tree, Category baseCategory, boolean onlyStandard) {
        CategoryID baseCategoryID = baseCategory.getID();
        List<Category> categoriesWithSameParent;
        if (onlyStandard) {
            categoriesWithSameParent = categoryRepository.getListOfStandardCategoriesWithSameParent(baseCategoryID);
        } else {
            categoriesWithSameParent = categoryRepository.getListOfCategoriesWithSameParent(baseCategoryID);
        }

        for (Category category : categoriesWithSameParent) {
            CategoryTreeOutDTO childTree = new CategoryTreeOutDTO(category.getDesignation().toString());
            constructTree(childTree, category, onlyStandard);
            tree.addChildTree(childTree);
        }
        return tree;
    }

    /**
     * Get list of the categories on the family’s category tree.
     *
     * @param familyID - family identifier
     * @return list of categories internal and external
     */
    public List<CategoryOutDTO> getListOfFamilyCategories(String familyID) {
        FamilyID parsedFamilyID = new FamilyID(UUID.fromString(familyID));

        //get internal standard and custom categories linked to this family.
        List<Category> customCategoryList = categoryRepository.getListOfFamilyCategories(parsedFamilyID);
        List<CategoryOutDTO> categoryOutDTOList = CategoryOutDTOMapper.toDTOList(customCategoryList);
        //get external standard categories
        List<StandardCategoryOutDTO> standardCategoryOutDTOList = getListOfExternalStandardCategories();
        //add external standard categories to the internal and custom categories.
        categoryOutDTOList.addAll(StandardCategoryOutDTOMapper.toOutCategoryDTOList(standardCategoryOutDTOList));

        return categoryOutDTOList;
    }

    /**
     * Get list of all standard categories in the system and the external source.
     *
     * @return list of categories dto
     */
    public List<StandardCategoryOutDTO> getListOfAllStandardCategories() {
        List<StandardCategoryOutDTO> standardCategoryOutDTOS = new ArrayList<>();
        standardCategoryOutDTOS.addAll(getListOfStandardCategories());
        standardCategoryOutDTOS.addAll(getListOfExternalStandardCategories());
        return standardCategoryOutDTOS;
    }

    /**
     * Gets all the standard categories in the system and adds them to a new list.
     *
     * @return list with standard categories DTOs
     */
    public List<StandardCategoryOutDTO> getListOfStandardCategories() {
        List<Category> standardCategories = categoryRepository.getStandardCategories();
        List<StandardCategoryOutDTO> standardCategoryOutDTOS = new ArrayList<>();

        for (Category standardCategory : standardCategories) {
            StandardCategoryOutDTO standardCategoryOutDTO = StandardCategoryOutDTOMapper.toDTO(standardCategory);
            standardCategoryOutDTOS.add(standardCategoryOutDTO);
        }
        return standardCategoryOutDTOS;
    }

    /**
     * Get all the standard categories from the external source.
     *
     * @return list with standard categories DTOs
     */
    private List<StandardCategoryOutDTO> getListOfExternalStandardCategories() {
        IExternalCategoryRepository httpRepository = (IExternalCategoryRepository) applicationContext.getApplicationContext().getBean("groupRepo");

        List<StandardCategoryDTO> allExternalCategories = httpRepository.getStandardCategories();

        return StandardCategoryOutDTOMapper.toOutStandardCategoryDTOList(allExternalCategories);
    }

    /**
     * Get category by identifier.
     *
     * @param categoryID category identifier
     * @return category dto
     */
    public CategoryOutDTO getCategoryByID(String categoryID) throws ElementNotFoundException {
        CategoryID categoryIdentifier = new CategoryID(categoryID);
        Category category = categoryRepository.findByID(categoryIdentifier);
        return CategoryOutDTOMapper.toDTO(category);
    }

}
