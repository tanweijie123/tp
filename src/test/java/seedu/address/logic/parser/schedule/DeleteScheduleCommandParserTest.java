package seedu.address.logic.parser.schedule;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SCHEDULE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.schedule.DeleteScheduleCommand;

public class DeleteScheduleCommandParserTest {
    private DeleteScheduleCommandParser parser = new DeleteScheduleCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteScheduleCommand(INDEX_FIRST_SCHEDULE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "word", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteScheduleCommand.MESSAGE_USAGE));
    }
}
