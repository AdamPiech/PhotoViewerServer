package image;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

/**
 * Created by Adam Piech on 2017-08-30.
 */
public class ImageProcessing {

    public static BufferedImage scale(BufferedImage image, double factor) {
        BufferedImage createdImage = null;
        if (image != null) {
            int newWidth = (int) (image.getWidth() * factor);
            int newHeight = (int) (image.getHeight() * factor);
            createdImage = new BufferedImage(newWidth, newHeight, image.getType());
            Graphics2D graphics = createdImage.createGraphics();
            AffineTransform transformation = AffineTransform.getScaleInstance(factor, factor);
            graphics.drawRenderedImage(image, transformation);
        }
        return createdImage;
    }

    public static BufferedImage leftRotation(BufferedImage image) {
        return rotate(image, AffineTransform
                .getRotateInstance(Math.toRadians(90), image.getHeight() / 2.0, image.getHeight() / 2.0));
    }

    public static BufferedImage rightRotation(BufferedImage image) {
        return rotate(image, AffineTransform
                .getRotateInstance(Math.toRadians(-90), image.getWidth() / 2.0, image.getWidth() / 2.0));
    }

    private static BufferedImage rotate(BufferedImage image, AffineTransform transformation) {
        BufferedImage createdImage = null;
        if (image != null) {
            createdImage = new BufferedImage(image.getHeight(), image.getWidth(), image.getType());
            Graphics2D graphics = createdImage.createGraphics();
            graphics.drawRenderedImage(image, transformation);
        }
        return createdImage;
    }

    public static BufferedImage getImageInGrayScale(BufferedImage image) {
        ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp convertOption = new ColorConvertOp(colorSpace, null);
        return convertOption.filter(image, null);
    }

    public static BufferedImage getImageInSepia(BufferedImage image) {
        for (int indexX = 0; indexX < image.getWidth(); indexX++) {
            for (int indexY = 0; indexY < image.getHeight(); indexY++) {
                image.setRGB(indexX, indexY, convertColorToSepia(image.getRGB(indexX, indexY)));
            }
        }
        return image;
    }

    private static int convertColorToSepia(int RGB) {
        Color color = new Color(RGB);

        int red = (int) Math.min(255.0, color.getRed() * .393 + color.getGreen() *.769 + color.getBlue() * .189);
        int green = (int) Math.min(255.0, color.getRed() * .349 + color.getGreen() *.686 + color.getBlue() * .168);
        int blue = (int) Math.min(255.0, color.getRed() * .272 + color.getGreen() *.534 + color.getBlue() * .131);

        return new Color(red, green, blue).getRGB();
    }

}