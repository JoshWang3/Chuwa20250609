public class SingletonPattern {

    //1.private static variable
    private static SingletonPattern instance = new SingletonPattern();

    //2. private constructor
    private SingletonPattern(){
    }

    public static SingletonPattern getInstance(){
        return instance;
    }
}
