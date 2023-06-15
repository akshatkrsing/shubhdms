package Staff.controller;

import Patient.entity.Appointment;
import Patient.entity.Main;
import Patient.request.AppointmentListRequest;
import Patient.response.AppointmentListResponse;
import Server.request.ManageAppointmentRequest;
import Staff.response.ManageAppointmentResponse;
import com.sun.net.httpserver.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.w3c.dom.Node;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class ProfileScreenController {

    @FXML
    private Button changePasswordButton;

    @FXML
    private ImageView changeProfilePicImageView;

    @FXML
    private Label chooseDocLb;

    @FXML
    private PasswordField confirmNewPasswordTextField;

    @FXML
    private Button confirmPicChangeButton;

    @FXML
    private Label dateLb;

    @FXML
    private Label eTimeLb;

    @FXML
    private TextField enterCourseCodeTextField;

    @FXML
    private Label heyNameLabel;

    @FXML
    private Button joinCourseButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Label memoLb;

    @FXML
    private PasswordField newPasswordTextField;

    @FXML
    private VBox notificationContainer;

    @FXML
    private ScrollPane notificationScrollPane;

    @FXML
    private PasswordField oldPasswordTextField;

    @FXML
    private Label patentNamelb;

    @FXML
    private Label patientIDLb;

    @FXML
    private ImageView profilePicImageView;

    @FXML
    private Button refreshButton;

    @FXML
    private Label sTimeLb;

    @FXML
    private Button selectImageButton;


    @FXML
    void changePasswordButtonResponse(ActionEvent event) {

    }

    @FXML
    void confirmPicChangeButtonResponse(ActionEvent event) {

    }

    @FXML
    void joinCourseButtonResponse(ActionEvent event) {

    }

    @FXML
    void logOutButtonResponse(ActionEvent event) {

    }
    @FXML
    void onNotificationsClicked(ActionEvent event) {

    }

    @FXML
    void refreshButtonResponse(ActionEvent event) {

    }

    @FXML
    void selectImageButtonResponse(ActionEvent event) {

    }

    @FXML
    private VBox manageAppointmentContainer;

    public void setAppointmentsList() {
        manageAppointmentContainer.getChildren().clear();
        Main.sendRequest((Request) new AppointmentListRequest());
        System.out.println("requesting appointment list");
        AppointmentListResponse appointmentListResponse = (AppointmentListResponse) Main.getResponse();
        System.out.println("got appointment list response");
        assert appointmentListResponse != null;
        System.out.println("response is " + appointmentListResponse);
        ArrayList<Appointment> appointmentArrayList = appointmentListResponse.getAppointmentsList();

        for (Appointment appointment : appointmentArrayList) {
            if (appointment.getStatus() == "0") {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/appointmentCard.fxml"));
                try {
                    Node node = fxmlLoader.load();
                    AppointmentCardController appointmentCardController = fxmlLoader.getController();
                    appointmentCardController.patientNamelabel.setText(getPatientName());
                    appointmentCardController.patientIDlabel.setText("");
                    appointmentCardController.date.setConverter();
                    appointmentCardController.startTimeLabel.setText(appointment.getFromTime().toString());
                    appointmentCardController.endTimelabel.setText(appointment.getToTime().toString());
                    appointmentCardController.memolable.setText(appointment.getMemo());
                    manageAppointmentContainer.getChildren().add((javafx.scene.Node) node);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
    @FXML
    private ComboBox<?> docDropDown;

    public void manageAppointments(){

        if(docDropDown.getValue()==null) {
            JOptionPane.showMessageDialog(null,"choose doc");
        }

        else {
            ManageAppointmentRequest manageAppointmentRequest =  new ManageAppointmentRequest((String) docDropDown.getValue());
            Main.sendRequest((Request) manageAppointmentRequest);
            ManageAppointmentResponse manageAppointmentResponse = (ManageAppointmentResponse) Main.getResponse();
            assert manageAppointmentResponse != null;
            System.out.println("Booking response received");
            if(manageAppointmentResponse.getResponse().equals("Successful")) {
                System.out.println("Booking "+manageAppointmentResponse.getResponse());

            }
            else {
                JOptionPane.showMessageDialog(null,"Booking failed. Try again!");
            }

        }
    }

}


