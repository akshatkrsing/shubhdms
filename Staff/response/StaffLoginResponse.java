package Staff.response;

public class StaffLoginResponse extends Response {
    private final String firstName,lastName,emailID;
    private final int registrationNo;

    public StaffLoginResponse(String firstName, String lastName, String emailID, int registrationNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailID = emailID;
        this.registrationNo = registrationNo;
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

    public int getRegistrationNo() {
        return registrationNo;
    }
}
