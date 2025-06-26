class Warehouse {
    private int stock = 0;
    private final Object lock = new Object();

    public void produce() throws InterruptedException {
        synchronized (lock) {
            stock++;
            System.out.println("Stock Successful: " + stock);
            lock.notifyAll();
        }
    }

    public void consume() throws InterruptedException {
        synchronized (lock) {
            while (stock == 0) {
                System.out.println("Wait no stock");
                lock.wait();
            }
            stock--;
            System.out.println("Stock Consumed: " + stock);
        }
    }
}