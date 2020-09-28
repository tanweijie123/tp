package seedu.address.model.session;

import java.time.LocalDateTime;

interface sessionStud {
    sessionStud add(String gymName, String exerciseType, LocalDateTime startDateTime, int durationInMinute);

    sessionStud edit(int index, String gymName, String exerciseType, LocalDateTime startDateTime, int durationInMinute);

    sessionStud remove(int index);

    String getSessionName();

    String getExerciseType();

    LocalDateTime getStartDateTime();

    int getDuration();
}