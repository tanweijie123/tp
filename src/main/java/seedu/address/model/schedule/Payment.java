package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;

public class Payment {
    public static final String PAID_STATUS = "STATUS:";
    public static final String IS_PAID_STATUS = "PAID";
    public static final String IS_NOT_PAID_STATUS = "NOT PAID";
    public static final Payment UNPAID_PAYMENT = new Payment(false);

    public final boolean isPaid;

    /**
     * Constructs a {@code Payment}.
     *
     * @param isPaid Whether paid or not paid.
     */
    public Payment(boolean isPaid) {
        requireNonNull(isPaid);
        this.isPaid = isPaid;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    @Override
    public String toString() {
        return PAID_STATUS + " " + (isPaid ? IS_PAID_STATUS : IS_NOT_PAID_STATUS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && isPaid == ((Payment) other).isPaid); // state check
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(isPaid);
    }
}
