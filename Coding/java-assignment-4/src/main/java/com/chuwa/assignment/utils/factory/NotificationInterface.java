package com.chuwa.assignment.utils.factory;

import com.chuwa.assignment.pojos.Message;

// Product Interface
//  Represents the abstraction of the object we want to create
//  Interface is ideal here because we expect multiple concrete
//  Notification types that implement shared behavior.
public interface NotificationInterface {
    void send(Message message);
}
