package Staff.controller;

import Patient.request.ChangePasswordRequest;
import Patient.request.ChangeProfilePicRequest;
import Patient.request.GetProfilePicRequest;
import Patient.request.LogOutRequest;
import Server.entity.Appointment;
import Server.response.*;
import Staff.main.StaffMain;
import Staff.request.StaffAppointmentListRequest;
import Staff.request.UpcomingListRequest;
import Staff.util.HashUtil;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.awt.image.ToolkitImage;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ProfileScreenController {
    public void refreshButtonResponse() {
        System.out.println("Refreshed!");
//        setDutyChart();
//        setProfilePic();
//        System.out.println("Profile pic processed!");
        setStaffAppointmentsList();
        setUpcomingList();
//        setBulletin();
    }
    public String name;
    @FXML
    public Label heyNameLabel;
    public void first(String name) {
        StaffMain.profileScreenController = this;
        this.name=name;
        heyNameLabel.setText("Hey, "+name);
        System.out.println("inside the first method after login trying to create chat socket");
        refreshButtonResponse();
    }
    @FXML
    private ImageView profilePicImageView;
    @FXML
    private ImageView changeProfilePicImageView;

    private void setProfilePic() {
        GetProfilePicRequest getProfilePicRequest = new GetProfilePicRequest();
        System.out.println("Profile pic request sent!");
        StaffMain.sendRequest(getProfilePicRequest);
        GetProfilePicResponse getProfilePicResponse = (GetProfilePicResponse) StaffMain.getResponse();
        System.out.println("Image input stream received "+getProfilePicResponse);
        BufferedImage bufferedImage;
        Image image;
        if(getProfilePicResponse != null) {

            System.out.println("java.swing.ImageIcon to javafx.scene.image.Image");
            bufferedImage=  ((ToolkitImage)getProfilePicResponse. getImageIcon().getImage()).getBufferedImage();
//            bufferedImage  = getProfilePicResponse.getBufferedImage();
            image = SwingFXUtils.toFXImage(bufferedImage, null);
//            ImageIcon swingImageIcon = getProfilePicResponse. getImageIcon();
//            image = new javafx.scene.image.Image(swingImageIcon.getDescription());
            profilePicImageView.setImage(image);
            changeProfilePicImageView.setImage(image);
        }
    }

    @FXML
    private PasswordField oldPasswordTextField;
    @FXML
    private PasswordField newPasswordTextField;
    @FXML
    private PasswordField confirmNewPasswordTextField;

    public void changePasswordButtonResponse() {
        String oldPassword = oldPasswordTextField.getText();
        String newPassword = newPasswordTextField.getText();
        String confirmedNewPassword = confirmNewPasswordTextField.getText();

        if(newPassword.equals(confirmedNewPassword)) {
            ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(HashUtil.getMd5(oldPassword),HashUtil.getMd5(newPassword));
            System.out.println("change password request sent");
            StaffMain.sendRequest(changePasswordRequest);
            ChangePasswordResponse changePasswordResponse = (ChangePasswordResponse) StaffMain.getResponse();
            assert changePasswordResponse != null;
            if(changePasswordResponse.getResponse().equals("Successful")) {
                JOptionPane.showMessageDialog(null,"Password changed successfully!");
            }
            else {
                JOptionPane.showMessageDialog(null,"Some error occurred.");
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"New password fields don't match.");
        }
    }

    //Changing profile picture
    @FXML
    private Button selectImageButton;
    File selectedFile;
    //Select the image to set
    public void selectImageButtonResponse() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("image files","*.png","*.jpg","*.jpeg"));
        selectedFile = fc.showOpenDialog(null);
        selectImageButton.setText(selectedFile.getName());
    }

    //Send request to server to set it
    public void confirmPicChangeButtonResponse() {
        try {
            FileInputStream fis = new FileInputStream(selectedFile);
            byte[] imageArray  = new byte[(int) selectedFile.length()];
            fis.read(imageArray);
            fis.close();
            ChangeProfilePicRequest changeProfilePicRequest = new ChangeProfilePicRequest(imageArray);
            StaffMain.sendRequest(changeProfilePicRequest);
            System.out.println("profile pic Request sent ");
            ChangeProfilePicResponse changeProfilePicResponse = (ChangeProfilePicResponse) StaffMain.getResponse();
            System.out.println("response profile pic "+ changeProfilePicResponse);
            assert changeProfilePicResponse != null;
            if(changeProfilePicResponse.getResponse().equals("Successful")) {
                JOptionPane.showMessageDialog(null,"Profile picture changed successfully!");
                setProfilePic();
            }
            else {
                JOptionPane.showMessageDialog(null,"Some error occurred.");
            }
        }
        catch(FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null,"Please select a valid image first!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button logOutButton;
    public void logOutButtonResponse() {
        LogOutRequest logOutRequest = new LogOutRequest();
        StaffMain.sendRequest(logOutRequest);
        LogOutResponse logOutResponse = (LogOutResponse)StaffMain.getResponse();
        assert logOutResponse != null;
        System.out.println("Response received ");
        if(logOutResponse.getResponse().equals("Successful")) {
            System.out.println("Logout "+logOutResponse.getResponse());
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../fxml/Login.fxml"));
            Stage stage=(Stage)logOutButton.getScene().getWindow();
            try {
                Scene scene=new Scene(loader.load(), logOutButton.getScene().getWidth(), logOutButton.getScene().getHeight());
                StaffMain.profileScreenController = null;
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("Login");
        }
        else {
            JOptionPane.showMessageDialog(null,"LogOut failed. Please try again!");
        }
    }

    /** using appointment state rather than userID **/
    @FXML
    private FlowPane appointmentContainer ;
    public void setStaffAppointmentsList(){
        appointmentContainer.getChildren().clear();
        StaffMain.sendRequest(new StaffAppointmentListRequest());
        System.out.println("requesting appointment list");
        StaffAppointmentListResponse staffAppointmentListResponse = (StaffAppointmentListResponse) StaffMain.getResponse();
        System.out.println("got appointment list response");
        assert staffAppointmentListResponse != null;
        System.out.println("response is "+staffAppointmentListResponse);
        ArrayList<Appointment> appointmentArrayList = staffAppointmentListResponse.getAppointmentsList();
        if(appointmentArrayList.isEmpty()) System.out.println("Appointment list is empty!");
        else {
            int i = 1;
            for (Appointment appointment : appointmentArrayList) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/appointmentCard.fxml"));

                System.out.println("loaded ");
                try {
                    Node node = fxmlLoader.load();
                    AppointmentCardController appointmentCardController = fxmlLoader.getController();
                    appointmentCardController.preferredFrom.setText(appointment.getFromTime());
                    appointmentCardController.preferredTo.setText(appointment.getToTime());
                    appointmentCardController.ID.setText(appointment.getUserID());
                    appointmentCardController.memo.setText(appointment.getMemo());
                    appointmentCardController.appointmentTimestamp.setText(appointment.getTimestamp().toString());
                    appointmentCardController.appointmentID.setText(appointment.getAppointmentID());
                    appointmentContainer.getChildren().add(node);
                    ++i;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private FlowPane upcomingContainer ;
    public void setUpcomingList(){
        appointmentContainer.getChildren().clear();
        StaffMain.sendRequest(new UpcomingListRequest());
        System.out.println("requesting appointment list");
        UpcomingListResponse upcomingListResponse = (UpcomingListResponse) StaffMain.getResponse();
        System.out.println("got appointment list response");
        assert upcomingListResponse != null;
        System.out.println("response is "+ upcomingListResponse);
        ArrayList<Appointment> appointmentArrayList = upcomingListResponse.getAppointmentsList();
        if(appointmentArrayList.isEmpty()) System.out.println("Appointment list is empty!");
        else {
            int i = 1;
            for (Appointment appointment : appointmentArrayList) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/UpcomingController.fxml"));
                Image image = new Image("Server/images/sample.png", true);
                try {
                    Node node = fxmlLoader.load();
                    UpcomingController upcomingController = fxmlLoader.getController();
                    upcomingController.preferredFrom.setText(appointment.getFromTime());
                    upcomingController.preferredTo.setText(appointment.getToTime());
                    upcomingController.ID.setText(appointment.getUserID());
                    upcomingController.memo.setText(appointment.getMemo());
                    upcomingController.appointmentTimestamp.setText(appointment.getTimestamp().toString());
                    upcomingController.appointmentID.setText(appointment.getAppointmentID());
                    upcomingController.doctorName.setText(appointment.getDoctor().getDoctorName());
                    upcomingController.doctorName.setText(appointment.getDoctor().getDoctorType());
                    upcomingContainer.getChildren().add(node);
                    ++i;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}




