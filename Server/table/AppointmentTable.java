package Server.table;


public class AppointmentTable {
    public static final String TABLE_NAME="appointments";
    public static final String COLUMN_REGISTRATION_NUMBER="registrationNo";
    public static final String COLUMN_DOC_NAME = "doctorName";
    public static final String COLUMN_DOC_TYPE = "doctorType";
    public static final String COLUMN_IMAGE_BLOB = "image";
    public static final String COLUMN_APPOINTMENT_MEMO="memo";
    public static final String COLUMN_APPOINTMENT_TIMESTAMP = "timestamp";
    public static final String COLUMN_APPOINTMENT_FROM_TIME= "fromTime";
    public static final String COLUMN_APPOINTMENT_TO_TIME = "toTime";
    public static final String QUERY_FETCH_APPOINTMENTS = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_REGISTRATION_NUMBER+" =?;";


}
