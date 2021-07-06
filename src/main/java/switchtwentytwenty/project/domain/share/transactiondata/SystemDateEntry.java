package switchtwentytwenty.project.domain.share.transactiondata;

import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.InternalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemDateEntry implements InternalDate {

    //Attributes

    private final Date date;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.REGISTRATION_DATE_FORMAT);


    //Constructor Methods

    /**
     * Constructor date
     *
     * @param date - date instance
     * @throws ParseException - wrong format
     */
    public SystemDateEntry(Date date) throws ParseException {
        if (date == null) {
            throw new NullPointerException("Date is null");
        }
        String dateString = this.dateFormat.format(date);
        this.date = this.dateFormat.parse(dateString);
    }

    /**
     * Override toString method()
     *
     * @return string
     */
    @Override
    public String toString() {
        return this.dateFormat.format(this.date);
    }

    //Getter and Setters

    //Business Methods

    //Equals and HashCode
}
