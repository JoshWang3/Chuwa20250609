package interfacefeatures;

public interface Animal {
    default void sound() {
        System.out.println("Some generic animal sound");
    }

    static void info() {
        System.out.println("Animals make sounds.");
    }
}