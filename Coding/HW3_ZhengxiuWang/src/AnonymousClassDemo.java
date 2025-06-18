public class AnonymousClassDemo {
    public static void main(String[] args) {
        // use interface
        Task todo = new Task() {
            public void run() {
                System.out.println("Doing homework");
            }
        };
        todo.run(); // call interface method

        // use abstract class
        Worker jack = new Worker("Jack") {
            public void work() {
                System.out.println(name + " is coding");
            }
        };
        jack.work(); // call abstract method
    }
}

// interface
interface Task {
    void run();
}

// abstract class
abstract class Worker {
    String name;

    public Worker(String name) {
        this.name = name;
    }

    abstract void work();
}
