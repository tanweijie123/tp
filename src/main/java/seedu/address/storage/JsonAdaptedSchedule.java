package seedu.address.storage;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.session.Session;

public class JsonAdaptedSchedule {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule's %s field is missing!";

    private final String clientEmail;
    private final String sessionId;

    /**
     * Constructs a {@code JsonAdaptedSchedule} with the given Schedule details.
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("clientEmail") String clientEmail,
                              @JsonProperty("sessionId") String sessionId) {
        this.clientEmail = clientEmail;
        this.sessionId = sessionId;
    }

    /**
     * Converts a given {@code Schedule} into this class for Jackson use.
     */
    public JsonAdaptedSchedule(Schedule source) {
        clientEmail = source.getClient().getEmail().toString();
        sessionId = "" + source.getSession().getId();
    }

    /**
     * Converts this Jackson-friendly adapted Schedule object into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Schedule.
     */
    public Schedule toModelType(ObservableList<Client> clients, ObservableList<Session> sessions)
            throws IllegalValueException {
        /* To do the same as Client's toModelType codes, we need to create field-typed classes */

        if (clientEmail == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(clientEmail)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelClientEmail = new Email(clientEmail);

        // todo: validate id.
        if (sessionId == null) {
            throw new IllegalValueException("Id is blank");
        }
        final int modelSessionId = Integer.parseInt(sessionId);

        Client modelClient = null;

        // find client with the same email address
        for (Client client : clients) {
            if (client.getEmail().equals(modelClientEmail)) {
                modelClient = client;
                break;
            }
        }

        Session modelSession = null;

        // find client with the same email address
        for (Session session : sessions) {
            if (session.getId() == modelSessionId) {
                modelSession = session;
                break;
            }
        }

        requireAllNonNull(modelClient, modelSession);

        return new Schedule(modelClient, modelSession);
    }
}
