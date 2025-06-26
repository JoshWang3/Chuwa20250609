package hw4.builder;

public class Test {
    public static void main(String[] args) {
        // without Builder Pattern, it's complicated to create this complex computer object
        Computer computer1 = new Computer("Intel i9", "Nvidia RTX 4060", "16GB", "1TB SSD", true, true);
        // with Builder Pattern, easy to create, and more flexible for the optional fields
        Computer computer2 = new Computer.Builder("Intel i9", "16GB")
                .setStorage("1TB SSD")
                .setGpu("Nvidia RTX 4060")
                .setBluetooth(true)
                .setWifi(true)
                .build();
        System.out.println(computer2.toString());
    }
}
