package com.chuwa.assignment.defaultStatic;

public class SmsService implements MessageService {
    @Override
    public String getMessage(String name){
        return "SMS sent to: "+ name;
    }
    // Inherits default greet() from the interface (not override)
}
