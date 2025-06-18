package com.chuwa.assignment.defaultStatic;

public class defaultStatic {
    public static void main(String[] args) {
        MessageService email = new EmailService();
        MessageService sms = new SmsService();

        System.out.println(email.getMessage("Angela"));
        email.greet("Angela");
        System.out.println(sms.getMessage("Bob"));
        sms.greet("Bob");

        // Static method - called directly on the interface
        System.out.println("Is Angela a valid name? " + MessageService.isValidName("Angela"));
        System.out.println("Is Bob a valid name? " + MessageService.isValidName("Bob"));
    }
}
// Explanation
// • `default` methods:
//      - Can be overridden by implementing classes
//      - Provide shared logic with options to customize
//      - Allow interface to evolve without breaking existing code
// • `static` methods:
//      - Belong to the interface, not instances.
//      - Used for utility or validation logic.
//      - Must be called like: MessageService.isValidName(...)