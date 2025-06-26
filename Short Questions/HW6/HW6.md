

3.Code is in file CreateNewThread.java, it shows both direct approach and thread pool apporach for creating thread

4.Runnable run() method do not have return value, while Callable call() method have return value

5.t.start() actually create a OS thread and eventually calls run() in the new thread, while directly call run() method just execute the method in current thread.

6.Thread class is better. Thread class contain all states need for maintaining a thread, and methods to control the thread. If just using Runnable interface, all of above must be implemented, introduce lot of extra unnecessary code.

7.Thread status contains: 

​	a. Waiting, Thread waiting for being notified.

​	b. Running, Thread is currently running.

​	c. Ready, Thread could running now, but not running for system scheduling

​	d. Blocked, Thread is blocked for not have specific lock

​	e. Terminated, Thread finished running

​	f. New, Thread is newly created without calling start()

8.

9.Java Thread could  communicate with each other using 

​	a.wait()/notify()

​	b.shared variable with synchronization

​	c.CompletableFuture

10.Class lock, like public static synchronized void foo(), have only one lock for the class, operations on different instances will block each other and get serialization. object lock, like public synchronized void foo(), have one lock per instance, so the methods belongs to different instances could run parallel.

11.join() method is for a thread waiting for another thread do all of their work and terminated.

12.yield() method is a hint to the scheduler for moving the current thread from Running state to Runnable state, hoping another thread to run.

13.Thread pool is a utility to manage many threads. users could submit tasks to the Thread pool to make them run by these threads. There are 6 types: Fixed size, Cached, Single Threaded,Sheduled, Work Stealing, Virtual Thread per Task executor. 

14.Task queue is BlockingQueue<Runnable> that buffer the works to be done by the threads. If there are threads available, the task will be done. Otherwise the task is queued waiting for available threads.

15.submit() method could be used for submit tasks to Thread Pool. Usage could be shown at CreateNewThread.java

16.When using thread pool, It's easy to run tasks, and get the task result using Future. No need to manually manage and generate threads.

17.shutdown() will only stop accepting new tasks. shutdownNow() will stop accepting new tasks, cancel queued tasks, interrupts running tasks.

18.Atomic class is classes behaves like their regular version(like Integer, Long), but support operations like compareAndSet() , incrementAndGet(), for lock free concurrency. Usage is at Atomic.java

19.Concurrent collections in java behaves like their corresponding collections, but ensured thread safety when accessing. Examples is ConcurrentHashMap, ConcurrentLinkedDeque

20.To lock, one could use synchronized keyword, or ReentrantLock. synchronized keyword is easy to use, provide mutex semantic. ReentrantLock could be use when the more flexible lock() unlock() time is needed, or a thread need to get lock many times.

21.Future could be used to represent result of asychronous computation, result could get using get() method, which is blocking. CompletableFuture implements future, and full support non-blocking, callback based result getting. Main methods contains supplyAsync(), thenApply(), thenAccept().

23.Code is at EvenAndOddThreads.java

24.Code is at ThreeThreads.java

25.Code is at Completable.java

