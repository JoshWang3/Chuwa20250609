package interface_keywords;

public interface Animals {
    void makeSound();

    default void eat() {
        System.out.println("The animal is eating.");
    }

    static void sleep() {
        System.out.println("The animal is sleeping.");
    }
}
