public class DefaultVsStatic {
    public static void main(String[] args) {
        MyDog dog = new MyDog();
        dog.sayHello();        // default method
        dog.bark();            // class method

        Animal1.showInfo();     // static method
    }
}

// interface with default + static
interface Animal1 {
    default void sayHello() {
        System.out.println("Hello from Animal");
    }

    static void showInfo() {
        System.out.println("Animal interface - static info");
    }
}

// class implements interface
class MyDog implements Animal1 {
    public void bark() {
        System.out.println("Dog is barking");
    }

    // override optional
    // public void sayHello() {
    //     System.out.println("Hi from MyDog");
    // }
}
