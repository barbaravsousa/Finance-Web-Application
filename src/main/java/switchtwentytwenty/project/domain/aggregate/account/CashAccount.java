package switchtwentytwenty.project.domain.aggregate.account;

import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.transactiondata.MovementType;
import switchtwentytwenty.project.exception.AccountNotCreatedException;
import switchtwentytwenty.project.exception.DepositNotPossibleException;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;
import switchtwentytwenty.project.exception.WithdrawNotPossibleException;

import java.util.Objects;

public class CashAccount implements Account {

    //Attributes

    private final RootAccount rootAccount;
    private MoneyValue cashAmount;


    // Constructor methods

    /**
     * Sole constructor
     *
     * @param initialAmount - initial amount
     * @param accountDesignation   - designation of the account
     */
    protected CashAccount(AccountID cashAccountID, MoneyValue initialAmount, Designation accountDesignation) throws AccountNotCreatedException {
        if (!initialAmount.isPositiveOrZero() ) {
            throw new AccountNotCreatedException("Cash Account can't have a negative value.");
        }
        this.cashAmount = initialAmount;
        this.rootAccount = new RootAccount(cashAccountID, accountDesignation);
    }

    /**
     * Allows to withdraw amounts from account.
     *
     * @param amount - amount to withdraw.
     * @return true if withdraw successfully occurs.
     */
    @Override
    public MovementType withdraw(MoneyValue amount) throws WithdrawNotPossibleException, InvalidMovementTypeException {
        if (this.cashAmount.compare(amount) < 0) {
            throw new WithdrawNotPossibleException("Not enough money.");
        }
        if (!amount.isPositiveOrZero()) {
            throw new WithdrawNotPossibleException("Invalid money. Amount must be positive.");
        }
        this.cashAmount = this.cashAmount.subtract(amount);
        return new MovementType(Constants.DEBIT);
    }

    /**
     * Allows to deposit amounts from account.
     *
     * @param amount - amount to deposit.
     * @return true if deposit successfully occurs.
     */
    @Override
    public MovementType deposit(MoneyValue amount) throws InvalidMovementTypeException, DepositNotPossibleException {
        if (!amount.isPositiveOrZero()) {
            throw new DepositNotPossibleException("Can not deposit negative amount");
        }
        this.cashAmount = this.cashAmount.sum(amount);
        return new MovementType(Constants.CREDIT);
    }


    /**
     * Returns true if it is a cash account.
     *
     * @return true if it is a cash account
     */
    @Override
    public boolean isCashAccount() {
        return true;
    }

    /**
     * Check if account has a specific designation
     * @param designation - designation to be compared
     * @return true, if designation is the same
     */
    @Override
    public boolean isSameDesignation(Designation designation) {
        return this.rootAccount.isSameDesignation(designation);
    }

    /**
     * Get the balance of the account
     * @return the amount of balance of the account
     */
    @Override
    public MoneyValue getBalance() {
        return this.cashAmount;
    }

    /**
     * Get the accountID
     * @return - accountID
     */
    @Override
    public AccountID getID() {
        return this.rootAccount.getId();
    }

    /**
     * Get the account designation
     * @return - designation
     */
    @Override
    public Designation getDesignation() {
        return this.rootAccount.getDesignation();
    }

    /**
     * Check if account has a specific accountID
     * @param accountID - other accountID
     * @return true, if accountIDs are the same
     */
    @Override
    public boolean hasSameID(AccountID accountID) {
        return this.rootAccount.hasSameID(accountID);
    }

    //Equal method

    /**
     * Override equals method.
     *
     * @param o - Object to be compared
     * @return true if there is a match
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashAccount that = (CashAccount) o;
        return Objects.equals(rootAccount, that.rootAccount);
    }

    /**
     * Override hashCode method
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(rootAccount);
    }

    /**
     * Check if two accounts are the same
     * @param other - the other entity.
     * @return true, of accounts are the same
     */
    @Override
    public boolean sameValueAs(Account other) {
        return this.rootAccount.sameValueAs(other);
    }

    /**
     * Get account type.
     *
     * @return string
     */
    @Override
    public String getAccountType() {
        return Constants.CASH_ACCOUNT_TYPE;
    }

}
