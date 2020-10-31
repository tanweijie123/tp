package seedu.address.logic.parser.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.schedule.PaymentStatus.MESSAGE_INVALID_PAYMENT_STATUS;
import static seedu.address.model.schedule.PaymentStatus.VALUE_PAID;
import static seedu.address.model.schedule.PaymentStatus.VALUE_UNPAID;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.PaymentStatus;
import seedu.address.model.schedule.Weight;
import seedu.address.model.util.WeightUnit;


public class ScheduleParserUtil extends ParserUtil {
    public static final Pattern VALID_WEIGHT_PATTERN = Pattern.compile("^(?<num>(\\+|-)?\\d+(\\.\\d+)?)(?<unit>.+)?$");

    /**
     * Parses a {@code String payment status} into a {@code PaymentStatus}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static PaymentStatus parsePaymentStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedPaymentStatus = status.trim().toLowerCase();
        if (trimmedPaymentStatus.equals(VALUE_PAID)) {
            return new PaymentStatus(VALUE_PAID);
        } else if (trimmedPaymentStatus.equals(VALUE_UNPAID)) {
            return new PaymentStatus(VALUE_UNPAID);
        } else {
            throw new ParseException(MESSAGE_INVALID_PAYMENT_STATUS);
        }
    }

    /**
     * Parses a {@code String} weight into a {@code Weight}
     * @throws ParseException If given weight contains non-positive numbers, or not a type Double object.
     */
    public static Weight parseWeight(String weight) throws ParseException {
        requireNonNull(weight);
        try {
            String trimmedWeight = weight.trim();
            Matcher m = VALID_WEIGHT_PATTERN.matcher(trimmedWeight);
            // Java Regex requires usage of m.group() to be within a additional block with m.find()
            if (m.find() && m.group("unit") != null) {
                // parses weight number and unit
                String parsedNum = m.group("num");
                String parsedUnit = m.group("unit");
                return new Weight(Double.valueOf(parsedNum), new WeightUnit(parsedUnit));
            }
            return new Weight(Double.valueOf(trimmedWeight));
        } catch (NumberFormatException e) {
            throw new ParseException(Weight.MESSAGE_INVALID_WEIGHT_STATUS);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
    }
}
