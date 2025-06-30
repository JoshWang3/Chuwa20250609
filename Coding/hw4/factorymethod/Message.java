package hw4.factorymethod;

/**
 * Example POJO
 */

public class Message {
    private String content;
    private String recipient;

    public Message(String content, String recipient) {
        this.content = content;
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", recipient='" + recipient + '\'' +
                '}';
    }
}
