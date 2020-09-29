package seedu.address.model.schedule.exceptions;

/**
 * Signals that the operation will result in duplicate Schedules (Schedules are considered duplicates if
 * they have the same Client and Session).
 */
public class DuplicateScheduleException extends RuntimeException {
    public DuplicateScheduleException() {
        super("Operation would result in duplicate Schedules");
    }
}
