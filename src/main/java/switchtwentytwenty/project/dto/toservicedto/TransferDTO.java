package switchtwentytwenty.project.dto.toservicedto;

import java.util.Objects;

public class TransferDTO {

    //Attributes
    private String senderId;
    private String receiverId;
    private String originAccountId;
    private String destinationAccountId;
    private double amount;
    private String categoryId;
    private String description;
    private String date;


    //Constructor
    /**
     * Sole constructor
     */
    private TransferDTO(TransferDTOBuilder builder) {
        this.senderId = builder.senderId;
        this.receiverId = builder.receiverId;
        this.originAccountId = builder.originAccountId;
        this.destinationAccountId = builder.destinyAccountId;
        this.amount = builder.amount;
        this.categoryId = builder.categoryId;
        this.description = builder.description;
        this.date = builder.date;
    }


    //Getter and Setters

    /**
     * @return senderId
     */
    public String getSenderID() {
        return this.senderId;
    }

    /**
     * @param senderId - id of the sender
     */
    public void setSenderID(String senderId) {
        this.senderId = senderId;
    }

    /**
     * @return receiverId
     */
    public String getReceiverID() {
        return this.receiverId;
    }

    /**
     * @param receiverId - id of the receiver
     */
    public void setReceiverID(String receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * @return originAccountId
     */
    public String getOriginAccountID() {
        return this.originAccountId;
    }

    /**
     * @param originAccountId - id of the origin account
     */
    public void setOriginAccountID(String originAccountId) {
        this.originAccountId = originAccountId;
    }

    /**
     * @return destinyAccountId
     */
    public String getDestinationAccountID() {
        return this.destinationAccountId;
    }

    /**
     * @param destinyAccountId - id of the destiny account
     */
    public void setDestinationAccountID(String destinyAccountId) {
        this.destinationAccountId = destinyAccountId;
    }

    /**
     * @return amount
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * @param amount - amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return categoryId
     */
    public String getCategoryID() {
        return this.categoryId;
    }

    /**
     * @param categoryId - id of the category
     */
    public void setCategoryID(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description - description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return date
     */
    public String getDate() {
        return this.date;
    }

    /**
     * @param date - date
     */
    public void setDate(String date) {
        this.date = date;
    }

    //Business Methods
    public static class TransferDTOBuilder {

        //Builder attributtes
        private String senderId;
        private String receiverId;
        private String originAccountId;
        private String destinyAccountId;
        private double amount;
        private String categoryId;
        private String description;
        private String date;

        //Attributes Constructor methods
        public TransferDTOBuilder senderId(String senderId) {
            this.senderId = senderId;
            return this;
        }

        public TransferDTOBuilder receiverId(String receiverId){
            this.receiverId = receiverId;
            return this;
        }

        public TransferDTOBuilder originAccountId(String originAccountId){
            this.originAccountId = originAccountId;
            return this;
        }

        public TransferDTOBuilder destinationAccountId(String destinyAccountId){
            this.destinyAccountId = destinyAccountId;
            return this;
        }

        public TransferDTOBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public TransferDTOBuilder categoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public TransferDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TransferDTOBuilder date(String date) {
            this.date = date;
            return this;
        }

        //MemberDTO Constructor
        public TransferDTO build() {
            return new TransferDTO(this);
        }
    }

    //Equals method
    /**
     * Override equal method
     *
     * @param o - object that we want to compare to
     * @return true if the two objects are true, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferDTO)) return false;
        TransferDTO that = (TransferDTO) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(senderId, that.senderId) && Objects.equals(receiverId, that.receiverId) && Objects.equals(originAccountId, that.originAccountId) && Objects.equals(destinationAccountId, that.destinationAccountId) && Objects.equals(categoryId, that.categoryId) && Objects.equals(description, that.description) && Objects.equals(date, that.date);
    }

    /**
     * Override hashCode method
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(senderId, receiverId, originAccountId, destinationAccountId, amount, categoryId, description, date);
    }
}
