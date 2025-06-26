**1. Read: [Link Text](https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock)**

**2. Write a thread-safe singleton class**
--Double-Checked Locking
```java
   public class Singleton {
    public static volatile Singleton instance;
    
    private Singleton(){
        // private constructor
    }
    
    public Singleton getInstance(){
        if(instance == null){
            synchronized(Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
               
            }
        }
       return instance;
    }
}
```
-`volatile` ensures that changes to a variable are immediately visible to all threads. The variable is not cached, and always read from main memory.
-Use `synchronized` to ensure atomic operations
-Coordinate multiple threads to use the same resources

**3. How to create a new thread(Please also consider Thread Pool approach)?**
-Extend `Thread` class
```java
public class MyThread extends Thread {
     @override
     public void run(){
     	System.out.println("Running in MyThread.");
     }
}
public class Main {
    public static void main(String[] args) {
	Thread t = new MyThread();
	t.start();
    }
}
```
-Using runnable interface
```java
class MyRunnable implements Runnable {
	public void run(){
		System.out.println("Thread running via Runnable interface.");
	}
}

public class Main {
	public static void main(String[] args){
		Thread t = new Thread(new MyRunnable());
		t.start();
	}
}
```
-Using Thread Pool (manage many threads
```java
import java.util.concurrent.*;

class MyTask implements Runnable {
    private final String name;

    public MyTask(String name){
        this.name = name;
    }

    @Override
    public void run(){
        System.out.println("Task " + name + " is running in thread: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        // Create thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit 5 tasks to the pool
        for(int i = 1; i <= 5; i++){
            MyTask task = new MyTask("Task-" + i);
            executor.submit(task);
        }

        executor.shutdown();
    }
}
```

**4. Difference between Runnable and Callable?**
The main differences between `Runnable` and `Callable` are:
-**Return Type:** `Runnable` does not return a result—it only defines a `run()` method with no return value.
		  `Callable` is a **generic interface** that defines a `call()` method and can return a result of a specified type (e.g. `Callable<String>`)
-**Exception Handling:** `Callable` allows throwing checked exceptions from the `call()` method, while `Runnable` does not.
-To retrieve the result from a `Callable` that runs asynchronously, we use an `ExecutorService` to submit the task, which returns a `Future<T>` object.
The `Future` allows us to get the result later when it is ready.

```java
import java.util.concurrent.*;
class MyTask implements Callable<String> {
	public String call(){
		return "Task Completed by " + Thread.currentThread().getName();
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		ExecutorService executors = Executors.newSingleThreadExecutor();
		Future<String> future = executors.submit(new MyTask());
		System.out.println(future.get());	// Waits and prints the result (get async result)
		executors.shutdown();
	}
}
```

**5.What is the difference between t.start() and t.run()?**
-`start()`: Creates a new thread and calls the `run()` method in that new thread. It starts concurrent execution.
-`run()`: Just a normal method call; it runs in the current thread, not the new one.

**6. Which way of creating threads is better: Thread class or Runnable interface?**

Implementing the `Runnable` interface is generally better because:
-**java only supports single inheritance**, so extending `Thread` limits the class from extending anything else.
-**Runnable separates task from thread logic**, promoting better reusability.
-It works well with **thread pools** and `ExecutorService`.

**7. What are the thread statuses?**
Thread status refers to the current state of a thread in the lifecycle.
-1. NEW: The thread is created but not yet started (start() hasn’t been called).
-2. RUNNABLE: The thread is ready to run or currently running.
-3. BLOCKED: The thread is waiting to acquire a lock held by another thread.
-4. WAITING: The thread is waiting indefinitely for another thread’s action (e.g., join() or wait()).
-5. TIMED_WAITING: The thread is waiting for a specified time (e.g., sleep(), join(timeout)).
-6. TERMINATED: The thread has finished execution or was stopped.

**8. Demonstrate deadlock and how to resolve it in java code.**
```java
public class Deadlock{
    static final Object lockA = new Object();
    static final Object lockB = new Object();
    
    public static void main(String[] args){
        Thread t1 = new Thread(() -> {
            synchronized(lockA){
                System.out.println("Thread 1: Holding lockA...");
                try{Thread.sleep(100);} catch(Exception e){}
                synchronized(lockB){
                    System.out.println("Thread 1: Holding lockB too.");
                }
            }
        });
        
        Thread t2 = new Thread(() -> {
            synchronized(lockB){
                System.out.println("Thread 2: Holding lockB...");
                try{Thread.sleep(100);} catch(Exception e){}
                synchronized(lockA){
                    System.out.println("Thread 1: Holding lockA too.");
                }
            }
        });
        
        t1.start();
        t2.start();
    }
}
```
-Both threads hold one lock and wait for the other, causing a deadlock.
-How to resolve deadlock?
-Approach: Always acquire deadlock in the same order
```java
public class Deadlock{
    static final Object lockA = new Object();
    static final Object lockB = new Object();
    
    public static void main(String[] args){
        Thread t1 = new Thread(() -> {
            synchronized(lockA){
                System.out.println("Thread 1: Holding lockA...");
                try{Thread.sleep(100);} catch(Exception e){}
                synchronized(lockB){
                    System.out.println("Thread 1: Holding lockB too.");
                }
            }
        });
        
        Thread t2 = new Thread(() -> {
            synchronized(lockA){
                System.out.println("Thread 2: Holding lockA...");
                try{Thread.sleep(100);} catch(Exception e){}
                synchronized(lockB){
                    System.out.println("Thread 1: Holding lockB too.");
                }
            }
        });
        
        t1.start();
        t2.start();
    }
}
```

**9. How do threads communicate each other?**
Threads can communicate using three synchronized methods i.e., `wait()`, `notify()`, and` notifyAll()`.

Producer-Consumer problems: one thread waits for the condition to be met by another
```java
class Shared {
    boolean ready = false;
    
    synchronized void produce() throws InterruptedException {
        ready = true;
        notify();
    }
    
    synchronized void consume() throws InterruptedException {
        while(!ready) wait();
        System.out.println("Consumed!");
    }
}

public class Main {
    public static void main(String[] args) {
        Shared shared = new Shared();
        new Thread(() ->{
            try{
                shared.consume();
            } catch (Exception e){
        
            }
        }).start();
        
        new Thread(() -> {
            try{
                Thread.sleep(100);
                shared.produce();
            } catch (Exception e){
                
            }
        }).start();
        
        
    }
}
```
-`wait()` Makes the current thread release the lock and wait
-`notify()` Wakes up one waiting thread
-`notifyAll()` Wakes up all waiting thread

**10. What’s the difference between class lock and object lock?**
-**Object lock** Acquired on an instance using `synchonized` instance methods or blocks (`synchronized(this)`). Only one thread can access synchronized code for that specific object.
-**Class lock** Acquired on an `Class` object using `synchronized static` methods or `synchronized(ClassName.class)`. It applies to all instances of the class.

Use class lock when shared resource is static; use object lock when it's tied to an instance.

**11. What is join() method?**
`join()` makes the current thread **wait** until the specified thread finishes execution. It is used to ensure one thread completes before another continues.

**12. what is yield() method**
`yield()` hints to the thread scheduler that the current thread is willing to pause and let other threads of the same priority to run. It doesn't guarantee a pause but instead a suggestion.

**13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?**
A **ThreadPool** is a pool of reusable threads used to execute multiple tasks efficiently, reducing the overhead of thread creation.
-Types of ThreadPool:
	-`newFixedThreadPool(int n)` fixed number of threads
	-`newCachedThreadPool()` creates new threads as needed, reuse idle ones
	-`newSingleThreadExecutor()` one thread executes tasks sequentially
	-`newScheduledThreadPool(int n)` supports delayed and periodic tasks
-TaskQueue in ThreadPool is a **blocking queue** that holds tasks waiting to be executed when all threads are busy. It helps manage load and maintain order.

**14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?**
-**Library**: The `java.util.concurrent` package provides classes for creating and managing thread pools.
-**Interface**: The main interface is `ExecutorService`. It provides key thread pool functions like `submit()`, `shutdown()`, `invokeAll()`, and `awaitTermination()`.

**15. How to submit a task to ThreadPool?**
-We can submit tasks to a ThreadPool using `ExecutorService`. Use `executor.submit(Callable<T>)` to submit the task and receive a `Future<T>` result asynchronously.

**16. What is the advantage of ThreadPool?**
-Better performance and resource management 
-Reduce overhead for creating new threads
-Better control over concurrency through task queues and thread limits

**17. Difference between shutdown() and shutdownNow() methods of executor**
-`shutdown()` initiates an orderly showdown. For previous submitted tasks would be executed but no new tasks are accepted.
-`shutdownNow()` attempts to stop all actively executing tasks and returns a list of tasks that were waiting in the queue. It may interrupt all running tasks.

**18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?**
Atomic classes are part of the `java.util.concurrent.atomic` package and provide **lock-free, thread-safe** operations on single variables using low-level CPU atomic instructions. Using Atomic can avoid `synchronized` or locks for better performance

Common Atomic Classes: `AtomicInteger`, `AtomicLong`, `AtomicBoolean`
```java
import java.util.concurrent.atomic.*;
public class AtomicDemo {
	private static AtomicInteger count = new AtomicInteger(1);
	public static void main(String[] args){
		count.incrementAndGet();	// Increments and returns new value
		count.getAndIncrement();	// Returns current value, then increments
		count.addAndGet(5);		// Add by 5 and return
		System.out.println("Count: " + count.get());
	}
}
```

**19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)**

Concurrent collections are **thread-safe data structures** designed for use in multi-threaded environments without needing explicit synchronization

These are designed to handle high concurrency with minimal performance overhead.

-Common concurrent data structures:
	-`ConcurrentHashMap`: Thread-safe version of `HashMap`
	-`CopyOnWriteArrayList`: Thread-safe list optimized for frequent reads
	-`CopyOnWriteArraySet`: Thread-safe set	
	-`LinkedBlockingQueue`: Blocking queue for producer-consumer use cases
	
**20. What kind of locks do you know? What is the advantage of each lock?**
-`synchronized`: Built-in lock for methods or code blocks
-`ReentrantLock`(from `java.util.concurrent.locks`) : explicit, flexible lock with advanced features. Supports tryLock(), interruptible lock, and fairness policy.
-`ReadWriteLock`: allows multiple readers or one writer. Improves performance in read-heavy scenarios
-`Semaphore`: controls access to a limited number of resources. Useful for managing resource pools or rate limiting
-`StampedLock`: optimized for high-performance reads with support for optimistic locking. Reduce contention in systems with frequent reads and rare writes.
-`CountDownLatch`: wait until all tasks complete. One-time task coordination
-`CyclicBarrier`: wait for group of threads. Parallel processing phases.

**21. What is future and completableFuture? List some main methods of ComplertableFuture.**
-`Future`
	-Represents a result of an asynchronous computation
	-Returned by `ExecutorService.submit()`
	-Use `future.get()` to block and retrieve the result
-`CompletableFuture`
	-Enhanced version of `Future` (java 8)
	-Supports **result chaining operations** for **future combination**
	-**Non-blocking** asynchronous operations
-Main methods of `CompletableFuture`:
	| Method Signature                                | Description                            | Example                                      |
|--------------------------------------------------|----------------------------------------|----------------------------------------------|
|`supplyAsync(() -> T)`                            | Run task asynchronously **with returning result**    | `supplyAsync(() -> 5)`                       |
|`runAsync(() -> T)`                            | Run task asynchronously **with no returning result**    | `runAsync(() -> println("Run"))`                       |
|`thenApply(fn)`                            | Transform result    | `cf.thenApply(x -> x * 2)`                       |
|`thenAccept(consumer)`                            | Consume result **without returning a new CompletableFuture**    | `cf.thenAccept(System.out::println)`                       |
|`exceptionally(e -> fallback)`                            | Handle exceptions     | `cf.exceptionally((e) -> {e.printStackTrace()});`                       |
| `allOf(cf1, cf2)`                                | Wait for all futures to complete       | `CompletableFuture.allOf(...).join()`         |
| `anyOf(cf1, cf2)`                                | Complete when any one finishes        | `CompletableFuture.anyOf(...).join()`         |

**23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10.**

> Solution use synchronized and wait notify
```java
public class OddEvenSync {
    static final Object lock = new Object();
    static int num = 1;

    public static void main(String[] args) {
        Runnable printOdd = () -> {
            while (num <= 10) {
                synchronized (lock) {
                    if (num % 2 != 0) {
                        System.out.println("Thread-0: " + num++); // Safe to print odd number
                        lock.notify();			          // Wake up even thread
                    } else {
                        try { 
			   lock.wait();  // Wait if it's not odd thread's run
			} catch (InterruptedException e) {
			}
                    }
                }
            }
        };

        Runnable printEven = () -> {
            while (num <= 10) {
                synchronized (lock) {
                    if (num % 2 == 0) {
                        System.out.println("Thread-1: " + num++);	// Safe to print even number
                        lock.notify();					// Wake up odd thread
                    } else {
                        try { 
			   lock.wait();  // Wait if it's not even thread's run
			} catch (InterruptedException e) {
			}
                    }
                }
            }
        };

        new Thread(printOdd).start();
        new Thread(printEven).start();
    }
}
```
-Shared `lock` object: Ensures mutual exclusion. Only one thread can enter the `synchronized` block at a time, preventing race conditions.
-`wait()` suspends a thread and releases the lock if it's not its turn.
-`notify()` wakes the other thread to continue execution.
-All access to shared state (num, wait, notify) is within synchronized(lock) blocks, ensuring thread-safe visibility and atomicity.

> Solution use ReentrantLock and await, signal
```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinter {
    private final Lock lock = new ReentrantLock();
    private final Condition oddTurn = lock.newCondition();  // Condition for odd thread
    private final Condition evenTurn = lock.newCondition(); // Condition for even thread
    private boolean isOddTurn = true;

    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter();
        new Thread(printer::printOdd, "Thread-0").start();
        new Thread(printer::printEven, "Thread-1").start();
    }

    public void printOdd() {
        for (int i = 1; i <= 9; i += 2) {
            lock.lock();  // Acquire lock before entering critical section
            try {
                while (!isOddTurn) oddTurn.await(); // Wait until it's the odd thread's turn
                System.out.println(Thread.currentThread().getName() + ": " + i);
                isOddTurn = false;
                evenTurn.signal();  // Signal even thread to continue
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Handle interruption
            } finally {
                lock.unlock();  // always release the lock in finally block
            }
        }
    }

    public void printEven() {
        for (int i = 2; i <= 10; i += 2) {
            lock.lock();
            try {
                while (isOddTurn) evenTurn.await(); // Wait until it's the even thread's turn
                System.out.println(Thread.currentThread().getName() + ": " + i);
                isOddTurn = true;
                oddTurn.signal(); // Signal odd thread to continue
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Handle interruption
            } finally {
                lock.unlock(); // always release the lock in finally block
            }
        }
    }
}
```
-`ReentrantLock`: Provides explicit locking. Ensure only one thread can enter critical section at a time.
-`await()`: Causes the current thread to wait and releases the lock until it is signaled.
-`signal()`: Wakes up a thread waiting on the associated condition (e.g., signals the other thread to proceed).
-`unlock()`: Release the lock to avoid deadlock.

**24. Create 3 threads, one thread output 1-10, one thread output 11-20, one thread output 21-22. threads run
sequence is random.**
```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNumber1 {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static int turn = 0; // 0: Thread-0, 1: Thread-2, 2: Thread-1

    public static void main(String[] args) {
        new Thread(new PrintRunnable(0, 1, 10), "Thread-0").start();
        new Thread(new PrintRunnable(1, 11, 20), "Thread-2").start();
        new Thread(new PrintRunnable(2, 21, 30), "Thread-1").start();
    }

    static class PrintRunnable implements Runnable {
        private final int myTurn;
        private final int start;
        private final int end;

        public PrintRunnable(int myTurn, int start, int end) {
            this.myTurn = myTurn;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                // Wait until it's this thread turn
                while (turn != myTurn) {
                    condition.await();
                }
                for (int i = start; i <= end; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    Thread.sleep(50); // optional delay for visibility
                }
                turn++;
                condition.signalAll();  // Wake up all waiting thread
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}
```
-`ReentrantLock`: Ensures only one thread can access the critical section at a time.
-`condition.await()`: Makes threads to wait if it is not their turn
-`condition.signalAll()`: Wakes up all threads so they can recheck the step

**25. completable future:**
**1. Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.**
```java
import java.util.concurrent.CompletableFuture;

public class AsyncSumProduct {
    public static void main(String[] args) {
       int a = 7, b = 9;
       // Asynchronously compute sum
       CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> a + b);
       // Asynchronously compute product
       CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> a * b);
       
       // Consumes and prints results after both tasks complete
        sumFuture.thenAccept(sum -> {
            System.out.println("Sum: " + sum);
        });

        productFuture.thenAccept(product -> {
            System.out.println("Product: " + product);
        });

        // Ensures the main thread waits for all asynchronous tasks to finish, preventing premature termination 
        CompletableFuture.allOf(sumFuture, productFuture).join();
    }
}
```
-**Thread-safe** Each `CompletableFuture` runs independently; there is no shared mutable state.

**2. Assume there is an online store that needs to fetch data from three APIs: products, reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched data for further processing.**

```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OnlineFetcher {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        CompletableFuture<String> productsFuture = fetch("https://jsonplaceholder.typicode.com/posts/1");
        CompletableFuture<String> reviewsFuture = fetch("https://jsonplaceholder.typicode.com/comments/1");
        CompletableFuture<String> inventoryFuture = fetch("https://jsonplaceholder.typicode.com/todos/1");

        CompletableFuture<Void> allDone = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        allDone.thenRun(() -> {
            try {
                String products = productsFuture.get();
                String reviews = reviewsFuture.get();
                String inventory = inventoryFuture.get();

                System.out.println("=== Merged Data ===");
                System.out.println("1. Products: " + products);
                System.out.println("2. Reviews: " + reviews);
                System.out.println("3. Inventory: " + inventory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
    }

    private static CompletableFuture<String> fetch(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}
```
-`HttpClient.sendAsync()` is non-blocking and returns a `CompletableFuture<HttpResponse<T>>.`
-`CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture)` is used to wait until all futures complete
-It calls multiple API and asynchronously combine with `CompletableFuture`
-We use `.join()` on each future because they are guaranteed to have completed.

**3. For question 2, implement exception handling. If an exception occurs during any API call, return a default value and log the exception information.**
```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

public class OnlineFetcher {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Logger logger = Logger.getLogger(OnlineFetcher.class.getName());

    public static void main(String[] args) {
        CompletableFuture<String> productsFuture = fetchWithFallback("https://jsonplaceholder.typicode.com/pts/1", "{\"products\":[]}");	// Wrong one, return empty
        CompletableFuture<String> reviewsFuture = fetchWithFallback("https://jsonplaceholder.typicode.com/comments/1", "{\"reviews\":[]}");
        CompletableFuture<String> inventoryFuture = fetchWithFallback("https://jsonplaceholder.typicode.com/todos/1", "{\"inventory\":[]}");

        CompletableFuture<Void> allDone = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        allDone.thenRun(() -> {
            try {
                String products = productsFuture.get();
                String reviews = reviewsFuture.get();
                String inventory = inventoryFuture.get();

                System.out.println("=== Merged Data (with fallback) ===");
                System.out.println("Products: " + products);
                System.out.println("Reviews: " + reviews);
                System.out.println("Inventory: " + inventory);
            } catch (Exception e) {
                logger.severe("Failed to merge results: " + e.getMessage());
            }
        }).join();
    }

    private static CompletableFuture<String> fetchWithFallback(String url, String fallback) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    logger.warning("Failed to fetch " + url + ": " + ex.getMessage());
                    return fallback;
                });
    }
}
```
-`exceptionally(...)` catches and handles exceptions in the `CompletableFuture` chain.
-`"[]"` is the default JSON fallback for failed API responses.
