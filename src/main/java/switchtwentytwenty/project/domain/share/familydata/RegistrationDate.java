package switchtwentytwenty.project.domain.share.familydata;

import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.InternalDate;
import switchtwentytwenty.project.domain.share.dddtype.ValueObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class RegistrationDate implements ValueObject, InternalDate {

    //Attributes
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.REGISTRATION_DATE_FORMAT);

    private Date date;


    //Constructor Methods

    /**
     * Sole Constructor
     */
    public RegistrationDate() {
        this.date = new Date(System.currentTimeMillis());
    }


    //Getter and Setters

    /**
     * Return a copy of the date attribute.
     *
     * @return date
     */
    public Date getDate() {
        return new Date(this.date.getTime());
    }

    //Business Methods

    //Equals toString and HashCode

    /**
     * Method to set the date
     *
     * @param dateToInput
     */
    public void setDate(Date dateToInput) {
        this.date = new Date(dateToInput.getTime());
    }


    /**
     * Override equals method
     *
     * @param other - other value object
     * @return true if there is a match
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        RegistrationDate that = (RegistrationDate) other;
        return Objects.equals(date, that.date);
    }

    /**
     * Override hashCode method
     *
     * @return true if the same hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    /**
     * Override toString method
     *
     * @return String from the value
     */
    @Override
    public String toString() {
        return this.dateFormat.format(this.date);
    }
}
