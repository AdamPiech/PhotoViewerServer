package utils;

/**
 * Created by Adam Piech on 2017-09-04.
 */
public class Util {

    public static final String SQS_NAME = "PhotoViewerSQS";
    public static final String SQS_URL = "https://sqs.us-west-2.amazonaws.com/211653061305/PhotoViewerSQS";

    public static final String BUCKET_NAME = "photoviewerstore";
    public static final String BUCKET_IMAGES_FOLDER = "";

    public static final String IMAGE_DIRECTORY_PATH = "/home/ubuntu/images/";

    public static final String IMAGE_SCALE_SQS_MESSAGE = "IMAGE-SCALE";
    public static final String IMAGE_LEFT_ROTATION_SQS_MESSAGE = "IMAGE-LEFT-ROTATION";
    public static final String IMAGE_RIGHT_ROTATION_SQS_MESSAGE = "IMAGE-RIGHT-ROTATION";
    public static final String GRAY_SCALE_IMAGE_SQS_MESSAGE = "GRAY-SCALE-IMAGE";
    public static final String SEPIA_IMAGE_SQS_MESSAGE = "SEPIA-IMAGE";
    public static final String REMOVE_IMAGE_SQS_MESSAGE = "REMOVE-IMAGE";

    public static final String SQS_MESSAGE_SEPARATOR = ": ";

}
