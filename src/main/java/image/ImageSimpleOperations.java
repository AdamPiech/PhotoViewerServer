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
            ioException.printStackTrace();
            return null;
        }
    }

    public static boolean saveImage(BufferedImage image, String filePath, String extension) {
        try {
            ImageIO.write(image, extension, new File(filePath));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return false;
        }
        return true;
    }

    public static void createDirectory(String dirPath) {
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

}
