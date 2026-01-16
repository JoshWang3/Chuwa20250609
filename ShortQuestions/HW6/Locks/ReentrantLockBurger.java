package Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockBurger {
    private int burgerCount = 0;
    private final Lock lock = new ReentrantLock();

    public void addBurger() {
        lock.lock();
        try {
            burgerCount++;
            System.out.println(Thread.currentThread().getName() + " added a burger. Total is: " + burgerCount);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockBurger counter = new ReentrantLockBurger();
        Runnable task = counter::addBurger;

        for (int i = 0; i < 5; i++) {
            new Thread(task, "SpongeBob-" + i).start();
        }
    }
}

/*
output:
SpongeBob-0 added a burger. Total is: 1
SpongeBob-1 added a burger. Total is: 2
SpongeBob-2 added a burger. Total is: 3
SpongeBob-3 added a burger. Total is: 4
SpongeBob-4 added a burger. Total is: 5

 */
