<p>1. Write up Example code to demonstrate the three fundamental concepts of OOP.</p>
a. Encapsulation;
/Coding/hw3/src/Encapsulation

b. Polymorphism;
/Coding/hw3/src/Inheritance (getArea() overrides the method in Shape)
/Coding/hw3/src/Polymorphism

c. Inheritance;
/Coding/hw3/src/Inheritance

<p>2. What is wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class?</p>
Wrapper classes provide a way to use primitive data types (int, boolean, etc..) as objects.
It's useful when working with Collection objects(List, Stack, ...)

<p>3. What is the difference between HashMap and HashTable?</p>
HashMap allows one null key and multiple null values but hashtable does not allow any null keys or values.
HashMap is not thread-safe but hashtable is thread-safe.

<p>4. What is String pool in Java and why we need String pool? Explain String immunity.</p>
String pool stores string literal. When creating a new string literal the JVM first checks
if a String with a same value already exists in the pool. Once string is created, it is immutable.

<p>5. Explain garbage collection? Explain types of garbage collection.</p>
<p>Garbage collection is an automatic memory management process that helps Java programs run efficiently.</p>
Minor or incremental Garbage Collection (GC): This occurs when unreachable objects in the Young Generation heap memory are removed.
Major or Full Garbage Collection (GC): This happens when objects that survived minor garbage collection are removed from the Old Generation heap memory.

<p>6. What are access modifiers and their scopes in Java?</p>
<p>default modifier: declarations are visible only within the package</p>
<p>private modifier: declarations are visible within the clsss</p>
<p>protected modifier: declarations are visible within the package or all subclasses</p>
<p>public modifier: declarations are visible everywhere</p>

<p>7. Explain final key word? (Field, Method, Class)</p>
<p>final field: once instantiated, the value cannot be modified.</p>
<p>final method: the method cannot be overridden</p>
<p>final class: the class cannot be inherited.</p>

<p>8. Explain static keyword? (Field, Method, Class). When do we usually use it?</p>
<p>Static field: shared by all instances of the class</p>
<p>static method: the method can be called without creating an object</p>
<p>static class: for nested class, only inner class can be static</p>

<p>9. What is the differences between overriding and overloading?</p>
<p>Override: child class methods overrides the methods from parent class,
the methods should have same name, same parameters and same return type.</p>

<p>Overload: methods have same name, different number of parameters, different types of
parameter, different order of parameters and different returns.</p>

<p>10. Explain how Java defines a method signature, and how it helps on overloading and overriding.</p>

Java defines a method signature with method name, parameter types, number of parameters and return type.
<p>Override: child class methods overrides the methods from parent class,
the methods should have same name, same parameters and same return type.</p>

<p>Overload: methods have same name, different number of parameters, different types of
parameter, different order of parameters and different returns.</p>

<p>11. What is the differences between super and this?</p>
super is used to call parent class's constructor and this is used in current class.

<p>12. Explain how equals and hashCode work.</p>
The equals() method determines if two objects are logically equivalent, while hashCode() provides a 
hash value (an integer) that represents the object's state. If two objects have the same hashCode(), 
they are not necessarily equal. But if two objects are equal, they have the same hashcode.

<p>13. What is the Java load sequence?</p>
Loading, linking, initialization

<p>14. What is Polymorphism ? And how Java implements it ?</p>
Performing a single action in different ways. 
Override: child class methods overrides the methods from parent class, 

Overload: within the same class, methods have same name, different number of parameters, different types of
parameter, different order of parameters and different returns.

<p>15. What is Encapsulation ? How Java implements it? And why we need encapsulation?</p>
The binding of data and methods that manipulate them into a single unit such that the sensitive data
is hidden from the users. Getter/setter methods are used to set and get object's field value.

/Coding/hw3/src/Encapsulation

<p>16. Compare interface and abstract class with use cases.</p>
Interface contains the method signatures without implementation and Default/static methods with implementation.
Abstract class can have method with implementation and method signatures without implementation.

/Coding/hw3/src/Abstract

