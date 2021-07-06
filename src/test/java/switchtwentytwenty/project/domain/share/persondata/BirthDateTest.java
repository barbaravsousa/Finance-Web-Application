package switchtwentytwenty.project.domain.share.persondata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.exception.InvalidDateException;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class BirthDateTest {

    @Test
    @DisplayName("Same values as")
    void sameValueAs() throws Exception {
        BirthDate date = new BirthDate("2021-01-03");
        BirthDate date1 = new BirthDate("2021-01-03");
        boolean result = date.equals(date1);
        assertTrue(result);
    }

    @Test
    @DisplayName("Not same values as")
    void notSameValueAs() throws Exception {
        BirthDate date = new BirthDate("2021-01-03");
        BirthDate date1 = new BirthDate("2021-01-02");
        boolean result = date.equals(date1);
        assertFalse(result);
    }

    @Test
    @DisplayName("Creating valid birth date")
    void creatingValidBirthDate() throws Exception {
        BirthDate date = new BirthDate("1997-01-31");
        assertNotNull(date);
    }

    @Test
    @DisplayName("Failure creating birth date: null")
    void failureBirthDate_Null() {
        assertThrows(NullPointerException.class, () -> new BirthDate(null));
    }

    @Test
    @DisplayName("Failure creating birth date: empty")
    void failureBirthDate_Empty() {
        assertThrows(InvalidDateException.class, () -> new BirthDate(""));
    }

    @Test
    @DisplayName("Failure creating birth date: blank")
    void failureBirthDate_blank() {
        assertThrows(InvalidDateException.class, () -> new BirthDate("   "));
    }

    @Test
    @DisplayName("Failure creating birth date: invalid date format")
    void failureBirthDate_failure() {
        assertThrows(InvalidDateException.class, () -> new BirthDate("a/aa/aaaa"));
    }

    @Test
    @DisplayName("Failure creating birth date: invalid date format")
    void failureBirthDate_failure1() {
        assertThrows(InvalidDateException.class, () -> new BirthDate("a/aa/1997"));
    }

    @Test
    @DisplayName("Failure creating birth date: invalid date format")
    void failureBirthDate_failure2() {
        assertThrows(InvalidDateException.class, () -> new BirthDate("32/01/1997"));
    }

    @Test
    @DisplayName("Failure creating birth date: invalid date format")
    void failureBirthDate_failure3() {
        assertThrows(InvalidDateException.class, () -> new BirthDate("20/13/1997"));
    }

    @Test
    @DisplayName("Failure creating birth date: future date")
    void failureBirthDate_failure4() {
        assertThrows(InvalidDateException.class, () -> new BirthDate("20/01/2126"));
    }

    @Test
    void stringToDate() throws Exception {
        String date = "1997-01-10";
        Date dateDt = BirthDate.stringToDate(date);
        Date expected = new GregorianCalendar(1997, Calendar.JANUARY, 10).getTime();
        Assertions.assertEquals(expected, dateDt);
    }

    @Test
    @DisplayName("Same object")
    void sameObject() throws Exception {
        //arrange
        BirthDate birthDate = new BirthDate("1997-03-20");
        //act
        BirthDate sameBirthDate = birthDate;
        //assert
        assertEquals(birthDate, sameBirthDate);
    }

    @Test
    @DisplayName("Compare null object")
    void compareNullObject() throws Exception {
        //arrange
        BirthDate birthDate = new BirthDate("1997-03-20");
        //act
        BirthDate sameBirthDate = null;
        //assert
        assertNotEquals(birthDate, sameBirthDate);
    }

    @Test
    @DisplayName("Identical object")
    void identicalObject() throws Exception {
        //arrange
        BirthDate birthDate = new BirthDate("1997-03-20");
        //act
        BirthDate identicalBirthDate = new BirthDate("1997-03-20");
        //assert
        assertEquals(birthDate, identicalBirthDate);
    }

    @Test
    @DisplayName("Different objects")
    void differentObjects() throws Exception {
        //arrange
        BirthDate birthDate = new BirthDate("1997-03-20");
        //act
        BigDecimal bigDecimal = new BigDecimal("100");
        //assert
        assertNotEquals(birthDate, bigDecimal);
    }

    @Test
    @DisplayName("Same hash")
    void sameHash() throws Exception {
        //arrange
        BirthDate birthDate = new BirthDate("1997-03-20");
        //act
        BirthDate sameBirthDate = birthDate;
        //assert
        assertEquals(birthDate.hashCode(), sameBirthDate.hashCode());
    }

    @Test
    @DisplayName("Same hash")
    void notSameHash() throws Exception {
        //arrange
        BirthDate birthDate = new BirthDate("1997-03-20");
        BirthDate otherBirthDate = new BirthDate("1998-11-10");
        //act
        //assert
        assertNotEquals(birthDate.hashCode(), otherBirthDate.hashCode());
    }

}