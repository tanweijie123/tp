package seedu.address.ui;

import static seedu.address.model.session.Interval.DATE_TIME_FORMATTER;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.client.Client;
import seedu.address.model.session.Session;

/**
 * An UI component that displays information of a {@code Client}.
 */
public class ClientCard extends UiPart<Region> {

    private static final String FXML = "ClientListCard.fxml";
    private static final String nextSessionStart = "Next Session: ";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Client client;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label nextSession;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ClientCode} with the given {@code Client} and index to display.
     */
    public ClientCard(Client client, int displayedIndex, Session session) {
        super(FXML);
        this.client = client;
        id.setText(displayedIndex + ". ");
        name.setText(client.getName().fullName);
        phone.setText(client.getPhone().value);
        //        client.getTags().stream()
        //                .sorted(Comparator.comparing(tag -> tag.tagName))
        //                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        if (session != null) {
            nextSession.setText(nextSessionStart + session.getInterval().getFormattedStartDateTime(
                    DATE_TIME_FORMATTER)); // placeholder
        } else {
            nextSession.setText(nextSessionStart + " - ");
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientCard)) {
            return false;
        }

        // state check
        ClientCard card = (ClientCard) other;
        return id.getText().equals(card.id.getText())
                && client.equals(card.client);
    }
}
