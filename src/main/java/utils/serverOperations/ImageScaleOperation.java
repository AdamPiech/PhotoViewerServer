package utils.serverOperations;

import java.awt.image.BufferedImage;

import static image.ImageProcessing.*;
import static image.ImageSimpleOperations.*;
import static java.io.File.separator;

/**
 * Created by Adam Piech on 2017-09-10.
 */
public class ImageScaleOperation implements IOperation {

    @Override
    public void execute(String imageName, String imagePath) {
        BufferedImage image = openImage(imagePath + separator + imageName);
        scale(image, 0.5);
        removeImage(imagePath + separator + imageName);
    }

}
