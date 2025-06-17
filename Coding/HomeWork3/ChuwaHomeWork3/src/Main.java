//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.HashMap;
import java.util.Hashtable;

public class Main {
    public static void main(String[] args) {

        Dog chiwawa = new Dog();
        Dog pug = new Dog();

        chiwawa.setId(5);
        pug.setId(6);


        /**
         # 2
         since java is oop language but still have primitive data type. it make it hard to
         be involved in oop functions. wrapper class is an object from java lang package to bridge
         primitive data type and OOP model so they can be used in collection, generic and nullaliilty etc.
         it also provide utility method like parse
        */
        //int a = null;
        Integer b = null;


        /**
            # 3
            HashMap extend AbstractMap which is more modern
            HashTable is older one extend Dictionary
            HashMap is not synchronized, so it's faster and allows one null key and multiple null values.
            Hashtable is synchronized (thread-safe), but slower and doesn’t allow nulls.
            HashMap is modern (Java 1.2+), while Hashtable is legacy (Java 1.0).

        */
        Hashtable<Integer,Integer> ht = new Hashtable<>();
        HashMap<Integer, Integer> hp = new HashMap<>();


        /**
            #4
            String Pool in Java is a special memory area in the heap that stores unique String literals.
            When you create a String like "Hello", Java checks the pool first—if it exists, it reuses it; if not, it adds it. This improves memory efficiency and performance.
            Strings are immutable, meaning their state can't change after creation. This makes them:
            Thread-safe (no sync needed)
            Ideal for hash keys
            Secure for use in class loading, caching, etc.
            We need the String pool because:
            It reduces memory footprint
            Enables reuse of objects, aligning with OOP principles like efficiency and encapsulation.

        */
        String text = "hello";



        /**
         #5
         * **Garbage Collection (GC)** in Java handles **automatic memory management**
         * by deleting **unreachable objects** from the heap to avoid **memory leaks**.
         * Supports **OOP** by managing **object lifecycles** and ensuring **efficiency**.
         *
         * **Types of GC:**
         * 1. **Serial GC** – single-threaded, ideal for small apps.
         * 2. **Parallel GC** – multi-threaded, high throughput.
         * 3. **CMS GC** – low pause time, runs alongside app threads.
         * 4. **G1 GC** – default since Java 9, balances latency and throughput.
         * 5. **ZGC/Shenandoah** – ultra-low pause, for large-scale apps.
         */


        /**
         * 6
         * **Access Modifiers** in Java define the **visibility** and **access scope** of classes, methods, and variables.
         * public > protected (abstract class and interface) > default > private
         * 1. **public** – accessible **everywhere** (any class, any package).
         * 2. **protected** – accessible in the **same package** and **subclasses** (even in other packages).
         * 3. **default** (no modifier) – accessible **only within the same package**.
         * 4. **private** – accessible **only within the same class**.
         *
         * Used to enforce **encapsulation**, a core **OOP principle**.
         */
        Cat englishShort =  new Cat();
        englishShort.companion();
        chiwawa.companion();

        /**
         * 7 final
         * - class: make whole class un-inheritable
         *  - field: make filed a constant variable
         *  - method: make method unable to override
         * */
        System.out.println(Bird.isDinosaur);


        /**
         * 8 static
         * - class: (only inner classes): can be accessed **without creating outer class object**.
         *  - field: accesiable without class be instantiated as object
         *  - method: field: accesiable without class be instantiated as object
         *  it usually to be used when utility method or constant field needed for the class
         *  supports memory use
         * */
        System.out.println(Bird.isDinosaur);




        /**
         * 9 override vs overload
         * all part of polymorphism
         * override is to implement a method which extend or implement from parent class or interface
         * need to keep exact same function signature
         * overload is ability to multiple method with same name but different passing parameter
         *
         * */
        chiwawa.lactation();
        chiwawa.lifeExpectancy();
        chiwawa.lifeExpectancy(18);

        /**
         * 10 signature in java
         * all part of polymorphism
         * override is to implement a method which extend or implement from parent class or interface
         * need to keep exact same function signature
         * overload is ability to multiple method with same name but different passing parameter
         *
         * */
        pug.lactation();
        pug.lifeExpectancy();
        pug.lifeExpectancy(18);


        /**
         * 11 super vs this
         * super: keyword is used to access field and method from parent class
         * this: keyword is used to access its own field and method. refer to current class
         * */
        chiwawa.lactation();


        /**
         * 12  Explain how equals and hashCode work
         * **equals() and hashCode()** are used for **object comparison** and **hash-based collections** (e.g., HashMap).
         *
         * - **equals(Object obj)**: checks **logical equality** (override to compare values, not references).
         * - **hashCode()**: returns an **int hash**, used in **hashing algorithms** (e.g., buckets in HashMap).
         *
         * ✅ Rule: If two objects are **equal via equals()**, they must have the **same hashCode()**.
         * ❌ Violating this breaks contracts in sets, maps, etc.
         * */
        HashMap<Dog, String> dogMap = new HashMap<>();
        dogMap.put(chiwawa, "mexico");
        boolean isSameObj = chiwawa.equals(pug);
        System.out.println(isSameObj);

        /**
         * 13 JAVA load sequence
         * **Java Class Load Sequence** follows this order:
         *
         * 1. **Static Blocks** – executed once when class is loaded.
         * 2. **Instance Blocks** – run every time an object is created, before constructor.
         * 3. **Constructor** – runs after instance block, completes object initialization.
         *
         * 🔁 So: **static block → instance block → constructor**
         * Enables controlled setup in **OOP**, ensuring proper class/object state.
         */

        /**
         * answered in question 9
         * method override and overload is how java implement polymorphism
         * */

        /**
         * 15 encapsulation
         * 1. usage of access modifier
         * 2. public getter and setter
         * */
        Dog terrier = new Dog();
        terrier.setId(1);
        terrier.setChipId(1);


        // example of interface and abstract class. please check Dog class implementation

        terrier.lactation();
        //implemented method from Pet class
        terrier.isRegistered();




















    }
}


