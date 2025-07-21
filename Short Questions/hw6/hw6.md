# Multithreading Assignment

1. Read: [https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock](https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock)

2. Write a thread-safe singleton class
```java
public class Singleton {

    // Private constructor prevents instantiation from other classes
    private Singleton() {}

    // Static inner class - inner classes are not loaded until they are referenced.
    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    // Global access point
    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}
```
3. How to create a new thread (Please also consider Thread Pool approach)?
```java
// 3.1 extend  thread class
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running via Thread class.");
    }
}

public class Main {
    public static void main(String[] args) {
        Thread t = new MyThread();
        t.start(); // start the new thread
    }
}

//3.2 inplement runnale interface
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread running via Runnable interface.");
    }
}

public class Main {
    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();
    }
}

//3.3 Using Lambda with Runnable (Java 8+)
public class Main {
    public static void main(String[] args) {
        Thread t = new Thread(() -> System.out.println("Thread using lambda."));
        t.start();
    }
} 

//3.4 Using Thread Pool (ExecutorService)
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task = () -> System.out.println("Thread running in thread pool.");

        executor.submit(task);
        executor.shutdown(); // Don't forget to shut down the pool
    }
}

// 3.5 implement callable
class MyCallable implements Callable {
    public void call() throws Exception {
        System.out.println("Thread running via Callable interface.");
    }
}

```
4. Difference between Runnable and Callable?
- Callable has a return value and expect checked exception , but Runnable does not have one.


5. What is the difference between t.start() and t.run()?
- start() create a new thread and execute, run() execute in the current thread

6. Which way of creating threads is better: Thread class or Runnable interface?
- Runnable is better then extending class from thread. Because reasons below:
  - Allows multiple inheritance: You can implement Runnable while extending another class
  - Decouples task from thread: Logic is separated from thread mechanics. 
  - Better for thread pools: Works smoothly with ExecutorService, Callable, etc. 
  - More flexible and scalable

7. What are the thread statuses?
- new / runnable/ blocked/ waiting/ time_waiting/ terminated

8. Demonstrate deadlock and how to resolve it in Java code.
- A wait for B, B wait for A
- Both threads acquire locks in the same order, avoiding circular waiting.

9. How do threads communicate each other
- wait-notify mechanism, for instance, wait(), notify(), notifyAll()

10. What’s the difference between class lock and object lock?
- Object Lock:
The lock is associated with a specific instance of the class. Only one thread can execute a synchronized block/method on that instance at a time.
- Class Lock:
The lock is associated with the Class object itself (ClassName.class), and is shared across all instances. Only one thread can execute a synchronized static method or block on the class at a time.

11. What is join() method?
- The join() method in Java is used to pause the execution of the current thread until another thread has finished executing.

12. What is yield() method?
- the current thread is willing to pause its execution to let other threads of the same priority run.

13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
- A ThreadPool is a pool (collection) of worker threads that are reused to execute multiple tasks. It helps in managing resources efficiently 
- FixedThreadPool/CachedThreadPool/SingleThreadExecutor
- The TaskQueue is usually a BlockingQueue (e.g. LinkedBlockingQueue) that holds submitted tasks waiting to be executed by a thread.

14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
- Java provides the java.util.concurrent library to create and manage thread pools efficiently.
- Future<?> submit(Runnable task);
  <T> Future<T> submit(Callable<T> task);
  void shutdown();
  List<Runnable> shutdownNow();
  boolean isShutdown();
  boolean isTerminated();

15. How to submit a task to ThreadPool?
-   <T> Future<T> submit(Callable<T> task);

16. What is the advantage of ThreadPool?
- better performance + resource management
- allow task queue and manage thread lifecycle
- flexible error handling

17. Difference between shutdown() and shutdownNow() methods of executor
- Here is the plain text version for copy-paste:

⸻

Difference between shutdown() and shutdownNow() methods of ExecutorService:
- shutdown() initiates an orderly shutdown and would wait for the previous task finished. Meanwhile, it will reject all the other task request.
- shutdownNow() attempts to stop all actively executing tasks and returns a list of tasks that were submitted but not started.

18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
-  They are classes designed to perform atomic (indivisible) operations like increment, update, or compare-and-set.
- AtomicInteger/ AtomicLong/ AtomicBoolean/ AtomicReference<T>
- ```java
  // addAndGet(int delta)
  //incrementAndGet()
  //getAndSet(int newValue)
  //getAndIncrement()
    AtomicInteger ai = new AtomicInteger(10);
    int result = ai.addAndGet(5); //15
    int result = ai.incrementAndGet(); //  16
    int old = ai.getAndSet(200);
    System.out.println(old);          //  16
    System.out.println(ai.get());     //  200
    int old2 = ai.getAndIncrement();  // 200 201
    System.out.println(old2);         // 200
    System.out.println(ai.get());    // 201
    ```

19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
- CopyOnWriteArrayList/ ConcurrentHashMap/CopyOnWriteArraySet/ArrayBlockingQueue/LinkedBlockingQueue/LinkedBlockingDeque

20. What kind of locks do you know? What is the advantage of each lock?
- Here’s a concise and clear answer for:

20. What kind of locks do you know? What is the advantage of each lock?
- ReentrantLock: More flexible than synchronized./Can try to acquire the lock (tryLock()). /Supports timed lock acquisition. /Supports fair locking (FIFO). /Supports interruptible lock acquisition.
- ReadWriteLock: Multiple threads can read simultaneously. /Write locks are exclusive. /Great for read-heavy systems.
- StampedLock:Supports optimistic reading without locking. /Improves performance when writes are rare.

21. What is future and completableFuture? List some main methods of CompletableFuture.
- future is a object that represents the result of an asynchronous computation.
- CompletableFuture is an advanced version of Future which supports Non-blocking async computation.
```java
CompletableFuture.supplyAsync(() -> "Hello")
.thenApply(s -> s + " World")
.thenAccept(System.out::println); // Output: Hello World
```

22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)

23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)

- One solution use synchronized and wait notify
```java
public class OddEvenPrinterSync {
    private int count = 1;
    private final int MAX = 10;

    public void printOdd() {
        synchronized (this) {
            while (true) {
                if (count > MAX) break;
                if (count % 2 == 1) {
                    System.out.println("Odd: " + count);
                    count++;
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException ignored) {}
                }
            }
        }
    }

    public void printEven() {
        synchronized (this) {
            while (true) {
                if (count > MAX) break;
                if (count % 2 == 0) {
                    System.out.println("Even: " + count);
                    count++;
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException ignored) {}
                }
            }
        }
    }

    public static void main(String[] args) {
        OddEvenPrinterSync printer = new OddEvenPrinterSync();
        new Thread(printer::printOdd).start();
        new Thread(printer::printEven).start();
    }
}
```
- One solution use ReentrantLock and await, signal
```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinterLock {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int count = 1;
    private static final int MAX = 10;

    public void printOdd() {
        while (true) {
            lock.lock();
            try {
                if (count > MAX) break;
                if (count % 2 == 1) {
                    System.out.println("Odd: " + count);
                    count++;
                    condition.signal();
                } else {
                    condition.await();
                }
            } catch (InterruptedException ignored) {
            } finally {
                lock.unlock();
            }
        }
    }

    public void printEven() {
        while (true) {
            lock.lock();
            try {
                if (count > MAX) break;
                if (count % 2 == 0) {
                    System.out.println("Even: " + count);
                    count++;
                    condition.signal();
                } else {
                    condition.await();
                }
            } catch (InterruptedException ignored) {
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        OddEvenPrinterLock printer = new OddEvenPrinterLock();
        Thread oddThread = new Thread(printer::printOdd);
        Thread evenThread = new Thread(printer::printEven);

        oddThread.start();
        evenThread.start();
    }
}
```
