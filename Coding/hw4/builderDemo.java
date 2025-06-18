package hw4;

public class builderDemo {
    private final String name;
    private final String type;
    private final String origin;

    private builderDemo(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.origin = builder.origin;
    }

    public void playSound() {
        System.out.println("Playing the " + name + " (" + type + ")");
    }

    public String toString() {
        return name + " | type: " + type + " | origin: " + origin;
    }

    // ✅ Builder inner class
    public static class Builder {
        private String name;
        private String type;
        private String origin = "Unknown";

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setOrigin(String origin) {
            this.origin = origin;
            return this;
        }

        public builderDemo build() {
            return new builderDemo(this);
        }
    }

    public static void main(String[] args) {
        builderDemo violin = new builderDemo.Builder()
                .setName("Violin")
                .setType("String")
                .setOrigin("Italy")
                .build();
        violin.playSound();
    }
}

// Basically using an inner class to build the object, the object is immutable