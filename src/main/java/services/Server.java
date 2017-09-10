package services;

import image.ImageSimpleOperations;
import queue.SQS;
import storage.S3Store;
import utils.Message;
import utils.serverOperations.IOperation;
import utils.serverOperations.OperationsType;

import static image.ImageSimpleOperations.createDirectory;
import static image.ImageSimpleOperations.removeImage;
import static storage.S3Store.getObject;
import static utils.Util.*;

/**
 * Created by Adam Piech on 2017-09-10.
 */
public class Server implements Runnable {

    private final static OperationsType OPERATION_TYPE = new OperationsType();
    private SQS sqs;

    public Server() {
        sqs = new SQS();
    }

    @Override
    public void run() {
        Message message = sqs.receiveQueueMessages();
        executeImageOperation(message);
    }

    private void executeImageOperation(Message message) {
        IOperation operation = OPERATION_TYPE.getOperation(message.getOperation());
        createDirectory(IMAGE_DIRECTORY_PATH);
        getObject(message.getFileName(), IMAGE_DIRECTORY_PATH);
        operation.execute(message.getFileName(), IMAGE_DIRECTORY_PATH);
        if (!message.getOperation().equals(REMOVE_IMAGE_SQS_MESSAGE)) {
            S3Store.putObject(message.getFileName(), IMAGE_DIRECTORY_PATH);
        }
        removeImage(message.getFileName(), IMAGE_DIRECTORY_PATH);
    }

}
