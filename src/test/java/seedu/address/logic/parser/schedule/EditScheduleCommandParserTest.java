package seedu.address.logic.parser.schedule;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.schedule.ScheduleCommandTestUtil.CLIENT_INDEX_DESC_A;
import static seedu.address.logic.commands.schedule.ScheduleCommandTestUtil.SESSION_INDEX_DESC_A;
import static seedu.address.logic.commands.schedule.ScheduleCommandTestUtil.UPDATED_IS_PAID_FALSE;
import static seedu.address.logic.commands.schedule.ScheduleCommandTestUtil.UPDATED_IS_PAID_TRUE;
import static seedu.address.logic.commands.schedule.ScheduleCommandTestUtil.UPDATED_REMARK_EMPTY;
import static seedu.address.logic.commands.schedule.ScheduleCommandTestUtil.UPDATED_REMARK_NONEMPTY;
import static seedu.address.logic.commands.schedule.ScheduleCommandTestUtil.UPDATED_SESSION_INDEX_DESC_B;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SESSION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SESSION;
import static seedu.address.testutil.TypicalSchedules.IS_PAID_FALSE;
import static seedu.address.testutil.TypicalSchedules.IS_PAID_TRUE;
import static seedu.address.testutil.TypicalSchedules.TEST_REMARK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.schedule.EditScheduleCommand;
import seedu.address.logic.commands.schedule.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.model.schedule.Remark;
import seedu.address.testutil.EditScheduleDescriptorBuilder;

public class EditScheduleCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditScheduleCommand.MESSAGE_USAGE);

    private final EditScheduleCommandParser parser = new EditScheduleCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no session index and update session index specified
        assertParseFailure(parser, " c/1", MESSAGE_INVALID_FORMAT);

        // no client index and update session index specified
        assertParseFailure(parser, " s/1", MESSAGE_INVALID_FORMAT);

        // no updated field specified
        assertParseFailure(parser, " c/1 s/1", EditScheduleCommand.MESSAGE_NOT_EDITED);

    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, " c/-5 s/-1 us/-3 pd/false", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, " c/0 s/1 us/1 pd/true" , MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, " 1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, " c/1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = CLIENT_INDEX_DESC_A + SESSION_INDEX_DESC_A
                + UPDATED_SESSION_INDEX_DESC_B + UPDATED_IS_PAID_FALSE + UPDATED_REMARK_NONEMPTY;

        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder()
                .withClientIndex(INDEX_FIRST_CLIENT)
                .withSessionIndex(INDEX_FIRST_SESSION)
                .withUpdatedSessionIndex(INDEX_SECOND_SESSION)
                .withUpdatedIsPaid(IS_PAID_FALSE)
                .withUpdatedRemark(TEST_REMARK)
                .build();
        EditScheduleCommand expectedCommand = new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = CLIENT_INDEX_DESC_A + SESSION_INDEX_DESC_A
                + UPDATED_SESSION_INDEX_DESC_B;

        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder()
                .withClientIndex(INDEX_FIRST_CLIENT)
                .withSessionIndex(INDEX_FIRST_SESSION)
                .withUpdatedSessionIndex(INDEX_SECOND_SESSION)
                .build();
        EditScheduleCommand expectedCommand = new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // updated isPaid
        String userInput = CLIENT_INDEX_DESC_A + SESSION_INDEX_DESC_A + UPDATED_IS_PAID_TRUE;
        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder()
                .withClientIndex(INDEX_FIRST_CLIENT)
                .withSessionIndex(INDEX_FIRST_SESSION)
                .withUpdatedIsPaid(IS_PAID_TRUE)
                .build();
        EditScheduleCommand expectedCommand = new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION,
                descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // updated remark
        userInput = CLIENT_INDEX_DESC_A + SESSION_INDEX_DESC_A + UPDATED_REMARK_NONEMPTY;
        descriptor = new EditScheduleDescriptorBuilder()
                .withClientIndex(INDEX_FIRST_CLIENT)
                .withSessionIndex(INDEX_FIRST_SESSION)
                .withUpdatedRemark(TEST_REMARK)
                .build();
        expectedCommand = new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION,
                descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        // is paid and then updated session index
        String userInput = CLIENT_INDEX_DESC_A + SESSION_INDEX_DESC_A + UPDATED_IS_PAID_FALSE
                + UPDATED_SESSION_INDEX_DESC_B;

        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder()
                .withClientIndex(INDEX_FIRST_CLIENT)
                .withSessionIndex(INDEX_FIRST_SESSION)
                .withUpdatedSessionIndex(INDEX_SECOND_SESSION)
                .withUpdatedIsPaid(IS_PAID_FALSE)
                .build();
        EditScheduleCommand expectedCommand = new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // update remark twice, takes last
        userInput = CLIENT_INDEX_DESC_A + SESSION_INDEX_DESC_A + UPDATED_REMARK_NONEMPTY
                + UPDATED_REMARK_EMPTY;

        descriptor = new EditScheduleDescriptorBuilder()
                .withClientIndex(INDEX_FIRST_CLIENT)
                .withSessionIndex(INDEX_FIRST_SESSION)
                .withUpdatedRemark(Remark.EMPTY_REMARK)
                .build();
        expectedCommand = new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = CLIENT_INDEX_DESC_A + SESSION_INDEX_DESC_A + UPDATED_SESSION_INDEX_DESC_B;
        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder()
                .withClientIndex(INDEX_FIRST_CLIENT)
                .withSessionIndex(INDEX_FIRST_SESSION)
                .withUpdatedSessionIndex(INDEX_SECOND_SESSION)
                .build();
        EditScheduleCommand expectedCommand = new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION,
                descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //        // other valid values specified
        //        userInput = targetIndex.getOneBased() + GYM_DESC_MACHOMAN + EXERCISE_TYPE_DESC_MACHOMAN
        //                + START_TIME_DESC_MACHOMAN
        //                + DURATION_DESC_MACHOMAN;
        //        descriptor = new EditScheduleDescriptorBuilder()
        //                .withGym(VALID_GYM_MACHOMAN)
        //                .withExerciseType(VALID_EXERCISE_TYPE_MACHOMAN)
        //                .withInterval(VALID_START_TIME_MACHOMAN,
        //                        VALID_DURATION_MACHOMAN)
        //                .build();
        //        expectedCommand = new EditScheduleCommand(targetIndex, descriptor);
        //        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
