package OddEvenPrinter;

public class OddEvenPrinter1 {
    private static final Object printer = new Object();
    private static int val = 1;

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            while (true) {
                synchronized (printer) {
                    if (val > 10) break;

                    System.out.println(Thread.currentThread().getName() + ": " + val++);
                    printer.notifyAll();

                    try {
                        if (val <= 10) {
                            printer.wait();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
