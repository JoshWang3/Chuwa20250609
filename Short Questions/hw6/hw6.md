### hw6
### 2. Write a thread-safe singleton class.
> Double-Checked Locking Singleton - Lazy + Efficient:
```java
public class Singleton {

    // `volatile` ensures visibility across threads and prevents reordering
    private static volatile Singleton instance;

    // Private constructor prevents instantiation from outside 
    private Singleton() {}

    public static Singleton getInstance() {
        // First check (no locking) — fast path for already-initialized instance
        if (instance == null) {
            // Synchronize only the first time
            synchronized (Singleton.class) {
                // Second check (with locking) — handles multi-thread race condition
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```
> Why Thread-Safe?
1. `volatile` keyword:
    - Prevents instruction reordering during object creation.
    - Ensures all threads see a fully constructed object.
    - Without it, a thread might see a partially initialized object.


2. Double-checking:
    - Avoids locking once the instance is initialized.
    - Locking only happens once per JVM.


3. JVM Guarantee:
    - Class-level lock on `Singleton.class` ensures only one thread can enter the critical section during initialization.
    - Once initialized, all threads return the same reference, avoiding duplicate instances.

    
---
### 3. How to create a new thread? (Also consider Thread Pool approach.)
> 1. Extends `Thread` class.
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();  // DO NOT call run() directly
    }
}
```
> 2. Implements `Runnable` interface.  
     Pass a `Runnable` instance to a `Thread` object.
```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable thread: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();
    }
}
```
> 3. Implements `Callable` with `FutureTask`.  

```java
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class MyCallable implements Callable<String> {
    public String call() {
        return "Callable thread: " + Thread.currentThread().getName();
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        Thread t = new Thread(futureTask);
        t.start();

        String result = futureTask.get(); // blocks until result is available
        System.out.println(result);
    }
}
```

> 4. Use Thread Pool (`ExecutorService`).
```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            return "Result from Callable";
        };

        Future<String> future = executor.submit(task);
        System.out.println(future.get()); // blocks until result is available

        executor.shutdown();
    }
}
```


---
### 4. Difference between Runnable and Callable?

|            | `Runnable`                          | `Callable<V>`                         |
|------------|--------------------------------------|----------------------------------------|
| Return Value | No (`void run()`)                   | Yes (`V call()`)                       |
| Exceptions | Cannot throw checked exceptions     | Can throw checked exceptions           |
| Used With  | `Thread`, `ExecutorService`         | `ExecutorService`, `FutureTask`        |
| Result Retrieval | Not possible                        | `Future.get()` retrieves result        |
 
**Summary:**  
- Use `Runnable` for simple tasks with no result.
- Use `Callable` when you need a return value or your task can throw checked exceptions and you want the caller to handle it.


---
### 5. What is the difference between t.start() and t.run()?
|                | `t.start()`                            | `t.run()`                                  |
|----------------|-----------------------------------------|--------------------------------------------|
| Threading      | Starts a **new thread**                | Runs in the **current thread**             |
| Multithreading | **Yes**                             | **No**                                     |
| Common Use     | Proper way to start a thread           | Used only for direct method call / testing |


```java
Thread t = new Thread(() -> System.out.println(Thread.currentThread().getName()));

t.start(); // Runs on a separate thread
t.run();   // Runs on main thread
```
✅ Always use `start()` to execute a thread concurrently.


---
### 6. Which way of creating threads is better: Thread class or Runnable interface?

|              | `Thread` class                | `Runnable` interface        |
|--------------|--------------------------------|-----------------------------|
| Design       | Inherits `Thread`             | Implements `Runnable`       |
| Flexibility  | Cannot extend another class   | Can still extend other classes |
| Reusability  | Less reusable                 | More reusable               |
 
✅ Use `Runnable`:
- Follows best practice: **"favor composition over inheritance"**
- Works well with Thread Pools (`ExecutorService`)
- Easier to test and reuse


---
### 7. What are the thread statuses?

| State           | Description                                                                 |
|------------------|-----------------------------------------------------------------------------|
| **NEW**          | Thread created but `start()` not called yet                                 |
| **RUNNABLE**     | Thread ready to run or currently running                                     |
| **BLOCKED**      | Waiting to acquire a lock (another thread holds the monitor)                |
| **WAITING**      | Waiting indefinitely for another thread to perform an action (`join()`, etc.) |
| **TIMED_WAITING**| Waiting for a specified time (`sleep()`, `join(timeout)`, `wait(timeout)`)  |
| **TERMINATED**   | Thread finished execution (normally or via exception)                       |



---
### 8. Demonstrate deadlock and how to resolve it in Java code.
> A deadlock occurs when two or more threads are permanently blocked, each waiting for a resource the other holds.

Example:  
Two threads hold locks in reverse order, causing both to wait forever.

```java
public class DeadlockExample {
    static final Object LockA = new Object();
    static final Object LockB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (LockA) {
                System.out.println("Thread 1: locked LockA");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (LockB) {
                    System.out.println("Thread 1: locked LockB");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (LockB) {
                System.out.println("Thread 2: locked LockB");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (LockA) {
                    System.out.println("Thread 2: locked LockA");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
```
**Solution to Resolve Deadlock:**  
Always acquire multiple locks in a fixed global order to avoid deadlocks.

```java
public class DeadlockResolved {
    static final Object LockA = new Object();
    static final Object LockB = new Object();

    public static void main(String[] args) {
        Runnable task = () -> {
            synchronized (LockA) {
                System.out.println(Thread.currentThread().getName() + " locked LockA");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (LockB) {
                    System.out.println(Thread.currentThread().getName() + " locked LockB");
                }
            }
        };

        new Thread(task).start();
        new Thread(task).start();
    }
}
```


---
### 9. How do threads communicate with each other?
> Threads communicate with each other primarily using **shared objects** and **synchronization mechanisms**.

Example 1: Use `synchronized`:
```java
class Counter {
    private int count = 0;

    public void increment() {
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}

public class SyncExample {
    public static void main(String[] args) throws InterruptedException {
        
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Count: " + counter.getCount());
    }
}
```

Example 2: Use a lock, `wait()`, and `notifyAll()`:
```java
public class WaitNotifyExample {
    private final Object lock = new Object();
    private boolean ready = false;

    // Thread A waits for "ready" to become true
    public void waitForReady() throws InterruptedException {
        synchronized (lock) {
            while (!ready) {
                lock.wait();  // Releases the lock and waits
            }
            System.out.println("Thread A: Ready to proceed!");
        }
    }

    // Thread B sets "ready" and notifies waiting threads
    public void markReady() {
        synchronized (lock) {
            ready = true;
            lock.notifyAll();  // Wakes up all waiting threads
            System.out.println("Thread B: Notified all waiting threads.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyExample example = new WaitNotifyExample();

        Thread t1 = new Thread(() -> {
            try {
                example.waitForReady();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);  // Simulate some work
                example.markReady();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
```

- `synchronized(lock)` ensures proper coordination on the shared object.
- `wait()` pauses the thread until `notifyAll()` is called.



---
### 10. What’s the difference between class lock and object lock?

|          | Lock Scope   | Applied On                                                          | Use Case                         |
|----------|--------------|---------------------------------------------------------------------|----------------------------------|
| **Object Lock** | Instance-level | `synchronized` on instance methods **or** `synchronized(this)`      | Controls access to a **single object** |
| **Class Lock** | Class-level  | `synchronized` on static methods **or** `synchronized(ClassName.class)` | Controls access to **all instances** of the class |



---
### 11. What is join() method?
> The `join()` method makes the calling thread wait until the target thread finishes.

```java
Thread t = new Thread(() -> {
    System.out.println("Child thread running");
});

t.start();
t.join();  // Main thread waits for t to finish

System.out.println("Main thread resumes");
```



---
### 12. What is yield() method?
> The `yield()` method tells the thread scheduler:  
  " I'm willing to give other threads of **equal priority** a chance to run. "
>
> ⚠️ It's a **hint**, not a command. The scheduler **may ignore it**.

```java
public class YieldExample {
    public static void main(String[] args) {
        
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            
            for (int i = 1; i <= 5; i++) {
                System.out.println(threadName + " - iteration " + i);

                // Yield control to allow other threads of equal priority to run
                Thread.yield();
            }
        };

        Thread t1 = new Thread(task, "Thread-A");
        Thread t2 = new Thread(task, "Thread-B");

        t1.start();
        t2.start();
    }
}
```
**What Happens?**
- Both threads `t1` and `t2` run the same task.
- After each print, they call `Thread.yield()`, asking the CPU: 
" Let other threads run if they’re ready. "
- This can result in threads taking turns more fairly.
- But no guarantee—sometimes one thread may still run multiple times in a row.



---
### 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

> **ThreadPool** is a pool of threads. 
> 
> Instead of creating a new thread for each task (which is expensive), tasks are submitted to the pool and assigned to **reusable worker threads**.

> **Types of ThreadPool:** Fixed, Cached, Single, Scheduled.

| Method                                | Description |
|---------------------------------------|-------------|
| `Executors.newFixedThreadPool(n)`     | Fixed number of threads |
| `Executors.newCachedThreadPool()`     | Creates new threads as needed, reuses idle ones |
| `Executors.newSingleThreadExecutor()` | Single worker thread (sequential tasks) |
| `Executors.newScheduledThreadPool(n)` | Executes tasks after delay or periodically |

> A **TaskQueue** is an internal **blocking queue**.
> 
> 1. You submit a task.
> 2. If all threads are busy, the task goes into the **TaskQueue**.
> 3. When a thread becomes free, it **takes a task from the queue** and runs it.


| Common Queue Type     | Behavior                                                                      |
|-----------------------|-------------------------------------------------------------------------------|
| `LinkedBlockingQueue` | Unbounded, used by `FixedThreadPool`                                          |
| `SynchronousQueue`    | No capacity, used by `CachedThreadPool` (task must be handed off immediately) |
| `DelayedWorkQueue`    | Used by `ScheduledThreadPool` for delayed / periodic tasks                    |



---
### 14. Which Library is used to create ThreadPool? Which Interface provide main functions of ThreadPool?
> Library: `java.util.concurrent` 
> 
> Interface:  `ExecutorService`

Key Methods from `ExecutorService`:

| Method            | Description                                      |
|-------------------|--------------------------------------------------|
| `submit()`        | Submits a `Runnable` or `Callable` for execution |
| `shutdown()`      | Initiates an orderly shutdown                    |
| `shutdownNow()`   | Attempts to stop all actively executing tasks    |
| `invokeAll()`     | Executes all given `Callable` tasks              |
| `invokeAny()`     | Executes the fastest `Callable` and returns result |



---
### 15. How to submit a task to ThreadPool?

> 1. **Create a ThreadPool** using `Executors` (e.g., `newFixedThreadPool`)
> 2. **Submit a task** using `executor.submit(Runnable)` or `executor.submit(Callable)`
> 3. **Shutdown the ThreadPool** after task submission

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // Step 1: Create ThreadPool with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Step 2: Submit a Runnable task
        executor.submit(() -> {
            System.out.println("Running task in: " + Thread.currentThread().getName());
        });

        // Step 3: Shutdown the ThreadPool
        executor.shutdown();
    }
}
```


---
### 16. What is the advantage of ThreadPool?

1. **Improve Performance**
    - Reuses existing threads instead of creating new ones for each task.


2. **Better Thread Management**
    - Manages task queue and thread lifecycle efficiently.


3. **Scalability**
    - Supports growing workloads by handling tasks asynchronously.


4. **Support Scheduling**
    - Use `ScheduledThreadPoolExecutor` for delayed or periodic tasks.



---
### 17. Difference between shutdown() and shutdownNow() methods of executor?

| Method         | Behavior                                                                 |
|----------------|--------------------------------------------------------------------------|
| `shutdown()`   | - Gracefully shuts down the executor. <br> - No new tasks accepted. <br> - Already submitted tasks continue to run. |
| `shutdownNow()`| - Forces shutdown immediately. <br> - Attempts to stop all running tasks. <br> - Returns a list of pending tasks.    |

**Use `shutdown()`** when you want a clean exit.  
**Use `shutdownNow()`** only if you need to stop everything immediately.



---
### 18. What is Atomic classes? When to use it? How many types of Atomic classes? 
#### Give me some code example of Atomic classes and its main methods. 
>Atomic classes are part of `java.util.concurrent.atomic` package.
> 
>They support **lock-free, thread-safe** operations on single variables.
> 
>Use **CAS (Compare-And-Swap)** internally to ensure atomicity.


> When to Use?  
>- When multiple threads update a **single variable concurrently**.
>- Useful for **counter**, **flag**, or **accumulator** without using `synchronized`.

> Common Types of Atomic Classes:  

| Class                     | Description                              |
|---------------------------|------------------------------------------|
| `AtomicInteger`           | Atomic operations on `int` values        |
| `AtomicLong`              | Atomic operations on `long` values       |
| `AtomicBoolean`           | Atomic operations on `boolean` values    |
| `AtomicReference<V>`      | Atomic operations on object references   |
| `AtomicIntegerArray`      | Atomic operations on `int[]`             |
| `AtomicLongArray`         | Atomic operations on `long[]`            |
| `AtomicReferenceArray<V>` | Atomic operations on object arrays       |

>Code Example:
```java
import java.util.concurrent.atomic.*;

public class AtomicExamples {
   public static void main(String[] args) {
      //---- AtomicInteger example (used for counters) ----
      AtomicInteger atomicInt = new AtomicInteger(0);

      // Thread-safe increment: no need for synchronized block
      atomicInt.incrementAndGet(); // ++value, returns new value
      atomicInt.getAndIncrement(); // value++, returns old value

      // Thread-safe addition
      atomicInt.addAndGet(5);      // value += 5, returns new value
      atomicInt.getAndAdd(3);      // returns old value, then adds 3

      // Thread-safe conditional update (CAS operation)
      atomicInt.compareAndSet(9, 100); // if value == 9, then set to 100

      System.out.println("AtomicInteger: " + atomicInt.get());

      //---- AtomicBoolean example (used for flags) ----
      AtomicBoolean atomicBool = new AtomicBoolean(false);

      // Thread-safe write
      atomicBool.set(true);

      // Thread-safe conditional update
      boolean updated = atomicBool.compareAndSet(true, false); // true → false if current is true

      System.out.println("AtomicBoolean: " + atomicBool.get());

      //---- AtomicReference example (used for reference objects) ----
      AtomicReference<String> atomicRef = new AtomicReference<>("A");

      // Thread-safe write
      atomicRef.set("B");

      // Thread-safe conditional update
      atomicRef.compareAndSet("B", "C"); // if current is "B", change to "C"

      System.out.println("AtomicReference: " + atomicRef.get());
   }
}
```

> Key Methods in Atomic Classes:

| Method                  | Description                                      |
|-------------------------|--------------------------------------------------|
| `get()`                 | Returns current value                            |
| `set(value)`            | Sets value                                       |
| `incrementAndGet()`     | Increments and returns new value                 |
| `getAndIncrement()`     | Returns current, then increments                 |
| `addAndGet(n)`          | Adds n, returns new value                        |
| `getAndAdd(n)`          | Returns current, then adds n                     |
| `compareAndSet(x, y)`   | If current == x, sets to y and returns true      |



---
### 19. What is the concurrent collections? List some concurrent (thread-safe) data structures.
> **Concurrent Collections** are thread-safe data structures designed for use in multithreaded environments.   
>
> They handle synchronization internally to avoid race conditions.

**Common Concurrent (Thread-Safe) Data Structures:**

| Data Structure                     | Description |
|----------------------------------|-------------|
| `ConcurrentHashMap`              | Thread-safe hash map. Allows concurrent reads and segmented writes. |
| `ConcurrentLinkedQueue`         | Non-blocking FIFO queue. Suitable for high-concurrency scenarios. |
| `ConcurrentLinkedDeque`         | Non-blocking double-ended queue. Supports FIFO and LIFO. |
| `CopyOnWriteArrayList`          | Thread-safe variant of `ArrayList`. Good for many reads, few writes. |
| `CopyOnWriteArraySet`           | Thread-safe set using copy-on-write. Backed by `CopyOnWriteArrayList`. |
| `BlockingQueue` (Interface)     | Supports thread-safe `put()` and `take()` operations (e.g., in producer-consumer). |
| └── `LinkedBlockingQueue`       | Linked nodes; optionally bounded. |
| └── `ArrayBlockingQueue`        | Bounded, backed by array. |
| └── `PriorityBlockingQueue`     | Priority-based blocking queue. |
| └── `DelayQueue`                | Elements become available after a delay. |
| └── `SynchronousQueue`          | No capacity; each insert waits for a remove. |
| `ConcurrentSkipListMap`         | Thread-safe sorted map (like `TreeMap`). |
| `ConcurrentSkipListSet`         | Thread-safe sorted set (like `TreeSet`). |

**Notes:**
- These collections are in `java.util.concurrent` package.
- Prefer them over synchronizing regular collections.
- Use the right one based on your use case: read-heavy, write-heavy, ordered, bounded, etc.


---
### 20. What kind of locks do you know? What is the advantage of each lock?
>Java provides several locking mechanisms for thread synchronization in `java.util.concurrent.locks`.

>1. **ReentrantLock**:
- More flexible than `synchronized`, supporting:
    - `tryLock()` (non-blocking attempt)
    - Timed lock (`tryLock(timeout)`)
    - Interruptible lock acquisition
    - Fairness policy (FIFO locking order)
- **Use case:** Fine-grained locking or when needing more control over thread synchronization.


>2. **ReadWriteLock**:
- How it works:
  - When one writer thread is writing, no other thread can proceed.
  - Multiple reader threads can read concurrently when no writer is active.
  
- `ReadWriteLock` is considered a `pessimistic lock` because it blocks other operations (reads or writes) to prevent conflicts:
  - When Read lock is held:	 Blocks writers, allows other readers.
  - When Write lock is held: Blocks everyone (readers + writers).
- **Use case:** Read-heavy application with rare writes.


>3. **StampedLock**:
- How it works:
    - Introduced in Java 8 as an alternative to `ReadWriteLock`.
    - Provides three modes:
        - **Read Lock:** Like `ReadWriteLock`'s read lock — shared and blocks writers.
        - **Write Lock:** Exclusive — blocks all other readers and writers.
        - **Optimistic Read:** Non-blocking read; does **not** acquire a traditional lock. May proceed even if a write is ongoing — but **must validate** afterward to check if the data was modified.

- `StampedLock` is considered an **optimistic lock** because:
    - It allows optimistic reads without locking.
    - Assumes reads won't conflict with writes (i.e., no modification during read), and only re-validates if necessary.

- **Use case:** Read-heavy application with rare writes.


>4. **CountDownLatch:**
- Initialized with a count; each `countDown()` call decrements it.
- Waiting threads call `await()` and proceed when count reaches zero.
- **Use case:** Waiting for multiple tasks to complete before continuing (e.g., main thread waiting for worker threads).


>5. **CyclicBarrier:**
- A **reusable** synchronization aid.
- Allows a group of threads to wait at a barrier point until all reach it.
- Once all parties arrive, barrier is tripped, and threads proceed.
- Can be reused for multiple rounds.
- **Use case:** Phased computation or iteration-based parallel tasks.


 
---
### 21. What is future and completableFuture? List some main methods of CompletableFuture.

>1. `Future`
- Represents a result of an async computation.  
- Returned by `ExecutorService.submit()`.

>2. `CompletableFuture`
- Enhanced version of `Future` (introduced in Java 8).


> `Future` vs `CompletableFuture`:

|                    | `Future`              | `CompletableFuture`           |
|--------------------|-----------------------|-------------------------------|
| Async Execution    | ✅                     | ✅                             |
| Result Chaining    | ❌                     | ✅ (`thenApply`, etc.)         |
| Non-blocking       | ❌                     | ✅                             |
| Combine Futures    | ❌                     | ✅ (`thenCombine`, `allOf`)    |
| Exception Handling | ❌                     | ✅ (`exceptionally`, `handle`) |


> Key Methods of `CompletableFuture`:

| Operation Signature                                                                 | Description                                                                                   | Example                                                                 |
|-------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|-------------------------------------------------------------------------|
| `supplyAsync(Supplier<T>) → CompletableFuture<T>`                                  | Runs a task asynchronously and returns a future with the result                              | `supplyAsync(() -> 5)` → `CompletableFuture[5]`                         |
| `runAsync(Runnable) → CompletableFuture<Void>`                                     | Runs a task asynchronously without returning a result                                        | `runAsync(() -> println("Run"))` → `CompletableFuture[Void]`           |
| `thenApply(Function<T, U>) → CompletableFuture<U>`                                 | Transforms result of previous stage                                                           | `cf.thenApply(x -> x * 2)` → `CompletableFuture[10]` if `cf = 5`        |
| `thenAccept(Consumer<T>) → CompletableFuture<Void>`                                | Consumes the result of previous stage                                                         | `cf.thenAccept(x -> println(x))` → prints `5`, returns `Void`          |
| `thenRun(Runnable) → CompletableFuture<Void>`                                      | Runs a task after completion, ignoring result                                                 | `cf.thenRun(() -> println("Done"))` → `Void`                           |
| `thenCombine(CompletableFuture<U>, BiFunction<T, U, R>) → CompletableFuture<R>`    | Combines two futures and applies function to their results                                    | `cf1.thenCombine(cf2, (a,b) -> a + b)` → `CompletableFuture[a+b]`      |
| `thenCompose(Function<T, CompletableFuture<U>>) → CompletableFuture<U>`            | Flattens chained futures                                                                      | `cf.thenCompose(x -> getFuture(x))` → single future result             |
| `exceptionally(Function<Throwable, T>) → CompletableFuture<T>`                     | Handles exception and provides fallback value                                                 | `cf.exceptionally(e -> -1)` → returns `-1` if exception occurs          |
| `handle(BiFunction<T, Throwable, U>) → CompletableFuture<U>`                       | Processes result or exception                                                                 | `cf.handle((res, ex) -> ex == null ? res : -1)`                        |
| `whenComplete(BiConsumer<T, Throwable>) → CompletableFuture<T>`                    | Like `handle`, but returns original result instead of transformed result                      | `cf.whenComplete((res, ex) -> log(res))`                               |
| `allOf(CompletableFuture<?>...) → CompletableFuture<Void>`                         | Returns when all futures complete                                                             | `CompletableFuture.allOf(cf1, cf2)` → when all complete                |
| `anyOf(CompletableFuture<?>...) → CompletableFuture<Object>`                       | Returns when any one of the futures completes                                                 | `CompletableFuture.anyOf(cf1, cf2)` → result of first completed future |
| `join() → T`                                                                        | Waits and returns result, throws unchecked exception on failure                               | `cf.join()` → `5`                                                      |
| `get() → T`                                                                         | Waits and returns result, throws checked exceptions                                           | `cf.get()` → `5`                                                       |
| `complete(T value) → boolean`                                                      | Manually completes the future                                                                 | `cf.complete(10)` → `true`                                             |
| `isDone() → boolean`                                                               | Checks if task is completed                                                                   | `cf.isDone()` → `true` or `false`                                     |

---
### 23. Write code to create 2 threads, one thread print `1,3,5,7,9`, another thread print `2,4,6,8,10`. 

>1. Solution with `synchronized` and `wait()`, `notify()`.
```java
public class OddEvenPrinter {
    private final Object lock = new Object();
    private boolean isOddTurn = true; // Shared flag to indicate which thread should print

    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter();

        Thread oddThread = new Thread(() -> printer.printOdd());
        Thread evenThread = new Thread(() -> printer.printEven());

        oddThread.start();
        evenThread.start();
    }

    // Print odd numbers 1, 3, 5, 7, 9
    public void printOdd() {
        for (int i = 1; i <= 9; i += 2) {
            synchronized (lock) {
                while (!isOddTurn) {
                    try {
                        lock.wait(); // Wait if it's not the odd thread's turn
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(i); // Safe to print odd number
                isOddTurn = false;     // Now it's even thread's turn
                lock.notify();         // Wake up even thread
            }
        }
    }

    // Print even numbers 2, 4, 6, 8, 10
    public void printEven() {
        for (int i = 2; i <= 10; i += 2) {
            synchronized (lock) {
                while (isOddTurn) {
                    try {
                        lock.wait(); // Wait if it's not the even thread's turn
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(i); // Safe to print even number
                isOddTurn = true;      // Now it's odd thread's turn
                lock.notify();         // Wake up odd thread
            }
        }
    }
}
```
> Key Thread-Safety Concepts:

 | Concept                          | Description |
 |----------------------------------|-------------|
 | **Shared `lock` object**         | Ensures mutual exclusion. Only one thread can enter the `synchronized` block at a time, preventing race conditions. |
 | **Shared flag `isOddTurn`**      | Coordinates turn-taking between threads. `true` means odd thread's turn; `false` means even thread's turn. |
 | **`wait()` and `notify()`**      | `wait()` suspends a thread and releases the lock if it's not its turn. `notify()` wakes the other thread to continue execution. |
 | **`while` loop around `wait()`** | Guards against spurious wakeups. Ensures thread only proceeds when it's truly its turn. |
 | **Synchronized blocks**          | All access to shared state (`isOddTurn`, `wait`, `notify`) is within `synchronized(lock)` blocks, ensuring thread-safe visibility and atomicity. |


>2. Solution with `ReentrantLock` and `await()`, `signal()`.
```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinterWithLock {
    private final Lock lock = new ReentrantLock();
    private final Condition oddTurn = lock.newCondition();   // Condition for odd thread
    private final Condition evenTurn = lock.newCondition();  // Condition for even thread
    private boolean isOddTurn = true;  // Shared flag to control turn

    public static void main(String[] args) {
        OddEvenPrinterWithLock printer = new OddEvenPrinterWithLock();

        Thread oddThread = new Thread(() -> printer.printOdd());
        Thread evenThread = new Thread(() -> printer.printEven());

        oddThread.start();
        evenThread.start();
    }

    // Prints 1, 3, 5, 7, 9
    public void printOdd() {
        for (int i = 1; i <= 9; i += 2) {
            lock.lock();  // Acquire lock before entering critical section
            try {
                while (!isOddTurn) {
                    // Wait until it's the odd thread's turn
                    oddTurn.await();
                }
                System.out.println(i); // Safe to print
                isOddTurn = false;     // Toggle turn
                evenTurn.signal();     // Signal even thread to continue
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Handle interruption
            } finally {
                lock.unlock(); // Always release the lock in finally block
            }
        }
    }

    // Prints 2, 4, 6, 8, 10
    public void printEven() {
        for (int i = 2; i <= 10; i += 2) {
            lock.lock();  // Acquire lock before entering critical section
            try {
                while (isOddTurn) {
                    // Wait until it's the even thread's turn
                    evenTurn.await();
                }
                System.out.println(i); // Safe to print
                isOddTurn = true;      // Toggle turn
                oddTurn.signal();      // Signal odd thread to continue
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Handle interruption
            } finally {
                lock.unlock(); // Always release the lock in finally block
            }
        }
    }
}
```

>Key Thread-Safety Concepts (with `ReentrantLock` and `Condition`)

| Concept                         | Description |
|----------------------------------|-------------|
| **`ReentrantLock`**              | Provides explicit locking. Ensures only one thread can enter the critical section at a time. |
| **`Condition` objects**          | Separate wait queues (`oddTurn`, `evenTurn`) that allow threads to wait and be notified precisely. |
| **`await()`**                    | Causes the current thread to wait and releases the lock until it is signaled. |
| **`signal()`**                   | Wakes up a thread waiting on the associated condition (e.g., signals the other thread to proceed). |
| **Shared flag `isOddTurn`**      | Boolean flag to determine which thread should run. Enforces correct alternation between threads. |
| **`lock.lock()` / `unlock()`**   | Enters and exits the critical section. Always use `unlock()` in a `finally` block to avoid deadlocks. |



---
### 24. Write code to create 3 threads, one thread outputs `1-10`, one thread outputs `11-20`, one thread outputs `21-22`. Threads' run sequence is random.

Use `ReentrantLock` and `Condition` to strictly enforce execution order:   
Thread-0 → Thread-2 → Thread-1, regardless of which thread starts first.

```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSequenceLock {

   public static void main(String[] args) {
      OrderedPrinter printer = new OrderedPrinter();

      // Assign ranges in logical order
      Thread t0 = new Thread(() -> printer.printNumbers(1, 10, 0), "Thread-0");
      Thread t1 = new Thread(() -> printer.printNumbers(11, 20, 1), "Thread-1");
      Thread t2 = new Thread(() -> printer.printNumbers(21, 30, 2), "Thread-2");

      // Start in random order — scheduler decides execution
      t2.start(); // Random start
      t1.start(); // Random start
      t0.start(); // Random start
   }
}

class OrderedPrinter {
   private final ReentrantLock lock = new ReentrantLock();
   private final Condition condition = lock.newCondition();
   private int step = 0; // Controls which thread prints next

   public void printNumbers(int start, int end, int threadStep) {
      lock.lock();
      try {
         // Wait until it’s this thread’s turn
         while (step != threadStep) {
            condition.await();
         }

         // Print numbers in assigned range
         for (int i = start; i <= end; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
         }

         // Move to the next thread step
         step++;
         condition.signalAll(); // Wake up all waiting threads

      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
      } finally {
         lock.unlock();
      }
   }
}
```

**Summary:**

| Thread Name | Number Range | Step |
|-------------|---------------|------|
| Thread-0    | 1–10          | 0    |
| Thread-1    | 11–20         | 1    |
| Thread-2    | 21–30         | 2    |

Even if threads **start in any order**, the use of `ReentrantLock`, `Condition`, and a shared `step` counter ensures that the output is always in the correct sequence.


**How Thread-Safety and Order Are Guaranteed:**
- `ReentrantLock`: Ensures only one thread can access the critical section at a time.
- `Condition.await()`: Causes threads to wait if it’s not their turn (i.e., step mismatch).
- **`step` variable**: Controls which thread gets to print next.
- `condition.signalAll()`: Wakes up all threads so they can re-check the step.
- `System.out.println()`: Internally synchronized — no interleaved lines.



----
### 25. CompletableFuture:

#### 1. Write a simple program that uses `CompletableFuture` to asynchronously get the sum and product of two integers, and print the results.

```java
import java.util.concurrent.CompletableFuture;

public class AsyncSumProduct {

    public static void main(String[] args) {
        int a = 5;
        int b = 3;

        // Asynchronously compute sum
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            return a + b;
        });

        // Asynchronously compute product
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            return a * b;
        });

        // Combine results after both tasks complete
        sumFuture.thenAccept(sum -> {
            System.out.println("Sum: " + sum);
        });

        productFuture.thenAccept(product -> {
            System.out.println("Product: " + product);
        });

        // Wait for both to complete (optional if running in a short-lived program)
        CompletableFuture.allOf(sumFuture, productFuture).join();
    }
}
```
>Output (for `a = 5`, `b = 3`):  
>Sum: 8  
>Product: 15  

**Notes:**
- **`supplyAsync()`**: Runs tasks asynchronously using the default ForkJoinPool.
- **`thenAccept()`**: Consumes and prints the result once computation is complete.
- **`CompletableFuture.allOf(...).join()`**: Ensures the main thread waits for all asynchronous tasks to finish. This is necessary in short-lived programs to prevent premature termination.
- **Thread-safe**: Each `CompletableFuture` runs independently; there is no shared mutable state.



---
#### 2. Assume there is an online store that needs to fetch data from three APIs: products, reviews, and inventory. Use `CompletableFuture` to implement this scenario and merge the fetched data for further processing. 
**Assumptions:**  
Simulate the following API endpoints using `https://jsonplaceholder.typicode.com`:

|           | Endpoint URL                                      |                    
|-----------|----------------------------------------------------| 
| Products  | `https://jsonplaceholder.typicode.com/posts`       |  
| Reviews   | `https://jsonplaceholder.typicode.com/comments`    |  
| Inventory | `https://jsonplaceholder.typicode.com/users`       |  



```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OnlineStoreDataFetcher {

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    // Fetch Products
    public static CompletableFuture<String> fetchProducts() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                         .thenApply(HttpResponse::body);
    }

    // Fetch Reviews
    public static CompletableFuture<String> fetchReviews() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/comments"))
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                         .thenApply(HttpResponse::body);
    }

    // Fetch Inventory
    public static CompletableFuture<String> fetchInventory() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                         .thenApply(HttpResponse::body);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> productsFuture = fetchProducts();
        CompletableFuture<String> reviewsFuture = fetchReviews();
        CompletableFuture<String> inventoryFuture = fetchInventory();

        // Combine all results
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                productsFuture, reviewsFuture, inventoryFuture
        );

        // When all done, merge and print
        CompletableFuture<String> mergedResult = allFutures.thenApply(v -> {
            String products = productsFuture.join();
            String reviews = reviewsFuture.join();
            String inventory = inventoryFuture.join();

            return "=== Products ===\n" + products.substring(0, 300) + "...\n\n" +
                   "=== Reviews ===\n" + reviews.substring(0, 300) + "...\n\n" +
                   "=== Inventory ===\n" + inventory.substring(0, 300) + "...";
        });

        // Print final result
        System.out.println(mergedResult.get());
    }
}
```
**Notes:**
- `HttpClient.sendAsync()` is **non-blocking** and returns a `CompletableFuture<HttpResponse<T>>`.
- `CompletableFuture.allOf(f1, f2, f3)` is used to **wait until all futures complete**.
- Inside `.thenApply()`, we safely use `.join()` on each future because they are guaranteed to have completed.
- This example only prints the **first 300 characters** of each response to keep the output concise.
- This is suitable for **asynchronous aggregation of API calls** with `CompletableFuture` for I/O-bound operations.



---
#### 3. For question 2, implement exception handling. If an exception occurs during any API call, return a default value and log the exception information.

An enhanced version of the previous code with exception handling:
- If an exception occurs during any API call, a default fallback value is returned.
- Exceptions are logged using `System.err.println()`.


```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OnlineStoreDataFetcher {

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    // Fetch Products
    public static CompletableFuture<String> fetchProducts() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    System.err.println("Failed to fetch products: " + ex.getMessage());
                    return "[]";  // default empty JSON array
                });
    }

    // Fetch Reviews
    public static CompletableFuture<String> fetchReviews() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/comments"))
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    System.err.println("Failed to fetch reviews: " + ex.getMessage());
                    return "[]";
                });
    }

    // Fetch Inventory
    public static CompletableFuture<String> fetchInventory() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    System.err.println("Failed to fetch inventory: " + ex.getMessage());
                    return "[]";
                });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> productsFuture = fetchProducts();
        CompletableFuture<String> reviewsFuture = fetchReviews();
        CompletableFuture<String> inventoryFuture = fetchInventory();

        // Combine all results
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                productsFuture, reviewsFuture, inventoryFuture
        );

        // When all done, merge and print
        CompletableFuture<String> mergedResult = allFutures.thenApply(v -> {
            String products = productsFuture.join();
            String reviews = reviewsFuture.join();
            String inventory = inventoryFuture.join();

            return "=== Products ===\n" + preview(products) + "\n\n" +
                   "=== Reviews ===\n" + preview(reviews) + "\n\n" +
                   "=== Inventory ===\n" + preview(inventory);
        });

        // Print final result
        System.out.println(mergedResult.get());
    }

    private static String preview(String json) {
        return json.length() > 300 ? json.substring(0, 300) + "..." : json;
    }
}
```
**Notes:**  
- `exceptionally(...)` catches and handles exceptions in the `CompletableFuture` chain.
- `System.err.println` logs error messages to standard error.
- `"[]"` is	the default JSON fallback for failed API responses.
- `.join()` is safe to use here because `.exceptionally()` ensures futures complete.



























