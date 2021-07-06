package switchtwentytwenty.project.domain.aggregate.account;


import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.id.AccountID;


public class CurrentAccount extends BankAccount {


    //Attribute


    //Constructor methods

    /**
     * Sole constructor
     *
     * @param accountDesignation - designation of the account
     */
    public CurrentAccount(AccountID accountID, AccountDesignation accountDesignation) {
        super(accountID, accountDesignation);
    }

    // Equals method

    /**
     * Override equals().
     *
     * @param o - Object to be compared with.
     * @return true if the attribute relationType is a match.
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * Override hashCode().
     *
     * @return boolean
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Get account type.
     *
     * @return string
     */
    @Override
    public String getAccountType() {
        return Constants.CURRENT_ACCOUNT_TYPE;
    }


}
