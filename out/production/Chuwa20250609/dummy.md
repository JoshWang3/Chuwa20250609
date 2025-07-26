### Short Questions
## Question1
Code examples are in Question1.java
Encapsulation is creating a class, make the important values private. Modifying these values can only be 
done by given method. In my example - bank account: the balance should not be modified directly. Therefore
this value can only be changed when using deposit/withdraw method.

Inheritance inherit its parent class's methods but with some modification when different.
In my example, saving account inherit from bank account. The difference is saving account will 
deduct a 1.25 fee when withdrawing.

Polymorphism has 2 main concepts. One is Overriding. In my saving account, the withdraw method overide its
parent class, this is dynamic polymorphism - redefinition of parent method. The Other one is overloading. In 
my saving account example, withdraw method has another condition when the special user's Fee get waived.
So the parameters are different. this is a method overloading.

## Question2
What is wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class?

The Wrapper Class in Java is the object version of 8 primitive data types in Java: 

int - Integer

double - Double

char - Character

boolean - Boolean

byte - Byte

short - Short

long - Long

float - Float


The reason we need Wrapper class is because we want to use these data types in Java Collections which only accepts Objects.



## Question3
What is the difference between HashMap and HashTable?

HashMap is not-synchronized, meaning can be viewed at the same time, therefore not thread safe.
And therefore it is faster because of no-synchronization. Recommend using it in single-thread apps.

While HashTable is synchronized, thread-safe but slow. Recommend using in multi-thread apps.


## Question4
What is String pool in Java and why we need String pool? Explain String immunity.

String pool is a special memory in Java Heap Area in modern JVM. It stores unique String Literals
The String pool only store unique String literals, so all the same Strings will point to the same address.
The reason we need String pool is because of memory efficiency, string pool saves memory by reusing identical strings.
Its performance is better than creating the same String objects.

String is also immutable, once created, cannot be modified, this ensures thread safe and security.


## Question5
In Java, when an object is no longer reachable, java will remove this object and free memory automatically.
This is automatic, no need to free memory manually like in C.

## Question6
What are access modifiers and their scopes in Java?

In a class, the access modifiers appear in front of variables, like private, public, indicates how these
variable will be access:

Default(when no explicit access modifier) - only visible within the package
Private - only visible within the class
Public - visible everywhere
Protected - visible within the package and all subclasses 

## Question7
Explain final key word? (Filed, Method, Class)

Field: cannot be reassigned after initialization
Method: cannot be overridden in subclass
Class: cannot be inherited


## Question8
Explain static keyword? (Filed, Method, Class). When do we usually use it?

Field: a static variable is shared by all instances of the class. There is only one copy
of the value when the class is initiated no matter how many objects are created.

Method: A static method belongs to the class, not an instance. You can call it without creating an object of the class.

Class: Java does not allow top class to be static, but allowed nested class(class inside another class) to be static,
When a nested class is static, you can create an object of the nested class without creating object for the top class.


## Question9
What is the differences between overriding and overloading?

Both overriding and overloading is polymorphism. 

Overriding is when a subclass override its parent class' method.

Overloading is when a class has multiple same name methods but different parameter lists.


## Question10
Method Signature is Method Name + Parameter types(in order)

Overloading is Same Name with different Parameters:

valid overloading:
```
void print(int a)
void print(String a)
void print(int a, int b)
```

invalid overloading
```
void print(int a)
String print(int a)
```

Overriding is subclass redefines a method with the same method signature of the super class

valid overriding:
```
class Animal{
    void makeSound(){
        System.out.println("animal makes sound")
    }
}

class Cat extends Animal{
    void makeSound(){
        System.out.println("meow")
    }    
}
```

invalid overriding:  -- the method signature is not the same as the super class
```
class Animal{
    void makeSound(){
        System.out.println("animal makes sound")
    }
}

class Cat extends Animal{
    void makeSound(String catSound){
        System.out.println(catSound)
    }    
}
```

## Question11
What is the differences between super and this?

"this" refers to the current object's  stuffs
"super" refers to the super class of the current stuffs

we also have "that", which refers to the other same class object's stuff.


## Question12
Explain how equals and hashCode work.

the default .equals means "==", checking if 2 objects are the same reference
but normally the equal's method will be override to check if 2 objects meaningfully the same:

.equals will check if 2 object/variables are meaningfully the same, this means 2 objects with
the same name and variables will be considered the same, even they are different in hashcode


.hashcode will return an integer for the object. the hashcode determines where the object stores in
a hash-based data structure.


## Question13
What is the Java load sequence?

1. Parent static block (runs once when class is loaded)
2. Child static block (runs once when class is loaded)
3. Memory allocation for new object
4. Parent instance block (runs before constructor, for each object)
5. Parent constructor (runs after instance block)
6. Child instance block (runs before constructor, for each object)
7. Child constructor (runs after instance block)

## Question14 & Question15
Code examples are in Question1.java
Polymorphism has 2 main concepts. One is Overriding. In my saving account, the withdraw method overide its
parent class, this is dynamic polymorphism - redefinition of parent method. The Other one is overloading. In
my saving account example, withdraw method has another condition when the special user's Fee get waived.
So the parameters are different. this is a method overloading.

Encapsulation is creating a class, make the important values private. Modifying these values can only be
done by given method. In my example - bank account: the balance should not be modified directly. Therefore
this value can only be changed when using deposit/withdraw method.


## Question16
| Feature / Aspect         | **Interface**                                             | **Abstract Class**                                             |
| ------------------------ |-----------------------------------------------------------| -------------------------------------------------------------- |
| **Purpose**              | Defines a **contract** (what a class **must** do)         | Defines a **partial implementation** (what a class **can** do) |
| **Methods**              | can have **default** and **static** methods               | Can have **abstract** and **concrete** methods                 |
| **Fields**               | Only **public static final** (constants)                  | Can have **instance variables** (any access modifier)          |
| **Constructors**         | ❌ No constructors                                         | ✅ Can have constructors                                        |
| **Multiple Inheritance** | ✅ Supports multiple interfaces                            | ❌ Cannot extend multiple abstract classes                      |
| **Access Modifiers**     | Methods are `public` by default                           | Methods and fields can have any access modifier                |
| **Inheritance Keyword**  | Use `implements`                                          | Use `extends`                                                  |

