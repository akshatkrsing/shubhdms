package Server.response;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class GetProfilePicResponse extends Response implements Serializable {
    private ImageIcon imageIcon;
//    private BufferedImage bufferedImage;
    public GetProfilePicResponse(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

//
//    public GetProfilePicResponse(BufferedImage bufferedImage) {
//        this.bufferedImage = bufferedImage;
//    }

//    public BufferedImage getBufferedImage() {
//        return bufferedImage;
//    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
}
