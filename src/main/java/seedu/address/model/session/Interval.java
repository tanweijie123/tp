package seedu.address.model.session;

import static java.time.temporal.ChronoUnit.DAYS;
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

    public static final String MESSAGE_DATE_TIME_CONSTRAINTS = "Start time must be a valid datetime and follow "
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

    public LocalDateTime getEnd() {
        return this.start.plusMinutes(durationInMinutes);
    }

    public String getFormattedStartDateTime(DateTimeFormatter formatter) {
        return this.getStart().format(formatter);
    }

    public String getFormattedEndDateTime(DateTimeFormatter formatter) {
        return this.getEnd().format(formatter);
    }

    /**
     * Returns a String representation of start and end time depending on whether they are on different days.
     * If same day, the String representation will specify the time of start and end.
     * Else, also specify the date of the end time.
     *
     * @return String representation of start and end time specifying end date if interval is over >1 days.
     */
    public String getAdjustedStartDateTime() {
        if (this.isPastMidnight()) {
            return getFormattedStartDateTime(TIME_12HR_PATTERN_FORMATTER) + " - "
                    + getFormattedEndDateTime(SIMPLE_DATE_TIME_PATTERN_FORMATTER) + " "
                    + getFormattedEndDateTime(TIME_12HR_PATTERN_FORMATTER);
        } else {
            return getFormattedStartDateTime(TIME_12HR_PATTERN_FORMATTER) + " - "
                    + getFormattedEndDateTime(TIME_12HR_PATTERN_FORMATTER);
        }
    }

    /**
     * Returns true if the start and end time are on different days.
     * @return Boolean value of whether start and end time are on different days
     */
    public boolean isPastMidnight() {
        assert(this.getStart() != null && this.getEnd() != null);
        return this.getEnd().truncatedTo(DAYS).isAfter(this.getStart().truncatedTo(DAYS));
    }

    @Override
    public String toString() {
        return getFormattedStartDateTime(DATE_TIME_FORMATTER)
                + " - "
                + getFormattedEndDateTime(DATE_TIME_FORMATTER);
    }

    public String getTime12hrPattern() {
        return getFormattedStartDateTime(TIME_12HR_PATTERN_FORMATTER)
                + " - "
                + getFormattedEndDateTime(TIME_12HR_PATTERN_FORMATTER);
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
