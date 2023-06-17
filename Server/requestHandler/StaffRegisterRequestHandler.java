package Server.requestHandler;


import Server.response.StaffRegisterResponse;
import Server.table.PatientTable;
import Staff.request.StaffRegisterRequest;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StaffRegisterRequestHandler extends RequestHandler {
    private StaffRegisterRequest staffRegisterRequest;
    private ObjectOutputStream oos;
    private Connection connection;

    public StaffRegisterRequestHandler(StaffRegisterRequest staffRegisterRequest, ObjectOutputStream oos, Connection connection) {
        this.staffRegisterRequest = staffRegisterRequest;
        this.oos = oos;
        this.connection = connection;
    }
    @Override
    public void sendResponse(String userID) {
        PreparedStatement preparedStatement;
        System.out.println("Registration process invoked !");
        File file=new File("Server/images/sample.png");
        FileInputStream fis;
        int result = 0;
        try {
            preparedStatement=connection.prepareStatement(StaffTable.QUERY_REGISTER);
            preparedStatement.setString(1,staffRegisterRequest.getUsername());
            preparedStatement.setString(2,staffRegisterRequest.getFirstName());
            preparedStatement.setString(3,staffRegisterRequest.getLastName());
            preparedStatement.setString(4,staffRegisterRequest.getEmailID());
            preparedStatement.setString(5,staffRegisterRequest.getPassword());
            fis=new FileInputStream(file);
            preparedStatement.setBinaryStream(6,fis);
            System.out.println(preparedStatement);
            result=preparedStatement.executeUpdate();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
        if(result==0) {
            try {
                oos.writeObject(new StaffRegisterResponse(""));
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                oos.writeObject(new StaffRegisterResponse("Registered successfully"));
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}