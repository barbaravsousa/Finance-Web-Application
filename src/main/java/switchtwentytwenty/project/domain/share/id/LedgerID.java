package switchtwentytwenty.project.domain.share.id;

import java.util.Objects;
import java.util.UUID;

public class LedgerID implements ID {


    //Attribute
    private final UUID id;

    //Constructor Methods

    /**
     * Sole constructor.
     *
     * @param id - identifier
     */
    public LedgerID(UUID id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    /**
     * Override method toString.
     *
     * @return ID in string format.
     */
    @Override
    public String toString() {
        return this.id.toString();
    }

    //Getter and Setters

    //Business Methods

    //Equals and HashCode

    /**
     * Override equals method.
     *
     * @param o to be compared
     * @return true if there is a match
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LedgerID ledgerID = (LedgerID) o;
        return Objects.equals(id, ledgerID.id);
    }

    /**
     * Override hashCode method.
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
