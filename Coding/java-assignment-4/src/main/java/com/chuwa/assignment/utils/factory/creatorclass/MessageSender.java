package com.chuwa.assignment.utils.factory.creatorclass;

import com.chuwa.assignment.pojos.Message;
import com.chuwa.assignment.utils.factory.NotificationInterface;

// Creator class: defines the factory method and a shared message sending workflow
public abstract class MessageSender {
    // Factory Method - overriden by subclasses to return a concrete NotificationInterface
    protected abstract NotificationInterface createNotification();

    // Shared high-level logic: how we send
    public void notifyUser(Message msg){
        NotificationInterface notification = createNotification();
        notification.send(msg);
    }
}
