# HW2
1. 
Encapsulation:
```
public class department {
    private int departmentId;
    public String name;
    private String departmentContact;
    
    public getContact() {
        return departmentContact;
    }
    
    public setContact(String contact) {
        this.departmentContact = contact;
    }
}
```
Basically important data fields are private and can only be accessed via getters and setters.

Polymorphism:
By definition polymorphism allows objects to behave differently based on their specific class type. There are two type of polymorphism:
Compile-time polymorphism:
also known as method overloading
```
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }
}
```

Runtime polymorphism:
also known as method overriding
```
class Animal {
    public void speak() {
        System.out.println("Animal speak");
    }
}

class Dog extends Animal {
    @Override
    public void speak() {
        System.out.println("Dog bark");
    }
}

class Cat extends Animal {
    @Override
    public void speak() {
        System.out.println("Cat Meow");
    }
}

public static void main(String[] args) {
    Animal myDog = new Dog();
    Animal myCat = new Cat();

    myDog.speak();  // Output: Dog bark
    myCat.speak();  // Output: Cat Meow
}
```
The speak function performs differently although myDog and myCat are both of class Animal

Inheritance:
The above code also shows inheritance.
The Dog class and Cat class are allowed to inherit the features(fields and methods) of animal class

2. Wrapper classes in Java are object representations of the primitive data types.
There are several reasons why we need wrapper class in Java:
1) Java collections like HashMap cannot store primitives data types, instead they require objects.
2) Wrapper classes can hold a null value but primitive data types cannot

3. HashMap is not thread-safe while hashTable is thread safe. HashMap has much better perforrmance than HashTable, HashTable is nearly aborted
4. In order to make memory efficient, the same string will point to the same memory location in heap, which avoids creating duplicate string objects.
String immutability means once a String is created, its cannot be modified.
5. Garbage Collection is Java's automatic process to delete object that are no longer reachable
Types of garbage collector:
Serial GC focus on Simplicity, has high pause time
Parallel GC	focus on Throughput	and has Medium-High pause time
CMS (legacy) focus on Low pause	and has Low pause time
G1 GC focus on Balance and has Low-Medium pause time
ZGC focus Sub-ms pause and has Very Low pause time
Shenandoah GC focus Predictable pause and has Very Low pause time

6. private, public, protected and default
Private make data only visible in same class
Default make data only visible in same package
Protected make data only visible in same package and any subclasses of the class
Public make data accessible anywhere
7. final is used to create constants in Java, or more precisely, ensuring immutability, if it's used on a variable, the variable cannot be modified after creation, if it's used on a method, this means the method cannot be override, if it's used on a class, the class cannot be extended
8. static keyword make the field/method/class belong to the class
Static fields has only one copy and belong to the class, can be accessed without creating an instance
Static methods also belong to the class, and can be accessed directly without creating an instance of the class
For Static class, only inner class can be static, the class can be created without the outside class instance
9. Already Explained in question 1 polymorphism part
overriding means a subclass provides implementation of a method defined in its superclass
overloading means in the same class there are multiple functionns with the same name but different parameter list\
10. Method signature includes Method name,number, types, and order of parameters
Method signature allows compile to select the correct method
Method signature allows Java to standardize the format of overriding (strictly the same method signature)
11. this refers to the current class while super refers to the parent class
12. ```equals``` check if two object are logically equal. It's default implementation is ==, which might be override in some class
```hashCode``` returns an integer hash code that helps Java locate objects. Objects that ```equals``` return true must have the same hashCode
13. Loading, linking and initialization
14. 
Method Overloading in Java is handled by compiler using method signatures
Method Overriding in Java is handled by JVM using dynamic method dispatch
15. Encapsulation is basically hiding internal data and implementation details and make the data only accessible through given methods (getter and setter)
It's purpose is to prevent unauthorized or invalid access for data security and as a good programming habit reduce the chance of unaware data modifications
16. Interface defines a contract while abstract class defines a base class. Interface has no constructor while abstract class can have constructor.
A class can implement multiple interface but can only extends one abstract class.
Mostly, abstract class defines a strict is-a relationship
Interface are used more to define contract among unrelated classes