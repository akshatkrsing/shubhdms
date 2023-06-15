package Staff.controller;



import Patient.entity.Main;
import Patient.util.HashUtil;
import Server.request.RegisterRequest;
import Staff.response.RegisterResponse;

import com.sun.net.httpserver.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

public class StaffRegisterController {
    @FXML
    public AnchorPane registerPane;
    @FXML
    public Button registerButton;
    @FXML
    public Hyperlink loginLink;
    @FXML
    public Label signupLabel;
    @FXML
    public TextField firstNameField;
    @FXML
    public TextField lastNameField;
    @FXML
    public TextField staffIDField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public PasswordField confirmPasswordField;
    @FXML
    public TextField emailIDField;
    @FXML
    public Label matchLabel;

    public boolean check;
    public void first(){
        firstNameField.setTextFormatter(new TextFormatter<>(c -> c.getControlNewText().matches(".{0,15}") ? c : null));
        lastNameField.setTextFormatter(new TextFormatter<>(c -> c.getControlNewText().matches(".{0,15}") ? c : null));
        emailIDField.setTextFormatter(new TextFormatter<>(c -> c.getControlNewText().matches(".{0,50}") ? c : null));
        staffIDField.setTextFormatter(new TextFormatter<>(c -> c.getControlNewText().matches(".{0,8}") ? c : null));
    }
    public void switchToLogin(ActionEvent actionEvent) {
        FXMLLoader loginLoader=new FXMLLoader(getClass().getResource("../fxml/StaffLogin.fxml"));
        Stage stage=(Stage)loginLink.getScene().getWindow();
        Scene scene=null;
        try {
            scene=new Scene(loginLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.setTitle("Login");
    }

    public void checkIfMatches(ActionEvent actionEvent) {
        if(passwordField.getText().equals(confirmPasswordField.getText())){
            check=true;
            matchLabel.setText("Passwords Match");
        }
        else {
            check=false;
            matchLabel.setText("Passwords don't match");
        }
    }

    public void register(ActionEvent actionEvent) {
        FXMLLoader loginLoader=new FXMLLoader(getClass().getResource("../fxml/StaffLogin.fxml"));
        if(passwordField.getText().equals(confirmPasswordField.getText())){
            RegisterRequest registerRequest=new RegisterRequest(firstNameField.getText(),lastNameField.getText(),emailIDField.getText(),
                    HashUtil.getMd5(passwordField.getText()),staffIDField.getText());
            Main.sendRequest((Request) registerRequest);
            System.out.println("Register request sent");
            RegisterResponse response=(RegisterResponse)Main.getResponse();
            assert response != null;
            if(response.getMessage().length()==0) System.out.println("Please Try Again");
            else {
                Stage stage=(Stage)registerButton.getScene().getWindow();
                Scene scene=null;
                try {
                    scene=new Scene(loginLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setTitle("Login");
                stage.setScene(scene);
            }
        }
        else System.out.println("Please enter correct info");
    }
}
