# HW6 - Multithreading

---


## 1. **Reading**

Read: https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock


---

## 2. **Write a thread-safe singleton class**

```java
public class Singleton {
    // Private construction to prevent instantiation
    private Singleton() {
        
    }
    
    // Inner static class responsible for holding Singleton instance
    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }
    
    // Public method to provide access to the instance
    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
  
}
```

---

## 3. **How to create a new thread(Please also consider Thread Pool approach)?**

````java
    // Method1: extends Thread Class
    public class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Start new thread using extends Thread.");
        }
    }
    
    Thread t = new MyThread();
        t.
    
    start();
    
    // Method2: Implements Runnable
    public class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Start new thread using Runnable.");
        }
    }
    
    Thread t2 = new Thread(new MyRunnable());
        t.
    
    start();
    
    // Method3: Implements Callable
    public class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(500);
            return "Start new thread using Callable"
        }
    }
    
    // Method4: Use a Thread Pool
    import java.util.concurrent.*;
    public class Main {
        public static void main(String{} args) {
            ExecutorService es = Executors.newFixedThreadPool(4);
            Runnable task = () -> System.out.println("Running in thread pool: " + Thread.currentThread().getName());
            es.submit(task);
            es.shutdown();
        }
    }
    
````


---

## 4. **Difference between Runnable and Callable?**

* **runnable** has no return;
* **callable** has return and expect checked exception.

---

## 5. **What is the difference between t.start() and t.run()?**

* `t.start()`: starts a new thread to execute the task（run()）
* `t.run()`: executes the task in the current thread.

---

## 6. **Which way of creating threads is better: Thread class or Runnable interface?**

* Using the Runnable interface is generally better than extending the Thread class in most cases.
* **Loose Coupling and Lightweight**: Runnable interface makes task logic decoupled from thread management.
* **Supports Multiple Inheritance**: A class can implement Runnable and still extend another class.
* **Better Reusability**: Runnable tasks can be passed to thread pools (ExecutorService), making them reusable.
* **Scalable**: Runnable interface works well with modern concurrency frameworks (e.g., thread pools, scheduled executors).

---

## 7. **What are the thread statuses?**

* NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED

---

## 8. **Demonstrate deadlock and how to resolve it in Java code.**

### Deadlock Example:

```java
// Thread 1 locks LockA, waits for LockB.
// Thread 2 locks LockB, waits for LockA.
// Deadlock: each waits for the other forever.
public class DeadlockDemo {
    private static final Object LockA = new Object();
    private static final Object LockB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (LockA) {
                System.out.println("Thread 1: Holding LockA...");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (LockB) {
                    System.out.println("Thread 1: Acquired LockB");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (LockB) {
                System.out.println("Thread 2: Holding LockB...");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (LockA) {
                    System.out.println("Thread 2: Acquired LockA");
                }
            }
        });

        t1.start();
        t2.start();
    }
}

```

### ✅ Resolution: Modify both threads to acquire locks in the same order:

```java
public class DeadlockResolved {
    private static final Object LockA = new Object();
    private static final Object LockB = new Object();

    public static void main(String[] args) {
        Runnable task = () -> {
            synchronized (LockA) {
                System.out.println(Thread.currentThread().getName() + ": Holding LockA...");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (LockB) {
                    System.out.println(Thread.currentThread().getName() + ": Acquired LockB");
                }
            }
        };

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");

        t1.start();
        t2.start();
    }
}
```

---

## 9. **How do threads communicate each other?**

* Threads can share objects and use synchronized, wait(), notify(), or notifyAll() for coordination.


---

## 10. **What’s the difference between class lock and object lock?**

* **class lock:**
  * Acquired on an instance of a class.
  * Used when synchronizing non-static methods or blocks with synchronized(this).
  * Each object has its own lock.
  * Two threads can call the same method on different objects simultaneously.
  
* **object lock:**
  * Acquired on the Class object itself (ClassName.class).
  * Used when synchronizing static methods or blocks like synchronized(ClassName.class).
  * Shared across all instances of the class.
  * Only one thread can execute any static synchronized method at a time across all objects.


---

## 11. **What is join() method?**

* The `join()` method in Java is used to pause the current thread until another thread completes its execution.

---

## 12. **what is yield() method?**

* The `yield()` method is a hint to the thread scheduler that the current thread is willing to pause and let other threads of the same or higher priority run.

---

## 13. **What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?**

**What?**
* ThreadPool is a pool of pre-created worker threads that execute tasks from a queue, improving performance by reusing threads instead of creating new ones for each task. 
* It controls the number of concurrent threads and reduces thread creation overhead.
* It manages task queueing and scheduling and prevents resource exhaustion.

**types**
* FixedThreadPool, CachedThreadPool, SingleThreadExecutor

**TaskQueue in ThreadPool**
* TaskQueue stores pending tasks before threads can pick them up.

---

## 14. **Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?**

* The `java.util.concurrent` package is used to create ThreadPool.
* The key interface is `ExecutorService`.

---

## 15. **How to submit a task to ThreadPool?**

* Use the `ExecutorService` interface (from `java.util.concurrent`) and call its `submit()` or `execute()` methods.

```java
    import java.util.concurrent.*;
    public class Main {
        public static void main(String{} args) {
            ExecutorService es = Executors.newFixedThreadPool(4);
            Runnable task = () -> System.out.println("Running in thread pool: " + Thread.currentThread().getName());
            es.submit(task);
            es.shutdown();
        }
    }
```

---

## 16. **What is the advantage of ThreadPool?**

* Offers better performance and resource management compared to creating single threads directly. 
* Controls the number of concurrent tasks, reduce the overhead of thread creation and destruction, and improve performance. 
* Allows task queuing for pending execution, automatically manage thread lifecycles.
* Provides more flexible error handling mechanisms.

---

## 17. **Difference between shutdown() and shutdownNow() methods of executor**

* `shutdown()` stops accepting new tasks and lets existing tasks finish.
* `shutdownNow()` tries to stop all tasks immediately by interrupting threads and returns a list of pending tasks that were never started.

---

## 18. **What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?**

* **What?**
  * Atomic classes in Java (from `java.util.concurrent.atomic`) provide lock-free, thread-safe operations on single variables using low-level atomic CPU instructions.

* **Types**
  * AtomicInteger, AtomicLong, AtomicBoolean, AtomicReference<T>

* **Example Code**

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        System.out.println(atomicInteger.getAndIncrement()); // 1 2
        System.out.println(atomicInteger.incrementAndGet()); // 3
        System.out.println(atomicInteger.addAndGet(3)); // 6
        System.out.println(atomicInteger.get()); // 6
        System.out.println(atomicInteger.getAndSet(100)); // 6
        System.out.println(atomicInteger.get()); // 100
        
    }
}
```

* **When to use?**
  * When multiple threads need to safely update a shared variable (like counters or flags).
  
---

## 19. **What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)**

* **What?** 
  * Concurrent collections are Java data structures from the `java.util.concurrent package`
  * Designed to be thread-safe without needing external synchronization (like synchronized blocks). 
  * They allow high-performance, safe access by multiple threads.

* **Concurrent data structure**
  * CopyOnWriteArrayList, ConcurrentHashMap, CopyOnWriteArraySet, ArrayBlockingQueue, LinkedBlockingQueue

---

## 20. **What kind of locks do you know? What is the advantage of each lock?**

* **synchronized**
  * Built-in lock on objects or methods.
  * Simple and easy to use.
  * Automatically releases lock after block/method exits.
  
* **ReentrantLock**
  * Supports `tryLock()` (non-blocking attempt)
  * Supports `lockInterruptibly()`
  * Can be fair (FIFO) with constructor: new ReentrantLock(true)
  * Allows manual control over lock/unlock
  
* **ReadWriteLock**
  * Multiple threads can read concurrently, but only one can write.
  * Boosts performance in read-heavy scenarios.

* **StampedLock**
  * Advanced lock that supports optimistic reads, along with read/write locks.

---

## 21. **What is future and completableFuture? List some main methods of CompletableFuture.**

* **What?**
  * `Future<T>` is an interface that represents the result of an asynchronous computation.
  * `CompletableFuture<T>`:
    * An asynchronous programming tool introduced in Java 8.
    * Implements the Future interface, providing powerful asynchronous capabilities.
    * Supports chaining operations for easy combination and management of multiple asynchronous tasks.
    * Supports non-blocking asynchronous operations.

* **Common CompletableFuture Methods:**
  * `supplyAsync(Supplier)`, `runAsync(Runnable)`, `thenApply(fn)`,  `thenAccept(Consumer)`, `thenRun(Runnable)`, `join()`
  
```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10)
    .thenApply(x -> x * 2)
    .thenApply(x -> x + 1);

System.out.println(future.join()); // Output: 21

```

---

## 22. **Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)**

---

## 23. **Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)**

* 1. One solution use synchronized and wait notify
* 2. One solution use ReentrantLock and await, signal

📁 *Code snippets are in `Coding - hw6` package.*

---

## 24. **create 3 threads, one thread output 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random.(solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1) **

📁 *Code snippets are in `Coding - hw6` package. ThreeThreadPrinter*

---

## 25. **completable future:**
1. Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.

📁 *Code snippets are in `Coding - hw6` package. AsyncSumProduct*

2. Homework 2: Assume there is an online store that needs to fetch data from three APIs: products, reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched data for further processing. (需要找public api去模拟，)
   * Sign In to Developer.BestBuy.com
   * Best Buy Developer API Documentation (bestbuyapis.github.io)
   * 可以⽤fake api https://jsonplaceholder.typicode.com/
   * Github public api: https://api.github.com/users/your-user-name/repos

📁 *Code snippets are in `Coding - hw6` package. OnlineStoreDataFetcher*

3. Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API call, return a default value and log the exception information.

📁 *Code snippets are in `Coding - hw6` package. OnlineStoreDataFetcherWithExceptionHandling*

---

