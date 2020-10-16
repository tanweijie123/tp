package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.session.Session;

public class RightSideBar extends UiPart<AnchorPane> {
    private static final String FXML = "RightSideBar.fxml";
    private final Logger logger = LogsCenter.getLogger(RightSideBar.class);
    private final MainWindow mainWindow;
    private final Logic logic;


    @FXML
    private ListView<Session> sessionListView;

    /**
     * Creates a {@code RightSideBar} with the given {@code ObservableList}.
     */
    public RightSideBar(MainWindow mainWindow, Logic logic) {
        super(FXML);
        this.mainWindow = mainWindow;
        this.logic = logic;
        sessionListView.setItems(logic.getFilteredSessionList());
        sessionListView.setCellFactory(listView -> new RightSideBar.SessionListViewCell());
    }

    /**
     * Updates the content of the Session ListView
     */
    public void update() {
        sessionListView.setItems(null);
        sessionListView.setItems(logic.getFilteredSessionList());
        sessionListView.setCellFactory(listView -> new SessionListViewCell());
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
