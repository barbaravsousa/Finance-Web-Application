package switchtwentytwenty.project.applicationservice.irepository;

import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.domain.aggregate.category.Category;

import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.exception.ElementNotFoundException;

import java.util.List;


public interface ICategoryRepository {

    /**
     * Transforms a Category in Category JPA and adds them to the Category JPA repository.
     *
     * @param category object
     */
    Category save(Category category);

    /**
     * Finds a Category JPA given a ID and checks if the category exists in the database.
     *
     * @param id of the category we are looking
     * @return a object Category
     * @throws ElementNotFoundException is thrown in case the id is not found
     */
    @Transactional
    Category findByID(CategoryID id) throws ElementNotFoundException;

    /**
     * Checks the list of categories in the database, to see if exists a category with the same parent that has the same designation.
     *
     * @param designation of a given category
     * @param parentID    of the category
     * @return true if the designation already exists at the same level
     * @throws ElementNotFoundException if the ID is not found in the system
     */
    boolean containsDesignationWithSameParent(CategoryID parentID,Designation designation) throws ElementNotFoundException;

    /**
     * Checks the list of categories to see if a certain designation exists.
     *
     * @param designation of a category
     * @param parentID of a category
     * @return true if the designations exists
     */
    boolean containsRootDesignation(Designation designation);

    /**
     * Iterates over the list of categories in the database and checks which ones are Standard and adds them to a new list.
     *
     * @return a list with all the standard categories.
     */
    List<Category> getStandardCategories();

    /**
     * Checks if a category ID exists in the system.
     *
     * @param categoryID of the category
     * @return false if the category doesn't exists in the system
     */
    boolean existsByID(CategoryID categoryID);

    /**
     * Get list of the categories on the family’s category tree.
     *
     * @param familyID - family identifier
     * @return list of categories
     */
    List<Category> getListOfFamilyCategories(FamilyID familyID);

    /**
     * Iterates over a list of Category JPA's that have the same parent ID and are Standard, transforms the JPA into a domain
     * object and adds them to a new list.
     *
     * @param parentID of the category
     * @return a list of standard categories with same parentID
     */
    List<Category> getListOfStandardCategoriesWithSameParent(CategoryID parentID);


    /**
     * Iterates over the list of categories in the database with the same given parent ID, transform's  them into domain objects and
     * adds them to a new list.
     *
     * @param parentID of the category
     * @return list with all the categories with same parentID
     */
    List<Category> getListOfCategoriesWithSameParent(CategoryID parentID);


    /**
     * Delete all data from repository
     */
    void deleteAll();
}


