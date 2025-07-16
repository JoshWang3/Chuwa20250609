# HW6: Multi-Threading
@ Jun 24, 2025 _Gloria Wang_

## 2. Write a thread-safe singleton class
> Code can be checked in `ThreadSafeSingleton` package
#### BurgerMaker 🍔
-> Thread-safe Singleton
```Java
package ThreadSafeSingleton;

public class BurgerMaker {

    // 1. volatile ensures visibility and prevents instruction reordering
    private static volatile BurgerMaker instance;


    // 2. private constructor to prevent instantiation from outside
    private BurgerMaker(){
        System.out.println("BurgerMaker instance created.");
    }

    // 3. static synchronized getInstance method
    public static BurgerMaker getInstance(){
        // 4. make sure thread safe
        if (instance == null) { // first check (no locking)
            synchronized (BurgerMaker.class) { // lock class obj
                if (instance == null) { // second check (with lock)
                    instance = new BurgerMaker(); // create instance
                }
            }
        }
        return instance;
    }

    public void makeBurger(Burger burger) {
        System.out.println(Thread.currentThread().getName() + " made: " + burger);
    }
}
```

#### BurgerMain
```Java
package ThreadSafeSingleton;

public class BurgerMain {
    public static void main(String[] args) {
        Runnable task = () -> {
            BurgerMaker maker = BurgerMaker.getInstance();
            Burger burger = new Burger("Beef", true);
            maker.makeBurger(burger);
        };

        Thread t1 = new Thread(task, "Thread A");
        Thread t2 = new Thread(task, "Thread B");
        Thread t3 = new Thread(task, "Thread C");

        t1.start();
        t2.start();
        t3.start();
    }
}

/*
output: 
BurgerMaker instance created.
Thread B made: Cheese Beef Burger
Thread C made: Cheese Beef Burger
Thread A made: Cheese Beef Burger

-> as we can see: 
- Only one time BurgerMaker instance created. — confirms that only a single instance was created, despite multiple threads accessing it concurrently.
- Each thread successfully used the same instance to make a burger
 */
```
## 3. How to create a new thread(Please also consider Thread Pool approach)?
> Code can be checked in `ThreadCreation` package

### 1. Extending Thread Class
```Java
package ThreadCreation;

class BurgerThread extends Thread{
    @Override
    public void run(){
        System.out.println(("Making bruger usin Thread class - " + Thread.currentThread().getName()));
    }
}

public class ExtendingThread {
    public static void main(String[] args) {
        Thread t = new BurgerThread();
        t.start();
    }
}

// output: 
// Making bruger usin Thread class - Thread-0
```

### 2. Implementing Runnable Interface
```Java
package ThreadCreation;

class BurgerRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Making burger using Runnable - " + Thread.currentThread().getName());
    }
}

public class RunnableInterface {
    public static void main(String[] args) {
        Thread t = new Thread(new BurgerRunnable());
        t.start();
    }
}

// output: 
// Making burger using Runnable - Thread-0
```

### 3. Implementing Callable Interface
```Java
package ThreadCreation;

import java.util.concurrent.*;

class BurgerCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000); // simulate work
        return "Burger made by Callable - " + Thread.currentThread().getName();
    }
}

public class CallableInterface {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new BurgerCallable());

        System.out.println("Waiting for burger...");

        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}

// output:
// Waiting for burger...
// Burger made by Callable - pool-1-thread-1
```

### Thread Pool 🏊
```Java
package ThreadCreation;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        Runnable task = () -> {
            System.out.println("Burger made in thread pool - " + Thread.currentThread().getName());
        };

        pool.submit(task);
        pool.submit(task);
        pool.submit(task);

        pool.shutdown();
    }
}

/*
output:
Burger made in thread pool - pool-1-thread-1
Burger made in thread pool - pool-1-thread-3
Burger made in thread pool - pool-1-thread-2
 */
 ```

## 4. Difference between Runnable and Callable?

| Feature                  | Runnable                    | Callable<V>                        |
|--------------------------|-----------------------------|------------------------------------|
| Return value             | ❌ No return value           | ✅ Can return a value (`V call()`)  |
| Throws checked exceptions| ❌                           | ✅                                  |
| Method to override       | `public void run()`         | `public V call() throws Exception` |
| Used with                | `Thread`, `ExecutorService` | `ExecutorService.submit()` only    |
| Result retrieval         | ❌ Not possible directly     | ✅ Uses `Future<V>` to get result   |
| Functional interface     | ✅ Yes                       | ✅ Yes                              |

## 5. What is the difference between `t.start()` and `t.run()`?

| Feature                   | `t.start()`                                    | `t.run()`                                      |
|---------------------------|------------------------------------------------|------------------------------------------------|
| Starts a new thread?      | ✅ Yes – creates a **new thread**              | ❌ No – runs in **current thread**             |
| Executes asynchronously   | ✅ Yes – runs `run()` in a new thread          | ❌ No – just a normal method call              |
| Managed by JVM?           | ✅ Yes – JVM schedules thread execution        | ❌ No – behaves like a regular method call     |
| Execution context         | Runs in a **new thread**                       | Runs in the **main thread**                   |
| Typical use case          | ✅ Real multithreading                         | ❌ Often accidental misuse or testing only     |

## 6. Which way of creating threads is better: Thread class or Runnable interface?
### Runnable interface is better 🩵

|                     | Thread Class                      | Runnable Interface                                   |
|---------------------|-----------------------------------|------------------------------------------------------|
| Inheritance         | ❌ No extend                       | ✅                                                    |
| Code Separation     | ❌ Mix thread logic and task logic | ✅ Separates task (`Runnable`) from thread (`Thread`) |
| Thread Pool Support | ❌ Not directly                    | ✅ Runnable easily submitted to pools                 |

## 7. What are the thread statuses?
| State           | Description                                                                                                                                                                                                                                                  |
|-----------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `NEW`           | Thread obj is created but `start()` hasn't been called yet                                                                                                                                                                                                   |
| `RUNNABLE`      | Thread in the **`RUNNABLE`** state is considered **"ready"** to run but hasn't been selected by the CPU yet. When the **thread scheduler** selects it and assigns a **CPU time slice**, the thread transitions from **`RUNNABLE` (ready)** to **`RUNNING`**. |
| `BLOCKED`       | Thread is blocked, waiting to acquire a lock held by another thread                                                                                                                                                                                          |  
| `WAITING`       | Thread is waiting indefinitely for another thread to perform a specific action.                                                                                                                                                                              |
| `TIMED_WAITING` | Thread is waiting for a specific time (e.g., `sleep()`, `join(timeout)`, `wait(timeout)`).                                                                                                                                                                   |
| `TERMINATED`    | Thread has finished execution / was aborted                                                                                                                                                                                                                  |

## 8. Demonstrate deadlock and how to resolve it in Java code.
> Code can be checked in `DeadLock` package
### DeadLock Example: ⚠️
```Java
package DeadLock;

public class KitchenDeadLock {

    private static final Object BUN = new Object();
    private static final Object PATTY = new Object();

    public static void main(String[] args) {
        Thread spongebob = new Thread(() -> {
            synchronized (BUN) {
                System.out.println("SpongeBob got the bun, waiting for patty...");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
                synchronized (PATTY) {
                    System.out.println("SpongeBob assembled the Krabby Patty :)");
                }
            }
        });

        Thread squidward = new Thread(() -> {
            synchronized (PATTY) {
                System.out.println("Squidward got the patty, waiting for bun...");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
                synchronized (BUN) {
                    System.out.println("Squidward assembled the Krabby Patty :)");
                }
            }
        });

        spongebob.start();
        squidward.start();

    }

}

/*
output: 🥶 freeze here 🧊
SpongeBob got the bun, waiting for patty...
Squidward got the patty, waiting for bun...
 */
```
### Fixed DeadLock 🔧
```Java
package DeadLock;

public class DeadLockFixed {

    private static final Object BUN = new Object();
    private static final Object PATTY = new Object();

    public static void main(String[] args) {
        Thread spongebob = new Thread(() -> {
            synchronized (BUN) {
                System.out.println("SpongeBob got the bun");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
                synchronized (PATTY) {
                    System.out.println("SpongeBob assembled the Krabby Patty :)");
                }
            }
        });

        Thread squidward = new Thread(() -> {
            synchronized (BUN) { // Squidward now locks BUN first too
                System.out.println("Squidward got the bun");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
                synchronized (PATTY) {
                    System.out.println("Squidward assembled the Krabby Patty :)");
                }
            }
        });

        spongebob.start();
        squidward.start();

    }

}

/*
output: ✌️
SpongeBob got the bun
SpongeBob assembled the Krabby Patty :)
Squidward got the bun
Squidward assembled the Krabby Patty :)
 */
```

### How to Prevent Deadlock
- Consistent Lock Order: Both SpongeBob and Squidward grab **bun → patty**                   

## 9. How do threads communicate each other?
### Synchronized: wait & notify & notifyAll
- Java threads share memory, but to avoid race conditions, they often need to communicate safely
- -> then we will use `wait(), notify(),` and `notifyAll()` 
- these are used with synchronized blocks and shared lock (monitor)
> Code can be checked in `ThreadCommunication` package

```Java
package ThreadCommunication;

public class WaitNotify extends Thread {

  private static final Object GRILL = new Object(); // shared monitor grill
  private static int pattyNumber = 1; // shared counter

  public static void main(String[] args) {
    PattyFlipper flipper = new PattyFlipper();
    new Thread(flipper, "SpongeBob").start();
    new Thread(flipper, "Squidward").start();
  }

  static class PattyFlipper implements Runnable {
    @Override
    public void run() {
      synchronized (GRILL) {
        while (pattyNumber <= 10) {
          System.out.println(Thread.currentThread().getName() + " flips patty #" + pattyNumber++);
          GRILL.notifyAll(); // wake the other thread
          try {
            if (pattyNumber <= 10) {
              GRILL.wait(); // wait for the other thread to take its turn
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
}
/*
output:
SpongeBob flips patty #1
Squidward flips patty #2
SpongeBob flips patty #3
Squidward flips patty #4
SpongeBob flips patty #5
Squidward flips patty #6
SpongeBob flips patty #7
Squidward flips patty #8
SpongeBob flips patty #9
Squidward flips patty #10       
 */
```
| Role         | Thread Name  | Behavior                                               |
|--------------|--------------|--------------------------------------------------------|
| Monitor      | `GRILL`      | Used for `synchronized`, `wait()`, and `notifyAll()`   |
| Shared State | `pattyNumber`| Tracks how many patties have been flipped              |
| SpongeBob 🧽 | Thread A     | Flips odd-numbered patties  🍔                         |
| Squidward 🐙 | Thread B     | Flips even-numbered patties 🍔                         |

### ReentrantLock
- `condition.await()` -> make the current thread wait (like `wait()`)
- `condition.singnal()` -> wake up one waiting thread (like `notify()`)
- `condition.signalAll()` -> wake up all waiting threads (like `notifyAll()`)
These must be used within a `lock.lock()` / `try-finally` block.

```Java
package ThreadCommunication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BurgerShopWithReentrantLock {

    private static final Lock lock = new ReentrantLock();
    private static final Condition burgerReady = lock.newCondition();
    private static boolean isBurgerReady = false;

    public static void main(String[] args) {
        Thread spongeBob = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("SpongeBob is making burger...");
                Thread.sleep(1000); // making time
                isBurgerReady = true;
                System.out.println("SpongeBob: Burger is ready :)");
                burgerReady.signal(); // wake up customer
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread customer = new Thread(() -> {
            lock.lock();
            try {
                while (!isBurgerReady) {
                    System.out.println("Customer is waiting for burger...");
                    burgerReady.await();
                }
                System.out.println("Customer: Where is my burger?! :(");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        customer.start();
        spongeBob.start();
    }
}

/*
output: 
Customer is waiting for burger...
SpongeBob is making burger...
SpongeBob: Burger is ready :)
Customer: Where is my burger?! :(
 */
 ```

## 10. What’s the difference between class lock and object lock?
### Class Lock
- locks Class object itself, not an instance
- applied via:
  - `synchronized(CLasssName.class)`
  - `static synchronized` 
- ensures only one thread can access synchronized static methods / blocks for the whole class
```Java
public static synchronized void staticMethod() {
    // synchronized on ClassName.class
}
```
### Object Lock
- locks a specific instance of a class
- applied via: 
  - `synchronized(this)`
  - `synchronized(instance)`
  - `synchronized` instance methods
- ensures only one thread can access synchronized static methods / blocks on the same object
```Java
public synchronized void instanceMethod() {
    // synchronized on 'this'
}
```

### Summary

| Aspect               | Object Lock                              | Class Lock                                 |
|----------------------|-------------------------------------------|---------------------------------------------|
| Lock scope           | One specific object instance              | Entire class (shared across all instances)  |
| Applied on           | `synchronized(this)` or instance method   | `synchronized(ClassName.class)` or static method |
| Affects              | Threads accessing same object instance    | Threads accessing any static member of class |
| Use case             | Protect instance-level data               | Protect static/shared class-level data       |
| Multiple instances   | Do **not** block each other               | All instances share the same lock            |

## 11. What is `join()` method?
- Main thread will wait for thread spongebob completes its task
- Main thread is `BLOCKED` until thread spongebob finishes
- Main thread will continue after spongebob finished its task

> Code can be checked in `Join` package

```Java
package Join;

public class JoinDemo {
    public static void main(String[] args) {
        Thread spongebob = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("SpongeBob finished cooking :)");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Main starts");
        spongebob.start();

        try {
            spongebob.join(); // wait for thread spongebob finished cooking
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main resumes after SpongeBob finished");
    }
}


/*
output: 
Main starts
SpongeBob finished cooking :)
Main resumes after SpongeBob finished
 */

```

## 12. What is `yield()` method?
- static method -> **_suggests_** to the CPU scheduler that the current thread is willing to pause and let others run
- the thread that yields may be scheduled again immediately if no other equal priority threads are waiting
> Code can be checked in `Yield` package

```Java
package Yield;

public class YieldDemo {
    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " running: " + i);
                Thread.yield();
            }
        };

        Thread t1 = new Thread(task, "SpongeBob");
        Thread t2 = new Thread(task, "Squidward");

        t1.start();
        t2.start();
    }
}

/*
output:
Squidward running: 0
Squidward running: 1
Squidward running: 2
SpongeBob running: 0
Squidward running: 3
SpongeBob running: 1
Squidward running: 4
Squidward running: 5
SpongeBob running: 2
SpongeBob running: 3
SpongeBob running: 4
SpongeBob running: 5
 */
 ```
- `Thread.yield()` was called, but not enforced — Squidward continued running after yielding
- SpongeBob caught up only after Squidward reached his count of 5
- Eventually, both threads completed their loops, but not in a strictly alternating pattern

## 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
### What is ThreadPool? 🏊
A ThreadPool is a pool of worker threads that can be reused to execute multiple tasks
- Threads are created ahead of time and reused -> improve performance
- Helps avoid the overhead of creating and destroying threads repeatedly
- Threads in the pool pull tasks from a shared task queue and execute them

### How many types of ThreadPool? 
4 build-in types

```Java
Executors.newFixedThreadPool(int n);       // A pool with a fixed number of threads 
Executors.newCachedTnreadPool();           // Creates new threads as needed and reuses idle ones 
Executors.newSingleThreadExecutor();       // Executes tasks one-by-one using a single thread
Executors.newSchefuledThreadPool(int n);   // Supports delayed and periodic task execution 
```
### What is the TaskQueue?
TaskQueue is a blocking queue used to temporarily hold tasks submitted to the thread pool while all threads are busy
- enable smooth task handoff btwn producers and worker threads
- common queue types:
  - `LinkedBlockingQueue` - used in `FixedThreadPool`
  - `SynchronousQueue` - used in `CachedThreadPool`
  - `DelayQueue` - used in `SchedulesThreadPool`

## 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
### Which Library is used to create ThreadPool?
-  `java.util.concurrent`
```Java
import java.util.concurrent.*;
```
### Which Interface provide main functions of thread-pool?
- `ExecutorService` -> provides lifecycle management and task submission for thread pools
- e.g.
  - `submit()`
  - `shuDown()`
  - `invokeAll()`
  - `invokeAny()`

## 15. How to submit a task to ThreadPool?
- using `submit()` / `execute()`
- `submit()` used for Runnable / Callable, return type: `Future<?>`
- `execute()` used for Runnable only, return type: `void` -> no result return

```Java
import java.util.concurrent.Executors;

ExecutorService pool = Executors.newFixedThreadPool(2);

Runnable task = () -> {
    System.out.println("Burger task running in " + Thread.currentThread().getName());
};

pool.submit(task); // submit task to thread pool
pool.shutdown(); // shutdown the pool after submission
```
## 16. What is the advantage of ThreadPool?
### Better Performance
- Reuses existing threads instead of creating new oes each time
- Reduces overhead of thread creation and destruction
### Efficient Resource Management
- Limits the number of concurrent threads -> avoids exhausting system resources
### Improved Scalability
- Handles large number of tasks efficiently using a limited pool of threads
### Task Management with Queue
- Uses a task queue to manage pending tasks while threads are busy
### Simplified Thread Lifecycle
- Thread creation, scheduling, and terminate are manager by the `ExecutorService`

## 17. Difference between shutdown() and shutdownNow() methods of executor
### `shutDown()`
- Initiated an orderly shutdown:
  - no new tasks are accepted
  - already submitted tasks continue and complete
### `shutdownNow()`
- Attempts to stop all actively executing tasks immediately:
  - returns a list of tasks that were waiting in the queue
  - tries to interrupt running threads using `Thread.interrupt()`
- ⚠️ `shutdownNow()` doesn’t guarantee all threads stop instantly
  - it depends on whether they handle `InterruptedException`

| Aspect                | shutdown()                                  | shutdownNow()                                 |
|------------------------|---------------------------------------------|------------------------------------------------|
| Accepts new tasks      | ❌ No                                       | ❌ No                                          |
| Executes queued tasks  | ✅ Yes                                      | ❌ No – returns queued tasks                   |
| Interrupts running tasks | ❌ No                                     | ✅ Yes – attempts to interrupt                 |
| Task completion        | Allows running tasks to finish              | Abruptly tries to stop all tasks               |
| Return value           | void                                        | List of unexecuted tasks                      |
| Safety level           | Safer, preferred shutdown method            | More aggressive, may cause inconsistencies     |

## 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
### What is Atomic classes?
`Atomic classes` belongs to `java.util.concurrent.atomic` package
- provide a way to perform thread-safe operations on single variables (`int, long, boolean, references`) without using synchronization (locks)
- use **low level atomic CPU instruction**, like CAS to ensure data integrity in multi threaded environments

### How many types of Atomic classes?

#### Primitive Types
```Java
AtomicInteger
AtomicBoolean
AtomicLong
```
#### Array types
```Java
AtomicIntegerArray
AtomicReferenceArray
AtomicLongArray
```

#### Reference types
```java
AtomicReference<T>
AtomicStampedReference<T>
AtomicMarkableReference<T>
```
### Give me some code example of Atomic classes and its main methods
> Code can be checked in `Atomic` package

#### AtomicInteger Example
```java
package Atomic;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);

        System.out.println("Initial: " + counter.get());

        counter.incrementAndGet(); // ++
        System.out.println("Increment: " + counter.get());

        counter.addAndGet(10); // +10
        System.out.println("Add and Get: " + counter.get());

        boolean updated = counter.compareAndSet(11, 20); // if 10 -> update to 20
        System.out.println("Compare and set (11 -> 20): " + updated);
        System.out.println("Final value: " + counter.get());
    }
}

/*
output:
Initial: 0
Increment: 1
Add and Get: 11
Compare and set (11 -> 20): true
Final value: 20
 */
```
#### AtomicBoolean Example
```Java
package Atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanDemo {
    public static void main(String[] args) {
        AtomicBoolean shopOpen = new AtomicBoolean(false);

        System.out.println("Shop initially open? " + shopOpen.get());

        // open the shop
        shopOpen.set(true);
        System.out.println("Burger shop is now open? " + shopOpen.get());

        // close shop if currently open
        boolean closed = shopOpen.compareAndSet(true, false);
        System.out.println("Successfully close burger shop: " + closed);

        System.out.println("Burger shop still open? " + shopOpen.get());
    }
}

/*
output:
Shop initially open? false
Burger shop is now open? true
Successfully close burger shop: true
Burger shop still open? false
 */
 ```

#### AtomicReference Example
```Java
package Atomic;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    public static void main(String[] args) {

        // set the initial special burger of the day
        AtomicReference<String> specialBurger = new AtomicReference<>("Black Truffle");

        System.out.println("Initial special burger of the day: " + specialBurger.get());

        // update the special to bbq burger
        specialBurger.set("BBQ Chicken");
        System.out.println("Updated special burger of the day: " + specialBurger.get());

        // change from BBQ chicken to Vegan only it's currently bbq chicken
        boolean updated = specialBurger.compareAndSet("BBQ Chicken", "Vegan");
        System.out.println("Changed BBQ Chicken -> Vegan? " + updated);

        System.out.println("Current special burger of the day: " + specialBurger.get());
    }
}

/*
output: 
Initial special burger of the day: Black Truffle
Updated special burger of the day: BBQ Chicken
Changed BBQ Chicken -> Vegan? true
Current special burger of the day: Vegan
 */
```

### When to use Atomic?
- need to safely update a single variable
- want faster performance than locks (synchronized)
- doing simple counters, flags, or reference

## 19.  What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
Concurrent collections are thread-safe versions of standard java collections designed for safe and efficient access by multiple threads simultaneously
- they use internal synchronization, lock splitting, or non-blocking algorithms, like CAS to ensure high performance in concurrent environments

### Concurrent Data Structure (Thread Safe)
| Interface | Class Name                     | Thread Safety Mechanism                  |
|-----------|--------------------------------|------------------------------------------|
| Map       | ConcurrentHashMap              | Lock splitting / CAS                     |
| Map       | ConcurrentSkipListMap          | Non-blocking skip list                   |
| Queue     | ConcurrentLinkedQueue          | Lock-free queue                          |
| Queue     | LinkedBlockingQueue            | ReentrantLock + condition variables      |
| Queue     | PriorityBlockingQueue          | Internal locking                         |
| Queue     | DelayQueue                     | Delayed tasks with blocking              |
| List      | CopyOnWriteArrayList           | Copies array on each write               |
| Set       | CopyOnWriteArraySet            | Backed by CopyOnWriteArrayList           |
| Set       | ConcurrentSkipListSet          | Concurrent and sorted                    |

## 20. What kind of locks do you know? What is the advantage of each lock?
> Code can be checked in `Locks` package

### Synchronized
- Build in locking in java
- Auto acquires and releases the lock 🔒

**Advantages:**
- simple to use
- auto release after code block / methods exists

### ReentrantLock 🔒
- flexible lock implementation -> behaved similarly to the build in synchronized, but provides more capabilities

Key Features:
- explicit locking (`lock() / unlock()`)
- a thread can acquire the same lock multiple times
- must be released in finally block to avoid deadlocks if exceptions occur
- use `tryLock()` for non-blocking attempt, and `lockInterruption()` to interruptible wait

**Advantages:**
- More flexible than synchronized
- support `tryLock()` and `lockInterruptibly()`
- can creats fair locks

```Java
package Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockBurger {
    private int burgerCount = 0;
    private final Lock lock = new ReentrantLock();

    public void addBurger() {
        lock.lock();
        try {
            burgerCount++;
            System.out.println(Thread.currentThread().getName() + " added a burger. Total is: " + burgerCount);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockBurger counter = new ReentrantLockBurger();
        Runnable task = counter::addBurger;

        for (int i = 0; i < 5; i++) {
            new Thread(task, "SpongeBob-" + i).start();
        }
    }
}

/*
output: 
SpongeBob-0 added a burger. Total is: 1
SpongeBob-1 added a burger. Total is: 2
SpongeBob-2 added a burger. Total is: 3
SpongeBob-3 added a burger. Total is: 4
SpongeBob-4 added a burger. Total is: 5
 */
```

### ReadWriteLock
- allows multiple threads to **read** concurrently, but **only one thread can write**, and no other threads can read / write while a thread is writing ✏️

Key Features:
- read and write locks are separate (`readLock() and writeLock()`).
- improves performance by allowing concurrent reads.
- best for: Scenarios with **many reads and few writes**

**Advantages:**
- high performance for read heavy operations
- allow multiple concurrent readers
- blocks reads during writes to ensure consistency

```Java
package Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockBurger {
    private String menu = "Beef Burger, Chicken Burger";
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    public void readMenu() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reads menu:  " + menu);
        } finally {
            readLock.unlock();
        }
    }


    public void updateMenu(String newMenu) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " updating menu...  ");
            menu = newMenu;
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockBurger menu = new ReadWriteLockBurger();
        Runnable reader = menu::readMenu;
        Runnable writer = () -> menu.updateMenu("Fish Burger, Vegan Burger");

        for (int i = 0; i < 2; i++) {
            new Thread(reader, "Customer-").start();
        }
        new Thread(writer, "SpongeBob-").start();

        try {
            Thread.sleep(1000); // waiting for updating menu
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 2; i < 4; i++) {
            new Thread(reader, "Customer-").start();
        }
    }
}

/*
output:
Customer- reads menu:  Beef Burger, Chicken Burger
Customer- reads menu:  Beef Burger, Chicken Burger
SpongeBob- updating menu...  
Customer- reads menu:  Fish Burger, Vegan Burger
Customer- reads menu:  Fish Burger, Vegan Burger
 */
 ```

### StampedLock
- supports optimistic read, pessimistic read, and exclusive write

Key Features:
- `tryOptimisticRead()` allows reading without blocking
- must call `validate(`) to check if data was modified during optimistic read
- if not valid, fallback to pessimistic `readLock()`

**Advantages:**
- allow optimistic reads fpr better throughput
- falls back to pessimistic read if data changes
- improve performance when writes are rare

## 21. What is future and completableFuture? List some main methods of CompletableFuture
### What is Future?  🤔
`Future<T>` is an interface to represent the **result of an asynchronous computation**
- It allows to: 
  - **submit a task**
  - **retrieve the result**
  - **cancel a task**
  - **check** if a task is complete

#### Common Methods for Future
| Method               | Description                                         |
|----------------------|-----------------------------------------------------|
| `get()`              | waits and returns the result (blocking)             |
| `get(timeout, unit)` | waits up to timeout, else throws `TimeourException` |
| `cancel(boolean)`    | attempts to cancel execution                        |
| `isDone()`           | returns true if task is complete                    |
| `isCancelled()`       | returns true is task was cancelled                  |


### What is CompletableFuture? 🤔
`CompletableFuture<T>` is more powerful 💪 subclass of Future, it offering:
- asynchronous + non-blocking programming
- functional-style chaining
- execption handling
- combining multiple tasks

> It solved Future's biggest problem: blocking, instead of `get()`, it use callbacks like `thenApply`...

#### Key Methods for CompletableFuture
✨ **Result Handling** 

| Method                           | Description                                              |
|----------------------------------|----------------------------------------------------------|
| `thenApply(Function<T, U> fn)`   | transforms result into another type                      |
| `thenAccept(Comsumer<T> action)` | return value without returning a new `CompletableFuture` |
| `thenRun(Runnable action)`       | ignores the return value and runs a runnable             |
| `get(timeout, unit)`             | waits up to timeout, els                                 |

✨ **Task Compose**

| Method                    | Description                                                                    |
|---------------------------|--------------------------------------------------------------------------------|
| `thenCompose()`           | chains dependent async tasks, pass the result to next task                     |
| `thenCombine(future, fn)` | combines results of two CompletableFutures and returns a new CompletableFuture |


✨ **Exception Handling**

| Method               | Description                                       |
|----------------------|---------------------------------------------------|
| `execptioonally(fn)` | handles exception and return an alternative value |
| `handle(fn)`         | processes result / exception                      |

✨ **Parallel Composition**

| Method              | Description                       |
|---------------------|-----------------------------------|
| `allOf(futures...)` | waits for all futures to complete |
| `anyOf(futures...)` | returns first completed result    |

## 23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10
> Code can be checked in `OddEven` package

### Synchronized and wait notify
```Java
public class OddEvenWaitNotify {
  private static final Object monitor = new Object();
  private static int value = 1;

  public static void main(String[] args) {
    PrintRunnable runnable = new PrintRunnable();
    new Thread(runnable).start();
    new Thread(runnable).start();
  }

  static class PrintRunnable implements Runnable {
    @Override
    public void run() {
      while (true) {
        synchronized (monitor) {
          if (value > 10) { // if reached more than -> call notify() to release the other thread (if it's still waiting) and exists loop
            monitor.notify();
            break;
          }
          System.out.println(Thread.currentThread().getName() + ": " + value++);

          monitor.notify(); // wake up the other thread -> switch btwn odd and even

          try {
            if (value <= 10) { // if value <= 10, it calls wait() to pause and allow the other thread to proceed
              monitor.wait();
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
}
```

### ReentrantLock and await, signal

```Java
package OddEven;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenReentrant {
    private static final Lock lock = new ReentrantLock();
    private static final Condition turn = lock.newCondition();
    private static int value = 1;
    private static boolean isOddTurn = true;

    public static void main(String[] args) {
        new Thread(new OddRunnable()).start();
        new Thread(new EvenRunnable()).start();
    }

    static class OddRunnable implements Runnable {
        @Override
        public void run() {
            while(true) {
                lock.lock();
                try {
                    // wait if it's not odd thread's turn
                    while (!isOddTurn) {
                        turn.await(); // make the current even thread wait -> will change to odd
                    }
                    if (value > 10) {
                        turn.signal();  // notify other thread in case it's waiting, then exit
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + value++);
                    isOddTurn = false; // Set flag to even's turn
                    turn.signal(); // wake up even -> change back to even
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class EvenRunnable implements Runnable {
        @Override
        public void run() {
            while(true) {
                lock.lock();
                try {
                    // wait if it's not even thread's turn
                    while (isOddTurn) {
                        turn.await(); // make the current odd thread wait -> will change to even
                    }
                    if (value > 10) {
                        turn.signal(); // notify other thread in case it's waiting, then exit
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + value++);
                    isOddTurn = true; // set flag to odd's turn
                    turn.signal(); // wake up odd -> change back to odd
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
```

## 24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random.
> Code can be checked in `ThreeThreadsSeparateInSequence` package
```Java
public class ThreeThreadsCounter {
    private static int counter = 1;
    private static final Object lock = new Object();
    private static int groupId = 0;

    public static void main(String[] args) {
        // shared task for all 3 threads
        Runnable task = () -> {
            int myGroup;
            synchronized (lock) { // assign each thread groupId (0, 1, 2)
                myGroup = groupId++; // thread 0, 1, 2
            }

            // based on groupId -> determine the number range this thread should print
            int start = myGroup * 10 + 1; // e.g. group 0 -> 1, group 1 -> 11, group 2 -> 21
            int end = Math.min(start + 9, 30); // limit is 30, group 2 -> 21-30

            // wait until its my turn
            synchronized (lock) {
                while (counter != start) {  // wait if it's not yet this thread's turn
                    try {
                        lock.wait(); // not this thread turn -> so wait
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // once it's this thread turn -> print its assigned range
                for (int i = start; i <= end; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    counter++; // move counter + 1
                }
                lock.notifyAll(); // notify all other waiting threads to check if it's their turn right now
            }
        };
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
    }
}
```

## 25. Completable Future:
### HW1:  Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results
> Code can be checked in `CompletableFuture` package

```java
package CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class AsyncSumProduct {
    public static void main(String[] args) {
        int a = 4;
        int b = 5;

        // asynchronously sum
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            return a + b;
        });

        // asynchronously product
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            return a * b;
        });

        // when both finished -> print result
        sumFuture.thenAccept(sum -> {
            System.out.println("Sum: " + sum);
        });

        productFuture.thenAccept(product -> {
            System.out.println("Product: " + product);
        });

        // keep the main thread alive in order to see output
        try {
            Thread.sleep(1000); // make sure output print before main ends
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
output:
Sum: 9
Product: 20
 */

// CompletableFuture.supplyAsync(...) runs tasks on a separate background thread (usually from the common ForkJoinPool)
//  main method finishes before those background threads print the results, the JVM may terminate early, and we won’t see any output
```

### HW2: CompletableFuture and public APIs to simulate fetching products, reviews, and inventory asynchronously 🏬
> Code can be checked in `OnlineStore` package

```Java
package OnlineStore;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.concurrent.CompletableFuture;


public class OnlineStoreSimulator {

  static HttpClient client = HttpClient.newHttpClient();

  public static void main(String[] args) throws Exception {
    CompletableFuture<String> productFuture = fetchAsync("https://jsonplaceholder.typicode.com/posts");
    CompletableFuture<String> reviewFuture = fetchAsync("https://jsonplaceholder.typicode.com/comments");
    CompletableFuture<String> inventoryFuture = fetchAsync("https://jsonplaceholder.typicode.com/todos");

    CompletableFuture<Void> allDone = CompletableFuture.allOf(productFuture, reviewFuture, inventoryFuture);

    allDone.thenRun(() -> {
      try {
        System.out.println("Product: " + productFuture.get().substring(0, 200));
        System.out.println("Review: " + reviewFuture.get().substring(0, 200));
        System.out.println("Inventory: " + inventoryFuture.get().substring(0, 200));
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    Thread.sleep(3000);
  }
  static CompletableFuture<String> fetchAsync(String url) {
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
    return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body);
  }
}

```

## HW3. Implement exception handling
If an exception occurs during any API call, return a default value and log the exception information.

