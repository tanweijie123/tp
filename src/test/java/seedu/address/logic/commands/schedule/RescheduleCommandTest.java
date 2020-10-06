package seedu.address.logic.commands.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.client.ClientCommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.client.ClientCommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.client.ClientCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.client.ClientCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.client.ClientCommandTestUtil.VALID_TAG_INJURY;
import static seedu.address.logic.commands.client.ClientCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.client.ClientCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.client.ClientCommandTestUtil.showClientAtIndex;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.*;

import javax.management.Descriptor;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.client.EditClientCommand;
import seedu.address.logic.commands.client.EditClientCommand.EditClientDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.schedule.Schedule;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.EditClientDescriptorBuilder;
import seedu.address.testutil.RescheduleDescriptorBuilder;
import seedu.address.testutil.ScheduleBuilder;
import seedu.address.logic.commands.schedule.RescheduleCommand.RescheduleDescriptor;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * EditClientCommand.
 */
public class RescheduleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    //    @Test
    //    public void execute_allFieldsSpecifiedUnfilteredList_success() {
    //        Schedule editedSchedule = new ScheduleBuilder().build();
    //        RescheduleDescriptor descriptor = new RescheduleDescriptorBuilder(editedSchedule).build();
    //        RescheduleCommand rescheduleCommand = new RescheduleCommand(INDEX_FIRST_SCHEDULE, descriptor);
    //
    //        String expectedMessage = String.format(RescheduleCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS, rescheduleCommand);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    //        expectedModel.setSchedule(model.getFilteredScheduleList().get(0), editedSchedule);
    //
    //        assertCommandSuccess(rescheduleCommand, model, expectedMessage, expectedModel);
    //    }
    //
    //    @Test
    //    public void execute_someFieldsSpecifiedUnfilteredList_success() {
    //        Index indexLastClient = Index.fromOneBased(model.getFilteredClientList().size());
    //        Client lastClient = model.getFilteredClientList().get(indexLastClient.getZeroBased());
    //
    //        ClientBuilder clientInList = new ClientBuilder(lastClient);
    //        Client editedClient = clientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
    //                .withTags(VALID_TAG_INJURY).build();
    //
    //        EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
    //                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_INJURY).build();
    //        EditClientCommand editClientCommand = new EditClientCommand(indexLastClient, descriptor);
    //
    //        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    //        expectedModel.setClient(lastClient, editedClient);
    //
    //        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel);
    //    }
    //
    //    @Test
    //    public void execute_noFieldSpecifiedUnfilteredList_success() {
    //        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST_CLIENT, new EditClientDescriptor());
    //        Client editedClient = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
    //
    //        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    //
    //        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel);
    //    }
    //
    //    @Test
    //    public void execute_filteredList_success() {
    //        showClientAtIndex(model, INDEX_FIRST_CLIENT);
    //
    //        Client clientInFilteredList = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
    //        Client editedClient = new ClientBuilder(clientInFilteredList).withName(VALID_NAME_BOB).build();
    //        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST_CLIENT,
    //                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());
    //
    //        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    //        expectedModel.setClient(model.getFilteredClientList().get(0), editedClient);
    //
    //        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel);
    //    }
    //
    //    @Test
    //    public void execute_duplicateClientUnfilteredList_failure() {
    //        Client firstClient = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
    //        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(firstClient).build();
    //        EditClientCommand editClientCommand = new EditClientCommand(INDEX_SECOND_CLIENT, descriptor);
    //
    //        assertCommandFailure(editClientCommand, model, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    //    }
    //
    //    @Test
    //    public void execute_duplicateClientFilteredList_failure() {
    //        showClientAtIndex(model, INDEX_FIRST_CLIENT);
    //
    //        // edit Client in filtered list into a duplicate in address book
    //        Client clientInList = model.getAddressBook().getClientList().get(INDEX_SECOND_CLIENT.getZeroBased());
    //        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST_CLIENT,
    //                new EditClientDescriptorBuilder(clientInList).build());
    //
    //        assertCommandFailure(editClientCommand, model, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    //    }

    @Test
    public void execute_invalidScheduleIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredScheduleList().size() + 1);
        RescheduleDescriptor descriptor = new RescheduleDescriptorBuilder().withSessionIndex(INDEX_FIRST_SCHEDULE).build();
        RescheduleCommand rescheduleCommand = new RescheduleCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(rescheduleCommand, model, Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
    }

//    @Test
//    public void equals() {
//        final RescheduleCommand standardCommand = new RescheduleCommand(INDEX_FIRST_SCHEDULE,
//                new RescheduleDescriptor());
//
//        // same values -> returns true
//        RescheduleDescriptor copyDescriptor = new RescheduleDescriptor(DESC_AMY);
//        RescheduleCommand commandWithSameValues = new RescheduleCommand(INDEX_FIRST_SCHEDULE, copyDescriptor);
//        assertTrue(standardCommand.equals(commandWithSameValues));
//
//        // same object -> returns true
//        assertTrue(standardCommand.equals(standardCommand));
//
//        // null -> returns false
//        assertFalse(standardCommand.equals(null));
//
//        // different types -> returns false
//        assertFalse(standardCommand.equals(new ClearCommand()));
//
//        // different index -> returns false
//        assertFalse(standardCommand.equals(new RescheduleCommand(INDEX_SECOND_SCHEDULE, DESC_AMY)));
//
//        // different descriptor -> returns false
//        assertFalse(standardCommand.equals(new RescheduleCommand(INDEX_FIRST_SCHEDULE, DESC_BOB)));
//    }

}
