package switchtwentytwenty.project.applicationservice.appservice.iappservice;

import switchtwentytwenty.project.dto.outdto.CategoryTreeOutDTO;
import switchtwentytwenty.project.dto.outdto.CustomCategoryOutDTO;
import switchtwentytwenty.project.dto.outdto.StandardCategoryOutDTO;
import switchtwentytwenty.project.dto.outdto.CategoryOutDTO;
import switchtwentytwenty.project.dto.toservicedto.CustomCategoryDTO;
import switchtwentytwenty.project.exception.*;

import java.util.List;

public interface ICategoryService {
    /**
     * Create a root standard category
     *
     * @param designation - designation
     * @throws DesignationNotPossibleException
     */
    StandardCategoryOutDTO createRootStandardCategory(String designation) throws DesignationNotPossibleException;

    /**
     * Create a child standard category
     *
     * @param designation - designation
     * @param parentID    category parent ID
     * @throws ElementNotFoundException
     * @throws DesignationNotPossibleException
     */
    StandardCategoryOutDTO createChildStandardCategory(String designation, String parentID) throws ElementNotFoundException, DesignationNotPossibleException;

    /**
     * Creates a root custom category.
     *
     * @param dto - custom category dto
     * @return custom category DTO
     * @throws DesignationNotPossibleException if the designation already exists
     */
    CustomCategoryOutDTO createRootCustomCategory(CustomCategoryDTO dto) throws DesignationNotPossibleException;


    /**
     * Creates a child custom category.
     *
     * @param dto - custom category dto
     * @return a custom category DTO
     * @throws ElementNotFoundException        if the ID of the parent doesn't exist
     * @throws DesignationNotPossibleException if the designation already exists
     */
    CustomCategoryOutDTO createChildCustomCategory(CustomCategoryDTO dto) throws DesignationNotPossibleException;


    /**
     * Iterates over the list of standard categories where parent is null,creates a tree and adds it to a new list of trees.
     *
     * @return list with all the category trees dtos of the system
     */
    List<CategoryTreeOutDTO> getStandardCategoriesTree();

    /**
     * Get list of the categories on the family’s category tree.
     *
     * @param familyID - family identifier
     * @return list of categories
     */
    List<CategoryOutDTO> getListOfFamilyCategories(String familyID);

    /**
     * Gets all the standard categories in the system and adds them to a new list.
     *
     * @return list with standard categories DTOs
     */
    List<StandardCategoryOutDTO> getListOfStandardCategories();

    /**
     * Get all the standard categories in the system and all the standard category from the external source.
     *
     * @return list with standard categories DTOs
     */
    List<StandardCategoryOutDTO> getListOfAllStandardCategories();

    /**
     * Get category by identifier.
     *
     * @param categoryID category identifier
     * @return category dto
     */
    CategoryOutDTO getCategoryByID(String categoryID) throws ElementNotFoundException;
}



