package queue;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.List;
import java.util.stream.Collectors;

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
                .withMessageBody(message)
                .withDelaySeconds(1);
        sqs.sendMessage(send_msg_request);
    }

    public String receiveQueueMessages() {
        List<Message> messages = sqs.receiveMessage(getQueueURL()).getMessages();
        String message = null;
        for (Message m : messages) {
            message = m.getBody();
            sqs.deleteMessage(getQueueURL(), m.getReceiptHandle());
        }
        return message;
    }

    public String getQueueURL() {
        return sqs.getQueueUrl(SQS_NAME).getQueueUrl();
    }
}
