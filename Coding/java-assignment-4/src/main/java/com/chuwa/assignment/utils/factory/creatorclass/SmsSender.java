package com.chuwa.assignment.utils.factory.creatorclass;

import com.chuwa.assignment.utils.factory.NotificationInterface;
import com.chuwa.assignment.utils.factory.factoryclass.SmsNotification;

public class SmsSender extends MessageSender {
    @Override protected NotificationInterface createNotification() {
        return new SmsNotification();
    }
}