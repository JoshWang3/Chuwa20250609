
public class OddEven_synchronized_wait_notify {
    private static final Object monitor = new Object();
    private static int value = 1;
    private static int turn = 0; // 0, 1, or 2 to indicate which thread's turn it is

    public static void main(String[] args) {
        // Create one runnable per thread with their respective IDs
        new Thread(new PrintRunnable(0), "Thread-0").start();
        new Thread(new PrintRunnable(1), "Thread-1").start();
    }

    static class PrintRunnable implements Runnable {
        private final int threadId;
        
        public PrintRunnable(int threadId) {
            this.threadId = threadId;
        }
        
        @Override
        public void run() {
            while (true) { // keep trying to print one value per lock
                synchronized (monitor) {
                    // Wait until it's this thread's turn
                    while (turn != threadId) {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    if (value > 10) {
                        monitor.notifyAll(); // wake up other waiting threads to exit
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + value++);
                    
                    // Update turn to next thread (0->1->2->0...)
                    turn = (turn + 1) % 2;
                    monitor.notifyAll(); // Notify all waiting threads
                }
            }
        }
    }
}
