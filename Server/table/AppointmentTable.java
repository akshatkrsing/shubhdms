package Server.table;


public class AppointmentTable {
    public static final String TABLE_NAME="appointments";
    public static final String COlUMN_APPOINTMENT_ID="appointmentID";

    public static final String COLUMN_REGISTRATION_NUMBER="registrationNo";
    public static final String COLUMN_APPOINTMENT_MEMO="memo";
    public static final String COLUMN_APPOINTMENT_STATUS="appointmentStatus";
    public static final String COLUMN_APPOINTMENT_TIMESTAMP = "appointmentDate";
//    public static final String COLUMN_APPOINTMENT_DATE = "confirmationTime";
    public static final String COLUMN_APPOINTMENT_FROM_TIME= "fromTime";
    public static final String COLUMN_APPOINTMENT_TO_TIME = "toTime";


    public static final String QUERY_FETCH_APPOINTMENTS = "SELECT appointments.memo, appointments.appointmentDate, appointments.fromTime,"+
            "appointments.toTime, doctor.doctorName,doctor.doctorType, doctor.doctorImage FROM "+TABLE_NAME+
            "INNER JOIN doctor ON appointments.doctorID=doctor.doctorID WHERE appointments.registrationNo=?;";
    public static final String QUERY_INSERT_APPOINTMENT = "INSERT INTO "+TABLE_NAME+"("+COLUMN_REGISTRATION_NUMBER+","+COLUMN_APPOINTMENT_FROM_TIME+","+COLUMN_APPOINTMENT_TO_TIME +","+COLUMN_APPOINTMENT_MEMO+","+COLUMN_APPOINTMENT_STATUS+","+COLUMN_APPOINTMENT_TIMESTAMP+")VALUES(?,?,?,?,?,CURRENT_TIMESTAMP)";

}
