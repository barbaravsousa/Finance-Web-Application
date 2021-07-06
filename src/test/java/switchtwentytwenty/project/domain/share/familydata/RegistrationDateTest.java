package switchtwentytwenty.project.domain.share.familydata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import switchtwentytwenty.project.domain.share.id.FamilyID;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationDateTest {

    @Test
    @DisplayName("Two different registrationDate String")
    void twoDifferentRegistrationDateString() throws Exception {
        //arrange
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("08/01/1970");
        Date date2;
        RegistrationDate registrationDate = new RegistrationDate();
        //act
        date2 = registrationDate.getDate();
        //assert
        assertNotEquals(date1, date2);
    }

    @Test
    @DisplayName("The objects don't have the same value but the same string")
    void objectsWithDifferentValues() throws Exception {
        //arrange
        boolean result;
        String date1;
        String date2;
        RegistrationDate registrationDate1 = new RegistrationDate();
        Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2001-07-04 08:56:60.120");
        ReflectionTestUtils.setField(registrationDate1, "date", now);

        RegistrationDate registrationDate2 = new RegistrationDate();
        Date nowPlus = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2001-07-04 08:56:60.130");
        ReflectionTestUtils.setField(registrationDate2, "date", nowPlus);

        //act
        result = registrationDate1.equals(registrationDate2);
        date1 = registrationDate1.toString();
        date2 = registrationDate2.toString();
        //assert
        assertFalse(result);
        assertEquals(date1, date2);
    }

    @Test
    @DisplayName("Compare same object")
    void compareSameObject() {
        //arrange
        RegistrationDate registrationDate1 = new RegistrationDate();
        RegistrationDate registrationDate2;
        //act
        registrationDate2 = registrationDate1;
        //assert
        assertEquals(registrationDate1, registrationDate2);
    }

    @Test
    @DisplayName("Compare object with null")
    void compareObjectWithNull() {
        //arrange
        RegistrationDate registrationDate1 = new RegistrationDate();
        RegistrationDate registrationDate2;
        //act
        registrationDate2 = null;
        //assert
        assertNotEquals(registrationDate1, registrationDate2);
    }

    @Test
    @DisplayName("Compare object with object of different instance")
    void compareObjectWithDifferentInstance() {
        //arrange
        RegistrationDate registrationDate = new RegistrationDate();
        FamilyID familyID;
        //act
        UUID id = UUID.randomUUID();
        familyID = new FamilyID(id);
        //assert
        assertNotEquals(registrationDate, familyID);
    }

    @Test
    @DisplayName("Same hashCode")
    void sameHashCode() {
        //arrange
        int hashCode1;
        int hashCode2;
        RegistrationDate registrationDate1 = new RegistrationDate();
        RegistrationDate registrationDate2 = registrationDate1;
        //act
        hashCode1 = registrationDate1.hashCode();
        hashCode2 = registrationDate2.hashCode();
        //assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Different hashCode")
    void differentHashCode() {
        //arrange
        int hashCode1;
        int hashCode2;
        RegistrationDate registrationDate1 = new RegistrationDate();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.add(Calendar.DATE, 1); //one day added
        Date nowPlusOneDay = calendar.getTime();

        RegistrationDate registrationDate2 = new RegistrationDate();
        ReflectionTestUtils.setField(registrationDate2, "date", nowPlusOneDay);
        //act
        hashCode1 = registrationDate1.hashCode();
        hashCode2 = registrationDate2.hashCode();
        //assert
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Get registration date")
    void getRegistrationDate() {
        //arrange
        RegistrationDate registrationDate = new RegistrationDate();
        //act
        Date date = registrationDate.getDate();
        //assert
        assertNotNull(date);
    }

    @Test
    @DisplayName("Set registration date")
    void setDate() throws Exception {
        //arrange
        RegistrationDate registrationDate = new RegistrationDate();
        Date expected = new SimpleDateFormat("dd/MM/yyyy").parse("08/01/1970");
        //act
        registrationDate.setDate(new SimpleDateFormat("dd/MM/yyyy").parse("08/01/1970"));
        Date result = registrationDate.getDate();
        //assert

        assertEquals(result, expected);

    }
}
