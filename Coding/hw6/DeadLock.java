package hw6;

public class DeadLock {
    private static final Object Lock1 = new Object();
    private static final Object Lock2 = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            synchronized (Lock1) {
                System.out.println("Thread A: Holding Lock1...");
                // pauses the current thread for 100 milliseconds to increase the chance of a deadlock happening
                // without it, thread 1 may acquire Lock1 and Lcok2 quickly so Thread2 may mot have a chance to acquire Lock2 first
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                System.out.println("Thread A: Waiting for Lock2...");
                synchronized (Lock2) {
                    System.out.println("Thread A: Acquired Lock2");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (Lock2) {    // resolve it by replacing to Lock1
                System.out.println("Thread B: Holding Lock2...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                System.out.println("Thread B: Waiting for Lock1...");
                synchronized (Lock1) {  // resolve it by replacing to Lock2
                    System.out.println("Thread B: Acquired Lock1");
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}

