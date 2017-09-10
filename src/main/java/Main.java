import queue.SQS;

import java.util.Random;

/**
 * Created by Adam Piech on 2017-09-01.
 */

public class Main {

    public static void main(String[] args) {
        Random random = new Random();

        SQS sqs = new SQS();
        sqs.sendQueueMessage("DUPA " + random.nextInt());
        sqs.receiveQueueMessage();
    }

}
