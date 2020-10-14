package seedu.address.logic.commands.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.client.ClientCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.schedule.RescheduleTestUtil.DESC_SCHA;
import static seedu.address.logic.commands.schedule.RescheduleTestUtil.DESC_SCHB;
import static seedu.address.logic.commands.schedule.ScheduleCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalClients;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SCHEDULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SESSION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SCHEDULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SESSION;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_SESSION;
import static seedu.address.testutil.TypicalSchedules.getTypicalSchedules;
import static seedu.address.testutil.TypicalSessions.getTypicalSessions;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.schedule.RescheduleCommand.RescheduleDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.session.Session;
import seedu.address.testutil.RescheduleDescriptorBuilder;
import seedu.address.testutil.ScheduleBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * EditClientCommand.
 */
public class RescheduleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Schedule schedule : getTypicalSchedules()) {
            ab.addSchedule(schedule);
        }
        for (Session session : getTypicalSessions()) {
            ab.addSession(session);
        }
        for (Client client : getTypicalClients()) {
            ab.addClient(client);
        }

        return ab;
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_failure() {
        Schedule editedSchedule = new ScheduleBuilder().build();
        RescheduleDescriptor descriptor = new RescheduleDescriptorBuilder().build();
        RescheduleCommand rescheduleCommand = new RescheduleCommand(INDEX_FIRST_SCHEDULE,
                INDEX_FIRST_SESSION, descriptor);

        String expectedMessage = String.format(RescheduleCommand.MESSAGE_DUPLICATE_SCHEDULE, rescheduleCommand);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSchedule(model.getFilteredScheduleList().get(0), editedSchedule);

        assertCommandFailure(rescheduleCommand, model, expectedMessage);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastSchedule = Index.fromOneBased(model.getFilteredScheduleList().size());
        Index indexLastSession = Index.fromOneBased(model.getFilteredSessionList().size());
        Client lastClient = model.getFilteredClientList().get(1);
        Session lastSession = model.getFilteredSessionList().get(2);
        Schedule lastSchedule = model.getFilteredScheduleList().get(1);

        ScheduleBuilder scheduleInList = new ScheduleBuilder();
        Schedule editedSchedule = scheduleInList
                .withClient(lastClient)
                .withSession(lastSession).build();

        RescheduleDescriptor descriptor = new RescheduleDescriptorBuilder()
                .withClientIndex(INDEX_SECOND_CLIENT)
                .withSessionIndex(INDEX_THIRD_SESSION).build();
        RescheduleCommand rescheduleCommand = new RescheduleCommand(indexLastSchedule, indexLastSession,
                descriptor);

        String expectedMessage = String.format(RescheduleCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS, editedSchedule);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSchedule(lastSchedule, editedSchedule);

        assertCommandSuccess(rescheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        RescheduleCommand rescheduleCommand = new RescheduleCommand(INDEX_FIRST_SCHEDULE, INDEX_FIRST_SESSION,
                new RescheduleDescriptor());
        Schedule editedSchedule = model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased());

        String expectedMessage = String.format(RescheduleCommand.MESSAGE_NOT_EDITED, editedSchedule);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandFailure(rescheduleCommand, model, expectedMessage);
    }

    @Test
    public void execute_filteredList_success() {
        Client lastClient = model.getFilteredClientList().get(0);
        Session lastSession = model.getFilteredSessionList().get(2);

        Schedule scheduleInFilteredList = model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased());
        Schedule editedSchedule = new ScheduleBuilder(scheduleInFilteredList)
                .withClient(lastClient)
                .withSession(lastSession)
                .build();
        RescheduleCommand rescheduleCommand = new RescheduleCommand(INDEX_FIRST_SCHEDULE, INDEX_FIRST_SESSION,
                new RescheduleDescriptorBuilder()
                        .withClientIndex(INDEX_SECOND_CLIENT)
                        .withSessionIndex(INDEX_THIRD_SESSION)
                        .build());

        String expectedMessage = String.format(RescheduleCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS, editedSchedule);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSchedule(model.getFilteredScheduleList().get(0), editedSchedule);

        assertCommandSuccess(rescheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateScheduleUnfilteredList_failure() {
        RescheduleDescriptor descriptor = new RescheduleDescriptorBuilder().build();
        RescheduleCommand rescheduleCommand = new RescheduleCommand(INDEX_FIRST_SCHEDULE,
                INDEX_FIRST_SESSION, descriptor);

        assertCommandFailure(rescheduleCommand, model, RescheduleCommand.MESSAGE_DUPLICATE_SCHEDULE);
    }

    @Test
    public void execute_duplicateScheduleFilteredList_failure() {

        // edit Schedule in filtered list into a duplicate in address book
        RescheduleCommand rescheduleCommand = new RescheduleCommand(INDEX_FIRST_SCHEDULE, INDEX_FIRST_SESSION,
                new RescheduleDescriptorBuilder().build());

        assertCommandFailure(rescheduleCommand, model, RescheduleCommand.MESSAGE_DUPLICATE_SCHEDULE);
    }

    @Test
    public void execute_invalidScheduleIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredScheduleList().size() + 1);
        RescheduleDescriptor descriptor = new RescheduleDescriptorBuilder()
                .withSessionIndex(INDEX_FIRST_SCHEDULE).build();
        RescheduleCommand rescheduleCommand = new RescheduleCommand(outOfBoundIndex, INDEX_FIRST_SESSION, descriptor);

        assertCommandFailure(rescheduleCommand, model, Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final RescheduleCommand standardCommand = new RescheduleCommand(INDEX_FIRST_SCHEDULE, INDEX_FIRST_SESSION,
                DESC_SCHA);

        // same values -> returns true
        RescheduleDescriptor copyDescriptor = new RescheduleDescriptor(DESC_SCHA);
        RescheduleCommand commandWithSameValues = new RescheduleCommand(INDEX_FIRST_SCHEDULE, INDEX_FIRST_SESSION,
                copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RescheduleCommand(INDEX_SECOND_SCHEDULE,
                INDEX_SECOND_SESSION , DESC_SCHA)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new RescheduleCommand(INDEX_FIRST_SCHEDULE,
                INDEX_FIRST_SESSION, DESC_SCHB)));
    }

}
