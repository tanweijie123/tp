package seedu.address.model.schedule;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Schedule's Weight in the address book.
 */
public class Weight {
    public static final String MESSAGE_INVALID_WEIGHT_STATUS = "Weight must be a positive number.";
    public static final String MESSAGE_INVALID_UNIT_STATUS = "Weight must be in kg or lbs.";
    public static final String KILOGRAM = "kg";
    public static final String POUND = "lb";
    private static final double KILOGRAM_TO_POUND_MULTIPLIER = 2.20462262185;
    private final double weight;

    /**
     * Constructs a {@code Weight}.
     *
     * @param weight A valid weight for schedule.
     */
    public Weight(double weight) {
        checkArgument(isValidWeight(weight), MESSAGE_INVALID_WEIGHT_STATUS);
        this.weight = weight;
    }

    /**
     * Constructs a {@code Weight}.
     *
     * @param weight A valid weight for schedule.
     */
    public Weight(double weight, String unit) {
        checkArgument(isValidWeight(weight), MESSAGE_INVALID_WEIGHT_STATUS);
        checkArgument(isValidUnit(unit), MESSAGE_INVALID_UNIT_STATUS);
        if (unit.equals(POUND)) {
            this.weight = weight;
        } else {
            this.weight = weight / KILOGRAM_TO_POUND_MULTIPLIER;
        }
    }

    /**
     * Constructs a Default Weight of 0.
     */
    private Weight() {
        this.weight = 0;
    }

    /**
     * Returns true if a given String value is a valid unit.
     */
    public static boolean isValidUnit(String unit) {
        return unit.equals(KILOGRAM) | unit.equals(POUND);
    }

    /**
     * Returns true if a given String value represents pound unit.
     */
    public static boolean isPoundUnit(String unit) {
        return unit.equals(POUND);
    }

    /**
     * Returns true if a given double value is a valid weight double.
     */
    public static boolean isValidWeight(double weight) {
        return weight > 0;
    }

    /**
     * Returns true if a given Weight object contains a valid weight.
     * @param weight Weight object to check if the value is valid weight.
     * @throws NullPointerException throws NullPointerException if either of the given input is null.
     */
    public static boolean isValidWeight(Weight weight) {
        return isValidWeight(weight.getWeight());
    }

    public static Weight getDefaultWeight() {
        return new Weight();
    }

    public double getWeight() {
        return this.weight;
    }

    public double getWeightInPound() {
        return this.weight * KILOGRAM_TO_POUND_MULTIPLIER;
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
