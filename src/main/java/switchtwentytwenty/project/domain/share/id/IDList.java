package switchtwentytwenty.project.domain.share.id;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class IDList<K extends ID> {

    //Attributes
    @Getter
    private final List<K> list;

    //Constructor Methods

    /**
     * Sole constructor
     */
    protected IDList() {
        this.list = new ArrayList<>();
    }

    //Getter and Setters

    //Business Methods

    /**
     * Add id instance to the list
     *
     * @param id instance
     * @return true if successfully added
     */
    public void add(K id) {
        if (id == null) {
            throw new NullPointerException("Argument points to nothing in memory, i.e., is null");
        }
        this.list.add(id);
    }

    /**
     * Add collection to the list
     *
     * @param idList List instance
     */
    public void addAll(List<K> idList) {
        if (idList == null) {
            throw new NullPointerException("List points to nothing in memory, i.e., null");
        }
        this.list.addAll(idList);
    }

    /**
     * Get list's size
     *
     * @return the size of the list
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Get ID in index i
     *
     * @param i - index
     * @return return index
     */
    public K getID(int i) {
        return this.list.get(i);
    }

    /**
     * Check if ID List is empty
     *
     * @return true, if list is empty
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }
    //Equals and HashCode

    /**
     * Checks if an ID belongs to the ID list
     *
     * @param id - id
     * @return true if the ID belongs to the ID list, false otherwise
     */
    public boolean contains(K id) {
        for (K idInList : list) {
            if (idInList.equals(id)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Iterates over the list of ID's and turns them into string's and adds them to a new list.
     *
     * @return list with string id's
     */
    public List<String> idToString() {
        List<String> idStringList = new ArrayList<>();
        for (K idInList : list) {
            String id = idInList.toString();
            idStringList.add(id);
        }
        return idStringList;
    }

    /**
     * Equal method
     * @param o - object
     * @return true, if both objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IDList idList1 = (IDList) o;
        return Objects.equals(list, idList1.list);
    }

    /**
     * hash code method
     * @return hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
