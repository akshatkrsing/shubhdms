package Staff.response;

import com.example.dispensary_management_system.Patient.response.Response;

public class RegisterResponse extends Response {
    private String message;

    public RegisterResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
