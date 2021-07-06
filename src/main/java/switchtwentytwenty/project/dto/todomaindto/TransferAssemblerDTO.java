package switchtwentytwenty.project.dto.todomaindto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.transactiondata.MovementType;
import switchtwentytwenty.project.domain.share.transactiondata.SystemDateEntry;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;

public class TransferAssemblerDTO {

    //Attributes

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private AccountID originAccountID;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private AccountID destinationAccountID;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private CategoryID categoryID;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private MovementType credit;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private MovementType debit;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private MoneyValue amount;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private TransactionDate date;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private TransactionDescription description;
    @Getter
    @Setter
    private SystemDateEntry systemDateEntry;


    //Builder Class

    public static class TransferAssemblerDTOBuilder {

        //Builder attributes
        private final TransferAssemblerDTO transferAssemblerDTO;

        /**
         * Sole constructor.
         */
        public TransferAssemblerDTOBuilder() {
            transferAssemblerDTO = new TransferAssemblerDTO();
        }

        //Attributes Constructor methods

        /**
         * Define origin account identifier.
         *
         * @param originAccountID - account identifier
         * @return this
         */
        public TransferAssemblerDTO.TransferAssemblerDTOBuilder withOriginAccountID(AccountID originAccountID) {
            transferAssemblerDTO.setOriginAccountID(originAccountID);
            return this;
        }

        /**
         * Define destination account identifier.
         *
         * @param destinationAccountID - account identifier
         * @return this
         */
        public TransferAssemblerDTO.TransferAssemblerDTOBuilder withDestinationAccountID(AccountID destinationAccountID) {
            transferAssemblerDTO.setDestinationAccountID(destinationAccountID);
            return this;
        }

        /**
         * Define category identifier.
         *
         * @param categoryID - category identifier
         * @return this
         */
        public TransferAssemblerDTO.TransferAssemblerDTOBuilder withCategoryID(CategoryID categoryID) {
            transferAssemblerDTO.setCategoryID(categoryID);
            return this;
        }

        /**
         * Define movement type.
         *
         * @param debit - movement type
         * @return this
         */
        public TransferAssemblerDTO.TransferAssemblerDTOBuilder withDebit(MovementType debit) {
            transferAssemblerDTO.setDebit(debit);
            return this;
        }

        /**
         * Define movement type.
         *
         * @param credit - movement type
         * @return this
         */
        public TransferAssemblerDTO.TransferAssemblerDTOBuilder withCredit(MovementType credit) {
            transferAssemblerDTO.setCredit(credit);
            return this;
        }

        /**
         * Define amount.
         *
         * @param amount - money
         * @return this
         */
        public TransferAssemblerDTO.TransferAssemblerDTOBuilder withAmount(MoneyValue amount) {
            transferAssemblerDTO.setAmount(amount);
            return this;
        }

        /**
         * Define date.
         *
         * @param date - transfer date
         * @return this
         */
        public TransferAssemblerDTO.TransferAssemblerDTOBuilder withDate(TransactionDate date) {
            transferAssemblerDTO.setDate(date);
            return this;
        }

        /**
         * Define description.
         *
         * @param description - transfer description
         * @return this
         */
        public TransferAssemblerDTO.TransferAssemblerDTOBuilder withDescription(TransactionDescription description) {
            transferAssemblerDTO.setDescription(description);
            return this;
        }

        /**
         * Define system entry date.
         *
         * @param systemDateEntry - transfer system entry date
         * @return this
         */
        public TransferAssemblerDTO.TransferAssemblerDTOBuilder withSystemDateEntry(SystemDateEntry systemDateEntry) {
            transferAssemblerDTO.setSystemDateEntry(systemDateEntry);
            return this;
        }

        /**
         * Return fully build DTO instance.
         *
         * @return DTO
         */
        public TransferAssemblerDTO build() {
            return this.transferAssemblerDTO;
        }

    }
}
