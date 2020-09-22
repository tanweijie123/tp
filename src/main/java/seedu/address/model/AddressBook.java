package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.client.UniqueClientList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameClient comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueClientList Clients;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        Clients = new UniqueClientList();
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
    public void setClients(List<Client> Clients) {
        this.Clients.setClients(Clients);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setClients(newData.getClientList());
    }

    //// Client-level operations

    /**
     * Returns true if a Client with the same identity as {@code Client} exists in the address book.
     */
    public boolean hasClient(Client Client) {
        requireNonNull(Client);
        return Clients.contains(Client);
    }

    /**
     * Adds a Client to the address book.
     * The Client must not already exist in the address book.
     */
    public void addClient(Client p) {
        Clients.add(p);
    }

    /**
     * Replaces the given Client {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in the address book.
     * The Client identity of {@code editedClient} must not be the same as another existing Client in the address book.
     */
    public void setClient(Client target, Client editedClient) {
        requireNonNull(editedClient);

        Clients.setClient(target, editedClient);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeClient(Client key) {
        Clients.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return Clients.asUnmodifiableObservableList().size() + " Clients";
        // TODO: refine later
    }

    @Override
    public ObservableList<Client> getClientList() {
        return Clients.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && Clients.equals(((AddressBook) other).Clients));
    }

    @Override
    public int hashCode() {
        return Clients.hashCode();
    }
}
