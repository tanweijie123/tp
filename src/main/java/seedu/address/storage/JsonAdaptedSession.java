package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.session.ExerciseType;
import seedu.address.model.session.Gym;
import seedu.address.model.session.Interval;
import seedu.address.model.session.Session;

public class JsonAdaptedSession {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Session's %s field is missing!";

    private final String id;
    private final String gym;
    private final String exerciseType;
    private final String start;
    private final String end;

    /**
     * Constructs a {@code JsonAdaptedSession} with the given Session details.
     */
    @JsonCreator
    public JsonAdaptedSession(@JsonProperty("id") String id, @JsonProperty("gym") String gym,
                              @JsonProperty("exerciseType") String exerciseType,
                              @JsonProperty("start") String start,
                              @JsonProperty("end") String end) {
        this.id = id;
        this.gym = gym;
        this.exerciseType = exerciseType;
        this.start = start;
        this.end = end;
    }

    /**
     * Converts a given {@code Session} into this class for Jackson use.
     */
    public JsonAdaptedSession(Session source) {
        id = "" + source.getId();
        gym = source.getGym().toString();
        exerciseType = source.getExerciseType().toString();
        start = ParserUtil.parseDateTimeToString(source.getInterval().getStart());
        end = ParserUtil.parseDateTimeToString(source.getInterval().getEnd());
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

        final Gym modelGym = new Gym(gym);

        if (exerciseType.isBlank()) {
            throw new IllegalValueException("ExerciseType is blank");
        }

        final ExerciseType modelExerciseType = new ExerciseType(exerciseType);

        if (start.isBlank() || !ParserUtil.tryParseDateTime(start)) {
            throw new IllegalValueException("Start is blank or invalid");
        }

        if (end.isBlank() || !ParserUtil.tryParseDateTime(end)) {
            throw new IllegalValueException("Start is blank or invalid");
        }

        final LocalDateTime startDateTime = ParserUtil.parseStringToDateTime(start);
        final LocalDateTime endDateTime = ParserUtil.parseStringToDateTime(end);
        final int duration = (int) startDateTime.until(endDateTime, ChronoUnit.MINUTES);

        if (!Interval.isValidInterval(duration)) {
            throw new IllegalValueException(Interval.MESSAGE_CONSTRAINTS);
        }
        final Interval modelInterval = new Interval(startDateTime, duration);

        return new Session(modelGym, modelExerciseType, modelInterval);
    }
}
