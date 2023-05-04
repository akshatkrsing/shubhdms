package Patient.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class BulletinController implements Initializable {

    @FXML
    Label bulletinTopic;
    @FXML
    Label bulletinCaption;
    @FXML
    ImageView bulletinImage;
    @FXML
    Button viewCaption;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onViewCaption(javafx.event.ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(null,bulletinCaption.getText());
    }
}
