package switchtwentytwenty.project.domain.aggregate.ledger;

import switchtwentytwenty.project.domain.share.dddtype.AggregateRoot;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.ID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata.Payment;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;
import switchtwentytwenty.project.domain.share.transactiondata.Transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ledger implements AggregateRoot<Ledger, ID> {


    //Attributes
    private final LedgerID id;
    private final TransactionList transactions;


    //Constructor Methods

    /**
     * Sole constructor.
     *
     * @param id identification
     */
    protected Ledger(LedgerID id) {
        this.id = id;
        this.transactions = new TransactionList();
    }


    //Getter and Setters

    /**
     * Returns id of the instance that implements the interface
     *
     * @return ID instance
     */
    @Override
    public LedgerID getID() {
        return this.id;
    }


    //Business Methods

    /**
     * Confirms if object as same id as given argument.
     *
     * @param id - ID instance
     * @return true if there is a match
     */
    @Override
    public boolean hasSameID(ID id) {
        return this.id.equals(id);
    }

    /**
     * Entities compare by identity, not by attributes.
     *
     * @param other - the other entity.
     * @return true if th identities are the same, regardless of other attributes.
     */
    @Override
    public boolean sameValueAs(Ledger other) {
        return this.id.equals(other.id) && this.transactions.equals(other.transactions);
    }

    /**
     * Gets all the movements from a given account and between a certain dates
     *
     * @param accountID - account identifier
     * @param startDate - initial date
     * @param endDate   - final date
     */
    public List<Transaction> getListOfMovementsBetweenDates(AccountID accountID, TransactionDate startDate, TransactionDate endDate) {
        return this.transactions.getListOfMovementsBetweenDates(accountID, startDate, endDate);
    }

    /**
     * Add transaction to ledger
     *
     * @param transaction - transaction
     * @return true, if transaction added successfully
     */
    public boolean addTransaction(Transaction transaction) {
        return this.transactions.addTransaction(transaction);
    }

    /**
     * Add transaction list in the ledger
     *
     * @param transactionList - transaction list
     * @return true, if transaction list added successfully
     */
    protected boolean addAllTransaction(List<Transaction> transactionList) {
        return this.transactions.addAll(transactionList);
    }

    /**
     * Get list of payments in the ledger
     *
     * @return list of payments
     */
    public List<Payment> getPaymentList() {
        return this.transactions.getPaymentList();
    }

    /**
     * Get list of transfers in the ledger
     *
     * @return list of transfers
     */
    public List<Transfer> getTransferList() {
        return this.transactions.getTransferList();
    }

    /**
     * Get list of transactions in the ledger.
     *
     * @return list of transactions.
     */
    public List<Transaction> getTransactionList() {
        return this.transactions.getTransactionList();
    }

    //Equals and HashCode

    /**
     * Override equals.
     *
     * @param other - other instance
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Ledger ledger = (Ledger) other;
        return Objects.equals(id, ledger.id);
    }

    /**
     * Override hashCode.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    //Inner class

    private class TransactionList {

        //Attributes
        private final List<Transaction> list;


        //Constructor

        /**
         * Sole constructor.
         */
        private TransactionList() {
            this.list = new ArrayList<>();
        }


        //Business Methods

        /**
         * Add transaction in the ledger
         *
         * @param transaction - transaction
         * @return true, if transaction added successfully
         */
        public boolean addTransaction(Transaction transaction) {
            if (transaction != null) {
                return this.list.add(transaction);
            }
            return false;
        }

        /**
         * Add transaction list in the ledger
         *
         * @param transactionList - transaction list
         * @return true, if transaction list added successfully
         */
        public boolean addAll(List<Transaction> transactionList) {
            if (transactionList != null) {
                return this.list.addAll(transactionList);
            }
            return false;
        }

        /**
         * Get list of transactions.
         * @return The list of transactions.
         */
        public List<Transaction> getTransactionList() {
            return new ArrayList<>(this.list);
        }

        /**
         * Gets all the movements from a given account and between a certain dates
         *
         * @param accountID - account identifier
         * @param startDate - initial date
         * @param endDate   - final date
         */
        private List<Transaction> getListOfMovementsBetweenDates(AccountID accountID, TransactionDate startDate, TransactionDate endDate) {
            List<Transaction> transactionsBetweenDates = new ArrayList<>();
            for (Transaction transaction : this.list) {
                if (transaction.isFromAccount(accountID) && transaction.isBetweenDates(startDate, endDate)) {
                    transactionsBetweenDates.add(transaction);
                }
            }
            return transactionsBetweenDates;
        }

        /**
         * Get list of payments in the ledger
         *
         * @return list of payments
         */
        private List<Payment> getPaymentList() {
            List<Payment> paymentList = new ArrayList<>();
            for (Transaction transaction : list) {
                if (transaction.isPayment()) {
                    paymentList.add((Payment) transaction);
                }
            }
            return paymentList;
        }

        /**
         * Get list of transfers in the ledger
         *
         * @return list of transfers
         */
        private List<Transfer> getTransferList() {
            List<Transfer> transferList = new ArrayList<>();
            for (Transaction transaction : list) {
                if (transaction.isTransfer()) {
                    transferList.add((Transfer) transaction);
                }
            }
            return transferList;
        }
    }

}
