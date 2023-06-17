package Server.requestHandler;

import Patient.request.LoginRequest;
import Server.response.LoginResponse;
import Server.table.PatientTable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRequestHandler extends RequestHandler{
    private ObjectOutputStream oos;
    private LoginRequest loginRequest;
    private Connection connection;
    private boolean loginSuccessful;

    public LoginRequestHandler(ObjectOutputStream oos, LoginRequest loginRequest, Connection connection) {
        this.oos = oos;
        this.loginRequest = loginRequest;
        this.connection = connection;
        loginSuccessful = false;
    }

    @Override
    public void sendResponse(String userID) {
        PreparedStatement preparedStatement;
        try{
            preparedStatement=connection.prepareStatement(PatientTable.QUERY_LOGIN);
            preparedStatement.setInt(1,Integer.parseInt(loginRequest.getUsername()));
            preparedStatement.setString(2,loginRequest.getPassword());
            System.out.println(loginRequest.getUsername() + " : " + loginRequest.getPassword());
            ResultSet resultSet=preparedStatement.executeQuery();
            LoginResponse response=null;
            if(resultSet.next()){
                response=new LoginResponse(resultSet.getString(PatientTable.COLUMN_FIRST_NAME),
                        resultSet.getString(PatientTable.COLUMN_LAST_NAME),resultSet.getString(PatientTable.COLUMN_EMAIL_ID),
                        resultSet.getInt(PatientTable.COLUMN_REGISTRATION_NUMBER));
                preparedStatement=connection.prepareStatement(PatientTable.QUERY_UPDATE_LAST_ACTIVE);
                preparedStatement.setString(1,loginRequest.getUsername());
                preparedStatement.execute();
                loginSuccessful = true;
            }
            try {
                oos.writeObject(response);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

}
