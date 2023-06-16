package Staff.main;

import Staff.request.Request;
import Staff.response.Response;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class StaffMain extends Application {
    private static String staffId = "";
    private static String staffName = "";
    public static ObjectOutputStream outputStream;
    public static ObjectInputStream inputStream;
    public static VBox chatVBox = null;
    public static String lastOpenCourseId = null;
    public static final String myColor = "#f55f78";
    public static final String otherColor = "#bee2f7";
    public static Object tempHolder = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(StaffMain.class.getResource("../fxml/StaffLogin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 590, 600);
//            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Teacher Login");
            primaryStage.setMinHeight(600);
            primaryStage.setMinWidth(590);
            primaryStage.setOnCloseRequest(event -> {
                try {
                    StaffMain.outputStream.close();
                    StaffMain.inputStream.close();
                    StaffMain.outputStream = null;
                    StaffMain.inputStream = null;
                } catch (IOException e) {
                    StaffMain.outputStream = null;
                    StaffMain.inputStream = null;
                    e.printStackTrace();
                }
            });
//            primaryStage.setAlwaysOnTop(true);
//            primaryStage.setMaximized(true);
//            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            primaryStage.show();
            connectToServer();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 6969);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getStaffId() {
        return staffId;
    }

    public static String getstaffName() {
        return staffName;
    }

    public static void setTeacherId(String id) {
        if(staffId.equals("") || id.equals("")) staffId = id;
    }

    public static void setTeacherName(String teacherName) {
        StaffMain.staffName = staffName;
    }

    public static void sendRequest(Request request) {
        try {
            outputStream.writeObject(request);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Response receiveResponse() {
        try {
            Object obj=inputStream.readObject();
            System.out.println("Response is " + obj);
            return (Response) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}