### Short Questions

2.  Static Utility vs Instance Behavior

  Employee.printCompany(); // Static method, no need to create object
  System.out.println(Employee.COMPANY_NAME); // Static field

​    

```

```

4.Why Global Variables Are Not Recommended

// Bad Practice
public class Globals {
    public static int counter = 0; // shared globally
}



Global variables break encapsulation, increase coupling, and make debugging hard.



5.Why Strings Are Immutable

String s1 = "hello";
String s2 = s1;
s1 = "world";
System.out.println(s2); // still "hello"



Strings are immutable to ensure security, hashcode consistency, and thread safety.



6.Final Keyword

```
final int x = 10;
// x = 20; // ❌ Error

final class A {}
// class B extends A {} // ❌ Can't extend

class C {
    final void method() {
        System.out.println("Can't override");
    }
}

```

7.Java is Pass-by-Value

```
void modify(int x) { x = 5; }
void changeName(Employee e) { e = new Employee("Bob", null, null, "", null); }

int a = 10;
modify(a); // a is still 10

Employee e = new Employee("Alice", null, null, "", null);
changeName(e); // e still points to original object

```

8.Overloading and Method Signature



```
void print(int x) {}
void print(String x) {} // overload: same name, different parameter list

```

