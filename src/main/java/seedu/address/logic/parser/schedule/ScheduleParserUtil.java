package seedu.address.logic.parser.schedule;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class ScheduleParserUtil extends ParserUtil {
    public static final String MESSAGE_INVALID_IS_PAID = "isPaid must be either true or false";

    /**
     * Parses a {@code String isPaid} into a {@code boolean}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code isPaid} is invalid.
     */
    public static boolean parseIsPaid(String isPaidString) throws ParseException {
        requireNonNull(isPaidString);
        String trimmedIsPaid = isPaidString.trim();
        if (trimmedIsPaid.equals("true")) {
            return true;
        } else if (trimmedIsPaid.equals("false")) {
            return false;
        } else {
            throw new ParseException(MESSAGE_INVALID_IS_PAID);
        }
    }

    /**
     * Parses a {@code String isPaid} into a {@code boolean}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code isPaid} is invalid.
     */
    public static String parseIsPaidToString(boolean isPaidBoolean) {
        requireNonNull(isPaidBoolean);
        if (isPaidBoolean) {
            return "true";
        } else {
            return "false";
        }
    }
}
