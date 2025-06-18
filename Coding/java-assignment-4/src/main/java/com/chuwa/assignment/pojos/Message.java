package com.chuwa.assignment.pojos;

public class Message {
    private final String receipent;
    private final String content;

    public Message(String receipent, String content) {
        this.receipent = receipent;
        this.content = content;
    }
    public String getReceipent() {
        return receipent;
    }
    public String getContent() {
        return content;
    }
    @Override
    public String toString() {
        return "Message [receipent=" + receipent + ", content=" + content + "]";
    }
}
