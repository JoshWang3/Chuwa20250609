// Q1DesignPattern.java - Demo for Singleton, Factory, Abstract Factory, and Builder Patterns

// Lazy Singleton (Thread-safe with double-checked locking)
class LazySingleton {
    private static volatile LazySingleton instance;
    private String config = "PRODUCTION";

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    public String getConfig() {
        return config;
    }
}

// Eager Singleton
class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return instance;
    }

    public void log(String msg) {
        System.out.println("Log: " + msg);
    }
}

// Factory Method Pattern
interface Message {
    void send(String msg);
}

class EmailMessage implements Message {
    public void send(String msg) {
        System.out.println("Email sent: " + msg);
    }
}

class SmsMessage implements Message {
    public void send(String msg) {
        System.out.println("SMS sent: " + msg);
    }
}

class MessageFactory {
    public static Message getMessage(String type) {
        if ("email".equalsIgnoreCase(type)) return new EmailMessage();
        if ("sms".equalsIgnoreCase(type)) return new SmsMessage();
        throw new IllegalArgumentException("Unknown type: " + type);
    }
}

// Abstract Factory Pattern
interface Button {
    void render();
}

interface ThemeFactory {
    Button createButton();
}

class LightButton implements Button {
    public void render() {
        System.out.println("Rendering Light Button");
    }
}

class DarkButton implements Button {
    public void render() {
        System.out.println("Rendering Dark Button");
    }
}

class LightFactory implements ThemeFactory {
    public Button createButton() {
        return new LightButton();
    }
}

class DarkFactory implements ThemeFactory {
    public Button createButton() {
        return new DarkButton();
    }
}

// Builder Pattern
class Computer {
    private String cpu;
    private String ram;
    private String storage;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
    }

    public void printSpecs() {
        System.out.println("CPU: " + cpu);
        System.out.println("RAM: " + ram);
        System.out.println("Storage: " + storage);
    }

    static class Builder {
        private String cpu;
        private String ram;
        private String storage;

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setRam(String ram) {
            this.ram = ram;
            return this;
        }

        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

// Q1DesignPattern Demo
public class Q1DesignPattern {
    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern ===");
        LazySingleton lazy = LazySingleton.getInstance();
        System.out.println("LazySingleton config: " + lazy.getConfig());

        EagerSingleton eager = EagerSingleton.getInstance();
        System.out.println("EagerSingleton config: " + eager);
        eager.log("App started");

        System.out.println("\n=== Factory Method Pattern ===");
        Message email = MessageFactory.getMessage("email");
        email.send("Welcome via Email");

        Message sms = MessageFactory.getMessage("sms");
        sms.send("Welcome via SMS");

        System.out.println("\n=== Abstract Factory Pattern ===");
        ThemeFactory factory = new DarkFactory(); // Try LightFactory too
        Button button = factory.createButton();
        button.render();

        System.out.println("\n=== Builder Pattern ===");
        Computer myPc = new Computer.Builder()
                .setCpu("Intel i9")
                .setRam("32GB")
                .setStorage("1TB SSD")
                .build();
        myPc.printSpecs();
    }
}
