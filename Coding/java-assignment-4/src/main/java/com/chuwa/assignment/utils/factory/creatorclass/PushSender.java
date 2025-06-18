package com.chuwa.assignment.utils.factory.creatorclass;

import com.chuwa.assignment.utils.factory.NotificationInterface;
import com.chuwa.assignment.utils.factory.factoryclass.PushNotification;

public class PushSender extends MessageSender {
    //    Represents the abstraction of the object we want to create.
    //    Interfaces are ideal here because we expect multiple concrete
    //    Notification types that implement shared behavior.
    @Override protected NotificationInterface createNotification() {
        return new PushNotification();
    }
}