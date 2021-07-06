package switchtwentytwenty.project.interfaceadaptor.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import switchtwentytwenty.project.datamodel.CategoryJPA;

public interface CategoryJPARepository extends CrudRepository<CategoryJPA, String> {

    /**
     * find category through familyID
     *
     * @param familyID - familyID
     * @return category jpa
     */
    @Query("FROM CategoryJPA WHERE familyId = ?1 or isStandard = true")
    Iterable<CategoryJPA> findByFamilyID(String familyID);

    /**
     * Finds category through designation.
     *
     * @param designation of the category
     * @return category jpa
     */
    @Query("FROM CategoryJPA WHERE parentID IS NULL and designation = ?1")
    Iterable<CategoryJPA> findCategoryJPAByDesignation(String designation);

    /**
     * Finds standard categories.
     *
     * @return category jpa
     */
    @Query("FROM CategoryJPA WHERE isStandard = true")
    Iterable<CategoryJPA> findCategoryJPAStandard();

    /**
     * Finds all the categories that have the same parent.
     *
     * @param designation of the category
     * @param parentID    of the category
     * @return category jpa
     */
    @Query("FROM CategoryJPA WHERE parentID = ?1")
    Iterable<CategoryJPA> findCategoryJPAByParentIDWithDesignation(String parentID, String designation);

    /**
     * Iterates over a list of Category JPA's that have the same parent ID and are standararentCategory.isStandard() && categoryRepository.containsDesignationWithSameParent(parseCategoryDesignation, parentCategoryID)) {
     * throw new Designation, transforms the JPA into a domain
     * object and adds them to a new list.
     *
     * @param parentID of the category
     * @return a list of standard categories and have the same
     */
    @Query("FROM CategoryJPA WHERE parentID = ?1 and isStandard = true")
    Iterable<CategoryJPA> findCategoryJPAStandardByParentID(String parentID);

    /**
     * Iterates over the list of categories in the database with the same given parent ID, transform's  them into domain objects and
     * adds them to a new list.
     *
     * @param parentID of the category
     * @return list with all the categories with same parentID
     */
    @Query("FROM CategoryJPA WHERE parentID = ?1")
    Iterable<CategoryJPA> findCategoryJPAByParentID(String parentID);
}
