package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import seedu.address.model.ReadOnlyAddressBook;

public class Homepage extends UiPart<AnchorPane> {
    private static final String FXML = "Homepage.fxml";
    private static Homepage homepage;

    @FXML
    private GridPane gridpane;

    @FXML
    private Label lblCurrentlyDetails;

    @FXML
    private Label lblSampleCommands;

    @FXML
    private Label lblQotd;

    private Homepage() {
        super(FXML);
        updateStatistics(null);
        setContent();
        setQotd();
    }

    /**
     * Creates a Singleton Homepage GUI to create only 1 instance of Homepage
     * @return Homepage
     */
    public static Homepage getHomePage() {
        if (homepage == null) {
            homepage = new Homepage();
        }
        return homepage;
    }

    /**
     * Initialise the Statistics shown in Homepage
     */
    public void updateStatistics(ReadOnlyAddressBook addressBook) {
        String display = "";
        if (addressBook != null) {
            int clients = addressBook.getClientList().size();
            int sessions = addressBook.getSessionList().size();
            int schedule = addressBook.getScheduleList().size();
            display = String.format("%-10d Clients\n%-10d Sessions \n%-10d Schedules", clients, sessions, schedule);
        }
        this.lblCurrentlyDetails.setText(display);
    }

    /**
     * Initialise the content shown in Homepage
     */
    public void setContent() {
        this.lblSampleCommands.setText("Add Client:\t\tcadd\nEdit Client:\t\tcedit\nHelp:\t\t\thelp");
    }

    /**
     * Initialise the Quote of the day shown in homepage
     */
    private void setQotd() {
        this.lblQotd.setText("Quote of the day:\nDo Something Today That Your Future Self Will Thank You For");
    }
}
