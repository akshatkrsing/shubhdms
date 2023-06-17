package Server.response;



public class StaffLoginResponse extends Response {
    private final String firstName,lastName,emailID;
    private final int staffID;

    public StaffLoginResponse(String firstName, String lastName, String emailID, int staffID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailID = emailID;
        this.staffID = staffID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailID() {
        return emailID;
    }

    public int getStaffID() {
        return staffID;
    }
}
