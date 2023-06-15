package Staff.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AppointmentCardController {

    @FXML
    DatePicker date;

    @FXML
    ComboBox<?> docDropDown;

    @FXML
    Label endTimelabel;

    @FXML
    HBox manageapphbox;

    @FXML
    Label memolable;

    @FXML
    Label patientIDlabel;

    @FXML
    Label patientNamelabel;

    @FXML
    Label startTimeLabel;

}
