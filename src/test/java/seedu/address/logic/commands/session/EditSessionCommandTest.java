package seedu.address.logic.commands.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.session.SessionCommandTestUtil.*;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SESSION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SESSION;
import static seedu.address.testutil.TypicalSessions.getTypicalAddressBook;
import seedu.address.logic.commands.session.EditSessionCommand.EditSessionDescriptor;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.session.Session;
import seedu.address.testutil.SessionBuilder;
import seedu.address.testutil.EditSessionDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * EditSessionCommand.
 */
public class EditSessionCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

//    @Test
//    public void execute_allFieldsSpecifiedUnfilteredList_success() {
//        Session editedSession = new SessionBuilder().build();
//        EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder(editedSession).build();
//        EditSessionCommand editSessionCommand = new EditSessionCommand(INDEX_FIRST_SESSION, descriptor);
//
//        String expectedMessage = String.format(EditSessionCommand.MESSAGE_EDIT_SESSION_SUCCESS, editedSession);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setSession(model.getFilteredSessionList().get(0), editedSession);
//
//        assertCommandSuccess(editSessionCommand, model, expectedMessage, expectedModel);
//    }

//    @Test
//    public void execute_someFieldsSpecifiedUnfilteredList_success() throws ParseException {
//        Index indexLastSession = Index.fromOneBased(model.getFilteredSessionList().size());
//        Session lastSession = model.getFilteredSessionList().get(indexLastSession.getZeroBased());
//
//        SessionBuilder SessionInList = new SessionBuilder(lastSession);
//        Session editedSession = SessionInList.withGym(VALID_GYM_GETWELL).withExerciseType(VALID_EXERCISE_TYPE_GETWELL)
//                .withInterval(VALID_START_TIME_GETWELL, VALID_DURATION_GETWELL).build();
//
//        EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
//                .withGym(VALID_GYM_GETWELL)
//                .withExerciseType(VALID_EXERCISE_TYPE_GETWELL)
//                .withInterval(VALID_START_TIME_GETWELL, VALID_DURATION_GETWELL).build();
//        EditSessionCommand editSessionCommand = new EditSessionCommand(indexLastSession, descriptor);
//
//        String expectedMessage = String.format(EditSessionCommand.MESSAGE_EDIT_SESSION_SUCCESS, editedSession);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setSession(lastSession, editedSession);
//
//        assertCommandSuccess(editSessionCommand, model, expectedMessage, expectedModel);
//    }

//    @Test
//    public void execute_noFieldSpecifiedUnfilteredList_success() {
//        EditSessionCommand editSessionCommand = new EditSessionCommand(INDEX_FIRST_SESSION, new EditSessionDescriptor());
//        Session editedSession = model.getFilteredSessionList().get(INDEX_FIRST_SESSION.getZeroBased());
//
//        String expectedMessage = String.format(EditSessionCommand.MESSAGE_EDIT_SESSION_SUCCESS, editedSession);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//
//        assertCommandSuccess(editSessionCommand, model, expectedMessage, expectedModel);
//    }

//    @Test
//    public void execute_filteredList_success() {
//        showSessionAtIndex(model, INDEX_FIRST_SESSION);
//
//        Session SessionInFilteredList = model.getFilteredSessionList().get(INDEX_FIRST_SESSION.getZeroBased());
//        Session editedSession = new SessionBuilder(SessionInFilteredList).withGym(VALID_GYM_GETWELL).build();
//        EditSessionCommand editSessionCommand = new EditSessionCommand(INDEX_FIRST_SESSION,
//                new EditSessionDescriptorBuilder().withGym(VALID_GYM_GETWELL).build());
//
//        String expectedMessage = String.format(EditSessionCommand.MESSAGE_DUPLICATE_SESSION, editedSession);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setSession(model.getFilteredSessionList().get(0), editedSession);
//
//        assertCommandSuccess(editSessionCommand, model, expectedMessage, expectedModel);
//    }

//    @Test
//    public void execute_duplicateSessionUnfilteredList_failure() {
//        Session firstSession = model.getFilteredSessionList().get(INDEX_FIRST_SESSION.getZeroBased());
//        EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder(firstSession).build();
//        EditSessionCommand editSessionCommand = new EditSessionCommand(INDEX_SECOND_SESSION, descriptor);
//
//        assertCommandFailure(editSessionCommand, model, EditSessionCommand.MESSAGE_DUPLICATE_SESSION);
//    }

//    @Test
//    public void execute_duplicateSessionFilteredList_failure() {
//        showSessionAtIndex(model, INDEX_FIRST_SESSION);
//
//        // edit Session in filtered list into a duplicate in address book
//        Session sessionInList = model.getAddressBook().getSessionList().get(INDEX_SECOND_SESSION.getZeroBased());
//        EditSessionCommand editSessionCommand = new EditSessionCommand(INDEX_FIRST_SESSION,
//                new EditSessionDescriptorBuilder(sessionInList).build());
//
//        assertCommandFailure(editSessionCommand, model, EditSessionCommand.MESSAGE_DUPLICATE_SESSION);
//    }

    @Test
    public void execute_invalidSessionIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredSessionList().size() + 1);
        EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder().withGym(VALID_GYM_GETWELL).build();
        EditSessionCommand editSessionCommand = new EditSessionCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editSessionCommand, model, Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
    }

//    /**
//     * Edit filtered list where index is larger than size of filtered list,
//     * but smaller than size of address book
//     */
//    @Test
//    public void execute_invalidSessionIndexFilteredList_failure() {
//        showSessionAtIndex(model, INDEX_FIRST_SESSION);
//        Index outOfBoundIndex = INDEX_SECOND_SESSION;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getSessionList().size());
//
//        EditSessionCommand editSessionCommand = new EditSessionCommand(outOfBoundIndex,
//                new EditSessionDescriptorBuilder().withGym(VALID_GYM_GETWELL).build());
//
//        assertCommandFailure(editSessionCommand, model, Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
//    }

    @Test
    public void equals() {
        final EditSessionCommand standardCommand = new EditSessionCommand(INDEX_FIRST_SESSION, DESC_GETWELL);

        // same values -> returns true
        EditSessionDescriptor copyDescriptor = new EditSessionDescriptor(DESC_GETWELL);
        EditSessionCommand commandWithSameValues = new EditSessionCommand(INDEX_FIRST_SESSION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditSessionCommand(INDEX_SECOND_SESSION, DESC_GETWELL)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditSessionCommand(INDEX_FIRST_SESSION, DESC_MACHOMAN)));
    }

}
