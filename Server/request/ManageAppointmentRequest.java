package Server.request;

import java.io.Serializable;

public class ManageAppointmentRequest implements Serializable {

String doctor;
    public ManageAppointmentRequest(String doctor) {
        this.doctor=doctor;
    }
}
