package org.example;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final String PRODUCT_URL = "https://fakestoreapi.com/products" ;
    private static final String REVIEW_URL = "https://jsonplaceholder.typicode.com/comments" ;
    private static final String INVENTORY_URL = "https://fakestoreapi.com/products/1" ;


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //3
        Runnable task = () -> System.out.println("Running in thread: " + Thread.currentThread().getName());
        Thread thread = new Thread(task);
        thread.start();

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executor.submit(task);
        }
        executor.shutdown();

        //4
        /**
         * 🔄 Runnable vs Callable in Java
         *
         * ✅ Runnable:
         * - Introduced in **Java 1.0**
         * - Represents a **task that runs asynchronously**
         * - Must override the method:
         *     public void run()
         * - **Does NOT return a result**
         * - **Cannot throw checked exceptions**
         * - Typically used with:
         *     - new Thread(new RunnableTask()).start()
         *     - ExecutorService.execute(Runnable)
         *
         * ✅ Callable:
         * - Introduced in **Java 5 (java.util.concurrent)**
         * - Represents a task that can **return a result** and **throw exceptions**
         * - Must override the method:
         *     public T call() throws Exception
         * - Used with:
         *     - ExecutorService.submit(Callable)
         *     - Returns a **Future<T>** object
         *     - Can block on future.get() to get result
         *
         * ✅ Key Differences:
         * ----------------------------------------------------------------
         * | Feature          | Runnable               | Callable           |
         * |------------------|------------------------|---------------------|
         * | Returns result   | ❌ No                  | ✅ Yes              |
         * | Throws exception | ❌ No                  | ✅ Yes (checked)    |
         * | Method name      | run()                  | call()              |
         * | Output type      | void                   | T (generic return)  |
         * | Used with        | Thread, execute()      | submit(), Future<T> |
         * ----------------------------------------------------------------
         *
         * ✅ Example Use Case:
         * - Use Runnable when you just need to **perform a task**
         * - Use Callable when you **need a result** or want to **handle errors**
         */


        //5
        /**
         * 🔄 Difference Between t.start() and t.run() in Java
         *
         * ✅ t.start():
         * - Creates a **new thread**
         * - The **JVM** calls the `run()` method **in a separate call stack**
         * - **Actual multithreading** happens
         * - The thread is managed by the **Thread Scheduler**
         * - Example:
         *     Thread t = new Thread(new MyRunnable());
         *     t.start();  // ✅ New thread starts and runs run()
         *
         * ✅ t.run():
         * - Just calls the `run()` method **like a normal function**
         * - **Does NOT create a new thread**
         * - Executes in the **current thread**
         * - No concurrency is involved
         * - Example:
         *     Thread t = new Thread(new MyRunnable());
         *     t.run();  // ❌ No new thread; just a method call in main thread
         *
         * ✅ Key Differences:
         * ----------------------------------------------------
         * | Feature              | t.start()     | t.run()    |
         * |----------------------|---------------|------------|
         * | Starts new thread    | ✅ Yes        | ❌ No      |
         * | Uses new call stack  | ✅ Yes        | ❌ No      |
         * | Managed by JVM       | ✅ Yes        | ❌ No      |
         * | Executes concurrently| ✅ Yes        | ❌ No      |
         * ----------------------------------------------------
         *
         * ✅ Interview Insight:
         * - Always use **t.start()** to run a thread.
         * - Calling **t.run()** directly defeats the purpose of multithreading.
         */

        //6
        /**
         * 🔄 Thread vs Runnable in Java
         *
         * ✅ Runnable (Recommended):
         * - Implements **Runnable interface**
         * - Allows **extending another class**
         * - Promotes **separation of concerns** (task vs thread)
         * - Works well with **ExecutorService** (thread pools)
         *
         * ✅ Thread (Not recommended):
         * - **Extends Thread class**
         * - Cannot extend other classes (❌ single inheritance)
         * - Logic is tightly coupled with thread behavior
         *
         * ✅ Summary:
         * - Use **Runnable** for clean, reusable, and flexible design
         * - Use **Thread** only for simple or quick tasks
         */

        //7
        /**
         * 🔄 Java Thread States (java.lang.Thread.State)
         *
         * ✅ NEW:
         * - Thread is created but **not yet started**
         * - e.g., Thread t = new Thread(...);

         * ✅ RUNNABLE:
         * - Thread is **ready or running**
         * - Scheduled by the **Thread Scheduler**

         * ✅ BLOCKED:
         * - Thread is waiting to acquire a **monitor lock**
         * - Happens during **synchronized block** contention

         * ✅ WAITING:
         * - Thread is waiting **indefinitely** for another thread to signal
         * - e.g., object.wait(), thread.join(), LockSupport.park()

         * ✅ TIMED_WAITING:
         * - Waiting for a specified **time duration**
         * - e.g., sleep(ms), wait(ms), join(ms), parkNanos()

         * ✅ TERMINATED:
         * - Thread has **finished execution**
         * - Also called **DEAD state**
         *
         * ✅ Summary:
         * NEW → RUNNABLE → [BLOCKED / WAITING / TIMED_WAITING] → TERMINATED
         */

        //8
        final Object lockA = new Object();
        final Object lockB = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("thread 1 holding lockA...");
                try{ Thread.sleep(1000);} catch (InterruptedException ignored) {}
                synchronized (lockB) {
                    System.out.println("thread 1 holding lockB...");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("thread 2 holding lockB...");
                try{ Thread.sleep(1000);} catch (InterruptedException ignored) {}
                synchronized (lockA) {
                    System.out.println("thread 2 holding lockA...");
                }
            }
        });
        t1.start();
        t2.start();

        //9
        /**
         * 🔄 Thread Communication in Java
         *
         * ✅ Threads communicate using:
         * 1. **Shared memory** (common objects/variables)
         * 2. **Object monitors** via methods:
         *    - **wait()**  → thread releases lock and waits
         *    - **notify()** → wakes up one waiting thread
         *    - **notifyAll()** → wakes up all waiting threads
         *
         * ✅ Example:
         * synchronized (sharedObject) {
         *     while (!condition) sharedObject.wait();
         *     // do work
         *     sharedObject.notifyAll();
         * }
         *
         * ✅ Key Rules:
         * - Must call **wait()/notify() inside synchronized block**
         * - Threads must **share the same object monitor**
         *
         * ✅ Alternative Tools:
         * - **BlockingQueue** (safe inter-thread queueing)
         * - **CountDownLatch**, **Semaphore**, **Exchanger**, **Condition**
         *
         * ✅ Interview Insight:
         * - Use **wait/notify** for basic coordination
         * - Use **high-level concurrency utils** for cleaner and safer thread communication
         */

        //10
        /**
         * 🔐 Class Lock vs Object Lock in Java
         *
         * ✅ Object Lock:
         * - Used when a thread enters a **non-static synchronized method/block**
         * - Locks the **current instance (this)**
         * - Each object has its own lock
         *
         * ✅ Class Lock:
         * - Used when a thread enters a **static synchronized method/block**
         * - Locks the **Class object** (e.g., MyClass.class)
         * - Shared across **all instances** of the class
         *
         * ✅ Example:
         * synchronized (this) { ... }         // 🔒 Object Lock
         * synchronized (MyClass.class) { ... } // 🔒 Class Lock
         *
         * ✅ Summary:
         * - **Object Lock** → controls access to instance-level data
         * - **Class Lock** → controls access to static/class-level data
         */

        //11
        /**
         * 🔗 Thread join() Method in Java
         *
         * ✅ Purpose:
         * - Causes the **current thread to wait** until another thread **finishes execution**
         *
         * ✅ Syntax:
         *   thread.join();              // waits indefinitely
         *   thread.join(milliseconds); // waits up to given time
         *
         * ✅ Use Case:
         * - Useful when one thread must **complete before** others can continue
         *
         * ✅ Example:
         *   Thread t = new Thread(() -> {
         *       // background task
         *   });
         *   t.start();
         *   t.join(); // main thread waits until t finishes
         *
         * ✅ Key Points:
         * - **Checked exception**: must handle InterruptedException
         * - Used for **synchronizing thread execution order**
         */

        //12
        /**
         * 🔁 Thread yield() Method in Java
         *
         * ✅ Purpose:
         * - **Hints** to the thread scheduler that the current thread is **willing to pause**
         * - Allows other threads of **equal or higher priority** to run
         *
         * ✅ Syntax:
         *   Thread.yield();
         *
         * ✅ Behavior:
         * - The thread **remains runnable** (not blocked or waiting)
         * - May or may not actually yield — it's **scheduler-dependent**
         * - Often used in **testing**, **low-priority tasks**, or **tight loops**
         *
         * ✅ Example:
         *   for (int i = 0; i < 100; i++) {
         *       System.out.println("Running...");
         *       Thread.yield(); // allows other threads to run
         *   }
         *
         * ✅ Key Points:
         * - **Does not release locks**
         * - **Does not guarantee** another thread will run immediately
         * - Use **sparingly**; avoid relying on it for thread coordination
         */

        //13
        /**
         * 🔄 ThreadPool in Java
         *
         * ✅ What is ThreadPool?
         * - A **pool of pre-created threads** used to execute tasks
         * - Improves performance by **reusing threads**
         * - Managed using **ExecutorService**
         * - Avoids creating a new thread per task (which is expensive)
         *
         * ✅ Common Types of ThreadPools (via Executors):
         * 1. **newFixedThreadPool(int n)**
         *    - Fixed number of threads
         *    - Idle threads reused for new tasks
         *
         * 2. **newCachedThreadPool()**
         *    - Creates new threads as needed
         *    - Reuses idle threads
         *    - Good for many short-lived tasks
         *
         * 3. **newSingleThreadExecutor()**
         *    - Only **one thread**
         *    - Tasks executed sequentially
         *
         * 4. **newScheduledThreadPool(int n)**
         *    - Supports **delayed or periodic** task execution
         *
         * ✅ What is TaskQueue?
         * - A **blocking queue** (usually a **LinkedBlockingQueue**)
         * - Stores tasks **waiting to be executed**
         * - If all threads are busy, tasks go into this queue
         * - Task queue + thread pool = efficient task handling
         *
         * ✅ Summary:
         * - Use **ThreadPool** for scalable and efficient multithreading
         * - TaskQueue **buffers incoming tasks** until a thread is available
         */

        //14
        /**
         * 📚 Library & Interface for ThreadPool in Java
         *
         * ✅ Library:
         * - ThreadPool is part of **java.util.concurrent** package
         * - Provides high-level concurrency tools since **Java 5**
         *
         * ✅ Main Interface:
         * - **ExecutorService**
         *   - Core interface that provides thread-pool functionality
         *   - Key methods:
         *     - execute(Runnable task)
         *     - submit(Callable<T> task)
         *     - shutdown(), shutdownNow()
         *     - invokeAll(), invokeAny()
         *
         * ✅ Implementing Class:
         * - **ThreadPoolExecutor**
         *   - Concrete class that implements **ExecutorService**
         *   - Can be used directly or via **Executors** factory methods
         *
         * ✅ Common Factory:
         * - **Executors** class provides static methods:
         *     - newFixedThreadPool(int)
         *     - newCachedThreadPool()
         *     - newSingleThreadExecutor()
         */

        //15
        ExecutorService executor1 = Executors.newFixedThreadPool(3);

        Runnable runnableTask = () -> {
            System.out.println("runnableTask on thread: " + Thread.currentThread().getName());
        };

        executor1.execute(runnableTask);
        Future<?> futureA = executor1.submit(runnableTask);

        Callable<String> callableTask = () -> {
            Thread.sleep(1000);
            return "callableTask from thread: " + Thread.currentThread().getName();
        };
        Future<String> futureB = executor1.submit(callableTask);

        try{
            System.out.println("Res from callable: " + futureB.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor1.shutdown();

        //16
        /**
         * ✅ Advantages of ThreadPool in Java
         *
         * 1. 🔁 **Thread reuse**
         *    - Reuses existing threads instead of creating new ones for each task
         *    - Reduces thread creation overhead

         * 2. ⚙️ **Improved performance**
         *    - Faster task execution due to ready-to-use threads
         *    - Lower latency in handling short-lived tasks

         * 3. 🎛️ **Controlled concurrency**
         *    - Limits the number of concurrent threads
         *    - Prevents system overload and resource exhaustion

         * 4. 📦 **Task queueing**
         *    - Tasks are held in a queue if all threads are busy
         *    - Ensures orderly execution

         * 5. 🧩 **Better resource management**
         *    - Allows fine-grained control (core size, max size, keep-alive time)

         * 6. 📊 **Scalability**
         *    - Scales well under load with proper tuning
         *    - Works well with `ExecutorService` and high-level concurrency tools

         * ✅ Summary:
         * ThreadPool provides **efficient, scalable, and controlled multithreading**—
         * ideal for production-grade concurrent applications.
         */


        //17
        /**
         * ⚛️ Atomic Classes in Java (java.util.concurrent.atomic)
         *
         * ✅ What are Atomic Classes?
         * - Provide **lock-free**, **thread-safe** operations on single variables
         * - Use **low-level atomic CPU instructions (CAS: compare-and-swap)**
         * - Avoid need for synchronized blocks

         * ✅ When to Use?
         * - When multiple threads **read/write shared variables**
         * - For **counters, flags, and state** without using locks
         * - In **high-performance concurrent systems**

         * ✅ Common Types of Atomic Classes:
         * 1. 🔢 AtomicInteger
         * 2. 🔢 AtomicLong
         * 3. 🧾 AtomicBoolean
         * 4. 🧱 AtomicReference<T>
         * 5. 🧰 AtomicIntegerArray, AtomicLongArray, AtomicReferenceArray

         * ✅ Key Methods:
         * - get()               → read value
         * - set(value)          → write value
         * - incrementAndGet()   → ++x then return
         * - getAndIncrement()   → return x then ++x
         * - compareAndSet(expected, update) → atomic conditional update

         * ✅ Code Examples:
         */
        AtomicInteger counter = new AtomicInteger(0);

        // Increment thread-safe
        counter.incrementAndGet(); // ++counter
        counter.addAndGet(5);      // counter += 5

        // Conditional update
        boolean updated = counter.compareAndSet(6, 10); // if (counter == 6) counter = 10;

        System.out.println("Current Value: " + counter.get());
        System.out.println("Compare-And-Set Success: " + updated);

        // AtomicBoolean Example
        AtomicBoolean flag = new AtomicBoolean(false);
        if (flag.compareAndSet(false, true)) {
            System.out.println("Flag flipped to true atomically.");
        }

        // AtomicReference Example
        AtomicReference<String> name = new AtomicReference<>("Alice");
        name.compareAndSet("Alice", "Bob");
        System.out.println("Updated name: " + name.get());

        //18
        /**
         * 🔄 Concurrent Collections in Java (Thread-Safe)
         *
         * ✅ What Are Concurrent Collections?
         * - Collections in **java.util.concurrent** that are designed for **safe multi-threaded access**
         * - Provide **better performance** than synchronizing standard collections manually
         * - Use **internal partitioning, lock stripping, or non-blocking algorithms**

         * ✅ Common Concurrent Data Structures:

         * 1. 📦 ConcurrentHashMap
         *    - Thread-safe map with high concurrency
         *    - Uses bucket-level locking

         * 2. 📋 CopyOnWriteArrayList
         *    - ArrayList optimized for frequent reads, infrequent writes
         *    - On each write, a new copy is made

         * 3. 📋 CopyOnWriteArraySet
         *    - Thread-safe Set backed by CopyOnWriteArrayList

         * 4. 📚 ConcurrentLinkedQueue
         *    - Non-blocking, lock-free queue (FIFO)

         * 5. 🪜 ConcurrentLinkedDeque
         *    - Thread-safe double-ended queue

         * 6. 🧱 LinkedBlockingQueue
         *    - Blocking queue used in producer-consumer problems

         * 7. 🧱 ArrayBlockingQueue
         *    - Fixed-size blocking queue with internal locking

         * 8. 🧱 PriorityBlockingQueue
         *    - Thread-safe priority queue

         * 9. 🧾 DelayQueue
         *    - Queue where elements become available after a delay

         * 10. 🧮 SynchronousQueue
         *    - Zero-capacity queue; used for handoff between threads

         * ✅ Summary:
         * - Use **ConcurrentHashMap** for shared maps
         * - Use **BlockingQueue** for producer-consumer problems
         * - Use **CopyOnWrite** for mostly-read collections
         */

        //20
        /**
         * 🔐 Types of Locks in Java (Thread Synchronization)
         *
         * ✅ 1. synchronized (Intrinsic Lock)
         * - Implicitly uses the object monitor
         * - Easy to use: synchronized methods or blocks
         * - ❌ Cannot try-lock or timeout
         * - ✅ Best for simple mutual exclusion

         * ✅ 2. ReentrantLock (java.util.concurrent.locks)
         * - Explicit lock with more control
         * - Supports:
         *   - tryLock()
         *   - lockInterruptibly()
         *   - fairness policy
         *   - multiple Condition objects
         * - ✅ More flexible than synchronized

         * ✅ 3. ReentrantReadWriteLock
         * - Separates read and write locks
         * - Allows multiple readers, one writer
         * - ✅ Great for **read-heavy** applications

         * ✅ 4. StampedLock (Java 8+)
         * - Supports:
         *   - read/write locks
         *   - **optimistic reads** (non-blocking)
         * - ✅ Very fast for scenarios with frequent reads, few writes

         * ✅ 5. Semaphore
         * - Limits access to a resource
         * - Has a fixed number of permits
         * - ✅ Good for connection pools, rate limiting

         * ✅ 6. CountDownLatch
         * - One-time latch to wait for threads to finish
         * - ✅ Used for thread joining or coordination

         * ✅ 7. CyclicBarrier
         * - Barrier that **resets automatically**
         * - ✅ Useful for batch thread coordination

         * ✅ 8. LockSupport (Low-level)
         * - Parking/unparking of threads
         * - ✅ Basis for advanced*/

        //22
        ExecutorService executor2 = Executors.newFixedThreadPool(2);
        Future<String> future = executor2.submit(() -> "future result");
        System.out.println("Futures: " + future.get());

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "hello").thenApply(
                s -> s + "world"
        );
        System.out.println("CompletableFuture: " + cf.get());

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            if(true) throw new RuntimeException();
            return "ok";
        }).exceptionally(e -> "recovered: " + e.getMessage() );

        executor2.shutdown();

        //23
        MultiThreadPlayground mtp = MultiThreadPlayground.getInstance();
        Thread printEven = new Thread(() -> {
            for(int i = 2; i <= 10; i+=2) {
                try {
                    //mtp.even(i);
                    mtp.evenLock(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        Thread printOdd = new Thread(() -> {
            for(int i = 1; i <= 9; i+=2) {
                try {
                    //mtp.odd(i);
                    mtp.oddLock(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });


        printOdd.start();
        printEven.start();

        printEven.join();
        printOdd.join();

        //24

        Runnable task0 = () -> {
            for(int i = 1; i <= 10; i++) {
                System.out.println(i);

            }
        };

        Runnable task1 = () -> {
            for(int i = 11; i <= 20; i++) {
                System.out.println(i);
            }
        };

        Runnable task2 = () -> {
            for(int i = 21; i <= 30; i++) {
                System.out.println(i);

            }
        };

        new Thread(() -> {
            try { mtp.printThreadZero(task0); } catch (InterruptedException e) {}
        }).start();

        new Thread(() -> {
            try { mtp.printThreadOne(task1); } catch (InterruptedException e) {}
        }).start();

        new Thread(() -> {
            try { mtp.printThreadTwo(task2); } catch (InterruptedException e) {}
        }).start();


        //25
        int a = 1;
        int b = 2;
        Runnable task3 = () -> {
            int sum = 1 + 1;
            System.out.println(sum);
        };
        CompletableFuture<Integer> cfSum = CompletableFuture.supplyAsync(() -> a).thenApply(
                x -> x + b
        );

        CompletableFuture<Integer> cfProd = CompletableFuture.supplyAsync(() -> a).thenApply(
                x -> x * b
        );

        System.out.println("sum: " + cfSum.get());
        System.out.println("prod: " + cfProd.get());


        //api fetcher 
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest productRequest = HttpRequest.newBuilder().uri(URI.create(PRODUCT_URL)).build();
        HttpRequest reviewRequest = HttpRequest.newBuilder().uri(URI.create(REVIEW_URL)).build();
        HttpRequest inventoryRequest = HttpRequest.newBuilder().uri(URI.create(INVENTORY_URL)).build();


        CompletableFuture<HttpResponse<String>> futureProducts =
                client.sendAsync(productRequest, HttpResponse.BodyHandlers.ofString())
                        .exceptionally(ex -> {
                            System.err.println(ex.getMessage());
                            return HttpResponseStub.withBody("[]");


                        });

        CompletableFuture<HttpResponse<String>> futureReviews =
                client.sendAsync(reviewRequest, HttpResponse.BodyHandlers.ofString())
                        .exceptionally(ex -> {
                            System.err.println(ex.getMessage());
                            return HttpResponseStub.withBody("[]");
                        });


        CompletableFuture<HttpResponse<String>> futureInventory =
                client.sendAsync(inventoryRequest, HttpResponse.BodyHandlers.ofString())
                        .exceptionally(ex -> {
                            System.err.println(ex.getMessage());
                            return HttpResponseStub.withBody("[]");
                        });

        CompletableFuture<String> mergedJson =
                CompletableFuture.allOf(futureProducts, futureReviews, futureInventory).thenApply(v -> {
                    String products = futureProducts.join().body();
                    String reviews = futureReviews.join().body();
                    String inventory = futureInventory.join().body();

                    String merged = String.format( "{ \"products\": %s, \"reviews\": %s, \"inventory\": %s }", products, reviews, inventory);
                    System.out.println("merged json" + merged);
                    return merged;
                });

    }
}

class Singleton  {
    private Singleton() {}

    private static class Holder {
        private static final Singleton instance = new Singleton();
    }
    public static Singleton getInstance() {
        return Holder.instance;
    }
}


class MultiThreadPlayground {
    private boolean oddTurn = true;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition conditionOdd = lock.newCondition();
    private final Condition conditionEven = lock.newCondition();

    private int state = 0;



    private MultiThreadPlayground() {}

    private static class Holder {
        private static final MultiThreadPlayground instance = new MultiThreadPlayground();
    }
    public static MultiThreadPlayground getInstance() {
        return MultiThreadPlayground.Holder.instance;
    }


    public synchronized void odd(int num) throws InterruptedException {
        while (!oddTurn) wait();

        System.out.println(num);
        oddTurn = false;
        notifyAll();
    }

    public synchronized void even(int num) throws InterruptedException {
        while (oddTurn) wait();

        System.out.println(num);
        oddTurn = true;
        notifyAll();
    }

    public void oddLock(int num) throws InterruptedException {
        lock.lock();
        try{

            while (!oddTurn) conditionOdd.await();

            System.out.println(num);
            oddTurn = false;
            conditionEven.signal();


        } finally {
            lock.unlock();

        }

    }

    public void evenLock(int num) throws InterruptedException {
        lock.lock();

        try{
            while (oddTurn) conditionEven.await();

            System.out.println(num);
            oddTurn = true;
            conditionOdd.signal();

        }finally {
            lock.unlock();
        }

    }


    public synchronized void printThreadZero(Runnable r) throws InterruptedException {
        r.run();
        state = 1;
        notifyAll();
    }

    public synchronized void printThreadOne(Runnable r) throws InterruptedException {
        while (state != 1) wait();
        r.run();
        state = 2;
        notifyAll();
    }

    public synchronized void printThreadTwo(Runnable r) throws InterruptedException {
        while (state != 2) wait();
        r.run();
        notifyAll();
    }


    public int sum(int x, int y) {
        return x + y;
    }

    public int prod(int x, int y) {
        return x * y;
    }


}




class HttpResponseStub {

    public static HttpResponse<String> withBody(String body) {
        return new HttpResponse<String>() {
            @Override public int statusCode() { return 501;} //default code
            @Override public String body() { return body; }
            @Override public HttpRequest request() { return null; }
            @Override public Optional<HttpResponse<String>> previousResponse() { return Optional.empty(); }
            @Override public HttpHeaders headers() { return HttpHeaders.of(Map.of(), (s1, s2) -> true); }
            @Override public URI uri() { return URI.create(""); }
            @Override public HttpClient.Version version() { return HttpClient.Version.HTTP_1_1; }
            @Override public Optional<SSLSession> sslSession() { return Optional.empty(); }
        };
    }
}


