package utils;

/**
 * Created by Adam Piech on 2017-09-10.
 */
public class Message {

    private String operation = null;
    private String fileName = null;

    public Message(String operation, String fileName) {
        this.operation = operation;
        this.fileName = fileName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
