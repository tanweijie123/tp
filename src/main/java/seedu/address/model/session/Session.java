package seedu.address.model.session;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a training Session in FitEgo.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Session {
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
     * This defines a weaker notion of equality between two Sessions.
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
        return Objects.hash(gym, interval, exerciseType);
    }

    @Override
    public String toString() {
        return getInterval()
                + " Exercise Type: "
                + getExerciseType()
                + " Gym: "
                + getGym()
                + " Tags: ";
    }
}
