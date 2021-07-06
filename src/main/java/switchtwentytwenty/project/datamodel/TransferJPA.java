package switchtwentytwenty.project.datamodel;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "Transfer")
public class TransferJPA {

    @EmbeddedId
    @Getter
    private final TransferIDJPA id;

    @Getter
    @ManyToOne()
    @JoinColumn(name = "ledger_id")
    private LedgerJPA ledgerJPA;

    //Constructor method

    /**
     * Sole constructor
     */
    protected TransferJPA() {
        this.id = new TransferIDJPA();
    }

    public static class TransferJPABuilder {

        //Attribute

        private final TransferJPA transferJPA;

        //Constructor

        /**
         * Sole constructor
         */
        public TransferJPABuilder() {
            this.transferJPA = new TransferJPA();
        }

        /**
         * Set the origin account identifier.
         *
         * @param originAccountID - account identifier
         * @return this
         */
        public TransferJPABuilder withOriginAccountID(String originAccountID) {
            this.transferJPA.id.setOriginAccountID(originAccountID);
            return this;
        }

        /**
         * Set the destination account identifier.
         *
         * @param destinationAccountID - account identifier
         * @return this
         */
        public TransferJPABuilder withDestinationAccountID(String destinationAccountID) {
            this.transferJPA.id.setDestinationAccountID(destinationAccountID);
            return this;
        }

        /**
         * Set the category identifier.
         *
         * @param categoryID - category identifier
         * @return this
         */
        public TransferJPABuilder withCategoryID(String categoryID) {
            this.transferJPA.id.setCategoryID(categoryID);
            return this;
        }

        /**
         * Set the description.
         *
         * @param description - transfer's description
         * @return this
         */
        public TransferJPABuilder withDescription(String description) {
            this.transferJPA.id.setDescription(description);
            return this;
        }

        /**
         * Set date.
         *
         * @param date - transfer's date
         * @return this
         */
        public TransferJPABuilder withDate(String date) {
            this.transferJPA.id.setDate(date);
            return this;
        }

        /**
         * Set systemDateEntry.
         *
         * @param systemDateEntry - transfer's systemDateEntry
         * @return this
         */
        public TransferJPABuilder withSystemDateEntry(String systemDateEntry) {
            this.transferJPA.id.setSystemDateEntry(systemDateEntry);
            return this;
        }

        /**
         * Set balance.
         *
         * @param balance - transfer's balance
         * @return this
         */
        public TransferJPABuilder withBalance(double balance) {
            this.transferJPA.id.setBalance(balance);
            return this;
        }

        /**
         * Set amount.
         *
         * @param amount - transfer's amount
         * @return this
         */
        public TransferJPABuilder withAmount(double amount) {
            this.transferJPA.id.setAmount(amount);
            return this;
        }

        /**
         * Set ledger JPA.
         *
         * @param ledgerJPA - transfer's ledger
         * @return this
         */
        public TransferJPABuilder withLedgerJPA(LedgerJPA ledgerJPA) {
            this.transferJPA.ledgerJPA = ledgerJPA;
            return this;
        }

        /**
         * Return fully build transferJPA instance.
         *
         * @return transferJPA
         */
        public TransferJPA build() {
            return this.transferJPA;
        }
    }
}
