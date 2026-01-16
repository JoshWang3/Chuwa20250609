public class ThreeThreadsCounter {
    private static int counter = 1;
    private static final Object lock = new Object();
    private static int groupId = 0;

    public static void main(String[] args) {
        // shared task for all 3 threads
        Runnable task = () -> {
            int myGroup;
            synchronized (lock) { // assign each thread groupId (0, 1, 2)
                myGroup = groupId++; // thread 0, 1, 2
            }

            // based on groupId -> determine the number range this thread should print
            int start = myGroup * 10 + 1; // e.g. group 0 -> 1, group 1 -> 11, group 2 -> 21
            int end = Math.min(start + 9, 30); // limit is 30, group 2 -> 21-30

            // wait until its my turn
            synchronized (lock) {
                while (counter != start) {  // wait if it's not yet this thread's turn
                    try {
                        lock.wait(); // not this thread turn -> so wait
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // once it's this thread turn -> print its assigned range
                for (int i = start; i <= end; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    counter++; // move counter + 1
                }
                lock.notifyAll(); // notify all other waiting threads to check if it's their turn right now
            }
        };
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
    }
}