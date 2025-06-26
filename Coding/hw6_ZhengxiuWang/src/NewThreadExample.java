public class NewThreadExample {
    public static void main(String[] args) {
        // Create a thread using Runnable
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Running in a new thread");
            }
        });

        t1.start(); // Start the thread

        // Or use lambda (Java 8+)
        Thread t2 = new Thread(() -> {
            System.out.println("Running with lambda");
        });

        t2.start();
    }
}
