package com.chuwa.assignment.utils.factory;

import com.chuwa.assignment.pojos.Message;
import com.chuwa.assignment.utils.factory.creatorclass.EmailSender;
import com.chuwa.assignment.utils.factory.creatorclass.MessageSender;
import com.chuwa.assignment.utils.factory.creatorclass.PushSender;
import com.chuwa.assignment.utils.factory.creatorclass.SmsSender;
// This demo shows how the FactoryMethod Pattern allows subclasses to determine which object
// to instantiate at runtime.
public class FactoryMethod {
    public static void main(String[] args) {
        System.out.println("\n=== Factory Method Pattern ===\n");

        Message emailMsg = new Message("angela@gmail.com", "Hi Huang, Thank you...");
        Message smsMsg = new Message("+1-400-000-0000", "Sender code:0000");
        Message pushMsg = new Message("user123", "Black Friday Sale...");

        // Each sender defines its product by overriding createNotification()
        MessageSender emailSender = new EmailSender();
        MessageSender smsSender = new SmsSender();
        MessageSender pushSender = new PushSender();

        emailSender.notifyUser(emailMsg);
        smsSender.notifyUser(smsMsg);
        pushSender.notifyUser(pushMsg);
    }

    // Explanation
    // Why Notification is an interface?
    // We expect many types of notifications (email, SMS, push...)
    // Interfaces allow multiple implementations and maximize flexibility
    //
    // Why MessageSender is an abstract class?
    // It contains reusable logic (notifyUser) that we don't want to repeat
    // Abstract classes allow share behavior + extensible factory method
    // If we used an interface here, we'd have to implement notifyUser in every subclass
    //
    // Benefits:
    // - Follows Open/Closed Principle (easy to add new notification types)
    // - Decouples creation logic from usage logic
    // - Make workflow reusable, allowing flexible creation

}
