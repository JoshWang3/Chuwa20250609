package Coding.hw6;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicClass {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);

        int count = counter.incrementAndGet();
        System.out.println("Count: " + counter.get());

        AtomicReference<String> str = new AtomicReference<>();
        String old = str.getAndSet("new String");
        System.out.println("Old: " + old + " New: " + str.get());

        boolean flag = str.compareAndSet("new string", "updated string");
        System.out.println("Reference: " + flag + " Updated: " + str.get());
    }

    // Use cases: When multiple threads need to update a primitive data type or a reference,
    // use atomic classes to ensure atomicity.
}
