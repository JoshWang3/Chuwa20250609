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