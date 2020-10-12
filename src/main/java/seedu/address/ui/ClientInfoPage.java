package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import seedu.address.model.client.Client;

public class ClientInfoPage extends UiPart<AnchorPane> {
    private static final String FXML = "ClientInfoPage.fxml";
    private final Client client;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPhone;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblAddress;

    @FXML
    private FlowPane tags;

    /**
     * Displays a client's profile in a separate window.
     * It should display all the details pertaining to this {@code Client}
     * @param client The client to display
     */
    public ClientInfoPage(Client client) {
        super(FXML);
        this.client = client;

        //TODO: update profile image
        this.lblName.setText(client.getName().fullName);
        this.lblPhone.setText(client.getPhone().value);
        this.lblEmail.setText(client.getEmail().value);
        this.lblAddress.setText(client.getAddress().value);
        client.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientInfoPage)) {
            return false;
        }

        // state check
        ClientInfoPage card = (ClientInfoPage) other;
        return client.equals(card.client);
    }
}
