package Server.entity;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Patient implements Serializable {
    private String patientName;
    private int patientID;
    private Image patientImage;

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPatientImage(Image patientImage) {
        this.patientImage = patientImage;
    }

    public int getPatientID() {
        return patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public Image getPatientImage() {
        return patientImage;
    }
}
