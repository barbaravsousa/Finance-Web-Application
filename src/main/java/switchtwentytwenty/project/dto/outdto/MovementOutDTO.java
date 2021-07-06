package switchtwentytwenty.project.dto.outdto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovementOutDTO extends RepresentationModel<MovementOutDTO> {

    //Attributes

    @Getter
    private double amount;
    @Getter
    private String accountID;
    @Getter
    private String movementType;
    @Getter
    private String date;
    @Getter
    private String category;
    @Getter
    private double balanceToThisDate;
    @Getter
    private String description;


    //Equals and hashCode

    /**
     * Override hashCode.
     *
     * @param o - other instance
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovementOutDTO that = (MovementOutDTO) o;
        return Double.compare(that.amount, amount) == 0 && Double.compare(that.balanceToThisDate, balanceToThisDate) == 0 && Objects.equals(accountID, that.accountID) && Objects.equals(movementType, that.movementType) && Objects.equals(date, that.date) && Objects.equals(category, that.category) && Objects.equals(description, that.description);
    }

    /**
     * Override hashCode.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), amount, accountID, movementType, date, category, balanceToThisDate, description);
    }

    //Builder class

    public static class OutMovementDTOBuilder {


        //Attribute

        private final MovementOutDTO dto;


        //Constructor method

        /**
         * Sole constructor.
         */
        public OutMovementDTOBuilder() {
            this.dto = new MovementOutDTO();
        }


        //Methods

        /**
         * Set amount.
         *
         * @param amount of money
         * @return this
         */
        public OutMovementDTOBuilder withAmount(double amount) {
            dto.amount = amount;
            return this;
        }

        /**
         * Set account ID.
         *
         * @param accountID account identifier
         * @return this
         */
        public OutMovementDTOBuilder withAccountID(String accountID) {
            dto.accountID = accountID;
            return this;
        }

        /**
         * Set movement type.
         *
         * @param movementType - type of movement.
         * @return this
         */
        public OutMovementDTOBuilder withMovementType(String movementType) {
            dto.movementType = movementType;
            return this;
        }

        /**
         * Set date.
         *
         * @param date of the transaction
         * @return this
         */
        public OutMovementDTOBuilder withDate(String date) {
            dto.date = date;
            return this;
        }

        /**
         * Set category.
         *
         * @param category of the transaction
         * @return this
         */
        public OutMovementDTOBuilder withCategory(String category) {
            dto.category = category;
            return this;
        }

        /**
         * Set balance to this date.
         *
         * @param balanceToThisDate - balance to this date
         * @return this
         */
        public OutMovementDTOBuilder withBalanceToThisDate(double balanceToThisDate) {
            dto.balanceToThisDate = balanceToThisDate;
            return this;
        }

        /**
         * Set description.
         *
         * @param description - balance to this date
         * @return this
         */
        public OutMovementDTOBuilder withDescription(String description) {
            dto.description = description;
            return this;
        }

        /**
         * Return DTO fully build.
         *
         * @return DTO
         */
        public MovementOutDTO build() {
            return this.dto;

        }

    }

}
