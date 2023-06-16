package Staff.response;

public class ManageAppointmentResponse extends Response {
    private String response = "Failure";
    public ManageAppointmentResponse(String response){
        this.response =response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

}
