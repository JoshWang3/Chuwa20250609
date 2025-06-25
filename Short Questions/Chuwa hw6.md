# Chuwa hw6

## Question 1

```java
public class Singleton {

    private static volatile Singleton instance;

    private Singleton() {

    }

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

## Question 2

```java
new Thread(() -> System.out.println("Running")).start();
ExecutorService executor = Executors.newFixedThreadPool(2);
executor.submit(() -> System.out.println("In thread pool"));
executor.shutdown();
```

## Question 3

| Feature | Runnable | Callable |
| --- | --- | --- |
| Return | void | V (generic type) |
| Exception | Cannot throw checked | Can throw checked |
| Method | `run()` | `call()` |

## Question 4

- `start()`: Launches a new thread.
- `run()`: Runs in current thread, no multithreading.

## Question 5

**Runnable** is preferred (separates task from thread management; more flexible with thread pools).

## Question 6

`NEW`, `RUNNABLE`, `BLOCKED`, `WAITING`, `TIMED_WAITING`, `TERMINATED`

## Question 7

Two threads hold one lock each, and they waiting for the other one to release

```java
// Example
synchronized(lock1) {
    synchronized(lock2) { }
}

```

Solution

- Always acquire locks in a fixed global order.
- Use `tryLock()` from `ReentrantLock` with timeout.

## Question 8

Use `wait()`, `notify()`, `notifyAll()` on shared object inside synchronized block.

## Question 9

- **Object Lock**: `synchronized(this)` or instance method.
- **Class Lock**: `synchronized(ClassName.class)` or `static synchronized` method.

## Question 10

Causes current thread to wait until the joined thread finishes.

## Question 11

Suggests that current thread is willing to pause to let others run. It's a hint, not a guarantee.

## Question 12

- A pool of reusable threads.
- Types: FixedThreadPool, CachedThreadPool, ScheduledThreadPool, SingleThreadExecutor
- TaskQueue: Queue holding submitted tasks awaiting execution.

## Question 13

- Library: `java.util.concurrent`
- Interface: `Executor`, `ExecutorService`

## Question 14

```java
executor.submit(() -> {
    // task
});

```

## Question 15

Reuses threads to avoid overhead of frequent thread creation. And easy to manage the threads. Easy to maintain the threads.

## Question 16

- `shutdown()`: Graceful shutdown, lets running tasks complete.
- `shutdownNow()`: Attempts to stop all running tasks immediately.

## Question 17

- For lock-free thread-safe operations.
- Types: `AtomicInteger`, `AtomicLong`, `AtomicBoolean`, etc.

```java
AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet();
```

use when multiple threads update shared numeric values

## Question 18

Thread-safe alternatives:

- `ConcurrentHashMap`
- `CopyOnWriteArrayList`
- `ConcurrentLinkedQueue`
- `BlockingQueue`

## Question 19

| Lock Type | Advantage |
| --- | --- |
| `synchronized` | Simple, built-in |
| `ReentrantLock` | More control (fairness, interruptible) |
| `ReadWriteLock` | Allows concurrent reads |
| `StampedLock` | Optimistic locking |

## Question 20

- `Future`: Represents result of async task, blocks on `get()`
- `CompletableFuture`: More powerful, non-blocking, support chaining.

```java
CompletableFuture.supplyAsync(() -> 5)
    .thenApply(x -> x * 2)
    .thenAccept(System.out::println);

```

## Question 21

I already type it and understand

## Question 22

```java
public class OddEvenPrinter {

    public static void main(String[] args) {
        Printer printer = new Printer();

        Thread oddThread = new Thread(() -> {
            try {
                printer.printOdd();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread evenThread = new Thread(() -> {
            try {
                printer.printEven();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        oddThread.start();
        evenThread.start();
    }
}

class Printer {
    private int number = 1;
    private final int MAX = 10;

    public synchronized void printOdd() throws InterruptedException {
        while (number <= MAX) {
            if (number % 2 == 0) {
                wait();
            } else {
                System.out.println("Odd Thread: " + number);
                number++;
                notify();
            }
        }
    }

    public synchronized void printEven() throws InterruptedException {
        while (number <= MAX) {
            if (number % 2 != 0) {
                wait();
            } else {
                System.out.println("Even Thread: " + number);
                number++;
                notify();
            }
        }
    }
}

```

```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinterWithLock {

    public static void main(String[] args) {
        Printer printer = new Printer();

        Thread oddThread = new Thread(() -> {
            try {
                printer.printOdd();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread evenThread = new Thread(() -> {
            try {
                printer.printEven();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        oddThread.start();
        evenThread.start();
    }
}

class Printer {
    private int number = 1;
    private static final int MAX = 10;

    private final Lock lock = new ReentrantLock();
    private final Condition oddTurn = lock.newCondition();
    private final Condition evenTurn = lock.newCondition();

    public void printOdd() throws InterruptedException {
        lock.lock();
        try {
            while (number <= MAX) {
                while (number % 2 == 0) {
                    oddTurn.await();
                }
                // It's odd's turn
                System.out.println("Odd Thread: " + number);
                number++;
                evenTurn.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void printEven() throws InterruptedException {
        lock.lock();
        try {
            while (number <= MAX) {
                while (number % 2 != 0) {
                    evenTurn.await();
                }
                // It's even's turn
                System.out.println("Even Thread: " + number);
                number++;
                oddTurn.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}

```

## Question 23

```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderedNumberPrinter {

    public static void main(String[] args) {
        OrderedPrinter printer = new OrderedPrinter();

        Thread t1 = new Thread(() -> {
            try {
                printer.printRange(1, 10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            try {
                printer.printRange(11, 20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Thread-2");

        Thread t3 = new Thread(() -> {
            try {
                printer.printRange(21, 22);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class OrderedPrinter {
    private int current = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void printRange(int start, int end) throws InterruptedException {
        for (int i = start; i <= end; i++) {
            lock.lock();
            try {
                while (i != current) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                current++;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}

```

## Question 24

homework1

```java
import java.util.concurrent.CompletableFuture;

public class Homework1 {
    public static void main(String[] args) {
        int a = 5, b = 3;

        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> a + b);
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> a * b);

        sumFuture.thenAccept(result -> System.out.println("Sum: " + result));
        productFuture.thenAccept(result -> System.out.println("Product: " + result));
    }
}

```

homework 2

```java
import java.net.URI;
import java.net.http.*;
import java.util.concurrent.CompletableFuture;

public class Homework2 {
    static HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        CompletableFuture<String> products = fetch("https://jsonplaceholder.typicode.com/posts/1");
        CompletableFuture<String> reviews = fetch("https://jsonplaceholder.typicode.com/comments/1");
        CompletableFuture<String> inventory = fetch("https://jsonplaceholder.typicode.com/todos/1");

        CompletableFuture<Void> combined = CompletableFuture.allOf(products, reviews, inventory);

        combined.thenRun(() -> {
            try {
                System.out.println("Products: " + products.get());
                System.out.println("Reviews: " + reviews.get());
                System.out.println("Inventory: " + inventory.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static CompletableFuture<String> fetch(String url) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                     .thenApply(HttpResponse::body);
    }
}

```

homework 3

```java
import java.net.URI;
import java.net.http.*;
import java.util.concurrent.CompletableFuture;

public class Homework3 {
    static HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        CompletableFuture<String> products = fetchSafe("https://jsonplaceholder.typicode.com/posts/1", "Default Product");
        CompletableFuture<String> reviews = fetchSafe("https://jsonplaceholder.typicode.com/comments/1", "Default Review");
        CompletableFuture<String> inventory = fetchSafe("https://jsonplaceholder.typicode.com/todos/1", "Default Inventory");

        CompletableFuture<Void> combined = CompletableFuture.allOf(products, reviews, inventory);

        combined.thenRun(() -> {
            try {
                System.out.println("Products: " + products.get());
                System.out.println("Reviews: " + reviews.get());
                System.out.println("Inventory: " + inventory.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static CompletableFuture<String> fetchSafe(String url, String defaultValue) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                     .thenApply(HttpResponse::body)
                     .exceptionally(ex -> {
                         System.err.println("Error calling " + url + ": " + ex.getMessage());
                         return defaultValue;
                     });
    }
}

```