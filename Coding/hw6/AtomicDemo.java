package hw6;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        System.out.println(atomicInteger.getAndIncrement()); // 1
        System.out.println(atomicInteger.incrementAndGet()); // 3
        System.out.println(atomicInteger.addAndGet(3)); // 6
        System.out.println(atomicInteger.get()); // 6
        System.out.println(atomicInteger.getAndSet(100)); // 6
        System.out.println(atomicInteger.get()); // 100

    }
}