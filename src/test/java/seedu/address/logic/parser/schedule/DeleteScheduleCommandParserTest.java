package seedu.address.logic.parser.schedule;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SESSION;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.schedule.DeleteScheduleCommand;

public class DeleteScheduleCommandParserTest {
    private DeleteScheduleCommandParser parser = new DeleteScheduleCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, " c/1 s/1", new DeleteScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " c/word s/0", MESSAGE_INVALID_INDEX);
    }
}
