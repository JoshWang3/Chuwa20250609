package Question1;

public class SingletonPatterns {
    public static void main(String[] args) {
        System.out.println("===Program Started===");

        EagerLoadSingleton.ping();
        LazyLoadSingleton.ping();


        //Eager Load
        EagerLoadSingleton els = EagerLoadSingleton.getInstance();


        System.out.println("=== The Lazy load Singleton will construct only when we get its instance ===");
        //Lazy Load
        LazyLoadSingleton lls = LazyLoadSingleton.getInstance();

        System.out.println();

        // tested we only have 1 instance for each load:

        EagerLoadSingleton els2 = EagerLoadSingleton.getInstance();
        System.out.println("Check is els2 == els1: " + (els == els2) );

        LazyLoadSingleton lls2 = LazyLoadSingleton.getInstance();
        System.out.println("Check is lls2 == lls1: " + (lls == lls2) );


        System.out.println("The above result shows both eagerload and lazyload will only create one instance.");
    }



}

class EagerLoadSingleton {
    //1.private static instance - the only instance will be used
    private static final EagerLoadSingleton INSTANCE = new EagerLoadSingleton();

    private String message = "Question1.EagerLoadSingleton";

    //2. private constructor
    private EagerLoadSingleton(){
        System.out.println(">> Question1.EagerLoadSingleton constructor called");
    }


    //3. get this pattern
    public static EagerLoadSingleton getInstance(){
        return INSTANCE;
    }

    public static void ping() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println(this.message);
    }
}



class LazyLoadSingleton {
    private LazyLoadSingleton(){
        System.out.println(">> Question1.LazyLoadSingleton constructor called");
    }

    static {
        System.out.println(">> Question1.LazyLoadSingleton static block called");
    }

    public static void ping() {
    }

    private static class SingletonHolder {
        private static final LazyLoadSingleton INSTANCE = new LazyLoadSingleton();
    }

    public static LazyLoadSingleton getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
