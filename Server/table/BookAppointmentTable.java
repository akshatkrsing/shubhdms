package Server.table;

public class BookAppointmentTable {
    public static final String TABLE_NAME="bookings";
    public static final String COLUMN_REGISTRATION_NUMBER="registrationNo";
    public static final String COLUMN_PREFERRED_FROM_TIME = "preferredFromTime";
    public static final String COLUMN_PREFERRED_TO_TIME= "preferredToTime";
    public static final String COLUMN_MEMO = "memo";

    public static final String QUERY_INSERT_APPOINTMENT = "INSERT INTO "+TABLE_NAME+"("+COLUMN_REGISTRATION_NUMBER+","+COLUMN_PREFERRED_FROM_TIME+","+COLUMN_PREFERRED_TO_TIME+","+COLUMN_MEMO+")VALUES(?,?,?,?)";
}
