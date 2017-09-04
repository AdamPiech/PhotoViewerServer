import storage.S3Store;

/**
 * Created by Adam Piech on 2017-09-01.
 */

public class Main {

    public static void main(String[] args) {
        for (String s : S3Store.listObjects()) {
            System.out.println(" ========================= " + s);
        }
    }

}
