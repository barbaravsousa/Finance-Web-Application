package switchtwentytwenty.project.domain.share.familydata;

import lombok.Getter;
import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import switchtwentytwenty.project.domain.share.id.Email;

public class FamilyRelation implements ValueObject {

    //Attributes

    //The person is "relationType" of kin
    @Getter
    private final Email personID;
    @Getter
    private final Email kinID;
    @Getter
    private final RelationType relationType;


    //Constructor Methods

    /**
     * Sole Constructor
     * @param personID - person identifier
     * @param kinID - kin identifier
     * @param relationType - type of relation
     */
    public FamilyRelation(Email personID, Email kinID, RelationType relationType) {
        validateParameters(personID, kinID, relationType);
        this.personID = personID;
        this.kinID = kinID;
        this.relationType = relationType;
    }


    //Getter and Setters


    //Business Methods

    /**
     * Compare if the two persons are elements in the class attributes.
     *
     * @param other - the other value object
     * @return true if the values are the same
     */
    public boolean samePersonsInvolved(FamilyRelation other) {
        return this.personID.equals(other.personID) && this.kinID.equals(other.kinID) || this.personID.equals(other.kinID) && this.kinID.equals(other.personID);
    }

    /**
     * Compare if the two persons are elements in the class attributes.
     *
     * @param kinID - kin identifier
     * @param personID - person identifier
     * @return true if the values are the same
     */
    public boolean samePersonsInvolved(Email personID, Email kinID) {
        return this.personID.equals(personID) && this.kinID.equals(kinID);
    }

    /**
     * Validate constructor's parameters by confirming that they aren't null
     *
     * @param personID     - person ID
     * @param kinID        - kin ID
     * @param relationType - RelationType instance
     */
    private void validateParameters(Email personID, Email kinID, RelationType relationType) {
        if (personID == null) {
            throw new NullPointerException("Person ID is null");
        }
        if (kinID == null) {
            throw new NullPointerException("Kin ID is null");
        }
        if (relationType == null) {
            throw new NullPointerException("Relation type is null");
        }
    }

    /**
     * Get inverse - but still valid - family relation.
     *
     * @return FamilyRelation instance
     */
    public FamilyRelation getInverse() {
        return new FamilyRelation(this.kinID, this.personID, this.relationType.getInverse());
    }

    /**
     * Verifies if the relation is of type child-parent.
     *
     * @return true if it is a child-parent relation
     */
    public boolean isMyChild(Email parentID, Email childID) {
        if (parentID == null || childID == null) {
            return false;
        }
        if (this.relationType.isAChildRelation() && parentID.equals(this.kinID) && childID.equals(this.personID)) {
            return true;
        }
        return this.relationType.isAParentRelation() && parentID.equals(this.personID) && childID.equals(this.kinID);
    }


    //Equals and HashCode


}
