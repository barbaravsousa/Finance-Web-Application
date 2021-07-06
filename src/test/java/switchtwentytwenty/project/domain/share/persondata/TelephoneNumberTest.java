package switchtwentytwenty.project.domain.share.persondata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


class TelephoneNumberTest {

    @Test
    @DisplayName("Create a Phone Number successfully (mobile)")
    public void CreatingValidTelePhoneNumber() {
        TelephoneNumber validPhoneNumber = new TelephoneNumber("917755744");
        assertNotNull(validPhoneNumber);
    }

    @Test
    @DisplayName("Create a Phone Number successfully (fixed)")
    public void CreatingValidPhoneNumber() {
        TelephoneNumber validPhoneNumber = new TelephoneNumber("227755744");
        assertNotNull(validPhoneNumber);
    }

    @Test
    @DisplayName("Create a null phone number")
    public void CreatingPhoneNumberWithNull() {

        Exception exception = assertThrows(NullPointerException.class, () -> new TelephoneNumber(null));

        String exceptionMessage = exception.getMessage();
        String expectedMessage = "Phone number can't be null";
        assertEquals(exceptionMessage, expectedMessage);
    }

    @Test
    @DisplayName("Create a Phone Number with invalid number of digits")
    public void CreatingPhoneNumberWithInvalidNumberOfDigits() {
        Exception exception =
                assertThrows(IllegalArgumentException.class, () -> new TelephoneNumber("1120717"));

        String exceptionMessage = exception.getMessage();
        String expectedMessage = "Phone number is not in the correct format";
        assertEquals(exceptionMessage, expectedMessage);

    }

    @Test
    @DisplayName("Create a Phone Number with invalid characters")
    public void CreatingPhoneNumberWithInvalidCharacters() {
        Exception exception =
                assertThrows(IllegalArgumentException.class, () -> new TelephoneNumber("1120a71754"));

        String exceptionMessage = exception.getMessage();
        String expectedMessage = "Phone number is not in the correct format";
        assertEquals(exceptionMessage, expectedMessage);

    }


    @Test
    @DisplayName("Create a Phone Number with invalid characters")
    public void CreatingPhoneNumberWithInvalidFormat() {
        Exception exception =
                assertThrows(IllegalArgumentException.class, () -> new TelephoneNumber("312255677"));

        String exceptionMessage = exception.getMessage();
        String expectedMessage = "Phone number is not in the correct format";
        assertEquals(exceptionMessage, expectedMessage);

    }

    @Test
    @DisplayName("Create a Phone Number with invalid characters")
    public void CreatingPhoneNumberWithBlankSpaces() {
        Exception exception =
                assertThrows(IllegalArgumentException.class, () -> new TelephoneNumber("   "));

        String exceptionMessage = exception.getMessage();
        String expectedMessage = "Phone number can't have blank spaces";
        assertEquals(exceptionMessage, expectedMessage);

    }

    @Test
    @DisplayName("Create a Phone Number with invalid characters")
    public void objectWithSameValues() {
        //arrange
        boolean result;
        TelephoneNumber number1 = new TelephoneNumber("226189574");
        TelephoneNumber number2 = new TelephoneNumber("226189574");
        //act
        result = number1.equals(number2);
        //assert
        assertTrue(result);

    }

    @Test
    @DisplayName("Create a Phone Number with invalid characters")
    public void objectWithDifferentValues() {
        //arrange
        boolean result;
        TelephoneNumber number1 = new TelephoneNumber("226189574");
        TelephoneNumber number2 = new TelephoneNumber("226184774");
        //act
        result = number1.equals(number2);
        //assert
        assertFalse(result);

    }

    @Test
    @DisplayName("Same Object")
    void sameObject() {
        //arrange
        TelephoneNumber number = new TelephoneNumber("226189574");
        //act
        TelephoneNumber otherNumber = number;
        //assert
        assertEquals(number, otherNumber);
    }

    @Test
    @DisplayName("Identical Object")
    void identicalObject() {
        //arrange
        TelephoneNumber number1 = new TelephoneNumber("226189574");
        //act
        TelephoneNumber number2 = new TelephoneNumber("226189574");
        //assert
        assertEquals(number1, number2);
    }

    @Test
    @DisplayName("Compare with null Object")
    void compareWithNullObject() {
        //arrange
        TelephoneNumber number = new TelephoneNumber("226189574");
        //act
        TelephoneNumber invalidNumber = null;
        //assert
        assertNotEquals(number, invalidNumber);
    }

    @Test
    @DisplayName("Different Classes")
    void differentClasses() {
        //arrange
        TelephoneNumber number = new TelephoneNumber("226189574");
        //act
        BigDecimal bigDecimal = new BigDecimal("100");
        //assert
        assertNotEquals(number, bigDecimal);
    }

    @Test
    @DisplayName("Same Hash")
    void sameHash() {
        //arrange
        TelephoneNumber number = new TelephoneNumber("226189574");
        //act
        TelephoneNumber otherNumber = new TelephoneNumber("226189574");
        //assert
        assertEquals(number.hashCode(), otherNumber.hashCode());
    }

    @Test
    @DisplayName("Not same Hash")
    void notSameHash() {
        //arrange
        TelephoneNumber number = new TelephoneNumber("226189574");
        //act
        TelephoneNumber otherNumber = new TelephoneNumber("212177579");
        //assert
        assertNotEquals(number.hashCode(), otherNumber.hashCode());
    }

}