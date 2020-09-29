package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlySessionList;
import seedu.address.model.SessionList;
import seedu.address.model.session.Session;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "session")
public class JsonSerializableSessionList {
    public static final String MESSAGE_DUPLICATE_CLIENT = "Session list contains duplicate Session(s).";

    private final List<JsonAdaptedSession> sessions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSessionList} with the given Sessions.
     */
    @JsonCreator
    public JsonSerializableSessionList(@JsonProperty("sessions") List<JsonAdaptedSession> sessions) {
        this.sessions.addAll(sessions);
    }

    /**
     * Converts a given {@code ReadOnlySessionList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSessionList}.
     */
    public JsonSerializableSessionList(ReadOnlySessionList source) {
        this.sessions.addAll(
                source.getSessionList().stream().map(JsonAdaptedSession::new).collect(Collectors.toList()));
    }

    /**
     * Converts this session list into the model's {@code SessionList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SessionList toModelType() throws IllegalValueException {
        SessionList sessions = new SessionList();
        for (JsonAdaptedSession jsonAdaptedSession : this.sessions) {
            Session session = jsonAdaptedSession.toModelType();
            if (sessions.hasSession(session)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLIENT);
            }
            sessions.addSession(session);
        }
        return sessions;
    }

}
