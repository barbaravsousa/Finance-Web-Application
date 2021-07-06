package switchtwentytwenty.project.domain.share.dddtype;

import switchtwentytwenty.project.domain.share.id.ID;

public interface AggregateRoot<T extends Entity, K extends ID> extends Entity<T, K>{

    /**
     * Returns id of the instance that implements the interface
     *
     * @return ID instance
     */
    K getID();

    /**
     * Confirms if object as same id as given argument.
     *
     * @param objectID - object ID instance
     * @return true if there is a match
     */
    boolean hasSameID(K objectID);
}
