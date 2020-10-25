package seedu.address.model.schedule;

import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.util.WeightUnit;


/**
 * Represents a Schedule's Weight in the address book.
 */
public class Weight {
    public static final String MESSAGE_INVALID_WEIGHT_STATUS = "Weight must be a positive number.";
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
    public Weight(double weight, WeightUnit unit) {
        checkArgument(isValidWeight(weight), MESSAGE_INVALID_WEIGHT_STATUS);
        if (!unit.isPoundUnit()) {
            this.weight = weight;
        } else {
            this.weight = WeightUnit.getPoundInKg(weight);
        }
    }

    /**
     * Constructs a Default Weight of 0.
     */
    private Weight() {
        this.weight = 0;
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
