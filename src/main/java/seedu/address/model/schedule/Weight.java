package seedu.address.model.schedule;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Schedule's Weight in the address book.
 */
public class Weight {
    public static final String MESSAGE_INVALID_PAYMENT_STATUS = "Weight must be a positive number.";
    public final double weight;

    /**
     * Constructs a {@code Weight}.
     *
     * @param weight A valid weight for schedule.
     */
    public Weight(double weight) {
        checkArgument(isValidWeight(weight), MESSAGE_INVALID_PAYMENT_STATUS);
        this.weight = weight;
    }

    /**
     * Constructs a Default Weight of 0.
     */
    private Weight() {
        this.weight = 0;
    }

    /**
     * Returns true if a given double value is a valid weight.
     */
    public static boolean isValidWeight(double weight) {
        return weight > 0;
    }

    /**
     * Returns true if a given Weight object contains a valid weight.
     * @param weight Weight object to check if the value is valid weight.
     * @throws NullPointerException throws NullPointerException if given input is null.
     */
    public static boolean isValidWeight(Weight weight) {
        return isValidWeight(weight.weight);
    }

    public static Weight getDefaultWeight() {
        return new Weight();
    }

    @Override
    public String toString() {
        return Double.toString(weight);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Weight // instanceof handles nulls
                && weight == ((Weight) other).weight); // state check
    }

    @Override
    public int hashCode() {
        return Double.valueOf(weight).hashCode();
    }
}
