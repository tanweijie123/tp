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
import seedu.address.model.schedule.Schedule;

/**
 * An Immutable Schedule List that is serializable to JSON format.
 */
@JsonRootName(value = "schedule")
public class JsonSerializableScheduleList {
    public static final String MESSAGE_DUPLICATE_SCHEDULE = "Schedule list contains duplicate Schedule(s).";

    private final List<JsonAdaptedSchedule> schedules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableScheduleList} with the given Schedules.
     */
    @JsonCreator
    public JsonSerializableScheduleList(@JsonProperty("schedules") List<JsonAdaptedSchedule> schedules) {
        this.schedules.addAll(schedules);
    }

    /**
     * Converts a given {@code ReadOnlyScheduleList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableScheduleList}.
     */
    public JsonSerializableScheduleList(ReadOnlyScheduleList source) {
        this.schedules.addAll(
                source.getScheduleList().stream().map(JsonAdaptedSchedule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this schedule list into the model's {@code ScheduleList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ScheduleList toModelType() throws IllegalValueException {
        ScheduleList schedules = new ScheduleList();
        for (JsonAdaptedSchedule jsonAdaptedSchedule : this.schedules) {
            Schedule schedule = jsonAdaptedSchedule.toModelType();
            if (schedules.hasSchedule(schedule)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SCHEDULE);
            }
            schedules.addSchedule(schedule);
        }
        return schedules;
    }

}
