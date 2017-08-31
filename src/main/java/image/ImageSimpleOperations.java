package image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Adam Piech on 2017-08-30.
 */
public class ImageSimpleOperations {

    public static BufferedImage openImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException ioException) {
            return null;
        }
    }

    public static boolean saveImage(BufferedImage image, String filePath) {
        try {
            ImageIO.write(image,"png", new File(filePath));
        } catch (IOException ioException) {
            return false;
        }
        return true;
    }

}
