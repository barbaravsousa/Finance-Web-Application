package switchtwentytwenty.project.domain.share.id;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;



public class AccountID implements ID, Serializable {

    //Attribute
    private final UUID id;

    /**
     * Constructor methods
     *
     * @param id - id
     */
    public AccountID(UUID id) {
        Objects.requireNonNull(id);
        this.id = id;
    }


    /**
     * Equals Method
     * @param o - object
     * @return true if obsjects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountID)) return false;
        AccountID that = (AccountID) o;
        return Objects.equals(id, that.id);
    }

    /**
     * hash code method
     * @return hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Override method toString.
     *
     * @return ID in string format.
     */
    @Override
    public String toString() {
        return this.id.toString();
    }
}

