package switchtwentytwenty.project.domain.share.persondata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.exception.InvalidVATException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class VATTest {

    @Test
    @DisplayName("Same values as")
    void sameValueAs() throws InvalidVATException {
        VAT vat = new VAT("114381844");
        VAT vat1 = new VAT("114381844");
        boolean result = vat.equals(vat1);
        assertTrue(result);
    }

    @Test
    @DisplayName("Not same values as")
    void notSameValueAs() throws InvalidVATException {
        VAT vat = new VAT("109904729");
        VAT vat1 = new VAT("114381844");
        boolean result = vat.equals(vat1);
        assertFalse(result);
    }

    @Test
    @DisplayName("Create VAT successfully")
    void createValidVAT() throws InvalidVATException {
        VAT vat = new VAT("109904729");
        assertNotNull(vat);
    }

    @Test
    @DisplayName("Valid last number")
    void validLastNumber() throws InvalidVATException {
        VAT vat = new VAT("286216876");
        assertNotNull(vat);
    }

    @Test
    @DisplayName("Failure creating VAT: null")
    void failureVAT_Null() {
        assertThrows(InvalidVATException.class, () -> new VAT(null));
    }

    @Test
    @DisplayName("Failure creating VAT: empty")
    void failureVAT_Empty() {
        assertThrows(InvalidVATException.class, () -> new VAT(""));
    }

    @Test
    @DisplayName("Failure creating VAT: blank")
    void failureVAT_Blank() {
        assertThrows(InvalidVATException.class, () -> new VAT("  "));
    }

    @Test
    @DisplayName("Failure creating VAT: less then 9 digits")
    void failureVAT_Invalid() {
        assertThrows(InvalidVATException.class, () -> new VAT("10990472"));
    }

    @Test
    @DisplayName("Failure creating VAT: more then 9 digits")
    void failureVAT_Invalid1() {
        assertThrows(InvalidVATException.class, () -> new VAT("1099047290"));
    }

    @Test
    @DisplayName("Failure creating VAT: 0 as first digit")
    void failureVAT_Invalid2() {
        assertThrows(InvalidVATException.class, () -> new VAT("010990472"));
    }

    @Test
    @DisplayName("Failure creating VAT: 4 as first digit")
    void failureVAT_Invalid3() {
        assertThrows(InvalidVATException.class, () -> new VAT("40990472"));
    }

    @Test
    @DisplayName("Failure creating VAT: 7 as first digit")
    void failureVAT_Invalid4() {
        assertThrows(InvalidVATException.class, () -> new VAT("70990472"));
    }

    @Test
    @DisplayName("Failure creating VAT: with letters")
    void failureVAT_Invalid5() {
        assertThrows(InvalidVATException.class, () -> new VAT("109hju972"));
    }

    @Test
    @DisplayName("Invalid last number")
    void invalidLastNumber() {
        assertThrows(InvalidVATException.class, () -> new VAT("286216870"));
    }

    @Test
    @DisplayName("Equals: Same Instance")
    void testEquals_SameInstance() throws InvalidVATException {
        //arrange
        VAT vat = new VAT("284620459");
        VAT otherVat = vat;
        //assert
        assertEquals(vat, otherVat);
    }

    @Test
    @DisplayName("Equals: Different Classes")
    void testEquals_DifferentClasses() throws InvalidVATException {
        //arrange
        VAT vat = new VAT("284620459");
        BigDecimal bigDecimal = new BigDecimal("100");
        //assert
        assertNotEquals(vat, bigDecimal);
    }

    @Test
    @DisplayName("Hash Code: true")
    void testHashCode_True() throws InvalidVATException {
        //arrange
        VAT vat = new VAT("284620459");
        //assert
        assertEquals(vat.hashCode(), vat.hashCode());
    }

    @Test
    @DisplayName("Hash Code: false")
    void testHashCode_False() throws InvalidVATException {
        //arrange
        VAT vat = new VAT("284620459");
        VAT otherVat = new VAT("278511864");
        //assert
        assertNotEquals(otherVat.hashCode(), vat.hashCode());
    }
}




