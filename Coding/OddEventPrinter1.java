
public class OddEventPrinter1 {
 private static final Object monitor = new Object();
 private static int value = 1; // public resource
 
    public static void main(String[] args) {
        PrintRunnable runnable = new PrintRunnable();
        new Thread(runnable).start(); // t0
        new Thread(runnable).start(); // t1
    }
    static class PrintRunnable implements Runnable {
        @Override
        public void run() {
            // synchronized : door
            // there are resources in door
            // buy a lock monitor
            synchronized (monitor) {
                while (value <= 10) {
                    System.out.println(Thread.currentThread().getName() + ": " + value++);
                    monitor.notifyAll(); // t0: monitor.notify()  -> those have the same lock 
                    try {
                        monitor.wait(); // // unlock and enter Waiting
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}