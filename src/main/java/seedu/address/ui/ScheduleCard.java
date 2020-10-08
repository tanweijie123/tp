package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.client.Client;
import seedu.address.model.schedule.Schedule;

import java.util.Comparator;

/**
 * An UI component that displays information of a {@code Client}.
 */
public class ScheduleCard extends UiPart<Region> {

    private static final String FXML = "ScheduleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private HBox cardPane;
    @FXML
    private Schedule schedule;
    @FXML
    private Label dayOfWeek;
    @FXML
    private Label sessionTime;
    @FXML
    private Label gymName;
    @FXML
    private FlowPane clientNames;


    /**
     * Creates a {@code ScheduleCard} with the given {@code Schedule}.
     */
    public ScheduleCard(Schedule schedule) {
        super(FXML);
        this.schedule = schedule;
        sessionTime.setText(schedule.getSession().getInterval().toString());
        gymName.setText(schedule.getSession().getGym().toString());
        dayOfWeek.setText(schedule.getSession().getInterval().getStartDay());
        // schedule.getClients().stream()
        // .sorted(Comparator.comparing(tag -> tag.tagName))
        // .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduleCard)) {
            return false;
        }

        // state check
        ScheduleCard card = (ScheduleCard) other;
        return schedule.equals(card.schedule);
    }
}
