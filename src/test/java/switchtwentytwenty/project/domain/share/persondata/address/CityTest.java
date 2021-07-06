package switchtwentytwenty.project.domain.share.persondata.address;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CityTest {

    @Test
    @DisplayName("Same Object")
    void sameObject() {
        //arrange
        City city = new City("Hagen");
        //act
        City sameCity = city;
        //assert
        assertEquals(city, sameCity);
    }

    @Test
    @DisplayName("Null")
    void nullObject() {
        //arrange
        City city = new City("Hagen");
        //act
        City sameCity = null;
        //assert
        assertNotEquals(city, sameCity);
    }

    @Test
    @DisplayName("Identical Object")
    void identicalObject() {
        //arrange
        City city = new City("Hagen");
        //act
        City sameCity = new City("Hagen");
        //assert
        assertEquals(city, sameCity);
    }

    @Test
    @DisplayName("Different Object")
    void differentObject() {
        //arrange
        City city = new City("Hagen");
        //act
        City sameCity = new City("Mangualde");
        //assert
        assertNotEquals(city, sameCity);
    }

    @Test
    @DisplayName("Different Class")
    void differentClass() {
        //arrange
        City city = new City("Hagen");
        //act
        BigDecimal bigDecimal = new BigDecimal("100");
        //assert
        assertNotEquals(city, bigDecimal);
    }

    @Test
    @DisplayName("Same HashCode")
    void sameHashCode() {
        //arrange
        int hash1;
        int hash2;
        City city = new City("Hagen");
        City sameCity = new City("Hagen");
        //act
        hash1 = city.hashCode();
        hash2 = sameCity.hashCode();
        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Not the same HashCode")
    void notSameHashCode() {
        //arrange
        int hash1;
        int hash2;
        City city = new City("Hagen");
        City sameCity = new City("Mangualde");
        //act
        hash1 = city.hashCode();
        hash2 = sameCity.hashCode();
        //assert
        assertNotEquals(hash1, hash2);
    }
}
