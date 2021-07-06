package switchtwentytwenty.project.domain.share.transactiondata;

import switchtwentytwenty.project.dto.todomaindto.TransferInformationDTO;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.dto.todomaindto.TransferAssemblerDTO;

import java.text.ParseException;

public class Transfer implements Transaction {

    //Attributes

    private final Movement debit;
    private final Movement credit;
    private final BaseTransaction baseTransaction;

    //Constructor Method
    /**
     * Constructor for creation of new Transfer.
     *
     * @param dto - data transfer object
     * @param balance - account balance after transaction
     * @throws ParseException - read
     */
    public Transfer(TransferInformationDTO dto, MoneyValue balance) throws ParseException {

        this.debit = createMovement(dto.getOriginAccountID(), dto.getDebit(), dto.getAmount());
        this.credit = createMovement(dto.getDestinationAccountID(), dto.getCredit(), dto.getAmount());

        this.baseTransaction = new BaseTransaction(dto.getCategoryID(), dto.getDescription(), dto.getDate(), balance, null);
    }

    /**
     * Constructor for transaction between Data model and Domain model.
     *
     * @param dto - data transfer object
     * @param balance - account balance after transaction
     * @throws ParseException - read
     */
    public Transfer(TransferAssemblerDTO dto, MoneyValue balance) throws ParseException {

        this.debit = createMovement(dto.getOriginAccountID(), dto.getDebit(), dto.getAmount());
        this.credit = createMovement(dto.getDestinationAccountID(), dto.getCredit(), dto.getAmount());

        this.baseTransaction = new BaseTransaction(dto.getCategoryID(), dto.getDescription(), dto.getDate(), balance, dto.getSystemDateEntry());
    }

    /**
     * create movement
     * @param accountID - accountID
     * @param type - movement type
     * @param amount - amount
     * @return movement created
     */
    private Movement createMovement(AccountID accountID, MovementType type, MoneyValue amount) {
        return new Movement(accountID, type, amount);
    }


    //Business Methods

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

    /**
     * Verifies if the transaction is a transfer.
     *
     * @return boolean
     */
    public boolean isTransfer() {
        return true;
    }

    /**
     * Verifies if the transaction is a payment.
     *
     * @return boolean
     */
    public boolean isPayment() {
        return false;
    }

    /**
     * Verifies if transaction as any relation to the given account identifier.
     *
     * @param accountID - account identifier
     * @return boolean
     */
    public boolean isFromAccount(AccountID accountID) {
        return this.debit.isFromAccount(accountID) || this.credit.isFromAccount(accountID);
    }


    //Getters

    /**
     * Returns the amount of the movement that corresponds to the given account identifier.
     *
     * @param accountID - account identifier
     * @return amount
     */
    @Override
    public MoneyValue getMyAmount(AccountID accountID) {
        if (this.debit.isFromAccount(accountID)) {
            return this.debit.getAmount();
        }
        if (this.credit.isFromAccount(accountID)) {
            return this.credit.getAmount();
        }
        throw new IllegalArgumentException("Movements are not from this account");
    }

    /**
     * Returns the movement type of the movement that corresponds to the given account identifier.
     *
     * @param accountID - account identifier
     * @return movement type
     */
    @Override
    public MovementType getMyMovementType(AccountID accountID) {
        if (this.debit.isFromAccount(accountID)) {
            return this.debit.getType();
        }
        if (this.credit.isFromAccount(accountID)) {
            return this.credit.getType();
        }
        throw new IllegalArgumentException("Movements are not from this account");
    }

    /**
     * Returns the transaction date.
     *
     * @return transaction date
     */
    @Override
    public TransactionDate getDate() {
        return this.baseTransaction.date;
    }

    /**
     * Returns the transaction balance to this date.
     *
     * @return balance to this date
     */
    @Override
    public MoneyValue getBalanceToThisDate() {
        return this.baseTransaction.getBalance();
    }

    /**
     * Returns the category of the transaction.
     *
     * @return transaction's category
     */
    @Override
    public CategoryID getCategoryID() {
        return this.baseTransaction.getCategoryID();
    }

    /**
     * Returns transaction's description.
     *
     * @return transactions's description
     */
    @Override
    public TransactionDescription getDescription() {
        return this.baseTransaction.getDescription();
    }

    /**
     * Get the account identifier of the debit movement.
     *
     * @return account identifier
     */
    public AccountID getOriginAccountID(){
        return this.debit.getAccountID();
    }

    /**
     * Get the account identifier of the credit movement.
     *
     * @return account identifier
     */
    public AccountID getDestinationAccountID(){
        return this.credit.getAccountID();
    }

    /**
     * Get system entry date.
     *
     * @return system entry date
     */
    public SystemDateEntry getSystemDateEntry() {
        return this.baseTransaction.getSystemDateEntry();
    }

    /**
     * Get balance after transaction
     *
     * @return balance
     */
    public MoneyValue getBalance() {
        return this.baseTransaction.getBalance();
    }

    /**
     * Get account ID for payment transactions
     *
     * @return Unsupported Operation
     */
    @Override
    public AccountID getAccountID() {
        throw new UnsupportedOperationException();
    }

    /**
     * Get transaction amount.
     *
     * @return amount
     */
    public MoneyValue getAmount() {
        return this.credit.getAmount();
    }

}
