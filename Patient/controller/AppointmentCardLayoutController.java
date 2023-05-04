package Patient.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentCardLayoutController implements Initializable {

    @FXML
    Label appointmentLabel;
    @FXML
    Label timeStampLabel;
    @FXML
    Label statusLabel;
    @FXML
    Label fromTimeLabel;
    @FXML
    Label toTimeLabel;
    @FXML
    Label doctorNameLabel;
    @FXML
    Label doctorTypeLabel;
    @FXML
    Label memoLabel;
    @FXML
    ImageView doctorImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
