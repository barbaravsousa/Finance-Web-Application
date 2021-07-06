package switchtwentytwenty.project.domain.share.persondata.address;

import switchtwentytwenty.project.domain.share.dddtype.ValueObject;

import java.util.Objects;

public class Country extends AddressContent implements ValueObject {


    //Constructor methods

    /**
     * Sole constructor.
     *
     * @param denomination string
     */
    public Country(String denomination) {
        super(denomination);
    }


    //Equals and hashCode

    /**
     * Override equals.
     *
     * @param o to be compares
     * @return true if there is a match
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country otherCountry = (Country) o;
        return Objects.equals(denomination, otherCountry.denomination);
    }

    /**
     * Override hashCode.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(denomination);
    }
}