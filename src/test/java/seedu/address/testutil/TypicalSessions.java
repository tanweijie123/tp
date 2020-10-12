package seedu.address.testutil;

import static seedu.address.logic.commands.session.SessionCommandTestUtil.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.session.Session;

/**
 * A utility class containing a list of {@code Session} objects to be used in tests.
 */
public class TypicalSessions {

    // Manually added - Session's details found in {@code SessionCommandTestUtil}
    public static final Session GETWELL = new SessionBuilder()
            .withGym(VALID_GYM_GETWELL)
            .withExerciseType(VALID_EXERCISE_TYPE_GETWELL)
            .withInterval(VALID_START_TIME_GETWELL,
                    VALID_DURATION_GETWELL)
            .build();
    public static final Session MACHOMAN = new SessionBuilder()
            .withGym(VALID_GYM_MACHOMAN)
            .withExerciseType(VALID_EXERCISE_TYPE_MACHOMAN)
            .withInterval(VALID_START_TIME_MACHOMAN,
                    VALID_DURATION_MACHOMAN)
            .build();

    public static final Session MULTRAMAN = new SessionBuilder()
            .withGym(VALID_GYM_ULTRAMAN)
            .withExerciseType(VALID_EXERCISE_TYPE_ULTRAMAN)
            .withInterval(VALID_START_TIME_ULTRAMAN,
                    VALID_DURATION_ULTRAMAN)
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

    public static List<Session> getTypicalSessions() {
        return new ArrayList<>(Arrays.asList(GETWELL, MACHOMAN));
    }
}
