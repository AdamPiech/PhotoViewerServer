package utils.serverOperations;

import java.awt.image.BufferedImage;

import static image.ImageProcessing.*;
import static image.ImageSimpleOperations.*;
import static java.io.File.separator;

/**
 * Created by Adam Piech on 2017-09-10.
 */
public class ImageGrayScaleOperation implements IOperation {

    @Override
    public void execute(String imageName, String imagePath) {
        BufferedImage image = openImage(imagePath + separator + imageName);
        getImageInGrayScale(image);
        removeImage(imagePath + separator + imageName);
    }

}
