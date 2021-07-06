package switchtwentytwenty.project.domain.share.list;

import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.id.AccountID;

import java.util.ArrayList;
import java.util.List;

public class PersonList {

    //Attributes

    private final List<Person> list;


    //Constructor Methods

    /**
     * Sole constructor.
     */
    public PersonList() {
        this.list = new ArrayList<>();
    }


    //Business Methods

    /**
     * Adds a Person instance to the list.
     *
     * @param person instance
     */
    public void add(Person person) {
        this.list.add(person);
    }

    /**
     * Verifies if someone in the list is the holder of the account
     *
     * @param accountID - account identifier
     * @return boolean
     */
    public boolean ownsAccount(AccountID accountID) {
        for (Person person : list) {
            if (person.isMyAccount(accountID)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the list of users.
     *
     * @return list with all the users
     */
    public List<Person> getPersonList() {
        return new ArrayList<>(list);
    }


    /**
     * Return list size
     *
     * @return integer
     */
    public int size() {
        return list.size();
    }
}
