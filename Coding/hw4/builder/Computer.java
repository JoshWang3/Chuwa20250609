package hw4.builder;

public class Computer {
    private String cpu;
    private String gpu;
    private String ram;
    private String storage;
    private boolean bluetooth;
    private boolean wifi;

    // without Builder Pattern, we need this public contractor
    public Computer(String cpu, String gpu, String ram, String storage, boolean bluetooth, boolean wifi) {
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.storage = storage;
        this.bluetooth = bluetooth;
        this.wifi = wifi;
    }

    // private constructor - force users to use Builder
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.gpu = builder.gpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.bluetooth = builder.bluetooth;
        this.wifi = builder.wifi;
    }
    // static inner Builder class
    public static class Builder {
        private String cpu;
        private String gpu;
        private String ram;
        private String storage;
        private boolean bluetooth;
        private boolean wifi;
        // constructor with required fields
        public Builder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }
        // setter for optional fields
        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder setBluetooth(boolean bluetooth) {
            this.bluetooth = bluetooth;
            return this;
        }

        public Builder setWifi(boolean wifi) {
            this.wifi = wifi;
            return this;
        }

        public Builder setGpu(String gpu) {
            this.gpu = gpu;
            return this;
        }
        // build() method to create a computer
        public Computer build() {
            return new Computer(this);
        }
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", gpu='" + gpu + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", bluetooth=" + bluetooth +
                ", wifi=" + wifi +
                '}';
    }
}

