package seedu.address.logic.commands.session;

import static seedu.address.logic.commands.session.SessionCommandTestUtil.MACHOMAN_TOMORROW;
import static seedu.address.logic.commands.session.SessionCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSessions.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ViewWeekSessionsCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        SessionCommandTestUtil.assertCommandSuccess(new ViewWeekSessionsCommand(), model,
                Messages.MESSAGE_UPCOMING_WEEK_SESSIONS_OVERVIEW,
                expectedModel);
    }

    @Test
    public void execute_zeroSessionWithinWeek_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager();
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ViewWeekSessionsCommand(), model, Messages.MESSAGE_UPCOMING_WEEK_SESSIONS_OVERVIEW,
                expectedModel);
    }

    @Test
    public void execute_filterToOneSessionWithinWeek_success() {
        AddressBook ab = getTypicalAddressBook();
        ab.addSession(MACHOMAN_TOMORROW);
        AddressBook testAb = new AddressBook();
        testAb.addSession(MACHOMAN_TOMORROW);

        Model model = new ModelManager(ab, new UserPrefs());
        Model expectedModel = new ModelManager(testAb, new UserPrefs());

        assertCommandSuccess(new ViewWeekSessionsCommand(), model, Messages.MESSAGE_UPCOMING_WEEK_SESSIONS_OVERVIEW,
                expectedModel);
    }
}
