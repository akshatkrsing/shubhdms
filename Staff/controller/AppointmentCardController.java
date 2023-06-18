package Staff.controller;

import Server.response.ApproveAppointmentResponse;
import Server.response.CancelAppointmentResponse;
import Staff.main.StaffMain;
import Staff.request.ApproveAppointmentRequest;
import Staff.request.CancelAppointmentRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class AppointmentCardController implements Initializable {
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
    Label doctorPicker;
    @FXML
    Button close;
    @FXML
    Button cancel;
    @FXML
    Button approve;
    @FXML
    TextField fromTime;
    @FXML
    TextField toTime;
    @FXML
    VBox doctorListContainer;
    @FXML
    Label appointmentID;


    public void expandMemo(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(null,memo.getText());
    }

    public void removeCard(ActionEvent actionEvent) {
       FlowPane flowPane = (FlowPane)cardAnchor.getParent();
       flowPane.getChildren().remove(cardAnchor);
    }

    public void cancelAppointment(ActionEvent actionEvent) {
        StaffMain.sendRequest(new CancelAppointmentRequest(appointmentTimestamp.getText(),ID.getText()));
        CancelAppointmentResponse cancelAppointmentResponse = (CancelAppointmentResponse)StaffMain.getResponse();
        assert cancelAppointmentResponse!= null;
        if(cancelAppointmentResponse.getResponse().equals("Successful")) {
            JOptionPane.showMessageDialog(null,"Appointment cancelled successfully!");
        }
        else {
            JOptionPane.showMessageDialog(null,"Some error occurred.");
        }
    }

    public void approveAppointment(ActionEvent actionEvent) {
        StaffMain.sendRequest(new ApproveAppointmentRequest(appointmentID.getText(),ID.getText(),fromTime.getText(), toTime.getText(), doctorPicker.getText()));
        ApproveAppointmentResponse approveAppointmentResponse = (ApproveAppointmentResponse)StaffMain.getResponse();
        assert approveAppointmentResponse!= null;
        if(approveAppointmentResponse.getResponse().equals("Successful")) {
            JOptionPane.showMessageDialog(null,"Appointment approved successfully!");
        }
        else {
            JOptionPane.showMessageDialog(null,"Some error occurred.");
        }
    }


}
