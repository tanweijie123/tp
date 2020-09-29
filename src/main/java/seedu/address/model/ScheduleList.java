package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.UniqueScheduleList;

public class ScheduleList implements ReadOnlyScheduleList {
    private final UniqueScheduleList schedules;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        schedules = new UniqueScheduleList();
    }

    public ScheduleList() {}

    /**
     * Creates a ScheduleList using the Schedules in the {@code toBeCopied}
     */
    public ScheduleList(ReadOnlyScheduleList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Schedule list with {@code Schedule}.
     * {@code Schedules} must not contain duplicate Sessions.
     */
    public void setSchedules(List<Schedule> schedules) {
        this.schedules.setSchedules(schedules);
    }

    /**
     * Resets the existing data of this {@code ScheduleList} with {@code newData}.
     */
    public void resetData(ReadOnlyScheduleList newData) {
        requireNonNull(newData);

        setSchedules(newData.getScheduleList());
    }

    //// Client-level operations

    /**
     * Returns true if a Schedule with the same identity as {@code Schedule} exists in the address book.
     */
    public boolean hasSchedule(Schedule schedule) {
        requireNonNull(schedule);
        return schedules.contains(schedule);
    }

    /**
     * Adds a Schedule to the schedule list.
     * The Schedule must not already exist in the schedule list.
     */
    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
    }

    /**
     * Replaces the given Schedule {@code target} in the list with {@code editedSchedule}.
     * {@code target} must exist in the address book.
     * The Schedule identity of {@code editedSchedule} must not be the same as another existing Schedule.
     */
    public void setSchedule(Schedule target, Schedule editedSchedule) {
        requireNonNull(editedSchedule);

        schedules.setSchedule(target, editedSchedule);
    }

    /**
     * Removes {@code key} from this {@code ScheduleList}.
     * {@code key} must exist in the schedule list.
     */
    public void removeSchedule(Schedule key) {
        schedules.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return schedules.asUnmodifiableObservableList().size() + " Schedules";
        // TODO: refine later
    }

    @Override
    public ObservableList<Schedule> getScheduleList() {
        return schedules.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleList // instanceof handles nulls
                && schedules.equals(((ScheduleList) other).schedules));
    }

    @Override
    public int hashCode() {
        return schedules.hashCode();
    }
}
