package seedu.address.ui;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import seedu.address.logic.parser.session.SessionParserUtil;
import seedu.address.model.client.Client;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.Weight;

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

    @FXML
    private LineChart<String, Number> weightLineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    /**
     * Displays a client's profile in a separate window.
     * It should display all the details pertaining to this {@code Client}
     * @param client The client to display
     */
    public ClientInfoPage(Client client, List<Schedule> relatedSchedule) {
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


        //x is date (at the bottom)
        //y is weight (at the left)
        XYChart.Series<String, Number> xy = new XYChart.Series<>();

        List<XYChart.Data<String, Number>> xyList = relatedSchedule.stream()
                .filter(x -> x.getClient().equals(client) && !x.getWeight().equals(Weight.getDefaultWeight()))
                .sorted() //use default comparator
                .map(x -> {
                    XYChart.Data<String, Number> data = new XYChart.Data<>(
                            SessionParserUtil.parseDateTimeToString(x.getSession().getStartTime()),
                            x.getWeight().getWeight());
                    return data;
                })
                .collect(Collectors.toList());

        if (xyList.size() > 0) {
            xy.getData().addAll(xyList);
            weightLineChart.getData().setAll(xy);
            yAxis.setAutoRanging(false);
            int lowerBound = xyList.stream().mapToInt(x -> (int) x.getYValue().doubleValue()).min().getAsInt() - 3;
            int upperBound = xyList.stream().mapToInt(x -> (int) x.getYValue().doubleValue()).max().getAsInt() + 3;

            yAxis.setLowerBound(lowerBound);
            yAxis.setUpperBound(upperBound);
            yAxis.setTickUnit(1);
        } else {
            weightLineChart.setVisible(false);
        }
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
