package Server.requestHandler;


import Server.response.CancelAppointmentResponse;
import Server.table.AppointmentTable;
import Staff.request.CancelAppointmentRequest;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CancelAppointmentRequestHandler extends RequestHandler{
    private ObjectOutputStream oos;
    private CancelAppointmentRequest cancelAppointmentRequest;
    private Connection connection;

    public CancelAppointmentRequestHandler(Connection connection,ObjectOutputStream oos, CancelAppointmentRequest cancelAppointmentRequest){
        this.oos = oos;
        this.cancelAppointmentRequest = cancelAppointmentRequest;
        this.connection = connection;
    }
    @Override
    public void sendResponse(String userID) {
        int result = 0;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(AppointmentTable.QUERY_CANCEL_APPOINTMENT);
            preparedStatement.setString(1,cancelAppointmentRequest.getUserID());
            preparedStatement.setString(2,cancelAppointmentRequest.getAppointmentID());
            result=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(result==0)oos.writeObject(new CancelAppointmentResponse("Failure"));
            else oos.writeObject(new CancelAppointmentResponse("Successful"));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
