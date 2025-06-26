import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        // Increment and get value
        System.out.println("Incremented Value: " + atomicInteger.incrementAndGet());

        // Add and get value
        System.out.println("Added Value: " + atomicInteger.addAndGet(5));

        // Compare and set
        boolean isUpdated = atomicInteger.compareAndSet(6, 10);
        System.out.println("Was value updated? " + isUpdated);
        System.out.println("Current Value: " + atomicInteger.get());
    }
}
