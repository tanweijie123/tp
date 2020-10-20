package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.schedule.ScheduleParserUtil;
import seedu.address.logic.parser.session.SessionParserUtil;
import seedu.address.model.client.Email;
import seedu.address.model.schedule.PaymentStatus;
import seedu.address.model.schedule.Remark;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.session.Interval;

public class JsonAdaptedSchedule {
    public static final String MISSING_CLIENT_EMAIL_MESSAGE_FORMAT = "Schedule's %s client email is missing!";
    public static final String MISSING_SESSION_START_MESSAGE_FORMAT = "Schedule's %s session start time is missing!";
    public static final String MISSING_SESSION_END_MESSAGE_FORMAT = "Schedule's %s session end time is missing!";
    public static final String MISSING_PAYMENT_STATUS_MESSAGE_FORMAT = "Schedule's %s payment status is missing!";
    public static final String MISSING_REMARK_MESSAGE_FORMAT = "Schedule's %s is missing!";

    private final String clientEmail;
    private final String start;
    private final String end;
    private final String paymentStatus;
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedSchedule} with the given Schedule details.
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("clientEmail") String clientEmail,
                               @JsonProperty("sessionStart") String start,
                               @JsonProperty("sessionEnd") String end,
                               @JsonProperty("paymentStatus") String paymentStatus,
                               @JsonProperty("remark") String remark) {
        this.clientEmail = clientEmail;
        this.start = start;
        this.end = end;
        this.paymentStatus = paymentStatus;
        this.remark = remark;
    }

    /**
     * Converts a given {@code Schedule} into this class for Jackson use.
     */
    public JsonAdaptedSchedule(Schedule source) {
        clientEmail = source.getClient().getEmail().toString();
        start = SessionParserUtil.parseDateTimeToString(source.getSession().getStartTime());
        end = SessionParserUtil.parseDateTimeToString(source.getSession().getEndTime());
        paymentStatus = source.getPaymentStatus().value;
        remark = source.getRemark().value;
    }

    /**
     * Converts this Jackson-friendly adapted Schedule object to get its model's {@code Email} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Email.
     */
    public Email getClientEmail() throws IllegalValueException {

        if (clientEmail == null) {
            throw new IllegalValueException(String.format(MISSING_CLIENT_EMAIL_MESSAGE_FORMAT,
                    Email.class.getSimpleName()));
        }

        if (!Email.isValidEmail(clientEmail)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }

        return new Email(clientEmail);
    }

    /**
     * Converts this Jackson-friendly adapted Schedule object to get its model's {@code Interval} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted id.
     */
    public Interval getSessionInterval() throws IllegalValueException {
        if (start == null) {
            throw new IllegalValueException(String.format(MISSING_SESSION_START_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }
        if (end == null) {
            throw new IllegalValueException(String.format(MISSING_SESSION_END_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }

        return SessionParserUtil.parseIntervalFromStartAndEnd(start, end);
    }

    /**
     * Converts this Jackson-friendly adapted Schedule object to get its model's {@code PaymentStatus} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted payment status.
     */
    public PaymentStatus getPaymentStatus() throws IllegalValueException {
        if (paymentStatus == null) {
            throw new IllegalValueException(String.format(MISSING_PAYMENT_STATUS_MESSAGE_FORMAT,
                    PaymentStatus.class.getSimpleName()));
        }

        return ScheduleParserUtil.parsePaymentStatus(paymentStatus);
    }

    /**
     * Converts this Jackson-friendly adapted Schedule object to get its model's {@code remark} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted remark value.
     */
    public Remark getRemark() throws IllegalValueException {
        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_REMARK_MESSAGE_FORMAT,
                    Remark.class.getSimpleName()));
        }

        return new Remark(remark);
    }
}
