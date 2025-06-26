# 06/25

1. Bill Pugh Singleton Pattern: lazy initialization (the instance is created only when `getInstance()` is called), thread-safe, avoids performance hit.
    
    ```java
    public class SingletonExample {
    	//private constructor 
    	//prevents instantiation from other classes
    	private Singleton() {
    	
    	}
    	//inner classes are not loaded until they are referenced
    	private static class SingletonExampleHelper {
    		private static final SingletonExample INSTANCE = new SingletonExample();
    	}
    	public static SingletonExample getInstance() {
    		return SingletonExampleHelper.INSTANCE;
    	}
    }
    ```
    
2. Basic
    
    ```java
    // Thread + Runnable
    public class ThreadExample {
        public static void main(String[] args) {
            Runnable task = () -> System.out.println("Running: " + Thread.currentThread().getName());
            Thread thread = new Thread(task);
            thread.start();
        }
    }
    // Extending the thread class
    class ThreadExample2 extends Thread {
        public void run() {
            System.out.println("Running: " + Thread.currentThread().getName());
        }
    }
    
    public class Demo {
        public static void main(String[] args) {
            new ThreadExample2().start();
        }
    }
    ```
    
    Thread pool
    
    ```java
    import java.util.concurrent.*;
    
    //Fixed Thread Pool
    //good for known number of long-running or CPU bound tasks
    // no idle thread timeout
    public class ThreadPoolExample {
        public static void main(String[] args) {
            ExecutorService executor = Executors.newFixedThreadPool(3);
    
            Runnable task = () -> System.out.println("Running: " + Thread.currentThread().getName());
            executor.submit(task);
    
            executor.shutdown(); // Shutdown gracefully
            /**
            //good for many short-lived tasks or bursty workloads
            //default idle thread timeout 60s
            ExecutorService executor = Executors.newCachedThreadPool();
    				executor.execute(() -> System.out.println("Running: " + Thread.currentThread().getName()));
    				executor.shutdown();
    
            */
        }
    }
    
    ```
    
3. Runnable VS Callable
    1. return type: runnable - void, callable - any value (via `Future`)
    2. exception: runnable - can’t throw checked exception, callable - can throw checked exception. Checked exception - checked by compiler, unchecked - `RuntimeException`
4. `t.start()` creates a new thread, `t.run()` runs on the current thread (no new thread).
5. `Runnable` interface is better. It allows extending other classes. It’s better decoupling of logic from threading. It’s cleaner for thread pooling.
6. `New`, `Runnable`, `Blocked`, `Waiting`, `Timed_Awaiting`, `Terminated`
7. A deadlock occurs when two or more threads are blocked forever, each waiting for the other to release a lock. 
    
    ```java
    //deadlock demo
    public class DeadlockExample {
    
        private static final Object lock1 = new Object();
        private static final Object lock2 = new Object();
    
        public static void main(String[] args) {
            Thread t1 = new Thread(() -> {
                synchronized (lock1) {
                    System.out.println("Thread 1: holding lock1...");
                    try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                    System.out.println("Thread 1: waiting for lock2...");
                    synchronized (lock2) {
                        System.out.println("Thread 1: acquired lock2.");
                    }
                }
            });
    
            Thread t2 = new Thread(() -> {
                synchronized (lock2) {
                    System.out.println("Thread 2: holding lock2...");
                    try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                    System.out.println("Thread 2: waiting for lock1...");
                    synchronized (lock1) {
                        System.out.println("Thread 2: acquired lock1.");
                    }
                }
            });
    
            t1.start();
            t2.start();
        }
    }
    /**
    Thread 1: holding lock1...
    Thread 2: holding lock2...
    Thread 1: waiting for lock2...
    Thread 2: waiting for lock1...
    
    */
    ```
    
    Resolve:
    
    ```java
    public class DeadlockResolved {
        private static final Object lockA = new Object();
        private static final Object lockB = new Object();
    
        public static void main(String[] args) {
            Runnable task = () -> {
                synchronized (lockA) {
                    System.out.println(Thread.currentThread().getName() + ": Holding lockA...");
                    try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                    synchronized (lockB) {
                        System.out.println(Thread.currentThread().getName() + ": Acquired lockB");
                    }
                }
            };
    
            new Thread(task, "Thread 1").start();
            new Thread(task, "Thread 2").start();
        }
    }
    ```
    
    Best practices to avoid deadlocks: always acquire multiple locks in the same order, use timeout-based locking, keep synchronized section as short as possible. 
    
8. Java threads communicate using shared objects and synchronization mechanisms. 
    1. using `wait()`, `notify()`, and `notifyAll()`
        
        ```java
        class MessageBox {
            private String message;
            private boolean hasMessage = false;
        
            public synchronized void put(String msg) {
                while (hasMessage) {
        	        try {
        		        wait();
        	        } catch (InterruptedException e) {}
                }
                message = msg;
                hasMessage = true;
                notify(); // Notify the waiting consumer
            }
        
            public synchronized String get() {
                while (!hasMessage) {
        	        try {
        		        wait();
        	        } catch (InterruptedException e) {}
                }
                hasMessage = false;
                notify(); // Notify the waiting producer
                return message;
            }
        }
        
        ```
        
    2. producer-consumer example
        
        ```java
        MessageBox box = new MessageBox();
        
        Thread producer = new Thread(() -> box.put("Hello from Producer!"));
        Thread consumer = new Thread(() -> System.out.println(box.get()));
        
        producer.start();
        consumer.start();
        
        ```
        
    3. other techniques: `BlockingQueue`, `Atomic` classes (safe communication for numeric or reference types)
    
    Always use (a) inside a `synchronized` block
    
    prefer `java.util.concurrent` classes (`BlockingQueue`, `CountDownLatch`, `Semaphore`) in real-world for better readability and control.
    
9. Class lock and object lock refer to what part of memory is locked when using `synchronized`.
    1. Object lock applies `synchronized` instance methods(`this`), class lock applies in static methods or ClassName.class.
        
        ```java
        //Object lock: Other threads can still access 
        //synchronized methods of other instances
        public synchronized void instanceMethod() {}
        synchronized(this) {}
        
        //Class lock: no instance (or thread) can execute
        //any static synchronized method concurrently
        public static synchronized void staticMethod() {}
        synchronized(ClassName.class) {}
        ```
        
    2. Object lock won’t affect other instances while class lock will.
10. `join()` method is used to pause the current thread until the target thread finishes execution. It can ensure one thread completes before another continues. 
    
    ```java
    public class JoinExample {
        public static void main(String[] args) throws InterruptedException {
            Thread t1 = new Thread(() -> {
                System.out.println("Thread 1 starting...");
                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
                System.out.println("Thread 1 done.");
            });
    
            t1.start();
    
            System.out.println("Main thread waiting for Thread 1...");
            t1.join(); // Main thread waits here
            System.out.println("Main thread resumes after Thread 1 finishes.");
        }
    }
    /**
    Main thread waiting for Thread 1...
    Thread 1 starting...
    Thread 1 done.
    Main thread resumes after Thread 1 finishes.
    */
    ```
    
11. `yield()` is a static method that tells the currently executing thread to pause and allow other threads of the same priority a chance to run.
    
    It should be used in low-level thread tuning (rare). It’s also good for improving responsiveness in cooperative multitasking.
    
    ```java
    public class YieldExample {
        public static void main(String[] args) {
            Runnable task = () -> {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
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
    
12. ThreadPool manages a pool of worker threads. It includes `FixedThreadPool`, `CachedThreadPool`, and `ScheduledThreadPool`. `TaskQueue` holds tasks before threads pick them up. 
13. Library: `java.util.concurrent`, Interface: `ExecutorService`, `Executor`
14. `executor.submit(() -> doWork());`
15. It’s good for thread reuse. It limits resource consumption. It’s better performance.
16. `shutdown()` waits for tasks to finish. `shutdownNow()` tries to stop tasks immediately (may interrupt threads).
17. Atomic classes are part of the `java.util.concurrent.atomic`. They provide lock-free, thread-safe operations on single variables (like int, long, boolean and references) using low-level atomic CPU instruction (CAS - Compare-And-Swap). 
    
    It is used when multiple threads update a shared variable. It’s used in high-performance, low-contention scenarios. 
    
    It includes primitives (`AtomicInteger`, `AtomicLong`, `AtomicBoolean`), arrays (`AtomicIntegerArray`, `AtomicLongArray`, `AtomicReferenceArray`), objects (`AtomicReference`, `AtomicStampedReference`, `AtomicMarkableReference`), accumulators (`LongAdder`, `DoubleAdder`, `LongAccumulator`) atomic classes.
    
    ```java
    import java.util.concurrent.atomic.AtomicInteger;
    
    public class AtomicExample {
        public static void main(String[] args) {
            AtomicInteger count = new AtomicInteger(0);
    
            // Atomically increments by 1
            count.incrementAndGet(); // Returns 1
    
            // Atomically adds value
            count.addAndGet(5);      // Returns 6
    
            // Atomically sets value
            count.set(10);
    
            // Atomically compare and set
            boolean updated = count.compareAndSet(10, 20); // true if current == 10
    
            System.out.println("Final count: " + count.get()); // 20
        }
    }
    ```
    
18. Concurrent collections are thread-safe alternatives to standard Java collections that are designed for concurrent access. It allows safe, efficient operations by multiple threads. `ConcurrentHashMap`, `ConcurrentLinkedQueue`, `CopyOnWriteArrayList`, etc.
19. Locks
    1. `synchronized` :It’s good for simple and integrates with wait/notify. 
    2. `ReentrantLock` : It’s fairness and interruptible locking.
    3. `ReentrantReadWriteLock` : It’s used for multiple readers and single writer.
    4. `StampedLock` : Optimistic locking and lower overhead
    5. `Semaphore` : multiple permits and fair access.
20. `Future` and `CompletableFuture` are used for asynchronous programming, but they differ in flexibility and power. 
    
    Limitations of `Future` : can’t chain tasks. Blocks on get(). No built-in exception handling. 
    
    ```java
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Future<Integer> future = executor.submit(() -> 2 + 3);
    
    // Blocking call — waits until result is ready
    int result = future.get();
    System.out.println("Result: " + result);
    
    ```
    
    `CompletableFuture` is applied in Java 8+ version. It’s workable for chained, non-blocking or reactive flows. 
    
    ```java
    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 2 + 3);
    
    future.thenApply(result -> result * 2)
          .thenAccept(System.out::println); // Prints 10
    
    ```
    
21. N/A
22. `synchronized` + `wait/notify`
    
    ```java
    public class OddEvenPrinterSync {
        private int count = 1;
        private final int MAX = 10;
        private final Object lock = new Object();
    
        public void printOdd() {
            while (count <= MAX) {
                synchronized (lock) {
                    if (count % 2 == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException ignored) {}
                    } else {
                        System.out.println("Odd: " + count++);
                        lock.notify();
                    }
                }
            }
        }
    
        public void printEven() {
            while (count <= MAX) {
                synchronized (lock) {
                    if (count % 2 != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException ignored) {}
                    } else {
                        System.out.println("Even: " + count++);
                        lock.notify();
                    }
                }
            }
        }
    
        public static void main(String[] args) {
            OddEvenPrinterSync printer = new OddEvenPrinterSync();
            Thread t1 = new Thread(printer::printOdd);
            Thread t2 = new Thread(printer::printEven);
            t1.start();
            t2.start();
        }
    }
    
    ```
    
    `ReentrantLock` + `Condition`
    
    ```java
    import java.util.concurrent.locks.*;
    
    public class OddEvenPrinterLock {
        private int count = 1;
        private final int MAX = 10;
        private final Lock lock = new ReentrantLock();
        private final Condition oddTurn = lock.newCondition();
        private final Condition evenTurn = lock.newCondition();
    
        public void printOdd() {
            while (count <= MAX) {
                lock.lock();
                try {
                    while (count % 2 == 0) {
                        oddTurn.await();
                    }
                    System.out.println("Odd: " + count++);
                    evenTurn.signal();
                } catch (InterruptedException ignored) {
                } finally {
                    lock.unlock();
                }
            }
        }
    
        public void printEven() {
            while (count <= MAX) {
                lock.lock();
                try {
                    while (count % 2 != 0) {
                        evenTurn.await();
                    }
                    System.out.println("Even: " + count++);
                    oddTurn.signal();
                } catch (InterruptedException ignored) {
                } finally {
                    lock.unlock();
                }
            }
        }
    
        public static void main(String[] args) {
            OddEvenPrinterLock printer = new OddEvenPrinterLock();
            Thread t1 = new Thread(printer::printOdd);
            Thread t2 = new Thread(printer::printEven);
            t1.start();
            t2.start();
        }
    }
    
    ```
    
23. Answer
    
    ```java
    public class PrintNumber1 {
        public static void main(String[] args) {
            Runnable task1 = () -> {
                for (int i = 1; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    try { Thread.sleep(5); } catch (InterruptedException ignored) {}
                }
            };
    
            Runnable task2 = () -> {
                for (int i = 11; i <= 20; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    try { Thread.sleep(5); } catch (InterruptedException ignored) {}
                }
            };
    
            Runnable task3 = () -> {
                for (int i = 21; i <= 22; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    try { Thread.sleep(5); } catch (InterruptedException ignored) {}
                }
            };
    
            Thread t1 = new Thread(task1, "Thread-0");
            Thread t2 = new Thread(task2, "Thread-1");
            Thread t3 = new Thread(task3, "Thread-2");
    
            t1.start();
            t2.start();
            t3.start();
        }
    }
    
    ```
    
24. Completable Future
    1. 
        
        ```java
        import java.util.concurrent.CompletableFuture;
        
        public class Homework1 {
            public static void main(String[] args) {
                int a = 4, b = 5;
        
                CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> a + b);
                CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> a * b);
        
                sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));
                productFuture.thenAccept(product -> System.out.println("Product: " + product));
            }
        }
        
        ```
        
    2. fake api
        
        ```java
        import java.util.concurrent.*;
        import java.net.http.*;
        import java.net.URI;
        
        public class Homework2 {
            static HttpClient client = HttpClient.newHttpClient();
        
            public static CompletableFuture<String> fetch(String url) {
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
                return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                             .thenApply(HttpResponse::body);
            }
        
            public static void main(String[] args) {
                String productAPI = "https://jsonplaceholder.typicode.com/posts/1";
                String reviewAPI = "https://jsonplaceholder.typicode.com/comments/1";
                String inventoryAPI = "https://jsonplaceholder.typicode.com/users/1";
        
                CompletableFuture<String> productFuture = fetch(productAPI);
                CompletableFuture<String> reviewFuture = fetch(reviewAPI);
                CompletableFuture<String> inventoryFuture = fetch(inventoryAPI);
        
                CompletableFuture<Void> combined = CompletableFuture.allOf(productFuture, reviewFuture, inventoryFuture);
        
                combined.thenRun(() -> {
                    try {
                        String product = productFuture.get();
                        String review = reviewFuture.get();
                        String inventory = inventoryFuture.get();
        
                        System.out.println("Product:\n" + product);
                        System.out.println("Review:\n" + review);
                        System.out.println("Inventory:\n" + inventory);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).join();
            }
        }
        
        ```
        
    3. Exception 
        
        ```java
        // Modified from Homework2
        public static CompletableFuture<String> safeFetch(String url, String defaultValue) {
            return fetch(url)
                   .exceptionally(ex -> {
                       System.err.println("Failed to fetch from: " + url);
                       ex.printStackTrace();
                       return defaultValue;
                   });
        }
        
        public static void main(String[] args) {
            String defaultProduct = "{\"product\": \"default\"}";
            String defaultReview = "{\"review\": \"default\"}";
            String defaultInventory = "{\"inventory\": \"default\"}";
        
            CompletableFuture<String> productFuture = safeFetch(productAPI, defaultProduct);
            CompletableFuture<String> reviewFuture = safeFetch(reviewAPI, defaultReview);
            CompletableFuture<String> inventoryFuture = safeFetch(inventoryAPI, defaultInventory);
        
            CompletableFuture.allOf(productFuture, reviewFuture, inventoryFuture).thenRun(() -> {
                try {
                    System.out.println("Product: " + productFuture.get());
                    System.out.println("Review: " + reviewFuture.get());
                    System.out.println("Inventory: " + inventoryFuture.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).join();
        }
        
        ```