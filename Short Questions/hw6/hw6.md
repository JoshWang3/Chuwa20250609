<p>2. Write a thread-safe singleton class</p>
Coding/src/Singleton.java

<p>3. How to create a new thread(Please also consider Thread Pool approach)?</p>
<p>1). extends Thread class</p>
<p>2). implements runnable</p>
<p>3). implements callable</p>
<p>4). thread pool: create fixed size thread pool</p>
`
ExecutorService executor = Executors.newFixedThreadPool(4);
`

<p> 4. Difference between Runnable and Callable?</p>
Runnable has no return, callable has return and expect checked exception.

<p> 5. What is the difference between t.start() and t.run()? </p>
t.start() A new thread is created through lazy initialization. The thread is created once t.start() is called. t.run() executes the task in the current thread.

<p> 6. Which way of creating threads is better: Thread class or Runnable interface?</p>
Runnable interface. It supports multiple inheritance, separates the task from the thread and works better with thread pools.

<p> 7. What are the thread statuses?</p>
New, Runnable, Waiting, Timed_waiting, Blocked and terminated

<p> 8. Demonstrate deadlock and how to resolve it in Java code.</p>
<p> Thread1 waits for ResourceB (held by Thread2), and Thread2 waits for ResourceA (held by Thread1), resulting in a deadlock. Both threads acquire one lock and wait for the other. </p>
<p> Coding/src/Deadlock.java</p>
<p> The locks are in same order; Use tryLock() with Timeout; avoid nested locks </p>

<p> 9. How do threads communicate each other?</p>
Wait & Notify meaning that one thread notifies the other thread, and this thread wait the other thread

<p> 10. What’s the difference between class lock and object lock?</p>
<p> object lock: only 1 thread can access this object at any given time</p>
<p> class lock: only 1 thread can access that class at any given time, across all instances of the class</p>

<p> 11. What is join() method?</p>
The main thread is blocked when other threads are running. Once other threads finishes, main thread continues.

<p> 12. What is yield() method </p>
The thread is not doing anything particularly important and if any other threads or processes need to be run, they should run. 
Otherwise, the current thread will continue to run.

<p> 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool? </p>
A thread pool is a collection of pre-initialized, idle threads that are ready to execute tasks, 
improving efficiency by reusing existing threads instead of creating new ones for each task.
There're FixedThreadPool, CachedThreadPool, SingleThreadExecuter.
The taskqueue holds the submitted tasks waiting to be executed by the pool of worker threads.  

<p> 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool? </p>
java.until.concurrent.*; ExecutorService

<p> 15. How to submit a task to ThreadPool? </p>
Using the ExecutorService interface from the java.util.concurrent package to submit a task to ThreadPool.

<p> 16. What is the advantage of ThreadPool? </p>
Reduing overhead of thread creation and destruction, improving performance. 

<p> 17. Difference between shutdown() and shutdownNow() methods of executor </p>
<p> shutdown() does not interrupt running tasks, stops accepting new tasks and allows already submitted tasks to complete.</p>
shutdownNow() is forceful shutdown. It attempts to interrupt all running tasks.

<p> 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic
    classes and its main methods. when to use it? </p>
<p> Used in multithreaded environments to prevent race condition and ensure data consistency when multiple threads access and modify shared variables. </p>
AtomicInteger, AtomicLong, AtomicBoolean

<pre>
import java.util.concurrent.atomic.AtomicInteger;
public class Example {
    private final AtomicInteger counter = new AtomicInteger(1);
    
    public void increment() {
        counter.incrementAndGet();
    }
    
    public int get() {
        return counter.get(); 
    }
}
</pre>

<p> 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe) </p>
thread-safe collections, for example ConcurrentHashMap, CopyOnWriteArrayList, CopyOnWriteArraySet, ArrayBlockingQueue, LinkedBlockingDeque

<p> 20. What kind of locks do you know? What is the advantage of each lock? </p>
<p> Synchronized: only 1 thread can access the resource at a time. Simple to use and integrated with JVM. </p>
<p> Reentrantlock: allow 1 thread to acquire the same lock multiple times; It can avoid deadlock by using tryLock() </p>
<p> ReadWriteLock: allow multiple threads to read the resource at a time but only allows one to write it at a time. 
It can improve the efficiency of reading the resource(read-heavy workloads) and avoid concurrency errors.
<p> StampedLock: allows writing while reading the resource, but it could cause inconsistency of data. It can improve the efficiency of reading the resource. 
Optimistic reads avoid locking unless a write conflict is detected. </p>

<p> 21. What is future and completableFuture? List some main methods of CompletableFuture. </p> 
<p> Future is an interface that represents the result of an asynchronous computation. It is returned when you 
submit a task to an ExecutorService. </p>
CompletableFuture is a more powerful and flexible alternative introduced in Java 8. It implements the Future interface, providing 
powerful asynchronous capabilities. It supports chaining operations for easy combination and management of multiple asynchronous 
tasks. It's non-blocking asynchronous operations. eg. thenAccept(); exceptionally(); thenApplyAsync(); anyOf(); allOf()

<p> 22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading) </p>
<p> 23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in
    com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter) </p>
<p> 1). One solution use synchronized and wait notify</p>
Coding/src/OddEvenPrinter/OddEvenPrinter1.java

<p> 2). One solution use ReentrantLock and await, signal</p>
Coding/src/OddEvenPrinter/OddEvenPrinter2.java
<pre>
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
</pre>

<p> 24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run
    sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1) </p>
<pre>    
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
</pre>

Coding/src/PrintNumber.java

25. completable future:
<p> 1. Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum
   and product of two integers, and print the results. </p>
Coding/src/Completable/hw1.java

<p> 2. Homework 2: Assume there is an online store that needs to fetch data from three APIs: products,
   reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched
   data for further processing. (需要找public api去模拟，) </p>
1. Sign In to Developer.BestBuy.com
2. Best Buy Developer API Documentation (bestbuyapis.github.io)
3. 可以⽤fake api https://jsonplaceholder.typicode.com/
4. Github public api: https://api.github.com/users/your-user-name/repos

Coding/src/Completable/hw2.java

<p> 3. Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API
   call, return a default value and log the exception information. </p>

Coding/src/Completable/hw3.java
