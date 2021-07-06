package switchtwentytwenty.project.domain.share.persondata;

import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.InternalDate;
import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import switchtwentytwenty.project.exception.InvalidDateException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class BirthDate implements ValueObject, InternalDate {

    //Attributes
    private final Date date;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.MOVEMENT_DATE_FORMAT);

    //Constructor Methods

    /**
     * Sole constructor
     *
     * @param date
     */
    public BirthDate(String date) throws InvalidDateException {
        this.date = stringToDate(date);
        checkDate();
    }

    //Getter and Setters

    //Business Methods

    /**
     * Method that receives a date in String format and returns a Date.
     *
     * @param date of String type
     * @return the date in Date type
     */
    public static Date stringToDate(String date) throws InvalidDateException{
        DateFormat format = new SimpleDateFormat(Constants.BIRTH_DATE_FORMAT);
        format.setLenient(false); //é uma flag para que a data esteja sempre entre os limites corretos
        try {
            return format.parse(date);
        } catch (ParseException ignored) {
            throw new InvalidDateException("Invalid date");
        }
    }

    /**
     * Method that checks if given date is in the past.
     */
    public void checkDate() throws InvalidDateException {
        Date todayDate = new Date();
        if (!todayDate.after(date)) {
            throw new InvalidDateException("Date is from the future!");
        }
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
        BirthDate birthDate1 = (BirthDate) o;
        return Objects.equals(date, birthDate1.date);
    }

    /**
     * Override hasCode.
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    /**
     * Override toString method.
     *
     * @return Corresponding string of birth date.
     */
    @Override
    public String toString() {
        return this.dateFormat.format(this.date);
    }
}
