package hw4.builderpattern;


public class Computer {

    // Required
    private final String cpu;

    // Optional
    private final int ramGB;
    private final int storageGB;
    private final boolean hasGraphicsCard;

    // Private constructor
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ramGB = builder.ramGB;
        this.storageGB = builder.storageGB;
        this.hasGraphicsCard = builder.hasGraphicsCard;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ramGB=" + ramGB +
                ", storageGB=" + storageGB +
                ", hasGraphicsCard=" + hasGraphicsCard +
                '}';
    }

    // Builder inner class
    public static class Builder {
        private final String cpu; // required

        private int ramGB = 8;           // default
        private int storageGB = 256;     // default
        private boolean hasGraphicsCard = false;

        public Builder(String cpu) {
            this.cpu = cpu;
        }

       public Builder ramGB(int ramGB) {
            this.ramGB = ramGB;
            return this;
       }
       public Builder storageGB(int storageGB) {
            this.storageGB = storageGB;
            return this;
       }
       public Builder hasGraphicsCard(boolean hasGraphicsCard) {
            this.hasGraphicsCard = hasGraphicsCard;
            return this;
       }

        public Computer build() {
            return new Computer(this);
        }
    }

}



