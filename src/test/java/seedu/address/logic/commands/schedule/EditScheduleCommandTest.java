package seedu.address.logic.commands.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.client.ClientCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.schedule.EditScheduleTestUtil.DESC_SCHA;
import static seedu.address.logic.commands.schedule.EditScheduleTestUtil.DESC_SCHB;
import static seedu.address.logic.commands.schedule.ScheduleCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalClients;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SCHEDULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SESSION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SESSION;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_SESSION;
import static seedu.address.testutil.TypicalSchedules.getTypicalSchedules;
import static seedu.address.testutil.TypicalSessions.getTypicalSessions;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.schedule.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.session.Session;
import seedu.address.testutil.EditScheduleDescriptorBuilder;
import seedu.address.testutil.ScheduleBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * EditClientCommand.
 */
public class EditScheduleCommandTest {

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
        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder().build();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION,
                INDEX_SECOND_SESSION, descriptor);

        String expectedMessage = String.format(EditScheduleCommand.MESSAGE_DUPLICATE_SCHEDULE, editScheduleCommand);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSchedule(model.getFilteredScheduleList().get(0), editedSchedule);

        assertCommandFailure(editScheduleCommand, model, expectedMessage);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Schedule lastSchedule = model.getFilteredScheduleList().get(0);

        ScheduleBuilder scheduleInList = new ScheduleBuilder();
        Client lastClient = model.getFilteredClientList().get(0);
        Session lastSession = model.getFilteredSessionList().get(2);
        Schedule editedSchedule = scheduleInList
                .withClient(lastClient)
                .withSession(lastSession).build();

        Index indexFirstClient = Index.fromOneBased(1);
        Index indexFirstSession = Index.fromOneBased(1);
        Index indexUpdatedLastSession = Index.fromOneBased(model.getFilteredSessionList().size() - 1);
        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder()
                .withClientIndex(INDEX_FIRST_CLIENT)
                .withSessionIndex(INDEX_THIRD_SESSION).build();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(indexFirstClient, indexFirstSession,
                indexUpdatedLastSession, descriptor);
        String expectedMessage = String.format(EditScheduleCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS, editedSchedule);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSchedule(lastSchedule, editedSchedule);

        assertCommandSuccess(editScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION,
                INDEX_SECOND_SESSION, new EditScheduleDescriptor());
        Schedule editedSchedule = model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased());

        String expectedMessage = String.format(EditScheduleCommand.MESSAGE_NOT_EDITED, editedSchedule);

        assertCommandFailure(editScheduleCommand, model, expectedMessage);
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
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION,
                INDEX_SECOND_SESSION,
                new EditScheduleDescriptorBuilder()
                        .withClientIndex(INDEX_SECOND_CLIENT)
                        .withSessionIndex(INDEX_THIRD_SESSION)
                        .build());

        String expectedMessage = String.format(EditScheduleCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS, editedSchedule);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSchedule(model.getFilteredScheduleList().get(0), editedSchedule);

        assertCommandSuccess(editScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateScheduleUnfilteredList_failure() {
        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder().build();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION,
                INDEX_SECOND_SESSION, descriptor);

        assertCommandFailure(editScheduleCommand, model, EditScheduleCommand.MESSAGE_DUPLICATE_SCHEDULE);
    }

    @Test
    public void execute_duplicateScheduleFilteredList_failure() {

        // edit Schedule in filtered list into a duplicate in address book
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION,
                INDEX_SECOND_SESSION, new EditScheduleDescriptorBuilder().build());

        assertCommandFailure(editScheduleCommand, model, EditScheduleCommand.MESSAGE_DUPLICATE_SCHEDULE);
    }

    @Test
    public void execute_invalidScheduleIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredScheduleList().size() + 1);
        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder()
                .withSessionIndex(INDEX_FIRST_SESSION).build();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION,
                outOfBoundIndex, descriptor);

        assertCommandFailure(editScheduleCommand, model, EditScheduleCommand.MESSAGE_DUPLICATE_SCHEDULE);
    }

    @Test
    public void equals() {
        final EditScheduleCommand standardCommand = new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION,
                INDEX_SECOND_SESSION, DESC_SCHA);

        // same values -> returns true
        EditScheduleDescriptor copyDescriptor = new EditScheduleDescriptor(DESC_SCHA);
        EditScheduleCommand commandWithSameValues = new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION,
                INDEX_SECOND_SESSION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_THIRD_SESSION,
                INDEX_SECOND_SESSION , DESC_SCHA)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION,
                INDEX_SECOND_SESSION, DESC_SCHB)));
    }

}
