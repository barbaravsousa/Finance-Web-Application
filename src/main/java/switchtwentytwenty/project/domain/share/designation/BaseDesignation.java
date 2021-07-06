package switchtwentytwenty.project.domain.share.designation;

import switchtwentytwenty.project.util.Util;

import java.util.Objects;
import java.util.regex.Pattern;

abstract class BaseDesignation{

    //Constants
    private static final int MINIMAL_CHARACTERS = 3;

    //Attributes
    protected String designation;

    //Constructor

    /**
     * Sole Constructor
     * @param designation - designation
     */
    protected BaseDesignation(String designation){
        this.designation = validateDesignation(designation);
    }

    //Business Methods
    /**
     * All validations needed to create a designation.
     * @param designation - designation.
     * @return The validated designation.
     */
    public String validateDesignation(String designation){
        validate(designation);
        designation = Util.capitalizeFirstLetters(designation);
        postValidate(designation);

        return designation;
    }

    /**
     * Method that assures the correct format of the Standard Category's designation regarding its character composition
     *
     * @param designation String designation of a Standard Category with a well defined format
     * @return returns true if the designation String has no numeric or special characters other than apostrophe (and accent letters)
     */
    protected static boolean checkFormat(String designation) {

        String designationRegex = "[A-zÀ-ÿ\\s']*"; //does not allow for special characters or numeric characters but does allow for apostrophes and
        // for accents (for example á)

        Pattern pat = Pattern.compile(designationRegex);
        return pat.matcher(designation).matches();
    }

    /**
     * Method that validates the Standard Category's designation.
     *
     * @param designation String containing designation of a Standard Category
     * @return returns true if the designation String is not null, empty or blank and if the Standard Category has the desired format, containing
     * no numeric or special characters. Otherwise, returns false.
     */
    public static void validate(String designation) {
        if (designation == null) {
            throw new NullPointerException("Category must not be null");
        }

        if (!checkFormat(designation)) {
            throw new IllegalArgumentException("Numeric characters and special characters are not allowed");
        }
    }

    /**
     * ToString method
     * @return - variable in string
     */
    @Override
    public String toString(){
        return this.designation;
    }

    /**
     * Method that validates the designation String's length. It is assumed that a category can not have less than 3 characters
     *
     * @param designation a String containing a category name
     * @return true if the String has at least a length of 3
     */
    private static void postValidate(String designation) {
        if (designation.length() < MINIMAL_CHARACTERS) {
            throw new IllegalArgumentException("Category must have at least 3 characters");
        }
    }

    /**
     * Equal Method
     * @param o - other objetc
     * @return true, if both objects are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseDesignation)) return false;
        BaseDesignation that = (BaseDesignation) o;
        return Objects.equals(designation, that.designation);
    }

    /**
     * Generate hashcode
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }
}
