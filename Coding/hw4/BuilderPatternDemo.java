// Product class
class Computer {
    private String processor;
    private String memory;
    private String storage;
    private String graphics;
    private boolean hasWifi;
    private boolean hasBluetooth;
    private String operatingSystem;
    
    private Computer(ComputerBuilder builder) {
        this.processor = builder.processor;
        this.memory = builder.memory;
        this.storage = builder.storage;
        this.graphics = builder.graphics;
        this.hasWifi = builder.hasWifi;
        this.hasBluetooth = builder.hasBluetooth;
        this.operatingSystem = builder.operatingSystem;
    }
    
    @Override
    public String toString() {
        return "Computer{" +
                "processor='" + processor + '\'' +
                ", memory='" + memory + '\'' +
                ", storage='" + storage + '\'' +
                ", graphics='" + graphics + '\'' +
                ", hasWifi=" + hasWifi +
                ", hasBluetooth=" + hasBluetooth +
                ", operatingSystem='" + operatingSystem + '\'' +
                '}';
    }
    
    public void displaySpecs() {
        System.out.println("Computer Specifications:");
        System.out.println("- Processor: " + processor);
        System.out.println("- Memory: " + memory);
        System.out.println("- Storage: " + storage);
        System.out.println("- Graphics: " + graphics);
        System.out.println("- WiFi: " + (hasWifi ? "Yes" : "No"));
        System.out.println("- Bluetooth: " + (hasBluetooth ? "Yes" : "No"));
        System.out.println("- OS: " + operatingSystem);
        System.out.println();
    }
    
    // Builder class
    public static class ComputerBuilder {
        private String processor;
        private String memory;
        private String storage;
        private String graphics;
        private boolean hasWifi;
        private boolean hasBluetooth;
        private String operatingSystem;
        
        public ComputerBuilder setProcessor(String processor) {
            this.processor = processor;
            return this;
        }
        
        public ComputerBuilder setMemory(String memory) {
            this.memory = memory;
            return this;
        }
        
        public ComputerBuilder setStorage(String storage) {
            this.storage = storage;
            return this;
        }
        
        public ComputerBuilder setGraphics(String graphics) {
            this.graphics = graphics;
            return this;
        }
        
        public ComputerBuilder setWifi(boolean hasWifi) {
            this.hasWifi = hasWifi;
            return this;
        }
        
        public ComputerBuilder setBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }
        
        public ComputerBuilder setOperatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }
        
        public Computer build() {
            return new Computer(this);
        }
    }
}

public class BuilderPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Demo ===");
        
        // Gaming PC
        Computer gamingPC = new Computer.ComputerBuilder()
                .setProcessor("Intel i9-13900K")
                .setMemory("32GB DDR5")
                .setStorage("2TB NVMe SSD")
                .setGraphics("RTX 4080")
                .setWifi(true)
                .setBluetooth(true)
                .setOperatingSystem("Windows 11")
                .build();
        
        System.out.println("Gaming PC:");
        gamingPC.displaySpecs();
        
        // Office PC
        Computer officePC = new Computer.ComputerBuilder()
                .setProcessor("Intel i5-12400")
                .setMemory("16GB DDR4")
                .setStorage("512GB SSD")
                .setGraphics("Integrated")
                .setWifi(true)
                .setBluetooth(false)
                .setOperatingSystem("Windows 11 Pro")
                .build();
        
        System.out.println("Office PC:");
        officePC.displaySpecs();
        
        // Budget PC (minimal configuration)
        Computer budgetPC = new Computer.ComputerBuilder()
                .setProcessor("AMD Ryzen 3")
                .setMemory("8GB DDR4")
                .setStorage("256GB SSD")
                .setGraphics("Integrated")
                .setWifi(false)
                .setBluetooth(false)
                .setOperatingSystem("Linux Ubuntu")
                .build();
        
        System.out.println("Budget PC:");
        budgetPC.displaySpecs();
    }
} 