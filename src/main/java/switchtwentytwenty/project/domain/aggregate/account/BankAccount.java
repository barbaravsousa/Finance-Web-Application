package switchtwentytwenty.project.domain.aggregate.account;

import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.transactiondata.MovementType;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class BankAccount implements Account {

    //Attributes

    private final RootAccount account;
    private final MoneyValue balance;

    //Constructor Methods

    /**
     * Sole constructor
     *
     * @param accountID          - id number
     * @param accountDesignation - account designation
     */
    public BankAccount(AccountID accountID, Designation accountDesignation) {
        this.account = new RootAccount(accountID, accountDesignation);
        this.balance = new MoneyValue(new BigDecimal(0));
    }


    //Business Methods

    /**
     * Operation not supported
     *
     * @param amount - amount to withdraw
     * @return UnsupportedOperationException
     */
    @Override
    public MovementType withdraw(MoneyValue amount) {
        throw new UnsupportedOperationException(Constants.UNSUPPORTED_OPERATION);
    }

    /**
     * Operation not supported
     *
     * @param amount - amount to deposit
     * @return UnsupportedOperationException
     */
    @Override
    public MovementType deposit(MoneyValue amount) {
        throw new UnsupportedOperationException(Constants.UNSUPPORTED_OPERATION);
    }

    /**
     * Allows to check if given designation is equals to account's designation.
     *
     * @param designation Receives the designation to check.
     * @return True if is same designation.
     */
    @Override
    public boolean isSameDesignation(Designation designation) {
        return this.account.isSameDesignation(designation);
    }

    /**
     * Returns true if it is a cash account.
     *
     * @return true if it is a cash account
     */
    @Override
    public boolean isCashAccount() {
        return false;
    }

    /**
     * Override equals method.
     *
     * @param o - Object to be compared
     * @return true if there is a match between the id from both accounts.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(account, that.account);
    }

    /**
     * Override hashCode method
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(account);
    }

    /**
     * Get account balance
     *
     * @return account balance
     */
    @Override
    public MoneyValue getBalance() {
        return this.balance;
    }

    /**
     * Get account designation
     *
     * @return account's designation
     */
    @Override
    public Designation getDesignation() {
        return this.account.getDesignation();
    }

    /**
     * Get cash account balance
     *
     * @return error message
     */
    @Override
    public AccountID getID() {
        return this.account.getId();
    }

    /**
     * Check if account has the same ID
     *
     * @param accountID - accountID
     * @return true if accountID is the same
     */
    @Override
    public boolean hasSameID(AccountID accountID) {
        return this.account.hasSameID(accountID);
    }

    /**
     * Check if two accounts are the same
     *
     * @param other - the other entity.
     * @return true, if accounts are the same
     */
    @Override
    public boolean sameValueAs(Account other) {
        return this.account.sameValueAs(other);
    }
}