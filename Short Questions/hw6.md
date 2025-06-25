### HW 6
#### 1. What’s the difference between class lock and object lock?
In Java, the key difference between class lock and object lock lies in their scope and how they're acquired:

* **Object Lock** is associated with an instance of a class. It's acquired when a thread enters a `synchronized` instance method or a block synchronized on an object. It ensures that only one thread can execute a synchronized instance method on the same object at a time.

* **Class Lock** is associated with the `Class` object itself. It's acquired when a thread enters a `synchronized static` method or a block synchronized on `ClassName.class`. It ensures mutual exclusion at the class level, affecting all instances of that class.

In short:

* **Object Lock** → controls access to instance-level synchronized code.
* **Class Lock** → controls access to static-level synchronized code.

#### 2. Write a thread-safe singleton class
- see Coding 

#### 3. How to create a new thread (also consider Thread Pool approach)?
1. Extending the Thread Class
```java
class MyThread extends Thread {
    public void run() {
        // Code that will run in the new thread
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread: " + Thread.currentThread().getName() + ", i = " + i);
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Using the thread
public class Main {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        
        thread1.start(); // Start the thread
        thread2.start();
    }
}
```
2. Implementing the Runnable Interface (Preferred)
```java
class MyRunnable implements Runnable {
    public void run() {
        // Code that will run in the new thread
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread: " + Thread.currentThread().getName() + ", i = " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Using the runnable
public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new Thread(new MyRunnable());
        
        thread1.start();
        thread2.start();
    }
}
```
3. Using Lambda Expressions (Java 8+). Since Runnable is a functional interface, we can use lambda expressions:
```java
public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Lambda thread: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        thread.start();
    }
}
```
4. Using ThreadPool 
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {
    public static void main(String[] args) {
        // Create a pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Submit 10 tasks
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " running on thread: " + 
                    Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        // Shutdown the executor
        executor.shutdown();
    }
}
```
- Fixed Thread Pool: Creates a pool with a fixed number of threads
- Cached Thread Pool: Creates new threads as needed, but reuses previously constructed threads `Executors.newCachedThreadPool()`
- Single Thread Executor `Executors.newSingleThreadExecutor()`
- Scheduled Thread Pool
  
#### 4. Difference between Runnable and Callable?
**Runnable** and **Callable** are both functional interfaces used for defining tasks that can be executed by threads

**Return Value:**
- `Runnable` has a `void run()` method that doesn't return any value
- `Callable` has a `V call()` method that returns a result of type V

**Exception Handling:**
- `Runnable` cannot throw checked exceptions (only unchecked RuntimeExceptions)
- `Callable` can throw checked exceptions, declared as `throws Exception`

**Usage with ExecutorService:**
- `Runnable` can be submitted to ExecutorService using `execute()` or `submit()`, returning a Future<?> with null result
- `Callable` is submitted using `submit()`, returning a Future<V> containing the actual result



**When to use:**
- Use `Runnable` for fire-and-forget tasks where you don't need a result
- Use `Callable` when you need to return a value or handle checked exceptions from the task.
-  In practice, `Callable` is preferred in modern concurrent applications because it provides more flexibility with result handling and exception propagation through the Future interface.

#### 5. What is the difference between t.start() and t.run()
- Thread.start()	    
  - Creates a new thread and the `run()` method is executed on the newly created thread.	
  - It can't be invoked more than one time otherwise throws `java.lang.IllegalStateException	`
  - Defined in `java.lang.Thread` class.	
- Thread.run()
  - No new thread is created and the `run()` method is executed on the calling thread itself.
  - Multiple invocations are possible.
  - Defined in `java.lang.Runnable` interface and must be overridden in the implementing class.
  
#### 6. Which way of creating threads is better: Thread class or Runnable interface?
Using the Runnable interface is generally better. It promotes decoupling of task logic from thread management, allows your class to extend another class (since Java supports single inheritance), and is more flexible—especially in modern applications where tasks are often submitted to executor services rather than manually managed threads.

#### 7. What are the thread statuses?

1. **NEW** – The thread is created but not yet started.
2. **RUNNABLE** – The thread is ready to run and is either running or waiting for CPU time.
3. **BLOCKED** – The thread is waiting to acquire a monitor lock to enter a synchronized block/method.
4. **WAITING** – The thread is waiting indefinitely for another thread to perform a specific action (e.g., `Object.wait()`, `Thread.join()` without timeout).
5. **TIMED\_WAITING** – The thread is waiting for a specified amount of time (e.g., `Thread.sleep()`, `join(timeout)`, `wait(timeout)`).
6. **TERMINATED** – The thread has completed execution or was aborted.

These states are defined in the `java.lang.Thread.State` enum and can be queried using `Thread.getState()`.
- In Java, calling join() on a thread does not block the thread itself — it blocks the calling thread, waiting for the target thread to finish.


#### 8. Demonstrate deadlock and how to resolve it in Java code.
```java
// Deadlock Example - Two threads waiting for each other's locks
class DeadlockExample {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();
    
    public static void main(String[] args) {
        // Thread 1: Acquires lock1 first, then lock2
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: Acquired lock1");
                
                // Simulate some work
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                
                System.out.println("Thread 1: Waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread 1: Acquired lock2");
                }
            }
        });
        
        // Thread 2: Acquires lock2 first, then lock1
        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2: Acquired lock2");
                
                // Simulate some work
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                
                System.out.println("Thread 2: Waiting for lock1");
                synchronized (lock1) {
                    System.out.println("Thread 2: Acquired lock1");
                }
            }
        });
        
        thread1.start();
        thread2.start();
        
        // This will deadlock - Thread 1 holds lock1 and waits for lock2
        // Thread 2 holds lock2 and waits for lock1
    }
}

// Solution 1: Lock Ordering - Always acquire locks in the same order
class Solution1_LockOrdering {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();
    
    public static void main(String[] args) {
        // Both threads acquire locks in the same order: lock1 -> lock2
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: Acquired lock1");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                
                synchronized (lock2) {
                    System.out.println("Thread 1: Acquired lock2");
                }
            }
        });
        
        Thread thread2 = new Thread(() -> {
            synchronized (lock1) {  // Same order as thread1
                System.out.println("Thread 2: Acquired lock1");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                
                synchronized (lock2) {
                    System.out.println("Thread 2: Acquired lock2");
                }
            }
        });
        
        thread1.start();
        thread2.start();
    }
}

// Solution 2: Using tryLock with ReentrantLock
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

class Solution2_TryLock {
    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();
    
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> acquireLocks("Thread 1", lock1, lock2));
        Thread thread2 = new Thread(() -> acquireLocks("Thread 2", lock2, lock1));
        
        thread1.start();
        thread2.start();
    }
    
    private static void acquireLocks(String threadName, ReentrantLock first, ReentrantLock second) {
        while (true) {
            boolean acquiredFirst = false;
            boolean acquiredSecond = false;
            
            try {
                acquiredFirst = first.tryLock(50, TimeUnit.MILLISECONDS);
                if (acquiredFirst) {
                    acquiredSecond = second.tryLock(50, TimeUnit.MILLISECONDS);
                    
                    if (acquiredSecond) {
                        System.out.println(threadName + ": Acquired both locks");
                        // Do work
                        Thread.sleep(100);
                        return; // Success, exit loop
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            } finally {
                if (acquiredSecond) second.unlock();
                if (acquiredFirst) first.unlock();
            }
            
            // If couldn't acquire both locks, retry after a brief pause
            try { Thread.sleep(10); } catch (InterruptedException e) {}
        }
    }
}

// Solution 3: Single Lock for Related Resources
class Solution3_SingleLock {
    private static final Object sharedLock = new Object();
    private static int resource1 = 0;
    private static int resource2 = 0;
    
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (sharedLock) {
                System.out.println("Thread 1: Accessing both resources");
                resource1++;
                resource2++;
            }
        });
        
        Thread thread2 = new Thread(() -> {
            synchronized (sharedLock) {
                System.out.println("Thread 2: Accessing both resources");
                resource1--;
                resource2--;
            }
        });
        
        thread1.start();
        thread2.start();
    }
}

// Solution 4: Using java.util.concurrent utilities
import java.util.concurrent.Semaphore;

class Solution4_Semaphore {
    private static final Semaphore semaphore = new Semaphore(1);
    private static int sharedResource1 = 0;
    private static int sharedResource2 = 0;
    
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("Thread 1: Acquired semaphore");
                sharedResource1++;
                sharedResource2++;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release();
            }
        });
        
        Thread thread2 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("Thread 2: Acquired semaphore");
                sharedResource1--;
                sharedResource2--;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release();
            }
        });
        
        thread1.start();
        thread2.start();
    }
}
```


#### 9. How do threads communicate each other?
In Java, threads communicate with each other primarily through **shared memory** and **synchronization mechanisms**. The most common approach is using **`wait()`**, **`notify()`**, and **`notifyAll()`** methods, which are available on every Java object as part of the `Object` class. These methods are used within synchronized blocks or methods to allow threads to wait for a condition and notify other threads when that condition is met.

Additionally, higher-level concurrency utilities from the `java.util.concurrent` package, such as `BlockingQueue`, `CountDownLatch`, `CyclicBarrier`, and `Semaphore`, provide more robust and scalable inter-thread communication mechanisms.

In summary, Java supports thread communication through:

* Low-level synchronization: `wait()`, `notify()`, `notifyAll()` with `synchronized`.
* High-level constructs: classes in `java.util.concurrent`.

#### 10. What’s the difference between class lock and object lock?
- see Q1

#### 11. What is join() method?
The `join()` method in Java is used to pause the execution of the current thread until the thread on which `join()` was called has finished executing. It is defined in the `Thread` class. This is commonly used to ensure that a thread completes before the next steps are taken in the main or calling thread.

**Example:**

```java
Thread t1 = new Thread(() -> {
    // some task
});
t1.start();
t1.join(); // main thread waits for t1 to finish
```

#### 12. what is yield() method
The `yield()` method in Java is a static method of the `Thread` class. It is used to signal to the thread scheduler that the current thread is willing to pause its execution and allow other threads of the same priority to execute. However, it does **not guarantee** that the current thread will be paused or that any other thread will be scheduled immediately.

```java
Thread.yield();
```

It’s primarily used for debugging or performance tuning, and its behavior is highly platform-dependent. It's not commonly relied upon for precise thread control.

#### 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
**ThreadPool** is a pool of worker threads maintained to execute multiple tasks concurrently. It helps manage system resources efficiently by reusing threads instead of creating a new one for each task, reducing overhead and improving performance.

There are **four main types of ThreadPool** in Java (as provided by `Executors` utility class):

1. **FixedThreadPool** – A pool with a fixed number of threads. Ideal when you know the number of threads upfront.
2. **CachedThreadPool** – Creates new threads as needed, and reuses previously constructed threads when available. Suitable for short-lived, asynchronous tasks.
3. **SingleThreadExecutor** – A single-threaded executor, ensuring tasks are executed sequentially in a single thread.
4. **ScheduledThreadPool** – Allows scheduling tasks to run after a delay or periodically.

The **TaskQueue** (typically a `BlockingQueue<Runnable>`) is a critical component in the ThreadPoolExecutor. It holds the submitted tasks waiting to be executed. If all threads are busy, incoming tasks are stored in this queue until a thread becomes available. The queue type (e.g., `LinkedBlockingQueue`, `ArrayBlockingQueue`, or `SynchronousQueue`) influences the pool's behavior regarding task handling and resource management.
```java
import java.util.concurrent.*;

public class TaskQueueExample {
    public static void main(String[] args) {
        // Core threads: 2, Max threads: 4, Keep-alive time: 10s
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2,                      // corePoolSize
            4,                      // maximumPoolSize
            10, TimeUnit.SECONDS,  // keepAliveTime
            taskQueue              // task queue
        );

        // Submit 5 tasks
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.execute(() -> {
                System.out.println("Running Task " + taskId + " on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000); // simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
    }
}
```

#### 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
In Java, the **`java.util.concurrent`** package provides the necessary library support to create thread pools.

Specifically:

* The **`Executors`** class is commonly used to create thread pools using factory methods like `Executors.newFixedThreadPool()`, `newCachedThreadPool()`, etc.
* The **`ExecutorService`** interface provides the main functions of a thread pool, such as `submit()`, `invokeAll()`, `shutdown()`, and more. (see Q3.4)

For advanced control, `ThreadPoolExecutor` is the core implementation class that allows fine-grained configuration. (see Q13)

#### 15. How to submit a task to ThreadPool?
You can submit a task to a `ThreadPool` in Java using the `ExecutorService` interface. There are two primary methods:

1. **`execute(Runnable task)`** – submits a task for execution but does not return a result.
2. **`submit(Callable<T> task)` or `submit(Runnable task)`** – submits a task and returns a `Future` object representing the result.

**Example:**

```java
ExecutorService executor = Executors.newFixedThreadPool(5);

// Using execute (no result expected)
executor.execute(() -> System.out.println("Task executed"));

// Using submit (with result)
Future<Integer> future = executor.submit(() -> {
    return 42;
});

executor.shutdown();
```

After submitting tasks, it's good practice to shut down the executor, This ensures proper resource cleanup.


#### 16. What is the advantage of ThreadPool?
* **Improved performance** by reusing a fixed number of threads, reducing the overhead of thread creation and destruction.
* **Better resource management** by limiting the number of concurrent threads, preventing system overload.
\
* **Task scheduling and queueing**, allowing efficient handling of a large number of short-lived tasks.
* **Simplified concurrency control**, as thread pools handle thread lifecycle and management internally.
* **Enhanced scalability** by tuning pool size based on workload and system capacity.

#### 17. Difference between shutdown() and shutdownNow() methods of executor in ExecutorService

* `shutdown()` initiates an orderly shutdown where previously submitted tasks are executed, but no new tasks are accepted. It waits for running tasks to complete.

* `shutdownNow()` attempts to stop all actively executing tasks immediately and returns a list of tasks that were awaiting execution but not started. It interrupts running threads but does not guarantee their immediate termination.

#### 18. 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic  classes and its main methods. when to use it?
Atomic classes in Java are part of the `java.util.concurrent.atomic` package. They provide a way to perform lock-free, thread-safe operations on single variables. Atomic classes use low-level CPU instructions like Compare-And-Swap (CAS) to guarantee atomicity, which helps avoid synchronization overhead and improves performance in concurrent environments.

**Some commonly used atomic classes are:**
* `AtomicInteger`
* `AtomicLong`
* `AtomicBoolean`
* `AtomicReference<V>`
* `AtomicStampedReference<V>`
* `AtomicIntegerArray`
* `AtomicLongArray`
* `AtomicReferenceArray<E>`

**Code Example (AtomicInteger)**

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();  // atomically increments by 1
    }

    public int getCount() {
        return count.get();
    }

    public static void main(String[] args) {
        AtomicExample example = new AtomicExample();
        example.increment();
        System.out.println("Count: " + example.getCount());
    }
}
```
**Code Example (compareAndSet/CAS)**
```java
import java.util.concurrent.atomic.AtomicInteger;

public class CASMultithreadExample {

    private static AtomicInteger atomicCounter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            int oldValue, newValue;
            do {
                oldValue = atomicCounter.get();
                newValue = oldValue + 1;
                // Attempt to update the counter atomically
            } while (!atomicCounter.compareAndSet(oldValue, newValue));
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final counter value: " + atomicCounter.get());
    }
}

```

**Main Methods**

* `get()` - returns the current value
* `set(value)` - sets to given value
* `incrementAndGet()` - atomically increments by 1 and returns updated value
* `decrementAndGet()` - atomically decrements by 1 and returns updated value
* `compareAndSet(expectedValue, newValue)` - atomically sets the value if current value equals expectedValue, returns boolean
* `getAndSet(newValue)` - atomically sets to new value and returns old value

**When to Use Atomic Classes**

Use atomic classes when you need to perform thread-safe operations on single variables without the overhead of locks or synchronized blocks. They are ideal for counters, flags, or references that are updated frequently by multiple threads concurrently and require atomicity guarantees. For more complex compound actions, you may still need explicit synchronization or higher-level concurrency utilities.


#### 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
Concurrent collections in Java are specialized data structures designed to handle concurrent access by multiple threads safely **without** corrupting the data or requiring **explicit synchronization** by the developer. They provide built-in thread safety and high concurrency performance by using fine-grained locking or lock-free algorithms.


* **ConcurrentHashMap**: A high-performance, thread-safe implementation of a hash map that allows concurrent reads and updates with minimal contention.
* **CopyOnWriteArrayList**: A thread-safe variant of ArrayList where all mutative operations result in a new copy of the underlying array, making it ideal for scenarios with many reads and few writes.
* **ConcurrentLinkedQueue**: A non-blocking, thread-safe queue based on linked nodes, suitable for high-throughput concurrent access.
* **ConcurrentSkipListMap** and **ConcurrentSkipListSet**: Sorted, thread-safe map and set implementations based on skip lists, providing expected logarithmic time complexity for most operations.
* **BlockingQueue implementations** such as **LinkedBlockingQueue**, **ArrayBlockingQueue**, and **PriorityBlockingQueue**: Thread-safe queues that support blocking operations for producer-consumer scenarios.

These collections are part of the `java.util.concurrent` package and are preferred over manually synchronized collections for scalable concurrent applications.


#### 20. What kind of locks do you know? What is the advantage of each lock?


1. **Intrinsic Locks (Synchronized blocks/methods)**

   * **Advantage:** Simple to use and built into the JVM; no explicit lock management needed.
   * **Use case:** When basic mutual exclusion is sufficient.

2. **ReentrantLock (from `java.util.concurrent.locks`)**

   * **Advantage:** Provides more advanced features like tryLock (non-blocking attempt), timed lock waits, and the ability to interrupt while waiting for a lock. Also supports fairness policies.
   * **Use case:** When you need more control over locking behavior than `synchronized` offers.

3. **ReadWriteLock (e.g., ReentrantReadWriteLock)**

   * **Advantage:** Separates read and write locks, allowing multiple readers simultaneously but exclusive access for writers, improving performance in read-heavy scenarios.
   * **Use case:** When you have many read operations and fewer writes.

4. **StampedLock**

   * **Advantage:** Offers optimistic and pessimistic locking, which can improve throughput by allowing reads without blocking unless a write occurs.
   * **Use case:** When high-performance read/write access is required, especially in scenarios with more reads than writes.

#### 21. What is future and completableFuture? List some main methods of CompletableFuture.
**Future** and **CompletableFuture** are both used for asynchronous programming in Java:

* **Future** (introduced in Java 5) represents the result of an asynchronous computation. You can check if it's done, wait for it to complete, and retrieve the result. However, it lacks chaining and composing capabilities and doesn’t support callbacks easily.

* **CompletableFuture** (introduced in Java 8) is an enhanced implementation that supports a wide range of functional programming features. It allows you to explicitly complete it, chain multiple asynchronous computations, combine multiple futures, and register callbacks to execute when the computation completes.


Main methods of `CompletableFuture`:

* `supplyAsync(Supplier<U> supplier)` — starts an async computation that returns a result.
* `runAsync(Runnable runnable)` — starts an async computation without a result.
* `thenApply(Function<T, U> fn)` — transforms the result when the future completes.
* `thenAccept(Consumer<T> action)` — consumes the result without returning a value.
* `thenRun(Runnable action)` — executes a Runnable after completion, ignoring the result.
* `thenCombine(CompletionStage<U> other, BiFunction<T, U, V> fn)` — combines results of two futures.
* `thenCompose(Function<T, CompletionStage<U>> fn)` — chains futures sequentially.
* `exceptionally(Function<Throwable, T> fn)` — handles exceptions.
* `complete(T value)` — manually completes the future.
* `get() / join()` — waits for the future to complete and returns the result.

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Start an async task that returns a string after some processing
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // Simulate long-running task
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Hello from CompletableFuture!";
        });

        // Transform the result when it arrives
        CompletableFuture<String> greetingFuture = future.thenApply(result -> result + " Have a great day!");

        // Block and get the final result
        String message = greetingFuture.get();

        System.out.println(message);
    }
}
```
#### 22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)
- Done

#### 23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. 
(solution is in ` com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter`)  
1. One solution use synchronized and wait notify  
2. One solution use ReentrantLock and await, signal
- see Coding 

#### 24. create 3 threads, one thread output 1-10, one thread output 11-20, one thread output 21-22. threads run  sequence is random. 
(solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)
- see Coding

#### 25. completable future
##### hw1. Write a simple program that uses CompletableFuture to asynchronously get the sum  and product of two integers, and print the results.


##### hw2. Assume there is an online store that needs to fetch data from three APIs: products,  reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched  data for further processing. (需要找public api去模拟)


##### hw3. For hw2, implement exception handling. If an exception occurs during any API  call, return a default value and log the exception information.

- see Coding 