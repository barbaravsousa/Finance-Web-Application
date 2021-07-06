package switchtwentytwenty.project.domain.share.transactiondata;


import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;

import java.util.Objects;

public class MovementType {
    //Attributes
    private final String type;

    //Constructor Methods

    /**
     * Sole Constructor
     *
     * @param type - type
     * @throws InvalidMovementTypeException
     */
    public MovementType(String type) throws InvalidMovementTypeException {
        if (type == null) {
            throw new NullPointerException("Parameter is null");
        }
        type = type.toLowerCase();
        if (Constants.MOVEMENTS_TYPE.contains(type)) {
            this.type = type;
        } else {
            throw new InvalidMovementTypeException("Invalid Type");
        }
    }

    /**
     * Override equals().
     *
     * @param o - Object to be compared with.
     * @return true if the attribute relationType is a match.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovementType)) return false;
        MovementType that = (MovementType) o;
        return Objects.equals(type, that.type);
    }

    /**
     * Override hashCode().
     *
     * @return boolean
     */
    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    /**
     * to String
     * @return variable in string
     */
    @Override
    public String toString() {
        return this.type;
    }
}
