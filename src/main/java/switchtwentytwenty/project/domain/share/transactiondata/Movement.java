package switchtwentytwenty.project.domain.share.transactiondata;


import lombok.AccessLevel;
import lombok.Getter;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.id.AccountID;

import java.util.Objects;


public class Movement {

    //Attributes
    @Getter
    private final AccountID accountID;
    @Getter
    private final MovementType type;
    @Getter(AccessLevel.PROTECTED)
    private final MoneyValue amount;

    //Business Method

    /**
     * Sole Constructor
     *
     * @param amount    - amount of the movement
     * @param accountID - account identifier
     * @param type      - type of movement: credit or debit
     */
    public Movement(AccountID accountID, MovementType type, MoneyValue amount) {
        this.amount = amount;
        this.type = type;
        this.accountID = accountID;
    }


    // Business methods

    /**
     * Check if movement is from this account
     *
     * @param accountID - account identifier
     * @return true, if movement is from this account
     */
    public boolean isFromAccount(AccountID accountID) {
        return this.accountID.equals(accountID);
    }


    // Equals method

    /**
     * Override equals().
     *
     * @param o - Object to be compared with.
     * @return true if the attribute relationType is a match.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movement movement = (Movement) o;
        return Objects.equals(accountID, movement.accountID) &&
                Objects.equals(type, movement.type) &&
                Objects.equals(amount, movement.amount);
    }

    /**
     * Override hashCode().
     *
     * @return boolean
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountID, type, amount);
    }
}
