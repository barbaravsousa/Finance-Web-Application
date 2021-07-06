package switchtwentytwenty.project.domain.share.familydata;

import lombok.Getter;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;
import switchtwentytwenty.project.util.Util;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

public class RelationType implements ValueObject {

    //Attributes

    @Getter
    private final String denomination;
    @Getter
    private final String oppositeDenomination;


    //Constructor Methods

    /**
     * Sole constructor
     *
     * @param denomination         - denomination of the relation type
     * @param oppositeDenomination - denomination of the opposite relation type
     */
    private RelationType(String denomination, String oppositeDenomination) {
        this.denomination = denomination;
        this.oppositeDenomination = oppositeDenomination;
    }


    //Business Methods

    /**
     * Initializes a newly RelationType object.
     *
     * @param relationTypeName - relation type denomination
     * @return RelationType instance
     * @throws IOException - configuration file unable to be read
     * @throws InvalidRelationTypeException - invalid relation type denomination
     */
    public static RelationType getInstance(String relationTypeName) throws IOException, InvalidRelationTypeException {
        isNullOrEmptyString(relationTypeName);
        String oppositeRelationTypeName = getOppositeRelationTypeName(relationTypeName);
        return new RelationType(relationTypeName, oppositeRelationTypeName);
    }

    /**
     * Returns the inverse relation type.
     *
     * @return instance
     */
    public RelationType getInverse() {
        return new RelationType(this.oppositeDenomination, this.denomination);
    }

    /**
     * Validate if null or empty string
     *
     * @param string - a string
     */
    private static void isNullOrEmptyString(String string) {

        if (string == null) {
            throw new NullPointerException("Null string");
        }

        if (string.trim().length() == 0) {
            throw new IllegalArgumentException("Empty string");
        }
    }

    /**
     * Get the opposite relation type name/denomination.
     *
     * @param relationTypeName - relation type name/denomination
     * @return the opposite relation typ name/denomination
     */
    private static String getOppositeRelationTypeName(String relationTypeName) throws IOException, InvalidRelationTypeException {

        String denomination;
        String oppositeDenomination;
        Properties properties = Util.loadConfigurationFile(Constants.RELATION_TYPE_CONFIG_FILE);
        Set<String> keys = properties.stringPropertyNames();

        for (String key : keys) {
            String value = properties.getProperty(key);
            denomination = value.split("&")[0];
            oppositeDenomination = value.split("&")[1];

            if (denomination.equals(relationTypeName)) {
                return oppositeDenomination;
            } else if (oppositeDenomination.equals(relationTypeName)) {
                return denomination;
            }
        }

        throw new InvalidRelationTypeException("This relation type is not valid");
    }

    /**
     * Verifies if the denomination is CHILD.
     *
     * @return true if there is a match
     */
    public boolean isAChildRelation() {
        return this.denomination.equals(Constants.CHILD);
    }

    /**
     * Verifies if the denomination is PARENT.
     *
     * @return true if there is a match
     */
    public boolean isAParentRelation() {
        return this.denomination.equals(Constants.PARENT);
    }


    //Equals and HashCode

    /**
     * Override equals().
     *
     * @param object to be compared with
     * @return true if denomination as oppositeDenomination have a match
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        RelationType that = (RelationType) object;
        return Objects.equals(denomination, that.denomination) && Objects.equals(oppositeDenomination, that.oppositeDenomination);
    }

    /**
     * Override hashCode().
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(denomination, oppositeDenomination);
    }

    /**
     * toString method
     * @return variable in string
     */
    @Override
    public String toString() {
        return this.denomination;
    }
}
