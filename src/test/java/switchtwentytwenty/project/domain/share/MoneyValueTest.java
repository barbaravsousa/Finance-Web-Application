package switchtwentytwenty.project.domain.share;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class MoneyValueTest {

    @Test
    @DisplayName("Compare one instance with a smaller one")
    public void compareTwoInstancesLess() {
        //arrange
        MoneyValue value = new MoneyValue(new BigDecimal(0));
        MoneyValue valueLess = new MoneyValue(new BigDecimal(-10));
        int expectedLess = -1;
        int resultLess;
        //act
        resultLess = valueLess.compare(value);
        //assert
        assertEquals(resultLess, expectedLess);
    }

    @Test
    @DisplayName("Compare one instance with a equal one")
    public void compareTwoInstancesEqual() {
        //arrange
        MoneyValue value = new MoneyValue(new BigDecimal(0));
        MoneyValue valueEqual = new MoneyValue(new BigDecimal(0));
        int expectedEqual = 0;
        int resultEqual;
        //act
        resultEqual = valueEqual.compare(value);
        //assert
        assertEquals(resultEqual, expectedEqual);
    }

    @Test
    @DisplayName("Compare one instance with a greater one")
    public void compareTwoInstancesGreater() {
        //arrange
        MoneyValue value = new MoneyValue(new BigDecimal(0));
        MoneyValue valueGreater = new MoneyValue(new BigDecimal(10));
        int expectedGreater = 1;
        int resultGreater;
        //act
        resultGreater = valueGreater.compare(value);
        //assert
        assertEquals(resultGreater, expectedGreater);
    }

    @Test
    @DisplayName("Is negative")
    public void isNegative() {
        //arrange
        MoneyValue value = new MoneyValue(new BigDecimal(-10));
        boolean result;
        //act
        result = value.isPositiveOrZero();
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Is zero")
    public void isZero() {
        //arrange
        MoneyValue value = new MoneyValue(new BigDecimal(0));
        boolean result;
        //act
        result = value.isPositiveOrZero();
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Is positive")
    public void isPositive() {
        //arrange
        MoneyValue value = new MoneyValue(new BigDecimal(10));
        boolean result;
        //act
        result = value.isPositiveOrZero();
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("To double")
    public void toDouble() {
        //arrange
        BigDecimal bigDecimal = new BigDecimal(10);
        MoneyValue value = new MoneyValue(bigDecimal);
        double expected = 10;
        double result;
        //act
        result = value.toDouble();
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Sum between two MoneyValue")
    public void sumBetweenTwoMoneyValue() {
        //arrange
        MoneyValue value1 = new MoneyValue(new BigDecimal(10));
        MoneyValue value2 = new MoneyValue(new BigDecimal(15));
        MoneyValue result, expected = new MoneyValue(new BigDecimal(25));
        //act
        result = value1.sum(value2);
        //assert
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("subtraction between two MoneyValue")
    public void subtractionBetweenTwoMoneyValue() {
        //arrange
        MoneyValue value1 = new MoneyValue(new BigDecimal(10));
        MoneyValue value2 = new MoneyValue(new BigDecimal(15));
        MoneyValue result, expected = new MoneyValue(new BigDecimal(-5));
        //act
        result = value1.subtract(value2);
        //assert
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Negate a negative")
    public void negateANegative() {
        //arrange
        MoneyValue value = new MoneyValue(new BigDecimal(-10));
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            value.toNegative();
        });
    }

    @Test
    @DisplayName("Negate a positive")
    public void negateAPositive() {
        //arrange
        MoneyValue value = new MoneyValue(new BigDecimal(10));
        MoneyValue expected = new MoneyValue(new BigDecimal(-10));
        MoneyValue result;
        //act
        result = value.toNegative();
        //assert
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Negate a zero")
    public void negateAZero() {
        //arrange
        MoneyValue value = new MoneyValue(new BigDecimal(0));
        MoneyValue expected = new MoneyValue(new BigDecimal(0));
        MoneyValue result;
        //act
        result = value.toNegative();
        //assert
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Same value")
    public void moneyValueSameValue() {
        //arrange
        MoneyValue value1 = new MoneyValue(new BigDecimal(0));
        MoneyValue value2 = new MoneyValue(new BigDecimal(0));
        //act and assert
        assertEquals(value1, value2);
    }

    @Test
    @DisplayName("Not same value")
    public void moneyValueNotSameValue() {
        //arrange
        MoneyValue value1 = new MoneyValue(new BigDecimal(0));
        MoneyValue value2 = new MoneyValue(new BigDecimal(40));
        //act and assert
        assertNotEquals(value1, value2);
    }

    @Test
    @DisplayName("Not equal objects")
    public void moneyValueNotEqualToFamily() {
        //arrange
        MoneyValue value = new MoneyValue(new BigDecimal(0));
        BigDecimal bigDecimal = new BigDecimal("3");
        boolean result;
        //act
        result = value.equals(bigDecimal);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same instance")
    public void sameInstance() {
        //arrange
        MoneyValue value = new MoneyValue(new BigDecimal(0));
        MoneyValue sameValue = value;
        boolean result;
        //act
        result = value.equals(sameValue);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("hashCode test")
    public void testEqualsSymmetric() {
        //arrange
        MoneyValue value1 = new MoneyValue(new BigDecimal(0));
        MoneyValue value2 = new MoneyValue(new BigDecimal(0));
        MoneyValue value3 = new MoneyValue(new BigDecimal(10));
        //assert
        assertTrue(value1.equals(value2) && value2.equals(value1));
        assertEquals(value2.hashCode(), value1.hashCode());
        assertNotEquals(value1.hashCode(), value3.hashCode());
    }

    @Test
    @DisplayName("Return a float with many zeros")
    void returnAFloatWithManyZeros() {
        //arrange
        float amount = 100.5000f;
        float result;
        MoneyValue value = new MoneyValue(new BigDecimal(amount));
        //act
        result = value.floatValue();
        //assert
        assertEquals(result, amount);
    }

    @Test
    @DisplayName("Return a float with no decimals")
    void returnAFloatWithoutDecimals() {
        //arrange
        float amount = 101f;
        float result;
        MoneyValue value = new MoneyValue(new BigDecimal(amount));
        //act
        result = value.floatValue();
        //assert
        assertEquals(result, amount);
    }

    @Test
    @DisplayName("Return a zero")
    void returnAFloatZero() {
        //arrange
        float amount = 0f;
        float result;
        MoneyValue value = new MoneyValue(new BigDecimal(amount));
        //act
        result = value.floatValue();
        //assert
        assertEquals(result, amount);
    }

    @Test
    @DisplayName("Return a float with starting zero")
    void returnAFloatWithStartingZero() {
        //arrange
        float amount = 00001f;
        float result;
        float expected = 1;
        MoneyValue value = new MoneyValue(new BigDecimal(amount));
        //act
        result = value.floatValue();
        //assert
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Same money value")
    void testEquals() {
        //arrange
        MoneyValue amount1 = new MoneyValue(new BigDecimal(12.0));
        MoneyValue amount2 = new MoneyValue(new BigDecimal(12));

        //act
        boolean result = amount1.equals(amount2);

        //assert
        assertTrue(result);
    }
}
