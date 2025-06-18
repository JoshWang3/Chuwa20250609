package com.chuwa.assignment.utils.factory.factoryclass;

import com.chuwa.assignment.pojos.Message;
import com.chuwa.assignment.utils.factory.NotificationInterface;

// Concrete Products – Email, SMS, Push implementations
public class EmailNotification implements NotificationInterface {
    @Override public void send(Message msg){
        System.out.println("EmailNotification sent: " + msg);
    }
}
