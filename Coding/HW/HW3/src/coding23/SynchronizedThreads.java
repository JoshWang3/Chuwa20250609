package coding23;

public class SynchronizedThreads {
    private static final Object lock = new Object();
    private static int count = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(new PrintRunnable(true));
        Thread t2 = new Thread(new PrintRunnable(false));

        t1.start();
        t2.start();
    }

    public static class PrintRunnable implements Runnable {
        private final boolean printOdd;

        public PrintRunnable(boolean printOdd) {
            this.printOdd = printOdd;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (count > 10) {
                        lock.notify();
                        return;
                    }
                    while ((count % 2 == 0) == printOdd) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {}
                    }
                    if (count > 10) {
                        lock.notifyAll();
                        return;
                    }
                    System.out.println(Thread.currentThread().getName() + " " + count);
                    count++;
                    lock.notifyAll();
                }
            }
        }
    }


}

