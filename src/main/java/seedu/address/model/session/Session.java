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
    private static int idCounter = 0; //this also double as the number of sessions already created.

    // Identity fields
    private final int id;
    private final Gym gym;
    private final Interval interval;

    // Data fields
    private final ExerciseType exerciseType;

    /**
     * Every field must be present and not null.
     */
    public Session(Gym gym, ExerciseType exerciseType, Interval interval) {
        requireAllNonNull(gym, exerciseType, interval);
        this.id = ++idCounter; // TODO: This will create same ID on shutdown.
        this.exerciseType = exerciseType;
        this.interval = interval;
        this.gym = gym;
    }

    /**
     * Every field must be present and not null. To update a session's details
     * after editing.
     */
    public Session(int id, Gym gym, ExerciseType exerciseType, Interval interval) {
        requireAllNonNull(id, gym, exerciseType, interval);
        this.id = id;
        this.exerciseType = exerciseType;
        this.interval = interval;
        this.gym = gym;
    }

    /**
     * Creates a new Session object.
     * NOTE: DO NOT USE THIS FOR CLIENT INPUT; this is only for loading from database.
     */
    public Session(int id, String gym, String exerciseType, LocalDateTime start, int duration) {
        requireAllNonNull(id, gym, exerciseType, start, duration);
        this.id = id;
        this.gym = new Gym(gym);
        this.exerciseType = new ExerciseType(exerciseType);
        this.interval = new Interval(start, duration);

        //check if current counter is below assigned id
        if (id > idCounter) {
            idCounter = id;
        }
    }

    //Getters / Setters
    public int getId() {
        return id;
    }

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
     * Returns true if both Sessions overlap with each other
     */
    public boolean isOverlappingSession(Session otherSession) {
        if (otherSession == this) {
            return true;
        }

        if (otherSession == null) {
            return false;
        }

        if (otherSession.getInterval().getStart().isAfter(getInterval().getStart())) {
            return otherSession.getInterval().getStart().isBefore(getInterval().getEnd());
        } else {
            return getInterval().getStart().isBefore(otherSession.getInterval().getEnd());
        }
    }

    /**
     * Returns true if both Sessions have the same id
     * This defines a weaker notion of equality between two Sessions.
     */
    @Override
    public boolean isExisting(Session otherSession) {
        if (otherSession == this) {
            return true;
        }

        return otherSession != null
                && otherSession.getGym().equals(getGym())
                && otherSession.getInterval().equals(getInterval())
                && otherSession.getExerciseType().equals(getExerciseType());
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

        Session otherClient = (Session) other;
        return otherClient.getGym().equals(getGym())
                && otherClient.getInterval().equals(getInterval())
                && otherClient.getExerciseType().equals(getExerciseType());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, gym, interval, exerciseType);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[" + id + "]")
                .append(" Start: ")
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
