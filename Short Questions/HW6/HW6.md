# Ryan Ma HW6 Answers and Sample code



## 1. [Resources: Multiple Interview Questions](https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock)

## 2. Write a thread-safe singleton class
```java
class LoggerSingleton {
    private static volatile LoggerSingleton instance;
    
    private LoggerSingleton() {
        System.out.println("Instance created"); // Print something to verify instance creation
    }
    
    public static LoggerSingleton getInstance() {
        if (instance == null) {
            synchronized (LoggerSingleton.class) {
                if (instance == null) {
                    instance = new LoggerSingleton();
                }
            }
        }
        return instance;
    }
}

public class Main {
    public static void main(String[] arg) {
        Runnable task = () -> {
            LoggerSingleton logger = LoggerSingleton.getInstance();
            System.out.println("Got instance" + logger);
        };
        
        for (int i = 0; i < 5; i += 1) {
            new Thread(task).start();
        }
    }
}
```
## 3. How to create a new thread(Please also consider Thread Pool approach)?
- Extends Thread Class
```java
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Start new thread extending Thread");
    }
}

public class Main {
    public static void main(String[] arg) {
        Thread t = new MyThread();
        t.start();
    }
}
```
- Implements Runnable

```java
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(("Start new thread using Runnable"));
    }
}

public class Main {
    public static void main(String[] arg) {
        Thread t = new Thread(new MyRunnable());
        t.start();
    }
}
```
- Implements Callable

```java
import java.util.concurrent.Callable;

class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return "Start new thread using Callable";
    }
}
```
- Using a Thread Pool
```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] arg) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task = () -> System.out.println("Thread:" + Thread.currentThread().getName());

        for (int i = 0; i < 5; i ++) {
            executor.submit(task);
        }

        executor.shutdown();
    }
}
```

## 4. Difference between Runnable and Callable?
- Runnable has no return;
- Callable has return and expect check exception.

## 5. What is the difference between t.start() and t.run()?
- t.start() starts a new thread to execute the task.
- t.run() execute the task in the current thread.

## 6. Which way of creating threads is better: Thread class or Runnable interface?
- Use Runnable unless you have a very specific reason to extend Thread.
- Runnable interface is more flexible, testable and better aligned with real-world use

| Feature                          | `extends Thread`              | `implements Runnable`                     |
|----------------------------------|-------------------------------|-------------------------------------------|
| Inheritance flexibility          | ❌ Can't extend other classes  | ✅ Can extend other classes                |
| Separation of task & thread      | ❌ Mixed logic (task = thread) | ✅ Clean separation (task ≠ thread)        |
| Reusability of logic             | ❌ Less reusable               | ✅ More reusable and testable              |
| Used in thread pools / executors | ❌ Not directly usable         | ✅ Compatible with `ExecutorService`, etc. |
| Clean design (OOP)               | ❌ Weaker design               | ✅ Encourages composition over inheritance |

## 7. What are the thread statuses?
- In java, a thread goes through several states during its lifecycle. These states are defined as thread status.
- There are 6 main thread status

  | **State**       | **Meaning**                                                                  |
  |-----------------|------------------------------------------------------------------------------|
  | `NEW`           | Thread is created but not yet started (e.g., `new Thread(...)`)              |
  | `RUNNABLE`      | Thread is ready to run, and may be running or waiting for CPU time           |
  | `BLOCKED`       | Thread is waiting to acquire a lock held by another thread (synchronized)    |
  | `WAITING`       | Thread is waiting indefinitely for another thread to notify it               |
  | `TIMED_WAITING` | Thread is waiting for a specified time (e.g., `sleep`, `join`, `wait(1000)`) |
  | `TERMINATED`    | Thread has finished execution (either normally or via exception)             |

## 8. Demonstrate deadlock and how to resolve it in Java code.
- A deadlock happens when two or more threads are waiting forever for each other to release resources.
- Example Thread A holds Lock1, waits for lock2, Thread B holds lock2 and waits for lock1;
- DeadLock Demo Code (Intentional)
```java
class Main{
    private static final Object Lock1 = new Object();
    private static final Object Lock2 = new Object();
    
    public static void main(String[] arg) {
        Thread t1 = new Thread(() -> {
            synchronized (Lock1) {
                System.out.println("Thread1 holding lock1");
                try {Thread.sleep(100);} catch (InterruptedException e) {}
                
                System.out.println("Thread1 waiting for lock2");
                synchronized (Lock2) {
                    System.out.println("Thread1 acquired lock2");
                }
            }
        });
        
        Thread t2 = new Thread(() ->{
            synchronized (Lock2) {
                System.out.println("Thread2 holding lock2");
                try {Thread.sleep(100);} catch (InterruptedException e) {}
                
                System.out.println("Thread2 waiting for lock1");
                synchronized (Lock1) {
                    System.out.println("Thread2 acquire lock1");
                }
            }
        });
        
        t1.start();
        t2.start();
        // Output will freeze after deadlock
    }
}
```
- Ways to solve deadlock.
  - Always lock resources in the same order
  - Countdown latch
  - Wait & Notify
  - Using Locks (ReentrantLock, ReadWriteLock, StampedLock)
  
## 9. How do threads communicate each other?
- Shared Objects - through shared memory
- Synchronized: wait(), notify(), notifyAll() - Coordinated Communication
- ReentrantLock: condition.await() & condition.signal() & condition.signalAll()

## 10. What’s the difference between class lock and object lock?
- Class Lock: locks the class itself(shared across all instances).
  - Used when you synchronize static methods or use synchronized(ClassName.class) 
  - Only one thread across the entire class, no matter how many objects, can hold the class lock.
- Object Lock: locks a single instance of a class
  - Used when you synchronize instance methods or blocks using synchronized(this) or synchronized(Object)
  - Only one thread per object can execute a synchronized instance method at a time.
  - Different instances can be accessed by different threads simultaneously.
  
## 11. What is join() method?
- It is a method from the Thread class.
- It tells the main thread to wait the current thread until the current thread finishes.
- Main thread will continue after current thread finished.

## 12. what is yield() method?
- A hint to the scheduler that the current thread is willing to yield its current use of a processor. The scheduler is free to ignore this hint.
- When a thread calls Thread.yield(), the thread temporarily pauses and moves back to the runnable stable.

## 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
- ThreadPool is a pool (group) of reusable worker threads for executing tasks.
- Types of ThreadPools in Java(Executors)

  | Type                                           | Description                                                      |
  |------------------------------------------------|------------------------------------------------------------------|
  | `newFixedThreadPool(n)`                        | Fixed number of threads (`n`). Reused for all tasks.             |
  | `newCachedThreadPool()`                        | Unbounded pool; creates new threads as needed, reuses idle ones. |
  | `newSingleThreadExecutor()`                    | Only one thread. Tasks run sequentially.                         |
  | `newScheduledThreadPool(n)`                    | Fixed pool that can schedule tasks (delayed or periodic).        |

- The TaskQueue holds tasks waiting to be executed. If all threads are busy, the pool puts incoming tasks into the queue. 
Once a thread becomes free, it picks the next task from the queue.

## 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
- java.util.concurrent.Executors is the library we can use to create ThreadPool
- java.util.concurrent.ExecutorService Interface can provide main functions of thread-pool

## 15. How to submit a task to ThreadPool?
- Example 1
```java
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        Runnable task = () -> System.out.println("Thread creating");
        
        for (int i = 0; i < 5; i++) {
            executor.submit(task);
        }
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {}
        executor.shutdown();
    }
}
```
- Example 2
```java
import java.util.concurrent.*;
public class Main {
  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(3);

    for (int i = 0; i < 5; i++) {
      executor.submit(new MyRunnable("Name" + i));
    }
    executor.shutdown();

  }
}

class MyRunnable implements Runnable {
  private final String taskName;

  public MyRunnable(String taskName) {
    this.taskName = taskName;
  }

  public void run() {
    System.out.println("Start Task: " + taskName );
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {}
    System.out.println("End Task: " + taskName);
  }
}
```

## 16. What is the advantage of ThreadPool?
- Avoid creating/destroying threads repeatedly
- improves performance, especially for short or frequent tasks.
- You submit tasks -> the pool assigns threads to run them.

## 17. Difference between shutdown() and shutdownNow() methods of executor
- shutdown() is graceful: it lets tasks finish.
- shutdownNow() is forceful: it trys to stop now, may interrupt running tasks.

| Feature                        | `shutdown()`        | `shutdownNow()`                           |
|--------------------------------|---------------------|-------------------------------------------|
| Accept new tasks               | ❌ No                | ❌ No                                      |
| Running tasks                  | ✅ Finish normally   | ❌ Attempt to stop immediately (interrupt) |
| Waiting tasks in queue         | ✅ Will be processed | ❌ Removed and returned as a list          |
| Throws `InterruptedException`? | ❌ No                | ❌ It interrupts threads (if supported)    |
| Return value                   | `void`              | `List<Runnable>` (tasks not yet started)  |

## 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
- Atomic classes are part of java.util.concurrent.atomic package and provide lock-free, thread-safe operations on a single variables like int, long, boolean, etc.
- They use low level CPU(CAS - Compare and swap) to guarantee atomicity without using synchronized.
- Types of Atomic classes

  | Class                | Type Handled     |
  |----------------------|------------------|
  | `AtomicInteger`      | `int`            |
  | `AtomicLong`         | `long`           |
  | `AtomicBoolean`      | `boolean`        |
  | `AtomicReference<T>` | Object reference |
  | `AtomicIntegerArray` | Array of `int`s  |
- When to use Atomic Classes

  | Situation                                             | Use Atomic Classes? |
  |-------------------------------------------------------|---------------------|
  | You need to update a variable from multiple threads   | ✅ Yes               |
  | You want better performance than `synchronized`       | ✅ Yes               |
  | You don’t need full control like with `ReentrantLock` | ✅ Yes               |
- Key Methods of AtomicInteger (similar for others)

  | Method                          | Description                               |
  |---------------------------------|-------------------------------------------|
  | `get()`                         | Get current value                         |
  | `set(value)`                    | Set value                                 |
  | `incrementAndGet()`             | Add 1 and return new value                |
  | `getAndIncrement()`             | Return current value, then add 1          |
  | `addAndGet(x)`                  | Add `x` and return new value              |
  | `compareAndSet(expect, update)` | If current value == expect, set to update |
```java
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static AtomicInteger count = new AtomicInteger(0);
    
    public static void main(String[] args) {
        Runnable Task = () -> {
            for (int i = 0; i < 100; i++) {
                count.incrementAndGet();
            }
        };
        
        Thread t1 = new Thread(Task);
        Thread t2 = new Thread(Task);
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {}
        
        System.out.println("Final Count: " + count.get());
    }
}
```
## 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
- Concurrent collections are special versions of standard Java data Structures (like List, Map, Queue) that are designed to be thread-safe.
- Common Concurrent Collections in Java

  | Data Structure Type | Concurrent Version                          | Description                                     |
  |---------------------|---------------------------------------------|-------------------------------------------------|
  | `List`              | `CopyOnWriteArrayList`                      | Good for frequent reads, rare writes            |
  | `Set`               | `CopyOnWriteArraySet`                       | Thread-safe set based on `CopyOnWriteArrayList` |
  | `Map`               | `ConcurrentHashMap`                         | High-performance concurrent `Map`               |
  | `Queue`             | `ConcurrentLinkedQueue`                     | Non-blocking FIFO queue                         |
  | `Deque`             | `ConcurrentLinkedDeque`                     | Non-blocking double-ended queue                 |

```java
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

Map<String, Integer> map = new ConcurrentMap<>();
CopyOnWriteArrayList<Sting> list = new CopyOnWriteArrayList<>();
```

## 20. What kind of locks do you know? What is the advantage of each lock?
- synchronized 
  - Build-in locking mechanism via synchronized keyword.
  - Simple and easy to use.
  - Automatically release lock.
  - Works well for basic mutual exclusion.
- ReentrantLock
  - A class from java.util.concurrent.locks.
  - Interruptible lock attempts.
  - Try ot acquire lock with/without timeout.
- ReadWriteLock
  - Allow multiple readers or one writer.
  - Useful for read-heavy data structures.
  - Better concurrency: multiple threads can read simultaneously.
  - Excusive access for writes.
- StampedLock
  - No locking if data isn't modified
  - Higher performance for read-mostly cases.
  - Less blocking than ReadWriteLock
- Semaphore (leetcode used a lot)
  - Controls access to a fixed number of resources.
  - Useful for resource pooling
  - Can allow multiple threads at once
  
## 21. What is future and completableFuture? List some main methods of CompletableFuture.
- A Future is an object that represents the result of an asynchronous computation - something that might nobe be ready yet, but will be ready in the future. It's part of java.util.concurrent package.
- CompletableFuture is more powerful than Future as it supports non-blocking operations and callback chains.
- Methods of CompletableFuture.

  | Method                  | Description                                 |
  |-------------------------|---------------------------------------------|
  | `supplyAsync(Supplier)` | Starts a task that returns a result         |
  | `runAsync(Runnable)`    | Starts a task that returns nothing (`void`) |
  | `thenApply(fn)`         | Transforms result when it's done            |
  | `thenAccept(Consumer)`  | Consumes result (but doesn't return)        |
  | `thenCombine(f2, fn)`   | Combine two futures                         |
  | `exceptionally(fn)`     | Handle exceptions gracefully                |
  | `complete(value)`       | Manually complete the future                |

## 22. Type the code by yourself and try to understand it. (package com.chuwa.tutorial.t08_multithreading)
Done

## 23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
- One solution use synchronized and wait notify
  - In coding part
- One solution use ReentrantLock and await, signal
  - In coding part
  - 
## 24 create 3 threads, one thread output 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)
- In coding part

## 25. completable future:
1. Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum
   and product of two integers, and print the results.
- In coding part
2. Homework 2: Assume there is an online store that needs to fetch data from three APIs: products,
   reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched
   data for further processing. (需要找public api去模拟，)
   1. Sign In to Developer.BestBuy.com
   2. Best Buy Developer API Documentation (bestbuyapis.github.io)
   3. 可以⽤fake api https://jsonplaceholder.typicode.com/
   4. Github public api: https://api.github.com/users/your-user-name/repos
- In coding part
3. Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API
   call, return a default value and log the exception information.
- In coding part