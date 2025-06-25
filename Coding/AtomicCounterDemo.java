package Coding;
import java.util.concurrent.atomic.AtomicInteger;


public class AtomicCounterDemo {

    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet(); // Thread-safe increment
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count: " + counter.get()); // Expected: 2000
    }
}

//Atomic classes are thread-safe classes that support atomic (indivisible) operations on variables without needing synchronization (synchronized or locks).
//They use low-level CPU instructions (CAS - Compare-And-Swap) to ensure thread safety without blocking.

//Use them when:
//You want lock-free, thread-safe access to shared variables.
//You're working with high-concurrency environments (e.g., counters, flags).
//You want better performance than synchronized.