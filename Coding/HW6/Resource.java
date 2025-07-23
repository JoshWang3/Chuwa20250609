public class Resource {

    void method1() {
        synchronized (DeadlockDemo.r1) {
            System.out.println("Thread " + Thread.currentThread().getName() + " locked Resource 1");
            try {
                // Simulate some work with Resource 1
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " trying to lock Resource 2");
            synchronized (DeadlockDemo.r2) {
                System.out.println("Thread " + Thread.currentThread().getName() + " locked Resource 2");
            }
            
        }
    }

    void method2(){
        synchronized(DeadlockDemo.r1){
           System.out.println("Thread " + Thread.currentThread().getName() + " locked Resource 1");
            try {
                // Simulate some work with Resource 1
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " trying to lock Resource 2");
            synchronized (DeadlockDemo.r2) {
                System.out.println("Thread " + Thread.currentThread().getName() + " locked Resource 2");
            }
        }
    }
}
        

