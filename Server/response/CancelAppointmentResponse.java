package Server.response;

public class CancelAppointmentResponse extends Response{
    private String response;
    public CancelAppointmentResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
