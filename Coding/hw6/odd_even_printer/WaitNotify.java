package hw6.odd_even_printer;

public class WaitNotify {
    private static final Object lock = new Object();
    private int number = 1;

    public static void main(String[] args) {
        WaitNotify printer = new WaitNotify();

        Thread oddThread = new Thread(() -> printer.printOdd(), "OddThread");
        Thread evenThread = new Thread(() -> printer.printEven(), "EvenThread");

        oddThread.start();
        evenThread.start();
    }

    public void printOdd() {
        synchronized (lock) {
            while (number <= 10) {
                if (number % 2 == 1) {
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;
                    lock.notify();  // wake up even thread
                } else {
                    try {
                        lock.wait();  // wait for even thread to print
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    public void printEven() {
        synchronized (lock) {
            while (number <= 10) {
                if (number % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;
                    lock.notify();  // wake up odd thread
                } else {
                    try {
                        lock.wait();  // wait for odd thread to print
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

}


