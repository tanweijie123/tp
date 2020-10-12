package seedu.address.model.session;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.logic.parser.session.SessionParserUtil;
import seedu.address.model.CheckExisting;

/**
 * Represents a training Session in FitEgo.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Session implements CheckExisting<Session> {
    // Identity fields
    private final Gym gym;
    private final Interval interval;

    // Data fields
    private final ExerciseType exerciseType;

    /**
     * Every field must be present and not null.
     */
    public Session(Gym gym, ExerciseType exerciseType, Interval interval) {
        requireAllNonNull(gym, exerciseType, interval);
        this.exerciseType = exerciseType;
        this.interval = interval;
        this.gym = gym;
    }

    /**
     * Creates a new Session object.
     * NOTE: DO NOT USE THIS FOR CLIENT INPUT; this is only for loading from database.
     */
    public Session(String gym, String exerciseType, LocalDateTime start, int duration) {
        requireAllNonNull(gym, exerciseType, start, duration);
        this.gym = new Gym(gym);
        this.exerciseType = new ExerciseType(exerciseType);
        this.interval = new Interval(start, duration);
    }

    //Getters / Setters

    public Gym getGym() {
        return gym;
    }

    public Interval getInterval() {
        return interval;
    }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    /**
     * Returns true if both Sessions have overlapping sessions
     *
     * Two sessions are defined as duplicate if and only if at least one time boundary lies strictly inside
     * the other session's interval
     * This defines a different notion of equality between two Sessions compared to {@code equals}
     */
    @Override
    public boolean isUnique(Session otherSession) {
        if (otherSession == this) {
            return true;
        }

        if (otherSession == null) {
            return false;
        }

        if (otherSession.getInterval().getStart().isAfter(getInterval().getStart())) {
            // other session start time is > this session start time
            // this session: 2 - 4pm, other session: 4 - 6pm -> do not overlap
            // this session: 2 - 4.01pm, other session: 4 - 6pm -> overlap
            return getInterval().getEnd().isAfter(otherSession.getInterval().getStart());
        } else {
            // other session start time is <= this session start time
            return otherSession.getInterval().getEnd().isAfter(getInterval().getStart());
        }
    }

    /**
     * Returns true if both Session have the same identity and data fields.
     * This defines a stronger notion of equality between two Sessions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Session)) {
            return false;
        }

        Session otherSession = (Session) other;
        return otherSession.getGym().equals(getGym())
                && otherSession.getInterval().equals(getInterval())
                && otherSession.getExerciseType().equals(getExerciseType());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(gym, interval, exerciseType);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Start: ")
                .append(SessionParserUtil.parseDateTimeToString(getInterval().getStart()))
                .append(" End: ")
                .append(SessionParserUtil.parseDateTimeToString(getInterval().getEnd()))
                .append(" Gym: ")
                .append(gym)
                .append(" Exercise Type: ")
                .append(exerciseType);
        return builder.toString();
    }
}
