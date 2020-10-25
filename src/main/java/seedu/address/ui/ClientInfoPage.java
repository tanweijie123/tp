package seedu.address.ui;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Client;
import seedu.address.model.schedule.Remark;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.session.ExerciseType;
import seedu.address.model.session.Interval;

public class ClientInfoPage extends UiPart<AnchorPane> {
    private static final String FXML = "ClientInfoPage.fxml";
    private static final Logger logger = LogsCenter.getLogger(ClientInfoPage.class);
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

    @FXML
    private TableView<Schedule> pastSchedulesTableView;

    /**
     * Displays a client's profile in a separate window.
     * It should display all the details pertaining to this {@code Client}
     *
     * @param client        The client to display
     * @param pastSchedules The list of schedules the client has ever gone through
     */
    public ClientInfoPage(Client client, List<Schedule> pastSchedules) {
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

        // Set image based on client's name first character. Skipping if invalid url found.
        // Just to make the app a bit nicer with real human image
        try {
            Image img = new Image("/images/profile-"
                    + ((client.getName().fullName.toLowerCase().charAt(0) - 'a') / 6 + 1) + ".jpg");
            this.imgProfile.setImage(img);
        } catch (NullPointerException | IllegalArgumentException ex) {
            logger.info("Invalid image url, using default image\nException: " + ex);
        }

        TableColumn<Schedule, Interval> intervalColumn = new TableColumn<>("Interval");
        intervalColumn.setCellValueFactory(new PropertyValueFactory<>("interval"));

        TableColumn<Schedule, ExerciseType> exTypeColumn = new TableColumn<>("Exercise Type");
        exTypeColumn.setCellValueFactory(new PropertyValueFactory<>("exerciseType"));

        TableColumn<Schedule, Remark> remarkColumn = new TableColumn<>("Remark");
        remarkColumn.setCellValueFactory(new PropertyValueFactory<>("remark"));

        this.pastSchedulesTableView.getColumns().add(intervalColumn);
        this.pastSchedulesTableView.getColumns().add(exTypeColumn);
        this.pastSchedulesTableView.getColumns().add(remarkColumn);
        this.pastSchedulesTableView.getItems().addAll(pastSchedules);

        /* Make remark column can wrap text
         */
        remarkColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Schedule, Remark> call(TableColumn<Schedule, Remark> arg0) {
                return new TableCell<>() {
                    private Text text;

                    @Override
                    public void updateItem(Remark item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            text = new Text(item.toString());
                            text.wrappingWidthProperty().bind(getTableColumn().widthProperty());
                            text.setStyle("-fx-stroke: white; -fx-stroke-width: 0.5; -fx-padding: 10px;");
                            text.setFont(Font.font("Segoe UI Light"));
                            this.setWrapText(true);

                            setGraphic(text);
                        }
                    }
                };
            }
        });

        intervalColumn.setMinWidth(pastSchedulesTableView.getWidth() * .25);
        exTypeColumn.setPrefWidth(150);
        remarkColumn.setPrefWidth(400);

        remarkColumn.setSortable(false);

        this.pastSchedulesTableView.setPlaceholder(new Label("No schedules to display"));
        this.pastSchedulesTableView.setPrefHeight(250);


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
