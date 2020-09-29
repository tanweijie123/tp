package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.schedule.Schedule;

/**
 * Unmodifiable view of a schedule list
 */
public interface ReadOnlyScheduleList {

    /**
     * Returns an unmodifiable view of the Schedule list.
     * This list will not contain any duplicate schedule.
     */
    ObservableList<Schedule> getScheduleList();

}
