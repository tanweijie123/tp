package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyScheduleList;
import seedu.address.model.ScheduleList;
import seedu.address.model.session.Session;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "session")
public class JsonSerializableScheduleList {
    public static final String MESSAGE_DUPLICATE_CLIENT = "Session list contains duplicate Session(s).";

    private final List<JsonAdaptedSession> sessions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableScheduleList} with the given Sessions.
     */
    @JsonCreator
    public JsonSerializableScheduleList(@JsonProperty("sessions") List<JsonAdaptedSession> sessions) {
        this.sessions.addAll(sessions);
    }

    /**
     * Converts a given {@code ReadOnlyScheduleList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableScheduleList}.
     */
    public JsonSerializableScheduleList(ReadOnlyScheduleList source) {
        this.sessions.addAll(
                source.getScheduleList().stream().map(JsonAdaptedSession::new).collect(Collectors.toList()));
    }

    /**
     * Converts this session list into the model's {@code ScheduleList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ScheduleList toModelType() throws IllegalValueException {
        ScheduleList schedules = new ScheduleList();
        for (JsonAdaptedSession jsonAdaptedSession : this.sessions) {
            Session session = jsonAdaptedSession.toModelType();
            if (schedules.hasSession(session)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLIENT);
            }
            schedules.addSession(session);
        }
        return schedules;
    }

}
