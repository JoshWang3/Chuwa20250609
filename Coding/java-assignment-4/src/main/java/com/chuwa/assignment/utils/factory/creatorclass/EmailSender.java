package com.chuwa.assignment.utils.factory.creatorclass;

import com.chuwa.assignment.utils.factory.NotificationInterface;
import com.chuwa.assignment.utils.factory.factoryclass.EmailNotification;
//    Concrete Creators – EmailSender, SmsSender, PushSender
//    Each subclass defines the product it wants to use.
public class EmailSender extends MessageSender{
    @Override
    protected NotificationInterface createNotification(){
        return new EmailNotification();
    }
}
