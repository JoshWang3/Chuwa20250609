package hw6;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) throws InterruptedException {
        // Atomic Class
        AtomicInteger atomicInteger = new AtomicInteger();
        // 任务：自增 10000 次
        Runnable mr = () -> {
            for (int i = 0; i < 10000; i++) {
                atomicInteger.incrementAndGet();    // this is an atomic operation
            }
        };

        ArrayList<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(mr);
            t.start();
            ts.add(t);
        }

        for (Thread t : ts) {
            t.join();
        }
        // the output should be always 50000, because it's an atomic operation
        System.out.println("number = " + atomicInteger.get());
    }
}
