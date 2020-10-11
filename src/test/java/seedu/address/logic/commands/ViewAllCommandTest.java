package seedu.address.logic.commands;

import static seedu.address.logic.commands.session.SessionCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ViewAllCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ViewAllCommand(), model, Messages.MESSAGE_ALL_SESSIONS_OVERVIEW,
                expectedModel);
    }

    //TODO: Finish test scenario after getTypicalAddressBook() fixed

    //    @Test
    //    public void execute_nonEmptyAddressBook_success() { //
    //        AddressBook testAb = new AddressBook(getTypicalAddressBook());
    //        testAb.addSession(MACHOMAN_TOMORROW);
    //
    //        Model model = new ModelManager(testAb, new UserPrefs());
    //        Model testModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    //        Model expectedModel = new ModelManager(testAb, new UserPrefs());
    //
    //        new ViewWeekCommand().execute(model);
    //        assertEquals(model, testModel);
    //
    //        assertCommandSuccess(new ViewAllCommand(), model, Messages.MESSAGE_ALL_SESSIONS_OVERVIEW,
    //                expectedModel);
    //  }
}
