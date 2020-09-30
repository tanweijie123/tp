package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Email;
import seedu.address.model.schedule.Schedule;

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
        clientEmail = source.getClientId().toString();
        sessionId = "" + source.getSessionId();
    }

    /**
     * Converts this Jackson-friendly adapted Schedule object into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Schedule.
     */
    public Schedule toModelType() throws IllegalValueException {
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

        return new Schedule(modelClientEmail, modelSessionId);
    }
}
