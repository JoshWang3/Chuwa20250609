package com.chuwa.assignment.utils.factory.factoryclass;

import com.chuwa.assignment.pojos.Message;
import com.chuwa.assignment.utils.factory.NotificationInterface;

public class SmsNotification implements NotificationInterface {
    @Override
    public void send(Message msg){
        System.out.println("SmsNotification sent: " + msg);
    }
}
