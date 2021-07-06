package switchtwentytwenty.project.domain.share.persondata.address;

import switchtwentytwenty.project.domain.share.dddtype.ValueObject;

import java.util.Objects;

public class Street extends AddressContent implements ValueObject {


    //Constructor methods

    /**
     * Sole constructor.
     *
     * @param denomination string
     */
    public Street(String denomination) {
        super(denomination);
    }


    //Equals and hasCode

    /**
     * Override equals.
     *
     * @param o to be compared
     * @return true if there is a match
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Street that = (Street) o;
        return Objects.equals(denomination, that.denomination);
    }

    /**
     * Override hasCode.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(denomination);
    }
}