package Server.requestHandler;

import Server.response.ApproveAppointmentResponse;
import Server.table.AppointmentTable;
import Staff.request.ApproveAppointmentRequest;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ApproveAppointmentRequestHandler extends RequestHandler{
    private ObjectOutputStream oos;
    private ApproveAppointmentRequest approveAppointmentRequest;
    private Connection connection;
    public ApproveAppointmentRequestHandler(Connection connection,ObjectOutputStream oos, ApproveAppointmentRequest approveAppointmentRequest){
        this.oos = oos;
        this.approveAppointmentRequest = approveAppointmentRequest;
        this.connection = connection;
    }
    @Override
    public void sendResponse(String userID) {
        int result = 0;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(AppointmentTable.QUERY_APPROVE_APPOINTMENT);
            preparedStatement.setString(1,approveAppointmentRequest.getFromTime());
            preparedStatement.setString(2, approveAppointmentRequest.getToTime());
            preparedStatement.setInt(3,Integer.parseInt(approveAppointmentRequest.getDoctorID()));
            preparedStatement.setString(4, approveAppointmentRequest.getUserID());
            preparedStatement.setString(5, approveAppointmentRequest.getAppoitnmentID());
            result=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(result==0)oos.writeObject(new ApproveAppointmentResponse("Failure"));
            else oos.writeObject(new ApproveAppointmentResponse("Successful"));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
