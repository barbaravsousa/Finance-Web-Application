package switchtwentytwenty.project.domain.aggregate.category;

import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;

public class Standard extends BaseCategory {

    //Attributes

    //Constructor Method

    /**
     * Constructor of a category
     *
     * @param categoryDesignation of the category
     */
    protected Standard(Designation categoryDesignation, CategoryID categoryID, CategoryID parentID) {
        super(categoryDesignation, categoryID, parentID);
    }

    //Getters and Setters


    //Business Methods


    /**
     * Get familyID from Custom Categories
     *
     * @return IllegalArgumentException - not applied to standard categories
     */
    @Override
    public FamilyID getFamilyID() {
        throw new IllegalArgumentException("Does not apply to Standard Categories");
    }


    /**
     * Verifies if category is standard
     *
     * @return true if the category is standard
     */
    @Override
    public boolean isStandard() {
        return true;
    }


    /**
     * Returns true, since Standard categories belong to all the families
     *
     * @param familyID - familyID
     * @return true
     */
    @Override
    public boolean belongsToFamily(FamilyID familyID) {
        return true;
    }

    //Equal Method


    /**
     * Check if category has the same value as another category
     *
     * @param other - the other entity.
     * @return true, if the categories are the same
     */
    @Override
    public boolean sameValueAs(Category other) {
        if (!other.isStandard()) {
            return false;
        }
        return super.hasSameValueAs(other);
    }

}
