package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.session.Session;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameClient comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueTList<Client> clients;
    private final UniqueTList<Session> sessions;
    private final UniqueTList<Schedule> schedules;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        clients = new UniqueTList<>();
        sessions = new UniqueTList<>();
        schedules = new UniqueTList<>();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Clients in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Client list with {@code Clients}.
     * {@code Clients} must not contain duplicate Clients.
     */
    public void setClients(List<Client> clients) {
        this.clients.setAll(clients);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setClients(newData.getClientList());
        setSessions(newData.getSessionList());
        setSchedules(newData.getScheduleList());
    }

    //// Client-level operations

    /**
     * Returns true if a Client with the same identity as {@code Client} exists in the address book.
     */
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return clients.contains(client);
    }

    /**
     * Adds a Client to the address book.
     * The Client must not already exist in the address book.
     */
    public void addClient(Client p) {
        clients.add(p);
    }

    /**
     * Replaces the given Client {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in the address book.
     * The Client identity of {@code editedClient} must not be the same as another existing Client in the address book.
     */
    public void setClient(Client target, Client editedClient) {
        requireNonNull(editedClient);

        clients.set(target, editedClient);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeClient(Client key) {
        clients.remove(key);
    }

    //=============================== SESSION-RELATED ===========================================

    /**
     * Replaces the contents of the Session list with {@code Session}.
     * {@code Sessions} must not contain duplicate Sessions.
     */
    public void setSessions(List<Session> sessions) {
        this.sessions.setAll(sessions);
    }

    //// Client-level operations

    /**
     * Returns true if a Session with the same identity as {@code Session} exists in the address book.
     */
    public boolean hasSession(Session session) {
        requireNonNull(session);
        return sessions.contains(session);
    }

    /**
     * Adds a Session to the session list.
     * The Session must not already exist in the session list.
     */
    public void addSession(Session s) {
        sessions.add(s);
    }

    /**
     * Replaces the given Session {@code target} in the list with {@code editedSession}.
     * {@code target} must exist in the address book.
     * The Session identity of {@code editedSession} must not be the same as another existing Session.
     */
    public void setSession(Session target, Session editedSession) {
        requireNonNull(editedSession);

        sessions.set(target, editedSession);
    }

    /**
     * Removes {@code key} from this {@code SessionList}.
     * {@code key} must exist in the session list.
     */
    public void removeSession(Session key) {
        sessions.remove(key);
    }

    //=============================== SCHEDULE-RELATED ===========================================

    /**
     * Replaces the contents of the Schedule list with {@code Schedule}.
     * {@code Schedule} must not contain duplicate Schedules.
     */
    public void setSchedules(List<Schedule> schedules) {
        this.schedules.setAll(schedules);
    }

    //// Client-level operations

    /**
     * Returns true if a Schedule with the same identity as {@code Schedule} exists in the address book.
     */
    public boolean hasSchedule(Schedule schedule) {
        requireNonNull(schedule);
        return schedules.contains(schedule);
    }

    /**
     * Adds a Schedule to the schedule list.
     * The Schedule must not already exist in the schedule list.
     */
    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
    }

    /**
     * Replaces the given Schedule {@code target} in the list with {@code editedSchedule}.
     * {@code target} must exist in the address book.
     * The Schedule identity of {@code editedSchedule} must not be the same as another existing Schedule.
     */
    public void setSchedule(Schedule target, Schedule editedSchedule) {
        requireNonNull(editedSchedule);

        schedules.set(target, editedSchedule);
    }

    /**
     * Removes {@code key} from this {@code ScheduleList}.
     * {@code key} must exist in the schedule list.
     */
    public void removeSchedule(Schedule key) {
        schedules.remove(key);
    }


    //// util methods

    @Override
    public String toString() {
        return String.format("%s\n%s\n%s",
                clients.asUnmodifiableObservableList().size() + " Clients",
                sessions.asUnmodifiableObservableList().size() + " Sessions",
                schedules.asUnmodifiableObservableList().size() + " Schedules");
        // TODO: refine later
    }

    @Override
    public ObservableList<Client> getClientList() {
        return clients.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Session> getSessionList() {
        return sessions.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Schedule> getScheduleList() {
        return schedules.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && clients.equals(((AddressBook) other).clients));
        // todo: add equality check for sessions and schedules
    }

    @Override
    public int hashCode() {
        return clients.hashCode();
    }
}
