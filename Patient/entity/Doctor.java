package Patient.entity;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Doctor implements Serializable {
    private String name;
    private String type;
    private int doctorId;
    private Image image;


    public Doctor(String name, String type, int doctorId, Image image){
        this.name = name;
        this.type = name;
        this.doctorId = doctorId;
        this.image = image;
    }

    public Doctor(String name, String type, int doctorId){
        this.name = name;
        this.type = name;
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public int getDoctorId(){
        return doctorId;
    }
    public Image getImage() {
        return image;
    }
}
