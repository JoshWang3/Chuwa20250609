package hw4.factorymethod;

public class NotificationService {
    public static void main(String[] args) {

        NotificationFactory notificationFactory = new EmailNotificationFactory();

        // use the factory to create a notification sender
        Notification notification = notificationFactory.createNotification();

        // create a message
        Message msg = new Message("Your order has been shipped!", "user@example.com");

        // send the notification
        notification.send(msg.getContent());
    }
}
