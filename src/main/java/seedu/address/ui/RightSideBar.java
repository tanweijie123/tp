package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Client;

public class RightSideBar extends UiPart<AnchorPane> {
    private static final String FXML = "RightSideBar.fxml";
    private final Logger logger = LogsCenter.getLogger(RightSideBar.class);
    private final MainWindow mainWindow;

    @FXML
    private ListView<Client> sessionListView;

    /**
     * Creates a {@code RightSideBar} with the given {@code ObservableList}.
     */
    public RightSideBar(MainWindow mainWindow, ObservableList<Client> sessionList) {
        super(FXML);
        this.mainWindow = mainWindow;
        sessionListView.setItems(sessionList);
        sessionListView.setCellFactory(listView -> new RightSideBar.SessionListViewCell());
    }
    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Client} using a {@code ClientCard}.
     */
    class SessionListViewCell extends ListCell<Client> { //TODO: change to Session once done.
        @Override
        protected void updateItem(Client client, boolean empty) {
            super.updateItem(client, empty);

            if (empty || client == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ClientCard(client, getIndex() + 1).getRoot());
            }
        }
    }
}
