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





---
### 7. What are the thread statuses?


---
### 8. Demonstrate deadlock and how to resolve it in Java code.


---
### 9. How do threads communicate each other?


---
### 10. What’s the difference between class lock and object lock?


---
### 11. What is join() method?


---
### 12. what is yield() method


---
### 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?


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