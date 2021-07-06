package switchtwentytwenty.project.domain.domaindto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;

@NoArgsConstructor
public class TransferDomainDTO {

    @Setter(AccessLevel.PRIVATE)
    @Getter
    private String senderID;
    @Setter(AccessLevel.PRIVATE)
    @Getter
    private Email receiverID;
    @Setter(AccessLevel.PRIVATE)
    @Getter
    private AccountID originAccountID;
    @Setter(AccessLevel.PRIVATE)
    @Getter
    private AccountID destinationAccountID;
    @Setter(AccessLevel.PRIVATE)
    @Getter
    private MoneyValue amount;
    @Setter(AccessLevel.PRIVATE)
    @Getter
    private CategoryID categoryID;
    @Setter(AccessLevel.PRIVATE)
    @Getter
    private TransactionDate date;
    @Setter(AccessLevel.PRIVATE)
    @Getter
    private TransactionDescription description;

    //Builder Method
    public static class TransferBuilder {
        //Builder attributes
        private final TransferDomainDTO transferDomainMemberToMemberDTO;

        public TransferBuilder() {
            transferDomainMemberToMemberDTO = new TransferDomainDTO();
        }

        //Attributes Constructor methods
        /**
         * set origin account ID
         *
         * @param originAccountID - origin account ID
         * @return origin account ID
         */
        public TransferBuilder withOriginAccountID(AccountID originAccountID) {
            transferDomainMemberToMemberDTO.setOriginAccountID(originAccountID);
            return this;
        }

        /**
         * set origin destination ID
         *
         * @param destinationAccountID - destination account ID
         * @return destination account ID
         */
        public TransferBuilder withDestinationAccountID(AccountID destinationAccountID) {
            transferDomainMemberToMemberDTO.setDestinationAccountID(destinationAccountID);
            return this;
        }

        /**
         * set amount
         *
         * @param amount - amount
         * @return amount
         */
        public TransferBuilder withAmount(MoneyValue amount) {
            transferDomainMemberToMemberDTO.setAmount(amount);
            return this;
        }

        /**
         * set categoryID
         *
         * @param categoryID - categoryID
         * @return categoryID
         */
        public TransferBuilder withCategoryID(CategoryID categoryID) {
            transferDomainMemberToMemberDTO.setCategoryID(categoryID);
            return this;
        }

        /**
         * set date
         *
         * @param date - date
         * @return date
         */
        public TransferBuilder withDate(TransactionDate date) {
            transferDomainMemberToMemberDTO.setDate(date);
            return this;
        }

        /**
         * set sender ID
         * @param senderID - sender ID
         * @return sender ID
         */
        public TransferBuilder withSenderID(String senderID) {
            transferDomainMemberToMemberDTO.setSenderID(senderID);
            return this;
        }

        /**
         * set receiver ID
         * @param receiverID - receiver ID
         * @return receiver ID
         */
        public TransferBuilder withReceiverID(Email receiverID) {
            transferDomainMemberToMemberDTO.setReceiverID(receiverID);
            return this;
        }

        /**
         * set description
         * @param description - description
         * @return description
         */
        public TransferBuilder withDescription(TransactionDescription description) {
            transferDomainMemberToMemberDTO.setDescription(description);
            return this;
        }

        /**
         * TransferDomainDTO Builder
         * @return TransferDomainDTO
         */
        public TransferDomainDTO build() {
            return transferDomainMemberToMemberDTO;
        }
    }
}
