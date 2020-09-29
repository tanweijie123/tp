package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyScheduleList;

public class JsonScheduleListStorage implements ScheduleListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonScheduleListStorage.class);

    private Path filePath;

    public JsonScheduleListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getScheduleListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyScheduleList> readScheduleList() throws DataConversionException {
        return readScheduleList(filePath);
    }

    /**
     * Similar to {@link #readScheduleList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyScheduleList> readScheduleList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableScheduleList> jsonScheduleList = JsonUtil.readJsonFile(
                filePath, JsonSerializableScheduleList.class);
        if (jsonScheduleList.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonScheduleList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveScheduleList(ReadOnlyScheduleList scheduleList) throws IOException {
        saveScheduleList(scheduleList, filePath);
    }

    /**
     * Similar to {@link #saveScheduleList(ReadOnlyScheduleList)}
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveScheduleList(ReadOnlyScheduleList scheduleList, Path filePath) throws IOException {
        requireNonNull(scheduleList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableScheduleList(scheduleList), filePath);
    }
}
