package hw7;

public class printOneToThirty {
    public static void main(String[] args) {
        Thread t0 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        });

        Thread t1 = new Thread(() -> {
            for (int i = 11; i <= 20; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 21; i <= 30; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        });

        t0.setName("Thread-0");
        t1.setName("Thread-1");
        t2.setName("Thread-2");

        t0.start();
        try { t0.join(); } catch (InterruptedException e) {}

        t1.start();
        try { t1.join(); } catch (InterruptedException e) {}

        t2.start();
    }
}
