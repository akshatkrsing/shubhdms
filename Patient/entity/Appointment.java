package Patient.entity;

import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Appointment implements Serializable {

    private Doctor doctor;
    private String memo;
    private Timestamp timestamp;
    private String status = "Pending";
    private Timestamp fromTime;
    private Timestamp toTime;

    public Appointment(String doctorName, String doctorType, String memo, Blob blob, Timestamp timestamp,Timestamp fromTime,Timestamp toTime) {
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

    public void setStatus(String status) {
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

    public String getStatus() {
        return status;
    }

    public void setFromTime(Timestamp fromTime) {
        this.fromTime = fromTime;
    }

    public void setToTime(Timestamp toTime) {
        this.toTime = toTime;
    }

    public Timestamp getFromTime() {
        return fromTime;
    }

    public Timestamp getToTime() {
        return toTime;
    }
}
