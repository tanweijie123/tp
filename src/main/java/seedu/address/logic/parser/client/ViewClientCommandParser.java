package seedu.address.logic.parser.client;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.client.CliSyntax.PREFIX_UNIT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.client.ViewClientCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.util.WeightUnit;

/**
 * Parses input arguments and creates a new ViewClientCommand object
 */
public class ViewClientCommandParser implements Parser<ViewClientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewClientCommand
     * and returns a ViewClientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewClientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_UNIT);
        try {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            if (argMultimap.getValue(PREFIX_UNIT).isPresent()) {
                WeightUnit weightUnit = ParserUtil.parseUnit(argMultimap.getValue(PREFIX_UNIT).get());
                return new ViewClientCommand(index, weightUnit);
            }
            return new ViewClientCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewClientCommand.MESSAGE_USAGE), pe);
        }
    }
}
