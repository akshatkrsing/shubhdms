package Staff.request;

import Patient.request.Request;

import java.io.Serializable;
import java.sql.Timestamp;

public class ApproveAppointmentRequest extends Request implements Serializable {
    String appoitnmentID ;
    String userID;
    String fromTime;
    String toTime;
    String doctorID;

    public ApproveAppointmentRequest(String appoitnmentID, String userID, String fromTime,String toTime, String doctorID ){
        this.userID = userID;
        this.appoitnmentID = appoitnmentID;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.doctorID = doctorID;
    }

    public String getUserID() {
        return userID;
    }

    public String getAppoitnmentID() {
        return appoitnmentID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getToTime() {
        return toTime;
    }

    public String getFromTime() {
        return fromTime;
    }
}
