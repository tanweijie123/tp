package seedu.address.logic.commands.client;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.stage.Stage;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.ui.ClientInfoPage;

/**
 * View the selected clients' profile.
 */
public class ViewClientCommand extends Command {

    public static final String COMMAND_WORD = "cview";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View the full details of the selected client index\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_CLIENT_SUCCESS = "Viewing Client: %1$s";

    private final Index targetIndex;

    public ViewClientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToView = lastShownList.get(targetIndex.getZeroBased());

        Stage clientView = new Stage();
        ClientInfoPage cip = new ClientInfoPage(clientToView);
        cip.start(clientView);
        cip.show();

        return new CommandResult(String.format(MESSAGE_VIEW_CLIENT_SUCCESS, clientToView),
                false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewClientCommand // instanceof handles nulls
                && targetIndex.equals(((ViewClientCommand) other).targetIndex)); // state check
    }
}