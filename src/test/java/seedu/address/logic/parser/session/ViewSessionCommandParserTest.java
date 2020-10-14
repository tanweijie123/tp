package seedu.address.logic.parser.session;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.session.ViewSessionCommand.VALID_ALL_SESSIONS_PERIOD;
import static seedu.address.logic.commands.session.ViewSessionCommand.VALID_FUTURE_SESSIONS_PERIOD;
import static seedu.address.logic.commands.session.ViewSessionCommand.VALID_WEEK_SESSIONS_PERIOD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.session.CliSyntax.PREFIX_PERIOD;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.session.ViewSessionCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the SessionParserUtil, and
 * therefore should be covered by the SessionParserUtilTest.
 */
public class ViewSessionCommandParserTest {

    public static final String VALID_ALL_SESSION_PREFIX = " " + PREFIX_PERIOD + VALID_ALL_SESSIONS_PERIOD;
    public static final String VALID_WEEK_SESSION_PREFIX = " " + PREFIX_PERIOD + VALID_WEEK_SESSIONS_PERIOD;
    public static final String VALID_FUTURE_SESSION_PREFIX = " " + PREFIX_PERIOD + VALID_FUTURE_SESSIONS_PERIOD;
    public static final String INVALID_SESSION_PREFIX = " " + PREFIX_PERIOD + "TOMORROW";
    public static final String INVALID_EMPTY_SESSION_PREFIX = " " + PREFIX_PERIOD + "";
    public static final String EMPTY_PREAMBLE = " ";

    private ViewSessionCommandParser parser = new ViewSessionCommandParser();

    @Test
    public void parse_allSessionArgs_returnsViewCommand() {
        assertParseSuccess(parser, VALID_ALL_SESSION_PREFIX, new ViewSessionCommand(VALID_ALL_SESSIONS_PERIOD));
    }

    @Test
    public void parse_weekSessionArgs_returnsViewCommand() {
        assertParseSuccess(parser, VALID_WEEK_SESSION_PREFIX, new ViewSessionCommand(VALID_WEEK_SESSIONS_PERIOD));
    }

    @Test
    public void parse_futureSessionArgs_returnsViewCommand() {
        assertParseSuccess(parser, VALID_FUTURE_SESSION_PREFIX, new ViewSessionCommand(VALID_FUTURE_SESSIONS_PERIOD));
    }

    @Test
    public void parse_invalidSessionArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_SESSION_PREFIX, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewSessionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptySessionArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_EMPTY_SESSION_PREFIX, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewSessionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, EMPTY_PREAMBLE, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewSessionCommand.MESSAGE_USAGE));
    }
}
