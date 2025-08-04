package Atomic;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);

        System.out.println("Initial: " + counter.get());

        counter.incrementAndGet(); // ++
        System.out.println("Increment: " + counter.get());

        counter.addAndGet(10); // +10
        System.out.println("Add and Get: " + counter.get());

        boolean updated = counter.compareAndSet(11, 20); // if 10 -> update to 20
        System.out.println("Compare and set (11 -> 20): " + updated);
        System.out.println("Final value: " + counter.get());
    }
}

/*
output:
Initial: 0
Increment: 1
Add and Get: 11
Compare and set (11 -> 20): true
Final value: 20
 */