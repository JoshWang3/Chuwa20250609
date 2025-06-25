## 3. How to create a new Thread? (Also consider Thread Pool Approach)
* Extending the Thread Class:

    Create a subclass of Thread and override the run() method.

    public class MyThread extends Thread{

        public void run(){}

    }

    MyThread thread = new MyThread();

* Implementing the Runnable Interface:

    Creating a class that implements the java.lang.Runnable interface. 

    public class MyRunnable implements Runnable{

        publlic void run(){}
    }

    MyRunnable runnable = new MyRunnable();

    Thread thread = new Thread(runnable);

* ThreadPool:

    Using ExecutorService

    ExecutorService executor = Executors.newFixedThreadPool(5);

## 4. Difference between Runnable and Callable?
* Runnable instances can be run by both Thread and ExecutorService.

    Runnable has no return;
* Callable instances cannot be run directly by Thread, but can be executed via ExecutorService or wrapped in a FutureTask and then run by a Thread.

    Callable has return and expect checked exception

## 5. What is the difference between t.start() and t.run()
* t. start() : Starts a `new thread` and executes the run() method in that new thread. 
* t. run() : Calls the run() method in the `current thread`, just like a normal method.

## 6. Which way of creating threads is better: Thread class or Runnable interface?
Runnable
* extends Thread class, after that you can’t extend any other class which you required. 

* implements Runnable, you can save a space for your class to extend any other class in future or now.

However, the significant difference is.

* When you extends Thread class, each of your thread creates unique object and associate with it. 
* When you implements Runnable, it shares the same object to multiple threads.

## 7. What are the thread statuses?

A thread in Java can exist in any one of the following states at any given time. A thread lies only in one of the shown states at any instant:

* New State
* Runnable State
* Blocked State
* Waiting State
* Timed Waiting State
* Terminated State

## 8. Demonstrate deadlock and how to resolve it in Java code?

Two threads try to lock two resources (lock1 and lock2) in reverse order, which can lead to a deadlock.

* Thread 1 holds lock1 and waits for lock2.

* Thread 2 holds lock2 and waits for lock1.

* Both are blocked → deadlock.

Java:

*  using timed lock attempts. We can use the tryLock() method from the Lock interface. 

* Using Thread.join() Method

## 9. How do threads communicate each other?

* Shared Variables
* Synchronization

INTER-THREAD COMMUNICATION
* wait()
* notify() & notifyAll()

## 10. What's the difference between class lock and object lock?

what you are locking on — the class itself or a specific instance (object).

* Object Lock (Instance Lock)

    Applies to a specific object (this).

* Class Lock (Static Lock)

    Applies to the Class object, which is shared by all instances.
## 11. What is join() method?

It will put the current thread on wait until the thread on which it is called is dead or wait for the specified time

## 12. what is yield() method

The yield() basically means that the thread is not doing anything particularly important and if any other threads or processes need to be run, they should run. Otherwise, the current thread will continue to run.



## 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

* ThreadPool: A thread pool reuses previously created threads to execute current tasks and offers a solution to the problem of thread cycle overhead and resource thrashing.

* Types: 
    * Fixed Size Thread Pool Executor
    * Cached Thread Pool Executor
    * Scheduled Thread Pool Executor
    * Single Thread Pool Executor
    * Work Stealing Thread Pool Executor

* TaskQueue: When you submit a task, if all threads are busy, the task is stored in the queue until a thread becomes free.


## 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
* library: java.util.concurrent

* Interface:  Executor, ExecutorService
 
## 15. How to submit a task to ThreadPool?

execute(), submit()


## 16. What is the advantage of ThreadPool?
Thread pools save resources and time by reusing existing threads instead of creating and destroying them repeatedly

## 17. Difference between shutdown() and shutdownNow() methods of executor
* shutdown(): Waits for running tasks to finish, no new tasks allowed.

* shutdownNow(): Tries to interrupt running tasks and cancels tasks that haven't started.

## 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
* Atomic Class: These classes encapsulate primitive types or references and enable thread-safe, atomic operations
* Java provides atomic classes such as AtomicInteger, AtomicLong, AtomicBoolean and AtomicReference. 
* AtomicInteger Example:
    

        import java.util.concurrent.atomic.AtomicInteger;

        public class AtomicIntegerExample {

        public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);

        // Increment
        count.incrementAndGet(); // 1

        // Add and Get
        count.addAndGet(5);      // 6

        // Get and Increment
        count.getAndIncrement(); // 6 (then becomes 7)

        // Compare and Set
        boolean success = count.compareAndSet(7, 10);true
        count.get();             // 10
        }
        }
        
    When to use it: 
    * Counting requests, tasks, or events in multithreaded environments.

    * Thread-safe counters without locks.
    




## 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
* Concurrent Collections provides improved performance and scalability in multi-threaded environments and are always thread-safe. 
* ConcurrentHashMap, CopyOnWriteArrayList, CopyOnWriteArraySet


## 20. What kind of locks do you know? What is the advantage of each lock?
* ReentrantLock: Same thread can acquire multiple time
* ReadWriteLock: read heavy task, read more, write less
* StampedLock: Do not allow writing when reading



## 21. What is future and completableFuture? List some main methods of ComplertableFuture
* Future: Represents the result of an asynchronous computation.
* ComplertableFuture: 
    * An enhancement over Future.
    * Supports non-blocking, asynchronous programming, and functional-style callbacks.
    * Can combine, chain, and handle multiple tasks.





