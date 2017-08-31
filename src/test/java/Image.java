import image.ImageProcessing;
import image.ImageSimpleOperations;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.image.BufferedImage;

/**
 * Created by Adam Piech on 2017-08-30.
 */

public class Image {

    private BufferedImage image;

    @Before
    public void prepareImage() {
        image = ImageSimpleOperations.openImage("C:\\Users\\Adam Piech\\Desktop\\image.png");
    }

    @Test
    public void testResizeImage() {
        BufferedImage newImage = ImageProcessing.scale(image, 0.2);
        ImageSimpleOperations.saveImage(newImage, "C:\\Users\\Adam Piech\\Desktop\\resized_image.png");
    }

    @Test
    public void testLeftRotatedImage() {
        BufferedImage newImage = ImageProcessing.leftRotation(image);
        ImageSimpleOperations.saveImage(newImage, "C:\\Users\\Adam Piech\\Desktop\\left_rotated_image.png");
    }

    @Test
    public void testRightRotatedImage() {
        BufferedImage newImage = ImageProcessing.rightRotation(image);
        ImageSimpleOperations.saveImage(newImage, "C:\\Users\\Adam Piech\\Desktop\\right_rotated_image.png");
    }

    @Test
    public void testImageToGrayScale() {
        BufferedImage newImage = ImageProcessing.getImageInGrayScale(image);
        ImageSimpleOperations.saveImage(newImage, "C:\\Users\\Adam Piech\\Desktop\\gray_scale_image.png");
    }

    @Test
    public void testImageToSepia() {
        BufferedImage newImage = ImageProcessing.getImageInSepia(image);
        ImageSimpleOperations.saveImage(newImage, "C:\\Users\\Adam Piech\\Desktop\\sepia_image.png");
    }

}
