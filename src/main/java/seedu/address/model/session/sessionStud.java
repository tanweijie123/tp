package seedu.address.model.session;

import java.time.LocalDateTime;

interface SessionStud {
    SessionStud add(String gymName, String exerciseType, LocalDateTime startDateTime, int durationInMinute);

    SessionStud edit(int index, String gymName, String exerciseType, LocalDateTime startDateTime, int durationInMinute);

    SessionStud remove(int index);

    String getSessionName();

    String getExerciseType();

    LocalDateTime getStartDateTime();

    int getDuration();
}
