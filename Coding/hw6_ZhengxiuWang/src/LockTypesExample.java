import java.util.concurrent.locks.*;

public class LockTypesExample {

    // ========== 1. synchronized ========== //
    public synchronized void syncLockExample() {
        System.out.println("synchronized lock: " + Thread.currentThread().getName());
    }

    // ========== 2. ReentrantLock ========== //
    private final ReentrantLock reentrantLock = new ReentrantLock();

    public void reentrantLockExample() {
        reentrantLock.lock();
        try {
            System.out.println("ReentrantLock: " + Thread.currentThread().getName());
        } finally {
            reentrantLock.unlock();
        }
    }

    // ========== 3. ReadWriteLock ========== //
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void readLockExample() {
        rwLock.readLock().lock();
        try {
            System.out.println("Read lock: " + Thread.currentThread().getName());
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void writeLockExample() {
        rwLock.writeLock().lock();
        try {
            System.out.println("Write lock: " + Thread.currentThread().getName());
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    // ========== 4. StampedLock ========== //
    private final StampedLock stampedLock = new StampedLock();

    public void stampedLockExample() {
        long stamp = stampedLock.writeLock(); // could also try optimisticRead
        try {
            System.out.println("StampedLock write: " + Thread.currentThread().getName());
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    // ========== Main ========== //
    public static void main(String[] args) {
        LockTypesExample demo = new LockTypesExample();

        // Demo synchronized
        demo.syncLockExample();

        // Demo ReentrantLock
        demo.reentrantLockExample();

        // Demo ReadWriteLock
        demo.readLockExample();
        demo.writeLockExample();

        // Demo StampedLock
        demo.stampedLockExample();
    }
}
