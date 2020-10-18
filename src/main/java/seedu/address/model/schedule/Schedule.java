package seedu.address.model.schedule;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.CheckExisting;
import seedu.address.model.client.Client;
import seedu.address.model.session.Session;

public class Schedule implements CheckExisting<Schedule>, Comparable<Schedule> {
    private Client client;
    private Session session;
    private boolean isPaid;
    private Remark remark;

    /**
     * Every field must be present and not null.
     */
    public Schedule(Client client, Session session, boolean isPaid, Remark remark) {
        requireAllNonNull(client, session, isPaid);
        this.client = client;
        this.session = session;
        this.isPaid = isPaid;
        this.remark = remark;
    }

    /**
     * Every field must be present and not null. isPaid is set to {@code false}. remark is set to an empty string
     */
    public Schedule(Client client, Session session) {
        requireAllNonNull(client, session);
        this.client = client;
        this.session = session;
        this.isPaid = false;
        this.remark = Remark.EMPTY_REMARK;
    }

    public Client getClient() {
        return client;
    }

    public Session getSession() {
        return session;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns true if both Schedules have the same identity.
     */
    @Override
    public boolean isUnique(Schedule otherSchedule) {
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
        return otherSchedule.client.equals(this.client) && otherSchedule.session.equals(this.session)
                && otherSchedule.isPaid == isPaid && otherSchedule.remark.equals(this.remark);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(client, session, isPaid);
    }

    @Override
    public int compareTo(Schedule other) {
        return this.getSession().compareTo(other.getSession());
    }

    @Override
    public String toString() {
        String paymentStatus = isPaid ? "PAID" : "NOT PAID";
        String remarkPresent = !remark.equals(Remark.EMPTY_REMARK) ? "Remark: " + remark : "";
        return "Client "
                + client
                + "\n"
                + " with session "
                + session
                + "\n"
                + "Status: "
                + paymentStatus
                + "\n"
                + remarkPresent;
    }
}
