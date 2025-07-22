package Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockBurger {
    private String menu = "Beef Burger, Chicken Burger";
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    public void readMenu() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reads menu:  " + menu);
        } finally {
            readLock.unlock();
        }
    }


    public void updateMenu(String newMenu) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " updating menu...  ");
            menu = newMenu;
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockBurger menu = new ReadWriteLockBurger();
        Runnable reader = menu::readMenu;
        Runnable writer = () -> menu.updateMenu("Fish Burger, Vegan Burger");

        for (int i = 0; i < 2; i++) {
            new Thread(reader, "Customer-").start();
        }
        new Thread(writer, "SpongeBob-").start();

        try {
            Thread.sleep(1000); // waiting for updating menu
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 2; i < 4; i++) {
            new Thread(reader, "Customer-").start();
        }
    }
}

/*
output:
Customer- reads menu:  Beef Burger, Chicken Burger
Customer- reads menu:  Beef Burger, Chicken Burger
SpongeBob- updating menu...
Customer- reads menu:  Fish Burger, Vegan Burger
Customer- reads menu:  Fish Burger, Vegan Burger
 */