package seedu.address.model.session.exceptions;

/**
 * Signals that the operation will result in overlap Sessions (Sessions are considered overlapping if they share
 * an part of the interval).
 */
public class OverlapSessionException extends RuntimeException {
    public OverlapSessionException() {
        super("Operation would result in overlapping sessions");
    }
}
