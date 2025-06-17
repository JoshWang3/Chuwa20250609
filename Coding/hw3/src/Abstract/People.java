package Abstract;

public interface People {
    public void speak();

    default void sayHello() {
        System.out.println("Hello");
    }

    static void sayHi() {
        System.out.println("Hi");
    }
}
