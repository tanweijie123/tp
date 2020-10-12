package seedu.address.logic.commands.session;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ViewAllSessionsCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        SessionCommandTestUtil.assertCommandSuccess(new ViewAllSessionsCommand(), model,
                Messages.MESSAGE_ALL_SESSIONS_OVERVIEW,
                expectedModel);
    }
}
