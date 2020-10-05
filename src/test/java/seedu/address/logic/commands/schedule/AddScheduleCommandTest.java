package seedu.address.logic.commands.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BENSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SESSION;
import static seedu.address.testutil.TypicalSessions.GETWELL;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.session.AddSessionCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.session.Session;
import seedu.address.testutil.ScheduleBuilder;
import seedu.address.testutil.SessionBuilder;

public class AddScheduleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /**
     * Returns an {@code AddressBook} with all the typical Clients and Sessions.
     */
    private static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Client client : getTypicalClients()) {
            ab.addClient(client);
        }
        for (Session session : getTypicalSessions()) {
            ab.addSession(session);
        }
        return ab;
    }

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON));
    }

    public static List<Session> getTypicalSessions() {
        return new ArrayList<>(Arrays.asList(GETWELL));
    }

    @Test
    public void constructor_nullClientAndSessionIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddScheduleCommand(null, null));
    }

    @Test
    public void execute_scheduleAcceptedByModel_addSuccessful() throws Exception {
        Schedule validSchedule = new ScheduleBuilder().build();

        AddScheduleCommand command = new AddScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION);
        command.execute(model);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addSchedule(validSchedule);

        assertEquals(model, expectedModel);
    }

    @Test
    public void execute_duplicateSchedule_throwsCommandException() {
        Schedule validSchedule = new ScheduleBuilder().build();

        AddScheduleCommand command = new AddScheduleCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_SESSION);

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addSchedule(validSchedule);
        assertThrows(CommandException.class, AddScheduleCommand.MESSAGE_DUPLICATE_SCHEDULE, (
            )-> command.execute(model));
    }

    @Test
    public void equals() {
        Session getwell = new SessionBuilder().withGym("Getwell gym").build();
        Session machoman = new SessionBuilder().withGym("Machoman gym").build();
        AddSessionCommand addGetwellCommand = new AddSessionCommand(getwell);
        AddSessionCommand addMachomanCommand = new AddSessionCommand(machoman);

        // same object -> returns true
        assertTrue(addGetwellCommand.equals(addGetwellCommand));

        // same values -> returns true
        AddSessionCommand addGetwellCommandCopy = new AddSessionCommand(getwell);
        assertTrue(addGetwellCommand.equals(addGetwellCommandCopy));

        // different types -> returns false
        assertFalse(addGetwellCommand.equals(1));

        // null -> returns false
        assertFalse(addGetwellCommand.equals(null));

        // different Session -> returns false
        assertFalse(addGetwellCommand.equals(addMachomanCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteClient(Client target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClient(Client target, Client editedClient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getFilteredClientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredClientList(Predicate<Client> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSession(Session session) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSession(Session session) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSession(Session session) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSession(Session target, Session editedSession) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Session> getFilteredSessionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredSessionList(Predicate<Session> predicate) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public boolean hasSchedule(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSchedule(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSchedule(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSchedule(Schedule target, Schedule editedSchedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Schedule> getFilteredScheduleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredScheduleList(Predicate<Schedule> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

}

