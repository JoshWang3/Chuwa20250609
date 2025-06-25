## Short Questions
1. Q1: Refer to the provided InterviewBit link on multithreading interview questions.
2. Q3: You can create a thread by extending Thread or implementing Runnable and passing it to a new Thread; for a thread pool, use ExecutorService (e.g., via Executors.newFixedThreadPool) and submit Runnable or Callable tasks.
3. Q4: Runnable has no return value and cannot throw checked exceptions; Callable returns a result and can throw exceptions.
4. Q5: `start()` spawns a new OS thread and then calls `run()`, whereas `run()` executes in the current thread.
5. Q6: Implementing Runnable (or Callable) is preferred because it separates the task from the thread and allows reusing the task with different executors.
6. Q7: Thread states are NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, and TERMINATED.
7. Q9: Threads communicate via shared objects with synchronization, `wait`/`notify`, or higher-level constructs like `BlockingQueue`.
8. Q10: A class-level lock (`static synchronized`) locks on the Class object affecting all instances; an object-level lock (`synchronized` instance method or block) locks only that instance.
9. Q11: `join()` causes the calling thread to wait until the target thread finishes execution.
10. Q12: `yield()` hints to the scheduler that the current thread is willing to pause and allow other threads of the same priority to run.
11. Q13: A thread pool manages a set of worker threads. Common types include fixed, cached, scheduled, and single-thread pools. The task queue holds pending tasks until a thread is free.
12. Q14: The `java.util.concurrent` library (Executors and ThreadPoolExecutor) is used; `ExecutorService` provides core thread-pool functions.
13. Q15: Use `ExecutorService.submit(Callable)` or `execute(Runnable)` to submit tasks.
14. Q16: Thread pools reuse threads, reduce overhead, limit resource usage, and improve performance.
15. Q17: `shutdown()` stops accepting new tasks but finishes existing ones; `shutdownNow()` tries to cancel running tasks and returns pending ones.
16. Q19: Concurrent collections are thread-safe structures in `java.util.concurrent`, e.g., `ConcurrentHashMap`, `CopyOnWriteArrayList`, `LinkedBlockingQueue`, `ConcurrentSkipListMap`.
17. Q20: Locks include `synchronized` (intrinsic), `ReentrantLock` (fairness, tryLock, lockInterruptibly), `ReadWriteLock` (separate read/write locks), and `StampedLock` (optimistic reads).
18. Q21: `Future` represents a pending result; `CompletableFuture` adds chaining and callbacks. Key methods: `supplyAsync`, `runAsync`, `thenApply`, `thenAccept`, `thenCompose`, `exceptionally`, `allOf`.
