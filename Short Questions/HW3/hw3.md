All code could be found in directory Coding/HW3/src

1.  Code is in file TestPerson.java

   1.1 Encapsulation means that the data must be accessed in provided method. The name in class Person and the salary in class Employee is private, and public getter/setter is defined. This illustrate encapsulation.

   1.2 Polymorphism is using a same method name to access different forms of method. It contains overloading (like constructor Person) and overriding (like method describe).

   1.3 Inheritance is about a subclass extend a superclass and inherit all its fields. It represent is-a relationship. Class Employee extends Person is a form of Inheritance

2. Code is in file TestWrapper.java

   The wrapper data type is class type that hold the underlying data of primitive type. We need wrapper class because the template like class A< T >  , ArrayList< T > only accept class type, but not primitive type.

3. Code is in file TestHashmapAndHashtable.java

   Both two classes is like a hashtable data structure, and implement the Map<K,V> interface. The difference is that HashMap is a newer api, so it it recommended to use. HashMap could allow null key, while Hashtable disallow.

4. Code is in file TestString.java

   String Pool in java is a special area in JVM memory that store string literal. The String Pool is needed for effective storing the string literal and allow them sharing over threads. Because the string literal need to be shared by all classes and threads, it must remain immutable. It's underlying data is declared final.

5. Garbage collection is JVM recycle the memory occupied by objects no longer used. There are multiple types, like Serial GC, Parallel GC, CMS, G1 GC, ZGC, Shenandoah GC, Epsilon GC. It need to be specified when launching JVM, like `java -XX:+UseG1GC -Xms4g -Xmx4g -jar myapp.jar` . Default is G1 GC.

6. Access modifiers is public, protected, default, private. 

   public: allow using everywhere

   protect: allow using within same package, and subclass in different package.

   default: allow using within same package

   private: allow using inside same class

   In TestPerson.java there are field of all four kinds.

7. Code is in TestPerson.java

   final keyword is about:

   field: Not allow change the value about primitive type, or reference it point to about object type. (like Person.language)

   method: Not allow subclass overriding the method (like Person.finalMethod)

   class: Not allow create subclass extends the class(like Employee)

8. Code is in TestPerson.java

   static keyword is about method/field/class belongs to class, not belongs to object. Each class only have one copy of static method/field/class, accessible using ClassName.StaticField   . Example of static method: Employee.getCounter

   Example of static field: Person.counter

   Example of static class: Person.InnerClass

9. 10 . Code is in TestPerson.java

   Method signature is determined by method's parameter list, including parameter type, number, order.  In one class only one implementation of a specific method signature is allowed.

   Overriding is in subclass,  re declaring a method of same signature in superclass to change its content(like method describe). Overloading is declaring a method with same name but different signature(like constructor Person)

   

   11. Code is in TestPerson.java

       super is used when referencing a field in its superclass (class extends from). this is used when referencing a field in the own class. Constructor of Person and Employee shows the usage of super and this.

   12. Code is in TestEqualsAndHashCode.java

       equals and hashCode is method defined in class Object, all classes could overriding the method to provide the comparation based on the content. If the content is same, the equals should return true and the HashCode should be same.

   13. Code is in TestLoadSequence.java

       Load Sequence is the order of fields loading when class/object initialized. The order is:

       When Initializing Class:

       1.initialize all static fields and static blocks in their declaration order

       When Initializing/new Object:

       2.Initialize all non-static fields

       3.Call constructor

   14. Code is in file TestPerson.java

       Polymorphism is using a same method name to access different methods. It contains overloading (like constructor Person) and overriding (like method describe).Overriding is in subclass,  re declaring a method of same signature in superclass to change its content(like method describe). Overloading is declaring a method with same name but different signature(like constructor Person)

   15. Code is in file TestPerson.java

       Encapsulation means that the data must be accessed in provided method. Java provide access modifiers to control the means to access the data. The name in class Person and the salary in class Employee is private, and public getter/setter is defined. This illustrate encapsulation.Encapsulation is needed for ensure data not unexpectedly modified, and for easy defining the ways to use the data.

   16. Code is in file TestAbstractAndInterface.java

       The difference between abstract class and interface is:

       1. A class could only extend one abstract class, but allow to implement multiple interface
       2. Abstract class could have abstract method, and concrete method which have implementation, and non static variables. Interface typically only have abstract method and static constants, no implementation of method or non-static fields allowed (exclude the default method in Java 8)
       3. Abstract class could have all access modifiers, the field in interface is public typically and by default
       4. Abstract class could have constructors, the interface could not.
       5. The new keyword could used on abstract class, not interface.

       The usage of abstract class and interface is in TestAbstractAndInterface.java

       

       

   