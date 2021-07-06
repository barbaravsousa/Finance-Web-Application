package switchtwentytwenty.project.domain.share;

import switchtwentytwenty.project.util.Util;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

public class MoneyValue {

    //Attributes
    private final BigDecimal value;
    private final Currency currency;


    //Constructor methods

    /**
     * Constructor
     *
     * @param value - value to be stored
     */
    public MoneyValue(BigDecimal value) {
        this.value = value;
        String[] defaultCurrencyCouple = Util.getSystemDefaultCurrency();
        this.currency = Currency.getInstance(new Locale(defaultCurrencyCouple[0], defaultCurrencyCouple[1]));
    }

    //Business methods

    /**
     * @param moneyValue Receive the money value to compare.
     * @return 0 : if value of this BigDecimal is equal to that of BigDecimal object passed as parameter.
     * 1 : if value of this BigDecimal is greater than that of BigDecimal object passed as parameter.
     * -1 : if value of this BigDecimal is less than that of BigDecimal object passed as parameter.
     */
    public int compare(MoneyValue moneyValue) {
        return this.value.compareTo(moneyValue.value);
    }

    /**
     * Allows to verify if value money is positive or even zero.
     *
     * @return True if positive or zero.
     */
    public boolean isPositiveOrZero() {
        BigDecimal zero = new BigDecimal(0);
        return this.value.compareTo(zero) >= 0;
    }

    /**
     * Sum a value to the value of this instance an returns a new instance with the end value.
     *
     * @param moneyValue - value to be summed
     * @return a MoneyValue instance with the sum.
     */
    public MoneyValue sum(MoneyValue moneyValue) {
        return new MoneyValue(this.value.add(moneyValue.value));
    }

    /**
     * Subtract a value from the value of this instance an returns a new instance with the end value.
     *
     * @param moneyValue - value to be subtracted
     * @return a MoneyValue instance with the subtraction.
     */
    public MoneyValue subtract(MoneyValue moneyValue) {
        return new MoneyValue(this.value.subtract(moneyValue.value));
    }

    /**
     * Return a MoneyValue instance with a negative value. If already negative it returns a new instance with the same value
     *
     * @return a MoneyValue instance with a negative value
     */
    public MoneyValue toNegative() {
        if (!this.isPositiveOrZero()) {
            throw new IllegalArgumentException("This value is already negative.");
        }
        return new MoneyValue(this.value.negate());
    }

    /**
     * MoneyValue to long.
     *
     * @return a long
     */
    public float floatValue() {
        return this.value.floatValue();
    }


    //Equal methods

    /**
     * Override equal method
     *
     * @param o Object to be compared
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneyValue that = (MoneyValue) o;
        return Objects.equals(value, that.value) && Objects.equals(currency, that.currency);
    }

    /**
     * Override hashCode method
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    /**
     * to string method
     * @return variable in string
     */
    @Override
    public String toString() {
        return value.toString();
    }

    /**
     * get money amount
     * @return - amount
     */
    public double toDouble() {
        return this.value.doubleValue();
    }
}
