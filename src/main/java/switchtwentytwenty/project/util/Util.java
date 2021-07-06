package switchtwentytwenty.project.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Util {

    private Util() {
    }
    //Business Methods

    /**
     * Method that capitalizes the first letter of each word in a String, deletes space characters at the beginning and at the end of a String and
     * deletes multiple adjacent space characters
     *
     * @param designation a string containing one or more words
     * @return returns a String with the first letter capitalized in each word and with the correct space character arrangement
     */
    public static String capitalizeFirstLetters(String designation) {
        designation = designation.trim().toLowerCase();   //deletes space characters at the end and at the beginning of a Sting
        while (designation.contains("  ")) { // deletes multiple space characters
            designation = designation.replace("  ", " ");
        }
        String[] split = designation.split(" ");
        for (int i = 0; i < split.length; i++) {
            split[i] = Character.toUpperCase(split[i].charAt(0)) + split[i].substring(1);
        }
        designation = String.join(" ", split);
        return designation;
    }

    /**
     * Initialise relations List.
     *
     * @param url - directory of config file
     * @return Properties instance
     */
    public static Properties loadConfigurationFile(String url) throws IOException {
        InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(url);
        if (inputStream == null) {
            throw new IOException("File not found");
        }
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

    /**
     * Reads system configuration file and return default currency.
     *
     * @return default system's currency
     */
    public static String[] getSystemDefaultCurrency()  {
        String[] localeCouple;
        Properties properties = new Properties();
        InputStream inputStream = Util.class.getClassLoader().getResourceAsStream("./Currency.properties");
        try {
            properties.load(inputStream);
        } catch (IOException exception) {
        }
        String locale = properties.getProperty("Portugal");
        localeCouple = locale.split("&");
        return localeCouple;

    }

    /**
     * Check if an ID instance is null
     *
     * @param list - List of elements
     * @param <T>  - instance type
     */
    public static <T> void checkIfNull(List<T> list) {
        for (T element : list) {
            if (element == null) {
                throw new IllegalArgumentException("Of of the elements in the list is null");
            }
        }
    }


    /**
     * Method that receives a date in String format and returns a Date.
     *
     * @param date of String type
     * @return the date in Date type
     */
    public static Date stringToDate(String date, String dateFormat) {
        DateFormat format = new SimpleDateFormat(dateFormat);
        Date dateDt;
        try {
            dateDt = format.parse(date);
        } catch (ParseException ignored) {
            dateDt = null;
        }
        return dateDt;
    }


}
