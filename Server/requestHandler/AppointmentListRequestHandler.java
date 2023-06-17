package Server.requestHandler;


import Server.entity.Appointment;
import Server.response.AppointmentListResponse;
import Server.table.AppointmentTable;
import Server.table.DoctorTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentListRequestHandler extends RequestHandler{
    Connection connection;
    ObjectOutputStream oos;

    public AppointmentListRequestHandler(Connection connection, ObjectOutputStream oos) {
        this.connection = connection;
        this.oos = oos;
    }
    @Override
    public void sendResponse(String userID){
        ArrayList<Appointment> appointmentArrayList=new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(AppointmentTable.QUERY_FETCH_APPOINTMENTS);
            preparedStatement.setInt(1,Integer.parseInt(userID));
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("Result set is not empty!");
                if(resultSet.getString(DoctorTable.COLUMN_DOC_ID)==null){
                    appointmentArrayList.add(
                            new Appointment(
                                    resultSet.getString(AppointmentTable.COLUMN_APPOINTMENT_MEMO),
                                    resultSet.getTimestamp(AppointmentTable.COLUMN_APPOINTMENT_TIMESTAMP),
                                    resultSet.getString(AppointmentTable.COLUMN_APPOINTMENT_FROM_TIME),
                                    resultSet.getString(AppointmentTable.COLUMN_APPOINTMENT_TO_TIME)
                            )
                    );
                }
                else
                appointmentArrayList.add(
                        new Appointment(
                                resultSet.getString(DoctorTable.COLUMN_DOC_NAME),
                                resultSet.getString(DoctorTable.COLUMN_DOC_TYPE),
                                resultSet.getString(AppointmentTable.COLUMN_APPOINTMENT_MEMO),
                                resultSet.getBlob(DoctorTable.COLUMN_IMAGE_BLOB),
                                resultSet.getTimestamp(AppointmentTable.COLUMN_APPOINTMENT_TIMESTAMP),
                                resultSet.getString(AppointmentTable.COLUMN_APPOINTMENT_FROM_TIME),
                                resultSet.getString(AppointmentTable.COLUMN_APPOINTMENT_TO_TIME)
                        )
                );
            }
            oos.writeObject(new AppointmentListResponse(appointmentArrayList));
            oos.flush();
        }
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
