package anonymous;

public class AnonymousTest {
    public static void main(String[] args) {
        Vehicle bike = new Vehicle() {
            @Override
            public void run() {
                System.out.println("Bike is running using anonymous class.");
            }
        };

        bike.run();
    }
}
