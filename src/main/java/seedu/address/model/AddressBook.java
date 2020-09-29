package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.client.UniqueClientList;
import seedu.address.model.session.Session;
import seedu.address.model.session.UniqueSessionList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameClient comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    //TODO: create a Unique<T>List?
    private final UniqueClientList clients;
    private final UniqueSessionList sessions;
    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        clients = new UniqueClientList();
        sessions = new UniqueSessionList();
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
        this.clients.setClients(clients);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setClients(newData.getClientList());
        setSessions(newData.getSessionList());
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

        clients.setClient(target, editedClient);
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
        this.sessions.setSessions(sessions);
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

        sessions.setSession(target, editedSession);
    }

    /**
     * Removes {@code key} from this {@code SessionList}.
     * {@code key} must exist in the session list.
     */
    public void removeSession(Session key) {
        sessions.remove(key);
    }


    //// util methods

    @Override
    public String toString() {
        return String.format("%s\n%s",
                clients.asUnmodifiableObservableList().size() + " Clients",
                sessions.asUnmodifiableObservableList().size() + " Sessions");
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
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && clients.equals(((AddressBook) other).clients));
    }

    @Override
    public int hashCode() {
        return clients.hashCode();
    }
}
