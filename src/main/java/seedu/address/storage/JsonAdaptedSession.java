package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.session.Session;

public class JsonAdaptedSession {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Session's %s field is missing!";

    private final String id;
    private final String gym;
    private final String typeOfExercise;
    private final String start;
    private final String duration;

    /**
     * Constructs a {@code JsonAdaptedSession} with the given Session details.
     */
    @JsonCreator
    public JsonAdaptedSession(@JsonProperty("id") String id, @JsonProperty("gym") String gym,
                              @JsonProperty("typeOfExercise") String typeOfExercise,
                              @JsonProperty("start") String start,
                              @JsonProperty("duration") String duration) {
        this.id = id;
        this.gym = gym;
        this.typeOfExercise = typeOfExercise;
        this.start = start;
        this.duration = duration;
    }

    /**
     * Converts a given {@code Session} into this class for Jackson use.
     */
    public JsonAdaptedSession(Session source) {
        id = "" + source.getId();
        gym = source.getGym();
        typeOfExercise = source.getTypeOfExercise();
        start = ParserUtil.parseDateTimeToString(source.getStartTime());
        duration = "" + source.getDuration();
    }

    /**
     * Converts this Jackson-friendly adapted Session object into the model's {@code Session} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Session.
     */
    public Session toModelType() throws IllegalValueException {
        /* To do the same as Client's toModelType codes, we need to create field-typed classes */

        if (id.isBlank()) {
            throw new IllegalValueException("Id is blank");
        }

        if (gym.isBlank()) {
            throw new IllegalValueException("Gym is blank");
        }

        if (typeOfExercise.isBlank()) {
            throw new IllegalValueException("TypeOfExercise is blank");
        }

        if (start.isBlank() || !ParserUtil.tryParseDateTime(start)) {
            throw new IllegalValueException("Start is blank or invalid");
        }
        final LocalDateTime dateTime = ParserUtil.parseStringToDateTime(start);

        if (duration.isBlank() || !ParserUtil.tryParseInteger(duration)) {
            throw new IllegalValueException("Duration is blank or invalid");
        }
        final int dur = Integer.parseInt(duration); //assume correct

        return new Session(gym, typeOfExercise, dateTime, dur);
    }
}
