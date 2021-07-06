package switchtwentytwenty.project.domain.share.id;

import java.util.Objects;
import java.util.UUID;

public class FamilyID implements ID {

    //Attribute
    private final UUID id;


    //Constructor Methods

    /**
     * Sole constructor
     *
     * @param id - family id
     */
    public FamilyID(UUID id) {
        Objects.requireNonNull(id);
        this.id = id;
    }
    //Getter and Setters


    //Business Methods

    //Equals and HashCode

    /**
     * Override equals method
     *
     * @param other - other value object
     * @return true if there is a match
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        FamilyID familyID = (FamilyID) other;
        return Objects.equals(id, familyID.id);
    }

    /**
     * Override hashCode method
     *
     * @return true if the same hash code
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
