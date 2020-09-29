package seedu.address.model.session;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.client.Client;

public class Session {
    private static int idCounter = 0; //this also double as the number of sessions already created.

    /* For now, Session values are primitive types */
    private final int id;
    private String gym;
    private String typeOfExercise;
    private LocalDateTime start;
    private int duration;

    /**
     * Creates a new Session object.
     * Every field must be present and not null.
     *
     * @param gym The gym this session is held at.
     * @param typeOfExercise The type of exercises planned for this session.
     * @param start The starting Date and Time of this session.
     * @param duration The duration of this session.
     */
    public Session(String gym, String typeOfExercise, LocalDateTime start, int duration) {
        requireAllNonNull(gym, start, duration);
        this.id = ++idCounter;
        this.gym = gym;
        this.typeOfExercise = typeOfExercise;
        this.start = start;
        this.duration = duration;
    }

    /**
     * Creates a new Session object.
     * NOTE: DO NOT USE THIS FOR CLIENT INPUT; this is only for loading from database.
     */
    public Session(int id, String gym, String typeOfExercise, LocalDateTime start, int duration) {
        requireAllNonNull(id, gym, typeOfExercise, start, duration);
        this.id = id;
        this.gym = gym;
        this.typeOfExercise = typeOfExercise;
        this.start = start;
        this.duration = duration;

        //check if current counter is below assigned id
        if (id > idCounter) {
            idCounter = id;
        }
    }

    //Getters / Setters
    public int getId() {
        return id;
    }

    public String getGym() {
        return gym;
    }

    public String getTypeOfExercise() {
        return typeOfExercise;
    }

    public LocalDateTime getStartTime() {
        return start;
    }

    public int getDuration() {
        return duration;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

    public void setTypeOfExercise(String typeOfExercise) {
        this.typeOfExercise = typeOfExercise;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    //Methods
    public LocalDateTime getEndTime() {
        return this.start.plusMinutes(duration);
    }

    /**
     * Returns true if both sessions have the same identifier
     */
    public boolean isSameSession(Session otherSession) {
        if (otherSession == this) {
            return true;
        }

        return otherSession != null
                && otherSession.id == this.id;
    }

    /**
     * Returns true if both Sessions have the same identity and data fields.
     * This defines a stronger notion of equality between two Sessions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Session otherSession = (Session) other;
        return otherSession.id == this.id
                && otherSession.gym.equals(this.gym)
                && otherSession.typeOfExercise.equals(this.typeOfExercise)
                && otherSession.start.equals(this.start)
                && otherSession.duration == this.duration;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, gym, typeOfExercise, start, duration);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[" + id + "]")
                .append(" Gym: ")
                .append(gym)
                .append(" Type_Of_Exercise: ")
                .append(typeOfExercise)
                .append(" Start: ")
                .append(ParserUtil.parseDateTimeToString(start))
                .append(" Duration: ")
                .append(duration);
        return builder.toString();
    }
}
