package Server.entity;

import javafx.scene.image.Image;
import java.io.Serializable;

public class Doctor implements Serializable {
    private String name;
    private String type;
    private Image image;


    public Doctor(String name, String type,Image image){
        this.name = name;
        this.type = name;
        this.image = image;
    }

    public Doctor(String name, String type,int doctorId){
        this.name = name;
        this.type = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type){
        this.type = type;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public Image getImage() {
        return image;
    }
}
