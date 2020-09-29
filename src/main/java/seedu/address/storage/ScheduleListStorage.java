package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyScheduleList;

/**
 * Represents a storage for {@link seedu.address.model.ScheduleList}.
 */
public interface ScheduleListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getScheduleListFilePath();

    /**
     * Returns ScheduleList data as a {@link ReadOnlyScheduleList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyScheduleList> readScheduleList() throws DataConversionException, IOException;

    /**
     * @see #getScheduleListFilePath()
     */
    Optional<ReadOnlyScheduleList> readScheduleList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveScheduleList(ReadOnlyScheduleList addressBook) throws IOException;

    /**
     * @see #saveScheduleList(ReadOnlyScheduleList)
     */
    void saveScheduleList(ReadOnlyScheduleList scheduleList, Path filePath) throws IOException;

}

