import storage.S3Store;
import utils.Util;

/**
 * Created by Adam Piech on 2017-09-01.
 */

public class Main {

    public static void main(String[] args) {
        S3Store.putObjects(Util.IMAGE_DIRECTORY_PATH);
    }

}
