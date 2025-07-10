Question 1 - 3:
See Coding/hw2/Employee.java

Question 4: 
Global variables are not recommended because: 
1. Global variables can be modified from anywhere, leading to unintended side effects and bugs.
2. They reduce code modularity and make testing/debugging harder.

```java
    class GlobalVariableExample {
    static int counter = 0;  // Global variable
    
        public static void increment() {
            counter++;
        }
    
        public static void reset() {
            counter = -1;  // Anyone can change it freely
        }
    
        public static void main(String[] args) {
            increment();
            System.out.println("Counter: " + counter); // 1
            reset();
            System.out.println("Counter after reset: " + counter); // -1 (unexpected?)
        }
    }
```
    
Question 5:
Once a String is created, its contents cannot be changed. Any operation on a String creates a new object instead of modifying the original. 

```java
    public class StringImmutable {
        public static void main(String[] args) {
            String s = "Hello";
            s.concat(" World");
            System.out.println(s); // Output: Hello
            
            s2 = s.concat(" World");
            System.out.println(s2); // Output: Hello World
            
            System.out.println(s == s2); // Output: false (different objects)
        }
    }
```

Question 6:
Final keyword prevents a variable from being reassigned, a method from being overridden, or a class from being subclassed. It ensures immutability and can improve performance by allowing optimizations.

```java
final class FinalKeywordExample {
    final int y = 20; // Cannot change y
    
    void method() {
        try {
            y = 30; // This would cause a compile-time error
        } catch (Exception e) {
            System.out.println("Cannot change final variable y.");
        }
    }
    
    final void finalMethod() {
        System.out.println("Final method.");
    }
}

public class FinalKeywordDemo extends FinalKeywordExample {
    // Cannot override finalMethod() here
    // void finalMethod() { } // This would cause a compile-time error
}

```

Question 7:
Java passes copies of values, not the references themselves, for both primitive and reference types. 
For primitives, the value is copied; for objects, the reference is copied, but the object itself is not copied.
Therefore, it might be confusing. 


```java
class MyObject {
    int value = 10;
}

public class PassByValue {
    public static void modify(MyObject obj) {
        obj.value = 20; // changes internal state
        obj = new MyObject(); // this change doesn't affect the original reference
        obj.value = 30;
    }

    public static void main(String[] args) {
        MyObject obj = new MyObject();
        modify(obj);
        System.out.println(obj.value); // Output: 20, not 30
    }
}
```

Question 8:

```java
public class AreaCalculator {

    // Calculate area of a square
    public double area(double side) {
        return side * side;
    }

    // Calculate area of a rectangle
    public double area(double length, double width) {
        return length * width;
    }

    // Calculate area of a circle
    public double area(int radius) {
        return Math.PI * radius * radius;
    }

    public static void main(String[] args) {
        AreaCalculator calc = new AreaCalculator();
        
        System.out.println("Square area: " + calc.area(5.0));           // 25.0
        System.out.println("Rectangle area: " + calc.area(4.0, 6.0));   // 24.0
        System.out.println("Circle area: " + calc.area(3));             // 28.274...
    }
}
```

