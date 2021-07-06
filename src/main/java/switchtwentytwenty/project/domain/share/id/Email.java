package switchtwentytwenty.project.domain.share.id;

import switchtwentytwenty.project.exception.InvalidEmailException;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email implements ID {

    //Attributes
    private final String emailAddress;

    // Constructor Methods

    /**
     * Sole Constructor
     *
     * @param emailAddress - email address
     */
    public Email(String emailAddress) throws InvalidEmailException {
        isValid(emailAddress);
        this.emailAddress = emailAddress.trim();
    }

    //Getter and Setters


    // Business Methods

    /**
     * Method to validate the email address
     *
     * @param emailAddress that is going to be added
     */
    private static void isValid(String emailAddress) throws InvalidEmailException {
        if (emailAddress == null) {
            throw new NullPointerException("The email Address can't be null");
        }
        if (emailAddress.trim().length() == 0) {
            throw new InvalidEmailException("The email Address can't have blank spaces.");
        }
        if (!checkFormat(emailAddress)) {
            throw new InvalidEmailException("The email Address is not in the correct format.");
        }
    }

    /**
     * Method to verify if the email has the correct format
     * example: newEmail@gmail.com
     *
     * @param emailAddress - email address
     * @return true if the email is on the correct format
     */
    // Adapted from https://www.geeksforgeeks.org/check-email-address-valid-not-java/
    private static boolean checkFormat(String emailAddress) {
        String emailRegex = "[A-Z0-9a-z._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(emailAddress).matches();
    }

    /**
     * toString method
     * @return variable in string
     */
    @Override
    public String toString() {
        return this.emailAddress;
    }

    //Equals and HashCode

    /**
     * Override equals()
     *
     * @param object - Object instance
     * @return true if equal emailAddress attribute
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Email email = (Email) object;
        return Objects.equals(emailAddress, email.emailAddress);
    }

    /**
     * Override hashCode()
     *
     * @return hash number
     */
    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }
}
