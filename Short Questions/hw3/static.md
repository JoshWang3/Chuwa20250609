The static keyword is a non-access modifier that indicates that a member (variable, method, or nested class) belongs to the class itself, rather than to any specific instance (object) of the class. Static members can be accessed without creating an object of the class. 
1. static variable: All instances of a class share the single copy of a static variable. It is initialized with the creation of the class and can be accessed directly with class name.
2. static method: A static method cannot directly access non-static variables or use _**this**_ keyword. It can be called directly using the class name.
3. static class: A static nested class can be instantiated without an object of the outer class and can only access the static members of its enclosing outer class, not the instance members.

Usage:
1. Constant: A value that should be fixed and shared across all instances of a class, making it static ensures that there's only one copy in memory.
2. Method: A method performs a general-purpose operation that doesn't rely on the state of a specific object.
3. Class: A nested class that doesn't depend on an instance of the outer class.