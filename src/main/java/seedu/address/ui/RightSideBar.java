package seedu.address.ui;

import static seedu.address.logic.commands.session.ViewSessionCommand.PREDICATE_SHOW_UPCOMING_WEEK_SESSIONS;
import static seedu.address.logic.parser.session.CliSyntax.PREFIX_PERIOD;

import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.session.ViewSessionCommand;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.session.Session;

public class RightSideBar extends UiPart<AnchorPane> {
    private static final String FXML = "RightSideBar.fxml";
    private final Logger logger = LogsCenter.getLogger(RightSideBar.class);
    private final MainWindow mainWindow;
    private final Logic logic;
    private String latestPeriod = "WEEK";

    @FXML
    private ListView<Session> sessionListView;

    @FXML
    private Label title;

    /**
     * Creates a {@code RightSideBar} with the given {@code ObservableList}.
     */
    public RightSideBar(MainWindow mainWindow, Logic logic) {
        super(FXML);
        this.mainWindow = mainWindow;
        this.logic = logic;
        sessionListView.setItems(logic.getFilteredSessionList());
        sessionListView.setCellFactory(listView -> new RightSideBar.SessionListViewCell());
        title.setText(latestPeriod);
        logic.updateFilteredSessionList(PREDICATE_SHOW_UPCOMING_WEEK_SESSIONS);
    }

    /**
     * Updates the content of the Session ListView
     */
    public void update(CommandResult commandResult, String commandText) {
        String requiredPeriod = requiredPeriod(commandResult, commandText);
        title.setText(requiredPeriod);

        sessionListView.setItems(null);
        sessionListView.setItems(logic.getFilteredSessionList());
        sessionListView.setCellFactory(listView -> new SessionListViewCell());
    }

    /**
     * Filters and returns the requiredPeriod according to the commandText
     */
    private String requiredPeriod(CommandResult commandResult, String commandText) {
        if (commandResult.getFeedbackToUser().equals(ViewSessionCommand.MESSAGE_SHOW_SESSIONS_SUCCESS)
                && commandText.contains(PREFIX_PERIOD.toString())) {
            int startOfPeriod = commandText.indexOf(PREFIX_PERIOD.toString());
            String period = commandText.substring(startOfPeriod + 2).toUpperCase();
            this.latestPeriod = period;
            return period;
        }
        return this.latestPeriod;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Session} using a {@code SessionCard}.
     */
    class SessionListViewCell extends ListCell<Session> {
        @Override
        protected void updateItem(Session session, boolean empty) {
            super.updateItem(session, empty);

            if (empty || session == null) {
                setGraphic(null);
                setText(null);
            } else {
                List<Schedule> associatedSchedules = logic.getAssociatedScheduleList(session);
                if (associatedSchedules.size() > 0) {
                    setGraphic(new SessionCard(session, getIndex() + 1, associatedSchedules).getRoot());
                } else {
                    setGraphic(new SessionCard(session, getIndex() + 1, null).getRoot());
                }
            }
        }
    }
}
