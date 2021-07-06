package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CashAccountBalanceOutDTOTest {

    @Test
    void createCashAccountBalanceDTO() {
        double balance = 10;
        CashAccountBalanceOutDTO dto = new CashAccountBalanceOutDTO(balance);
        assertNotNull(dto);
    }


    @Test
    @DisplayName("Get balance - success case")
    void getBalance() {
        double balance = 10;
        CashAccountBalanceOutDTO dto = new CashAccountBalanceOutDTO(balance);

        double expect = 10;
        double result = dto.getBalance();
        assertEquals(result, expect);
    }


    @Test
    @DisplayName("Get balance - success case")
    void getBalanceUncessfullCase() {
        double balance = 12;
        CashAccountBalanceOutDTO dto = new CashAccountBalanceOutDTO(balance);

        double expect = 10;
        double result = dto.getBalance();
        assertNotEquals(result, expect);
    }


    @Test
    @DisplayName("Test equals  - success case")
    void testEquals() {
        //assert
        double balance = 10;
        CashAccountBalanceOutDTO dto = new CashAccountBalanceOutDTO(balance);

        double secondBalance = 10;
        CashAccountBalanceOutDTO secondDto = new CashAccountBalanceOutDTO(balance);

        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertTrue(result);

    }

    @Test
    @DisplayName("Test equals  - success case")
    void testEqualsWithTheSameDTO() {
        //assert
        double balance = 10;
        CashAccountBalanceOutDTO dto = new CashAccountBalanceOutDTO(balance);

        //act
        boolean result = dto.equals(dto);
        //assert
        assertTrue(result);

    }


    @Test
    @DisplayName("Test equals for null case  - unsuccess case")
    void testEqualsNullCase() {
        //assert
        double balance = 10;
        CashAccountBalanceOutDTO dto = new CashAccountBalanceOutDTO(balance);

        //act
        boolean result = dto.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals for diferent dtos case  - unsuccess case")
    void testTwoDiferentDtos() {
        //assert
        double balance = 10;
        CashAccountBalanceOutDTO dto = new CashAccountBalanceOutDTO(balance);

        double secondBalance = 12;
        CashAccountBalanceOutDTO secondDto = new CashAccountBalanceOutDTO(secondBalance);

        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }


    @Test
    @DisplayName("Test equals for diferent dtos case  - unsuccess case")
    void testTwoDiferentObjectsDtos() {
        //assert
        double balance = 10;
        CashAccountBalanceOutDTO dto = new CashAccountBalanceOutDTO(balance);

        BigDecimal bigDecimal = new BigDecimal(10);

        //act
        boolean result = dto.equals(bigDecimal);
        //assert
        assertFalse(result);
    }






    @Test
    @DisplayName("Test same hash code case  - unsuccess case")
    void testSameHashCode() {
        //assert
        double balance = 10;
        CashAccountBalanceOutDTO dto = new CashAccountBalanceOutDTO(balance);

        double secondBalance = 10;
        CashAccountBalanceOutDTO secondDto = new CashAccountBalanceOutDTO(secondBalance);

        //act
        int firstHashCode = dto.hashCode();
        int secondHashCode = secondDto.hashCode();

        //assert
        assertEquals(firstHashCode,secondHashCode);
    }

    @Test
    @DisplayName("Test same hash code case  - unsuccess case")
    void testDiferentHashCode() {
        //assert
        double balance = 10;
        CashAccountBalanceOutDTO dto = new CashAccountBalanceOutDTO(balance);

        double secondBalance = 12;
        CashAccountBalanceOutDTO secondDto = new CashAccountBalanceOutDTO(secondBalance);

        //act
        int firstHashCode = dto.hashCode();
        int secondHashCode = secondDto.hashCode();

        //assert
        assertNotEquals(firstHashCode,secondHashCode);
    }





}