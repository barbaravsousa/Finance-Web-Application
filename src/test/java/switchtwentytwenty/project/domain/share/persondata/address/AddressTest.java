package switchtwentytwenty.project.domain.share.persondata.address;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumber;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void creatingValidAddress() {
        Address address = new Address("Rua da Estação", "15", "4585-709", "Paredes", "Portugal");
        Assertions.assertNotNull(address);

    }

    @Test
    @DisplayName("Creating a valid address with capitalizes first letters of each attribute")
    public void verifyAddressWithCapitalizedFirstLetters() {
        Address address = new Address(
                "RuA dE SanTA cAtArinA",
                "2º esq fRT",
                "4445-620",
                "PorTO",
                "portUGAL");

        String result = address.toString();
        String expected = "Street:Rua De Santa Catarina, House Number:2º Esq Frt, ZipCode:4445-620, City:Porto, Country:Portugal";

        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Failure create Address: Invalid Street (empty)")
    public void creatingAddressWithInvalidStreet1() {
        assertThrows(IllegalArgumentException.class, () -> {
            Address invalidAddress = new Address("", "15", "4585-709", "Paredes", "Portugal");
        });
    }

    @Test
    @DisplayName("Failure create Address: Invalid Street (blank)")
    public void creatingAddressWithInvalidStreet2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Address invalidAddress = new Address("  ", "15", "4585-709", "Paredes", "Portugal");
        });
    }

    @Test
    @DisplayName("Failure create Address: Invalid Street (null)")
    public void creatingAddressWithInvalidStreet3() {
        assertThrows(NullPointerException.class, () -> {
            Address invalidAddress = new Address(null, "15", "4585-709", "Paredes", "Portugal");
        });
    }

    @Test
    @DisplayName("Failure create Address: Invalid House Number (empty)")
    public void creatingAddressWithInvalidHouseNumber1() {
        assertThrows(IllegalArgumentException.class, () -> {
            Address invalidAddress = new Address("Rua da Estação", "", "4585-709", "Paredes", "Portugal");
        });
    }

    @Test
    @DisplayName("Failure create Address: Invalid House Number (blank)")
    public void creatingAddressWithInvalidHouseNumber2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Address invalidAddress = new Address("Rua da Estação", "   ", "4585-709", "Paredes", "Portugal");
        });
    }

    @Test
    @DisplayName("Failure create Address: Invalid House Number (null)")
    public void creatingAddressWithInvalidHouseNumber3() {
        assertThrows(NullPointerException.class, () -> {
            Address invalidAddress = new Address("Rua da Estação", null, "4585-709", "Paredes", "Portugal");
        });
    }

    @Test
    @DisplayName("Failure create Address: Invalid ZipCode (empty)")
    public void creatingAddressWithInvalidZipCode1() {
        assertThrows(IllegalArgumentException.class, () -> {
            Address invalidAddress = new Address("Rua da Estação", "15", "", "Paredes", "Portugal");
        });
    }

    @Test
    @DisplayName("Failure create Address: Invalid ZipCode (blank)")
    public void creatingAddressWithInvalidZipCode2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Address invalidAddress = new Address("Rua da Estação", "15", "  ", "Paredes", "Portugal");
        });
    }

    @Test
    @DisplayName("Failure create Address: Invalid ZipCode (null)")
    public void creatingAddressWithInvalidZipCode3() {
        assertThrows(NullPointerException.class, () -> {
            Address invalidAddress = new Address("Rua da Estação", "15", null, "Paredes", "Portugal");
        });
    }

    @Test
    @DisplayName("Failure create Address: Invalid ZipCode (format)")
    public void creatingAddressWithInvalidZipCode4() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Address invalidAddress = new Address("Rua da Estação", "15", "4A789-2598", "Paredes", "Portugal");
        });

        String exceptionMessage = exception.getMessage();
        String expectedMessage = "Zip code is not in the correct format";
        assertEquals(exceptionMessage, expectedMessage);


    }

    @Test
    @DisplayName("Failure create Address: Invalid City (empty)")
    public void creatingAddressWithInvalidCity1() {
        assertThrows(IllegalArgumentException.class, () -> {
            Address invalidAddress = new Address("Rua da Estação", "15", "4789-2598", "", "Portugal");
        });
    }

    @Test
    @DisplayName("Failure create Address: Invalid City (blank)")
    public void creatingAddressWithInvalidCity2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Address invalidAddress = new Address("Rua da Estação", "15", "4789-2598", "  ", "Portugal");
        });
    }

    @Test
    @DisplayName("Failure create Address: Invalid City (null)")
    public void creatingAddressWithInvalidCity3() {
        assertThrows(NullPointerException.class, () -> {
            Address invalidAddress = new Address("Rua da Estação", "15", "4789-2598", null, "Portugal");
        });
    }

    @Test
    @DisplayName("Failure create Address: Invalid Country (empty)")
    public void creatingAddressWithInvalidCountry1() {
        assertThrows(IllegalArgumentException.class, () -> {
            Address invalidAddress = new Address("Rua da Estação", "15", "4789-2598", "Paredes", "");
        });
    }

    @Test
    @DisplayName("Failure create Address: Invalid Country (blank)")
    public void creatingAddressWithInvalidCountry2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Address invalidAddress = new Address("Rua da Estação", "15", "4789-2598", "Paredes", "   ");
        });
    }

    @Test
    @DisplayName("Failure create Address: Invalid Country (null)")
    public void creatingAddressWithInvalidCountry3() {
        assertThrows(NullPointerException.class, () -> {
            Address invalidAddress = new Address("Rua da Estação", "15", "4789-2598", "Paredes", null);
        });
    }

    @Test
    @DisplayName("Not equal objects")
    public void removingDoubleSpace() {

        //arrange
        Address address = new Address(
                "RuA dE SanTA   cAtArinA",
                "2º esq fRT",
                "4445-620",
                "PorTO",
                "portUGAL");
        //act
        String result = address.toString();
        String expected = "Street:Rua De Santa Catarina, House Number:2º Esq Frt, ZipCode:4445-620, City:Porto, Country:Portugal";
        //assert
        assertEquals(result, expected);

    }

    @Test
    @DisplayName("Not equal objects")
    public void relationTypeNotEqualToFamily() {
        //arrange
        Address address = new Address(
                "Rua de Santa Catarina",
                "2º esq frt",
                "4445-620",
                "Porto",
                "Portugal");

        TelephoneNumber validPhoneNumber = new TelephoneNumber("227755744");
        boolean result;
        //act
        result = address.equals(validPhoneNumber);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same instance")
    public void sameInstance() {
        //arrange
        Address address = new Address(
                "Rua de Santa Catarina",
                "2º esq frt",
                "4445-620",
                "Porto",
                "Portugal");
        Address sameAddress = address;
        boolean result;
        //act
        result = address.equals(sameAddress);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Same Address")
    public void sameAddress() {
        //arrange
        Address address1 = new Address(
                "Rua de Santa Catarina",
                "2º esq frt",
                "4445-620",
                "Porto",
                "Portugal");
        Address address2 = new Address(
                "RUA de SANTA Catarina",
                "2º esq FRT",
                "4445-620",
                "PORTO",
                "Portugal");
        boolean result;
        //act
        result = address1.equals(address2);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Not same Address")
    public void notSameAddress() {
        //arrange
        Address address1 = new Address(
                "Rua de Santa Catarina",
                "2º esq frt",
                "4445-620",
                "Porto",
                "Portugal");
        Address address2 = new Address(
                "Rua de Cedofeita",
                "R/C",
                "4440-620",
                "Porto",
                "Portugal");
        boolean result;
        //act
        result = address1.equals(address2);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Both objects have the same value")
    void bothObjectsHaveTheSameValue() {
        //arrange
        boolean result;
        Address address1 = new Address(
                "Rua de Santa Catarina",
                "2º esq frt",
                "4445-620",
                "Porto",
                "Portugal");
        Address addres2 = new Address(
                "Rua de Santa Catarina",
                "2º esq frt",
                "4445-620",
                "Porto",
                "Portugal");
        //act
        result = address1.equals(addres2);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("The objects dont have the same value")
    void objectWithDifferentValues() {
        //arrange
        boolean result;
        Address address1 = new Address(
                "Rua de Santa Catarina",
                "2º esq frt",
                "4445-620",
                "Porto",
                "Portugal");
        Address address2 = new Address(
                "Rua dos Dragões",
                "2º esq frt",
                "4445-620",
                "Porto",
                "Portugal");
        //act
        result = address1.equals(address2);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Get House number")
    void getHouseNumber() {
        //arrange
        String houseNumber = "2º Esq Frt";
        String result;
        Address address = new Address(
                "Rua de Santa Catarina",
                houseNumber,
                "4445-620",
                "Porto",
                "Portugal");

        //act
        result = address.getHouseNumber().toString();

        //assert
        assertEquals(result, houseNumber);
    }

    @Test
    @DisplayName("Get street")
    void getStreet() {
        //arrange
        String street = "Rua De Santa Catarina";
        String result;
        Address address = new Address(
                street,
                "2º esq frt",
                "4445-620",
                "Porto",
                "Portugal");

        //act
        result = address.getStreet().toString();

        //assert
        assertEquals(result, street);
    }

    @Test
    @DisplayName("Get city")
    void getCity() {
        //arrange
        String city = "Porto";
        String result;
        Address address = new Address(
                "Rua de Santa Catarina",
                "2º esq frt",
                "4445-620",
                city,
                "Portugal");

        //act
        result = address.getCity().toString();

        //assert
        assertEquals(result, city);
    }

    @Test
    @DisplayName("Get zip code")
    void getZipCode() {
        //arrange
        String zipCode = "4445-620";
        String result;
        Address address = new Address(
                "Rua de Santa Catarina",
                "2º esq frt",
                zipCode,
                "Porto",
                "Portugal");

        //act
        result = address.getZipCode().toString();

        //assert
        assertEquals(result, zipCode);
    }

    @Test
    @DisplayName("Get country")
    void getCountry() {
        //arrange
        String country = "Portugal";
        String result;
        Address address = new Address(
                "Rua de Santa Catarina",
                "2º esq frt",
                "4445-620",
                "Porto",
                country);

        //act
        result = address.getCountry().toString();

        //assert
        assertEquals(result, country);
    }

    @Test
    @DisplayName("Same hash code")
    void sameHashCode() {
        //arrange
        int hash1;
        int hash2;
        Address address1 = new Address(
                "Rua De Santa Catarina",
                "2º Esq frt",
                "4445-620",
                "Porto",
                "Portugal");

        Address address2 = new Address(
                "Rua De Santa Catarina",
                "2º Esq frt",
                "4445-620",
                "Porto",
                "Portugal");
        //act
        hash1 = address1.hashCode();
        hash2 = address2.hashCode();

        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Not same hash code")
    void notSameHashCode() {
        //arrange
        int hash1;
        int hash2;
        Address address1 = new Address(
                "Rua De Santa Catarina",
                "2º Esq frt",
                "4445-620",
                "Porto",
                "Portugal");

        Address address2 = new Address(
                "Rua De Santa Catarina",
                "2º Esq frt",
                "4445-620",
                "Porto",
                "Lisboa");
        //act
        hash1 = address1.hashCode();
        hash2 = address2.hashCode();

        //assert
        assertNotEquals(hash1, hash2);
    }
}