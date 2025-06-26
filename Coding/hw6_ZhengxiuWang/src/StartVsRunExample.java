public class StartVsRunExample extends Thread {
    @Override
    public void run() {
        System.out.println("Running in: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        StartVsRunExample t = new StartVsRunExample();

        // This will run in a new thread
        t.start();

        // This will run in the main thread (just a method call)
        t.run();
    }
}
