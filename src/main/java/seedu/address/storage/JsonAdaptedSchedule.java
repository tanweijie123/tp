package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.session.SessionParserUtil;
import seedu.address.model.client.Email;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.session.Interval;

import java.time.LocalDateTime;

public class JsonAdaptedSchedule {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule's %s field is missing!";
    public static final String MISSING_CLIENT_EMAIL_MESSAGE_FORMAT = "Schedule's %s client email is missing!";
    public static final String MISSING_SESSION_START_MESSAGE_FORMAT = "Schedule's %s session start time is missing!";
    public static final String MISSING_SESSION_END_MESSAGE_FORMAT = "Schedule's %s session end time is missing!";

    private final String clientEmail;
    private final String sessionStart;
    private final String sessionEnd;

    /**
     * Constructs a {@code JsonAdaptedSchedule} with the given Schedule details.
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("clientEmail") String clientEmail,
                               @JsonProperty("sessionStart") String sessionStart,
                               @JsonProperty("sessionEnd") String sessionEnd) {
        this.clientEmail = clientEmail;
        this.sessionStart = sessionStart;
        this.sessionEnd = sessionEnd;
    }

    /**
     * Converts a given {@code Schedule} into this class for Jackson use.
     */
    public JsonAdaptedSchedule(Schedule source) {
        clientEmail = source.getClient().getEmail().toString();
        sessionStart = source.getSession().getInterval().getStart().toString();
        sessionEnd = source.getSession().getInterval().getEnd().toString();
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
     * Converts this Jackson-friendly adapted Schedule object to get its model's {@code Interval} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted id.
     */
    public Interval getSessionInterval() throws IllegalValueException {
        if (sessionStart == null) {
            throw new IllegalValueException(String.format(MISSING_SESSION_START_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }
        if (sessionEnd == null) {
            throw new IllegalValueException(String.format(MISSING_SESSION_END_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }

        final Interval modelSessionInterval = SessionParserUtil.parseInterval(sessionStart, sessionEnd);

        return modelSessionInterval;
    }
}
