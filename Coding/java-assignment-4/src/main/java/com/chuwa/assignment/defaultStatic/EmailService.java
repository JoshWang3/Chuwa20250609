package com.chuwa.assignment.defaultStatic;

public class EmailService implements MessageService {
    @Override
    public String getMessage(String name){
        return "Email sent to: " + name;
    }

    @Override
    public void greet(String name){
        System.out.println("[Email Greet] Hi " + name +", welcome back!");
    }
}
