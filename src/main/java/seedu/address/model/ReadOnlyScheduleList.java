package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.session.Session;

/**
 * Unmodifiable view of an session list
 */
public interface ReadOnlyScheduleList {

    /**
     * Returns an unmodifiable view of the Session list.
     * This list will not contain any duplicate session (by id).
     */
    ObservableList<Session> getScheduleList();

}
