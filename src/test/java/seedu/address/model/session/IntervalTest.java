package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class IntervalTest {
    private static LocalDateTime validStartTime_1 = LocalDateTime.now();
    private static LocalDateTime validStartTime_2 = LocalDateTime.of(
            2017, 2, 13, 15, 56);
    private static DateTimeFormatter SIMPLE_DATE_TIME_PATTERN_FORMATTER = DateTimeFormatter.ofPattern("EE dd MMM");


    @Test
    public void constructor_nullStartTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Interval(null, 1));
    }

    @Test
    public void constructor_invalidDuration_throwsIllegalArgumentException() {
        LocalDateTime validStartTime = LocalDateTime.now();
        assertThrows(IllegalArgumentException.class, () -> new Interval(validStartTime, -1));
    }

    @Test
    public void getFormattedDateTime() {
        Interval validInterval = new Interval(validStartTime_2, 60);
        assertEquals("Mon 13 Feb", validInterval.getFormattedStartDateTime(SIMPLE_DATE_TIME_PATTERN_FORMATTER));
    }

    @Test
    public void getFormattedTime12hrPattern() {
        Interval validInterval = new Interval(validStartTime_2, 60);
        assertEquals("03:56 PM - 04:56 PM", validInterval.getTime12hrPattern());
    }

    @Test
    public void isPastMidnight() {
        Interval validInterval = new Interval(validStartTime_2, 540);
        assertTrue(validInterval.isPastMidnight());

        Interval validInterval2 = new Interval(validStartTime_2, 60);
        assertFalse(validInterval2.isPastMidnight());

        Interval validInterval3 = new Interval(validStartTime_2, 484);
        assertTrue(validInterval3.isPastMidnight());
    }

    @Test
    public void getAdjustedStartDateTime() {
        Interval validInterval = new Interval(validStartTime_2, 540);
        assertEquals("03:56 PM - Tue 14 Feb 12:56 AM", validInterval.getAdjustedStartDateTime());

        Interval validInterval2 = new Interval(validStartTime_2, 60);
        assertEquals("03:56 PM - 04:56 PM", validInterval2.getAdjustedStartDateTime());

        Interval validInterval3 = new Interval(validStartTime_2, 484);
        assertEquals("03:56 PM - Tue 14 Feb 12:00 AM", validInterval3.getAdjustedStartDateTime());
    }

    @Test
    public void isValidInterval() {
        // invalid Intervals
        assertFalse(Interval.isValidInterval(0));
        assertFalse(Interval.isValidInterval(-1));

        // valid Intervals
        assertTrue(Interval.isValidInterval(20));
    }

    @Test
    public void hashcode() {
        // same values -> returns same hashcode
        assertEquals(new Interval(validStartTime_1, 60).hashCode(),
                new Interval(validStartTime_1, 60).hashCode());

        // different startTime value -> returns different hashcode
        assertNotEquals(new Interval(validStartTime_1, 60).hashCode(),
                new Interval(validStartTime_2, 60).hashCode());

        // different duration value -> returns different hashcode
        assertNotEquals(new Interval(validStartTime_1, 60).hashCode(),
                new Interval(validStartTime_1, 40).hashCode());
    }
}

