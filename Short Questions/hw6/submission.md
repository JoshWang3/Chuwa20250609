# hw6 submission

## Q2. Write a thread-safe singleton class

### Answer:
```java
public class Main {

    public static void main(String[] args) {
        // Create multiple threads that get the Singleton instance
        Runnable task = () -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(Thread.currentThread().getName() + " -> Instance: " + instance.hashCode());
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}

// Thread-safe Singleton using double-checked locking
class Singleton {
    // 'volatile' ensures visibility and prevents instruction reordering
    private static volatile Singleton instance;

    // private constructor prevents external instantiation
    private Singleton() {
        System.out.println("Singleton instance created!");
    }

    public static Singleton getInstance() {
        if (instance == null) { // first check (no lock)
            synchronized (Singleton.class) {
                if (instance == null) { // second check (with lock)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

## Q3. How to create a new thread(Please also consider Thread Pool approach)?

### Answer:
1. Extending the Thread class
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start(); // Starts a new thread
    }
}
```

2. Implementing the Runnable interface
```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable running: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}
```

3. Implementing the Callable interface
```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallable implements Callable<String> {
    public String call() throws Exception {
        return "Callable executed by " + Thread.currentThread().getName();
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new MyCallable());
        String result = future.get(); // blocks until result is available
        System.out.println(result);
        executor.shutdown();
    }
}
```

4. Using a Thread Pool
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3); // pool of 3 threads

        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Running task " + taskId + " on " + Thread.currentThread().getName());
            });
        }

        executor.shutdown(); // shut down the pool gracefully
    }
}
```

## Q4. Difference between Runnable and Callable?

### Answer:
1. Return Value

Runnable does not return a result. Its run() method has a void return type, which means it performs some action but doesn't give anything back.

On the other hand, Callable can return a result. Its call() method returns a value of a generic type V. This is useful when you want a thread to compute and return something, like the result of a calculation.

2. Exception Handling

The run() method in Runnable cannot throw checked exceptions, so if you need exception handling, you must do it manually inside the run() method.

The call() method in Callable can throw checked exceptions, which makes it more flexible when the task might fail due to IO, database, or other operations that require exception handling.

3. Used With

Typically use Runnable with the Thread class or ExecutorService when no result or checked exception is needed.

Use Callable with ExecutorService and submit the task to it. The submit() method returns a Future object, which you can use to retrieve the result of the computation once it completes.

4. When to Use

Use Runnable when you have a simple task to run in the background that does not need to return anything and does not throw checked exceptions.

Use Callable when your task needs to return a value, or it might throw a checked exception and you want to handle that properly.

## Q5. What is the difference between t.start() and t.run()?

### Answer:
1. t.start()

When you call t.start(), the JVM creates a new thread of execution. This new thread will execute the code inside the run() method.

So, start() truly initiates multithreading. The main thread continues running independently, and the new thread runs concurrently.

2. t.run()

If you call t.run() directly, no new thread is created. The code inside run() is executed in the current thread, just like a regular method call.

That means it does not provide concurrent execution — it will block until run() finishes.

## Q6. Which way of creating threads is better: Thread class or Runnable interface?

### Answer:
Implementing the Runnable interface is generally better and preferred.

1. Java supports only single inheritance

When you extend the Thread class, your class cannot extend any other class, because Java doesn’t support multiple inheritance. This limits your flexibility.

On the other hand, if you implement the Runnable interface, you are free to extend any other class your design needs — which is a much better practice for real-world applications.

2. Separation of Concerns

Extending Thread mixes the job of thread management with business logic.

Implementing Runnable separates the task (Runnable) from the execution mechanism (Thread). This leads to cleaner, modular, and more maintainable code.

3. Reusability and Flexibility

A Runnable can be executed by multiple threads, reused, or submitted to an ExecutorService for thread pooling.

If you extend Thread, the thread and task are tightly coupled and not reusable across different threads.

## Q7. What are the thread statuses?

### Answer:
NEW: A thread is in the NEW state when it is created but has not yet been started.

RUNNABLE: A thread is in the RUNNABLE state when it has been started and is waiting for the CPU to schedule it. It may actually be running or just ready to run.

BLOCKED: A thread is in the BLOCKED state when it is waiting to acquire a lock (e.g., trying to enter a synchronized block or method, but another thread is holding the lock).

WAITING: A thread is in the WAITING state when it is waiting indefinitely for another thread to signal it (e.g., using Object.wait(), join() with no timeout, or LockSupport.park()). It will remain in this state until notified.

TIMED_WAITING: Similar to WAITING, but the thread will automatically wake up after a specified time (e.g., Thread.sleep(), wait(timeout), join(timeout)).

TERMINATED: A thread is in the TERMINATED state once its run() method has completed (either successfully or due to an exception).

## Q8. Demonstrate deadlock and how to resolve it in Java code.

### Answer:
A deadlock occurs in multithreaded programs when two or more threads are blocked forever, each waiting for the other to release a lock. This happens when:

Each thread holds a lock

Each thread tries to acquire a second lock held by the other

Neither can proceed
```java
public class Main {

    private static final Object LockA = new Object();
    private static final Object LockB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (LockA) {
                System.out.println("Thread-1: Holding LockA...");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                System.out.println("Thread-1: Waiting for LockB...");
                synchronized (LockB) {
                    System.out.println("Thread-1: Acquired LockB!");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (LockB) {
                System.out.println("Thread-2: Holding LockB...");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                System.out.println("Thread-2: Waiting for LockA...");
                synchronized (LockA) {
                    System.out.println("Thread-2: Acquired LockA!");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
```

## Q9. How do threads communicate each other?

### Answer:
In Java, threads communicate with each other primarily using shared memory and wait-notify mechanisms. This allows one thread to notify another thread about changes to shared data or to coordinate execution.

1. Shared Object Communication (Using synchronized + wait/notify)

wait() – makes the current thread wait until another thread calls notify() or notifyAll() on the same object.

notify() – wakes up one waiting thread.

notifyAll() – wakes up all waiting threads.

All of these must be called inside a synchronized block that locks on the same object.
```java
public class Main {
    private static final Object lock = new Object();
    private static boolean dataAvailable = false;

    public static void main(String[] args) {
        // Consumer Thread (waits for data)
        Thread consumer = new Thread(() -> {
            synchronized (lock) {
                while (!dataAvailable) {
                    try {
                        System.out.println("Consumer: Waiting for data...");
                        lock.wait(); // release the lock and wait
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Consumer: Consumed the data!");
            }
        });

        // Producer Thread (produces data and notifies)
        Thread producer = new Thread(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            synchronized (lock) {
                dataAvailable = true;
                System.out.println("Producer: Produced data and notifying...");
                lock.notify(); // wake up consumer
            }
        });

        consumer.start();
        producer.start();
    }
}
```

2. Concurrent Collections

Java provides high-level concurrent structures for safe thread communication:

BlockingQueue (e.g., ArrayBlockingQueue, LinkedBlockingQueue)

These are often used in producer-consumer patterns and handle synchronization internally.
```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);

        Thread producer = new Thread(() -> {
            try {
                queue.put("Message from producer");
                System.out.println("Producer: Message sent.");
            } catch (InterruptedException e) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                String message = queue.take();
                System.out.println("Consumer: Received -> " + message);
            } catch (InterruptedException e) {}
        });

        producer.start();
        consumer.start();
    }
}
```

## Q10. What’s the difference between class lock and object lock?

### Answer:
Class Lock: In java, each and every class has a unique lock usually referred to as a class level lock.

These locks are achieved using the keyword ‘static synchronized’ and can be used to make static data thread-safe.

It is generally used when one wants to prevent multiple threads from entering a synchronized block.

Object Lock: In java, each and every object has a unique lock usually referred to as an object-level lock.

These locks are achieved using the keyword ‘synchronized’ and can be used to protect non-static data.

It is generally used when one wants to synchronize a non-static method or block so that only the thread will be able to execute the code block on a given instance of the class.
```java
public class Main {
    public static void main(String[] args) {
        MyClass obj1 = new MyClass();
        MyClass obj2 = new MyClass();

        // Thread A locks on obj1 (object lock)
        Thread t1 = new Thread(() -> obj1.instanceMethod(), "Thread-A");

        // Thread B locks on obj2 (another object lock — won't conflict)
        Thread t2 = new Thread(() -> obj2.instanceMethod(), "Thread-B");

        // Thread C tries to acquire class lock
        Thread t3 = new Thread(() -> MyClass.staticMethod(), "Thread-C");

        t1.start();
        t2.start();
        t3.start();
    }
}

class MyClass {

    public synchronized void instanceMethod() {
        System.out.println(Thread.currentThread().getName() + " entered instanceMethod");
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        System.out.println(Thread.currentThread().getName() + " exiting instanceMethod");
    }

    public static synchronized void staticMethod() {
        System.out.println(Thread.currentThread().getName() + " entered staticMethod");
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        System.out.println(Thread.currentThread().getName() + " exiting staticMethod");
    }
}
```

## Q11. What is join() method?

### Answer:
join() method is generally used to pause the execution of a current thread unless and until the specified thread on which join is called is dead or completed.

To stop a thread from running until another thread gets ended, this method can be used.

It joins the start of a thread execution to the end of another thread’s execution. It is considered the final method of a thread class.
```java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            System.out.println("Worker thread started...");
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            System.out.println("Worker thread finished.");
        });

        worker.start();

        System.out.println("Main thread waiting for worker to finish...");
        worker.join(); // main thread waits here
        System.out.println("Main thread resumes after join.");
    }
}
```

## Q12. what is yield() method

### Answer:
The yield() method is used to hint to the thread scheduler that the current thread is willing to pause and let other threads of equal priority execute.

It doesn't pause the thread, but suggests that the CPU time be given to others.
```java
public class Main {
    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " - iteration " + i);
                Thread.yield(); // hint to give up CPU
            }
        };

        Thread t1 = new Thread(task, "Thread-A");
        Thread t2 = new Thread(task, "Thread-B");

        t1.start();
        t2.start();
    }
}
```
## Q13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

### Answer:
What is a Thread Pool?

A Thread Pool is a pool (or group) of pre-created threads that are managed and reused to execute many tasks efficiently, without creating a new thread for every task.

Instead of spawning a new thread every time, tasks are submitted to the pool, and threads are reused, reducing overhead and improving performance — especially under heavy loads.

Thread Pools are part of the java.util.concurrent package via the ExecutorService interface.

Why Use Thread Pools?

Improved performance (faster than creating threads repeatedly)

Better resource management

Prevents too many threads from overwhelming the system

Makes concurrent code easier to scale and maintain

Types of Thread Pools in Java

Java provides several built-in types of thread pools through the Executors utility class:

1. FixedThreadPool

Creates a pool with a fixed number of threads. Extra tasks are queued until threads are free. Use this when you want to limit concurrent threads (like handling 4 API requests in parallel).

2. CachedThreadPool

Creates new threads as needed, and reuses idle threads. Unused threads are removed after 60 seconds. Best for short-lived, lightweight tasks with unpredictable frequency.

3. SingleThreadExecutor

Creates a pool with only one thread. Tasks are executed sequentially in the order they are submitted. Use when tasks must not run in parallel (e.g., writing to a single log file).

4. ScheduledThreadPool

Used to run tasks after a delay or periodically. Best for repeating tasks (like polling, cleanup, or scheduled jobs).

What is a Task Queue in Thread Pool?

The task queue is an internal structure (usually a BlockingQueue) that holds tasks waiting to be executed.

If all threads are busy, new tasks are queued.

The queue type determines how overflow is handled:

Tasks in the queue are picked up by idle threads when available.

## Q14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?

### Answer:
Library Used: java.util.concurrent

This package includes all key classes and interfaces related to concurrency, such as: Executor, ExecutorService, Callable, Future, BlockingQueue, etc.

Core Interface: ExecutorService

It extends the basic Executor interface and provides:

Task submission (submit, invokeAll, invokeAny)

Shutdown methods (shutdown, shutdownNow)

Future-based result handling (Future<T>)

Key Methods of ExecutorService:

submit(Runnable task) — submits a Runnable task and returns a Future<?>

submit(Callable<T> task) — submits a Callable and returns a Future<T>

shutdown() — initiates a graceful shutdown

shutdownNow() — attempts to stop all executing tasks immediately

invokeAll(...) — submits a collection of callables and waits for all to finish

invokeAny(...) — returns the result of the first successful callable

## Q15. How to submit a task to ThreadPool?

### Answer:
Create a ThreadPool using the Executors factory methods (e.g., newFixedThreadPool()).

Submit a task using submit() or execute().

Optionally, get results using Future<T> if you use submit() with Callable.
```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<String> task = () -> {
            return "Result from " + Thread.currentThread().getName();
        };

        Future<String> future = executor.submit(task); // submits a task that returns a result
        String result = future.get(); // blocks until result is ready
        System.out.println("Got result: " + result);

        executor.shutdown();
    }
}
```

## Q16. What is the advantage of ThreadPool?

### Answer:
Better Performance

Creating and destroying threads repeatedly is expensive. Thread pools reuse existing threads, avoiding the overhead of thread creation and destruction — especially helpful when handling a large number of short-lived tasks.

Resource Management

Thread pools help prevent resource exhaustion (like OutOfMemoryError or too many threads) by limiting the maximum number of concurrent threads.

Improved Scalability

By limiting concurrency and using queues, thread pools help applications scale better under high load. For example, in a web server, a thread pool can handle hundreds of requests without spawning hundreds of threads.

Simplified Thread Lifecycle Management

With thread pools, you don’t need to manually start and manage threads. Just submit tasks, and the pool handles.

## Q17. Difference between shutdown() and shutdownNow() methods of executor

### Answer:
shutdown(): Initiates an orderly shutdown.

It stops accepting new tasks, but:

Already submitted tasks (both running and queued) are allowed to finish.

It does not interrupt actively running tasks.

Use this when you want to shut down the pool gracefully.

shutdownNow(): Attempts to stop all actively executing tasks by interrupting them.

Also prevents new tasks from being submitted.

Clears the task queue and returns a list of tasks that never started.

Use this when you want to force shutdown immediately.

## Q18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?

### Answer:
What are Atomic Classes?

Atomic classes are part of the java.util.concurrent.atomic package. They provide a way to safely update variables in a multithreaded environment without using locks like synchronized.

They use low-level, lock-free, hardware-level atomic operations (like CAS: Compare-And-Swap) to ensure thread safety.

Why Use Atomic Classes?

They are faster than using synchronized

They help avoid issues like race conditions

They support non-blocking algorithms

Ideal for high-performance, low-contention environments

Common Atomic Classes in Java

AtomicInteger — for atomic int operations

AtomicLong — for atomic long operations

AtomicBoolean — for atomic boolean operations

AtomicReference<V> — for atomic operations on objects

AtomicIntegerArray, AtomicLongArray — for atomic operations on arrays

AtomicStampedReference — handles ABA problem in concurrent programming
```java
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                count.incrementAndGet(); // Thread-safe atomic increment
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count (with AtomicInteger): " + count.get());
    }
}
```

## Q19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)

### Answer:
Concurrent collections are a set of thread-safe data structures provided by Java in the java.util.concurrent package. They are designed to be used safely by multiple threads without requiring external synchronization (synchronized blocks).

These data structures use advanced techniques like: Lock stripping, Non-blocking algorithms (CAS), Fine-grained synchronization

They allow better performance and scalability than using regular collections wrapped with Collections.synchronizedXXX().

1. ConcurrentHashMap<K, V>

A high-performance thread-safe HashMap. Allows concurrent reads and writes. Uses internal bucket-level locking (not global lock)

2. CopyOnWriteArrayList<E>

A thread-safe version of ArrayList. On write (add/remove), a new copy of the array is created. Best for read-heavy, write-rare use cases

3. ConcurrentLinkedQueue<E>

A lock-free, thread-safe FIFO queue. Based on linked nodes. Ideal for producer-consumer patterns

4. ConcurrentLinkedDeque<E>

A lock-free double-ended queue (can add/remove from both ends). Thread-safe alternative to ArrayDeque

5. BlockingQueue<E> and Its Variants

Used in producer-consumer models where threads wait for space or items.

ArrayBlockingQueue — fixed size, bounded queue

LinkedBlockingQueue — optionally bounded, good for large throughput

## Q20. What kind of locks do you know? What is the advantage of each lock?

### Answer:
1. ReentrantLock (from java.util.concurrent.locks)

An explicit, flexible, and powerful alternative to synchronized.

Supports tryLock() — try to acquire lock without blocking

tryLock(timeout) — wait only for a specific time

lockInterruptibly() — supports interrupting while waiting

Can be configured as fair (FIFO thread order)

Reentrant — same thread can acquire it multiple times

2. ReadWriteLock and ReentrantReadWriteLock

A lock that separates read and write access. Multiple threads can hold the read lock simultaneously. Only one thread can hold the write lock, and it blocks all reads and writes.

Increases performance in read-heavy systems

Prevents write conflicts while allowing concurrent reads

3. StampedLock (Java 8+)

An advanced version of ReadWriteLock that uses stamps/tokens for lock management. Supports optimistic reads (non-blocking read, later validated). Supports read, write, and optimistic read

Even better performance in read-dominant environments

Optimistic reads allow maximum concurrency

More fine-grained control over concurrency

4. Lock-Free / Atomic Operations (CAS-based)

Classes like AtomicInteger, AtomicReference use lock-free, low-level CPU instructions (CAS – Compare And Swap).

Super-fast, non-blocking

Great for simple counters and flags

No risk of deadlock

## Q21. What is future and completableFuture? List some main methods of ComplertableFuture.

### Answer:
1. Future

Future is an interface in java.util.concurrent introduced in Java 5.

It represents the result of an asynchronous computation that will be available in the future.

You get a Future when you submit a Callable to an ExecutorService.

2. CompletableFuture

CompletableFuture is a class in java.util.concurrent (Java 8+) that extends Future with asynchronous, non-blocking, and reactive-style capabilities.

Run tasks asynchronously

Chain operations

Combine multiple futures

Handle exceptions

Run tasks in parallel, and compose results

3. Main Methods of CompletableFuture
supplyAsync(Supplier<T>): Runs a task asynchronously and returns a result.

runAsync(Runnable): Runs a task asynchronously with no return value.

thenApply(Function<T, R>): Transforms the result when it becomes available.

thenAccept(Consumer<T>): Consumes the result without returning anything.

thenCompose(Function<T, CompletableFuture<R>>): Used to flatten and chain dependent futures.

thenCombine(): Combines two independent futures.

exceptionally(Function<Throwable, T>): Handles exceptions and recovers gracefully.

join() (non-blocking alternative to get()): Returns result or throws an unchecked exception — no need to handle InterruptedException.

## Q22. Code of CompletableFuture

### Answer:
```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 1. Run an async task that returns a value
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 1 running on: " + Thread.currentThread().getName());
            return 10;
        });

        // 2. Chain a transformation
        CompletableFuture<Integer> future2 = future1.thenApply(result -> {
            System.out.println("Doubling result: " + result);
            return result * 2;
        });

        // 3. Chain a consumer (final result)
        future2.thenAccept(result -> {
            System.out.println("Final Result: " + result);
        });

        // 4. Run a second independent task
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 2 running on: " + Thread.currentThread().getName());
            return 5;
        });

        // 5. Combine future2 and future3
        CompletableFuture<Integer> combined = future2.thenCombine(future3, (a, b) -> {
            System.out.println("Combining results: " + a + " + " + b);
            return a + b;
        });

        // 6. Handle result
        combined.thenAccept(result -> {
            System.out.println("Combined Result: " + result);
        });

        // 7. Handle exceptions
        CompletableFuture<Integer> withError = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Something went wrong!");
        });

        withError
            .exceptionally(ex -> {
                System.out.println("Caught exception: " + ex.getMessage());
                return -1;
            })
            .thenAccept(val -> System.out.println("Recovered value: " + val));

        // Wait for all tasks to complete
        Thread.sleep(2000); // wait to ensure async tasks finish
    }
}
```
## Q23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. One solution use synchronized and wait notify. One solution use ReentrantLock and await, signal

### Answer:
```java
import java.util.concurrent.locks.*;

public class Main {
    private static final Object monitor = new Object(); // used by synchronized block
    private static final ReentrantLock lock = new ReentrantLock(); // used by even thread
    private static final Condition condition = lock.newCondition();
    private static volatile boolean isOddTurn = true; // shared flag

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            for (int i = 1; i <= 9; i += 2) {
                synchronized (monitor) {
                    while (!isOddTurn) {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {}
                    }
                    System.out.println("Odd: " + i);
                    isOddTurn = false;

                    // Notify even thread using ReentrantLock
                    lock.lock();
                    try {
                        condition.signal();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });

        Thread evenThread = new Thread(() -> {
            for (int i = 2; i <= 10; i += 2) {
                lock.lock();
                try {
                    while (isOddTurn) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {}
                    }

                    System.out.println("Even: " + i);
                    isOddTurn = true;

                    // Notify odd thread using synchronized
                    synchronized (monitor) {
                        monitor.notify();
                    }
                } finally {
                    lock.unlock();
                }
            }
        });

        oddThread.start();
        evenThread.start();
    }
}
```
## Q24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random.

### Answer:
```java
public class Main {
    private static int n = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> printNumber());
        Thread t2 = new Thread(() -> printNumber());
        Thread t3 = new Thread(() -> printNumber());

        t1.start();
        t2.start();
        t3.start();
    }

    private static synchronized void printNumber() {
        int count = 10;
        while (count-- > 0) {
            System.out.println(Thread.currentThread().getName() + ": " + n++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Main.class.notifyAll();
    }
}
```
## Q25-1. Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.

### Answer:
```java
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        int a = 5;
        int b = 3;

        // CompletableFuture to calculate sum
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            return a + b;
        });

        // CompletableFuture to calculate product
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            return a * b;
        });

        // When sum is done, print it
        sumFuture.thenAccept(sum -> {
            System.out.println("Sum = " + sum);
        });

        // When product is done, print it
        productFuture.thenAccept(product -> {
            System.out.println("Product = " + product);
        });

        // Wait briefly to let async tasks complete
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
## Q25.

### Answer:
Code in /Coding/hw6/Main.java
