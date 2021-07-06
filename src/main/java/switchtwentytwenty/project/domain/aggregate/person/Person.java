package switchtwentytwenty.project.domain.aggregate.person;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import switchtwentytwenty.project.domain.share.dddtype.AggregateRoot;
import switchtwentytwenty.project.domain.share.id.*;
import switchtwentytwenty.project.domain.share.list.EmailList;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.outdto.UserProfileOutDTO;

import java.util.Objects;

public class Person implements AggregateRoot<Person, Email> {

    //Attributes
    @Getter
    private final Email mainEmail;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private EmailList otherEmails;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private AccountIDList accountIDList;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private PersonName name;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private VAT vat;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private BirthDate birthDate;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private TelephoneNumberList telephoneNumbers;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private Address address;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private FamilyID familyID;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private LedgerID ledgerID;


    // Constructor Methods

    /**
     * Constructor Method.
     */
    protected Person(Email mainEmail) {
        this.mainEmail = mainEmail;
        this.otherEmails = new EmailList();
        this.accountIDList = new AccountIDList();
        this.telephoneNumbers = new TelephoneNumberList();
    }


    //Business Methods
    /**
     * Returns id of the Person instance.
     *
     * @return ID instance
     */
    @Override
    public Email getID() {
        return this.mainEmail;
    }

    /**
     * Verifies if person's email contains email.
     *
     * @param email - email
     * @return true, if mail belongs to person
     */
    public boolean containsEmail(Email email) {
        return this.otherEmails.contains(email);
    }


    /**
     * Method to remove an email from a person
     * @param email
     * @return
     */
    public boolean removeEmail(Email email)
    {
        if(!containsEmail(email))
        {
            throw new IllegalArgumentException("Email not found");
        }
        otherEmails.remove(email);
        return true;
    }

    /**
     * Method to add an inputEmail to my list of emails.
     *
     * @param inputtedEmail - Email instance
     * @return true if successfully added
     */
    public void addEmail(Email inputtedEmail) {
        if (!checkIfEmailIsRegistered(inputtedEmail)) {
            otherEmails.add(inputtedEmail);
        } else {
            throw new IllegalArgumentException("Email not valid. Already exists in the app");
        }

    }

    /**
     * Method to verify is the inputtedEmail is already registered in the Person
     *
     * @param inputtedEmail - Emails instance
     * @return true if there is a registration
     */

    private boolean checkIfEmailIsRegistered(Email inputtedEmail) {
        for (Email email : otherEmails.getEmailList()) {
            if (email.equals(inputtedEmail)) {
               return true;
            }
        }
        boolean repeatedEmail = false;
        if (mainEmail.equals(inputtedEmail)) {
            repeatedEmail = true;
        }
        return repeatedEmail;

    }

    /**
     * Add accountID to list os person's accounts
     *
     * @param newAccountID account ID
     * @return true, if added successfully
     */
    public boolean addAccountID(AccountID newAccountID) {
        if (newAccountID != null) {
            this.accountIDList.add(newAccountID);
            return true;
        }
        return false;
    }

    /**
     * Confirms if the person as the same ID, aka same Person.
     *
     * @param personID - Person ID to compare with
     * @return true if there is a match
     */
    @Override
    public boolean hasSameID(Email personID) {
        if (personID == null) {
            throw new NullPointerException("Family ID is null");
        }
        return this.mainEmail.equals(personID);
    }

    /**
     * Check if given vat number corresponds to person's vat.
     * @param vat - vat number.
     * @return True if vat equals to person's vat.
     */
    public boolean hasSameVAT(VAT vat) {
        return this.vat.equals(vat);
    }

    /**
     * Returns true if the given family ID corresponds to the person family ID.
     *
     * @return true if there is a match
     */
    public boolean isMemberOfFamily(FamilyID familyID) {
        return this.familyID.equals(familyID);
    }

    /**
     * Checks if an account ID belongs to the person's account ID list
     *
     * @param accountID - account ID
     * @return true if the account ID belongs to the person's account ID list, false otherwise
     */
    public boolean isMyAccount(AccountID accountID) {
        return this.accountIDList.contains(accountID);
    }

    /**
     * Check if two persons have the same value
     *
     * @param other - the other entity.
     * @return true, if both Persons have same value
     */
    @Override
    public boolean sameValueAs(Person other) {
        return this.name.equals(other.name) &&
                this.vat.equals(other.vat) &&
                this.birthDate.equals(other.birthDate) &&
                this.mainEmail.equals(other.mainEmail) &&
                this.otherEmails.equals(other.otherEmails) &&
                this.telephoneNumbers.equals(other.telephoneNumbers) &&
                this.address.equals(other.address) &&
                this.familyID.equals(other.familyID);
    }

    /**
     * Method that creates a DTO and adds to it all the fields about the user
     *
     * @return a DTO that contains all the profile information of the user
     */
    public UserProfileOutDTO getProfile() {
        return new UserProfileOutDTO.UserProfileDTOBuilder()
                .withName(this.name.toString())
                .withVat(this.vat.toString())
                .withBirthDate(this.birthDate.toString())
                .withHouseNumber(this.address.getHouseNumber().toString())
                .withStreet(this.address.getStreet().toString())
                .withCity(this.address.getCity().toString())
                .withCountry(this.address.getCountry().toString())
                .withZipCode(this.address.getZipCode().toString())
                .withTelephoneNumbers(this.telephoneNumbers.toStringList())
                .withMainEmail(this.mainEmail.toString())
                .withOtherEmails(this.otherEmails.toStringList())
                .build();

    }

    //Equals and HashCode

    /**
     * Override equals().
     *
     * @param o - object to be compared
     * @return true if there is a match
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(mainEmail, person.mainEmail);
    }

    /**
     * Override hasCode.
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(mainEmail);
    }
}
