public class Computer {
    private final String cpu;
    private final String storage;
    private final int price;
    private final boolean hasGpu;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.storage = builder.storage;
        this.price = builder.price;
        this.hasGpu = builder.hasGpu;
    }

    public String getCpu() {
        return cpu;
    }

    public String getStorage() {
        return storage;
    }

    public int getPrice() {
        return price;
    }

    public boolean isHasGpu() {
        return hasGpu;
    }

    public static class Builder {
        private String cpu;
        private String storage;
        private int price;
        private boolean hasGpu;

        public Builder buildCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder buildStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder buildCpu(int price) {
            this.price = price;
            return this;
        }

        public Builder buildGpu(boolean hasGpu) {
            this.hasGpu = hasGpu;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

public class Builder {
    public static void main(String[] args) {
        Computer computer = new Computer.Builder()
                .buildCpu("9800x")
                .buildStorage("WD 500G")
                .buildCpu(1500)
                .buildGpu(true)
                .build();

        System.out.println("CPU: " + computer.getCpu() + ", Storage: " + computer.getStorage()
        + ", Price: " + computer.getPrice() + (computer.isHasGpu() ? ", has GPU" : "no GPU"));
    }
}