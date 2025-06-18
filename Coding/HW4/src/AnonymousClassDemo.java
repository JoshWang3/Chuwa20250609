abstract class Notification {
    abstract void send();

    void preview() {
        System.out.println("Previewing notification...");
    }
}

interface Clickable {
    void onClick();
}

class Logger {
    void log(String message) {
        System.out.println("Log: " + message);
    }
}

public class AnonymousClassDemo {
    public static void main(String[] args) {
        // 1. Abstract Class
        Notification email = new Notification() {
            @Override
            void send() {
                System.out.println("Sending Email Notification (Anonymous Class)");
            }
        };
        email.preview();
        email.send();

        // 2. Interface
        Clickable button = new Clickable() {
            @Override
            public void onClick() {
                System.out.println("Button clicked (Anonymous Class)");
            }
        };
        button.onClick();

        // 3. Concrete Class
        Logger customLogger = new Logger() {
            @Override
            void log(String message) {
                System.out.println("Custom Log >> " + message);
            }
        };
        customLogger.log("System initialized");
    }
}
