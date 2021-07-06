package switchtwentytwenty.project.domain.share.list;

import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import switchtwentytwenty.project.domain.share.id.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmailList implements ValueObject {

    //Attributes
    private final List<Email> list;

    //Constructor Methods

    /**
     * Sole Constructor
     */
    public EmailList() {
        this.list = new ArrayList<>();
    }


    //Getter and Setters

    //Business Method

    /**
     * Add Email instance to the list
     *
     * @param email - Email instance
     * @return true if successful
     */
    public boolean add(Email email) {
        if (email != null) {
            this.list.add(email);
            return true;
        }
        return false;
    }

    /**
     * Add all Email instance to the list
     *
     * @param list - list of Emails
     * @return true if successfully added
     */
    public boolean addAll(List<Email> list) {
        if (list != null && !list.isEmpty()) {
            this.list.addAll(list);
            return true;
        }
        return false;
    }

    /**
     * Obtains the list of emails from an EmailList
     *
     * @return
     */
    public List<Email> getEmailList() {
        List<Email> other = new ArrayList<>();
        for (Email email : list) {
            other.add(email);
        }
        return other;
    }

    /**
     * Verifies if list of emails contains email
     *
     * @param inEmail - email
     * @return true, if inEmail exist in the list
     */
    public boolean contains(Email inEmail) {
        for (Email email : this.list) {
            if (email.equals(inEmail)) {
                return true;
            }
        }
        return false;
    }

    /**
     * to String method
     *
     * @return list of strings
     */
    public List<String> toStringList() {
        List<String> result = new ArrayList<>();
        for (Email email : this.list) {
            result.add(email.toString());
        }
        return result;
    }

    /**
     * Method to remove an element from a EmailList
     *
     * @param email
     * @return
     */
    public boolean remove(Email email) {
        if (email != null) {
            list.remove(email);
            return true;
        }
        return false;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailList that = (EmailList) o;
        return list.size() == that.list.size() && list.containsAll(that.list);
    }

    /**
     * Override hashCode.
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
