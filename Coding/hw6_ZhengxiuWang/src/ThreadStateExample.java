public class ThreadStateExample {
    // NEW             → thread created, not started
    // RUNNABLE        → thread started, ready or running
    // TIMED_WAITING   → thread sleeping or waiting with timeout
    // TERMINATED      → thread finished
    public static void main(String[] args) throws InterruptedException {
        // Create a thread but not start yet → NEW
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);  // TIMED_WAITING
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // State: NEW (not started yet)
        System.out.println("State 1: " + t.getState());  // NEW

        t.start();  // Start thread → goes to RUNNABLE

        // Wait a short moment to make sure it's running or sleeping
        Thread.sleep(100);
        System.out.println("State 2: " + t.getState());  // RUNNABLE or TIMED_WAITING

        // Wait for thread to finish
        t.join();

        // State: TERMINATED (finished)
        System.out.println("State 3: " + t.getState());  // TERMINATED
    }
}
