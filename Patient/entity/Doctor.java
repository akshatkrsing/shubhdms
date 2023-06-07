package Patient.entity;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Doctor implements Serializable {
    private int doctorID;
    private String doctorName;
    private String doctorType;
    private Image doctorImage;


    public Doctor(String name, String type,Image doctorImage, int doctorID){
        this.doctorName = name;
        this.doctorType = name;
        this.doctorImage= doctorImage;
        this.doctorID=doctorID;
    }
    public Doctor(String name, String type,Image doctorImage){
        this.doctorName = name;
        this.doctorType = name;
        this.doctorImage= doctorImage;
    }

    public Doctor(String doctorName , String doctorType){
        this.doctorName  =doctorName ;
        this.doctorType = doctorType;
    }

    public void setDoctorID(int doctorID){this.doctorID = doctorID;};
    public void setName(String doctorName ) {
        this.doctorName  = doctorName ;
    }
    public void setType(String doctorType){
        this.doctorType= doctorType;
    }

    public void setImage(Image doctorImage) {
        this.doctorImage = doctorImage;
    }

    public String getDoctorName() {
        return doctorName ;
    }
    public String getDoctorType() {
        return doctorType;
    }
    public Image getDoctorImage() {
        return doctorImage;
    }

    public int getDoctorID() {
        return doctorID;
    }
}
