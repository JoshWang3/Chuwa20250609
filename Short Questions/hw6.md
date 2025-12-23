## HW6
#### 1. Read: https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock

#### 2. Write a thread-safe singleton class
[reference](https://www.digitalocean.com/community/tutorials/thread-safety-in-java-singleton-classes)
```java
public class ASingleton {

	private static volatile ASingleton instance;
	private static Object mutex = new Object();

	private ASingleton() {
	}

	public static ASingleton getInstance() {
		ASingleton result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
					instance = result = new ASingleton();
			}
		}
		return result;
	}

}
```
How to achieve thread-safe?  
Use synchronized block inside the if loop and volatile variable  
Pros:
- Thread safety is guaranteed
- Client application can pass arguments
- Lazy initialization achieved
- Synchronization overhead is minimal and applicable only for first few threads when the variable is null.  

Cons: Extra if condition

#### 3. How to create a new thread (please also consider Thread Pool approach)?

(1) extend Thread class: Subclass Thread, override run(), and call start() to spawn a new thread that executes your run() logic.
```java
class SimpleThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread runs");
    }
}

public class App1 {
    public static void main(String[] args) {
        SimpleThread t = new SimpleThread();
        t.start();  // start thread
    }
}

```

(2) implement Runnable interface: Implement Runnable.run(), wrap your Runnable in new Thread(runnable), and call start() to launch a new thread for that task.
```java
class SimpleTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Task runs");
    }
}

public class App2 {
    public static void main(String[] args) {
        // create a new task instance
        SimpleTask st = new SimpleTask();
        // wrap the task in a Thread(hand st off to a new Thread object) and start it
        new Thread(st).start();
    }
}

```

(3) using Lambda Expression: Wrap your Callable in a FutureTask, pass it to new Thread(futureTask), and call start() to run the call() method asynchronously and retrieve its result via futureTask.get().
```java
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class App3 {
    public static void main(String[] args) throws Exception {
        // create a FutureTask with a Callable that returns the length of a string
        FutureTask<Integer> futureTask = new FutureTask<>(() -> "HelloWorld".length());
        // wrap the FutureTask in a Thread
        Thread worker = new Thread(futureTask);
        worker.start();  // start the thread, which runs call()
        // retrieve and print the result
        Integer length = futureTask.get();
        System.out.println("Length = " + length);
    }
}

```

(4) Using ExecutorService (for Managing Thread Pools): Create an ExecutorService, submit Runnable or Callable tasks to it, and let the pool manage creating, reusing, and scheduling threads for you.
```java
import java.util.concurrent.*;

public class App4 {
    public static void main(String[] args) throws Exception {
        // create a thread pool with 2 threads
        ExecutorService pool = Executors.newFixedThreadPool(2);
        // submitting Threads as Runnable
        pool.submit(() -> System.out.println("Pool task A"));
        pool.submit(() -> System.out.println("Pool task B"));
        // shutdown the threads/executor
        pool.shutdown();  
    }
}
```

#### 4. Difference between Runnable and Callable?
[reference1](https://medium.com/@reetesh043/javas-multithreading-a-deep-dive-into-runnable-and-callable-interfaces-9a6f842b183f)
[reference2](https://juejin.cn/post/6844904086832152590)  
(1) Method Signature
- Runnable: has a single method called run() that does not take any arguments and **returns no value (void return type)**.
- Callable: has a single method called call() that does not take any arguments but **RETURNS a value of a specified type**.

(2) Return Type
- Runnable: cannot return a result, void return type
- Callable: returns a value of type V, where V is a generic type parameter. This feature makes Callable suitable for tasks that need to return a result after completion.

(3) Usage with Executor Framework 
- Runnable: do not return a result. The executor’s execute(Runnable command) method is used to run Runnable tasks, and there is no way to obtain the result of the execution directly or to check if the execution finished successfully or with an exception.
- Callable: returns a Future<T> object. This Future object can be used to retrieve the result of the execution, check if the execution is complete, and check if there were any exceptions during execution.

(4) Exception Handling
- Runnable: cannot throw any checked exceptions. Any checked exceptions thrown within the run() method must be caught and handled inside the method itself.
- Callable: can throw exceptions, allowing you to throw checked exceptions from the method. This capability facilitates better error handling by allowing the caller to **catch and respond to exceptions thrown during task execution**.

#### 5. What is the difference between t.start() and t.run()? [ref](https://www.naukri.com/code360/library/difference-between-run-and-start-method)

(1) Thread Creation
- t.start(): Starts a new thread and executes the run() method in that new thread.

- t.run(): Calls the run() method in the current thread, just like a normal method.

(2) Multiple invocation:  
- start() method cannot be performed again without throwing an IllegalStateException, 
- but the run() method may be called many times as it is just a regular method call. 

#### 6. Which way of creating threads is better: Thread class or Runnable interface? 
Implements Runnable is preferred over extends Thread!  
Because it avoids the single-inheritance limitation of extending Thread and lets you flexibly share one task object across multiple threads.

#### 7. What are the thread statuses?  [ref1](https://www.youtube.com/watch?v=B4IVu-2hCos)  [ref2](https://javaguide.cn/java/concurrent/java-concurrent-questions-01.html#%E2%AD%90%EF%B8%8F%E8%AF%B4%E8%AF%B4%E7%BA%BF%E7%A8%8B%E7%9A%84%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F%E5%92%8C%E7%8A%B6%E6%80%81)    
A thread can be in one of the following 6 states:
(1) NEW: 
A thread that has not yet started is in this state.

(2) RUNNABLE: 
A thread executing in the Java virtual machine is in this state.

(3) BLOCKED: 
A thread that is blocked waiting for a monitor lock is in this state.

(4) WAITING: 
A thread that is waiting indefinitely for another thread to perform a particular action is in this state.

(5) TIMED_WAITING: 
A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state.

(6) TERMINATED: 
A thread that has exited is in this state.


#### 8. Demonstrate deadlock and how to resolve it in Java code. [ref](https://www.bilibili.com/video/BV12S4y1u7bY/?spm_id_from=333.337.search-card.all.click&vd_source=08c214d4cbefc0b865de560cb7010840)  
- Detect: a snapshot of all the threads in a JVM at a moment in time, showing each thread’s stack trace and lock‐holding/waiting status. You use it to diagnose deadlock
Thread Dumps
kill -3 12704
jstack 11475

- Resolve
```java
public class SimpleDeadlockDemo {
    
    // Deadlock
    static class DeadlockProblem {
        private final Object lockA = new Object();
        private final Object lockB = new Object();
        
        public void task1() {
            synchronized (lockA) {
                System.out.println("Task1: Got lockA");
                
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                
                System.out.println("Task1: Waiting for lockB...");
                synchronized (lockB) {
                    System.out.println("Task1: Got lockB - Done!");
                }
            }
        }
        
        public void task2() {
            synchronized (lockB) {
                System.out.println("Task2: Got lockB");
                
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                
                System.out.println("Task2: Waiting for lockA...");
                synchronized (lockA) {
                    System.out.println("Task2: Got lockA - Done!");
                }
            }
        }
    }
    
    // Resolve
    static class DeadlockSolution {
        private final Object lockA = new Object();
        private final Object lockB = new Object();
        
        public void task1() {
            // Always get lockA first, then lockB
            synchronized (lockA) {
                System.out.println("Task1: Got lockA");
                
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                
                synchronized (lockB) {
                    System.out.println("Task1: Got lockB - Done!");
                }
            }
        }
        
        public void task2() {
            // Same order: get lockA first, then lockB
            synchronized (lockA) {
                System.out.println("Task2: Got lockA");
                
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                
                synchronized (lockB) {
                    System.out.println("Task2: Got lockB - Done!");
                }
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("=== SHOWING DEADLOCK ===");
        showDeadlock();
        
        System.out.println("\n=== SHOWING SOLUTION ===");
        showSolution();
    }
    
    private static void showDeadlock() throws InterruptedException {
        DeadlockProblem problem = new DeadlockProblem();
        
        Thread thread1 = new Thread(problem::task1, "Thread-1");
        Thread thread2 = new Thread(problem::task2, "Thread-2");
        
        thread1.start();
        thread2.start();
        
        // Wait a bit to see if threads complete
        Thread.sleep(2000);
        
        if (thread1.isAlive() || thread2.isAlive()) {
            System.out.println("DEADLOCK! Threads are stuck waiting for each other.");
            thread1.interrupt();
            thread2.interrupt();
        }
    }
    
    private static void showSolution() throws InterruptedException {
        DeadlockSolution solution = new DeadlockSolution();
        
        Thread thread1 = new Thread(solution::task1, "Thread-1");
        Thread thread2 = new Thread(solution::task2, "Thread-2");
        
        thread1.start();
        thread2.start();
        
        thread1.join();
        thread2.join();
        
        System.out.println("SUCCESS! Both threads completed without deadlock.");
    }
}
```

#### 9. How do threads communicate with each other?
(1) Shared Memory  
Multiple threads access the same object or field. Proper synchronization (synchronized, volatile, or locks) ensures changes one thread makes are visible to others.

(2) Object Monitors(wait/notify/notifyAll)  
-wait() -- It tells the calling thread to give up the lock and go to sleep until some other thread enters the same monitor and calls notify().  
-notify() -- It wakes up one single thread called wait() on the same object. It should be noted that calling notify() does not give up a lock on a resource.  
-notifyAll() -- It wakes up all the threads called wait() on the same object.  

(3) Locks and Conditions  
ReentrantLock plus its Condition objects let threads await() and signal() under explicit lock control, offering more flexibility than intrinsic monitors.

(4) Blocking Queues and Concurrent Collections  
Classes like LinkedBlockingQueue, ConcurrentHashMap, or SynchronousQueue let threads safely hand off or share data without explicit locks.

(5) Atomic Variables    
Classes in java.util.concurrent.atomic (e.g., AtomicInteger) let threads update single variables atomically without full synchronization, simplifying lock-free communication.

(6) Synchronizers
Utilities such as CountDownLatch, CyclicBarrier, Semaphore, and Exchanger coordinate points of rendezvous, signaling, or resource permits between threads.

#### 10. What’s the difference between class lock and object lock?
- Object Lock: Acquired on a specific instance’s monitor. Only one thread can execute any synchronized block/method on the same object at a time, but different instances don’t block each other.  
```java
public synchronized void instanceMethod() { … }
// or
synchronized(this) { … }
```
- Class Lock: Acquired on the Class object’s monitor. Ensures mutual exclusion across all instances of that class (and for static fields), since there is exactly one Class object per class.
```java
public static synchronized void staticMethod() { … }
// or
synchronized(MyClass.class) { … }

```

Difference:  
Object locks scope to **a single instance**, allowing concurrency between different instances.

Class locks are **global** to the class, preventing any thread from entering a static synchronized method or a synchronized(ClassName.class) block if another thread holds that lock, regardless of instance.

#### 11. What is the join() method? 
join() method is a blocking call on a Thread that makes the current thread wait until the target thread completes (or the optional timeout elapses).
```java
Thread worker = new Thread(() -> {
        // do work…
        });
        worker.start();     // start the new thread
        worker.join();      // current thread pauses here until worker finishes
```
- No-arg join() waits indefinitely.  
- join(long millis) waits up to the given milliseconds.  
- Throws InterruptedException if the waiting thread is interrupted.  

#### 12. What is the yield() method?
yield() method is a hint to the thread scheduler that the **current thread is willing to pause** its use of the CPU so that **other threads of the same priority can run**. It does not block or sleep the thread—after yielding, the thread simply **returns to the “runnable” state** and **may be scheduled again immediately.**  
```java
public class YieldExample {
    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " → " + i);
                Thread.yield();  // hint: let other same-priority threads run
            }
        }, "Worker");
        worker.start();

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " → " + i);
            Thread.yield();      // hint: let other same-priority threads run
        }
    }
}

```

#### 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
- ThreadPool Definition: a collection of **pre-created, reusable threads** that are managed to execute tasks efficiently. Instead of creating and destroying threads for each task, a thread pool **maintains a fixed or variable number of threads that can be reused to handle multiple tasks**.


- Types of ThreadPool:  
(1) Fixed Thread Pool (newFixedThreadPool(n)): Excess tasks wait in an unbounded queue until a thread is free.  
(2) Cached Thread Pool (newCachedThreadPool()): Creates new threads as needed, reuses idle threads up to a 60-second timeout. Uses a SynchronousQueue with no internal task buffer.  
(3) Single-Thread Executor (newSingleThreadExecutor()):Exactly one worker thread; tasks execute sequentially. For guarantee ordering.  
(4) Scheduled Thread Pool (newScheduledThreadPool(n)): Supports delayed and periodic task execution.

- What is the TaskQueue in ThreadPool?  
  a ThreadPoolExecutor holds incoming Runnable/Callable tasks in a BlockingQueue<Runnable> (the “task queue”). The task queue decouples task submission from execution, letting threads fetch work when they become available.

#### 14. Which library is used to create ThreadPool? Which interface provides the main functions of a thread-pool?
- Library: Thread pools are provided by the java.util.concurrent package, typically created via the static factory methods in the Executors utility class.
- Main interface: java.util.concurrent.ExecutorService. Defines methods like execute(), submit(), shutdown(), and awaitTermination().  
- 
#### 15. How to submit a task to ThreadPool?

(1) Create the pool


(2) Submit the task


(3) Task queuing and execution


(Optional) (4) Retrieve results

(5) Shutdown

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        // create a thread pool with 2 threads
        ExecutorService pool = Executors.newFixedThreadPool(2);
        // submit a simple task
        pool.submit(() -> System.out.println("Pool task running"));
        // stop accepting new tasks
        pool.shutdown();
    }
}

```

#### 16. What is the advantage of ThreadPool? [ref](https://www.linkedin.com/advice/0/what-benefits-using-thread-pools-multithreading-mmrkf)
(1) Reduce overhead: reusing precreated threads avoids the cost of repeatedly creating and destroying threads for each task, saving system resources and time

(2) Control concurrency: by capping the number of active threads, you prevent excessive context-switching, contention, and resource exhaustion

(3) Improve scalability: pools can be tuned (or even resized at runtime) to match varying workloads and use specialized pools for CPU-bound versus I/O-bound tasks

#### 17. Difference between shutdown() and shutdownNow() methods of Executor
- shutdown() graceful shutdown:  
Initiates an orderly shutdown  
Does NOT accept new tasks - throws RejectedExecutionException for new submissions  
Allows currently executing tasks to complete  
Allows queued tasks to be executed  
Returns immediately (non-blocking)  

- shutdownNow() immediate shutdown:  
Attempts to stop all actively executing tasks
Interrupts running threads (sends interrupt signal)
Halts processing of waiting tasks
Returns a list of unexecuted tasks that were queued
Returns immediately (non-blocking)

| Aspect | shutdown() | shutdownNow() |
|--------|------------|---------------|
| **Running Tasks** | Allowed to complete | Interrupted |
| **Queued Tasks** | Will be executed | Cancelled, returned as list |
| **New Tasks** | Rejected | Rejected |
| **Return Value** | void | List<Runnable> |
| **Approach** | Graceful | Forceful |

#### 18. What are Atomic classes? How many types of Atomic classes? Give some code examples of Atomic classes and their main methods. When to use them?

- Definition: provide thread-safe operations on single variables without explicit synchronization (e.g., synchronized blocks). They leverage low-level CPU atomic instructions (like CAS – Compare-And-Swap) for high-performance concurrent programming.

- Types:  
(1) Primitive Types

- AtomicBoolean: Atomic boolean
- AtomicInteger: Atomic int
```java
import java.util.concurrent.atomic.AtomicInteger;

public class Example {
    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet(); // Atomically increment by 1
    }

    public int get() {
        return counter.get(); // Get current value
    }

    public int update() {
        return counter.updateAndGet(x -> x * 2); // Atomically double the value
    }
}
```
- AtomicLong: Atomic long

(2) Reference Types:  
- AtomicReference<V>: Generic atomic reference  
- AtomicStampedReference<V>: Reference + integer stamp (avoids ABA problem)  
- AtomicMarkableReference<V>: Reference + boolean mark  

(3) Array Types:  
- AtomicIntegerArray: int[]  
- AtomicLongArray: long[]  
- AtomicReferenceArray<V>: V[]  

(4) Field Updaters:  
- AtomicIntegerFieldUpdater<T>  
- AtomicLongFieldUpdater<T>  

(5) Accumulators/Adders: LongAdder, DoubleAdder, LongAccumulator, DoubleAccumulator

- When to use:  
Single-Variable Atomicity: When you need atomic updates (e.g., counters, flags) without locking. For example: AtomicInteger for a shared counter.  
Avoiding Synchronization Overhead: Atomic classes outperform synchronized in low-to-moderate contention scenarios.  
Non-Blocking Algorithms: Implement lock-free data structures (e.g., queues) using compareAndSet.  
High-Contention Accumulators: Use LongAdder/LongAccumulator for frequently updated sums (e.g., metrics).  
Reference Updates: Use AtomicReference to atomically swap objects (e.g., cached configurations).  

#### 19. What are the concurrent collections? Can you list some thread-safe data structures?

(1) Concurrent collections are thread-safe implementations in java.util.concurrent designed for high‐throughput concurrent access.
They include:  
ConcurrentHashMap for map access under heavy contention,  
sorted variants like ConcurrentSkipListMap and ConcurrentSkipListSet,  
read-optimized collections such as CopyOnWriteArrayList and CopyOnWriteArraySet,  
non-blocking queues like ConcurrentLinkedQueue and ConcurrentLinkedDeque,  
and a family of blocking queues (e.g. ArrayBlockingQueue, LinkedBlockingQueue, PriorityBlockingQueue, DelayQueue, and SynchronousQueue) that support producer-consumer patterns and timed waits.  

(2) Thread-safe data structures: ConcurrentHashMap, ConcurrentLinkedQueue, ConcurrentLinkedDeque, ConcurrentSkipListMap, ConcurrentSkipListSet

#### 20. What kinds of locks do you know? What is the advantage of each lock?
Types & Advantage:

Intrinsic (monitor) lock – synchronized: simple syntax, automatic release on block exit or exception, no extra API needed.

ReentrantLock: supports interruptible lock acquisition, tryLock() with timeout, and optional fairness policy to reduce thread starvation.

ReentrantReadWriteLock: separates a shared “read” lock (many readers) from an exclusive “write” lock, improving throughput in read-heavy workloads.

StampedLock: offers an optimistic read mode that avoids locking when uncontended, plus traditional read/write and an upgradeable read lock for minimal overhead.

#### 21. What is Future and CompletableFuture? List some main methods of CompletableFuture.

(1) Future Definition:  a Java interface (introduced in Java 5) that represents the result of an asynchronous computation—you submit a Callable or Runnable to an ExecutorService and get back a Future, on which you can call get() (which blocks until the result is ready), cancel(), or check isDone().

CompletableFuture Definition: implements Future<V> and CompletionStage<V>, letting you not only block for a result but also chain and compose tasks with callbacks (thenApply, thenAccept, etc.), combine multiple futures (allOf/anyOf), and even complete them manually via complete().

(2) Main methods of CompletableFuture:  
```
supplyAsync(Supplier<U>) — Starts an asynchronous task that returns a value.

runAsync(Runnable) — Starts an asynchronous task that does not return a value.

thenApply(Function<T, U>) — Transforms this future’s result when it completes.

thenAccept(Consumer<T>) — Consumes this future’s result without producing a new one.

exceptionally(Function<Throwable, T>) — Provides a fallback value if this future completes exceptionally.

complete(T value) — Manually completes this future with the given value (if not already completed).

get() / join() — Blocks and retrieves the result (get() throws checked exceptions, join() throws unchecked).

allOf(CompletableFuture<?>… futures) — Returns a future that completes when all of the given futures complete.

anyOf(CompletableFuture<?>… futures) — Returns a future that completes when any one of the given futures completes.
```

#### 22. Type the code by yourself and try to understand it. (package com.chuwa.tutorial.t08_multithreading)

#### 23. Write code to create two threads: one thread prints 1,3,5,7,9; the other prints 2,4,6,8,10.  
    23.1 One solution uses synchronized and wait/notify  
    23.2 One solution uses ReentrantLock and await/signal

See Coding Folder.

#### 24. Create three threads: one outputs 1–10, one outputs 11–20, one outputs 21–22. Threads run in random sequence. (Solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)
See Coding Folder.

