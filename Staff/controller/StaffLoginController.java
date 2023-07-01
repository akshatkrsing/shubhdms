package Staff.controller;

import Patient.request.LoginRequest;
import Server.response.LoginResponse;
import Staff.main.StaffMain;
import Staff.util.GuiUtil;
import Staff.util.HashUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StaffLoginController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
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

    public void login(ActionEvent actionEvent) {
        LoginRequest request = new LoginRequest(usernameField.getText(), HashUtil.getMd5(passwordField.getText()));
        StaffMain.sendRequest(request);
        LoginResponse response = (LoginResponse) StaffMain.getResponse();
        if (response ==null)
            GuiUtil.alert(Alert.AlertType.ERROR,"Incorrect Information.Please try again.");
        else{
            StaffMain.staffID = String.valueOf(response.getRegistrationNo());

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
            ProfileScreenController profileScreenController=homepageLoader.getController();
            profileScreenController.first(response.getFirstName()+" "+response.getLastName());
        }
    }
    public void switchToSignup(ActionEvent actionEvent) {
        FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("../fxml/StaffRegister.fxml"));
        Scene scene = null;
        Stage stage = (Stage) signupLink.getScene().getWindow();
        try {
            scene = new Scene(registerLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        StaffRegisterController staffRegisterController=registerLoader.getController();
        staffRegisterController.first();
        stage.setScene(scene);
        stage.setTitle("Sign Up");
    }
}
