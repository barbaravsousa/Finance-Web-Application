package switchtwentytwenty.project.domain.aggregate.account;

import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.ID;

import java.util.Objects;


public class RootAccount {

    // Attributes
    private final AccountID accountID;
    private final Designation accountDesignation;


    // Constructor Methods

    /**
     * Sole constructor
     *
     * @param accountDesignation - designation of the account
     */
    public RootAccount(AccountID accountID, Designation accountDesignation) {
        this.accountID = accountID;
        this.accountDesignation = accountDesignation;
    }


    // Business Methods




    /**
     * Allows to check if given designation is equals to account's designation.
     *
     * @param designation Receives the designation to check.
     * @return True if is same designation.
     */
    public boolean isSameDesignation(Designation designation) {
        return this.accountDesignation.equals(designation);
    }


    // Setters and Getters Methods

    // Other Methods
    public AccountID getId() {
        return this.accountID;
    }

    public Designation getDesignation(){
        return this.accountDesignation;
    }

    /**
     * Check if account has a specific accountID
     * @param accountID - accountID
     * @return true if there is a match
     */
    public boolean hasSameID(ID accountID) {
        return this.accountID.equals(accountID);
    }

    /**
     * Check if two accounts have the same value
     * @param other - other account
     * @return true, if accounts are the same
     */
    public boolean sameValueAs(Account other) {
        return this.accountID.equals(other.getID()) && this.accountDesignation.equals(other.getDesignation());
    }

    // Equals Method

    /**
     * Override equals method.
     *
     * @param o - Object to be compared
     * @return true if there is a match between the id from both accounts.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RootAccount that = (RootAccount) o;
        return Objects.equals(accountID, that.accountID);
    }

    /**
     * Override hashCode method
     *
     * @return int hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountID);
    }

}
