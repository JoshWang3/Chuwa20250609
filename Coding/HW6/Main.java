//test threadSafeSingleton
public class Main {
    public static void main(String[] args){
        ThreadSafeSingleton s1 = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton s2 = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton s3 = ThreadSafeSingleton.getInstance();

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
    }
}