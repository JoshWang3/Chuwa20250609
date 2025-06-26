public class RandomSequenceOfThreeThreads {
    Runnable t1 =() -> {
        for (int i=1; i<=10; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    };
    Runnable t2 =() -> {
        for (int i=11; i<=20; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    };
    Runnable t3 =() -> {
        for (int i=21; i<=30; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    };
    public static void main(String[] args) {
        RandomSequenceOfThreeThreads printer = new RandomSequenceOfThreeThreads();
        //像 sleep()、wait()、join() 这样的“受检方法”才需要处理 InterruptedException
        Thread thread1 = new Thread(printer.t1);
        Thread thread2 = new Thread(printer.t2);
        Thread thread3 = new Thread(printer.t3);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
