package Server.requestHandler;

import Server.entity.Appointment;
import Server.response.StaffAppointmentListResponse;
import Server.table.AppointmentTable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffAppointmentListRequestHandler extends RequestHandler{
    Connection connection;
    ObjectOutputStream oos;

    public StaffAppointmentListRequestHandler(Connection connection, ObjectOutputStream oos) {
        this.connection = connection;
        this.oos = oos;
    }

    @Override
    public void sendResponse(String userID) {
        ArrayList<Appointment> appointmentArrayList=new ArrayList<>();
        ResultSet resultSet;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(AppointmentTable.QUERY_FETCH_PENDING);

            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("Result set is not empty!");
                    appointmentArrayList.add(
                            new Appointment(
                                    resultSet.getString(AppointmentTable.COLUMN_APPOINTMENT_MEMO),
                                    resultSet.getTimestamp(AppointmentTable.COLUMN_APPOINTMENT_TIMESTAMP),
                                    resultSet.getString(AppointmentTable.COLUMN_APPOINTMENT_FROM_TIME),
                                    resultSet.getString(AppointmentTable.COLUMN_APPOINTMENT_TO_TIME),
                                    resultSet.getString(AppointmentTable.COLUMN_REGISTRATION_NUMBER),
                                    resultSet.getString(AppointmentTable.COLUMN_APPOINTMENT_ID)
                            )
                            
                    );
                System.out.println(appointmentArrayList);
            }
            oos.writeObject(new StaffAppointmentListResponse(appointmentArrayList));
            oos.flush();
        }
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
