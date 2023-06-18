package Staff.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class UpcomingController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    AnchorPane cardAnchor;

    @FXML
    Label preferredDate;

    @FXML
    Label preferredFrom;

    @FXML
    Label preferredTo;
    @FXML
    Label ID;
    @FXML
    Label memo;
    @FXML
    Label appointmentTimestamp;
    @FXML
    Button expire;
    @FXML
    Label appointmentID;
    @FXML
    Label doctorName;
    @FXML
    Label doctorType;
    public void expandMemo(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(null,memo.getText());
    }
    public void onExpire(ActionEvent actionEvent){

    }
}
