package switchtwentytwenty.project.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

class UtilTest {

    @Test
    public void stringToDate() {
        String date = "1997-01-10";

        Date dateDt = Util.stringToDate(date,Constants.MOVEMENT_DATE_FORMAT);
        Date expected = new GregorianCalendar(1997, Calendar.JANUARY, 10).getTime();
        assertEquals(expected, dateDt);
    }

    @Test
    public void stringToDateInvalid(){
        //arrange
        String date = "9a/7/7777";
        //act
        Date result = Util.stringToDate(date, Constants.MOVEMENT_DATE_FORMAT);
        //assert
        assertNull(result);
    }


    @Test
    @DisplayName("Load configuration file with wrong url - Unsuccess case")
    void loadConfigurationFileWithWrongURL() {

        //arrange
        String url = "sdafhbfid";
        //act and assert
        assertThrows(IOException.class ,() -> {
            Util.loadConfigurationFile(url);
        });
    }
}
