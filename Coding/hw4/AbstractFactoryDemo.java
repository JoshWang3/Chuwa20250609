// Abstract products
interface Engine {
    void start();
}

interface Wheel {
    void rotate();
}

// Concrete products for Car family
class CarEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Car engine starting with 4 cylinders");
    }
}

class CarWheel implements Wheel {
    @Override
    public void rotate() {
        System.out.println("Car wheel rotating smoothly");
    }
}

// Concrete products for Truck family
class TruckEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Truck engine starting with 8 cylinders");
    }
}

class TruckWheel implements Wheel {
    @Override
    public void rotate() {
        System.out.println("Truck wheel rotating heavily");
    }
}

// Concrete products for Sports Car family
class SportsCarEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Sports car engine starting with turbo boost");
    }
}

class SportsCarWheel implements Wheel {
    @Override
    public void rotate() {
        System.out.println("Sports car wheel rotating at high speed");
    }
}

// Abstract factory
interface VehicleComponentFactory {
    Engine createEngine();
    Wheel createWheel();
}

// Concrete factories
class CarComponentFactory implements VehicleComponentFactory {
    @Override
    public Engine createEngine() {
        return new CarEngine();
    }
    
    @Override
    public Wheel createWheel() {
        return new CarWheel();
    }
}

class TruckComponentFactory implements VehicleComponentFactory {
    @Override
    public Engine createEngine() {
        return new TruckEngine();
    }
    
    @Override
    public Wheel createWheel() {
        return new TruckWheel();
    }
}

class SportsCarComponentFactory implements VehicleComponentFactory {
    @Override
    public Engine createEngine() {
        return new SportsCarEngine();
    }
    
    @Override
    public Wheel createWheel() {
        return new SportsCarWheel();
    }
}

// Client class
class VehicleAssembler {
    private Engine engine;
    private Wheel wheel;
    
    public VehicleAssembler(VehicleComponentFactory factory) {
        this.engine = factory.createEngine();
        this.wheel = factory.createWheel();
    }
    
    public void assembleVehicle() {
        System.out.println("Assembling vehicle...");
        engine.start();
        wheel.rotate();
        System.out.println("Vehicle assembled successfully\n");
    }
}

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        System.out.println("=== Abstract Factory Pattern Demo ===");
        
        // Create different vehicle assemblers using different factories
        System.out.println("--- Car Assembly ---");
        VehicleAssembler carAssembler = new VehicleAssembler(new CarComponentFactory());
        carAssembler.assembleVehicle();
        
        System.out.println("--- Truck Assembly ---");
        VehicleAssembler truckAssembler = new VehicleAssembler(new TruckComponentFactory());
        truckAssembler.assembleVehicle();
        
        System.out.println("--- Sports Car Assembly ---");
        VehicleAssembler sportsCarAssembler = new VehicleAssembler(new SportsCarComponentFactory());
        sportsCarAssembler.assembleVehicle();
    }
} 