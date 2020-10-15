package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Session's interval in FitEgo.
 * Guarantees: immutable; is valid as declared in {@link #isValidInterval(int)}
 */
public class Interval {
    private static final String DATE_TIME_PATTERN = "dd/MM/yyyy HHmm";
    private static final String SIMPLE_DATE_TIME_PATTERN = "EE dd MMM";
    private static final String TIME_12HR_PATTERN = "hh:mm a";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    public static final DateTimeFormatter SIMPLE_DATE_TIME_PATTERN_FORMATTER = DateTimeFormatter.ofPattern(
            SIMPLE_DATE_TIME_PATTERN);
    public static final DateTimeFormatter TIME_12HR_PATTERN_FORMATTER = DateTimeFormatter.ofPattern(TIME_12HR_PATTERN);

    public static final String MESSAGE_DATE_TIME_CONSTRAINTS = "Start time must follow "
            + DATE_TIME_PATTERN + " pattern";
    public static final String MESSAGE_CONSTRAINTS = "Intervals can start at any time, "
            + "but duration must be a positive integer and " + MESSAGE_DATE_TIME_CONSTRAINTS;

    private final LocalDateTime start;
    private final int durationInMinutes;

    /**
     * Constructs an {@code Gym}.
     *
     * @param start    A valid start datetime.
     * @param duration A valid duration.
     */
    public Interval(LocalDateTime start, int duration) {
        requireNonNull(start);
        checkArgument(isValidInterval(duration), MESSAGE_CONSTRAINTS);
        this.start = start;
        this.durationInMinutes = duration;
    }

    /**
     * Returns true if a given string is a valid gym.
     */
    public static boolean isValidInterval(int duration) {
        return duration > 0;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public String getFormattedStartDateTime(DateTimeFormatter formatter) {
        return this.start.format(formatter);
    }

    public LocalDateTime getEnd() {
        return this.start.plusMinutes(durationInMinutes);
    }

    public int getDurationInMinutes() {
        return this.durationInMinutes;
    }

    @Override
    public String toString() {
        return getStart().format(DATE_TIME_FORMATTER)
                + " - "
                + getEnd().format(DATE_TIME_FORMATTER);
    }

    public String getTime12hrPattern() {
        return getStart().format(TIME_12HR_PATTERN_FORMATTER)
                + " - "
                + getEnd().format(TIME_12HR_PATTERN_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Interval // instanceof handles nulls
                && start.equals(((Interval) other).start)
                && durationInMinutes == ((Interval) other).durationInMinutes); // state check
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
