package Patient.entity;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Bulletin implements Serializable {

    private Image image;
    private String caption;
    private Timestamp timestamp;
    private String topic;
    public Bulletin(Blob image, String caption, Timestamp timestamp, String topic){
        try {
            InputStream in = image.getBinaryStream();
            final BufferedImage bufferedImage = ImageIO.read(in);
            this.image = SwingFXUtils.toFXImage(bufferedImage,null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.caption = caption;
        this.timestamp = timestamp;
        this.topic  = topic;
    }

    public Image getImage() {
        return image;
    }

    public String getCaption() {
        return caption;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getTopic() {
        return topic;
    }
}
