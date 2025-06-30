package hw4.builderpattern;

public class BuilderPatternDemo {
    public static void main(String[] args) {
        Computer officePC = new Computer.Builder("Intel 15")
                .ramGB(16)
                .storageGB(512)
                .build();

        Computer gamingPC = new Computer.Builder("AMD Ryzen 9")
                .ramGB(32)
                .storageGB(1024)
                .hasGraphicsCard(true)
                .build();

        System.out.println(officePC);
        System.out.println(gamingPC);
    }
}
