import java.util.concurrent.locks.ReentrantLock;

public class ThreeThreads {
    static class RangePrinter implements Runnable {
        private final int start, end;
        private final ReentrantLock lock;
        RangePrinter(int start, int end, ReentrantLock lock) {
            this.start = start;
            this.end   = end;
            this.lock  = lock;
        }
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = start; i <= end; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            } finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args)  throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(new RangePrinter(1,  10, lock), "T1");
        Thread t2 = new Thread(new RangePrinter(11, 20, lock), "T2");
        Thread t3 = new Thread(new RangePrinter(21, 30, lock), "T3");
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
