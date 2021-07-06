package switchtwentytwenty.project.domain.share.designation;

public class CategoryDesignation extends BaseDesignation implements Designation{

    //Constructor
    /**
     * Sole Constructor
     * @param designation - designation
     */
    public CategoryDesignation(String designation){
        super(designation);
        this.designation = validateDesignation(designation);
    }

    /**
     * Equal Method
     * @param o - other objetc
     * @return true, if both objects are the same
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * Generate hashcode
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
