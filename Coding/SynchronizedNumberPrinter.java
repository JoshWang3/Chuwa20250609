// Q23. Write code to create two threads: one thread prints 1,3,5,7,9; the other prints 2,4,6,8,10.  
// 23.1 One solution uses synchronized and wait/notify 
public class SynchronizedNumberPrinter {
    private static final Object lock = new Object();
    private static boolean oddTurn = true; // Start with odd numbers

    public static void main(String[] args) {
        Thread oddThread = new Thread(new OddPrinter());
        Thread evenThread = new Thread(new EvenPrinter());

        oddThread.start();
        evenThread.start();

        try {
            oddThread.join();
            evenThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static class OddPrinter implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 9; i += 2) {
                synchronized (lock) {
                    while (!oddTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println("Odd Thread: " + i);
                    oddTurn = false;
                    lock.notify();
                }
            }
        }
    }

    static class EvenPrinter implements Runnable {
        @Override
        public void run() {
            for (int i = 2; i <= 10; i += 2) {
                synchronized (lock) {
                    while (oddTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println("Even Thread: " + i);
                    oddTurn = true;
                    lock.notify();
                }
            }
        }
    }
}
