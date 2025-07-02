public class PrintNumber {
        private static int n = 1;  // Shared counter

        public static void main(String[] args) {
            Thread t1 = new Thread(() -> printNumber(), "Thread-1");
            Thread t2 = new Thread(() -> printNumber(), "Thread-2");
            Thread t3 = new Thread(() -> printNumber(), "Thread-3");

            t1.start();
            t2.start();
            t3.start();
        }

        private static synchronized void printNumber() {
            for (int i = 0; i < 10; i++) {
                if (n > 30) return;
                System.out.println(Thread.currentThread().getName() + ": " + n++);
                try {
                    Thread.sleep(100); // reduce sleep to allow interleaving
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }