3. 
1) The easiest way is to extend the Thread class then override the run() method. Then we can create the implemented thread class and call the start method.
2) Second way is to implement the runnable class. We implement the run() method and then call Thread(mew runnable) and call the start method.
3) Use Thread pool
```angular2html
ExecutorService executor = Executors.newFixedThreadPool(3);
Runnable task = () -> &#123;
System.out.println(Thread.currentThread().getName());
&#125;;
for (int i = 0; i < 5; i++) &#123;
    executor.submit(task);
&#125;
```

4. runnable has no return value, and cannot throw checked exceptions, callable can throw chekced exceptions and can return a result V
5. Assume we have the folloiwng code:
```angular2html
Thread t = new Thread(() -> &#123;
    System.out.println("Running in: " + Thread.currentThread().getName());
&#125;)
```
t.start() starts a new thread, calls the thread’s run() method in a new call stack / thread.
The thread runs concurrently with the main thread.

t.run() does not start a new thread, it only calls the run method.

6. Mostly runnbale method is preferred. Runnable separates task logic from thread execution. We can define the task independently and then run it via Thread, ExecutorService, or thread pools.
Thread class is less flexible. Logic and thread control are mixed, which reduces reusability.
7. NEW-Thread created but not started
   RUNNABLE-Running or ready to run
   BLOCKED-Waiting for an intrinsic lock
   WAITING-Waiting indefinitely for another thread's signal (join, wait etc.)
   TIMED_WAITING-Waiting for a fixed period
   TERMINATED-Thread has finished execution

8. Below is an example of deadlock
```angular2html
public class deadlock &#123;
    private static final Object lockA = new Ojbect();
    private static final Object lockB - new Object();
    
    public static void main(String[] args) &#123;
        Thread thread1 = new Thread(() -> &#123;
            synchronized(lockA) &#123;
                System.out.println("Thread 1: Holding lock A...");
                synchronized (lockB) &#123;
                    System.out.println("Thread 1: Acquired lock B!");
                }
            }
        });
        Thread thread2 = new Thread(() -> &#123;
            synchronized(lockB) &#123;
                System.out.println("Thread 1: Holding lock B...");
                synchronized (lockA) &#123;
                    System.out.println("Thread 1: Acquired lock A!");
                }
            }
        });
    }
}
```
At some moment, Thread 1 might holds lockA and waits for lockB, and at the same time Thread 2 holds lockB and waits for lockA.
Deadlock happens

9. Threads communicate with each other mainly through shared memory and synchronization primitives like wait(), notify(), and notifyAll().
10. Object Lock is acquired when you synchronize on an instance of a class
Only one thread can execute a synchronized instance method on the same object at a time.
An example is
```angular2html
public synchronized void instanceMethod() &#123;
    // Locks 'this' (the object)
}
```

Class Lock is acquired when you synchronize on the class itself. There is only one class-level lock per class, shared by all instances.
It's mainly used when we need to protect access to static fields or perform actions that affect all instances.

11. The join() method is used to ensure a thread finishes before continuing.
12. The yield() method hint to the thread scheduler that the current thread is able to pause and let other threads run
13. A ThreadPool is a pool of reusable threads managed by the Java runtime to execute tasks. Instead of creating a new thread for every task (which is expensive), we submit tasks to a pool, and idle threads in the pool handle them.

Types of threadpool:
newFixedThreadPool(int n)
newCachedThreadPool()
newSingleThreadExecutor()
newScheduledThreadPool(int n)

The TaskQueue is a blocking queue used to hold submitted tasks before they’re picked up by worker threads.
14. java.util.concurrent
    ExecutorService
15. 
```angular2html
import java.util.concurrent.*;
ExecutorService executor = Executors.newFixedThreadPool(numThread); // numThread is number of threads we want
executor.submit(() -> &#123;
    // Task we want to do
});
// We can also do
Future<Integer> future = executor.submit(() -> {
    // Task we want to do
    });

    Integer result = future.get();
```

16.  
1) I mproved Performance because it reuses existing threads
2) Better thread management because it limits the maximum number of concurrent threads
3) It supports Callable and Future

17. shutdownNow() immediately interrupt the thread, it has return value while shutdown() does not.
18. Atomic classes are part of java.util.concurrent.atomic and provide lock-free, thread-safe operations on single variables using low-level atomic CPU instructions (like CAS - Compare And Swap).
There are three types of atomic classes: Primitive Wrappers, Array Types and Reference types
```angular2html
class User &#123;
    String name;
    User(String name) &#123; this.name = name; }
}

AtomicReference<User> ref = new AtomicReference<>(new User("Alice"));
ref.compareAndSet(ref.get(), new User("Bob"));
System.out.println(ref.get().name); // Bob
```

19. Concurrent Collections are thread-safe collections from the java.util.concurrent package. They are designed for concurrent access by multiple threads without requiring explicit synchronization (like synchronized blocks).
Examples: ConcurrentHashMap<K,V>, SynchronousQueue<E>
20. Intrinsic Lock: Simple to use, automatic release
    Explicit Lock: Try lock
    ReadWriteLock: Good read performance
    ReentrantLock: Try lock, Interruptible
21. A Future<T> represents the result of an asynchronous computation.
    It’s returned when you submit a Callable to an ExecutorService.

thenApply(Function)
thenAccept(Function)
23. 
