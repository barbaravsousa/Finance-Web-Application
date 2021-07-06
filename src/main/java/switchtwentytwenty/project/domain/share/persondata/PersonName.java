package switchtwentytwenty.project.domain.share.persondata;

import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import switchtwentytwenty.project.exception.InvalidPersonNameException;
import switchtwentytwenty.project.util.Util;

import java.util.Objects;


public class PersonName implements ValueObject {

    //Attributes
    private final String name;

    //Constructor Methods

    /**
     * Sole Constructor
     * @param name - person name
     * @throws InvalidPersonNameException
     */
    public PersonName (String name) throws InvalidPersonNameException {
       isValidName(name);
       this.name = Util.capitalizeFirstLetters(name);

    }
    //Getter and Setters

    // Business Methods

    /**
     * Method that returns true if the name is valid and only has letters.
     *
     * @param name - Name of the person that was added to the application
     */
    private void isValidName(String name) throws InvalidPersonNameException {
        if (name == null || name.trim().length() == 0 || !name.matches("[A-zÀ-ÿ\\s']*")) {
            throw new InvalidPersonNameException("Person name invalid");
        }
    }


    //Equals and HashCode

    /**
     * Override equals().
     * Just compare name attribute
     *
     * @param object to be compare with
     * @return true if there is a match
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PersonName that = (PersonName) object;
        return Objects.equals(name, that.name);
    }

    /**
     * Override hashCode
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Override toString.
     *
     * @return Corresponding string of person name.
     */
    @Override
    public String toString() {
        return name;
    }
}
