package switchtwentytwenty.project.domain.share.designation;

public class TransactionDescription extends BaseDesignation implements Designation {

    //Constructor

    /**
     * Sole Constructor
     *
     * @param designation - designation
     */
    public TransactionDescription(String designation) {
        super(designation);
    }

    /**
     * Equal Method
     *
     * @param o - other object
     * @return true, if both objects are the same
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * Generate hashcode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Override toString.
     *
     * @return a string
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
