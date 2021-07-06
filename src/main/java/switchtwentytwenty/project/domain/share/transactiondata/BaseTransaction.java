package switchtwentytwenty.project.domain.share.transactiondata;

import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.CategoryID;

import java.text.ParseException;
import java.util.Date;

public class BaseTransaction {

    //Attributes

    protected CategoryID categoryID;
    protected TransactionDescription description;
    protected TransactionDate date;
    protected SystemDateEntry systemDateEntry;
    protected MoneyValue balance;

    //Constructor Method

    /**
     * Sole constructor.
     *
     * @param categoryID      - category identifier
     * @param description     - transaction description
     * @param date            - transaction date
     * @param balance         - balance after transaction
     * @param systemDateEntry - transaction system entry date
     * @throws ParseException - read
     */
    protected BaseTransaction(CategoryID categoryID, TransactionDescription description, TransactionDate date, MoneyValue balance, SystemDateEntry systemDateEntry) throws ParseException {
        this.categoryID = categoryID;
        this.balance = balance;
        this.date = date;
        if (systemDateEntry == null) {
            this.systemDateEntry = new SystemDateEntry(new Date(System.currentTimeMillis()));
        } else {
            this.systemDateEntry = systemDateEntry;
        }
        this.description = description;
    }

    //Business Method

    /**
     * Returns true if the transaction's date is between a given time frame.
     * includes the initial and final date
     *
     * @param startDate - initial date
     * @param endDate   - final date
     * @return true if the transaction's date is between a given time frame
     */
    public boolean isBetweenDates(TransactionDate startDate, TransactionDate endDate) {
        return this.date.isBetween(startDate, endDate);
    }


    //Getters and Setters

    /**
     * get balance
     *
     * @return balance
     */
    public MoneyValue getBalance() {
        return this.balance;
    }

    /**
     * get categoryID
     *
     * @return categpryID
     */
    public CategoryID getCategoryID() {
        return this.categoryID;
    }

    /**
     * get description
     *
     * @return description
     */
    public TransactionDescription getDescription() {
        return this.description;
    }

    /**
     * get date
     *
     * @return date
     */
    public TransactionDate getDate() {
        return this.date;
    }

    /**
     * get system date entry
     * @return system date entry
     */
    public SystemDateEntry getSystemDateEntry() {
        return this.systemDateEntry;
    }
}
