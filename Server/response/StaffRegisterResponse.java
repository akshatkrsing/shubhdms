package Server.response;


public class StaffRegisterResponse extends Response {
    private String message;

    public StaffRegisterResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
