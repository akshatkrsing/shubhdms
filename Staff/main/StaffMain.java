package Staff.main;

import Patient.request.Request;
import Staff.controller.StaffLoginController;
import Staff.controller.ProfileScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class StaffMain extends Application {
//    private static String staffId = "";
//    private static String staffName = "";
//    public static ObjectOutputStream outputStream;
//    public static ObjectInputStream inputStream;
//    public static VBox chatVBox = null;
//    public static String lastOpenCourseId = null;
//    public static final String myColor = "#f55f78";
//    public static final String otherColor = "#bee2f7";
//    public static Object tempHolder = null;

    public static Socket socket=null;
    public static ObjectInputStream ois=null;
    public static ObjectOutputStream oos=null;
    public static String staffID;
    public static String staffFullName = "CAFE BABE";
    public static ProfileScreenController profileScreenController = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Application invoked!");
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../fxml/Login.fxml"));
        System.out.println("Login FXML Loaded!");
        try {
            System.out.println("Creating a new connection");
            socket=new Socket("localhost",6969);
            System.out.println(socket);
            oos=new ObjectOutputStream(socket.getOutputStream());
            ois=new ObjectInputStream(socket.getInputStream());
            System.out.println("Connection established and io streams created");

            System.out.println(Thread.currentThread());
        } catch (IOException e) {
            System.out.println("socket connect negative!");
            e.printStackTrace();
        }

        primaryStage.setTitle("Sign In");
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
            StaffLoginController login=fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.show();
    }

//    private void connectToServer() {
//        try {
//            Socket socket = new Socket("localhost", 6969);
//            outputStream = new ObjectOutputStream(socket.getOutputStream());
//            inputStream = new ObjectInputStream(socket.getInputStream());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public static String getStaffId() {
//        return staffId;
//    }
//
//    public static String getstaffName() {
//        return staffName;
//    }
//
//    public static void setTeacherId(String id) {
//        if(staffId.equals("") || id.equals("")) staffId = id;
//    }
//
//    public static void setTeacherName(String teacherName) {
//        StaffMain.staffName = staffName;
//    }

    public static void sendRequest(Request request){
        try {
            oos.writeObject(request);
            oos.flush();
            System.out.println("Request sent to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getResponse(){
        try {
            System.out.println("response is sent on Main method");
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}