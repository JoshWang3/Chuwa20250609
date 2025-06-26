public class DeadLock {
    static final Object lockA = new Object();
    static final Object lockB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("T1 locked A");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB) {
                    System.out.println("T1 locked B");
                }
            }

        });

        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("T2 locked B");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockA) {
                    System.out.println("T2 locked A");
                }
            }

        });

        t1.start();
        t2.start();
    }
}


// how to resolve deadlock???
// change Thread 2 to lock lockA first, then lockB (same as Thread 1).