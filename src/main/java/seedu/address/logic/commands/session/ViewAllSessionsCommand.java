package seedu.address.logic.commands.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SESSIONS;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Finds and lists all Sessions in address book regardless of date and time.
 */
public class ViewAllSessionsCommand extends Command {

    public static final String COMMAND_WORD = "vall";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Replaces your right side bar with a overview "
            + "of your sessions in the next 7 days.\n";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredSessionList(PREDICATE_SHOW_ALL_SESSIONS);
        return new CommandResult(Messages.MESSAGE_ALL_SESSIONS_OVERVIEW);
    }
}
