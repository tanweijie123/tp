package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javax.swing.text.View;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.session.ViewSessionCommand;
import seedu.address.model.client.Client;
import seedu.address.model.session.Session;

public class RightSideBar extends UiPart<AnchorPane> {
    private static final String FXML = "RightSideBar.fxml";
    private final Logger logger = LogsCenter.getLogger(RightSideBar.class);
    private final MainWindow mainWindow;
    private final Logic logic;
    private final String all = "ALL";
    private final String week = "WEEK";
    private final String future = "FUTURE";
    private String previousCommand = "ALL";


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
    }

    /**
     * Updates the content of the Session ListView
     */
    public void update(String commandText) {
        String requiredPeriod = requiredPeriod(commandText);
        title.setText(requiredPeriod);

        sessionListView.setItems(null);
        sessionListView.setItems(logic.getFilteredSessionList());
        sessionListView.setCellFactory(listView -> new SessionListViewCell());
    }

    /**
     * Filters and returns the requiredPeriod according to the commandText
     */
    private String requiredPeriod(String commandText) {
        String firstWord = commandText.split(" ")[0];
        if (firstWord.equals(ViewSessionCommand.COMMAND_WORD)) {
            if (commandText.contains("all")) {
                this.previousCommand = all;
                return all;
            } else if (commandText.contains("week")) {
                this.previousCommand = week;
                return week;
            } else if (commandText.contains("future")) {
                this.previousCommand = future;
                return future;
            }
        }

        return this.previousCommand;
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
                List<Client> associatedClients = logic.getAssociatedClientList(session);
                if (associatedClients.size() > 0) {
                    setGraphic(new SessionCard(session, getIndex() + 1, associatedClients).getRoot());
                } else {
                    setGraphic(new SessionCard(session, getIndex() + 1, null).getRoot());
                }
            }
        }
    }
}
