
## Question 2

```java
public class Singleton {
   private static volatile Singleton instance;
   
   private Singleton() {}
   
   public static Singleton getInstance() {
       if (instance == null) {
           synchronized (Singleton.class) {
               if (instance == null) {
                   instance = new Singleton();
               }
           }
       }
       return instance;
   }
}
``` 

## Question 3
```java
ExecutorService executor = Executors.newFixedThreadPool(5);

executor.submit(() -> {
   System.out.println("Task executed");
});

executor.shutdown();
```

## Question 4
Runnable:
- returns void
- cannot throw checked exceptions
- method signature: run()
- doesn't support Future

Callable:
- returns value
- can throw exceptions
- method signature: call()
- returns Future<T<T>>

## Question 5
t.start() creates a new thread and executes run() in that thread, while t.run() executes the code in the current thread without creating a new thread.

## Question 6
Runnable. It allows the class to extend another class, since Java doesn't support multiple inheritance, it provides better separation of concerns and allows the task to be reused.

## Question 7
NEW - thread created but not started
RUNNABLE - thread executing or ready to execute
BLOCKED - thread blocked waiting for monitor lock
WAITING - thread waiting indefinitely for another thread
TIMED_WAITING - thread waiting for specified time period
TERMINATED - thread completed execution

## Question 8

```java
Object lock1 = new Object();
Object lock2 = new Object();

Thread t1 = new Thread(() -> {
    synchronized(lock1) {
        synchronized(lock2) {
            System.out.println("Thread 1");
        }
    }
});

Thread t2 = new Thread(() -> {
    synchronized(lock2) {
        synchronized(lock1) {
            System.out.println("Thread 2");
        }
    }
});

t1.start();
t2.start();
```
Resolution:
```java
Thread t1 = new Thread(() -> {
    synchronized(lock1) {
        synchronized(lock2) {
            System.out.println("Thread 1");
        }
    }
});

Thread t2 = new Thread(() -> {
    synchronized(lock1) {
        synchronized(lock2) {
            System.out.println("Thread 2");
        }
    }
});
```

## Question 9
Communication approaches: 
-   Shared variables
-   wait()/notify()/notifyAll()
-   BlockingQueue
-   Future/CompletableFuture

## Question 10

Object lock: Acquired on instance methods/blocks, each object instance has its own lock
Class lock: Acquired on static methods/blocks, shared across all instances of the class

## Question 11
Makes current thread wait until the target thread completes execution. 



## Question 12
Pause current thread to give chance to other threads of same priority. 

## Question 13

ThreadPool: Collection of reusable worker threads that execute tasks from a queue, avoiding thread creation overhead.
- newFixedThreadPool()
- newCachedThreadPool()
- newSingleThreadExecutor()
- newScheduledThreadPool()
- newWorkStealingPool()

TaskQueue: Internal queue where submitted tasks wait to be executed by worker threads.

## Question 14
java.util.concurrent
ExecutorService

## Question 15
submit():
```java
ExecutorService executor = Executors.newFixedThreadPool(3);

Future<?> future1 = executor.submit(() -> System.out.println("Task"));

Future<String> future2 = executor.submit(() -> { return "Result"; }); // Returns Future

String result = future2.get();
```
execute():
```java
executor.execute(() -> System.out.println("Task")); //No return
```

## Question 16
Advantages:
-   Reuses existing threads instead of create/destroy repeatedly, reducing overhead
-   Limits concurrent threads to prevent system overload
-   Task management: Provides queue-based task scheduling and automatic load balancing across worker threads

## Question 17

shutdown():
- allows currently executing tasks to finish
- returns void


shutdownNow():
- interrupts currently executing tasks
- returns List<Runnable<Runnable>> (waiting tasks)

## Question 18
Atomic classes: Thread-safe classes that provide lock-free atomic operations.
- primitives, e.g. AtomicInteger
- reference types, e.g. AtomicReference<T<T>>
- arrays, e.g. AtomicIntegerArray
- field updaters, e.g. AtomicIntegerFieldUpdater

```java
AtomicInteger counter =  new  AtomicInteger(0); 
counter.get();
counter.set(10);
counter.incrementAndGet();

AtomicReference<String> atomicRef = new  AtomicReference<>("initial");
String current = atomicRef.get();
atomicRef.set("newValue");
```
Used when many threads accessing same variable frequently. 

## Question 19

Concurrent collections: Thread-safe data structures designed for high-performance concurrent access without external synchronization
- ConcurrentHashMap<K,V>
- SynchronousQueue<E<E>>
- ConcurrentSkipListSet<E<E>>

## Question 20
synchronized: automatic release, built-in wait/notify
ReentrantLock: interruptible, timed acquisition, fair/unfair options
ReadWriteLock: better performance for read-heavy tasks
StampedLock: no blocking for readers, highest performance
Semaphore: controls access to resource pool, counting-based synchronization

## Question 21
Future: provides basic methods to check completion and retrieve result of asynchronous computation.

CompletableFuture: enhanced Future with functional programming support, pipeline operations, and manual completion.
- supplyAsync(()  ->  "result")
- thenApply(Function)
- exceptionally(Function)

## Question 23

```java
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class SynchronizedPrinter {
    private boolean oddTurn = true;
    
    public synchronized void printOdd(int num) throws InterruptedException {
        while (!oddTurn) wait();
        System.out.println(Thread.currentThread().getName() + ": " + num);
        oddTurn = false;
        notify();
    }
    
    public synchronized void printEven(int num) throws InterruptedException {
        while (oddTurn) wait();
        System.out.println(Thread.currentThread().getName() + ": " + num);
        oddTurn = true;
        notify();
    }
}

class ReentrantLockPrinter{
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean oddTurn = true;
    
    public void printOdd(int num) throws InterruptedException {
        lock.lock();
        try {
            while (!oddTurn) condition.await();
            System.out.println(Thread.currentThread().getName() + ": " + num);
            oddTurn = false;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
    
    public void printEven(int num) throws InterruptedException {
        lock.lock();
        try {
            while (oddTurn) condition.await();
            System.out.println(Thread.currentThread().getName() + ": " + num);
            oddTurn = true;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SynchronizedPrinter printer =  new  SynchronizedPrinter();
        // ReentrantLockPrinter printer = new ReentrantLockPrinter();
        
        Thread oddThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 9; i += 2) {
                    printer.printOdd(i);
                }
            } catch (InterruptedException e) {}
        }, "Thread-0");
        
        Thread evenThread = new Thread(() -> {
            try {
                for (int i = 2; i <= 10; i += 2) {
                    printer.printEven(i);
                }
            } catch (InterruptedException e) {}
        }, "Thread-1");
        
        oddThread.start();
        evenThread.start();
        
        oddThread.join();
        evenThread.join();
    }
}
```


## Question 24

```java
import java.util.*;

class SequentialPrinter {
    private int nextStart = 1;
    
    public synchronized void printRange(int start, int end) throws InterruptedException {
        while (nextStart != start) wait();
        
        for (int i = start; i <= end; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
        
        nextStart = end + 1;
        notifyAll();
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SequentialPrinter printer = new SequentialPrinter();
        
        int[][] ranges = {{1, 10}, {11, 20}, {21, 22}};
        Collections.shuffle(Arrays.asList(ranges));
        
        Thread thread0 = new Thread(() -> {
            try {
                printer.printRange(ranges[0][0], ranges[0][1]);
            } catch (InterruptedException e) {}
        }, "Thread-0");
        
        Thread thread1 = new Thread(() -> {
            try {
                printer.printRange(ranges[1][0], ranges[1][1]);
            } catch (InterruptedException e) {}
        }, "Thread-1");
        
        Thread thread2 = new Thread(() -> {
            try {
                printer.printRange(ranges[2][0], ranges[2][1]);
            } catch (InterruptedException e) {}
        }, "Thread-2");
        
        thread0.start();
        thread1.start();
        thread2.start();
        
        thread0.join();
        thread1.join();
        thread2.join();
    }
}
```

## Question 25

```java
public class AsyncCalculator {
    public static void main(String[] args) {
        int a = 10, b = 5;
        
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> a + b);
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> a * b);
        
        sumFuture.thenCombine(productFuture, (sum, product) -> {
            System.out.println("Sum: " + sum);
            System.out.println("Product: " + product);
            return null;
        }).join();
    }
}
```
```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

public class BestBuyApiService {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final ExecutorService executor = Executors.newFixedThreadPool(3);

    private static final String PRODUCTS_API = "https://jsonplaceholder.typicode.com/posts";
    private static final String REVIEWS_API = "https://jsonplaceholder.typicode.com/comments";
    private static final String INVENTORY_API = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) {
        BestBuyApiService service = new BestBuyApiService();
        service.fetchAndMergeData().thenAccept(result -> {
            System.out.println("Data: " + result);
        }).join();
        executor.shutdown();
    }

    public CompletableFuture<MergedData> fetchAndMergeData() {
        CompletableFuture<JsonNode> productsFuture = fetchData(PRODUCTS_API, "products");
        CompletableFuture<JsonNode> reviewsFuture = fetchData(REVIEWS_API, "reviews");
        CompletableFuture<JsonNode> inventoryFuture = fetchData(INVENTORY_API, "inventory");

        return CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture)
                .handle((v, throwable) -> new MergedData(
                        productsFuture.join(),
                        reviewsFuture.join(),
                        inventoryFuture.join()
                ));
    }

    private CompletableFuture<JsonNode> fetchData(String apiUrl, String dataType) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(apiUrl))
                        .timeout(Duration.ofSeconds(5))
                        .GET()
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                
                if (response.statusCode() == 200) {
                    return objectMapper.readTree(response.body());
                } else {
                    throw new RuntimeException("API call failed with status: " + response.statusCode());
                }
            } catch (Exception e) {
                return getDefaultData(dataType);
            }
        }, executor).exceptionally(throwable -> getDefaultData(dataType));
    }

    private JsonNode getDefaultData(String dataType) {
        try {
            switch (dataType) {
                case "products":
                    return objectMapper.readTree("[{\"id\":0,\"title\":\"Default Product\",\"body\":\"No products available\"}]");
                case "reviews":
                    return objectMapper.readTree("[{\"id\":0,\"name\":\"Default Review\",\"body\":\"No reviews available\"}]");
                case "inventory":
                    return objectMapper.readTree("[{\"id\":0,\"name\":\"Default Inventory\",\"username\":\"No inventory data\"}]");
                default:
                    return objectMapper.createArrayNode();
            }
        } catch (Exception e) {
            return objectMapper.createArrayNode();
        }
    }

    static class MergedData {
        private final JsonNode products;
        private final JsonNode reviews;
        private final JsonNode inventory;

        public MergedData(JsonNode products, JsonNode reviews, JsonNode inventory) {
            this.products = products;
            this.reviews = reviews;
            this.inventory = inventory;
        }

        public JsonNode getProducts() {
            return products;
        }

        public JsonNode getReviews() {
            return reviews;
        }

        public JsonNode getInventory() {
            return inventory;
        }

        @Override
        public String toString() {
            return String.format("Data{products=%d items, reviews=%d items, inventory=%d items}",
                    products.size(), reviews.size(), inventory.size());
        }
    }
}
```