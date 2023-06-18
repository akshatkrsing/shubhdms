package Staff.request;

import Patient.request.Request;

import java.io.Serializable;
public class CancelAppointmentRequest extends Request implements Serializable {
    String appointmentID ;
    String userID;
    public CancelAppointmentRequest(String appointmentID, String userID){
        this.userID = userID;
        this.appointmentID = appointmentID;
    };

    public String getUserID() {
        return userID;
    }

    public String getAppointmentID() {
        return appointmentID;
    }
}
