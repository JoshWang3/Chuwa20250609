# HW6

**1. Read: https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock** 

- **Class Lock**: In java, each and every class has a unique lock usually referred to as a class level lock. These locks are achieved using the keyword ‘static synchronized’ and can be used to make static data thread-safe. It is generally used when one wants to prevent multiple threads from entering a synchronized block. 

  Example: 

  ```java
  public class ClassLevelLockExample  
  {    
    public void classLevelLockMethod()  
   {       
       synchronized (ClassLevelLockExample.class)  
         {         
              //DO your stuff here       
         }    
   } 
  } 
  ```

  **Object Lock**: In java, each and every object has a unique lock usually referred to as an object-level lock. These locks are achieved using the keyword ‘synchronized’ and can be used to protect non-static data. It is generally used when one wants to synchronize a non-static method or block so that only the thread will be able to execute the code block on a given instance of the class.  

  Example:  

  ```java
  public class ObjectLevelLockExample  
  {    
    public void objectLevelLockMethod()  
   {   
       synchronized (this)  
         {     
              //DO your stuff here   
         } 
   }
  } 
  ```

---

### **2. Write a thread-safe singleton class**  

```java
public class Singleton {
    private static volatile Singleton instance;
    private Singleton() { }
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

---

### **3. How to create a new thread (Please also consider Thread Pool approach)?**  

```java
// a) Extend Thread
class MyThread extends Thread {
    public void run() { System.out.println("Hello from MyThread"); }
}

// b) Implement Runnable
class MyTask implements Runnable {
    public void run() { System.out.println("Hello from MyTask"); }
}

// c) Thread pool via ExecutorService
import java.util.concurrent.*;
ExecutorService pool = Executors.newFixedThreadPool(4);
pool.execute(new MyTask());                     // Runnable
Future<String> future = pool.submit(() -> "Hi"); // Callable
pool.shutdown();
```

---

### **4. Difference between Runnable and Callable?**  

| Aspect                | Runnable                   | Callable<V>                         |
|-----------------------|----------------------------|-------------------------------------|
| Method signature      | `void run()`               | `V call()`                          |
| Return value          | none                       | returns `V`                         |
| Exceptions             | cannot throw checked ones | can throw checked exceptions        |
| Used with             | `new Thread(r).start()` or `execute(r)` | `submit(callable)` returns `Future<V>` |

---

### **5. What is the difference between `t.start()` and `t.run()`?**  

- **`t.start()`**: Creates a new OS thread, then invokes `t.run()` in that thread.  
- **`t.run()`**: Just calls the method on the current thread—no new thread is spawned.

---

### **6. Which way of creating threads is better: Thread class or Runnable interface?**  

Implementing **`Runnable`** (or `Callable`) is preferred because:  

1. Separates task from threading mechanism.  
2. Allows your class to extend another class.  
3. Integrates cleanly with thread pools.

---

### **7. What are the thread statuses?**  

1. **NEW** – created, not yet started  
2. **RUNNABLE** – executing or ready to execute  
3. **BLOCKED** – waiting for monitor lock  
4. **WAITING** – waiting indefinitely (`wait()`, `join()` without timeout)  
5. **TIMED_WAITING** – waiting with timeout (`sleep()`, `join(ms)`, `await(ms)`)  
6. **TERMINATED** – finished execution

---

### **8. Demonstrate deadlock and how to resolve it in Java code.**  

```java
public class DeadlockDemo {
    private final Object A = new Object(), B = new Object();

    void task1() {
        synchronized (A) {
            sleep(50);
            synchronized (B) {
                System.out.println("T1 got A→B");
            }
        }
    }
    void task2() {
        synchronized (B) {
            sleep(50);
            synchronized (A) {
                System.out.println("T2 got B→A");
            }
        }
    }
    private void sleep(long ms) { try { Thread.sleep(ms); } catch (InterruptedException ignored){} }

    public static void main(String[] args) {
        DeadlockDemo d = new DeadlockDemo();
        new Thread(d::task1, "T1").start();
        new Thread(d::task2, "T2").start();
    }
}
```
**Resolve** by enforcing **lock ordering** (always lock `A` then `B`), or use `ReentrantLock.tryLock(timeout)` with back-off.

---

### **9. How do threads communicate each other?**  

- **`wait()/notify()/notifyAll()`** on shared monitor  
- **`join()`** to wait for another thread’s completion  
- **`volatile`** for safe flag publishing  
- High-level constructs: **`BlockingQueue`**, **`CountDownLatch`**, **`CyclicBarrier`**, **`Exchanger`**, **`Phaser`**

---

### **10. What’s the difference between class lock and object lock?**  

- **Object lock**: per‐instance monitor  
- **Class lock**: per‐class (`Class` object) monitor, serializes static synchronized methods across all instances.

---

### **11. What is `join()` method?** 

`thread.join()` causes the current thread to wait until `thread` completes (or until an optional timeout expires).

---

### **12. What is `yield()` method?** 

A hint to the scheduler that the current thread is willing to pause and let other same-priority threads run. It moves the thread from RUNNING back to RUNNABLE.

---

### **13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?**  

- **ThreadPool**: a pool of worker threads & a queue of tasks to reuse threads.  
- **Common types**:  
  - **Fixed** (`newFixedThreadPool`)  
  - **Cached** (`newCachedThreadPool`)  
  - **Single** (`newSingleThreadExecutor`)  
  - **Scheduled** (`newScheduledThreadPool`)  
- **TaskQueue**: a `BlockingQueue<Runnable>` (e.g. `LinkedBlockingQueue`, `ArrayBlockingQueue`, `SynchronousQueue`) holding tasks awaiting execution.

---

### **14. Which Library is used to create ThreadPool? Which Interface provides main functions of thread-pool?**  

- **Library**: `java.util.concurrent`  
- **Interface**: `ExecutorService` (extends `Executor`), with methods like `submit()`, `shutdown()`, etc.

---

### **15. How to submit a task to ThreadPool?**  

```java
ExecutorService exec = Executors.newFixedThreadPool(4);
exec.execute(() -> System.out.println("Runnable"));           // Runnable
Future<Integer> f = exec.submit(() -> 1 + 2);                 // Callable, returns Future<Integer>
```

---

### **16. What is the advantage of ThreadPool?**  

- **Reuses threads** ⇒ lower creation overhead  
- **Bounds concurrency** ⇒ avoids resource exhaustion  
- **Provides queuing** & **rejection policies**  
- **Graceful shutdown** & **lifecycle control**

---

### **17. Difference between `shutdown()` and `shutdownNow()` methods of executor**  

- **`shutdown()`**: stops accepting new tasks, waits for queued and running tasks to finish.  
- **`shutdownNow()`**: attempts to cancel running tasks (via `interrupt()`), returns the list of tasks awaiting execution.

---

### **18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. When to use it?**  

- **Atomic classes** are lock-free, thread-safe primitives in `java.util.concurrent.atomic`.  
- **Common types**: `AtomicInteger`, `AtomicLong`, `AtomicBoolean`, `AtomicReference<V>`, `AtomicIntegerArray`, etc.  
- **Key methods**:  
  - `get()`, `set()`  
  - `getAndIncrement()`, `incrementAndGet()`  
  - `compareAndSet(expected, update)`  
```java
AtomicInteger counter = new AtomicInteger(0);
int prev = counter.getAndIncrement();  // atomically returns old value, then increments
boolean ok = counter.compareAndSet(5, 10);
```

Use when you need high-performance counters or references without full locks.

---

### **19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)** 

From `java.util.concurrent`:  

- **Maps**: `ConcurrentHashMap`  
- **Lists/Sets**: `CopyOnWriteArrayList`, `CopyOnWriteArraySet`  
- **Queues**: `ConcurrentLinkedQueue`, `LinkedBlockingQueue`, `ArrayBlockingQueue`, `PriorityBlockingQueue`  
- **Deques**: `ConcurrentLinkedDeque`, `LinkedBlockingDeque`

---

### **20. What kind of locks do you know? What is the advantage of each lock?**  

- **`synchronized`**: built-in, simple, reentrant  
- **`ReentrantLock`**: explicit lock, supports fairness, `tryLock()`, multiple `Condition`s  
- **`ReadWriteLock`** (`ReentrantReadWriteLock`): shared reads & exclusive writes  
- **`StampedLock`**: optimistic reads, ideal for mostly-read scenarios  
- **`ReentrantReadWriteLock`**: improved throughput when reads dominate

---

### **21. What is future and completableFuture? List some main methods of CompletableFuture.**  

- **`Future<V>`**: represents a pending result; `get()` blocks until available.  
- **`CompletableFuture<V>`**: non-blocking, composable.  
  **Common methods**:  
  - `supplyAsync(Supplier<U>)`, `runAsync(Runnable)`  
  - `thenApply()`, `thenAccept()`, `thenCompose()`  
  - `exceptionally()`, `whenComplete()`  
  - `allOf()`, `anyOf()`

---

### **22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)** 

---

### **23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)**  

```
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
```

1) One solution use synchronized and wait notify 


```java
package com.chuwa.tutorial.t08_multithreading.c05_waitNotify;

public class OddEvenSynch {
    private static final Object lock = new Object();
    private static int number = 1;
    private static final int MAX = 10;

    public static void main(String[] args) throws InterruptedException {
        // Thread-0 will print odd numbers (number % 2 == 1)
        // Thread-1 will print even numbers (number % 2 == 0)
        Runnable oddTask  = () -> print(1);
        Runnable evenTask = () -> print(0);

        Thread tOdd  = new Thread(oddTask,  "Thread-0");
        Thread tEven = new Thread(evenTask, "Thread-1");

        tOdd.start();
        tEven.start();

        tOdd.join();
        tEven.join();
        System.out.println("Process finished with exit code 0");
    }

    /**
     * Prints numbers from 1 to MAX.
     * threadFlag = 1 for the odd-number thread, 0 for the even-number thread.
     */
    private static void print(int threadFlag) {
        while (true) {
            synchronized (lock) {
                // If it's not this thread's turn, wait
                while (number <= MAX && number % 2 != threadFlag) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                // If all numbers have been printed, notify the other thread and exit
                if (number > MAX) {
                    lock.notifyAll();
                    break;
                }
                // Print the current number and increment
                System.out.println(Thread.currentThread().getName() + ": " + number++);
                // Notify the other thread to proceed
                lock.notifyAll();
            }
        }
    }
}
```

2. One solution use ReentrantLock and await, signal

   ```java
   package com.chuwa.tutorial.t08_multithreading.c05_waitNotify;
   
   import java.util.concurrent.locks.Condition;
   import java.util.concurrent.locks.Lock;
   import java.util.concurrent.locks.ReentrantLock;
   
   public class OddEvenLock {
       private static final Lock lock = new ReentrantLock();
       private static final Condition condition = lock.newCondition();
       private static int number = 1;
       private static final int MAX = 10;
   
       public static void main(String[] args) throws InterruptedException {
           // Create two tasks: 
           //  - oddTask prints odd numbers (threadFlag = 1)
           //  - evenTask prints even numbers (threadFlag = 0)
           Runnable oddTask  = () -> print(1);
           Runnable evenTask = () -> print(0);
   
           Thread tOdd  = new Thread(oddTask,  "Thread-0");
           Thread tEven = new Thread(evenTask, "Thread-1");
   
           tOdd.start();
           tEven.start();
   
           tOdd.join();
           tEven.join();
           System.out.println("Process finished with exit code 0");
       }
   
       /**
        * Prints numbers from 1 to MAX, alternating between odd and even threads.
        * threadFlag = 1 for odd thread, = 0 for even thread.
        */
       private static void print(int threadFlag) {
           while (true) {
               lock.lock();
               try {
                   // If it's not this thread's turn, wait.
                   while (number <= MAX && number % 2 != threadFlag) {
                       condition.await();
                   }
                   // If we've printed past MAX, signal the other thread and exit.
                   if (number > MAX) {
                       condition.signalAll();
                       break;
                   }
                   // Print the current number and increment.
                   System.out.println(Thread.currentThread().getName() + ": " + number++);
                   // Signal the other thread to proceed.
                   condition.signalAll();
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
                   return;
               } finally {
                   lock.unlock();
               }
           }
       }
   }
   ```
   ### 24.create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random. (solution is in `com.chuwa.exercise.t08_multithreading.PrintNumber1`)

   ```
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
   ```

   ```java
   package com.chuwa.exercise.t08_multithreading;
   
   public class PrintNumber1 {
       // Shared counter starting from 1
       private static int n = 1;
   
       public static void main(String[] args) {
           // Create three threads, which will be named Thread-0, Thread-1, Thread-2 by default
           Thread t1 = new Thread(PrintNumber1::printNumber);
           Thread t2 = new Thread(PrintNumber1::printNumber);
           Thread t3 = new Thread(PrintNumber1::printNumber);
   
           t1.start();
           t2.start();
           t3.start();
       }
   
       /**
        * When a thread calls this method, it prints 10 numbers in sequence
        * under the synchronized lock of PrintNumber1.class.
        * Because the method is static synchronized, only one thread can execute it at a time.
        */
       private static synchronized void printNumber() {
           int count = 10; // Each thread prints 10 times
           while (count-- > 0) {
               System.out.println(Thread.currentThread().getName() + ": " + n++);
               try {
                   // Sleep briefly to make the output order easier to observe
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
                   return;
               }
           }
           // Notify any threads waiting on PrintNumber1.class (though none are actually waiting here)
           PrintNumber1.class.notifyAll();
       }
   }
   ```

   

   ### 25.Completable future:

   **25.1-Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.**

   ```java
   import java.util.concurrent.CompletableFuture;
   
   public class AsyncCalculator {
       public static void main(String[] args) {
           int a = 5;
           int b = 3;
   
           // Start two asynchronous tasks: one to compute the sum, one to compute the product
           CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
               // Simulate a time-consuming operation
               sleep(500);
               return a + b;
           });
   
           CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
               // Simulate a time-consuming operation
               sleep(500);
               return a * b;
           });
   
           // When both results are ready, print the output
           CompletableFuture<Void> resultFuture = sumFuture.thenCombine(
               productFuture,
               (sum, product) -> {
                   System.out.println("Sum: " + sum);
                   System.out.println("Product: " + product);
                   return null;
               }
           );
   
           // Wait for all asynchronous operations to complete
           // (otherwise the main thread may exit before they finish)
           resultFuture.join();
       }
   
       private static void sleep(long millis) {
           try {
               Thread.sleep(millis);
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
           }
       }
   }
   ```

   **25.2-Homework 2: Assume there is an online store that needs to fetch data from three APIs: products,** **reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched** **data for further processing. (需要找public api去模拟，)**

   ​	1)Sign In to Developer.BestBuy.com

   ​	2)Best Buy Developer API Documentation (bestbuyapis.github.io)

   ​	3)可以⽤fake api https://jsonplaceholder.typicode.com/

   ​	4)Github public api: https://api.github.com/users/your-user-name/repos

   ```java
   package com.chuwa.async;
   
   import java.net.URI;
   import java.net.http.HttpClient;
   import java.net.http.HttpRequest;
   import java.net.http.HttpResponse;
   import java.util.*;
   import java.util.concurrent.CompletableFuture;
   
   import com.google.gson.Gson;
   import com.google.gson.reflect.TypeToken;
   
   public class AsyncStoreDemo {
       // Simple DTOs
       static class Product {
           int userId;
           int id;
           String title;
           String body;
           List<Review> reviews;
           boolean inStock;
       }
       static class Review {
           int postId;
           int id;
           String name;
           String email;
           String body;
       }
       static class Todo {
           int userId;
           int id;
           String title;
           boolean completed;
       }
   
       public static void main(String[] args) {
           HttpClient client = HttpClient.newHttpClient();
           Gson gson = new Gson();
   
           // 1. Asynchronously fetch “products” data
           CompletableFuture<List<Product>> productsF = client.sendAsync(
                   HttpRequest.newBuilder(URI.create("https://jsonplaceholder.typicode.com/posts"))
                              .GET()
                              .build(),
                   HttpResponse.BodyHandlers.ofString()
               )
               .thenApply(HttpResponse::body)
               .thenApply(json -> gson.fromJson(json, new TypeToken<List<Product>>(){}.getType()));
   
           // 2. Asynchronously fetch “reviews” data
           CompletableFuture<List<Review>> reviewsF = client.sendAsync(
                   HttpRequest.newBuilder(URI.create("https://jsonplaceholder.typicode.com/comments"))
                              .GET()
                              .build(),
                   HttpResponse.BodyHandlers.ofString()
               )
               .thenApply(HttpResponse::body)
               .thenApply(json -> gson.fromJson(json, new TypeToken<List<Review>>(){}.getType()));
   
           // 3. Asynchronously fetch “inventory” data
           CompletableFuture<List<Todo>> todosF = client.sendAsync(
                   HttpRequest.newBuilder(URI.create("https://jsonplaceholder.typicode.com/todos"))
                              .GET()
                              .build(),
                   HttpResponse.BodyHandlers.ofString()
               )
               .thenApply(HttpResponse::body)
               .thenApply(json -> gson.fromJson(json, new TypeToken<List<Todo>>(){}.getType()));
   
           // 4. When all fetches complete, merge the data
           CompletableFuture<Void> allDone = CompletableFuture.allOf(productsF, reviewsF, todosF)
               .thenAccept(v -> {
                   List<Product> products = productsF.join();
                   List<Review> reviews   = reviewsF.join();
                   List<Todo> todos       = todosF.join();
   
                   // Build a map from postId to list of reviews
                   Map<Integer, List<Review>> reviewsByPost = new HashMap<>();
                   for (Review r : reviews) {
                       reviewsByPost
                         .computeIfAbsent(r.postId, k -> new ArrayList<>())
                         .add(r);
                   }
   
                   // Build a map from id to todo, using completed flag to simulate stock
                   Map<Integer, Todo> todoById = new HashMap<>();
                   for (Todo t : todos) {
                       todoById.put(t.id, t);
                   }
   
                   // Merge into each Product
                   for (Product p : products) {
                       p.reviews = reviewsByPost.getOrDefault(p.id, Collections.emptyList());
                       Todo todo = todoById.get(p.id);
                       // Assume completed == true means sold out, so inStock = !completed
                       p.inStock = (todo == null) ? false : !todo.completed;
                   }
   
                   // Print the first 5 merged results as a demo
                   products.stream().limit(5).forEach(p -> 
                       System.out.printf(
                         "Product %2d: %-30s  reviews=%2d  inStock=%b%n",
                         p.id, p.title, p.reviews.size(), p.inStock
                       )
                   );
               });
   
           // Block until all asynchronous work is done
           allDone.join();
       }
   }
   ```

   **Core Idea：**

   1. **Fire parallel requests**

      Use HttpClient.sendAsync(...) to initiate three HTTP calls concurrently, each returning a CompletableFuture<String> containing the raw JSON response.

   2. **Parse JSON asynchronously**

      Chain .thenApply(...) on each future to deserialize the JSON string into a typed list (List<Product>, List<Review>, List<Todo>) using your JSON library of choice (e.g. Gson).

   3. **Wait for all to complete**

      Use CompletableFuture.allOf(productsF, reviewsF, todosF) to produce a new future that completes only when **all three** fetch-and-parse operations have finished.

   4. **Merge the data**

      In the .thenAccept(...) callback of the allOf future, call .join() on each individual future to safely retrieve its result (or default). Build maps keyed by postId or id, then attach each post’s reviews and inventory status to its corresponding Product object.

   5. **Process or output**

      Finally, with every Product now enriched with its reviews and stock flag, print a sample or hand the list off to downstream processing.

   This approach ensures that no single slow API call will block the others, achieving fully asynchronous, non-blocking data aggregation.

   

   **25.3 -Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API call, return a default value and log the exception information.**

   ```java
   package com.chuwa.async;
   
   import java.net.URI;
   import java.net.http.HttpClient;
   import java.net.http.HttpRequest;
   import java.net.http.HttpResponse;
   import java.util.*;
   import java.util.concurrent.CompletableFuture;
   import com.google.gson.Gson;
   import com.google.gson.reflect.TypeToken;
   
   public class AsyncStoreDemoWithErrorHandling {
       // Simple DTOs(Data Transfer Object)
       static class Product {
           int userId, id;
           String title, body;
           List<Review> reviews;
           boolean inStock;
       }
       static class Review {
           int postId, id;
           String name, email, body;
       }
       static class Todo {
           int userId, id;
           String title;
           boolean completed;
       }
   
       public static void main(String[] args) {
           HttpClient client = HttpClient.newHttpClient();
           Gson gson = new Gson();
   
           // 1. Fetch products asynchronously
           CompletableFuture<List<Product>> productsF = client.sendAsync(
                   HttpRequest.newBuilder(URI.create("https://jsonplaceholder.typicode.com/posts"))
                              .GET().build(),
                   HttpResponse.BodyHandlers.ofString()
               )
               .thenApply(HttpResponse::body)
               .thenApply(json -> gson.fromJson(json, new TypeToken<List<Product>>(){}.getType()))
               .exceptionally(ex -> {
                   System.err.println("Error fetching or parsing products: " + ex.getMessage());
                   ex.printStackTrace();
                   return Collections.emptyList();  // default safe value
               });
   
           // 2. Fetch reviews asynchronously
           CompletableFuture<List<Review>> reviewsF = client.sendAsync(
                   HttpRequest.newBuilder(URI.create("https://jsonplaceholder.typicode.com/comments"))
                              .GET().build(),
                   HttpResponse.BodyHandlers.ofString()
               )
               .thenApply(HttpResponse::body)
               .thenApply(json -> gson.fromJson(json, new TypeToken<List<Review>>(){}.getType()))
               .exceptionally(ex -> {
                   System.err.println("Error fetching or parsing reviews: " + ex.getMessage());
                   ex.printStackTrace();
                   return Collections.emptyList();
               });
   
           // 3. Fetch inventory (todos) asynchronously
           CompletableFuture<List<Todo>> todosF = client.sendAsync(
                   HttpRequest.newBuilder(URI.create("https://jsonplaceholder.typicode.com/todos"))
                              .GET().build(),
                   HttpResponse.BodyHandlers.ofString()
               )
               .thenApply(HttpResponse::body)
               .thenApply(json -> gson.fromJson(json, new TypeToken<List<Todo>>(){}.getType()))
               .exceptionally(ex -> {
                   System.err.println("Error fetching or parsing todos: " + ex.getMessage());
                   ex.printStackTrace();
                   return Collections.emptyList();
               });
   
           // 4. When all futures complete, merge the data
           CompletableFuture<Void> allDone = CompletableFuture.allOf(productsF, reviewsF, todosF)
               .thenAccept(v -> {
                   List<Product> products = productsF.join();
                   List<Review>   reviews  = reviewsF.join();
                   List<Todo>     todos    = todosF.join();
   
                   // Map postId -> list of reviews
                   Map<Integer, List<Review>> reviewsByPost = new HashMap<>();
                   for (Review r : reviews) {
                       reviewsByPost
                         .computeIfAbsent(r.postId, k -> new ArrayList<>())
                         .add(r);
                   }
   
                   // Map id -> todo (use completed flag to simulate out-of-stock)
                   Map<Integer, Todo> todoById = new HashMap<>();
                   for (Todo t : todos) {
                       todoById.put(t.id, t);
                   }
   
                   // Attach reviews and stock status to each product
                   for (Product p : products) {
                       p.reviews = reviewsByPost.getOrDefault(p.id, Collections.emptyList());
                       Todo t = todoById.get(p.id);
                       p.inStock = (t == null) ? false : !t.completed;
                   }
   
                   // Print first 5 merged results as a demo
                   products.stream().limit(5).forEach(p ->
                       System.out.printf(
                         "Product %-3d: %-30s  reviews=%2d  inStock=%b%n",
                         p.id, p.title, p.reviews.size(), p.inStock
                       )
                   );
               });
   
           // Block until everything is done
           allDone.join();
       }
   }
   ```

   **Key points:**

   1. Each API call uses sendAsync(...) to run in parallel on the HttpClient’s default thread pool.
   2. We chain .thenApply(...) to parse the JSON response into a list of DTOs.
   3. We attach .exceptionally(...) to each future so that any exception—network error, HTTP error, or JSON parse error—is caught, logged to stderr, and returns an empty list as a safe fallback.
   4. CompletableFuture.allOf(...) waits for all three to complete, then join() on each to get their (possibly defaulted) results.
   5. Finally, we merge reviews into products by matching postId → product.id and use the todo’s completed flag to simulate stock, printing out a sample of the merged data.

   
