package switchtwentytwenty.project.domain.share.persondata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.exception.InvalidPersonNameException;

import static org.junit.jupiter.api.Assertions.*;

class PersonNameTest {

    @Test
    @DisplayName("Same values as")
    void sameValueAs() throws InvalidPersonNameException {
        PersonName name = new PersonName("Constantino");
        PersonName name1 = new PersonName("Constantino");
        boolean result = name.equals(name1);
        assertTrue(result);
    }

    @Test
    @DisplayName("Not same values as")
    void notSameValueAs() throws InvalidPersonNameException {
        PersonName name = new PersonName("Constantino");
        PersonName name1 = new PersonName("Joãozinho");
        boolean result = name.equals(name1);
        assertFalse(result);
    }

    @Test
    @DisplayName("Create Person name  successfully")
    void createValidPersonName() throws InvalidPersonNameException {
        PersonName name = new PersonName("Constantino");
        assertNotNull(name);
    }

    @Test
    @DisplayName("Failure creating person name: null")
    void failurePersonName_Null() {
        assertThrows(InvalidPersonNameException.class, () -> new PersonName(null));
    }

    @Test
    @DisplayName("Failure creating person name: empty")
    void failurePersonName_Empty() {
        assertThrows(InvalidPersonNameException.class, () -> new PersonName(""));
    }

    @Test
    @DisplayName("Failure creating person name: blank")
    void failurePersonName_Blank() {
        assertThrows(InvalidPersonNameException.class, () -> new PersonName("  "));
    }

    @Test
    @DisplayName("Failure creating person name: name with digits")
    void failurePersonName_failure() {
        assertThrows(InvalidPersonNameException.class, () -> new PersonName("Jor678"));
    }

    @Test
    @DisplayName("Failure creating person name: name with digits")
    void failurePersonName_failure1() {
        assertThrows(InvalidPersonNameException.class, () -> new PersonName("18965"));
    }

    @Test
    @DisplayName("Failure creating person name: name < >")
    void failurePersonName_failure2() {
        assertThrows(InvalidPersonNameException.class, () -> new PersonName("<script>"));
    }

    @Test
    @DisplayName("Same object")
    void sameObject() throws InvalidPersonNameException {
        //arrange
        PersonName personName = new PersonName("Margaret Howard");
        //act
        PersonName samePersonName = personName;
        //assert
        assertEquals(personName, samePersonName);
    }

    @Test
    @DisplayName("Identical object")
    void identicalObject() throws InvalidPersonNameException {
        //arrange
        PersonName personName = new PersonName("Margaret Howard");
        //act
        PersonName otherPersonName = new PersonName("Margaret Howard");
        //assert
        assertEquals(personName, otherPersonName);
    }

    @Test
    @DisplayName("Compare null object")
    void compareNullObject() throws InvalidPersonNameException {
        //arrange
        PersonName personName = new PersonName("Margaret Howard");
        //act
        PersonName nullPersonName = null;
        //assert
        assertNotEquals(personName, nullPersonName);
    }

    @Test
    @DisplayName("Same hash")
    void sameHash() throws InvalidPersonNameException {
        //arrange
        PersonName personName = new PersonName("Margaret Howard");
        //act
        PersonName otherPersonName = new PersonName("Margaret Howard");
        //assert
        assertEquals(personName.hashCode(), otherPersonName.hashCode());
    }

    @Test
    @DisplayName("Not same hash")
    void notSameHash() throws InvalidPersonNameException {
        //arrange
        PersonName personName = new PersonName("Margaret Howard");
        //act
        PersonName otherPersonName = new PersonName("John von Neumann");
        //assert
        assertNotEquals(personName.hashCode(), otherPersonName.hashCode());
    }

}