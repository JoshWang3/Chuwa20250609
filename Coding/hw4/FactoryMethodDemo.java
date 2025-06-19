// Product interface
interface Vehicle {
    void start();
    void stop();
}

// Concrete products
class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car engine started");
    }
    
    @Override
    public void stop() {
        System.out.println("Car engine stopped");
    }
}

class Motorcycle implements Vehicle {
    @Override
    public void start() {
        System.out.println("Motorcycle engine started");
    }
    
    @Override
    public void stop() {
        System.out.println("Motorcycle engine stopped");
    }
}

class Truck implements Vehicle {
    @Override
    public void start() {
        System.out.println("Truck engine started");
    }
    
    @Override
    public void stop() {
        System.out.println("Truck engine stopped");
    }
}

// Creator abstract class
abstract class VehicleFactory {
    // Factory method
    public abstract Vehicle createVehicle();
    
    // Template method using factory method
    public void operateVehicle() {
        Vehicle vehicle = createVehicle();
        vehicle.start();
        System.out.println("Operating vehicle...");
        vehicle.stop();
    }
}

// Concrete creators
class CarFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        System.out.println("Creating a Car");
        return new Car();
    }
}

class MotorcycleFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        System.out.println("Creating a Motorcycle");
        return new Motorcycle();
    }
}

class TruckFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        System.out.println("Creating a Truck");
        return new Truck();
    }
}

public class FactoryMethodDemo {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Demo ===");
        
        VehicleFactory carFactory = new CarFactory();
        VehicleFactory motorcycleFactory = new MotorcycleFactory();
        VehicleFactory truckFactory = new TruckFactory();
        
        System.out.println("\n--- Car Factory ---");
        carFactory.operateVehicle();
        
        System.out.println("\n--- Motorcycle Factory ---");
        motorcycleFactory.operateVehicle();
        
        System.out.println("\n--- Truck Factory ---");
        truckFactory.operateVehicle();
    }
} 