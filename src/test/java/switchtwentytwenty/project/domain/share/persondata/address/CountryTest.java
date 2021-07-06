package switchtwentytwenty.project.domain.share.persondata.address;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CountryTest {

    @Test
    @DisplayName("Same Object")
    void sameObject() {
        //arrange
        Country country = new Country("UK");
        //act
        Country sameCountry = country;
        //assert
        assertEquals(country, sameCountry);
    }

    @Test
    @DisplayName("null Object")
    void nullObject() {
        //arrange
        Country country = new Country("UK");
        //act
        Country sameCountry = null;
        //assert
        assertNotEquals(country, sameCountry);
    }

    @Test
    @DisplayName("Identical Object")
    void identicalObject() {
        //arrange
        Country country = new Country("UK");
        //act
        Country sameCountry = new Country("UK");
        //assert
        assertEquals(country, sameCountry);
    }

    @Test
    @DisplayName("Different Object")
    void differentObject() {
        //arrange
        Country country = new Country("UK");
        //act
        Country notSameCountry = new Country("Austria");
        //assert
        assertNotEquals(country, notSameCountry);
    }

    @Test
    @DisplayName("Different Class")
    void differentClass() {
        //arrange
        Country country = new Country("UK");
        //act
        BigDecimal bigDecimal = new BigDecimal("100");
        //assert
        assertNotEquals(country, bigDecimal);
    }

    @Test
    @DisplayName("Same HashCode")
    void sameHashCode() {
        //arrange
        int hash1;
        int hash2;
        Country country = new Country("UK");
        Country sameCountry = new Country("UK");
        //act
        hash1 = country.hashCode();
        hash2 = sameCountry.hashCode();
        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Not the same HashCode")
    void notSameHashCode() {
        //arrange
        int hash1;
        int hash2;
        Country country = new Country("UK");
        Country notSameCountry = new Country("Austria");
        //act
        hash1 = country.hashCode();
        hash2 = notSameCountry.hashCode();
        //assert
        assertNotEquals(hash1, hash2);
    }

}
