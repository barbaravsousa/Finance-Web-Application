package switchtwentytwenty.project.datamodel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class AddressIDJPATest {

    @Test
    @DisplayName("Test all arguments constructor")
    void testAllArgumentsConstructor() {
        //arrange
        String houseNumber = "23";
        String street = "Street";
        String city = "London";
        String zipCode = "3000";
        String country = "UK";

        AddressIDJPA addressIDJPA = new AddressIDJPA(houseNumber, street, city, zipCode, country);

        //act
        String houseNumberResult = (String)ReflectionTestUtils.getField(addressIDJPA, "houseNumber");
        String streetResult = (String)ReflectionTestUtils.getField(addressIDJPA, "street");
        String cityResult = (String)ReflectionTestUtils.getField(addressIDJPA, "city");
        String zipCodeResult = (String)ReflectionTestUtils.getField(addressIDJPA, "zipCode");
        String countryResult = (String)ReflectionTestUtils.getField(addressIDJPA, "country");

        //assert
        assertEquals(houseNumberResult, houseNumber);
        assertEquals(streetResult, street);
        assertEquals(cityResult, city);
        assertEquals(zipCodeResult, zipCode);
        assertEquals(countryResult, country);
    }

    @Test
    @DisplayName("Test no arguments constructor")
    void testNoArgumentsConstructor() {
        //arrange
        AddressIDJPA addressIDJPA = new AddressIDJPA();

        //act
        Object houseNumberResult = ReflectionTestUtils.getField(addressIDJPA, "houseNumber");
        Object streetResult = ReflectionTestUtils.getField(addressIDJPA, "street");
        Object cityResult = ReflectionTestUtils.getField(addressIDJPA, "city");
        Object zipCodeResult = ReflectionTestUtils.getField(addressIDJPA, "zipCode");
        Object countryResult = ReflectionTestUtils.getField(addressIDJPA, "country");

        //assert
        assertNull(houseNumberResult);
        assertNull(streetResult);
        assertNull(cityResult);
        assertNull(zipCodeResult);
        assertNull(countryResult);
    }
}
