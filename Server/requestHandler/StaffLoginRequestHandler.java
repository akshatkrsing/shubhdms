package Server.requestHandler;

import Staff.request.StaffLoginRequest;

import java.io.ObjectOutputStream;
import java.sql.Connection;

public class StaffLoginRequestHandler extends RequestHandler{
    private ObjectOutputStream oos;
    private StaffLoginRequest staffLoginRequest;
    private Connection connection;
    private boolean loginSuccessful;

    public StaffLoginRequestHandler(ObjectOutputStream oos, StaffLoginRequest staffloginRequest, Connection connection) {
        this.oos = oos;
        this.staffLoginRequest = staffloginRequest;
        this.connection = connection;
        loginSuccessful = false;
    }

    @Override
    public void sendResponse(String userID) {

    }
}
