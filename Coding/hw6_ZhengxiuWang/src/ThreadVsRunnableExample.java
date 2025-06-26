// Two ways to create a thread in Java:
// 1. Extend Thread class  not recommended
// 2. Implement Runnable  better (more flexible)

public class ThreadVsRunnableExample {
    public static void main(String[] args) {
        // Using Thread class
        Thread t1 = new MyThread();
        t1.start();

        // Using Runnable interface (preferred)
        Runnable task = new MyRunnable();
        Thread t2 = new Thread(task);
        t2.start();
    }
}

// Method 1: Extend Thread
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread by extending Thread class");
    }
}

// Method 2: Implement Runnable
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread by implementing Runnable interface");
    }
}
