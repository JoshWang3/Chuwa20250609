class Dog {
    String name;
    Dog(String name) {
        this.name = name;
    }
}

public class PassByValueDemo {

    // Example 1: Primitive
    public static void modifyPrimitive(int x) {
        x = 10;
    }

    // Example 2: Object
    public static void modifyObject(Dog d) {
        d.name = "Max";             // Modifies the object via reference
        d = new Dog("Rocky");       // Changes local copy of reference only
    }

    public static void main(String[] args) {
        // ---- Primitive Test ----
        int num = 5;
        modifyPrimitive(num);
        System.out.println("Primitive after modifyPrimitive(): " + num); // Output: 5

        // ---- Object Test ----
        Dog myDog = new Dog("Buddy");
        modifyObject(myDog);
        System.out.println("Object after modifyObject(): " + myDog.name); // Output: Max
    }
}
