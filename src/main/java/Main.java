import services.Server;

/**
 * Created by Adam Piech on 2017-09-01.
 */

public class Main {

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.run();
        } catch (Exception e) {}
    }

}
