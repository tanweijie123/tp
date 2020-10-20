package seedu.address.logic.parser.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.schedule.PaymentStatus.STATUS_PAID;
import static seedu.address.model.schedule.PaymentStatus.STATUS_UNPAID;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.PaymentStatus;

public class ScheduleParserUtil extends ParserUtil {
    public static final String MESSAGE_INVALID_PAYMENT_STATUS = "Payment Status must be either paid or unpaid";

    /**
     * Parses a {@code String isPaid} into a {@code PaymentStatus}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code isPaid} is invalid.
     */
    public static PaymentStatus parsePaymentStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedIsPaid = status.trim();
        if (trimmedIsPaid.equals(STATUS_PAID)) {
            return new PaymentStatus(STATUS_PAID);
        } else if (trimmedIsPaid.equals(STATUS_UNPAID)) {
            return new PaymentStatus(STATUS_UNPAID);
        } else {
            throw new ParseException(MESSAGE_INVALID_PAYMENT_STATUS);
        }
    }
}
