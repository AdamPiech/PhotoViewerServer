package utils.serverOperations;

import static image.ImageSimpleOperations.removeImage;
import static java.io.File.separator;
import static storage.S3Store.*;

/**
 * Created by Adam Piech on 2017-09-10.
 */
public class RemoveImageOperation implements IOperation {

    @Override
    public void execute(String imageName, String imagePath) {
        deleteObject(imageName);
        removeImage(imagePath + separator + imageName);
    }

}
