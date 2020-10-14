package seedu.address.logic.commands.session;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.session.Session;

/**
 * Deletes a Session identified using it's displayed index from the address book.
 */
public class DeleteSessionCommand extends Command {

    public static final String COMMAND_WORD = "sdel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the Session identified by the index number used in the displayed Session list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_SESSION_SUCCESS = "Deleted Session: %1$s";

    public static final String MESSAGE_FORCE_DELETE_SESSION_USAGE = COMMAND_WORD
            + ": Cannot delete the Session identified by the index number because there are schedules tied to it.\n"
            + "To force delete, pass in f/ true as an option. BEWARE, YOU WILL LOSE ALL RELATED SCHEDULES.\n"
            + "Parameters: INDEX (must be a positive integer) f/ true\n"
            + "Example: " + COMMAND_WORD + " 1 f/ true";

    private final Index targetIndex;
    private final boolean isForced;

    /**
     * Creates a normal mode DeleteSession to delete the Session at {@code targetIndex}
     * @param targetIndex index of to-be deleted session
     */
    public DeleteSessionCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.isForced = false;
    }

    /**
     * Creates a increased privilege mode DeleteSession to force delete the Session at {@code targetIndex}
     * if {@code isForced} is true
     * @param targetIndex index of to-be deleted session
     * @param isForced true if the DeleteSession have increased privilege
     */
    public DeleteSessionCommand(Index targetIndex, boolean isForced) {
        this.targetIndex = targetIndex;
        this.isForced = isForced;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Session> lastShownList = model.getFilteredSessionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
        }

        Session sessionToDelete = lastShownList.get(targetIndex.getZeroBased());

        // Do not delete session unless user use the force flag
        if (model.hasAnyScheduleAssociatedWithSession(sessionToDelete) && !isForced) {
            return new CommandResult(MESSAGE_FORCE_DELETE_SESSION_USAGE);
        }

        model.deleteSessionAssociatedSchedules(sessionToDelete);
        model.deleteSession(sessionToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_SESSION_SUCCESS, sessionToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteSessionCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteSessionCommand) other).targetIndex)); // state check
    }
}
