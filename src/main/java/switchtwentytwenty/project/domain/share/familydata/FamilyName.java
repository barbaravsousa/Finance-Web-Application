package switchtwentytwenty.project.domain.share.familydata;

import switchtwentytwenty.project.domain.share.dddtype.ValueObject;

import java.util.Objects;

public class FamilyName implements ValueObject {

    //Attributes

    private final String name;

    //Constructor Methods
    /**
     * Sole Constructor
     * @param name
     */
    public FamilyName(String name) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Invalid family name");
        }
        this.name = name;
    }

    //Getter and Setters

    //Business Methods

    /**
     * Methods that validate family name
     *
     * @param name is the name we want to see if is valid
     * @return true is the name is valid, false if not
     */
    private static boolean isValidName(String name) {
        if (name == null || name.trim().length() == 0) {
            return false;
        }
        return name.matches("[A-zÀ-ÿ\\s']*");
        //Does not allow for special characters or numeric characters but does allow for apostrophes and for
        //accents (for example á)
    }

    //Equals toString and HashCode

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
        FamilyName that = (FamilyName) other;
        return Objects.equals(name, that.name);
    }

    /**
     * Override hashCode method
     *
     * @return true if the same hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Override toString method
     *
     * @return String instance of the value
     */
    @Override
    public String toString() {
        return this.name;
    }
}
