import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinter {

// Solution 1 – synchronized + wait/notify
    private static class WaitNotifySolution {
        private int counter = 1;                 // shared state
        private final Object monitor = new Object();

        private class OddThread extends Thread {
            @Override public void run() {
                while (true) {
                    synchronized (monitor) {
                        while (counter % 2 == 0) {        // wait for odd turn
                            if (counter > 10) return;
                            try { monitor.wait(); } catch (InterruptedException ignored) {}
                        }
                        if (counter > 10) { monitor.notifyAll(); return; }
                        System.out.println(getName() + " prints " + counter++);
                        monitor.notifyAll();
                    }
                }
            }
        }

        private class EvenThread extends Thread {
            @Override public void run() {
                while (true) {
                    synchronized (monitor) {
                        while (counter % 2 == 1) {        // wait for even turn
                            if (counter > 10) return;
                            try { monitor.wait(); } catch (InterruptedException ignored) {}
                        }
                        if (counter > 10) { monitor.notifyAll(); return; }
                        System.out.println(getName() + " prints " + counter++);
                        monitor.notifyAll();
                    }
                }
            }
        }

        void start() throws InterruptedException {
            Thread odd = new OddThread();  odd.setName("OddThread-WN");
            Thread even = new EvenThread(); even.setName("EvenThread-WN");
            odd.start();
            even.start();
            odd.join();
            even.join();
        }
    }

// Solution 2 – ReentrantLock + Condition
    private static class LockConditionSolution {
        private int counter = 1;                 // shared state
        private final Lock lock = new ReentrantLock();
        private final Condition oddTurn  = lock.newCondition();
        private final Condition evenTurn = lock.newCondition();

        private class OddThread extends Thread {
            @Override public void run() {
                while (true) {
                    lock.lock();
                    try {
                        while (counter % 2 == 0) {        // wait for odd turn
                            if (counter > 10) return;
                            try { oddTurn.await(); } catch (InterruptedException ignored) {}
                        }
                        if (counter > 10) { evenTurn.signal(); return; }
                        System.out.println(getName() + " prints " + counter++);
                        evenTurn.signal();                  // wake up even thread
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        private class EvenThread extends Thread {
            @Override public void run() {
                while (true) {
                    lock.lock();
                    try {
                        while (counter % 2 == 1) {        // wait for even turn
                            if (counter > 10) return;
                            try { evenTurn.await(); } catch (InterruptedException ignored) {}
                        }
                        if (counter > 10) { oddTurn.signal(); return; }
                        System.out.println(getName() + " prints " + counter++);
                        oddTurn.signal();                   // wake up odd thread
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        void start() throws InterruptedException {
            Thread odd = new OddThread();  odd.setName("OddThread-LC");
            Thread even = new EvenThread(); even.setName("EvenThread-LC");
            odd.start();
            even.start();
            odd.join();
            even.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Wait/Notify Solution ===");
        new WaitNotifySolution().start();

        System.out.println("\n=== ReentrantLock/Condition Solution ===");
        new LockConditionSolution().start();
    }
}
