package Server.entity;

import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

public class Appointment implements Serializable {

    private Doctor doctor = new Doctor("", "", null);
    private String memo;
    private Timestamp timestamp;
    private int status = 0;

    private String fromTime;
    private String toTime;
    private String userID;
    private String appointmentID;

    public Appointment(String memo, Timestamp timestamp, String fromTime, String toTime){
        this.memo = memo;
        this.timestamp = timestamp;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
    public Appointment(String memo, Timestamp timestamp, String fromTime, String toTime,String userID, String appointmentID){
        this.memo = memo;
        this.timestamp = timestamp;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.userID =userID;
        this.appointmentID = appointmentID;
    }

    public Appointment(String doctorName, String doctorType, String memo, Blob blob, Timestamp timestamp,String fromTime,String toTime) {
        this.doctor.setName(doctorName);
        this.doctor.setType(doctorType);
        try {
            InputStream in = blob.getBinaryStream();
            final BufferedImage bufferedImage = ImageIO.read(in);
            this.doctor.setImage(SwingFXUtils.toFXImage(bufferedImage,null));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        this.memo = memo;
        this.timestamp = timestamp;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
    public Appointment(String doctorName, String doctorType, String memo, Blob blob, Timestamp timestamp,String fromTime,String toTime, String userID, String appointmentID) {
        this.doctor.setName(doctorName);
        this.doctor.setType(doctorType);
        try {
            InputStream in = blob.getBinaryStream();
            final BufferedImage bufferedImage = ImageIO.read(in);
            this.doctor.setImage(SwingFXUtils.toFXImage(bufferedImage,null));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        this.memo = memo;
        this.timestamp = timestamp;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.userID = userID;
        this.appointmentID = appointmentID;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getMemo() {
        return memo;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getFromTime() {
        return fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public String getUserID() {
        return userID;
    }

    public String getAppointmentID() {
        return appointmentID;
    }
}
