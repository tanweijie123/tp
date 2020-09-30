package seedu.address.model.schedule;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.client.Email;

public class Schedule {
    private Email clientId;
    private int sessionId;

    /**
     * Every field must be present and not null.
     */
    public Schedule(Email clientId, int sessionId) {
        requireAllNonNull(clientId, sessionId);
        this.clientId = clientId;
        this.sessionId = sessionId;
    }

    public Email getClientId() {
        return clientId;
    }

    public int getSessionId() {
        return sessionId;
    }

    /**
     * Returns true if both Schedules have the same identity.
     */
    public boolean isSameSchedule(Schedule otherSchedule) {
        if (otherSchedule == this) {
            return true;
        }

        if (otherSchedule == null) {
            return false;
        }

        return otherSchedule.clientId.equals(this.clientId) && otherSchedule.sessionId == this.sessionId;
    }

    /**
     * Returns true if both Schedule have the same identity.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Schedule)) {
            return false;
        }

        Schedule otherSchedule = (Schedule) other;
        return otherSchedule.clientId.equals(this.clientId) && otherSchedule.sessionId == this.sessionId;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(clientId, sessionId);
    }

    @Override
    public String toString() {
        return "Client "
                + clientId
                + " with session "
                + sessionId;
    }
}
