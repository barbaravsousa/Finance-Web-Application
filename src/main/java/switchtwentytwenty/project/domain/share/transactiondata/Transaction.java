package switchtwentytwenty.project.domain.share.transactiondata;

import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;

public interface Transaction extends ValueObject {

    /**
     * Verifies if the transaction was created between the given dates.
     * The limits are included
     *
     * @param startDate - initial date
     * @param endDate   - final date
     * @return true if the transaction is between dates
     */
    boolean isBetweenDates(TransactionDate startDate, TransactionDate endDate);

    /**
     * Verifies if it is an instance of Transfer.
     *
     * @return boolean
     */
    boolean isTransfer();

    /**
     * Verifies if it is an instance of Payment.
     *
     * @return boolean
     */
    boolean isPayment();

    /**
     * Verifies if the transaction is related to my account.
     *
     * @param accountID - account identifier
     * @return true if there is a match
     */
    boolean isFromAccount(AccountID accountID);

    /**
     * Returns the amount of the movement that corresponds to the given account identifier.
     *
     * @param accountID - account identifier
     * @return amount
     */
    MoneyValue getMyAmount(AccountID accountID);

    /**
     * Returns the movement type of the movement that corresponds to the given account identifier.
     *
     * @param accountID - account identifier
     * @return movement type
     */
    MovementType getMyMovementType(AccountID accountID);

    /**
     * Returns the transaction date.
     *
     * @return transaction date
     */
    TransactionDate getDate();

    /**
     * Returns the category of the transaction.
     *
     * @return transaction's category
     */
    CategoryID getCategoryID();

    /**
     * Returns transaction's description.
     *
     * @return transactions's description
     */
    TransactionDescription getDescription();

    /**
     * Returns the transaction balance to this date.
     *
     * @return balance to this date
     */
    MoneyValue getBalanceToThisDate();

    /**
     * Get system entry date.
     *
     * @return system entry date
     */
    SystemDateEntry getSystemDateEntry();

    /**
     * Get account identifier.
     *
     * @return account identifier
     */
    AccountID getAccountID();

    /**
     * Get amount.
     *
     * @return transaction amount
     */
    MoneyValue getAmount();

    /**
     * Get the account identifier of the debit movement.
     *
     * @return account identifier
     */
    AccountID getOriginAccountID();

    /**
     * Get the account identifier of the credit movement.
     *
     * @return account identifier
     */
    AccountID getDestinationAccountID();
}
