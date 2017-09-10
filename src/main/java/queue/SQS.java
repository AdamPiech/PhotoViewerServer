package queue;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.List;

import static utils.Util.SQS_NAME;

/**
 * Created by Adam Piech on 2017-09-10.
 */
public class SQS {

    private AmazonSQS sqs;

    public SQS() {
        sqs = AmazonSQSClientBuilder.defaultClient();
    }

    public void sendQueueMessage(String message) {
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(getQueueURL())
                .withMessageBody(message);
        sqs.sendMessage(send_msg_request);
    }

    public List<String> receiveQueueMessage() {
        List<Message> messages = sqs.receiveMessage(getQueueURL()).getMessages();
        for (Message m : messages) {
            System.out.println(m.getBody());
            sqs.deleteMessage(getQueueURL(), m.getReceiptHandle());
        }
        return null;
    }

    public String getQueueURL() {
        return sqs.getQueueUrl(SQS_NAME).getQueueUrl();
    }
}
