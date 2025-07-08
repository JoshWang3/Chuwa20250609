### Short Questions

2.**Write a thread-safe singleton class**

```
public class Singleton {
    private static volatile Singleton instance;
    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized(Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

```

3.How to create a thread 

**Extending the Thread class**:

```
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread via extending Thread class");
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}

```

**Implementing Runnable and passing to a Thread**:

```
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread via Runnable");
    }
}

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}

```

Using **Executors**, 

```
ExecutorService executor = Executors.newFixedThreadPool(2);
executor.submit(() -> System.out.println("From thread pool"));
executor.shutdown();

```

4. **Difference between Runnable and Callable?**

`Runnable` is a functional interface that doesn't return a result and cannot throw checked exceptions.

`Callable` returns a result (`V`) and can throw checked exceptions.

`Callable` is used with `Future` to retrieve results asynchronously.

5.**What is the difference between t.start() and t.run()?**

`start()` actually creates a new thread and runs the task in parallel.

`run()` just invokes the method in the **current thread**, meaning no concurrency is achieved.

6. **Which way of creating threads is better: Thread class or Runnable interface?**

**Implementing Runnable is preferred** because:

- It allows the class to extend another class (Java only supports single inheritance).
- The task and the thread running it are decoupled, which is better for reusability and for use with thread pools.

7. **What are the thread statuses?**

   **NEW** – Thread is created but not started.

   **RUNNABLE** – Ready to run, but may not be running yet.

   **BLOCKED** – Waiting to acquire a lock.

   **WAITING** – Waiting indefinitely for another thread’s signal.

   **TIMED_WAITING** – Waiting for a fixed amount of time.

   **TERMINATED** – Thread has finished execution.

   

   8.Demonstrate deadlock and how to resolve it in Java code.

   A deadlock happens when two threads are waiting on each other’s lock forever. It's caused by circular dependency.

   **Example:**
    Thread A locks `Resource1` and waits for `Resource2`,
    Thread B locks `Resource2` and waits for `Resource1`.

   **Solutions:**

   - Always lock resources in the same order.

   - Use `tryLock()` with timeout to avoid indefinite blocking.

   - Consider lock hierarchy to avoid circular waits

     

9.How do threads communicate each other?

Java uses methods like `wait()`, `notify()`, and `notifyAll()` for inter-thread communication. These methods must be used inside synchronized blocks and are typically used in producer-consumer scenarios.

10.What’s the difference between class lock and object lock?

**Object lock** is associated with an instance (`synchronized(this)`). It prevents multiple threads from accessing synchronized blocks on the same object.

**Class lock** is used with static methods or `synchronized(ClassName.class)`, and applies across all instances of the class.



11. **What is join() method?**

The `join()` method is used when one thread needs to wait for another to finish before proceeding. It's a way to impose execution order among threads.

12. What is yield() method?

`yield()` is a static method that hints the thread scheduler that the current thread is willing to pause and let other threads of the same priority run. It doesn’t guarantee anything; it’s just a suggestion to the scheduler.

13. **What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?**

A **ThreadPool** is a collection of reusable threads. Instead of creating a thread for each task, tasks are submitted to the pool and assigned to available threads.

**Types:**

- `FixedThreadPool`
- `CachedThreadPool`
- `SingleThreadExecutor`
- `ScheduledThreadPool`

**TaskQueue** is an internal queue (like `BlockingQueue`) that holds submitted tasks until threads are available to execute them.

##### 14. **Which Library is used to create ThreadPool? Which Interface provides main functions of thread-pool?**

Java’s `java.util.concurrent` package provides thread pool support.
The core interface is **ExecutorService**, which provides methods to manage and control the execution of tasks and lifecycle of the thread pool.

15. **How to submit a task to ThreadPool?**

Use the `submit()` or `execute()` methods provided by `ExecutorService`.

- `submit()` returns a `Future` and is used for tasks with a result.
- `execute()` is used for fire-and-forget type tasks.

16. **What is the advantage of ThreadPool?**

Better performance by reusing threads.

Reduces memory and CPU overhead of frequent thread creation.

Avoids too many concurrent threads (via bounded pools).

Provides better resource control and management.

17. **Difference between shutdown() and shutdownNow() methods of executor**

`shutdown()` – Gracefully shuts down the pool, allowing already submitted tasks to complete.

`shutdownNow()` – Attempts to stop all actively executing tasks immediately and returns the list of waiting tasks.

18. **What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. When to use it?**

Atomic classes are part of `java.util.concurrent.atomic` and support lock-free thread-safe operations on single variables.

**Examples include:**

- `AtomicInteger`
- `AtomicBoolean`
- `AtomicLong`
- `AtomicReference`

**Use case:** When you need counters, flags, or reference swapping in a highly concurrent environment without explicit locking.

19. **What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)?**

Concurrent collections are data structures designed to handle multithreaded access safely and efficiently.

**Examples:**

- `ConcurrentHashMap`
- `CopyOnWriteArrayList`
- `ConcurrentLinkedQueue`
- `LinkedBlockingQueue`

They use internal locking or non-blocking algorithms to ensure thread safety.

20. **What kind of locks do you know? What is the advantage of each lock?**

- **synchronized** – Simple to use, JVM-managed.
- **ReentrantLock** – Offers more control (lockInterruptibly, tryLock, fairness).
- **ReadWriteLock** – Separates read and write locks for better concurrency in read-heavy workloads.
- **StampedLock** – Introduces optimistic locking for even faster reads when contention is low.

21. **What is Future and CompletableFuture? List some main methods of CompletableFuture.**

**Future**: Represents the result of an asynchronous computation. It blocks when calling `get()`.

**CompletableFuture**: A more powerful tool introduced in Java 8 that supports:

- Non-blocking async execution
- Chaining via `thenApply`, `thenCompose`, etc.
- Exception handling via `exceptionally()`
- Combining multiple async tasks

23

```
package com.chuwa.tutorial.t08_multithreading.c05_waitNotify;

public class OddEvenPrinter {
    private static final int MAX = 10;
    private int number = 1;
    private final Object lock = new Object();

    public void printOdd() {
        synchronized (lock) {
            while (number <= MAX) {
                if (number % 2 == 1) {
                    System.out.println("Odd: " + number++);
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException ignored) {}
                }
            }
        }
    }

    public void printEven() {
        synchronized (lock) {
            while (number <= MAX) {
                if (number % 2 == 0) {
                    System.out.println("Even: " + number++);
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException ignored) {}
                }
            }
        }
    }

    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter();
        new Thread(printer::printOdd).start();
        new Thread(printer::printEven).start();
    }
}

```

24.Create 3 threads: one prints 1–10, another 11–20, another 21–22. Thread order is random.

```
package com.chuwa.exercise.t08_multithreading;

public class PrintNumber1 implements Runnable {
    private final int start;
    private final int end;

    public PrintNumber1(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new PrintNumber1(1, 10), "Thread-0");
        Thread t2 = new Thread(new PrintNumber1(11, 20), "Thread-1");
        Thread t3 = new Thread(new PrintNumber1(21, 22), "Thread-2");

        t1.start();
        t2.start();
        t3.start();
    }
}

```

25.



```
package com.chuwa.tutorial.t08_multithreading;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureSumProduct {
    public static void main(String[] args) {
        int a = 5, b = 3;

        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> a + b);
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> a * b);

        sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));
        productFuture.thenAccept(product -> System.out.println("Product: " + product));
    }
}

```

