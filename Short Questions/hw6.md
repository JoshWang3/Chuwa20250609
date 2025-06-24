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


