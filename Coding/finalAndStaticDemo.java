package Coding;

public class finalAndStaticDemo {
    final int x = 5;
    //x = 10; cannot assign a value to final variable
    //Use case: constants, read-only values
    //A final method cannot be overridden by subclasses.
    //A final class cannot be extended (inherited).
    
    //static Field
    class Counter {
        static int count = 0;

        public Counter() {
        count++;
        }
    }
    // Use case: shared counters, constants, config flags.
    //static Method can be called without creating an instance of the class. But Cannot access instance variables or methods directly.
    
    
    



}
