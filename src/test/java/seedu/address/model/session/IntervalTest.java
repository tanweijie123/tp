package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class IntervalTest {

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
    public void isValidInterval() {
        // invalid Intervals
        assertFalse(Interval.isValidInterval(0));
        assertFalse(Interval.isValidInterval(-1));

        // valid Intervals
        assertTrue(Interval.isValidInterval(20));
    }
}

