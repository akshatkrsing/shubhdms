package Server.request;

public class StaffLoginRequest extends Request {
    private String username;
    private String password;


    public StaffLoginRequest(String username, String password) {
        this.password=password;
        this.username=username;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
