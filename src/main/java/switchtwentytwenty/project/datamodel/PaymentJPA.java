package switchtwentytwenty.project.datamodel;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "Payment")
public class PaymentJPA {


    @EmbeddedId
    @Getter
    private final PaymentIDJPA id;

    @Getter
    @ManyToOne()
    @JoinColumn(name = "ledger_id")
    private LedgerJPA ledgerJPA;

    /**
     * Sole constructor
     */
    protected PaymentJPA() {
        this.id = new PaymentIDJPA();
    }

    public static class PaymentJPABuilder {

        //Attribute

        private final PaymentJPA paymentJPA;


        //Constructor methods

        /**
         * sole constructor
         */
        public PaymentJPABuilder() {
            this.paymentJPA = new PaymentJPA();
        }

        /**
         * Set category identifier.
         *
         * @param categoryID - category identifier
         * @return this
         */
        public PaymentJPABuilder withCategoryID(String categoryID) {
            this.paymentJPA.id.setCategoryID(categoryID);
            return this;
        }

        /**
         * Set description.
         *
         * @param description - payment description
         * @return this
         */
        public PaymentJPABuilder withDescription(String description) {
            this.paymentJPA.id.setDescription(description);
            return this;
        }

        /**
         * Set date.
         *
         * @param date - payment description
         * @return this
         */
        public PaymentJPABuilder withDate(String date) {
            this.paymentJPA.id.setDate(date);
            return this;
        }

        /**
         * Set system entry date
         *
         * @param systemEntryDate - payment's system's entry date
         * @return this
         */
        public PaymentJPABuilder withSystemEntryDate(String systemEntryDate) {
            this.paymentJPA.id.setSystemDateEntry(systemEntryDate);
            return this;
        }

        /**
         * Set balance
         *
         * @param balance - payment's balance
         * @return this
         */
        public PaymentJPABuilder withBalance(double balance) {
            this.paymentJPA.id.setBalance(balance);
            return this;
        }

        /**
         * Set account identifier.
         *
         * @param accountID - payment's account identifier
         * @return this
         */
        public PaymentJPABuilder withAccountID(String accountID) {
            this.paymentJPA.id.setAccountID(accountID);
            return this;
        }

        /**
         * Set amount.
         *
         * @param amount - payment's amount
         * @return this
         */
        public PaymentJPABuilder withAmount(double amount) {
            this.paymentJPA.id.setAmount(amount);
            return this;
        }

        /**
         * Set ledger JPA.
         *
         * @param ledgerJPA - payment's ledger foreign key
         * @return this
         */
        public PaymentJPABuilder withLedgerJPA(LedgerJPA ledgerJPA) {
            this.paymentJPA.ledgerJPA = ledgerJPA;
            return this;
        }

        /**
         * Build a complete paymentJPA instance.
         *
         * @return paymentJPA instance
         */
        public PaymentJPA build() {
            return this.paymentJPA;
        }
    }

}
