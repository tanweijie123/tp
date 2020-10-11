package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.session.Session;

public class RightSideBar extends UiPart<AnchorPane> {
    private static final String FXML = "RightSideBar.fxml";
    private static ObservableList<Session> sessionList;
    private final Logger logger = LogsCenter.getLogger(RightSideBar.class);
    private final MainWindow mainWindow;


    @FXML
    private ListView<Session> sessionListView;
    @FXML
    private Label lblNextClient;

    /**
     * Creates a {@code RightSideBar} with the given {@code ObservableList}.
     */
    public RightSideBar(MainWindow mainWindow, ObservableList<Session> sessionList) {
        super(FXML);
        this.mainWindow = mainWindow;
        this.sessionList = sessionList;
        sessionListView.setItems(sessionList);
        sessionListView.setCellFactory(listView -> new RightSideBar.SessionListViewCell());
        updateUpcomingSession();
    }

    /**
     * Initialises the nearest upcoming session shown.
     */
    public void updateUpcomingSession() {
        if (this.sessionList.isEmpty()) {
            lblNextClient.setText("-");
        } else {
            lblNextClient.setText(sessionList.get(0).getInterval().toString());
        }
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
                setGraphic(new SessionCard(session, getIndex() + 1).getRoot());
            }
        }
    }
}
