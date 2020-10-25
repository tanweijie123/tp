
package seedu.address.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.client.CliSyntax.PREFIX_NAME;
import static seedu.address.model.util.WeightUnit.KILOGRAM;

import java.util.List;
import java.util.function.Supplier;

import javafx.scene.layout.AnchorPane;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.util.WeightUnit;
import seedu.address.ui.ClientInfoPage;

/**
 * View the selected clients' profile.
 */
public class ViewClientCommand extends Command {

    public static final String COMMAND_WORD = "cview";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View the full details of the selected client index\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "[" + PREFIX_NAME + "NAME] "
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_CLIENT_SUCCESS = "Viewing Client: %1$s";

    private final Index targetIndex;
    private Client client;
    /**
     * Indicates the unit of the weight graph shown if the Client has associated schedule with weight recorded.
     */
    private final WeightUnit weightUnit;

    /**
     * Creates a ViewClientCommand to view client at {@code targetIndex}.
     * Weight unit is set to Kilogram by default.
     *
     * @param targetIndex index of the Client in the filtered client list to view.
     */
    public ViewClientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.weightUnit = new WeightUnit(KILOGRAM);
    }

    /**
     *
     * @param targetIndex index of the Client in the filtered client list to view.
     * @param weightUnit unit of the weight graph shown if the Client has associated schedule with weight recorded.
     */
    public ViewClientCommand(Index targetIndex, WeightUnit weightUnit) {
        this.targetIndex = targetIndex;
        this.weightUnit = weightUnit;
    }

    public Client getClient() {
        assert(client != null);
        return client;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToView = lastShownList.get(targetIndex.getZeroBased());
        this.client = clientToView;
        Supplier<AnchorPane> run = () -> {
            ClientInfoPage cip = new ClientInfoPage(client, model.getAddressBook().getScheduleList(),
                    weightUnit.isPoundUnit());
            return cip.getRoot();
        };


        return new CommandResult(String.format(MESSAGE_VIEW_CLIENT_SUCCESS, clientToView), run);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewClientCommand // instanceof handles nulls
                && targetIndex.equals(((ViewClientCommand) other).targetIndex) // state check
                && weightUnit == (((ViewClientCommand) other).weightUnit));
    }
}
