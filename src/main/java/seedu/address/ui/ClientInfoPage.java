package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import seedu.address.commons.util.AppUtil;
import seedu.address.model.client.Client;

public class ClientInfoPage extends UiPart<AnchorPane> implements Ui {
    private static final String FXML = "ClientInfoPage.fxml";
    private static ClientInfoPage clientInfoPage;
    private final Client client;
    private Stage stage;

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

    /**
     * This method is not used if it is embbed in MAIN GUI.
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(getRoot());
        primaryStage.setScene(scene);
        primaryStage.setTitle("View Client Profile");
        primaryStage.getIcons().add(AppUtil.getImage("/images/profile_info.png"));
        this.stage = primaryStage;
    }

    /**
     * Shows the Profile to the user.
     * If there is another client info page opened, it will be closed.
     */
    public void show() {
        if (clientInfoPage != null) {
            Stage stage = (Stage) clientInfoPage.lblName.getScene().getWindow();
            stage.close();
        }
        this.stage.show();
        clientInfoPage = this;
    }
}
