package seedu.address.model;

/**
 * This interface provides a weaker equality to check if there exist another
 * object with the same unique identifier.
 */
public interface CheckExisting<T> {
    public boolean isExisting(T other);
}
