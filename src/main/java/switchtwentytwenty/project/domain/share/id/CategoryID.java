package switchtwentytwenty.project.domain.share.id;

import java.util.Objects;

public class CategoryID implements ID {

    //Attribute
    private final String id;

    /**
     * Constructor methods
     * @param id - id
     */
    public CategoryID(String id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    /**
     * Override method toString.
     *
     * @return ID in string format.
     */
    @Override
    public String toString(){
        return this.id;
    }

    /**
     * Equal Method
     * @param o - object
     * @return true if objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryID)) return false;
        CategoryID that = (CategoryID) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Hash code method
     * @return hashcode value
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
