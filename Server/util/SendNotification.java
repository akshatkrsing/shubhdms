package Server.util;

import com.example.dispensary_management_system.Server.main.Server;

import java.sql.Connection;
import java.sql.SQLException;

public class SendNotification implements Runnable {
    Connection connection;


    @Override
    public void run() {
        connection = Server.getConnection();
//        while (true) {
//            try {
//
//            } catch (InterruptedException | SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
