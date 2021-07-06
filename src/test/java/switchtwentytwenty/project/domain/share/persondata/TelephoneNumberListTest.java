package switchtwentytwenty.project.domain.share.persondata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TelephoneNumberListTest {

    @Test
    void addPhoneNumberToTheList() {
        //arrange
        boolean result;
        TelephoneNumberList phoneNumbers = new TelephoneNumberList();
        TelephoneNumber phoneNumber = new TelephoneNumber("911026177");
        //act
        result = phoneNumbers.add(phoneNumber);
        //assert
        assertTrue(result);
    }

    @Test
    void notPossibleToAddPhoneNumberToTheList() {
        //arrange
        boolean result;
        TelephoneNumberList phoneNumbers = new TelephoneNumberList();
        TelephoneNumber phoneNumber = null;
        //act
        result = phoneNumbers.add(phoneNumber);
        //assert
        assertFalse(result);
    }

    @Test
    void addAllPhoneNumberToTheList() {
        //arrange
        boolean result;
        TelephoneNumberList phoneNumbers = new TelephoneNumberList();
        TelephoneNumber phoneNumber1 = new TelephoneNumber("911026177");
        TelephoneNumber phoneNumber2 = new TelephoneNumber("962828987");
        List<TelephoneNumber> listOfPhoneNumbers = new ArrayList<>();
        listOfPhoneNumbers.add(phoneNumber1);
        listOfPhoneNumbers.add(phoneNumber2);
        //act
        result = phoneNumbers.addAll(listOfPhoneNumbers);
        //assert
        assertTrue(result);
    }

    @Test
    void notPossibleToAddAllPhoneNumberToTheList() {
        //arrange
        boolean result;
        TelephoneNumberList phoneNumbers = new TelephoneNumberList();
        List<TelephoneNumber> listOfPhoneNumbers = new ArrayList<>();
        //act
        result = phoneNumbers.addAll(listOfPhoneNumbers);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Create a PhoneNumbers with same values")
    public void objectWithSameValues() {
        //arrange
        boolean result;
        TelephoneNumberList phoneNumbers = new TelephoneNumberList();
        TelephoneNumber phoneNumber1 = new TelephoneNumber("911026177");
        TelephoneNumber phoneNumber2 = new TelephoneNumber("962828987");
        List<TelephoneNumber> listOfPhoneNumbers = new ArrayList<>();
        listOfPhoneNumbers.add(phoneNumber1);
        listOfPhoneNumbers.add(phoneNumber2);
        phoneNumbers.addAll(listOfPhoneNumbers);

        TelephoneNumberList phoneNumbers2 = new TelephoneNumberList();
        TelephoneNumber phoneNumber3 = new TelephoneNumber("911026177");
        TelephoneNumber phoneNumber4 = new TelephoneNumber("962828987");
        List<TelephoneNumber> listOfPhoneNumbers2 = new ArrayList<>();
        listOfPhoneNumbers2.add(phoneNumber3);
        listOfPhoneNumbers2.add(phoneNumber4);
        phoneNumbers2.addAll(listOfPhoneNumbers2);
        //act
        result = phoneNumbers.equals(phoneNumbers2);
        //assert
        assertTrue(result);

    }

    @Test
    @DisplayName("Create a Phone Number with invalid characters")
    public void objectWithDifferentValues() {
        //arrange
        boolean result;
        TelephoneNumberList phoneNumbers = new TelephoneNumberList();
        TelephoneNumber phoneNumber1 = new TelephoneNumber("911026177");
        TelephoneNumber phoneNumber2 = new TelephoneNumber("962828987");
        List<TelephoneNumber> listOfPhoneNumbers = new ArrayList<>();
        listOfPhoneNumbers.add(phoneNumber1);
        listOfPhoneNumbers.add(phoneNumber2);
        phoneNumbers.addAll(listOfPhoneNumbers);

        TelephoneNumberList phoneNumbers2 = new TelephoneNumberList();
        TelephoneNumber phoneNumber3 = new TelephoneNumber("916546177");
        TelephoneNumber phoneNumber4 = new TelephoneNumber("966878987");
        List<TelephoneNumber> listOfPhoneNumbers2 = new ArrayList<>();
        listOfPhoneNumbers2.add(phoneNumber3);
        listOfPhoneNumbers2.add(phoneNumber4);
        phoneNumbers2.addAll(listOfPhoneNumbers2);
        //act
        result = phoneNumbers.equals(phoneNumbers2);
        //assert
        assertFalse(result);

    }

    @Test
    @DisplayName("Create a Phone Number with invalid characters")
    public void objectWithDifferentValuesAndDifferentSizes() {
        //arrange
        boolean result;
        TelephoneNumberList phoneNumbers = new TelephoneNumberList();
        TelephoneNumber phoneNumber1 = new TelephoneNumber("911026177");
        TelephoneNumber phoneNumber2 = new TelephoneNumber("962828987");
        List<TelephoneNumber> listOfPhoneNumbers = new ArrayList<>();
        listOfPhoneNumbers.add(phoneNumber1);
        listOfPhoneNumbers.add(phoneNumber2);
        phoneNumbers.addAll(listOfPhoneNumbers);

        TelephoneNumberList phoneNumbers2 = new TelephoneNumberList();
        TelephoneNumber phoneNumber3 = new TelephoneNumber("916546177");
        List<TelephoneNumber> listOfPhoneNumbers2 = new ArrayList<>();
        listOfPhoneNumbers2.add(phoneNumber3);
        phoneNumbers2.addAll(listOfPhoneNumbers2);
        //act
        result = phoneNumbers.equals(phoneNumbers2);
        //assert
        assertFalse(result);

    }

    @Test
    @DisplayName("Same Object")
    void sameObject() {
        //arrange
        TelephoneNumberList phoneNumbers = new TelephoneNumberList();
        TelephoneNumber phoneNumber1 = new TelephoneNumber("911026177");
        TelephoneNumber phoneNumber2 = new TelephoneNumber("962828987");
        List<TelephoneNumber> listOfPhoneNumbers = new ArrayList<>();
        listOfPhoneNumbers.add(phoneNumber1);
        listOfPhoneNumbers.add(phoneNumber2);
        phoneNumbers.addAll(listOfPhoneNumbers);
        //act
        TelephoneNumberList otherPhoneNumbers = phoneNumbers;
        //assert
        assertEquals(phoneNumbers, otherPhoneNumbers);
    }

    @Test
    @DisplayName("Identical Object")
    void identicalObject() {
        //arrange
        TelephoneNumberList phoneNumbers = new TelephoneNumberList();
        TelephoneNumber phoneNumber1 = new TelephoneNumber("911026177");
        TelephoneNumber phoneNumber2 = new TelephoneNumber("962828987");
        List<TelephoneNumber> listOfPhoneNumbers = new ArrayList<>();
        listOfPhoneNumbers.add(phoneNumber1);
        listOfPhoneNumbers.add(phoneNumber2);
        phoneNumbers.addAll(listOfPhoneNumbers);
        //act
        TelephoneNumberList otherPhoneNumbers = new TelephoneNumberList();
        otherPhoneNumbers.addAll(listOfPhoneNumbers);
        //assert
        assertEquals(phoneNumbers, otherPhoneNumbers);
    }

    @Test
    @DisplayName("Compare with null Object")
    void compareWithNullObject() {
        //arrange
        TelephoneNumberList phoneNumbers = new TelephoneNumberList();
        TelephoneNumber phoneNumber1 = new TelephoneNumber("911026177");
        TelephoneNumber phoneNumber2 = new TelephoneNumber("962828987");
        List<TelephoneNumber> listOfPhoneNumbers = new ArrayList<>();
        listOfPhoneNumbers.add(phoneNumber1);
        listOfPhoneNumbers.add(phoneNumber2);
        phoneNumbers.addAll(listOfPhoneNumbers);
        //assert
        boolean result = phoneNumbers.equals(null);
        assertFalse(result);
        assertNotEquals(null, phoneNumbers);
    }

    @Test
    @DisplayName("Different Classes")
    void differentClasses() {
        //arrange
        TelephoneNumberList phoneNumbers = new TelephoneNumberList();
        TelephoneNumber phoneNumber1 = new TelephoneNumber("911026177");
        TelephoneNumber phoneNumber2 = new TelephoneNumber("962828987");
        List<TelephoneNumber> listOfPhoneNumbers = new ArrayList<>();
        listOfPhoneNumbers.add(phoneNumber1);
        listOfPhoneNumbers.add(phoneNumber2);
        phoneNumbers.addAll(listOfPhoneNumbers);
        //assert
        assertNotEquals(listOfPhoneNumbers, phoneNumbers);
    }

    @Test
    @DisplayName("Same Hash 2")
    void sameHashOne() {
        //arrange
        TelephoneNumberList phoneNumbers = new TelephoneNumberList();
        TelephoneNumber phoneNumber1 = new TelephoneNumber("911026177");
        TelephoneNumber phoneNumber2 = new TelephoneNumber("962828987");
        List<TelephoneNumber> listOfPhoneNumbers = new ArrayList<>();
        listOfPhoneNumbers.add(phoneNumber1);
        listOfPhoneNumbers.add(phoneNumber2);
        phoneNumbers.addAll(listOfPhoneNumbers);
        //act
        TelephoneNumberList otherPhoneNumbers = new TelephoneNumberList();
        otherPhoneNumbers.addAll(listOfPhoneNumbers);

        int hash1 = phoneNumbers.hashCode();
        int hash2 = otherPhoneNumbers.hashCode();

        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Same Hash 1")
    void sameHashTwo() {
        //arrange
        TelephoneNumberList phoneNumbers = new TelephoneNumberList();
        TelephoneNumber phoneNumber1 = new TelephoneNumber("911026177");
        TelephoneNumber phoneNumber2 = new TelephoneNumber("962828987");
        List<TelephoneNumber> listOfPhoneNumbers = new ArrayList<>();
        listOfPhoneNumbers.add(phoneNumber1);
        listOfPhoneNumbers.add(phoneNumber2);
        phoneNumbers.addAll(listOfPhoneNumbers);
        //act
        TelephoneNumberList otherPhoneNumbers = new TelephoneNumberList();
        List<TelephoneNumber> otherListOfPhoneNumbers = new ArrayList<>();
        //change order
        otherListOfPhoneNumbers.add(phoneNumber2);
        otherListOfPhoneNumbers.add(phoneNumber1);
        otherPhoneNumbers.addAll(otherListOfPhoneNumbers);

        int hash1 = phoneNumbers.hashCode();
        int hash2 = otherPhoneNumbers.hashCode();

        //assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Not same Hash")
    void notSameHash() {
        //arrange
        TelephoneNumberList phoneNumbers = new TelephoneNumberList();
        TelephoneNumber phoneNumber1 = new TelephoneNumber("911026177");
        TelephoneNumber phoneNumber2 = new TelephoneNumber("962828987");
        List<TelephoneNumber> listOfPhoneNumbers = new ArrayList<>();
        listOfPhoneNumbers.add(phoneNumber1);
        listOfPhoneNumbers.add(phoneNumber2);
        phoneNumbers.addAll(listOfPhoneNumbers);
        //act
        TelephoneNumberList otherPhoneNumbers = new TelephoneNumberList();
        List<TelephoneNumber> otherListOfPhoneNumbers = new ArrayList<>();
        //change order
        otherListOfPhoneNumbers.add(phoneNumber2);
        otherPhoneNumbers.addAll(otherListOfPhoneNumbers);

        int hash1 = phoneNumbers.hashCode();
        int hash2 = otherPhoneNumbers.hashCode();

        //assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Test toStringList : empty list")
    void testToStringList_Empty() {
        //arrange
        TelephoneNumberList phoneNumbers = new TelephoneNumberList();
        List<String> expected = new ArrayList<>();
        //act
        List<String> result = phoneNumbers.toStringList();
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test toStringList : one element list")
    void testToStringList_OneElement() {
        //arrange
        TelephoneNumberList phoneNumbers = new TelephoneNumberList();
        TelephoneNumber phoneNumber = new TelephoneNumber("911026177");
        List<String> expected = new ArrayList<>();
        expected.add("911026177");
        phoneNumbers.add(phoneNumber);
        //act
        List<String> result = phoneNumbers.toStringList();
        //assert
        assertEquals(expected, result);
    }
}