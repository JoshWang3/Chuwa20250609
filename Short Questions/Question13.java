public class Question13 {
    public static void main(String[] args) {
        System.out.println("=== Creating the first Child Object");
        Child c1 = new Child();

        System.out.println("=== Creating the second Child Object === Static blocks will only run once");
        Child c2 = new Child();
        Child.Inner.sayHello();

    }
}

class Parent {
    static {
        System.out.println("1. Parent static block (runs once when class is loaded)");
    }


    {
        System.out.println("4. Parent instance block (runs before constructor, for each object)");
    }

    public Parent() {
        System.out.println("5. Parent constructor (runs after instance block)");
    }
}

class Child extends Parent {
    static {
        System.out.println("2. Child static block (runs once when class is loaded)");
    }

    {
        System.out.println("6. Child instance block (runs before constructor, for each object)");
    }

    public Child() {
        System.out.println("7. Child constructor (runs after instance block)");
    }

    static class Inner {
        static {
            System.out.println("9. Child.Inner static block (runs only when Inner is first accessed)");
        }

        static void sayHello() {
            System.out.println("10. Hello from Child.Inner!");
        }
    }

}

