import queue.SQS;

import java.util.Random;

/**
 * Created by Adam Piech on 2017-09-01.
 */

public class Main {

    public static void main(String[] args) {
        Random random = new Random();

        try {
            SQS sqs = new SQS();
            System.out.println("Sending");
            for (int index = 0; index > 10; index++) {
                sqs.sendQueueMessage("DUPA " + random.nextInt());
            }
            System.out.println("Receive");
            for (int index = 0; index > 10; index++) {
                System.out.println(sqs.receiveQueueMessages());
            }

        } catch (Exception e) {
        }

    }

}
