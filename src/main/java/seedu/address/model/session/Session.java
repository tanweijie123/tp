package seedu.address.model.session;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a training Session in FitEgo.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Session {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM yyyy, HH:mm");

    // Identity fields
    private final String gymName;
    private final LocalDateTime startDateTime;
    private final int durationInMinute;

    // Data fields
    private final String exerciseType;

    /**
     * Every field must be present and not null.
     */
    public Session(String gymName, String exerciseType, LocalDateTime startDateTime, int durationInMinute) {
        requireAllNonNull(gymName, exerciseType, startDateTime, durationInMinute);
        this.gymName = gymName;
        this.exerciseType = exerciseType;
        this.startDateTime = startDateTime;
        this.durationInMinute = durationInMinute;
    }

    public String getGym() {
        return gymName;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return startDateTime.plusMinutes(durationInMinute);
    }

    public int getDuration() {
        return durationInMinute;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    /**
     * Returns true if both Sessions do not overlap with each other
     * This defines a weaker notion of equality between two Sessions.
     */
    public boolean isOverlappingSession(Session otherSession) {
        if (otherSession == this) {
            return true;
        }

        if (otherSession == null) {
            return false;
        }

        if (otherSession.getStartDateTime().isAfter(startDateTime)) {
            return otherSession.getStartDateTime().isBefore(getEndDateTime());
        } else {
            return startDateTime.isBefore(otherSession.getEndDateTime());
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
                && otherClient.getStartDateTime().equals(getStartDateTime())
                && otherClient.getDuration() == getDuration()
                && otherClient.getExerciseType().equals(getExerciseType());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(gymName, startDateTime, durationInMinute, exerciseType);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getStartDateTime().format(formatter))
                .append(" - ")
                .append(getEndDateTime().format(formatter))
                .append(" Exercise Type: ")
                .append(getExerciseType())
                .append(" Gym: ")
                .append(getGym())
                .append(" Tags: ");
        return builder.toString();
    }
}
