1. Read: https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock

2. Write a thread-safe singleton class

```
public final class ConfigService {      // final: can’t be subclassed
    private ConfigService() {           // private ctor: prevents new
        // heavy initialization ...
    }

    /** Holder is only loaded when first accessed, guaranteeing lazy loading */
    private static class Holder {
        private static final ConfigService INSTANCE = new ConfigService();
    }

    /** Global access point */
    public static ConfigService getInstance() {
        return Holder.INSTANCE;         // JVM handles class-loading locks
    }

    // other thread-safe, stateless/public methods …
}
```

3. How to create a new thread(Please also consider Thread Pool approach)?

```
<!-- Thread -->

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start(); // starts a new thread
    }
}

<!-- Runnable -->

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable is running: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}

<!-- Callable -->

public class MyCallable implements Callable<String> {
    @Override
    public String call() {
        return "Callable result from " + Thread.currentThread().getName();
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new MyCallable());

        String result = future.get();  // blocks until result is ready
        System.out.println(result);

        executor.shutdown();
    }
}

<!-- Thread Pool -->

ublic class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4); // 4 threads

        for (int i = 0; i < 10; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Running task " + taskId + " on " + Thread.currentThread().getName());
            });
        }

        executor.shutdown(); // shuts down after all tasks finish
    }
}
```

4. Difference between Runnable and Callable?

    1. Return Value

        * Runnable: Does not return any result. Its run() method has a void return type.

        * Callable: Returns a result. Its call() method returns a value of type V.

    2. Exception Handling

        * Runnable: Cannot throw checked exceptions. You must handle exceptions inside the run() method manually.

        * Callable: Can throw checked exceptions, as the call() method is declared with throws Exception.

    3. Method Signature

        * Runnable: public void run()

        * Callable: public V call() throws Exception

    4. Use With Thread Pools

        * Both can be used with ExecutorService, but only Callable can be submitted to return a Future<V> containing a meaningful result.

        * When using Runnable with an executor, you can still get a Future<?>, but calling get() will return null unless manually handled.


5. What is the difference between t.start() and t.run()?

    * t.start(): This method is inherited from the Thread class.
                 It creates a new thread, and the JVM calls run() on that thread.
                 True multithreading happens here: run() executes asynchronously in a new thread.

    * t.run(): This just calls the run() method directly, like any normal method.
               No new thread is started; the run() method executes in the current thread (e.g., main).

6. Which way of creating threads is better: Thread class or Runnable interface?

    Runnable is better.
    1. Decouples Task from Thread: Runnable lets you define the task separately and use it in different threading contexts (raw Thread, thread pools, timers).

    2. Allows Extending Other Classes: Java supports only single inheritance, so extending Thread limits your design flexibility.

    3. Works with Executor Framework: Runnable and Callable are used in modern concurrency tools (ExecutorService, ThreadPoolExecutor, etc.).

    4. Cleaner, More Testable Code: You can pass Runnable tasks around without worrying about threading, which improves testability and reuse.

7. What are the thread statuses?

    NEW: The thread is created but not yet started.

    RUNNABLE: The thread is ready to run and waiting for CPU time or actively running.


    BLOCKED: The thread is waiting to enter a synchronized block or method locked by another thread.

    WAITING: The thread is waiting indefinitely for another thread to perform a specific action (e.g. join(), wait()).

    TIMED_WAITING: The thread is waiting for a specified time, e.g. via sleep(), join(timeout), or wait(timeout).

    TERMINATED: The thread has completed execution or exited due to an exception.

8. Demonstrate deadlock and how to resolve it in Java code.

```
public class DeadlockDemo {

    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK_A) {                 // grabs A
                sleep(100);                        // let t2 start
                synchronized (LOCK_B) {            // waits for B
                    System.out.println("T1 finished");
                }
            }
        }, "T1");

        Thread t2 = new Thread(() -> {
            synchronized (LOCK_B) {                 // grabs B
                sleep(100);                        // let t1 take A
                synchronized (LOCK_A) {            // waits for A
                    System.out.println("T2 finished");
                }
            }
        }, "T2");

        t1.start();
        t2.start();
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}
```

Lock ordering: Every thread acquires locks in the same global order

tryLock + timeout: Detects potential deadlock, backs off and retries


9. How do threads communicate each other?

Shared Object + wait() / notify() / notifyAll(): This is the classic way for threads to cooperate using a common object as a signal.

Using High-Level Concurrency Utilities (BlockingQueue, CountDownLatch, etc.): Modern Java (java.util.concurrent) provides safe and simplified tools for thread communication.

10. What’s the difference between class lock and object lock?

Object Lock (Instance-Level Lock):

* Used when you synchronize an instance method or a block on this or another object.

* Each instance of a class has its own object monitor.

Class Lock (Static or Class-Level Lock):

* Locks the Class object, which is shared by all instances of that class.

* Used in synchronized static methods or with synchronized(SomeClass.class).

11. What is join() method?

The join() method in Java is used to wait for a thread to finish its execution.

It belongs to the java.lang.Thread class and is a key tool for thread coordination.

12. what is yield() method

The yield() method is a static method in the Thread class that hints to the thread scheduler that the current thread is willing to pause and let other threads of equal priority execute.

13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

A ThreadPool is a pool (group) of reusable threads managed by the Executor framework that efficiently executes tasks without creating new threads every time. Instead of creating a new thread for each task, tasks are submitted to a pool, and worker threads pick them up from a task queue.

Java provides 4 standard types via the Executors class:

* Fixed Thread Pool: Pool of n threads, reused for incoming tasks. Good for steady load.

* Cached Thread Pool: Creates new threads as needed, reuses idle threads. Good for short-lived bursty tasks.

* Single Thread Executor: Only one worker thread. Ensures tasks execute sequentially.

* Scheduled Thread Pool: Executes tasks with delay or periodically. Like a timer with thread control.

A TaskQueue (internally a BlockingQueue<Runnable>) is the queue that holds tasks waiting to be executed by available threads in the pool.

14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?

To create ThreadPool, we can use:

* ExecutorService
* ThreadPoolExecutor
* Executors
* Callable, Runnable
* BlockingQueue

The ExecutorService interface is the main interface that provides the core functionalities of a thread pool.

15. How to submit a task to ThreadPool?

```
ExecutorService executor = Executors.newFixedThreadPool(3);

// Runnable (no return value)
executor.submit(() -> {
    System.out.println("Running task in: " + Thread.currentThread().getName());
});

// Callable (returns a result)
Future<String> future = executor.submit(() -> {
    return "Result from " + Thread.currentThread().getName();
});

try {
    String result = future.get(); // waits for result
    System.out.println(result);
} catch (Exception e) {
    e.printStackTrace();
}

executor.shutdown(); // shutdown
```

16. What is the advantage of ThreadPool?

    1. Better Performance via Thread Reuse: A ThreadPool reuses existing threads, reducing the overhead of creating new ones for every task.

    2.  Improved Resource Management: ThreadPool lets you control the maximum number of concurrent threads, avoiding out-of-memory errors or CPU overload.

    3. Efficient Task Queueing: Tasks are placed in a queue when all threads are busy. They are picked up automatically as threads become available.

    4. Simplified Thread Management: With ExecutorService, you don’t need to manually manage thread lifecycle (start, join, shutdown).

    5. Supports Asynchronous Execution with Results: When submitting Callable tasks, you get a Future<T> that lets you: fetch result later, cancel task, check task status.

    6. Supports Scheduled and Periodic Tasks: With ScheduledThreadPoolExecutor, you can schedule tasks to run after a delay or at fixed intervals.

17. Difference between shutdown() and shutdownNow() methods of executor

shutdown():
* Initiates a graceful shutdown.
* Already submitted tasks are executed.
* No new tasks are accepted.
* he pool shuts down after completing all active and queued tasks.

shutdownNow():
* Initiates an immediate shutdown.
* Attempts to stop all actively executing tasks.
* Returns a list of queued tasks that never ran.
* May interrupt threads if they’re interrupt()-responsive.

18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?

Atomic classes in Java, available in java.util.concurrent.atomic, provide lock-free, thread-safe operations on variables such as int, long, boolean, and object references. They use low-level atomic CPU instructions to avoid the overhead of synchronization. These classes include AtomicInteger, AtomicLong, AtomicBoolean, and AtomicReference. They're ideal for building high-performance, concurrent programs that need to update shared variables safely without blocking.

19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)

Concurrent collections in Java are data structures provided in the java.util.concurrent package that are designed to be safely used by multiple threads. These include thread-safe versions of maps, queues, lists, sets, and deques, such as ConcurrentHashMap, ConcurrentLinkedQueue, CopyOnWriteArrayList, and LinkedBlockingQueue. They offer better performance and reliability than manually synchronized collections and are critical for building efficient concurrent applications.

20. What kind of locks do you know? What is the advantage of each lock?

Java provides various locking mechanisms to manage thread synchronization. These include intrinsic locks via synchronized, explicit locks like ReentrantLock, read/write locks such as ReentrantReadWriteLock, and advanced locks like StampedLock for optimistic concurrency. Each lock offers different advantages—synchronized for simplicity, ReentrantLock for flexibility, ReadWriteLock for performance in read-heavy cases, and Semaphore for limiting access to shared resources. Choosing the right lock depends on the concurrency pattern you're implementing.

21. What is future and completableFuture? List some main methods of ComplertableFuture.

Future and CompletableFuture are Java constructs for handling asynchronous computations. Future is limited to blocking result retrieval, while CompletableFuture offers powerful features like chaining, non-blocking callbacks, and exception handling. Common methods of CompletableFuture include supplyAsync(), thenApply(), thenAccept(), thenCombine(), and exceptionally(), allowing you to write more flexible and reactive code.

22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)

23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)

1. One solution use synchronized and wait notify
2. One solution use ReentrantLock and await, signal

[OddEvenPrinter](../../Coding/HW6/src/OddEvenPrinter.java)

24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)

[PrinterNumber](../../Coding/HW6/src/PrinterNumber.java)

25. completable future:
1. Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum
and product of two integers, and print the results.
2. Homework 2: Assume there is an online store that needs to fetch data from three APIs: products,
reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched
data for further processing. (需要找public api去模拟，)
1. Sign In to Developer.BestBuy.com
2. Best Buy Developer API Documentation (bestbuyapis.github.io)
3. 可以⽤fake api https://jsonplaceholder.typicode.com/
4. Github public api: https://api.github.com/users/your-user-name/repos
3. Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API call, return a default value and log the exception information.

[CompletableFutureHomework](../../Coding/HW6/src/CompletableFutureHomework.java)
