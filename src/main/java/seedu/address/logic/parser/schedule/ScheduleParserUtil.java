package seedu.address.logic.parser.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.schedule.PaymentStatus.MESSAGE_INVALID_PAYMENT_STATUS;
import static seedu.address.model.schedule.PaymentStatus.VALUE_PAID;
import static seedu.address.model.schedule.PaymentStatus.VALUE_UNPAID;
import static seedu.address.model.schedule.Weight.MESSAGE_INVALID_WEIGHT_STATUS;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.PaymentStatus;
import seedu.address.model.schedule.Weight;

public class ScheduleParserUtil extends ParserUtil {
    /**
     * Parses a {@code String payment status} into a {@code PaymentStatus}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static PaymentStatus parsePaymentStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedPaymentStatus = status.trim();
        if (trimmedPaymentStatus.equals(VALUE_PAID)) {
            return new PaymentStatus(VALUE_PAID);
        } else if (trimmedPaymentStatus.equals(VALUE_UNPAID)) {
            return new PaymentStatus(VALUE_UNPAID);
        } else {
            throw new ParseException(MESSAGE_INVALID_PAYMENT_STATUS);
        }
    }

    public static Weight parseWeight(String weight) throws ParseException {
        requireNonNull(weight);
        try {
            return new Weight(Double.valueOf(weight));
        } catch (IllegalArgumentException e) {
            throw new ParseException(MESSAGE_INVALID_WEIGHT_STATUS);
        }
    }
}
