import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenByReentrantLock {
    private final ReentrantLock lock = new ReentrantLock();
    Condition even = lock.newCondition();
    Condition odd = lock.newCondition();
    int number = 1;

    public void printOdd() throws InterruptedException{
        lock.lock();
        try {
            while (number <= 10) {
                if (number % 2 == 0) {
                    odd.await();
                } else {
                    if (number > 10) return;
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;
                    even.signal();
                }
            }
        }finally{
            lock.unlock();
        }
    }
    public void printEven() throws InterruptedException{
        lock.lock();
        try {
            while (number <= 10) {
                if (number % 2 == 1) {
                    even.await();
                } else {
                    if (number > 10) return;
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;
                    odd.signal();
                }
            }
        }finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        OddEvenByReentrantLock printer = new OddEvenByReentrantLock();
        Thread t1 = new Thread(() -> {
            try{
                printer.printOdd();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try{
                printer.printEven();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
    }
}
