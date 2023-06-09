package Server.requestHandler;

import Server.request.ManageAppointmentRequest;

import java.io.ObjectOutputStream;
import java.sql.Connection;

public class ManageAppointmentRequestHandler extends RequestHandler{
    private ObjectOutputStream oos;
    private ManageAppointmentRequest manageAppointmentRequest;
    private Connection connection;

    public ManageAppointmentRequestHandler(Connection connection,ObjectOutputStream oos, ManageAppointmentRequest manageAppointmentRequest){
        this.oos = oos;
        this.manageAppointmentRequest = manageAppointmentRequest;
        this.connection = connection;
    }
    @Override
    public void sendResponse(String userID) {
        int result = 0;
//        try {
//            PreparedStatement preparedStatement=connection.prepareStatement(AppointmentTable.QUERY_INSERT_APPOINTMENT);
//            preparedStatement.setInt(1,Integer.parseInt(userID));
//            preparedStatement.setString(2,bookAppointmentRequest.getFromTime());
//            preparedStatement.setString(3, bookAppointmentRequest.getToTime());
//            preparedStatement.setString(4,bookAppointmentRequest.getMemo());
//            result=preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            if(result==0)oos.writeObject(new BookAppointmentResponse("Failure"));
//            else oos.writeObject(new BookAppointmentResponse("Successful"));
//            oos.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}

