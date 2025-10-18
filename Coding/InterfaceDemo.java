interface Vehicle {
    default void start() {
        System.out.println("Vehicle is starting...");
    }

    static void showType() {
        System.out.println("Vehicle type: Generic");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car engine started!");
        
    }
    static void showType() {
        System.out.println("Vehicle type: Car");
    }
}

public class InterfaceDemo implements Vehicle{
    public static void main(String[] args) {
        Vehicle v = new Car();
        v.start();            // calls overridden default
        // static method call via interface
        InterfaceDemo.showType();
        Vehicle.showType();
        Car.showType();
    }
        static void showType() {
        System.out.println("Vehicle type: InterfaceDemo");
    }
}
