package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyScheduleList;
import seedu.address.model.ReadOnlySessionList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, SessionListStorage, ScheduleListStorage, UserPrefsStorage {

    @Override
    Path getUserPrefsFilePath();

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Path getSessionListFilePath();

    @Override
    Optional<ReadOnlySessionList> readSessionList() throws DataConversionException, IOException;

    @Override
    void saveSessionList(ReadOnlySessionList sessionList) throws IOException;

    @Override
    Path getScheduleListFilePath();

    @Override
    Optional<ReadOnlyScheduleList> readScheduleList() throws DataConversionException, IOException;

    @Override
    void saveScheduleList(ReadOnlyScheduleList scheduleList) throws IOException;
}
