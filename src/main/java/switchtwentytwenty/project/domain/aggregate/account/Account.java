package switchtwentytwenty.project.domain.aggregate.account;

import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.transactiondata.MovementType;
import switchtwentytwenty.project.domain.share.dddtype.AggregateRoot;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.exception.DepositNotPossibleException;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;
import switchtwentytwenty.project.exception.WithdrawNotPossibleException;

public interface Account extends AggregateRoot<Account, AccountID> {

    /**
     * Allows to withdraw amounts from account.
     *
     * @param amount - amount to withdraw.
     * @return true if withdraw successfully occurs.
     */
    MovementType withdraw(MoneyValue amount) throws WithdrawNotPossibleException, InvalidMovementTypeException;

    /**
     * Allows to deposit amounts from account.
     *
     * @param amount - amount to deposit.
     * @return true if deposit successfully occurs.
     */
    MovementType deposit(MoneyValue amount) throws InvalidMovementTypeException, DepositNotPossibleException;

    /**
     * Verifies if same designation
     *
     * @param designation - designation to be compared
     * @return true if there is a match
     */
    boolean isSameDesignation(Designation designation);

    /**
     * Returns true if it is a cash Account.
     *
     * @return true if it is a cash Account
     */
    boolean isCashAccount();

    /**
     * Get balance of cash Account
     *
     * @return cashAccount method
     */
    MoneyValue getBalance();

    /**
     * Get accountID
     *
     * @return ID
     */
    AccountID getID();

    /**
     * Get account designation
     *
     * @return designation
     */
    Designation getDesignation();

    /**
     * Check if account has a specific designation
     *
     * @param accountID - account identifier
     * @return boolean
     */
    boolean hasSameID(AccountID accountID);

    /**
     * Get account type
     *
     * @return string with directory
     */
    String getAccountType();


}
