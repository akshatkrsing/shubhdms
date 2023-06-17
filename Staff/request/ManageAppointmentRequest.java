package Staff.request;

import Staff.request.Request;
import Staff.response.ManageAppointmentResponse;
import javafx.scene.control.ComboBox;

import java.time.LocalDate;

public class ManageAppointmentRequest extends Request {

String doctor;
    public ManageAppointmentRequest(String doctor) {
        this.doctor=doctor;
    }
}
