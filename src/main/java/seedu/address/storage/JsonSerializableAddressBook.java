package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Client;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_Client = "Clients list contains duplicate Client(s).";

    private final List<JsonAdaptedClient> Clients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given Clients.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("Clients") List<JsonAdaptedClient> Clients) {
        this.Clients.addAll(Clients);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        Clients.addAll(source.getClientList().stream().map(JsonAdaptedClient::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedClient jsonAdaptedClient : Clients) {
            Client Client = jsonAdaptedClient.toModelType();
            if (addressBook.hasClient(Client)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_Client);
            }
            addressBook.addClient(Client);
        }
        return addressBook;
    }

}
