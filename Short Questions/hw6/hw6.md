# 6.23 HW6 - MultiThreading

1. Read: https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock

2. Write a thread-safe singleton class.

   Thread-safe singleton class can be both eager-loading and lazy loading.

   ```java
   public class EagerLoading {
       private static final EagerLoading instance = new EagerLoading();
       private EagerLoading() {}
       public static EagerLoading getInstance() {
           return instance;
       }
   }
   ```

   ```java
   // inner class
   public class LazyLoading {
       private LazyLoading() {}
       private static class SingletonHolder {
           private static final LazyLoading instance = new LazyLoading();
       }
       public static LazyLoading getInstance() {
           return SingletonHolder.instance;
       }
   }
   ```

   ```java
   // double-checked locking
   public class LazyLoading {
       private static volatile LazyLoading instance;
       private LazyLoading() {}
       public static LazyLoading getInstance() {
           if (instance == null) {
               synchronized (LazyLoading.class) {
                   if (instance == null) {
                       instance = new LazyLoading();
                   }
               }
           }
           return instance;
       }
   }
   ```

   

3. How to create a new thread (Please also consider Thread Pool approach)?

   1. Extends Thread class

      Simplest and provides comprehensive methods that a thread needs.

   2. Implements Runnable

      Preferred when the class already extends another class.

   3. Implements Callable 

      Like Runnable, but it can return a result and throw exceptions. Must be used with `FutureTask` or `ExecutorService`.

   4. Thread Pool

      Used to reuses pre-created threads for multiple tasks, which is more efficient compared to creating a new thread for each task.

      [ThreadCreation.java](../../Coding/hw6/ThreadCreation.java)
      
      

4. Difference between Runnable and Callable?

   They are both interfaces used to define tasks that can be executed by threads.

   - Runnable:
     - a single `run()` method with
     - a `void` return type: no return value
     - can’t throw exceptions

   - Callable:

     - a single  `call()` method 

     - can return a value of a generic type

     - can throw exceptions

       

5. What is the difference between t.start() and t.run()?

   - t.start():

     Used to start the execution of **a new thread**. The JVM creates a new thread, and that thread executes the code inside the `run()` method.

   - t.run():

     Directly calls the `run()` method **on the current thread**, just like a normal method call. It does **not** create a new thread.

     ```java
     public class MyThread extends Thread {
         public void run() {
             System.out.println("Current thread: " + Thread.currentThread().getName());
         }
     
         public static void main(String[] args) {
             MyThread t = new MyThread();
             t.start(); // JVM 创建新线程执行 run()
             System.out.println("Main thread: " + Thread.currentThread().getName());
         }
     }
     
     //output:
     //Main thread: main
     //Current thread: Thread-0
     ```

     

6. Which way of creating threads is better: Thread class or Runnable interface?

   Use Thread class to create threads is simple and it have all the access to thread methods and attributes, but it lacks flexibility - the class which extends Thread class cannot extend any other class.

   Use Runnable interface to create threads can support muiltiple inheritance, which is more flexible. And it **separates the task logic** (in the run() method) **from the thread management** (start(), sleep(), interrupt() handled by the Thread class). So I think this way is better.

   

7. What are the thread statuses?

   1. **New State**

      When a thread is **created**. The thread has not started to run in this state.

   2. **Runnable State**

      When a thread is **ready to run**. It is called runnable because at any given time, it could be either running or waiting for the next quantum of time from the thread scheduler(ready).

   3. **Blocked**

      When a thread is **trying to acquire a lock** to enter a `synchronized` block/method, but the lock is currently acquired by another thread.

   4. **Waiting**

      When a thread **waits for another thread to perform a particular action**(sending a notification or finishing execution), then it will move back to runnable.

      1. `Object.wait()`: causes the current thread to wait indefinitely until another thread calls `notify()` or `notifyAll()`. It must be called from within a `synchronized` block/method.
      2. `Thread.join()`: the current thread will wait until the thread on which `join()` is called finishes its execution
      3. `LockSupport.park()`: low-level method. The current thread will be blocked by the method. It will return if the thread is unparked by another thread.

   5. **Time Waiting**

      Same as the waiting state, but waiting for a specific amount of time version. When it calls a method with a **time-out parameter**. A thread lies in this state until the timeout is completed or until a notification is received.

   6. **Terminated**

       The thread has completed execution.

      

8. Demonstrate deadlock and how to resolve it in Java code.

   Deadlock occurs in Java when two or more threads are blocked forever, each waiting for the other to release a lock. This often happens with **nested `synchronized` blocks**. For example, if thread A holds lock 1 and tries to acquire lock 2, while thread B holds lock 2 and tries to acquire lock 1, they will both be stuck waiting for each other, which causes deadlock. To avoid deadlocks, the best practice is always **acquiring locks in the same order**.

   [DeadLock.java](../../Coding/hw6/DeadLock.java)

   

9. How do threads communicate each other?

   Threads can communicate using three methods: wait(), notify(), and notifyAll().

   wait() causes the current thread to wait indefinitely until another thread either invokes notify() for this object or notifyAll(). It must be called from within a synchronized block or method.

    [WaitNotifyDemo.java](../../Coding/hw6/WaitNotifyDemo.java)

   

10. What’s the difference between class lock and object lock?

    - **Object lock:** associated with an instance of a class, only the thread will be able to execute the code block on a given instance of the class

      Acquired by:

      - entering a `synchronized` **non-static method**
      - entering a `synchronized(this)` block

      ```java
      public class Example {
          public synchronized void instanceMethod() {
              // A synchronized instance method: Locks 'this' (the current object instance)
          }
      
          public void blockSync() {
              synchronized(this) {
                  // A synchronized(this) block, also locks 'this'
              }
          }
      }
      ```

      If `obj1` and `obj2` are two different instances:

      ```java
      Example obj1 = new Example();
      Example obj2 = new Example();
      
      Thread t1 = new Thread(() -> obj1.instanceMethod());
      Thread t2 = new Thread(() -> obj2.instanceMethod());
      ```

      `t1` and `t2` can run **concurrently** because they lock **different objects**.

    - **Class lock:** associated with the class

      Acquired by:

      - entering a `synchronized static` method
      - using `synchronized(Example.class)` block

      ```java
      public class Example {
          public static synchronized void staticMethod() {
              // Locks Example.class
          }
      
          public void blockClassLock() {
              synchronized(Example.class) {
                  // Also locks Example.class
              }
          }
      }
      ```

      Only **one thread** can execute any `synchronized static` method or block **per class**, regardless of how many instances exist.

      

11. What is join() method?

    When you call thread.join(), the current thread **will pause** its execution until the thread on which join() is called **finishes its execution **(Terminated). It does *not* release any locks.

    For example, if Main thread call thread1.join(), the Main thread will wait for thread1 completes its task, in the meanwhile, the main thread is blocked until thread1 teminated, then the Main thread will continue.

    ```java
    public class JoinTest {
        public static void main(String[] args) throws InterruptedException {
            Thread worker = new Thread(() -> {
                System.out.println("Worker thread starting...");
                try { Thread.sleep(2000); } catch (InterruptedException e) {}
                System.out.println("Worker thread finished.");
            });
    
            worker.start();
    
            System.out.println("Main thread waiting for worker to finish...");
            worker.join();  // Main thread pauses here until worker is done
            System.out.println("Main thread resumes after worker is done.");
        }
    }
    
    //outputs:
    //Main thread waiting for worker to finish...
    //Worker thread starting...
    //Worker thread finished.
    //Main thread resumes after worker is done.
    // "Main thread resumes after worker is done. " is always behind "Worker thread finished."
    
    ```

    

12. What is yield() method?

    It is a **static method** in the Thread class that gives a hint to to the thread scheduler that the current thread is **willing to pause** its execution and let other threads of the same or higher priority to run. It does *not* release any locks.

    

13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

    ThreadPool is a pool of pre-created reusable threads that can executes tasks concurrently. Instead of creating a new thread for every task, the tasks are submitted to the pool, and worker threads pick them up from a queue.

    **Types of ThreadPool:** in java.util.concurrent.Executors 

    | Type                            | Description                                                  |
    | ------------------------------- | ------------------------------------------------------------ |
    | `newFixedThreadPool(int n)`     | A fixed number of threads. Good for known workloads.         |
    | `newCachedThreadPool()`         | Creates threads as needed; reuses idle threads. Good for short-lived many tasks. |
    | `newSingleThreadExecutor()`     | Only one thread executes tasks sequentially.                 |
    | `newScheduledThreadPool(int n)` | Supports delayed and periodic task execution.                |

    **TaskQueue:** 

    Inside `ThreadPoolExecutor`(**actual implementation** used behind all `Executors.newXXX()` methods), tasks go into a TaskQueue (typically a BlockingQueue) before being picked up by threads.

    When you `submit()` a task:

    1. If a thread is available, it runs immediately.

    2. If all threads are busy:

       - If the queue has space, the task is added to the queue.

       - If the queue is full and max threads reached → task is rejected.

         

14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?

    In the `java.util.concurrent` package:

    - Library

      - Executors: A **factory class** for creating thread pools easily

      - ThreadPoolExecutor: The **core class** that actually implements thread pools

    - Interface:

      - ExecutorServicethe: **main interface** that defines the standard methods to work with thread pools

      

15. How to submit a task to ThreadPool?

    Two common ways to submit tasks to a thread pool:

    1. Using submit()

       - Accept a **Runnable(no return value) or Callable(returns a result)** task and returns a **Future** object, which can check if the task is complete, cancel the task, and retrieve the result when using Callable. It’s suitable for tasks where **return a result** or need tracking. 

       - When an exception occurs, it will **not throw it immediately**; the exception is captured and will only be thrown when you call Future.get().

         ```java
         ExecutorService executor = Executors.newFixedThreadPool(2);
         
         executor.submit(() -> {
             System.out.println("Running a task...");
         });
         
         ```

         ```java
         Callable<String> task = () -> {
             Thread.sleep(1000);
             return "Task Completed!";
         };
         
         Future<String> future = executor.submit(task);
         System.out.println(future.get());  // Blocks until result is available
         
         ```

    2. Using execute()

       - Can only accept **Runnable** task, so no return result, and the caller cannot track the task's outcome. It’s suitable for tasks where **we don't care about success or failure**.

       - When an exception occurs, it will **throw it immediately** in the caller thread or thread pool.
       
         ```java
         executor.execute(() -> System.out.println("Task via execute()"));
         ```
       
         

16. What is the advantage of ThreadPool?

    1. Reduce resource consumption
        Creating threads frequently is expensive. ThreadPool allows us to reuse existing threads, which reduces overhead and improves performance.

    2. Improves Response Time

       When a task arrives, it doesn't need to wait for a new thread to be created. It can be immediately executed by an available thread from the pool, which improves system responsiveness

    3. Enables Better Thread Management

       Threads are limited and expensive system resources. If we create too many threads without control, it can lead to resource waste. ThreadPool allows us to control the number of threads, configure queue size, apply rejection policies, and even monitor the thread usage.

       

17. Difference between shutdown() and shutdownNow() methods of executor.

    They are both methods used to control the termination of a thread pool of the `ExecutorService` interface.

    `shutdown()` allows previously submitted tasks(queued and running tasks) to complete before the executor shuts down, but no new tasks are accepted.

    `shutdownNow()` attempts to stop all actively running tasks immediately and prevent new tasks from being submitted.  It returns a list of tasks that were awaiting execution and were not started.

 

18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. When to use it?

    Atomic classes provides thread-safe operations on single variables without using synchronized or locks. They achieve thread safety using low-level, hardware-supported atomic operations (like Compare-And-Swap - CAS) rather than relying on locks.

    **Common types of Atomic classes:**

    - `AtomicInteger`: For atomic operations on an `int` value.
    - `AtomicLong`: For atomic operations on a `long` value.
    - `AtomicBoolean`: For atomic operations on a `boolean` value.
    - `AtomicReference`: For atomic operations on an object reference.
    - `AtomicIntegerArray`, `AtomicLongArray`, `AtomicReferenceArray`: For atomic operations on elements within arrays.
    - `LongAdder`, `DoubleAdder`, `LongAccumulator`, `DoubleAccumulator`: For efficient summation or accumulation in highly contended scenarios.

    **Common methods of Atomic classes:**

    - `get()`: Retrieves the current value of the atomic variable. 

    - `set(int newValue)`: Sets the value of the atomic variable to the specified `newValue`. 

    - `compareAndSet(int expect, int update)`: Atomically sets the value to `update` only if the current value is equal to `expect`. It returns `true` if the operation is successful, `false` otherwise. 

    - `getAndSet(int newValue)`: Atomically sets the value to `newValue` and returns the previous value.

    - `incrementAndGet()`: Atomically increments the current value by one and returns the new value.

    - `getAndIncrement()`: Atomically returns the previous value then increments the current value by one

    - `decrementAndGet()`: Atomically decrements the current value by one and returns the new value.

    - `getAndDecrement()`: Atomically returns the previous value then decrements the current value by one.

      ```java
      AtomicInteger count = new AtomicInteger(0);
      
      // Increment
      int a = count.incrementAndGet();     // ++count: count = 1, a = 1
      int b = count.getAndIncrement();     // count++: count = 2, b = 1
      
      // Decrement
      int d = count.decrementAndGet();    // --count: count = 1, c = 1
      int e = count.getAndDecrement();    // count--: count = 0, c = 1
      
      // Add
      count.addAndGet(5);          // count += 5: count = 5
      
      // Compare and Set
      // if current value is 5, set to 10
      boolean updated = count.compareAndSet(5, 10);  // updated = true, count = 10
      
      // Get value
      int val1 = count.get();			   // Get current value → 10
      count.set(3);                      // Set value to 3
      int val2 = count.getAndSet(7);     // count set to 7, return old value (3)
      
      ```

      [AtomicDemo.java](../../Coding/hw6/AtomicDemo.java)
      
      


19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe).

    Concurrent collections provides **thread-safe** implementations of standard collection interfaces like List, Map, Set, Queue and Deque. They don't require for external synchronization like `synchronized` blocks.

    | Data Structure Type            | Concurrent / Thread-Safe**                                   | **Non-Thread-Safe (Standard Java Collections)** |
    | ------------------------------ | ------------------------------------------------------------ | ----------------------------------------------- |
    | **List**                       | `CopyOnWriteArrayList`                                       | `ArrayList`, `LinkedList`                       |
    | **Set**                        | `CopyOnWriteArraySet`, `ConcurrentSkipListSet`               | `HashSet`, `TreeSet`, `LinkedHashSet`           |
    | **Map**                        | `ConcurrentHashMap`, `ConcurrentSkipListMap`                 | `HashMap`, `TreeMap`, `LinkedHashMap`           |
    | **Queue (FIFO)**               | `ConcurrentLinkedQueue`, `LinkedBlockingQueue`, `ArrayBlockingQueue` | `LinkedList`, `ArrayDeque`                      |
    | **Deque (Double-ended Queue)** | `ConcurrentLinkedDeque`, `LinkedBlockingDeque`               | `ArrayDeque`, `LinkedList`                      |
    | **Priority Queue**             | `PriorityBlockingQueue`                                      | `PriorityQueue`                                 |

    

20. What kind of locks do you know? What is the advantage of each lock?

    - **synchronized** keyword (Intrinsic Lock / Monitor Lock)
      - Built-in lock in Java.
      - Advantages: simple, automatically acquires and releases the lock.

- **ReentrantLock**: 
  - Allows a thread to **acquire the same lock multiple times**, which is particularly useful when a thread needs to access a shared resource repeatedly within its execution.
  - Advantages: more control — like lock/unlock explicitly, tryLock, interruptibility, and fairness; better suited for complex concurrent designs.

- **ReadWriteLock **(ReentrantReadWriteLock):

  - **Multiple threads can read** the data at the very moment, as long as there’s no thread to write the data or to update the data.
  - **Only one thread can write** the data at the very moment, and other threads have to wait.
  - Advantages: Improves performance in scenarios with frequent read operations and infrequent write operations.

- **StampedLock**:

  - A more flexible alternative to `ReadWriteLock` that provides: 

    Optimistic read: read shared data **without acquiring a lock** — assuming that no other thread will write the data during the read. You must call `validate(stamp)` afterward to check if a **write occurred** during your read.

    Pessimistic read: **acquires a lock** **before** read the data to prevent conflicts — assuming that other thread will write the data during the read.

    Write lock: Exclusive lock for write, **blocks all readers and writers**

  - Advantages: Optimistic Read can provide higher performance because no actual lock is acquired and it don't block writes, so it suitable for ultra-fast reads, low writes scenarios.


​        

21. What is future and completableFuture? List some main methods of ComplertableFuture.

    Future is an interface that represents a **result of an asynchronous computation**.

    CompletableFuture (Java 8+) is an extension of  Future that supports **non-blocking**, **asynchronous**, and **chained computation**.

    **Main methods**:

    Creation

    - `CompletableFuture.supplyAsync(Supplier<T>)` – run task asynchronously and return result
    - `CompletableFuture.runAsync(Runnable)` – run task asynchronously without return
    - `new CompletableFuture<>()` – create manually (can complete later)

     Result Handling

    - `thenApply(Function<T,R>)` – transforms result
    - `thenAccept(Consumer<T>)` – consumes result without returning
    - `thenRun(Runnable)` – runs after completion, doesn’t use result

     Chaining

    - `thenCompose(Function<T, CompletionStage<R>>)` – flatMap-style chaining (next depends on previous)
    - `thenCombine(CompletionStage<U>, BiFunction<T,U,R>)` – combine two futures’ results

    Error Handling

    - `exceptionally(Function<Throwable, ? extends T>)` – handle exception and provide fallback
    - `handle(BiFunction<T, Throwable, R>)` – handle both result and exception

    Completion

    - `complete(T value)` – manually complete the future
    - `completeExceptionally(Throwable ex)` – complete with exception

    Blocking (optional)

    - `get()` / `join()` – retrieve result (blocking)

    - `get(timeout, unit)` – timeout support

      

22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)

    Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)

    1. One solution use synchronized and wait notify
       
        [WaitNotify.java](../../Coding/hw6/odd_even_printer/WaitNotify.java)
    2. One solution use ReentrantLock and await, signal

       [ReentrantLock.java](../../Coding/hw6/odd_even_printer/ReentrantLock.java)
       
       

23. Create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. Threads run sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)

    [PrintNumber.java](../../Coding/hw6/PrintNumber.java)

    

24. Completable future: 
    1. Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.
    
       [Homework1.java](../../Coding/hw6/completable_future/Homework1.java)
    
    2. Homework 2: Assume there is an online store that needs to fetch data from three APIs: products, reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched data for further processing. (需要找public api去模拟)
       1. Sign In to Developer.BestBuy.com
       2. Best Buy Developer API Documentation (bestbuyapis.github.io)
       3. 可以⽤fake api https://jsonplaceholder.typicode.com/
       4. Github public api: https://api.github.com/users/your-user-name/repos
    3. Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API call, return a default value and log the exception information.
    
       [Homework2.java](../../Coding/hw6/completable_future/Homework2.java)