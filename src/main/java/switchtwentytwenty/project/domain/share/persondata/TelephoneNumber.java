package switchtwentytwenty.project.domain.share.persondata;

import switchtwentytwenty.project.domain.share.dddtype.ValueObject;

import java.util.Objects;

public class TelephoneNumber implements ValueObject {

    //Constants
    private static final int TELEPHONE_NUMBER_LENGTH = 9;

    //Attributes
    private final String number;

    // Constructor methods

    /**
     * Sole Constructor
     *
     * @param phoneNumber - phoneNumber
     */
    public TelephoneNumber(String phoneNumber) {
        isValid(phoneNumber);
        this.number = phoneNumber;

    }

    //Getter and Setters

    // Business Methods

    /**
     * Method to verify if the phone number is valid, it must not have blank spaces and have a correct format
     * e.g 915533677
     *
     * @param phoneNumber phone number we want to verify if is valid
     */
    private void isValid(String phoneNumber) {
        if (phoneNumber == null) {
            throw new NullPointerException("Phone number can't be null");
        }
        if (phoneNumber.trim().length() == 0) {
            throw new IllegalArgumentException("Phone number can't have blank spaces");
        }
        if (phoneNumber.length() != TELEPHONE_NUMBER_LENGTH || !checkPhoneNumberFormat(phoneNumber)) {
            throw new IllegalArgumentException("Phone number is not in the correct format");
        }
    }

    /**
     * Method to check if the phone number has the correct format, for example the first number is a digit 9
     * when is followed is a 1,2,3,4 or 6, or first number is a digit 2 followed by any digit between 1 and 9
     *
     * @param phoneNumber Receive the phone number in String format
     * @return true if the number checks the correct format
     */
    private boolean checkPhoneNumberFormat(String phoneNumber) {
        return phoneNumber.matches("(9)?[1-3&6][0-9]{7}") || phoneNumber.matches("(2)?[1-9][0-9]{7}");
    }


    //Equals and HashCode

    /**
     * Override equals().
     *
     * @param o - object to be compared
     * @return true if there is a match
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelephoneNumber that = (TelephoneNumber) o;
        return Objects.equals(number, that.number);
    }

    /**
     * Override hasCode
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    /**
     * Override toString method.
     *
     * @return Corresponding string of telephone number.
     */
    @Override
    public String toString() {
        return this.number;
    }
}
