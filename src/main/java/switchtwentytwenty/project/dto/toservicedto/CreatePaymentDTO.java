package switchtwentytwenty.project.dto.toservicedto;

import java.util.Objects;

public class CreatePaymentDTO {
    //Attributes
    private final String familyID;
    private final String personID;
    private final String accountID;
    private final double amount;
    private final String description;
    private final String date;
    private final String categoryID;

    //Constructor Method

    /**
     * sole constructor
     * @param builder
     */
    private CreatePaymentDTO(PaymentBuilder builder) {
        this.familyID = builder.familyID;
        this.personID = builder.personID;
        this.accountID = builder.accountID;
        this.amount = builder.amount;
        this.description = builder.description;
        this.date = builder.date;
        this.categoryID = builder.categoryID;

    }

    //Getters and Setters

    /**
     * get category ID
     * @return categoryID
     */
    public String getCategoryID(){
        return categoryID;
    }

    /**
     * get family ID
     * @return familyID
     */
    public String getFamilyID() {
        return familyID;
    }

    /**
     * get person ID
     * @return personID
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * get account ID
     * @return accountID
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * get amount
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * get description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * get date
     * @return date
     */
    public String getDate() {
        return date;
    }

    //Business Method

    //Builder Method
    public static class PaymentBuilder {
        //Builder attributes
        private String familyID;
        private String personID;
        private String accountID;
        private double amount;
        private String description;
        private String date;
        private String categoryID;

        //Attributes Constructor methods

        /**
         * sole constructor
         * @param familyID - familyID
         * @return familyID
         */
        public PaymentBuilder familyID(String familyID) {
            this.familyID = familyID;
            return this;
        }

        /**
         * set personID
         * @param personID - personID
         * @return personID
         */
        public PaymentBuilder personID(String personID) {
            this.personID = personID;
            return this;
        }

        /**
         * set accountID
         * @param accountID - accountID
         * @return accountID
         */
        public PaymentBuilder accountID(String accountID) {
            this.accountID = accountID;
            return this;
        }

        /**
         * set categoryID
         * @param categoryID - categoryID
         * @return categoryID
         */
        public PaymentBuilder categoryID(String categoryID){
            this.categoryID = categoryID;
            return this;
        }

        /**
         * set amount
         * @param amount - amount
         * @return amount
         */
        public PaymentBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        /**
         * set description
         * @param description - description
         * @return description
         */
        public PaymentBuilder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * set date
         * @param date - date
         * @return date
         */
        public PaymentBuilder date(String date) {
            this.date = date;
            return this;
        }

        /**
         * build
         * @return createPaymentDTO
         */
        public CreatePaymentDTO build() {
            return new CreatePaymentDTO(this);
        }
    }

    /**
     * Equal method
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreatePaymentDTO)) return false;
        CreatePaymentDTO that = (CreatePaymentDTO) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(familyID, that.familyID) && Objects.equals(personID, that.personID) && Objects.equals(accountID, that.accountID) && Objects.equals(description, that.description) && Objects.equals(date, that.date) && Objects.equals(categoryID, that.categoryID);
    }

    /**
     * hash code method
     * @return value
     */
    @Override
    public int hashCode() {
        return Objects.hash(familyID, personID, accountID, amount, description, date, categoryID);
    }

}
