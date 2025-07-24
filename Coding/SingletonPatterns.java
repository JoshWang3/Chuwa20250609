public class SingletonPatterns {
    public static void main(String[] args) {
        EagerLoadSingleton els1 = EagerLoadSingleton.getInstance();
        EagerLoadSingleton els2 = EagerLoadSingleton.getInstance();
        els1.printMessage();
        els2.printMessage();
        els1.setMessage("new_message");
        els2.printMessage();
    }



}

class EagerLoadSingleton {
    //1.private static instance - the only instance will be used
    private static final EagerLoadSingleton INSTANCE = new EagerLoadSingleton();

    private String message = "EagerLoadSingleton";

    //2. private constructor
    private EagerLoadSingleton(){
    }


    //3. get this pattern
    public static EagerLoadSingleton getInstance(){
        return INSTANCE;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println(this.message);
    }
}



class LazyLoadSingleton {
    private LazyLoadSingleton(){}

    static {}

    private static class SingletonHolder {
        private static final LazyLoadSingleton INSTANCE = new LazyLoadSingleton();
    }

    public static LazyLoadSingleton getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
