package ThreadCommunication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BurgerShopWithReentrantLock {

    private static final Lock lock = new ReentrantLock();
    private static final Condition burgerReady = lock.newCondition();
    private static boolean isBurgerReady = false;

    public static void main(String[] args) {
        Thread spongeBob = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("SpongeBob is making burger...");
                Thread.sleep(1000); // making time
                isBurgerReady = true;
                System.out.println("SpongeBob: Burger is ready :)");
                burgerReady.signal(); // wake up customer
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread customer = new Thread(() -> {
            lock.lock();
            try {
                while (!isBurgerReady) {
                    System.out.println("Customer is waiting for burger...");
                    burgerReady.await();
                }
                System.out.println("Customer: Where is my burger?! :(");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        customer.start();
        spongeBob.start();
    }
}

/*
output:
Customer is waiting for burger...
SpongeBob is making burger...
SpongeBob: Burger is ready :)
Customer: Where is my burger?! :(
 */