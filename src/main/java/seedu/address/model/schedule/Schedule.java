package seedu.address.model.schedule;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.CheckExisting;
import seedu.address.model.client.Client;
import seedu.address.model.session.Session;

public class Schedule implements CheckExisting<Schedule> {
    private Client client;
    private Session session;

    /**
     * Every field must be present and not null.
     */
    public Schedule(Client client, Session session) {
        requireAllNonNull(client, session);
        this.client = client;
        this.session = session;
    }

    public Client getClient() {
        return client;
    }

    public Session getSession() {
        return session;
    }

    /**
     * Returns true if both Schedules have the same identity.
     */
    @Override
    public boolean isExisting(Schedule otherSchedule) {
        if (otherSchedule == this) {
            return true;
        }

        if (otherSchedule == null) {
            return false;
        }

        return otherSchedule.client.equals(this.client) && otherSchedule.session.equals(this.session);
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
        return otherSchedule.client.equals(this.client) && otherSchedule.session == this.session;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(client, session);
    }

    @Override
    public String toString() {
        return "Client "
                + client
                + "\n"
                + " with session "
                + session;
    }
}
