public class OddEvenWaitNotify {
    private static final Object monitor = new Object();
    private static int value = 1;

    public static void main(String[] args) {
        PrintRunnable runnable = new PrintRunnable();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    static class PrintRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (monitor) {
                    if (value > 10) { // if reached more than -> call notify() to release the other thread (if it's still waiting) and exists loop
                        monitor.notify();
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + value++);

                    monitor.notify(); // wake up the other thread -> switch btwn odd and even

                    try {
                        if (value <= 10) { // if value <= 10, it calls wait() to pause and allow the other thread to proceed
                            monitor.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}