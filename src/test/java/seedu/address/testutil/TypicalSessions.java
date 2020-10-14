package seedu.address.testutil;

import static seedu.address.logic.commands.session.SessionCommandTestUtil.VALID_DURATION_GETWELL;
import static seedu.address.logic.commands.session.SessionCommandTestUtil.VALID_DURATION_MACHOMAN;
import static seedu.address.logic.commands.session.SessionCommandTestUtil.VALID_EXERCISE_TYPE_GETWELL;
import static seedu.address.logic.commands.session.SessionCommandTestUtil.VALID_EXERCISE_TYPE_MACHOMAN;
import static seedu.address.logic.commands.session.SessionCommandTestUtil.VALID_GYM_GETWELL;
import static seedu.address.logic.commands.session.SessionCommandTestUtil.VALID_GYM_MACHOMAN;
import static seedu.address.logic.commands.session.SessionCommandTestUtil.VALID_START_TIME_GETWELL;
import static seedu.address.logic.commands.session.SessionCommandTestUtil.VALID_START_TIME_MACHOMAN;
import static seedu.address.logic.commands.session.SessionCommandTestUtil.VALID_START_TIME_MACHOMAN_TOMORROW;
import static seedu.address.testutil.TypicalSchedules.getTypicalSchedules;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.session.Interval;
import seedu.address.model.session.Session;

/**
 * A utility class containing a list of {@code Session} objects to be used in tests.
 */
public class TypicalSessions {

    // Manually added - Session's details found in {@code SessionCommandTestUtil}
    public static final Session GETWELL = new SessionBuilder()
            .withGym(VALID_GYM_GETWELL)
            .withExerciseType(VALID_EXERCISE_TYPE_GETWELL)
            .withInterval(LocalDateTime.parse(VALID_START_TIME_GETWELL, Interval.DATE_TIME_FORMATTER),
                    Integer.parseInt(VALID_DURATION_GETWELL))
            .build();
    public static final Session MACHOMAN = new SessionBuilder()
            .withGym(VALID_GYM_MACHOMAN)
            .withExerciseType(VALID_EXERCISE_TYPE_MACHOMAN)
            .withInterval(LocalDateTime.parse(VALID_START_TIME_MACHOMAN, Interval.DATE_TIME_FORMATTER),
                    Integer.parseInt(VALID_DURATION_MACHOMAN))
            .build();
    public static final Session MACHOMAN_TOMORROW = new SessionBuilder()
            .withGym(VALID_GYM_MACHOMAN)
            .withExerciseType(VALID_EXERCISE_TYPE_MACHOMAN)
            .withInterval(LocalDateTime.parse(VALID_START_TIME_MACHOMAN_TOMORROW, Interval.DATE_TIME_FORMATTER),
                    Integer.parseInt(VALID_DURATION_MACHOMAN))
            .build();

    private TypicalSessions() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Sessions.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Session session : getTypicalSessions()) {
            ab.addSession(session);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical Sessions.
     */
    public static AddressBook getIntegrationAddressBook() {
        AddressBook ab = getTypicalAddressBook();
        for (Schedule schedule : getTypicalSchedules()) {
            ab.addSchedule(schedule);
        }
        return ab;
    }

    public static List<Session> getTypicalSessions() {
        return new ArrayList<>(Arrays.asList(GETWELL, MACHOMAN));
    }

    /**
     * Returns an {@code AddressBook} with all the typical Sessions + a Session with interval start that dynamically
     * to the day after application is loaded.
     */
    public static AddressBook getTypicalWithDayAfterAddressBook() {
        AddressBook ab = new AddressBook();
        for (Session session : getTypicalWithDayAfterSessions()) {
            ab.addSession(session);
        }
        return ab;
    }

    public static List<Session> getTypicalWithDayAfterSessions() {
        return new ArrayList<>(Arrays.asList(GETWELL, MACHOMAN, MACHOMAN_TOMORROW));
    }
}
