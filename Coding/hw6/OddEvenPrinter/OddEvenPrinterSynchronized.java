package Coding.hw6.OddEvenPrinter;

public class OddEvenPrinterSynchronized {
    private final Object lock = new Object();
    private boolean flag = true;

    public void printOdd() {
        for (int i = 0; i < 10; i += 2) {
            synchronized (lock) {
                while(!flag) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                flag = false;
                lock.notify();
            }
        }
    }

    public void printEven() {
        for (int i = 2; i <= 10; i += 2) {
            synchronized (lock) {
                while(flag) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                flag = true;
                lock.notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OddEvenPrinterSynchronized printer = new OddEvenPrinterSynchronized();
        Thread odd = new Thread(printer::printOdd, "Thread-0");
        Thread even = new Thread(printer::printEven, "Thread-1");

        odd.start();
        even.start();

        odd.join();
        even.join();
    }
}
