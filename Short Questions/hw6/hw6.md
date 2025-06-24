### hw6
### 2. Write a thread-safe singleton class.
Double-Checked Locking Singleton - Lazy + Efficient:
```java
public class DoubleCheckedSingleton {

    // `volatile` ensures visibility across threads and prevents reordering
    private static volatile DoubleCheckedSingleton instance;

    // Private constructor prevents instantiation from outside 
    private DoubleCheckedSingleton() {}

    public static DoubleCheckedSingleton getInstance() {
        // First check (no locking) — fast path for already-initialized instance
        if (instance == null) {
            // Synchronize only the first time
            synchronized (DoubleCheckedSingleton.class) {
                // Second check (with locking) — handles multi-thread race condition
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
}
```
Why Thread-Safe?
1. `volatile` keyword:
    - Prevents instruction reordering during object creation.
    - Ensures all threads see a fully constructed object.
    - Without it, a thread might see a partially initialized object.

2. Double-checking:
    - Avoids locking once the instance is initialized.
    - Locking only happens once per JVM.

3. JVM Guarantee:
    - Class-level lock on `DoubleCheckedSingleton.class` ensures only one thread can enter the critical section during initialization.
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
### 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?


---
### 15. How to submit a task to ThreadPool?


---
### 16. What is the advantage of ThreadPool?


---
### 17. Difference between shutdown() and shutdownNow() methods of executor


---
### 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?


---
### 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)


---
### 20. What kind of locks do you know? What is the advantage of each lock?


---
### 21. What is future and completableFuture? List some main methods of ComplertableFuture.


---
### 22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)


---
### 23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. 
#### (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
#### 1. One solution use synchronized and wait notify
#### 2. One solution use ReentrantLock and await, signal



---
### 24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random. 
#### (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)



----
### 25. completable future: