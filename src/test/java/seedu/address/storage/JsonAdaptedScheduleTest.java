package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedSchedule.END_TIME_FIELD;
import static seedu.address.storage.JsonAdaptedSchedule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.storage.JsonAdaptedSchedule.START_TIME_FIELD;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedules.ALICE_GETWELL;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.session.SessionParserUtil;
import seedu.address.model.client.Email;
import seedu.address.model.schedule.PaymentStatus;
import seedu.address.model.session.Interval;

public class JsonAdaptedScheduleTest {
    private static final String INVALID_EMAIL = "123";
    private static final String INVALID_END_TIME = "0";
    private static final String INVALID_START_TIME = "example.com";
    private static final String INVALID_PAYMENT_STATUS = "trueee";

    private static final String VALID_EMAIL = ALICE_GETWELL.getClient().getEmail().value;
    private static final String VALID_START_TIME = SessionParserUtil
            .parseDateTimeToString(ALICE_GETWELL.getSession().getStartTime());
    private static final String VALID_END_TIME = SessionParserUtil
            .parseDateTimeToString(ALICE_GETWELL.getSession().getEndTime());
    private static final String VALID_PAYMENT_STATUS = ALICE_GETWELL.getPaymentStatus().value;
    private static final String VALID_REMARK = ALICE_GETWELL.getRemark().value;

    @Test
    public void toModelType_validScheduleDetails_success() throws Exception {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(ALICE_GETWELL);
        assertEquals(ALICE_GETWELL.getClient().getEmail(), schedule.getClientEmail());
        assertEquals(ALICE_GETWELL.getSession().getInterval(), schedule.getSessionInterval());
        assertEquals(ALICE_GETWELL.getPaymentStatus(), schedule.getPaymentStatus());
    }

    @Test
    public void toModelType_invalidClientEmail_throwsIllegalValueException() {
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(INVALID_EMAIL, VALID_START_TIME,
                VALID_END_TIME, VALID_PAYMENT_STATUS, VALID_REMARK);
        assertThrows(IllegalValueException.class, expectedMessage, schedule::getClientEmail);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(null, VALID_START_TIME,
                VALID_END_TIME, VALID_PAYMENT_STATUS, VALID_REMARK);
        assertThrows(IllegalValueException.class, expectedMessage, schedule::getClientEmail);
    }

    @Test
    public void toModelType_invalidPaymentStatus_throwsIllegalValueException() {
        String expectedMessage = PaymentStatus.MESSAGE_INVALID_PAYMENT_STATUS;
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_EMAIL, VALID_START_TIME,
                VALID_END_TIME, INVALID_PAYMENT_STATUS, VALID_REMARK);
        assertThrows(IllegalValueException.class, expectedMessage, schedule::getPaymentStatus);
    }

    @Test
    public void toModelType_nullPaymentStatus_throwsIllegalValueException() {
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PaymentStatus.class.getSimpleName());
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_EMAIL, VALID_START_TIME,
                VALID_END_TIME, null, VALID_REMARK);
        assertThrows(IllegalValueException.class, expectedMessage, schedule::getPaymentStatus);
    }

    @Test
    public void toModelType_invalidInterval_throwsIllegalValueException() {
        // invalid start time
        String expectedMessage = Interval.MESSAGE_CONSTRAINTS;
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_EMAIL, INVALID_START_TIME,
                VALID_END_TIME, VALID_PAYMENT_STATUS, VALID_REMARK);
        assertThrows(IllegalValueException.class, expectedMessage, schedule::getSessionInterval);

        // invalid end time
        expectedMessage = Interval.MESSAGE_CONSTRAINTS;
        schedule = new JsonAdaptedSchedule(VALID_EMAIL, VALID_START_TIME,
                INVALID_END_TIME, VALID_PAYMENT_STATUS, VALID_REMARK);
        assertThrows(IllegalValueException.class, expectedMessage, schedule::getSessionInterval);
    }

    @Test
    public void toModelType_nullInterval_throwsIllegalValueException() {
        // null start time
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, START_TIME_FIELD);
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_EMAIL, null,
                VALID_END_TIME, VALID_PAYMENT_STATUS, VALID_REMARK);
        assertThrows(IllegalValueException.class, expectedMessage, schedule::getSessionInterval);

        // null end time
        expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, END_TIME_FIELD);
        schedule = new JsonAdaptedSchedule(VALID_EMAIL, VALID_START_TIME,
                null, VALID_PAYMENT_STATUS, VALID_REMARK);
        assertThrows(IllegalValueException.class, expectedMessage, schedule::getSessionInterval);
    }
}
