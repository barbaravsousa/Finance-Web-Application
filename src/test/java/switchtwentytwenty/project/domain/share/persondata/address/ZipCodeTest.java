package switchtwentytwenty.project.domain.share.persondata.address;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ZipCodeTest {

    @Test
    @DisplayName("Same Object")
    void sameObject() {
        //arrange
        ZipCode zip = new ZipCode("4111");
        //act
        ZipCode sameZip = zip;
        //assert
        assertEquals(zip, sameZip);
    }

    @Test
    @DisplayName("Null Object")
    void nullObject() {
        //arrange
        ZipCode zip = new ZipCode("4111");
        //act
        ZipCode sameZip = null;
        //assert
        assertNotEquals(zip, sameZip);
    }

    @Test
    @DisplayName("Identical Object")
    void identicalObject() {
        //arrange
        ZipCode zip = new ZipCode("4111");
        //act
        ZipCode sameZip = new ZipCode("4111");
        //assert
        assertEquals(zip, sameZip);
    }

    @Test
    @DisplayName("Different Object")
    void differentObject() {
        //arrange
        ZipCode zip = new ZipCode("4111");
        //act
        ZipCode sameZip = new ZipCode("2100");
        //assert
        assertNotEquals(zip, sameZip);
    }

    @Test
    @DisplayName("Different Class")
    void differentClass() {
        //arrange
        ZipCode zip = new ZipCode("4111");
        //act
        BigDecimal bigDecimal = new BigDecimal("4111");
        //assert
        assertNotEquals(zip, bigDecimal);
    }

    @Test
    @DisplayName("Same HashCode")
    void sameHashCode() {
        //arrange
        int hash1;
        int hash2;
        ZipCode zip = new ZipCode("4111");
        ZipCode sameZip = new ZipCode("4111");
        //act
        hash1 = zip.hashCode();
        hash2 = sameZip.hashCode();
        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Not the same HashCode")
    void notSameHashCode() {
        //arrange
        int hash1;
        int hash2;
        ZipCode zip = new ZipCode("4111");
        ZipCode sameZip = new ZipCode("2100");
        //act
        hash1 = zip.hashCode();
        hash2 = sameZip.hashCode();
        //assert
        assertNotEquals(hash1, hash2);
    }
}
