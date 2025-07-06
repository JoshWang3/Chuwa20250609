Thread pool is a collection of pre-created threads that are ready to execute tasks.

Types of Thread pool:
1. newFixedThreadPool(): fixed size thread pool
2. newCachedThreadPool(): create threads as needed
3. newSinglethreadExecutor(): single thread executor that serialize tasks
4. newScheduledThreadPool(): enable delayed task execution

A task queue is a queue based data structure used to hold tasks that need to be executed by the threads in the pool. When a task needs to be executed asynchronously, it will be pushed to the queue and wait for available threads to take up the task.

java.util.concurrent is the library to create thread pool and the main interface used is ExecutorService (with Executor as the super interface)

To submit a task we first create a new thread pool with ExecutorService (e.g. newFixedTreadPool(5)) and then call the .execute() or .submit() (returns a Future<V>) method to schedule a task.

The advantages of a thread pool are:
1. reuse pre-created threads stored in the pool and avoid creation each time we have a new task.
2. provide bounds to avoid going over the resource limit.
3. may cache any pending tasks in the TaskQueue until a thread becomes available.
4. having powerful framework such as ExecutorService that has built-in functions that simplifies the work and code.

shutdown() method stops the executor from picking up new tasks but allows ongoing tasks to complete before shutting down.
shutdownNow() stops receiving new tasks and all running tasks immediately and returns a List<Runnable> of tasks that are not yet executed.