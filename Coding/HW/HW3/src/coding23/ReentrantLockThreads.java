package coding23;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockThreads {
    private static ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static int count = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(new Printer(true));
        Thread t2 = new Thread(new Printer(false));

        t1.start();
        t2.start();
    }

    static class Printer implements Runnable {
        private final Boolean printOdd;

        public Printer(Boolean printOdd) {
            this.printOdd = printOdd;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();

                try {
                    if (count > 10) {
                        condition.signalAll();
                        return;
                    }
                    while ((count % 2 == 0 ) == printOdd) {
                        condition.await();
                    }
                    if (count > 10) {
                        condition.signalAll();
                        return;
                    }
                    System.out.println(Thread.currentThread().getName() + " " + count);
                    count++;
                    condition.signalAll(); //wake up other thread
                } catch (InterruptedException e) {

                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
