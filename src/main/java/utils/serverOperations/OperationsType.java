package utils.serverOperations;

import java.util.Map;
import java.util.HashMap;

import static utils.Util.*;

/**
 * Created by Adam Piech on 2017-09-10.
 */
public class OperationsType {

    Map<String, IOperation> operations;

    public OperationsType() {
        operations = new HashMap<>();
        fillOperationsMap();
    }

    private void fillOperationsMap() {
        operations.put(IMAGE_SCALE_SQS_MESSAGE, new ImageScaleOperation());
        operations.put(IMAGE_LEFT_ROTATION_SQS_MESSAGE, new ImageLeftRotationOperation());
        operations.put(IMAGE_RIGHT_ROTATION_SQS_MESSAGE, new ImageRightRotationOperation());
        operations.put(GRAY_SCALE_IMAGE_SQS_MESSAGE, new ImageGrayScaleOperation());
        operations.put(SEPIA_IMAGE_SQS_MESSAGE, new ImageSepiaOperation());
        operations.put(REMOVE_IMAGE_SQS_MESSAGE, new RemoveImageOperation());
    }

    public IOperation getOperation(String key) {
        return operations.get(key);
    }

}
