package switchtwentytwenty.project.domain.share.dddtype;

import switchtwentytwenty.project.domain.share.id.ID;

public interface Entity<T extends Entity, K extends ID> {

    /**
     * Entities compare by identity, not by attributes.
     *
     * @param other - the other entity.
     * @return true if th identities are the same, regardless of other attributes.
     */
    boolean sameValueAs(T other);

}
