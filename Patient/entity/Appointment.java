package Patient.entity;

import Server.entity.Doctor;
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

    private Server.entity.Doctor doctor;
    private String memo;
    private Timestamp timestamp;
    private int status = 0;

    private String fromTime;
    private String toTime;

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
}
