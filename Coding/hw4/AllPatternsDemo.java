/**
 * Comprehensive demonstration of all design patterns
 * This class runs all pattern demonstrations in sequence
 */
public class AllPatternsDemo {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("           DESIGN PATTERNS DEMONSTRATION");
        System.out.println("=".repeat(60));
        
        // 1. Singleton Pattern Demonstration
        demonstrateSingletonPatterns();
        
        // 2. Factory Method Pattern Demonstration
        demonstrateFactoryMethod();
        
        // 3. Abstract Factory Pattern Demonstration
        demonstrateAbstractFactory();
        
        // 4. Builder Pattern Demonstration
        demonstrateBuilderPattern();
        
        System.out.println("=".repeat(60));
        System.out.println("          ALL DEMONSTRATIONS COMPLETED");
        System.out.println("=".repeat(60));
    }
    
    private static void demonstrateSingletonPatterns() {
        System.out.println("\n1. SINGLETON PATTERN DEMONSTRATION");
        System.out.println("-".repeat(40));
        
        // Eager Singleton
        System.out.println("Eager Singleton:");
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();
        System.out.println("Same instance? " + (eager1 == eager2));
        eager1.performTask();
        
        System.out.println();
        
        // Lazy Singleton
        System.out.println("Lazy Singleton:");
        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();
        System.out.println("Same instance? " + (lazy1 == lazy2));
        lazy1.performTask();
    }
    
    private static void demonstrateFactoryMethod() {
        System.out.println("\n2. FACTORY METHOD PATTERN DEMONSTRATION");
        System.out.println("-".repeat(40));
        
        VehicleFactory[] factories = {
            new CarFactory(),
            new MotorcycleFactory(),
            new TruckFactory()
        };
        
        for (VehicleFactory factory : factories) {
            factory.operateVehicle();
            System.out.println();
        }
    }
    
    private static void demonstrateAbstractFactory() {
        System.out.println("\n3. ABSTRACT FACTORY PATTERN DEMONSTRATION");
        System.out.println("-".repeat(40));
        
        VehicleComponentFactory[] factories = {
            new CarComponentFactory(),
            new TruckComponentFactory(),
            new SportsCarComponentFactory()
        };
        
        String[] vehicleTypes = {"Car", "Truck", "Sports Car"};
        
        for (int i = 0; i < factories.length; i++) {
            System.out.printf("--- %s Assembly ---\n", vehicleTypes[i]);
            VehicleAssembler assembler = new VehicleAssembler(factories[i]);
            assembler.assembleVehicle();
        }
    }
    
    private static void demonstrateBuilderPattern() {
        System.out.println("\n4. BUILDER PATTERN DEMONSTRATION");
        System.out.println("-".repeat(40));
        
        // Different computer configurations
        Computer[] computers = {
            new Computer.ComputerBuilder()
                .setProcessor("Intel i9-13900K")
                .setMemory("32GB DDR5")
                .setStorage("2TB NVMe SSD")
                .setGraphics("RTX 4080")
                .setWifi(true)
                .setBluetooth(true)
                .setOperatingSystem("Windows 11")
                .build(),
                
            new Computer.ComputerBuilder()
                .setProcessor("Intel i5-12400")
                .setMemory("16GB DDR4")
                .setStorage("512GB SSD")
                .setGraphics("Integrated")
                .setWifi(true)
                .setBluetooth(false)
                .setOperatingSystem("Windows 11 Pro")
                .build()
        };
        
        String[] computerTypes = {"Gaming PC", "Office PC"};
        
        for (int i = 0; i < computers.length; i++) {
            System.out.println(computerTypes[i] + ":");
            computers[i].displaySpecs();
        }
    }
} 