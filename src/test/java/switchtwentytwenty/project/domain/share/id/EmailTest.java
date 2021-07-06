package switchtwentytwenty.project.domain.share.id;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.exception.InvalidEmailException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    @DisplayName("Create email successfully")
    void createValidEmail() throws Exception {
        Email email = new Email("newEmail@gmail.com");
        assertNotNull(email);
    }

    @Test
    @DisplayName("Failure create email: null")
    void failureCreateEmail_Null() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    @DisplayName("Failure create email: empty")
    void failureCreateEmail_Empty() {
        assertThrows(InvalidEmailException.class, () -> new Email(""));
    }

    @Test
    @DisplayName("Failure create email: blank")
    void failureCreateEmail_Blank() {
        assertThrows(InvalidEmailException.class, () -> new Email("  "));
    }

    @Test
    @DisplayName("Failure create email: without at")
    void failureCreateEmail_WithoutAT() {
        assertThrows(InvalidEmailException.class, () -> new Email("newEmailgmail.com"));
    }

    @Test
    @DisplayName("Failure create email: without domain")
    void failureCreateEmail_WithoutDomain() {
        assertThrows(InvalidEmailException.class, () -> new Email("newEmail@.com"));
    }

    @Test
    @DisplayName("Failure create email: without final field")
    void failureCreateEmail_WithoutFinalField() {
        assertThrows(InvalidEmailException.class, () -> new Email("newEmail@gmail"));
    }

    @Test
    @DisplayName("Failure create email: without personalized field")
    void failureCreateEmail_WithoutPersonalizedField() {
        assertThrows(InvalidEmailException.class, () -> new Email("@gmail.com"));
    }

    @Test
    @DisplayName("Failure create email: with two AT")
    void failureCreateEmail_WithTwoAT() {
        assertThrows(InvalidEmailException.class, () -> new Email("ne@Test@gmail.com"));
    }

    @Test
    @DisplayName("Failure create email: with left and right spaces")
    void failureCreateEmail_WithLeftAndRightSpaces() {
        assertThrows(InvalidEmailException.class, () -> new Email("   newTest@gmail.com   "));
    }

    @Test
    @DisplayName("Failure create email: with invalid characters")
    void failureCreateEmail_WithInvalidCharacters() {
        assertThrows(InvalidEmailException.class, () -> new Email("new&Test@gmail.com"));
    }

    @Test
    @DisplayName("Same values as")
    void sameValueAs() throws InvalidEmailException {
        Email email = new Email("newEmail@gmail.com");
        Email otherEmail = new Email("newEmail@gmail.com");
        boolean result = email.equals(otherEmail);
        assertTrue(result);
    }

    @Test
    @DisplayName("Not same values as")
    void notSameValueAs() throws InvalidEmailException {
        Email email = new Email("newEmail@gmail.com");
        Email otherEmail = new Email("newemail@gmail.com");
        boolean result = email.equals(otherEmail);
        assertFalse(result);
    }

    @Test
    @DisplayName("Compare with null")
    void compareWithNull() throws InvalidEmailException {
        //arrange
        boolean result;
        Email email = new Email("margaret_howard@hotmail.com");
        Email otherEmail = null;
        //act
        result = email.equals(otherEmail);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Compare with different object")
    void compareWithDifferentObject() throws InvalidEmailException {
        //arrange
        boolean result;
        Email email = new Email("margaret_howard@hotmail.com");
        BigDecimal bigDecimal = new BigDecimal("100");
        //act
        result = email.equals(bigDecimal);
        //assert
        assertFalse(result);
    }
}
