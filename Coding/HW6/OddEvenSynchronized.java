public class OddEvenSynchronized {
    private boolean isOdd = true;
    public synchronized void printOdd(int number) throws InterruptedException {
        while (!isOdd) {
            wait(); //不是它的回合，释放锁，等待唤醒
        }
        System.out.println(Thread.currentThread().getName() + ": " + number);
        isOdd = false; // 下一轮轮到偶数线程
        notify(); // 唤醒偶数线程
    }
    public synchronized void printEven(int number) throws InterruptedException{
        while (isOdd) {
            wait();
        }
        System.out.println(Thread.currentThread().getName() + ": " + number);
        isOdd = true;
        notify();
    }
    public static void main(String[] args) {
        OddEvenSynchronized printer = new OddEvenSynchronized();
        Thread t1 = new Thread(() -> { //Lambda 表达式不能直接 throws checked exception
            for (int i=1; i<=9; i+=2) {
                try{
                    printer.printOdd(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i=2; i<=10; i+=2) {
                try{
                    printer.printEven(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
