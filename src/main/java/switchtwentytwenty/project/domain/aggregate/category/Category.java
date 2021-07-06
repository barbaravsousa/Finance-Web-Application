package switchtwentytwenty.project.domain.aggregate.category;

import switchtwentytwenty.project.domain.share.dddtype.AggregateRoot;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;

public interface Category extends AggregateRoot<Category, CategoryID> {

    /**
     * Verifies if the category is custom
     *
     * @return false if the category is standard
     */
    boolean isStandard();

    /**
     * Verifies if custom category belongs to given family
     *
     * @param familyID - familyID
     * @return true, if belongs to family
     */
    boolean belongsToFamily(FamilyID familyID);

    /**
     * Check if category has a specific designation
     *
     * @param designation - designation
     * @return true, if designations are the same
     */
    boolean hasSameDesignation(Designation designation);

    /**
     * Gets category designation
     *
     * @return category designation
     */
    Designation getDesignation();

    /**
     * Get's the parent category ID.
     *
     * @return parent ID
     */
    CategoryID getParentID();

    /**
     * Get category ID.
     *
     * @return id
     */
    CategoryID getID();

    /**
     * Get familyID from custom category
     *
     * @return familyID
     */
    FamilyID getFamilyID();

}
