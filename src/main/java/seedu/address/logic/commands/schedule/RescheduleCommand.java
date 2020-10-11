package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.schedule.CliSyntax.PREFIX_SESSION_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.session.Session;

/**
 * Edits the details of an existing Client in the address book.
 */
public class RescheduleCommand extends Command {

    public static final String COMMAND_WORD = "reschedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reschedule a client with another session. "
            + "Parameters: INDEX (must be a positive integer)\n"
            + PREFIX_SESSION_INDEX + "SESSION "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_SESSION_INDEX + "1 ";

    public static final String MESSAGE_EDIT_SCHEDULE_SUCCESS = "Rescheduled : \n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_SCHEDULE = "This Schedule overlaps with an existing Schedule";

    private final Index scheduleIndex;
    private final Index sessionIndex;
    private final RescheduleDescriptor editRescheduleDescriptor;

    /**
     * @param scheduleIndex of the Schedule in the filtered schedule list to edit
     * @param sessionIndex of the Session in the filtered session list to edit
     * @param editRescheduleDescriptor details to edit the schedule with
     */
    public RescheduleCommand(Index scheduleIndex, Index sessionIndex, RescheduleDescriptor editRescheduleDescriptor) {
        requireNonNull(scheduleIndex);
        requireNonNull(editRescheduleDescriptor);

        this.scheduleIndex = scheduleIndex;
        this.sessionIndex = sessionIndex;
        this.editRescheduleDescriptor = new RescheduleDescriptor(editRescheduleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Schedule> lastShownList = model.getFilteredScheduleList();
        List<Session> lastShownSessionList = model.getFilteredSessionList();

        if (scheduleIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
        }

        if (sessionIndex.getZeroBased() >= lastShownSessionList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
        }

        Schedule scheduleToEdit = lastShownList.get(scheduleIndex.getZeroBased());
        Schedule editedSchedule = createEditedSchedule(scheduleToEdit, editRescheduleDescriptor,
                lastShownSessionList);

        if (!scheduleToEdit.isUnique(editedSchedule) && model.hasSchedule(editedSchedule)) {
            throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
        }

        model.setSchedule(scheduleToEdit, editedSchedule);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
        return new CommandResult(String.format(MESSAGE_EDIT_SCHEDULE_SUCCESS, editedSchedule));
    }

    /**
     * Creates and returns a {@code Schedule} with the details of {@code scheduleToEdit}
     * edited with {@code editRescheduleDescriptor}.
     */
    private static Schedule createEditedSchedule(Schedule scheduleToEdit, RescheduleDescriptor rescheduleDescriptor,
                                                 List<Session> lastShownSessionList) {
        assert scheduleToEdit != null;

        Client client = scheduleToEdit.getClient();
        Session session = rescheduleDescriptor.getSessionIndex() == null
                ? scheduleToEdit.getSession()
                : lastShownSessionList.get(rescheduleDescriptor.getSessionIndex().get().getZeroBased());

        return new Schedule(client, session);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RescheduleCommand)) {
            return false;
        }

        // state check
        RescheduleCommand e = (RescheduleCommand) other;
        return scheduleIndex.equals(e.scheduleIndex)
                && editRescheduleDescriptor.equals(e.editRescheduleDescriptor);
    }

    /**
     * Stores the details to edit the Schedule with. Each non-empty field value will replace the
     * corresponding field value of the Schedule.
     */
    public static class RescheduleDescriptor {
        private Index clientIndex;
        private Index sessionIndex;

        public RescheduleDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public RescheduleDescriptor(RescheduleDescriptor toCopy) {
            setClientIndex(toCopy.clientIndex);
            setSessionIndex(toCopy.sessionIndex);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(clientIndex, sessionIndex);
        }

        public void setClientIndex(Index clientIndex) {
            this.clientIndex = clientIndex;
        }

        public Optional<Index> getClientIndex() {
            return Optional.ofNullable(clientIndex);
        }

        public void setSessionIndex(Index sessionIndex) {
            this.sessionIndex = sessionIndex;
        }

        public Optional<Index> getSessionIndex() {
            return Optional.ofNullable(sessionIndex);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof RescheduleDescriptor)) {
                return false;
            }

            // state check
            RescheduleDescriptor e = (RescheduleDescriptor) other;

            return getClientIndex().equals(e.getClientIndex())
                    && getSessionIndex().equals(e.getSessionIndex());
        }
    }
}

