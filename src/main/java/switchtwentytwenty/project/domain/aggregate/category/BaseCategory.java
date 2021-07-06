package switchtwentytwenty.project.domain.aggregate.category;

import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;

import java.util.Objects;

abstract class BaseCategory implements Category {

    //Attributes
    protected CategoryID id;
    protected Designation categoryDesignation;
    protected CategoryID parentID;


    //Constructor Methods

    /**
     * Constructor of a base category.
     *
     * @param categoryDesignation - designation
     */
    protected BaseCategory(Designation categoryDesignation, CategoryID categoryID, CategoryID parentID) {
        this.parentID = parentID;
        this.categoryDesignation = categoryDesignation;
        this.id = categoryID;
    }


    //Getter and Setters

    /**
     * Get category ID.
     *
     * @return id
     */
    public CategoryID getID() {
        return this.id;
    }

    /**
     * Get's the parent category ID.
     *
     * @return parent ID
     */
    public CategoryID getParentID() {
        return this.parentID;
    }

    //Business Methods

    /**
     * Check if category has a specific designation
     *
     * @param categoryDesignation - designation
     * @return true, if designations are the same
     */
    public boolean hasSameDesignation(Designation categoryDesignation) {
        return this.categoryDesignation.equals(categoryDesignation);
    }


    //Equals and HashCode

    /**
     * Equal method
     *
     * @param o - other object
     * @return true, if both objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseCategory that = (BaseCategory) o;
        return Objects.equals(id, that.id);
    }


    /**
     * Generate hashcode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, categoryDesignation);
    }


    /**
     * Gets category designation
     *
     * @return category designation
     */
    public Designation getDesignation() {
        return this.categoryDesignation;
    }

    /**
     * Check if category has the same value as another category
     *
     * @param other - the other entity.
     * @return true, if the categories are the same
     */
    public boolean hasSameValueAs(Category other) {
        if (this.parentID != null && other.getParentID() != null) {
            return this.parentID.equals(other.getParentID()) && this.id.equals(other.getID()) && (other.getDesignation().equals(getDesignation()));
        } else if (this.parentID == null && other.getParentID() == null) {
            return this.id.equals(other.getID()) && (other.getDesignation().equals(getDesignation()));
        }
        return false;
    }

    /**
     * Confirms if the category has the same ID.
     *
     * @param categoryID - Category ID to compare with
     * @return true if there is a match
     */
    public boolean hasSameID(CategoryID categoryID) {
        if (categoryID == null) {
            throw new NullPointerException("Category ID is null");
        }
        return this.id.equals(categoryID);
    }
}

