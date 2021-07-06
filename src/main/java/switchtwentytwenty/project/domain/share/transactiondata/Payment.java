package switchtwentytwenty.project.domain.share.transactiondata;

import lombok.AccessLevel;
import lombok.Getter;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;

import java.text.ParseException;
import java.util.Objects;

public class Payment implements Transaction {

    //Attributes
    @Getter(AccessLevel.PROTECTED)
    private Movement debitMovement;
    @Getter(AccessLevel.PROTECTED)
    private BaseTransaction baseTransaction;


    //Constructor Method

    /**
     * Sole Constructor
     */
    private Payment() {
    }

    //Business Method

    /**
     * Verifies if this date is between two given dates.
     *
     * @param startDate - initial date
     * @param endDate   - end date
     * @return true if this date is between the given dates
     */
    public boolean isBetweenDates(TransactionDate startDate, TransactionDate endDate) {
        return this.baseTransaction.isBetweenDates(startDate, endDate);
    }

    //Getters and Setters

    /**
     * Returns the amount of the movement that corresponds to the given account identifier.
     *
     * @param accountID - account identifier
     * @return amount
     */
    @Override
    public MoneyValue getMyAmount(AccountID accountID) {
        if (this.debitMovement.isFromAccount(accountID)) {
            return this.debitMovement.getAmount();
        }
        throw new IllegalArgumentException("Movement not from this account");
    }

    /**
     * Returns the movement type of the movement that corresponds to the given account identifier.
     *
     * @param accountID - account identifier
     * @return movement type
     */
    @Override
    public MovementType getMyMovementType(AccountID accountID) {
        if (this.debitMovement.isFromAccount(accountID)) {
            return this.debitMovement.getType();
        }
        throw new IllegalArgumentException("Movement not from this account");
    }

    /**
     * Get category identifier.
     *
     * @return category identifier
     */
    @Override
    public CategoryID getCategoryID() {
        return this.baseTransaction.getCategoryID();
    }

    /**
     * Get date.
     *
     * @return transaction date
     */
    @Override
    public TransactionDate getDate() {
        return this.baseTransaction.getDate();
    }

    /**
     * Get system entry date.
     *
     * @return system entry date
     */
    @Override
    public SystemDateEntry getSystemDateEntry() {
        return this.baseTransaction.getSystemDateEntry();
    }


    /**
     * Get account identifier.
     *
     * @return account identifier
     */
    @Override
    public AccountID getAccountID() {
        return this.debitMovement.getAccountID();
    }

    /**
     * Get movement type.
     *
     * @return movement type
     */
    public MovementType getMovementType() {
        return this.debitMovement.getType();
    }

    /**
     * Get amount.
     *
     * @return transaction amount
     */
    @Override
    public MoneyValue getAmount() {
        return this.debitMovement.getAmount();
    }

    /**
     * Get origin account ID from transfer transaction
     *
     * @return Unsupported Operation
     */
    @Override
    public AccountID getOriginAccountID() {
        throw new UnsupportedOperationException();
    }

    /**
     * Get destination account ID from transfer transaction
     *
     * @return Unsupported Operation
     */
    @Override
    public AccountID getDestinationAccountID() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the transaction balance to this date.
     *
     * @return balance to this date
     */
    @Override
    public MoneyValue getBalanceToThisDate() {
        return this.baseTransaction.balance;
    }

    /**
     * Returns transaction's description.
     *
     * @return transactions's description
     */
    @Override
    public TransactionDescription getDescription() {
        return this.baseTransaction.description;
    }


    //Business methods

    /**
     * Verifies if the transaction is a transfer.
     *
     * @return boolean
     */
    public boolean isTransfer() {
        return false;
    }

    /**
     * Verifies if the transaction is a payment.
     *
     * @return boolean
     */
    public boolean isPayment() {
        return true;
    }

    /**
     * Verifies if transaction as any relation to the given account identifier.
     *
     * @param accountID - account identifier
     * @return boolean
     */
    public boolean isFromAccount(AccountID accountID) {
        return this.debitMovement.isFromAccount(accountID);
    }


    //Inner class

    public static class PaymentBuilder {

        //Attributes

        private final Payment payment;

        //Constructor methods

        /**
         * Sole Constructor
         */
        public PaymentBuilder() {
            payment = new Payment();
        }

        /**
         * Set base transaction
         *
         * @param categoryID  - category
         * @param description - description
         * @param date        - date
         * @param balance     - account balance
         * @return baseTransaction
         */
        public PaymentBuilder withBaseTransaction(CategoryID categoryID, TransactionDescription description, TransactionDate date, MoneyValue balance, SystemDateEntry systemDateEntry) throws ParseException {
            this.payment.baseTransaction = new BaseTransaction(categoryID, description, date, balance, systemDateEntry);
            return this;
        }

        /**
         * Set debit movement
         *
         * @param accountID - accountID
         * @param amount    - transaction amount
         * @return debit movement
         */
        public PaymentBuilder withDebitMovement(AccountID accountID, MoneyValue amount) throws InvalidMovementTypeException {
            MovementType type = new MovementType(Constants.DEBIT);
            this.payment.debitMovement = new Movement(accountID, type, amount);
            return this;
        }

        /**
         * Return a complete instance.
         *
         * @return Payment instance
         */
        public Payment build() {
            Objects.requireNonNull(this.payment.getBalanceToThisDate());
            Objects.requireNonNull(this.payment.getBaseTransaction());
            return payment;
        }
    }
}
