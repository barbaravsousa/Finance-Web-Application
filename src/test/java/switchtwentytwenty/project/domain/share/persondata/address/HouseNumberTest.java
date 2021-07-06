package switchtwentytwenty.project.domain.share.persondata.address;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class HouseNumberTest {

    @Test
    @DisplayName("Same Object")
    void sameObject() {
        //arrange
        HouseNumber houseNumber = new HouseNumber("2");
        //act
        HouseNumber sameHouseNumber = houseNumber;
        //assert
        assertEquals(houseNumber, sameHouseNumber);
    }

    @Test
    @DisplayName("null Object")
    void nullObject() {
        //arrange
        HouseNumber houseNumber = new HouseNumber("2");
        //act
        HouseNumber sameHouseNumber = null;
        //assert
        assertNotEquals(houseNumber, sameHouseNumber);
    }

    @Test
    @DisplayName("Identical Object")
    void identicalObject() {
        //arrange
        HouseNumber houseNumber = new HouseNumber("2");
        //act
        HouseNumber sameHouseNumber = new HouseNumber("2");
        //assert
        assertEquals(houseNumber, sameHouseNumber);
    }

    @Test
    @DisplayName("Different Object")
    void differentObject() {
        //arrange
        HouseNumber houseNumber = new HouseNumber("2");
        //act
        HouseNumber notSameHouseNumber = new HouseNumber("1");
        //assert
        assertNotEquals(houseNumber, notSameHouseNumber);
    }

    @Test
    @DisplayName("Different Class")
    void differentClass() {
        //arrange
        HouseNumber houseNumber = new HouseNumber("2");
        //act
        BigDecimal bigDecimal = new BigDecimal("2");
        //assert
        assertNotEquals(houseNumber, bigDecimal);
    }

    @Test
    @DisplayName("Same HashCode")
    void sameHashCode() {
        //arrange
        int hash1;
        int hash2;
        HouseNumber houseNumber = new HouseNumber("2");
        HouseNumber sameHouseNumber = new HouseNumber("2");
        //act
        hash1 = houseNumber.hashCode();
        hash2 = sameHouseNumber.hashCode();
        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Not the same HashCode")
    void notSameHashCode() {
        //arrange
        int hash1;
        int hash2;
        HouseNumber houseNumber = new HouseNumber("2");
        HouseNumber sameHouseNumber = new HouseNumber("3");
        //act
        hash1 = houseNumber.hashCode();
        hash2 = sameHouseNumber.hashCode();
        //assert
        assertNotEquals(hash1, hash2);
    }
}
