public class JoinVsYieldExample {
    public static void main(String[] args) throws InterruptedException {
        // join(): wait for t1 to finish
        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1 start");
            try { Thread.sleep(500); } catch (Exception e) {}
            System.out.println("Thread 1 end");
        });

        t1.start();
        t1.join();  // main waits

        System.out.println("Main after join");

        // yield(): give chance to other threads
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("Thread 2 running " + i);
                Thread.yield();  // suggest CPU switch
            }
        });

        t2.start();
    }
}
