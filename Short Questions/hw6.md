## Question 2

```java
public class Singleton {

    // The volatile keyword ensures that multiple threads handle the uniqueInstance variable correctly
    // when it is being initialized to the Singleton instance.
    private static volatile Singleton uniqueInstance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (uniqueInstance == null) { // Single check
            synchronized (Singleton.class) {
                if (uniqueInstance == null) { // Double check
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
```

## Question 3

Extending the Thread class: 

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running.");
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
```

Implementing the Runnable interface:

```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread is running.");
    }
}

public class Main {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}
```

Using a thread pool:

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyTask implements Runnable {
    public void run() {
        System.out.println("Task is being executed by a thread from the pool.");
    }
}

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(new MyTask());
        executor.shutdown();
    }
}
```

## Question 4

The run() method is void and call() method returns a generic value.
The run() method cannot throw checked exceptions, while the call() method can throw checked exceptions.


## Question 5

t.start() creates a new thread and calls the run() method in that thread.
t.run() does not create a new thread; it simply calls the run() method in the current thread.

## Question 6

Implementing the Runnable interface is preferred over extending the Thread class because:
1. **Separation of Concerns**: Implementing Runnable allows you to separate the task from the thread management, which leads to cleaner and more maintainable code.
2. **Multiple Inheritance**: Java does not support multiple inheritance with classes, so implementing Runnable allows a class to extend another class while still being able to run in a thread.
3. **Reusability**: A class can implement Runnable and be reused in different contexts without being tied to a specific thread implementation.

## Question 7

Thread status:
- **NEW**: The thread has been created but not yet started.
- **RUNNABLE**: The thread is ready to run and waiting for CPU time.
- **BLOCKED**: The thread is blocked waiting for a monitor lock to enter a synchronized block/method.
- **WAITING**: The thread is waiting indefinitely for another thread to perform a particular action.
- **TIMED_WAITING**: The thread is waiting for another thread to perform an action for a specified waiting time.
- **TERMINATED**: The thread has completed execution.


## Question 8

```java
public class DeadlockExample {

    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: Holding lock 1...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                System.out.println("Thread 1: Waiting for lock 2...");
                synchronized (lock2) {
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2: Holding lock 2...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                System.out.println("Thread 2: Waiting for lock 1...");
                synchronized (lock1) {
                    System.out.println("Thread 2: Holding lock 2 & 1...");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}

public class DeadlockResolvedExample {

    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: Holding lock 1...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                System.out.println("Thread 1: Waiting for lock 2...");
                synchronized (lock2) {
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            // Acquire locks in the same order as thread1
            synchronized (lock1) {
                System.out.println("Thread 2: Holding lock 1...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                System.out.println("Thread 2: Waiting for lock 2...");
                synchronized (lock2) {
                    System.out.println("Thread 2: Holding lock 1 & 2...");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
```

## Question 9

Threads communicate with each other through shared memory and methods including: 
- wait(): Causes the current thread to wait until another thread invokes notify() or notifyAll() on the same object.
- notify(): Wakes up a single thread that is waiting on the object's monitor.
- notifyAll(): Wakes up all threads that are waiting on the object's monitor.

## Question 10
- Object Lock: An object lock is a lock on a specific instance of a class. When a thread acquires an object lock on an instance, no other thread can acquire the same lock on the same instance until the first thread releases it. 
- Class Lock: A class lock is a lock on the Class object itself. When a thread acquires a class lock, no other thread can acquire a lock on the same class until the first thread releases it.

## Question 11
The join() method is a method of the Thread class. When t.join() is called, the currently running thread will pause its execution until the thread t has completed its execution.

## Question 12
The yield() method is a static method of the Thread class. It is a hint to the thread scheduler that the current thread is willing to yield its current use of a processor. The thread scheduler is free to ignore this hint.

## Question 13
- ThreadPool: A thread pool is a managed collection of threads. It is used to execute a number of tasks in a more efficient way by reusing existing threads instead of creating a new thread for each task.
- newFixedThreadPool(int nThreads): Creates a thread pool with a fixed number of threads.
- newCachedThreadPool(): Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.
  newSingleThreadExecutor(): Creates a thread pool with a single thread.
  newScheduledThreadPool(int corePoolSize): Creates a thread pool that can schedule commands to run after a given delay, or to execute periodically.

## Question 14

- Library: java.util.concurrent package
- Interface: ExecutorService interface

## Question 15

- execute(Runnable command): Submits a Runnable task for execution and returns void.
- submit(Runnable task): Submits a Runnable task for execution and returns a Future representing that task.
- submit(Callable<T> task): Submits a value-returning Callable task for execution and returns a Future that will hold the result of the Callable.

## Question 16

- Improved Performance: Reduces the overhead of creating and destroying threads for each task.
- Resource Management: Allows you to control the number of concurrent threads.
- Increased Responsiveness: Tasks can be executed without waiting for new threads to be created.
- Simplified Thread Management: Provides a higher-level abstraction for managing threads.

## Question 17
- shutdown(): Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted.
- shutdownNow(): Attempts to stop all actively executing tasks, halts the processing of waiting tasks, and returns a list of the tasks that were waiting to be executed.

## Question 18
Atomic class is a class that provides a way to perform atomic operations on single variables. It is used to ensure that operations on variables are thread-safe without using synchronization.
Types of atomic classes: 
- Scalar Classes, Array Classes, Updater Classes, Compound Variable Classes

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {

    public static void main(String[] args) {
        AtomicInteger atomicInt = new AtomicInteger(0);
        System.out.println("Initial value: " + atomicInt.get());
        atomicInt.set(5);
        System.out.println("After set: " + atomicInt.get());
        System.out.println("Old value from getAndSet: " + atomicInt.getAndSet(10));
        System.out.println("After getAndSet: " + atomicInt.get());
        boolean wasSet = atomicInt.compareAndSet(10, 15);
        System.out.println("Was set: " + wasSet + ", Current value: " + atomicInt.get());
        System.out.println("After incrementAndGet: " + atomicInt.incrementAndGet());
    }
}
```
Use atomic classes when you need to perform thread-safe operations on a single variable without using explicit locks. 

## Question 19
Concurrent collections are data structures that are designed to be used in a multithreaded environment. They provide thread-safe operations without the need for external synchronization.
Examples: ConcurrentHashMap, CopyOnWriteArrayList, BlockingQueue

## Question 20

Type of locks: 
- Reentrant Lock: It allows for timed waits, interruptible lock acquisition, and the creation of fair locks.
- ReentrantReadWriteLock: Allows multiple threads to read a shared resource concurrently, while still providing exclusive access for writing. This can improve performance in read-heavy scenarios.
- StampedLock: An advanced lock that provides an optimistic read mode. This can offer better performance than ReentrantReadWriteLock in some cases.

## Question 21

Future: A Future represents the result of an asynchronous computation. It provides a way to check if the computation is complete, to wait for its completion, and to retrieve the result of the computation.

CompletableFuture: A CompletableFuture is an implementation of Future that can be explicitly completed (setting its value and status), and it can be used as a CompletionStage, supporting dependent actions that trigger upon its completion.

supplyAsync(Supplier<U> supplier): Creates a CompletableFuture that is asynchronously completed by a task running in the ForkJoinPool.commonPool() with the value obtained by calling the given Supplier.

thenApply(Function<? super T,? extends U> fn): Returns a new CompletionStage that, when this stage completes normally, is executed with this stage's result as the argument to the supplied function.

### Question 23

Using synchronized, wait, and notify:

```java
public class OddEvenPrinter {
    private final Object lock = new Object();
    private volatile boolean isOdd = true;
    private int count = 1;
    private final int max = 10;

    public void printOdd() {
        synchronized (lock) {
            while (count < max) {
                while (!isOdd) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + count);
                count++;
                isOdd = false;
                lock.notify();
            }
        }
    }

    public void printEven() {
        synchronized (lock) {
            while (count <= max) {
                while (isOdd) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + count);
                count++;
                isOdd = true;
                lock.notify();
            }
        }
    }

    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter();
        Thread oddThread = new Thread(printer::printOdd, "OddThread");
        Thread evenThread = new Thread(printer::printEven, "EvenThread");
        oddThread.start();
        evenThread.start();
    }
}
```

Using ReentrantLock and await, signal: 

```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinterWithLock {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition oddCondition = lock.newCondition();
    private final Condition evenCondition = lock.newCondition();
    private volatile boolean isOdd = true;
    private int count = 1;
    private final int max = 10;

    public void printOdd() {
        lock.lock();
        try {
            while (count < max) {
                while (!isOdd) {
                    try {
                        oddCondition.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + count);
                count++;
                isOdd = false;
                evenCondition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void printEven() {
        lock.lock();
        try {
            while (count <= max) {
                while (isOdd) {
                    try {
                        evenCondition.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + count);
                count++;
                isOdd = true;
                oddCondition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        OddEvenPrinterWithLock printer = new OddEvenPrinterWithLock();
        Thread oddThread = new Thread(printer::printOdd, "OddThread");
        Thread evenThread = new Thread(printer::printEven, "EvenThread");
        oddThread.start();
        evenThread.start();
    }
}
```