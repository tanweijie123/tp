package seedu.address.logic.parser.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.schedule.CliSyntax.PREFIX_SESSION_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedule.RescheduleCommand;
import seedu.address.logic.commands.schedule.RescheduleCommand.RescheduleDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class RescheduleCommandParser implements Parser<RescheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RescheduleCommand
     * and returns an RescheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RescheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SESSION_INDEX);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RescheduleCommand.MESSAGE_USAGE), pe);
        }

        RescheduleDescriptor editScheduleDescriptor = new RescheduleDescriptor();

        if (argMultimap.getValue(PREFIX_SESSION_INDEX).isPresent()) {
            editScheduleDescriptor
                    .setSessionIndex(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_SESSION_INDEX).get()));
        }

        if (!editScheduleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(RescheduleCommand.MESSAGE_NOT_EDITED);
        }

        return new RescheduleCommand(index, ParserUtil.parseIndex(argMultimap.getValue(PREFIX_SESSION_INDEX).get()),
                editScheduleDescriptor);
    }

}
