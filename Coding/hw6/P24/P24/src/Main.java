//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static int n = 1; // shared variable

    public static void main(String[] args) {
        Runnable task = () -> printNumber();

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();
    }

    private static synchronized void printNumber() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + n);
            n++;
            try {
                Thread.sleep(100); // optional: slow down to see the thread switch
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}