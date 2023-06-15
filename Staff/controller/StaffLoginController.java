package Staff.controller;

import Patient.util.GuiUtil;
import Patient.util.HashUtil;
import Staff.main.StaffMain;
import Staff.request.StaffLoginRequest;
import Staff.response.StaffLoginResponse;
import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.stage.Stage;
        import com.example.dispensary_management_system.staff.util.ChatUtil;
        import com.example.dispensary_management_system.staff.util.GuiUtil;
        import com.example.dispensary_management_system.staff.main.StaffMain;
        import com.example.dispensary_management_system.staff.request.StaffLoginRequest;
        import com.example.dispensary_management_system.staff.response.StaffLoginResponse;
        import com.example.dispensary_management_system.staff.util.HashUtil;;

        import java.io.IOException;
        import java.io.InputStream;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import java.net.Socket;
        import java.net.URL;
        import java.util.ResourceBundle;

public class StaffLoginController implements Initializable {
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
        StaffLoginRequest request=new StaffLoginRequest(usernameField.getText(), HashUtil.getMd5(passwordField.getText()));
        StaffMain.sendRequest(request);
        StaffLoginResponse response= (StaffLoginResponse) StaffMain.receiveResponse();
        if(response==null)
            GuiUtil.alert(Alert.AlertType.ERROR,"Incorrect Information.Please try again.");
        else {
            Main.setTeacherId(response.getTeacherID());
            Main.setTeacherName(response.getFirstName() + " " + response.getLastName());
            startMessageThread();
            FXMLLoader homepageLoader= new FXMLLoader(getClass().getResource("../views/TeacherHomeView.fxml"));
            Stage currentStage=(Stage)loginButton.getScene().getWindow();

            Scene scene=null;
            try {
                scene=new Scene(homepageLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentStage.setScene(scene);
            currentStage.setMaximized(true);
            currentStage.setResizable(false);
            currentStage.setTitle("Welcome");
            TeacherHomeController controller = homepageLoader.getController();
            controller.callFirst();
        }
    }

    private void startMessageThread() {
        Socket chatSocket;
        ObjectInputStream chatois = null;
        try {
            chatSocket = new Socket("localhost",6970);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(chatSocket.getOutputStream());
            objectOutputStream.writeObject(StaffMain.getStaffId());
            objectOutputStream.flush();
            InputStream is = chatSocket.getInputStream();
            chatois=new ObjectInputStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread t=new Thread(new ChatUtil(chatois));
        t.start();
    }

    public void switchToSignup(ActionEvent actionEvent) {
        FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("../views/TeacherRegisterView.fxml"));
        Scene scene = null;
        Stage stage = (Stage) signupLink.getScene().getWindow();
        try {
            scene = new Scene(registerLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        TeacherRegisterController teacherRegisterController=registerLoader.getController();
        teacherRegisterController.first();
        stage.setScene(scene);
        stage.setTitle("Sign Up");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
