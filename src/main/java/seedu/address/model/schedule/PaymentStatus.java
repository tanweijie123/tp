package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class PaymentStatus {
    public static final String VALUE_PAID = "paid";
    public static final String VALUE_UNPAID = "unpaid";
    public static final PaymentStatus PAYMENT_STATUS_UNPAID = new PaymentStatus(VALUE_UNPAID);
    public static final String MESSAGE_INVALID_PAYMENT_STATUS = "Payment Status must be either paid or unpaid";


    public final String value;

    /**
     * Constructs a {@code PaymentStatus}.
     *
     * @param status Whether paid or not paid.
     */
    public PaymentStatus(String status) {
        requireNonNull(status);
        checkArgument(isValidPaymentStatus(status), MESSAGE_INVALID_PAYMENT_STATUS);
        assert status.equals(VALUE_PAID) || status.equals(VALUE_UNPAID);
        this.value = status;
    }

    /**
     * Returns true if paid, or false if unpaid
     */
    public boolean isPaid() {
        assert isValidPaymentStatus(value);
        return value.equals(VALUE_PAID) ? true : false;
    }

    /**
     * Returns true if a given string is a valid payment status.
     */
    public static boolean isValidPaymentStatus(String test) {
        return test.equals(VALUE_PAID) || test.equals(VALUE_UNPAID);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PaymentStatus // instanceof handles nulls
                && value.equals(((PaymentStatus) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
