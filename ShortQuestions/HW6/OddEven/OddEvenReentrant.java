package OddEven;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenReentrant {
    private static final Lock lock = new ReentrantLock();
    private static final Condition turn = lock.newCondition();
    private static int value = 1;
    private static boolean isOddTurn = true;

    public static void main(String[] args) {
        new Thread(new OddRunnable()).start();
        new Thread(new EvenRunnable()).start();
    }

    static class OddRunnable implements Runnable {
        @Override
        public void run() {
            while(true) {
                lock.lock();
                try {
                    // wait if it's not odd thread's turn
                    while (!isOddTurn) {
                        turn.await(); // make the current even thread wait -> will change to odd
                    }
                    if (value > 10) {
                        turn.signal();  // notify other thread in case it's waiting, then exit
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + value++);
                    isOddTurn = false; // Set flag to even's turn
                    turn.signal(); // wake up even -> change back to even
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class EvenRunnable implements Runnable {
        @Override
        public void run() {
            while(true) {
                lock.lock();
                try {
                    // wait if it's not even thread's turn
                    while (isOddTurn) {
                        turn.await(); // make the current odd thread wait -> will change to even
                    }
                    if (value > 10) {
                        turn.signal(); // notify other thread in case it's waiting, then exit
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + value++);
                    isOddTurn = true; // set flag to odd's turn
                    turn.signal(); // wake up odd -> change back to odd
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
