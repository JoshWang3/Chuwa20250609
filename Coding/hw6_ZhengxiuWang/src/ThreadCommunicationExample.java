public class ThreadCommunicationExample {
    public static void main(String[] args) {
        Object lock = new Object();

        Thread waiter = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread A: waiting...");
                try {
                    lock.wait();  // wait for notification
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread A: notified!");
            }
        });

        Thread notifier = new Thread(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException e) {}

            synchronized (lock) {
                System.out.println("Thread B: sending notify");
                lock.notify();  // notify waiting thread
            }
        });

        waiter.start();
        notifier.start();
    }
}
