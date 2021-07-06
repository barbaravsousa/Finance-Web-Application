package switchtwentytwenty.project.domain.aggregate.category;

import lombok.Getter;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;

import java.util.Objects;

public class Custom extends BaseCategory {

    //Attributes
    @Getter
    private final FamilyID familyID;


    //Constructor Method

    /**
     * Constructor of a category.
     *
     * @param categoryDesignation of the category
     */
    protected Custom(Designation categoryDesignation, CategoryID categoryID, CategoryID parentID, FamilyID familyID) {
        super(categoryDesignation, categoryID, parentID);
        this.familyID = familyID;
    }


    //Getters and Setters


    //Business Methods


    /**
     * Verifies if the category is custom
     *
     * @return false if the category is standard
     */
    @Override
    public boolean isStandard() {
        return false;
    }


    /**
     * Verifies if custom category belongs to given family
     *
     * @param familyID - familyID
     * @return true, if belongs to family
     */
    @Override
    public boolean belongsToFamily(FamilyID familyID) {
        return this.familyID.equals(familyID);
    }

    /**
     * Check if category has the same value as other category
     *
     * @param other - the other entity.
     * @return true, if the categories are the same
     */
    @Override
    public boolean sameValueAs(Category other) {
        if (other.isStandard()) {
            return false;
        }
        return super.hasSameValueAs(other) && this.familyID.equals(other.getFamilyID());

    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Custom custom = (Custom) o;
        return Objects.equals(familyID, custom.familyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), familyID);
    }
}

