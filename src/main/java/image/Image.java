package image;

import java.awt.image.BufferedImage;

/**
 * Created by Adam Piech on 2017-08-30.
 */
public class Image {

    BufferedImage image;
    String name;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
