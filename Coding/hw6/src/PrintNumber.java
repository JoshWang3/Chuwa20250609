public class PrintNumber {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        class RangePrinter implements Runnable {
            private final int start;
            private final int end;

            public RangePrinter(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public void run() {
                for (int i = start; i <= end; i++) {
                    synchronized (lock) {
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                    }
                }
            }
        }

        Thread thread1 = new Thread(new RangePrinter(1, 10), "Thread-0");
        Thread thread2 = new Thread(new RangePrinter(11, 20), "Thread-1");
        Thread thread3 = new Thread(new RangePrinter(21, 30), "Thread-2");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

    }
}
