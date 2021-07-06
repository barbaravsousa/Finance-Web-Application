package switchtwentytwenty.project.domain.aggregate.family;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.share.dddtype.AggregateRoot;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
import switchtwentytwenty.project.domain.share.familydata.RegistrationDate;
import switchtwentytwenty.project.domain.share.familydata.RelationType;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Family implements AggregateRoot<Family, FamilyID> {

    //Attributes
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private FamilyID familyID;
    private FamilyRelationList familyRelationList;
    @NonNull
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private RegistrationDate registrationDate;
    @NonNull
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private FamilyName name;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NonNull
    private Email administratorID;
    private AccountID cashAccountID;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PROTECTED)
    @NonNull
    private LedgerID ledgerID;


    //Constructor Methods

    /**
     * Sole constructor
     *
     * @param familyID - family familyID
     */
    protected Family(FamilyID familyID) {
        Objects.requireNonNull(familyID);
        this.familyID = familyID;
        this.registrationDate = new RegistrationDate();
        this.familyRelationList = new FamilyRelationList();
    }


    //Getter and Setters

    /**
     * Get family's cash account identifier
     *
     * @return Optional
     */
    public Optional<AccountID> getCashAccountID(){
        if (this.cashAccountID != null) {
            return Optional.of(this.cashAccountID);
        }
        return Optional.empty();
    }

    /**
     * set FamilyRelationList
     *
     * @param list - list of family relations
     */
    protected void setFamilyRelationList(List<FamilyRelation> list){
        this.familyRelationList = new FamilyRelationList(list);
    }

    /**
     * Method to verify if the person is the family administrator
     *
     * @param personID - person identifier
     * @return true if administrator id corresponds to given id
     */
    public boolean isAdministrator(Email personID) {
        return this.administratorID.equals(personID);
    }

    /**
     * Returns instance that implements ID
     *
     * @return ID
     */
    @Override
    public FamilyID getID() {
        return this.familyID;
    }

    /**
     * Gets family relations list from a person given his/her ID
     *
     * @param personID - person ID
     * @return a list of family relations
     */
    public List<FamilyRelation> getFamilyRelationByPersonID(Email personID) {
        return this.familyRelationList.getFamilyRelationListByID(personID);
    }

    /**
     * Checks if the child identifier is from one of my children.
     *
     * @param parentID - parent identifier
     * @param childID  - child identifier
     * @return true if there is a child-parent relation
     */
    public boolean isMyChild(Email parentID, Email childID) {
        return this.familyRelationList.isMyChild(parentID, childID);
    }


    //Business Methods

    /**
     * add accountID to family's account ID list
     *
     * @param newAccountID . accountID
     * @return true, if accountID added successfully
     */
    public boolean addAccountID(AccountID newAccountID) {
        if (this.cashAccountID == null) {
            this.cashAccountID = newAccountID;
            return true;
        }
        return false;
    }

    /**
     * Verifies if family already has a cash account
     *
     * @return
     */
    public boolean hasCashAccount() {
        return cashAccountID != null;
    }

    /**
     * Returns id of the instance that implements the interface
     *
     * @return true if the identical instances
     */
    public boolean sameValueAs(Family other) {
        return this.familyID.equals(other.familyID) &&
                this.name.equals(other.name) &&
                this.registrationDate.equals(other.registrationDate) &&
                this.administratorID.equals(other.administratorID);
    }

    /**
     * Verifies if the account belongs to the family
     *
     * @param accountID - account identifier
     * @return true if the account is yours
     */
    public boolean ownsCashAccount(AccountID accountID) {
        return this.cashAccountID != null && cashAccountID.equals(accountID);
    }

    /**
     * Confirms if the family as the same ID, aka same Family
     *
     * @param familyID - Family ID to compare with
     * @return true if there is a match
     */
    @Override
    public boolean hasSameID(FamilyID familyID) {
        return this.familyID.equals(familyID);
    }

    /**
     * Create FamilyRelation
     *
     * @param personID     - person ID
     * @param kinID        - kin ID
     * @param relationType - RelationType instance
     */
    public FamilyRelation createFamilyRelation(Email personID, Email kinID, RelationType relationType) {
        FamilyRelation familyRelation = new FamilyRelation(personID, kinID, relationType);
        this.familyRelationList.add(familyRelation);
        return familyRelation;
    }

    /**
     * Check if account belongs to the family
     * @param accountID - accountID
     * @return true, if account is from family
     */
    public boolean isMyAccount(AccountID accountID){
        return this.cashAccountID.equals(accountID);
    }

    //Equals and HashCode

    /**
     * Override equals method
     *
     * @param other - other value object
     * @return true if there is a match
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Family family = (Family) other;
        return Objects.equals(familyID, family.familyID);
    }

    /**
     * Override hashCode method
     *
     * @return true if the same hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(familyID);
    }

    /**
     * Get family's relation list
     * @return family relation list
     */
    public List<FamilyRelation> getFamilyRelationList() {
        return this.familyRelationList.getFamilyRelationList();
    }

    /**
     * Return a family relation that includes the given persons.
     *
     * @param personID person identifier
     * @param kinID kin identifier
     * @return optional
     */
    public Optional<FamilyRelation> getFamilyRelationByIDs(Email personID, Email kinID) {
        return this.familyRelationList.getFamilyRelationByIDs(personID, kinID);
    }

    //Inner Class

    private static class FamilyRelationList {

        //Attributes

        private final List<FamilyRelation> familyRelations;


        //Constructor Methods

        /**
         * Sole constructor
         */
        private FamilyRelationList() {
            this.familyRelations = new ArrayList<>();
        }

        /**
         * Sole constructor
         */
        private FamilyRelationList(List<FamilyRelation> initialList) {
            this.familyRelations = new ArrayList<>(initialList);
        }

        //Business Methods

        /**
         * Adds a FamilyRelation instance to the list
         *
         * @param familyRelation instance
         */
        private void add(FamilyRelation familyRelation) {
            boolean found = false;
            int listSize = this.familyRelations.size();
            for (int i = 0; i < listSize && !found; i++) {
                FamilyRelation relation = familyRelations.get(i);
                if (relation.samePersonsInvolved(familyRelation.getPersonID(), familyRelation.getKinID())) {
                    familyRelations.remove(relation);
                    found = true;
                } else if (relation.samePersonsInvolved(familyRelation.getKinID(), familyRelation.getPersonID())){
                    familyRelations.remove(relation);
                    familyRelation = familyRelation.getInverse();
                    found = true;
                }
            }
            this.familyRelations.add(familyRelation);
        }

        /**
         * Checks if the child identifier is from one of my children.
         *
         * @param parentID - parent identifier
         * @param childID  - child identifier
         * @return true if there is a child-parent relation
         */
        public boolean isMyChild(Email parentID, Email childID) {
            for (FamilyRelation familyRelation : this.familyRelations) {
                if (familyRelation.isMyChild(parentID, childID)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Gets list of family relations from a person given his/her ID
         *
         * @param personID - person's ID
         * @return a list of family relations of a person
         */
        private List<FamilyRelation> getFamilyRelationListByID(Email personID) {
            List<FamilyRelation> familyRelationList = new ArrayList<>();

            for (FamilyRelation familyRelation : familyRelations) {
                if (familyRelation.getPersonID().equals(personID) || familyRelation.getKinID().equals(personID)) {
                    familyRelationList.add(familyRelation);
                }
            }
            return familyRelationList;
        }

        /**
         * Get family's relation list
         * @return family relation list
         */
        private List<FamilyRelation> getFamilyRelationList() {
            List<FamilyRelation> familyRelationList = new ArrayList<>();
            familyRelationList.addAll(familyRelations);
            return familyRelationList;
        }

        /**
         * Return a family relation that includes the given persons.
         *
         * @param personID person identifier
         * @param kinID kin identifier
         * @return optional
         */
        private Optional<FamilyRelation> getFamilyRelationByIDs(Email personID, Email kinID) {
            for (FamilyRelation familyRelation : familyRelations) {
                if (familyRelation.samePersonsInvolved(personID, kinID) || familyRelation.samePersonsInvolved(kinID, personID)) {
                    return Optional.of(familyRelation);
                }
            }
            return Optional.empty();
        }

    }
}
