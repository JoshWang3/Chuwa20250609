//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Q1 {
    private final Object lock = new Object();
    private boolean isOdd = true;

    public static void main(String[] args) {
        Q1 printer = new Q1();

        Thread oddThread = new Thread(() -> printer.printOdd());
        Thread evenThread = new Thread(() -> printer.printEven());

        oddThread.start();
        evenThread.start();
    }

    public void printOdd() {
        for (int i = 1; i <= 9; i += 2) {
            synchronized (lock) {
                while (!isOdd) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                isOdd = false;
                lock.notify();
            }
        }
    }

    public void printEven() {
        for (int i = 2; i <= 10; i += 2) {
            synchronized (lock) {
                while (isOdd) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                isOdd = true;
                lock.notify();
            }
        }
    }
}