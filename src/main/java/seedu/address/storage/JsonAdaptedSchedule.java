package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Email;
import seedu.address.model.schedule.Schedule;

public class JsonAdaptedSchedule {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule's %s field is missing!";
    public static final String MISSING_CLIENT_EMAIL_MESSAGE_FORMAT = "Schedule's %s client email is missing!";
    public static final String MISSING_SESSION_ID_MESSAGE_FORMAT = "Schedule's %s session ID is missing!";

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
     * Converts this Jackson-friendly adapted Schedule object to get its model's {@code Email} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Email.
     */
    public Email getClientEmail() throws IllegalValueException {

        if (clientEmail == null) {
            throw new IllegalValueException(String.format(MISSING_CLIENT_EMAIL_MESSAGE_FORMAT,
                    Email.class.getSimpleName()));
        }

        if (!Email.isValidEmail(clientEmail)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelClientEmail = new Email(clientEmail);

        return modelClientEmail;
    }

    /**
     * Converts this Jackson-friendly adapted Schedule object to get its model's {@code session ID} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted session ID.
     */
    public int getSessionId() throws IllegalValueException {

        if (sessionId == null) {
            throw new IllegalValueException(String.format(MISSING_SESSION_ID_MESSAGE_FORMAT,
                    Integer.class.getSimpleName()));
        }

        // todo: validate id
        final int modelSessionId = Integer.parseInt(sessionId);

        return modelSessionId;
    }
}
