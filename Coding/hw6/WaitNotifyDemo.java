package hw6;

/**
 * 这个例子中有两个线程：
 *
 * - 一个是 producer，负责将数据生产到 `buffer` 对象中；
 * - 一个是 consumer，负责从 `buffer` 中取出数据进行消费。
 *
 * 当 producer 线程调用 `buffer.produce()`（`synchronized` 方法）时，说明该线程已经获取了 `buffer` 对象的监视器锁（monitor），
 * 因此可以进入这个方法的critical section执行
 * A critical section in Java refers to a block of code that accesses shared resources and must be executed by only one thread at a time.
 *
 * 在 `produce()` 方法内部：
 * - 如果当前 `buffer` 已满（`isDataAvailable == true`），说明还没被消费，就不能再写入。
 * - 此时 producer 调用了 `this.wait()`（即对 `buffer` 对象调用 `wait()`）：
 *     - 它会 释放 `buffer` 对象的锁；
 *     - 然后自己进入 WAITING 状态（暂停执行）；
 *     - 它会一直挂起，直到有另一个线程（比如 consumer）调用 `notify()` 或 `notifyAll()` 将它唤醒。
 *
 * 被唤醒后，不会立即执行，而是：
 * - producer 会尝试重新获得 `buffer` 的锁（BLOCKED状态直到获得锁）；
 * - 获得锁之后才会继续执行 `wait()` 后面的代码，完成数据生产。
 */

public class WaitNotifyDemo {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();

        // Thread 1: Producer
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                buffer.produce(i);
                System.out.println("Produced: " + i);
                try {
                    Thread.sleep(500); // Simulate time taken to produce
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Thread 2: Consumer
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                int data = buffer.consume();
                System.out.println("Consumed: " + data);
                try {
                    Thread.sleep(800); // Simulate time taken to consume
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}

class SharedBuffer {
    private int data;
    private boolean isDataAvailable = false;

    // Called by producer thread
    public synchronized void produce(int newData) {
        // synchronized method: producer thread acquired 'this' lock (monitor)

        while (isDataAvailable) {
            try {
                // If buffer is full, producer waits
                // wait() releases 'this' object's lock and puts the producer thread in WAITING state
                wait(); // == this.wait()
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // When notified and lock is reacquired, producer resumes here
        data = newData;
        isDataAvailable = true;

        // notifyAll() wakes up all threads waiting on 'this' object (e.g., consumers)
        // They still need to reacquire the lock before proceeding
        notifyAll();
    }

    // Called by consumer thread
    public synchronized int consume() {
        // synchronized ensures mutual exclusion (thread must hold 'this' lock)

        while (!isDataAvailable) {
            try {
                // If no data, consumer waits
                // Releases lock and waits to be notified
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // When notified and lock is reacquired, consumer continues here
        isDataAvailable = false;

        // Notify producers that they can produce again
        notifyAll();

        return data;
    }
}

