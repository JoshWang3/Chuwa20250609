 1. Read:  
 2. Write a thread-safe singleton class 
```java

public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    // Private constructor to prevent instantiation
    private ThreadSafeSingleton() {
        
    }

    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}

```
 3. How to create a new thread (Please also consider Thread Pool approach)?
    1. Extending Thread class
```java
class MyThread extends Thread{
    public void run(){
        System.out.println("Thread is running...");
    }
}

class Main{
    public static void main(Strinhg[] args){
        MyThread thread = new MyThread();
        thread.start();
    }
}
```
    2. Implementing Runnable Interface
```java
class MyRunnable implements Runnable{
    public void run(){
        System.out.println("Runnable thread is running");
    }
}

public class Main{
    public static void main(String[] args){
        Thread t2 = new Thread(new MyRunnable());
        t2.start();
    }
}

```
    3. Using Thread Pool
```java

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolThread {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for(int i = 0; i < 5; i++){
            executor.execute(() ->{
                System.out.println("Thread Pool Thread is running: " + Thread.currentThread().getName());
            });
        }
        executor.shutdown();

    }
}


```

 3. Difference between Runnable and Callable?
    | **Feature**            | Runnable                             | Callabel                                |
    | ---------------------- | ------------------------------------ | --------------------------------------- |
    | **Return value**       | Does not return a result('void').    | Returns a result using 'Future'         |
    | **Exception Handling** | Cannot throw checked exceptions.     | Can throw checked exceptions.           |
    | **Method**             | 'run()' method                       | 'call()' method                         |
    | **Use Case**           | Suitable for tasks without a result. | Suitable for tasks that return a result |

 4. What is the difference between t.start() and t.run()?

| **Feature**            | t.start()                                   | t.run()                                              |
| ---------------------- | ------------------------------------------- | ---------------------------------------------------- |
| **Thread Creation**    | Starts a new thread of execution.           | Executes the run() method on the current thread.     |
| **Parallel Execution** | Runs the run() method in a separate thread. | Runs the run() method in the same thread.            |
| **Use Case**           | Used to create and start a thread.          | Used when you just want to call the method directly. |

 5. Which way of creating threads is better: Thread class or Runnable interface?
 Runnable interface is better. It could support multiple inheritance, and task logic is separate from the thread behaviro, making the code more reusable and maintainable. Furthermore, the Runnable interface is better suited for use with modern thread pools and the Executor Framework, which provide efficient thread management and resource utilization.

 6. What are the thread statuses?
    1. New: A trhead is in the new state when it is created but has not yet started
    2. Runnable: A thread is in the Runnable state when it is ready to run but is waiting for CPU time to execute
    3. Blocked: A thread is in the blocked state when it is waiting to acquire a monitor lock to enter a synchronized block or method
    4. Waiting: A thread is in the Waiting state when it is waiting indefinitely for another thread to perform a specific action
    5. Timed_waiting: A thread is in the Timed_waiting state when it is waiting for another thread to perform a specific action, but only for a specificed amount of time.
    6. Terminated: A thread is in the terminated state when it has completed its execution or has been stopped

 7. Demonstrate deadlock and how to resolve it in Java code.
    Deadlock occurs when two or more threads are waiting for each other to release a resource and none of them can proceed.

    How to resolve:
    1. Lock ordering: Always acquire locks, in the same order
    2. Try and timeout: Use tryLock() with a timeout to avoid indefinite waiting
    3. Avoid nested locks: Minimize situations where locks are nested

```java

public class Resource {

    void method1() {
        synchronized (DeadlockDemo.r1) {
            System.out.println("Thread " + Thread.currentThread().getName() + " locked Resource 1");
            try {
                // Simulate some work with Resource 1
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " trying to lock Resource 2");
            synchronized (DeadlockDemo.r2) {
                System.out.println("Thread " + Thread.currentThread().getName() + " locked Resource 2");
            }
            
        }
    }

    void method2(){
        synchronized(DeadlockDemo.r1){
           System.out.println("Thread " + Thread.currentThread().getName() + " locked Resource 1");
            try {
                // Simulate some work with Resource 1
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " trying to lock Resource 2");
            synchronized (DeadlockDemo.r2) {
                System.out.println("Thread " + Thread.currentThread().getName() + " locked Resource 2");
            }
        }
    }
}
        

public class DeadlockDemo {
    static final Resource r1 = new Resource();
    static final Resource r2 = new Resource();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> r1.method1(), "Thread - 1");
        Thread t2 = new Thread(() -> r2.method2(), "Thread - 2");

        t1.start();
        t2.start();
    }
}

```

 8.  How do threads communicate each other?
    1. wait(): Cause the current trhead to wait until another thread invokes notify() or notifyAll() on the same object
    2. notify(): Wakes up a single thread that is waiting on the pbject's monitor
    3. notifyAll(): Wakes up all threads that are waiting on the object's monitor


 9.  What’s the difference between class lock and object lock?
   1. Object lock: An object lock is associated with a specific instance of a class. Each object has its own lock.
   2. Class lock: associated with the class object of a class, which is shared among all instances of thet class. It is used to synchronize static methods or static blocks
    | Aspect          | Oject Lock                                                  | Class Lock                                                   |
    | --------------- | ----------------------------------------------------------- | ------------------------------------------------------------ |
    | Scope           | Specific to an object instance                              | Shared across all instances of the class                     |
    | Used for        | Synchronizing instance method or blocks                     | Synchronizing static methods or blocks                       |
    | Lock Type       | Each object has its own lock                                | One lock shared by the class                                 |
    | Thread Behavior | Threads working on different objects don't block each other | Thread block each other even if working on different objects |

 10. What is join() method?
   join() method in java is used to make one thread wait until another thread compeletes its execution. It ensures that the current thread will not proceed untile the thread it is waiting for has finished
    
 11. what is yield() method
    yield() is a static method of the Thread class that pauses the currently executing thread and allos other threads of the same or higher priority to execute.

 12. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
    A ThreadPool is a pool of pre-created threads that can be reused for executing multiple tasks. Instead of creating a new thread for every task, tasks are submitted to the pool and threads execute them, improving performance.

    Types of ThreadPool:
    1. FixedThreadPool: A fixed number of threads
    2. CachedThreadPool: Creats threads as needed and reuses idle thread
    3. SingleThreadExecutor: A single-threaded executor
    4. ScheduledThreadPool: For scheduling tasks at fixed rate or delay
    5. WorkStealingPool: Uses multiple worker threads to steal tasks from each other
   
    TaskQueue in ThreadPool: an internal queue where tasks are stored before being executed by the thread in the pool. The tasks are picked up by the threads in the pool based on availability


 13. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
    Library: java.util.concurrent -> provides the classes and methods to create and manage thread pools
    Interface: The executor interface provides the main functions of a thread pool. Its subinterface, ExecutorService provides additionnal methods like submit(), shutdown(), etc.

 14. How to submit a task to ThreadPool?
    use submit() or execute() methods provided by the ExecutorService interface
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " is executing the task");
        };

        threadPool.submit(task);
        threadPool.shutdown(); // Close the thread pool after tasks are completed
    }
}

```

 15. What is the advantage of ThreadPool?
    1. Improved Performance: Reuses existing threads instead of creating new ones, recuding overhead
    2. Better Resource Management: Limits the number of concurrent threads to avoid resource exhaustion
    3. Simplified Thread Management: Abstracts thread creation and lifecycle management
    4. Task Queuing: Task can be queued and executed in order
    5. Scalability: Supports dynamic thread allocation in certain types of pools

 16. Difference between shutdown() and shutdownNow() methods of executor
   
| Aspect          | shutdown()                                                                                           | shutdownNow()                                                                           |
| --------------- | ---------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| Behavior        | Initiates an orderly shutdown previously submitted tasks are executed, but no new tasks are accepted | Attempts to stop all actively executing tasks and halts the processing of waiting tasks |
| Pending tasks   | Pending tasks in the queue are executed                                                              | Pending tasks are discarded                                                             |
| Active Threads  | Active threads are allowed to complete their execution                                               | Active threads are interrupted                                                          |
| Methods returns | Returns after initiating the shutdown process                                                        | Returns immdiately attempting to stop all tasks                                         |

 17. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
   
    Atomic classes are part of the java.util.concurrent.atomic package. They provide a way to perform thread-safe operations on single variables without using explicit synchronization. These classes use low-level atomic hardware instructions like compare-and-swap to ensure thread safety.

    Types of Atomic classes:
    1. AtomicInteger
    2. AtomicLong
    3. AtomicBoolean
    4. AtomicReference
    5. AtomicIntegerArray
    6. AtomicLongArray
    7. AtomicReferenceArray

```java

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        // Increment and get value
        System.out.println("Incremented Value: " + atomicInteger.incrementAndGet());

        // Add and get value
        System.out.println("Added Value: " + atomicInteger.addAndGet(5));

        // Compare and set
        boolean isUpdated = atomicInteger.compareAndSet(6, 10);
        System.out.println("Was value updated? " + isUpdated);
        System.out.println("Current Value: " + atomicInteger.get());
    }
}

```
    When to use:
    1. When we need thread-safe operation on single variables
    2. When performance is critical, and we want to avoid using synchronization or locks

 1.  What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
    1. Concurrent collections are part of the java.util.concurrent package. They are thread-safe collections that allow multiple threads to access and modify them without explicit synchronization
    2. Concurrent date structure
       1. ConcurrentHashMap
       2. CopyOnWriteArrayList
       3. CopyOnWriteArraySet
       4. ConcurrentLinkedQueue
       5. ConcurrentSkipListMap
       6. ConcurrentSkipListSet
       7. BlokcingQueue


 19. What kind of locks do you know? What is the advantage of each lock?
   Types of lock:
    1. ReentrantLock: Allows fair locking(FIFS) and provides better control with method like lockInterruptibly() and tryLock()
    2. ReadWriteLock: Improves performance in scenarios where reads are morea frequent than writes
    3. StampedLock: Optimistic reads improve performance when contetion is low
    4. Semaphore: Useful for managing fixed resorce pools like database connections
    5. SpinLock: Avoids context switching overhead in low-contention scenarios
    6. Monitor Lock(Synchronized): simple to use and sufficient for most use cases
   


 20. What is future and completableFuture? List some main methods of ComplertableFuture.
     1.  Future interface represents the result of an asynchronous computation. It provides methods to check if the computation is complete, wait for it to complete and retrieve the result
     2.  CompletableFuture is an enhancement over Future introduced in Java 8. It allows non-blocking asynchronous programming with the ability to chain multiple tasks and manually complete tasks
   
   Main Methods of CompletableFuture:
   1. Creating CompletableFuture: supplyAsync(Supplier<T>), runAsync(Runnable)
   2. Combining Tasks: thenApply(Function<T, R>), thenAccept(Consumer<T>), thenCombine(CompletableFuture, BiFunction)
   3. Handling Exeption: exceptionally(Function<Throwable, T>)
   4. Waiting for Completion: join(), get()
   


 21. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)


 22. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in 
com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)


 1. One solution use synchronized and wait notify 
   
```java
class OddEvenPrinter{
    private final Object lock = new Object();
    private boolean oddTurn = true;

    public void printOdd(){
        synchronized(lock){
            for(int i = 1; i <= 9; i+= 2){
                while(!oddTurn){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                oddTurn = false;
                lock.notify();
            }
        }
    }

    public void printEven(){
        synchronized(lock){
            for(int i = 2; i <= 10; i += 2){
                while(oddTurn){
                    try {
                       lock.wait(); 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                oddTurn = true;
                lock.notify();
            }
        }
    }
}

public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter();
        Thread oddThread = new Thread(printer::printOdd, "Thread-0");
        Thread evenThread = new Thread(printer::printEven, "Thread-1");

        oddThread.start();
        evenThread.start();
    }
}

```
1. One solution use ReentrantLock and await, signal
Thread-0: 1
 Thread-1: 2
 Thread-0: 3
 Thread-1: 4
 Thread-0: 5
 Thread-1: 6
 Thread-0: 7
 Thread-1: 8
 Thread-0: 9
 Thread-1: 10
 Process finished with exit code 0

```java
import java.util.concurrent.locks.*;

public class OddEvenPrinterWithLock {
    private final Lock lock = new ReentrantLock();
    private final Condition oddCondition = lock.newCondition();
    private final Condition evenCondition = lock.newCondition();
    private boolean oddTurn = true;

    public void printOdd(){
        lock.lock();

        try {
            for(int i = 0; i <= 9; i += 2){
                while(!oddTurn){
                    oddCondition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                oddTurn = false;
                evenCondition.signal();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void printEven(){
        lock.lock();

        try {
            for(int i = 2; i <= 10; i+= 2){
                while(oddTurn){
                    evenCondition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                oddTurn = true;
                oddCondition.signal();
        }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        OddEvenPrinterWithLock printer = new OddEvenPrinterWithLock();

        Thread oddThread = new Thread(printer::printOdd, "Thread-0");
        Thread evenThread = new Thread(printer::printEven, "Thread-1");

        oddThread.start();
        evenThread.start();

        
    }
}


```

 1.  create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run 
sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)
 Thread-0: 1
 Thread-0: 2
 Thread-0: 3
 Thread-0: 4
 Thread-0: 5
 Thread-0: 6
 Thread-0: 7
 Thread-0: 8
 Thread-0: 9
 Thread-0: 10
 Thread-2: 11
 Thread-2: 12
 Thread-2: 13
 Thread-2: 14
 Thread-2: 15
 Thread-2: 16
 Thread-2: 17
 Thread-2: 18
 Thread-2: 19
 Thread-2: 20
 Thread-1: 21
 Thread-1: 22
 Thread-1: 23
 Thread-1: 24
 Thread-1: 25
 Thread-1: 26
 Thread-1: 27
 Thread-1: 28
 Thread-1: 29
 Thread-1: 30

```java
public class PrintNumber {

    private static final Object lock = new Object();
    private static int currentThread = 1;
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock){
                while(currentThread != 1){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); 
                        e.printStackTrace();
                    }
                }

                for(int i = 1; i <= 10; i++){
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
                currentThread = 2;
                lock.notifyAll();
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized(lock){
                while(currentThread != 2){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }

                for(int i = 11; i <= 20; i++){
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
                currentThread = 3;
                lock.notifyAll();
            }
        });

        Thread t3 = new Thread(() ->{
            synchronized (lock) {
                while(currentThread != 3){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
                for(int i = 21; i <= 30; i++){
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
                currentThread = 1; // Reset to first thread
                lock.notifyAll();
            }
        });
    

        t1.start();
        t2.start();
        t3.start();
    }
}


```

23.  completable future:
 1. Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.
    
```java

import java.util.concurrent.CompletableFuture;

public class CompletableFutureHw1 {
    public static void main(String[] args) {
        int n1 = 5, n2 = 10;

        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            return n1 + n2;
        });

        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            return n1 * n2;
        });

        sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));
        productFuture.thenAccept(product -> System.out.println("Product: " + product));
    }
}


```

 2.  Homework 2: Assume there is an online store that needs to fetch data from three APIs: products, reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched data for further processing. (需要找public api去模拟，)
 1. Sign In to Developer.BestBuy.com
 2. Best Buy Developer API Documentation (bestbuyapis.github.io)
    1. 可以⽤fake api 
https://jsonplaceholder.typicode.com/
    1. Github public api: 
https://api.github.com/users/your-user-name/repos
 

 ```java

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureHw2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        CompletableFuture<String> productsFuture = fetchData(client, "https://jsonplaceholder.typicode.com/posts");
        CompletableFuture<String> reviewsFuture = fetchData(client, "https://jsonplaceholder.typicode.com/comments");
        CompletableFuture<String> inventoryFuture = fetchData(client, "https://jsonplaceholder.typicode.com/todos");
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        allFutures.thenRun(() -> {
            try {
                String products = productsFuture.get();
                String reviews = reviewsFuture.get();
                String inventory = inventoryFuture.get();

                System.out.println("Products data: " + products);
                System.out.println("Reviews data: " + reviews);
                System.out.println("Inventory data: " + inventory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
        
    }
    private static CompletableFuture<String> fetchData(HttpClient client, String url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return response.body();
            } catch (Exception e) {
                throw new RuntimeException("Failed to fetch data from " + url, e);
            }
        });
    }
}
    



 ```
 1. Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API call, return a default value and log the exception information

```java

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureHw3 {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        CompletableFuture<String> productsFuture = fetchData(client, "https://jsonplaceholder.typicode.com/posts").exceptionally(e -> {
            System.err.println("Failed to fetch products: " + e.getMessage());
            return "Default Products Data";
        });

        CompletableFuture<String> reviewsFutrue = fetchData(client, "https://jsonplaceholder.typicode.com/comments").exceptionally(e -> {
            System.err.println("Failed to fetch reviews: " + e.getMessage());
            return "Default Reviews Data";
        });

        CompletableFuture<String> inventoryFuture = fetchData(client, "https://jsonplaceholder.typicode.com/todos").exceptionally(e -> {
            System.err.println("Failed to fetch inventory: " + e.getMessage());
            return "Default Inventory Data";
        });

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(productsFuture, reviewsFutrue, inventoryFuture);

        allFutures.thenRun(() -> {
            try {
                String products = productsFuture.get();
                String reviews = reviewsFutrue.get();
                String inventory = inventoryFuture.get();

                System.out.println("Products data: " + products);
                System.out.println("Reviews data: " + reviews);
                System.out.println("Inventory data: " + inventory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
    }

    private static CompletableFuture<String> fetchData(HttpClient client, String url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return response.body();
            } catch (Exception e) {
                throw new RuntimeException("Failed to fetch data from " + url, e);
            }
        });
    }
    
}


```

