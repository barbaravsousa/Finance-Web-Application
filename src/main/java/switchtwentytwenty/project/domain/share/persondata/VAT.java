package switchtwentytwenty.project.domain.share.persondata;

import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import switchtwentytwenty.project.exception.InvalidVATException;

import java.util.Objects;


public class VAT implements ValueObject {

    //Attributes
    private final String number;

    //Constructor Methods

    /**
     * Sole constructor.
     *
     * @param number - person's value added tax identification number
     */
    public VAT(String number) throws InvalidVATException {
        isValidVAT(number);
        this.number = number;
    }

    //Getter and Setters

    //Business Methods

    /**
     * Method that checks if the vat number follows its allowed format and not null or empty.
     *
     * @param vat - Receive a vat number in String format
     */
    private void isValidVAT(String vat) throws InvalidVATException {
        if (vat == null) {
            throw new InvalidVATException("Vat can't be null");
        }
        if (vat.trim().length() == 0) {
            throw new InvalidVATException("Vat can not have blank spaces");
        }
        if (!checkFormat(vat)) {
            throw new InvalidVATException("Vat is not in correct format");
        }
    }

    /**
     * Method that checks if the vat number is valid according portugal government rules.
     * According to rules a vat number should be 9 digits, beginning with 1,2,3,5,6,8 or 9
     * Last digit is a check digit.
     * <p>
     * Method adapted from https://www.portugal-a-programar.pt/forums/topic/75515-regra-de-valida%C3%A7%C3%A3o-nif/
     *
     * @param vat Receive a vat number in String format.
     * @return True if vat is in valid format.
     */
    private boolean checkFormat(String vat) {
        if (!vat.matches("([1235689])[0-9]{8}")) {
            return false;
        }
        int checkSum = 0;
        final int maxDigits = 9;
        for (int index = 0; index < maxDigits - 1; index++) {
            checkSum += (vat.charAt(index)) * (maxDigits - index);
        }
        final int validationValue = 11;
        int checkDigit = validationValue - (checkSum % validationValue);
        final int checkDigitMaxValue = 10;
        if (checkDigit >= checkDigitMaxValue) checkDigit = 0;
        return checkDigit == vat.charAt(maxDigits - 1) - '0';
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
        VAT vat = (VAT) o;
        return Objects.equals(number, vat.number);
    }

    /**
     * Override hashCode().
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    /**
     * Override toString.
     *
     * @return Corresponding string of vat number.
     */
    @Override
    public String toString() {
        return number;
    }
}
