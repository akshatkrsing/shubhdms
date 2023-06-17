package Staff.main;


import Staff.controller.StaffLoginController;
import Staff.controller.StaffProfileScreenController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


import Server.response.Response;
import Staff.request.Request;

public class StaffMain extends Application {
    public static Socket socket=null;
    public static ObjectInputStream ois=null;
    public static ObjectOutputStream oos=null;
    public static String staffID;
    public static String staffFullName = "CAFE BABE";
    public static StaffProfileScreenController staffprofileScreenController = null;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Application invoked!");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/StaffLogin.fxml"));
        System.out.println("Login FXML Loaded!");
        try {

            System.out.println("Creating a new connection");
            socket = new Socket("localhost", 6970);
            System.out.println(socket);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connection established and io streams created");

            System.out.println(Thread.currentThread());
        } catch (IOException e) {
            System.out.println("socket connect negative!");
            e.printStackTrace();
        }

        primaryStage.setTitle("Sign In");
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
            StaffLoginController login = fxmlLoader.getController();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        primaryStage.show();
    }

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
