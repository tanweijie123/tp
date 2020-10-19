package seedu.address.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.schedule.Schedule;

public class Homepage extends UiPart<AnchorPane> {
    private static final String FXML = "Homepage.fxml";
    private static Homepage homepage;
    private ReadOnlyAddressBook addressBook;

    @FXML
    private GridPane gridpane;

    @FXML
    private ListView<Schedule> todaySchedule;

    @FXML
    private Label lblCurrentlyDetails;

    @FXML
    private Label lblContent;

    @FXML
    private Label lblQotd;

    private Homepage(ReadOnlyAddressBook addressBook) {
        super(FXML);
        this.addressBook = addressBook;
    }

    /**
     * Initialise a Singleton Homepage GUI with an addressbook
     *
     * @param addressBook Address Book that this homepage reference from.
     */
    public static Homepage getHomePage(ReadOnlyAddressBook addressBook) {
        if (Homepage.homepage == null) {
            Homepage.homepage = new Homepage(addressBook);
        }
        return getHomePage();
    }

    /**
     * Gets the Singleton Homepage GUI. To get homepage successfully, it must be initialised
     *     first with an addressbook
     */
    public static Homepage getHomePage() {
        homepage.update();
        return homepage;
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        updateStatistics();
        updateContent();
        updateQotd();
    }

    /**
     * Updates the Statistics shown in Homepage
     */
    private void updateStatistics() {
//        assert(Homepage.homepage != null);
        this.addressBook = addressBook;
        int clients = addressBook.getClientList().size();
        int sessions = addressBook.getSessionList().size();
        int schedule = addressBook.getScheduleList().size();
        String display = String.format("%-10d Clients\n%-10d Sessions \n%-10d Schedules", clients, sessions, schedule);
        this.lblCurrentlyDetails.setText(display);
    }

    /**
     * Updates the content shown in Homepage
     */
    private void updateContent() {
//        assert(Homepage.homepage != null);
        this.lblContent.setText("Today's Schedule - "
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE dd MMMM")));
        todaySchedule.setItems(this.addressBook.getScheduleList());
        todaySchedule.setCellFactory(listView -> new Homepage.ScheduleListViewCell());
    }

    /**
     * Updates the Quote of the day shown in homepage
     */
    private void updateQotd() {
        this.lblQotd.setText("Quote of the day:\nDo Something Today That Your Future Self Will Thank You For");
    }

    class ScheduleListViewCell extends ListCell<Schedule> {
        @Override
        protected void updateItem(Schedule schedule, boolean empty) {
            super.updateItem(schedule, empty);

            if (empty || schedule == null) {
                setGraphic(null);
                setText(null);
            } else {
                Label lbl = new Label();
                lbl.setText(schedule.getClient().getName() + "\n" + schedule.getSession().getGym() + "\n"
                        + schedule.getSession().getInterval().toString());
                setGraphic(lbl);
            }
        }
    }
}
