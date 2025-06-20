package hw4;

class Multithreading extends Thread {
    private final SingletonProvider provider;

    Multithreading(SingletonProvider provider) {
        this.provider = provider;
    }

    public void run()
    {
        try {
            Object instance = provider.getInstance();
            System.out.println("Thread " + Thread.currentThread().threadId() + " is running"
                    + ", the hashCode of lazySingleton is: " + instance.hashCode());
        }
        catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }
}
