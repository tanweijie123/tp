package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.schedule.Schedule;

public class DeleteScheduleCommand extends Command {
    public static final String COMMAND_WORD = "deschedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deschedule a client from a session. "
            + "Parameters: "
            + "INDEX (must be a positive integer)"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Schedule deleted: \n%1$s";

    private final Index targetIndex;

    /**
     * Creates an DeleteScheduleCommand to delete the specified {@code Schedule}
     */
    public DeleteScheduleCommand(Index targetIndex) {
        requireAllNonNull(targetIndex);

        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Schedule> lastShownScheduleList = model.getFilteredScheduleList();

        if (targetIndex.getZeroBased() >= lastShownScheduleList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
        }

        Schedule scheduleToDelete = lastShownScheduleList.get(targetIndex.getZeroBased());

        model.deleteSchedule(scheduleToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, scheduleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteScheduleCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteScheduleCommand) other).targetIndex));
    }
}
