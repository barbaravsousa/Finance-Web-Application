package switchtwentytwenty.project.domain.share.transactiondata;

import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.InternalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class TransactionDate implements InternalDate {

    //Attributes

    private final Date date;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.MOVEMENT_DATE_FORMAT);


    //Constructor Methods

    /**
     * Constructor string
     *
     * @param dateString - date in string
     * @throws ParseException - wrong format
     */
    public TransactionDate(String dateString) throws ParseException {
        dateFormat.setLenient(false); //"February 942, 1996" will NOT be treated as being equivalent to the 941st day after February 1, 1996
        this.date = this.dateFormat.parse(dateString);
    }

    /**
     * Constructor date
     *
     * @param date - date instance
     * @throws ParseException - wrong format
     */
    public TransactionDate(Date date) throws ParseException {
        if (date == null) {
            throw new NullPointerException("Date is null");
        }
        String dateString = this.dateFormat.format(date);
        this.date = this.dateFormat.parse(dateString);
    }


    //Business Methods

    /**
     * Override toString method()
     *
     * @return string
     */
    @Override
    public String toString() {
        return this.dateFormat.format(this.date);
    }

    /**
     * Compares two Dates for ordering.
     * The value 0 if the argument Date is equal to this Date;
     * a value less than 0 if this Date is before the Date argument;
     * and a value greater than 0 if this Date is after the Date argument.
     *
     * @param other TransactionDate instance
     * @return 0, 1 or -1
     */
    public int compare(TransactionDate other) {
        return this.date.compareTo(other.date);
    }

    /**
     * Verifies id given TransactionDate is between two other dates.
     *
     * @param startDate - initial date
     * @param endDate   - end date
     * @return true if this date is between the two given dates
     */
    public boolean isBetween(TransactionDate startDate, TransactionDate endDate) {
        return startDate.compare(this) * this.compare(endDate) >= 0; //inclusive given dates
    }


    //Equals and hashCode

    /**
     * Override equals method.
     *
     * @param o - object instance
     * @return true if there is a match
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDate date1 = (TransactionDate) o;
        return Objects.equals(date, date1.date) && Objects.equals(dateFormat, date1.dateFormat);
    }

    /**
     * Overrides hashCode method.
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(date, dateFormat);
    }
}
