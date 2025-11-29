import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {
    private final StampedLock lock = new StampedLock();
    private double x, y;

    public void updateCoordinates(double newX, double newY) {
        long stamp = lock.writeLock(); // Writer acquires a write lock
        try {
            System.out.println(Thread.currentThread().getName() + " acquired write lock.");
            Thread.sleep(1000); // Simulate work
            this.x = newX;
            this.y = newY;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlockWrite(stamp);
            System.out.println(Thread.currentThread().getName() + " released write lock.");
        }
    }

    public double distanceFromOrigin() {
        // Optimistic read attempt
        long stamp = lock.tryOptimisticRead();
        System.out.println(Thread.currentThread().getName() + " performs optimistic read attempt, got stamp: " + stamp);

        // A write lock is active, so stamp will be 0
        if (stamp == 0) {
            System.out.println(Thread.currentThread().getName() + " Optimistic read failed. Acquiring pessimistic read lock.");
            // Fallback to a pessimistic read lock
            stamp = lock.readLock();
            try {
                // Read coordinates with a full lock
                System.out.println(Thread.currentThread().getName() + " acquired pessimistic read lock.");
                return Math.sqrt(x * x + y * y);
            } finally {
                lock.unlockRead(stamp);
                System.out.println(Thread.currentThread().getName() + " released pessimistic read lock.");
            }
        }

        // --- This part is not executed when a write lock is active ---
        // (For a successful optimistic read, validation is needed)
        System.out.println(Thread.currentThread().getName() + " stamp is not 0");
        try {
            double currentX = x;
            double currentY = y;
            if (lock.validate(stamp)) {
                return Math.sqrt(currentX * currentX + currentY * currentY);
            }
        } finally {
            // Nothing to unlock for an optimistic read
        }
        
        return -1; // Should not be reached in this simplified case
    }

    public static void main(String[] args) throws InterruptedException {
        StampedLockDemo demo = new StampedLockDemo();

        // Start a writer thread
        Thread writer = new Thread(() -> demo.updateCoordinates(3, 4), "WriterThread");
        writer.start();

        // Give the writer thread time to acquire the lock
        Thread.sleep(100);

        // Start a reader thread that will encounter the write lock
        Thread reader = new Thread(() -> demo.distanceFromOrigin(), "ReaderThread");
        reader.start();

        writer.join();
        reader.join();
    }
}
