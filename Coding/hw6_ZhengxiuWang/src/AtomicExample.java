import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);

        // Increment and get new value
        System.out.println("After increment: " + counter.incrementAndGet());

        // Get current value and then increment
        System.out.println("Before increment: " + counter.getAndIncrement());

        // Add 5 and get result
        System.out.println("After adding 5: " + counter.addAndGet(5));

        // Set to a new value
        counter.set(10);
        System.out.println("Set to 10: " + counter.get());

        // Compare and set
        boolean updated = counter.compareAndSet(10, 20);
        System.out.println("Compare and set success? " + updated);
        System.out.println("Current value: " + counter.get());
    }
}
