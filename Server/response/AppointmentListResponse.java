package Server.response;



import Server.entity.Appointment;
import java.io.Serializable;
import java.util.ArrayList;

public class AppointmentListResponse extends Response implements Serializable {
    private ArrayList<Appointment> appointmentsList;

    public AppointmentListResponse(ArrayList<Appointment> AppointmentsList) {
        this.appointmentsList = AppointmentsList;
    }

    public ArrayList<Appointment> getAppointmentsList() {
        return appointmentsList;
    }

}