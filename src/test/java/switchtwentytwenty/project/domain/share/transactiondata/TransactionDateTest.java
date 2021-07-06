package switchtwentytwenty.project.domain.share.transactiondata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionDateTest {

    @Test
    @DisplayName("Valid format")
    void validFormat() throws ParseException {
        //arrange
        String dateString = "1988-08-01";
        String result;
        TransactionDate date;
        //act
        date = new TransactionDate(dateString);
        result = date.toString();
        //assert
        assertEquals(dateString, result);
    }

    @Test
    @DisplayName("Wrong format - word")
    void wrongFormatWord() {
        //arrange
        String dateString = "Alan";
        //act and assert
        assertThrows(ParseException.class, () -> {
            new TransactionDate(dateString);
        });
    }

    @Test
    @DisplayName("Wrong format - without year")
    void wrongFormatWithoutYear() {
        //arrange
        String dateString = "-01-01 23:59:59";
        //act and assert
        assertThrows(ParseException.class, () -> {
            new TransactionDate(dateString);
        });
    }

    @Test
    @DisplayName("Wrong format - with day of week")
    void wrongFormatWithDayOfWeek() {
        //arrange
        String dateString = "Wed 1990-01-30";
        //act and assert
        assertThrows(ParseException.class, () -> {
            new TransactionDate(dateString);
        });
    }

    @Test
    @DisplayName("Wrong format - invalid day")
    void wrongFormatInvalidDay() {
        //arrange
        String dateString = "1988-02-31";
        //act and assert
        assertThrows(ParseException.class, () -> {
            new TransactionDate(dateString);
        });
    }

    @Test
    @DisplayName("Wrong format - invalid month")
    void wrongFormatInvalidMonth() {
        //arrange
        String dateString = "1988-22-01";
        //act and assert
        assertThrows(ParseException.class, () -> {
            new TransactionDate(dateString);
        });
    }

    @Test
    @DisplayName("Wrong format - null")
    void wrongFormatWithoutNull() {
        //arrange
        String dateString = null;
        //act and assert
        assertThrows(NullPointerException.class, () -> {
            new TransactionDate(dateString);
        });
    }

    @Test
    @DisplayName("Wrong format - empty")
    void wrongFormatWithoutEmpty() {
        //arrange
        String dateString = "                    ";
        //act and assert
        assertThrows(ParseException.class, () -> {
            new TransactionDate(dateString);
        });
    }

    @Test
    @DisplayName("Give a wrong format date object to the constructor")
    void randomDateObjectWithStrangeFormatToConstructor() throws ParseException {
        //arrange
        int result;
        int expected = 0;

        String dateString = "10-02-1988 20:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        Date wrongFormatDate = dateFormat.parse(dateString);
        TransactionDate movementDate = new TransactionDate(wrongFormatDate);

        String dateStringToCompare = "1988-02-10 09:00:00";
        SimpleDateFormat dateFormatToCompare = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormatToCompare.setLenient(false);
        Date wrongFormatDateToCompare = dateFormatToCompare.parse(dateStringToCompare);
        TransactionDate dateToCompare = new TransactionDate(wrongFormatDateToCompare);

        //act
        result = movementDate.compare(dateToCompare);
        //assert
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Constructor argument is null")
    void constructorDateIsNull() {
        //arrange
        Date date = null;
        //act and assert
        assertThrows(NullPointerException.class, () -> {
            new TransactionDate(date);
        });
    }

    @Test
    @DisplayName("Same object")
    void sameObject() throws ParseException {
        //arrange
        boolean result;
        String dateString = "1988-08-01";
        TransactionDate date = new TransactionDate(dateString);
        TransactionDate sameDate = date;
        //act
        result = date.equals(sameDate);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("null object")
    void nullObject() throws ParseException {
        //arrange
        boolean result;
        String dateString = "1988-08-01";
        TransactionDate date = new TransactionDate(dateString);
        TransactionDate nullDate = null;
        //act
        result = date.equals(nullDate);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Identical object")
    void identicalObject() throws ParseException {
        //arrange
        boolean result;
        String dateString = "1988-08-01";
        TransactionDate date = new TransactionDate(dateString);
        TransactionDate sameDate = new TransactionDate(dateString);
        //act
        result = date.equals(sameDate);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Different object")
    void differentObject() throws ParseException {
        //arrange
        boolean result;
        String dateString = "2020-10-23";
        String otherDateString = "1988-08-01";
        TransactionDate date = new TransactionDate(dateString);
        TransactionDate differentDate = new TransactionDate(otherDateString);
        //act
        result = date.equals(differentDate);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Different class")
    void differentClass() throws ParseException {
        //arrange
        boolean result;
        String dateString = "1988-08-01";
        TransactionDate date = new TransactionDate(dateString);
        BigDecimal bigDecimal = BigDecimal.ONE;
        //act
        result = date.equals(bigDecimal);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Different hash")
    void differentHash() throws ParseException {
        //arrange
        int hash1;
        int hash2;
        String dateString = "1988-08-01";
        String otherDateString = "1990-08-02";
        TransactionDate date = new TransactionDate(dateString);
        TransactionDate otherDate = new TransactionDate(otherDateString);
        //act
        hash1 = date.hashCode();
        hash2 = otherDate.hashCode();
        //assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Different hash")
    void sameHash() throws ParseException {
        //arrange
        int hash1;
        int hash2;
        String dateString = "1988-08-01";
        TransactionDate date = new TransactionDate(dateString);
        TransactionDate otherDate = new TransactionDate(dateString);
        //act
        hash1 = date.hashCode();
        hash2 = otherDate.hashCode();
        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Between the dates")
    void betweenDates() throws ParseException {
        //arrange
        boolean result;
        String dateString = "1988-08-01";
        TransactionDate date = new TransactionDate(dateString);
        String startDateString = "1980-01-01";
        TransactionDate startDate = new TransactionDate(startDateString);
        String endDateString = "1990-02-1";
        TransactionDate endDate = new TransactionDate(endDateString);
        //act
        result = date.isBetween(startDate, endDate);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Not between the dates")
    void notBetweenDates() throws ParseException {
        //arrange
        boolean result;
        String dateString = "2020-08-01";
        TransactionDate date = new TransactionDate(dateString);
        String startDateString = "1980-01-01";
        TransactionDate startDate = new TransactionDate(startDateString);
        String endDateString = "1990-02-1";
        TransactionDate endDate = new TransactionDate(endDateString);
        //act
        result = date.isBetween(startDate, endDate);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Between the dates - end date coincide")
    void notBetweenDatesEndDateCoincide() throws ParseException {
        //arrange
        boolean result;
        String dateString = "2020-08-01";
        TransactionDate date = new TransactionDate(dateString);
        String startDateString = "1980-01-01";
        TransactionDate startDate = new TransactionDate(startDateString);
        String endDateString = "2020-08-01";
        TransactionDate endDate = new TransactionDate(endDateString);
        //act
        result = date.isBetween(startDate, endDate);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Between the dates - start date coincide")
    void notBetweenDatesStartDateCoincide() throws ParseException {
        //arrange
        boolean result;
        String dateString = "1980-01-01";
        TransactionDate date = new TransactionDate(dateString);
        String startDateString = "1980-01-01";
        TransactionDate startDate = new TransactionDate(startDateString);
        String endDateString = "2020-08-01";
        TransactionDate endDate = new TransactionDate(endDateString);
        //act
        result = date.isBetween(startDate, endDate);
        //assert
        assertTrue(result);
    }
}
