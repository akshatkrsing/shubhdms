package Staff.controller;



import Staff.request.StaffLoginRequest;
import Staff.main.StaffMain;
import Server.response.StaffLoginResponse;
import Staff.util.GuiUtil;
import Staff.util.HashUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StaffLoginController implements Initializable{

    @FXML
    public AnchorPane loginPane;
    @FXML
    public Button loginButton;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public Label signinLabel;
    @FXML
    public Hyperlink signupLink;

    @Override
    public void initialize(URL location, ResourceBundle resources){

    }
    public void login(ActionEvent actionEvent){
        StaffLoginRequest request = new StaffLoginRequest(usernameField.getText(), HashUtil.getMd5(passwordField.getText()));
        StaffMain.sendRequest(request);
        StaffLoginResponse response = (StaffLoginResponse) StaffMain.getResponse();
        if (response ==null)
            GuiUtil.alert(Alert.AlertType.ERROR,"Incorrect Information.Please try again.");
        else{
            StaffMain.staffID = String.valueOf(response.getStaffID());

            FXMLLoader homepageLoader= new FXMLLoader(getClass().getResource("../fxml/StaffProfileScreen.fxml"));
            Stage currentStage=(Stage)loginButton.getScene().getWindow();
            Scene scene=null;
            try {
                scene=new Scene(homepageLoader.load(), 682, 580.6667);
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentStage.setScene(scene);
            currentStage.setResizable(false);
            currentStage.setTitle("Welcome");
            currentStage.setOnCloseRequest(event -> {
                try {
                    StaffMain.oos.close();
                    StaffMain.ois.close();
                    StaffMain.oos = null;
                    StaffMain.ois = null;
                } catch (IOException e) {
                    StaffMain.oos = null;
                    StaffMain.ois = null;
                    e.printStackTrace();
                }
            });
            StaffMain.staffFullName = response.getFirstName() + " " + response.getLastName();
            StaffProfileScreenController staffProfileScreenController=homepageLoader.getController();
            staffProfileScreenController.first(response.getFirstName()+" "+response.getLastName());
            //profileScreenController.beforeAppointmentPage();

        }

    }



    public void switchToSignup(ActionEvent actionEvent) {
        FXMLLoader registerLoader=new FXMLLoader(getClass().getResource("../fxml/StaffRegister.fxml"));
        Scene scene=null;
        Stage stage=(Stage)signupLink.getScene().getWindow();
        try {
            scene=new Scene(registerLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        StaffRegisterController registerController=registerLoader.getController();
        registerController.first();
        stage.setTitle("Sign Up");
    }

}