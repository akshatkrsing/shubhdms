package Server.main;


import Patient.request.*;
import Staff.request.StaffLoginRequest;
import Staff.request.StaffRegisterRequest;
import Server.requestHandler.*;
import Staff.request.ManageAppointmentRequest;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.net.ServerSocket;
import java.net.Socket;

public class RequestIdentifier implements Runnable{
    Socket socket;
    ObjectOutputStream oos=null;
    ObjectInputStream ois=null;
//    ServerSocket chatServerSocket;
    public String userID;
    public String staffID;

    /**
     * Constructor that takes multiple socket parameters and initialises the I/O streams accordingly
     * @param socket is used to create ObjectOutput/Input streams through which communication takes place
     *
//     * @param chatServerSocket used to create a chat connection

     */
//server chat socket disabled
    public RequestIdentifier(Socket socket
                             //,ServerSocket chatServerSocket
    ){
        this.socket=socket;
//        this.chatServerSocket = chatServerSocket;
        try {
            System.out.println("io streams instantiated in request identifier!");
            oos=new ObjectOutputStream(socket.getOutputStream());
            ois=new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Abstract method of the Runnable interface.
     * This method is called when the thread starts.
     * Listens for requests from the client, uses if else to categorise the request
     * Creates the respective requestHandler instance and calls the sendResponse method
     * sendResponse method processes the request and sends the appropriate response back to the client
     */

    @Override
    public void run() {
        System.out.println("New identifier thread started!");
        while(socket.isConnected()) {
            Object request;
            try {
                System.out.println("Receiving request...");
                request = Server.receiveRequest(ois);
                System.out.println("Finding instance of request!");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }

            if (request instanceof LoginRequest) {
                //When login request arrives we create an instance of the LoginRequestHandler,
                // pass the required parameters and call the sendResponse method.
                userID = ((LoginRequest) request).getUsername();
                LoginRequestHandler loginRequestHandler = new LoginRequestHandler(oos, (LoginRequest) request, Server.getConnection());
                loginRequestHandler.sendResponse(userID);

                //Once the login is successful we create the chat connection
//                if (loginRequestHandler.isLoginSuccessful()) {
//                    try {
//                        Socket chatSocket = chatServerSocket.accept();
//                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(chatSocket.getOutputStream());
//                        ObjectInputStream objectInputStream = new ObjectInputStream(chatSocket.getInputStream());

                        /****This Input is from the output stream from the message thread initiating method startMessageThread in Patient.LoginController****/
//                        String registrationNumber = (String) objectInputStream.readObject();

                        // After chat connection is created we maintain an ArrayList of the userID and their respective
                        // Output streams
//                        Server.socketArrayList.add(new RegistrationStreamWrapper(registrationNumber, objectOutputStream));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }

            } else if (request instanceof RegisterRequest) {
                System.out.println("Registration request being handled!");
                RegisterRequestHandler registerRequestHandler = new RegisterRequestHandler((RegisterRequest) request, oos, Server.getConnection());
                registerRequestHandler.sendResponse(userID);
            }else if (request instanceof StaffLoginRequest) {
                //When login request arrives we create an instance of the LoginRequestHandler,
                // pass the required parameters and call the sendResponse method.
                staffID = ((StaffLoginRequest) request).getUsername();
                StaffLoginRequestHandler staffLoginRequestHandler = new StaffLoginRequestHandler(oos, (StaffLoginRequest) request, Server.getConnection());
                staffLoginRequestHandler.sendResponse(staffID);
            } else if (request instanceof StaffRegisterRequest) {
                System.out.println("Registration request being handled!");
                StaffRegisterRequestHandler staffRegisterRequestHandler = new StaffRegisterRequestHandler((StaffRegisterRequest) request, oos, Server.getConnection());
                staffRegisterRequestHandler.sendResponse(userID);

            } else if(request instanceof GetNotificationRequest){
                GetNotificationRequestHandler getNotificationRequestHandler=new GetNotificationRequestHandler(Server.getConnection(), oos);
                getNotificationRequestHandler.sendResponse(userID);
            }
            else if( request instanceof GetProfilePicRequest){
                GetProfilePicRequestHandler getProfilePicRequestHandler=new GetProfilePicRequestHandler(Server.getConnection(),oos,(GetProfilePicRequest)request);
                getProfilePicRequestHandler.sendResponse(userID);
            }
            else if (request instanceof ChangeProfilePicRequest){
                ChangeProfilePicRequestHandler changeProfilePicRequestHandler=new ChangeProfilePicRequestHandler(Server.getConnection(),oos,(ChangeProfilePicRequest)request);
                changeProfilePicRequestHandler.sendResponse(userID);
            }
            else if(request instanceof ChangePasswordRequest){
                ChangePasswordRequestHandler changePasswordRequestHandler=new ChangePasswordRequestHandler(Server.getConnection(),oos,(ChangePasswordRequest)request);
                changePasswordRequestHandler.sendResponse(userID);
            }
            else if(request instanceof LogOutRequest){
                LogOutRequestHandler logOutRequestHandler=new LogOutRequestHandler(Server.getConnection(),oos);
                logOutRequestHandler.sendResponse(userID);
//                deleteChatSocketConnection();
            }
            else if(request instanceof AppointmentListRequest){
                System.out.println("Appointment list request being processed ...");
                AppointmentListRequestHandler appointmentListRequestHandler = new AppointmentListRequestHandler(Server.getConnection(),oos);
                appointmentListRequestHandler.sendResponse(userID);
            }
            else if(request instanceof BookAppointmentRequest){
                BookAppointmentRequestHandler bookAppointmentRequestHandler = new BookAppointmentRequestHandler(Server.getConnection(),oos,(BookAppointmentRequest) request);
                bookAppointmentRequestHandler.sendResponse(userID);
            }
            else if(request instanceof BulletinRequest){
                BulletinRequestHandler bulletinRequestHandler = new BulletinRequestHandler(Server.getConnection(),oos);
                bulletinRequestHandler.sendResponse(userID);
            }
            else if(request instanceof DutyChartRequest){
                DutyChartRequestHandler dutyChartRequestHandler =  new DutyChartRequestHandler(Server.getConnection(),oos);
                dutyChartRequestHandler.sendResponse(userID);
            }
//            else if(request instanceof ManageAppointmentRequest){
//                ManageAppointmentRequestHandler manageAppointmentRequestHandler = new ManageAppointmentRequestHandler(Server.getConnection(),oos,(ManageAppointmentRequest) request);
//                manageAppointmentRequestHandler.sendResponse(userID);
//            }
            else{
                System.out.println("No instance found");

            }

        }

        System.out.println("Should have broken");
//        deleteChatSocketConnection();
    }

//    private void deleteChatSocketConnection() {
//        //Remove the OOS after disconnection
//        System.out.println(userID + " disconnected");
//        Server.socketArrayList.removeIf(r -> {
//
//            /**Any of staff or patient by userId disconnects */
//            if(r.getRegistrationNumber().equals(userID)) {
//                try {
//                    System.out.println("Sending disconnected to their oos");
//                    r.getOos().writeObject("disconnected");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return true;
//            }
//            return false;
//        });
        /***delete chat for staff***/
//        Server.staffSocketArrayList.removeIf(r-> {
//            if(r.getStaffId().equals(userID)) {
//                try {
//                    System.out.println("Sending disconnected to their oos");
//                    r.getOos().writeObject("disconnected");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return true;
//            }
//            return false;
//        });
//    }
}
