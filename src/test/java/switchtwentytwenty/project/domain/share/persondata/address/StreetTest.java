package switchtwentytwenty.project.domain.share.persondata.address;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class StreetTest {

    @Test
    @DisplayName("Same Object")
    void sameObject() {
        //arrange
        Street street = new Street("Rua");
        //act
        Street sameStreet = street;
        //assert
        assertEquals(street, sameStreet);
    }

    @Test
    @DisplayName("null Object")
    void nullObject() {
        //arrange
        Street street = new Street("Rua");
        //act
        Street sameStreet = null;
        //assert
        assertNotEquals(street, sameStreet);
    }

    @Test
    @DisplayName("Identical Object")
    void identicalObject() {
        //arrange
        Street street = new Street("Rua");
        //act
        Street sameStreet = new Street("Rua");
        //assert
        assertEquals(street, sameStreet);
    }

    @Test
    @DisplayName("Different Object")
    void differentObject() {
        //arrange
        Street street = new Street("Rua");
        //act
        Street sameStreet = new Street("Strasse");
        //assert
        assertNotEquals(street, sameStreet);
    }

    @Test
    @DisplayName("Different Class")
    void differentClass() {
        //arrange
        Street street = new Street("Rua");
        //act
        BigDecimal bigDecimal = new BigDecimal("300");
        //assert
        assertNotEquals(street, bigDecimal);
    }

    @Test
    @DisplayName("Same HashCode")
    void sameHashCode() {
        //arrange
        int hash1;
        int hash2;
        Street street = new Street("Rua");
        Street sameStreet = new Street("Rua");
        //act
        hash1 = street.hashCode();
        hash2 = sameStreet.hashCode();
        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Not the same HashCode")
    void notSameHashCode() {
        //arrange
        int hash1;
        int hash2;
        Street street = new Street("Rua");
        Street sameStreet = new Street("Strasse");
        //act
        hash1 = street.hashCode();
        hash2 = sameStreet.hashCode();
        //assert
        assertNotEquals(hash1, hash2);
    }
}
