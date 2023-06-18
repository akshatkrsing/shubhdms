package Server.response;

public class ApproveAppointmentResponse extends Response{
    private String response;
    public ApproveAppointmentResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
