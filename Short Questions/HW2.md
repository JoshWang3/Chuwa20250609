**1. Write a Java POJO (plain old java object) named "Employee", inside Employ class, you should have**
 1. Employee's name
 2. Employee's Date of Birth
 3. Employee's Department (it can be another POJO)
 4. Employee's Social Security Number
 5. Employee's home address (it can be another POJO)
 Override toString method, so that when an Employee object is being printed, the print out looks 
meaningful and readble.
 Override equals method, so that only when two Employees have identical information, we consider they 
are the same employee.

```java
class Employee{
    private String name;
    private String dateOfBirth;
    Department dept;
    private Long ssn;
    private Address address;

    //Static variables
    private static int employeeCount = 0;

    public Employee(){}
    public Employee(String name, String dateOfBirth, Department dept, Long ssn, Address addr){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dept = dept;
        this.address = addr;
        this.ssn = ssn;
        employeeCount++;
    }   

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Department getDept() {
        return dept;
    }

    public String getName() {
        return name;
    }

    public Long getSsn() {
        return ssn;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        // System.out.println("Employer: " + name);
        // System.out.println("Date of Birth: " + dateOfBirth);
        // System.out.println("Department: " + dept.getDeptName());
        // System.out.println("Social Security: " + ssn);
        // System.out.println("Address: " + address);

        String str = "Employer: " + name + "\n" 
                    + "Date of Birth: " + dateOfBirth + "\n"
                    + "Department: " + dept.getDeptName() + "\n"
                    + "Social Security: " + ssn + "\n"
                    + "Address: " + address;
        return str;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Employee e1 = (Employee) obj;
        if(name.equals(e1.getName()) && 
            dateOfBirth.equals(e1.getDateOfBirth()) && 
            ssn.equals(e1.getSsn())) return true;

        return false;
    }

    public static void printEmployeeInfo(Employee e){
        System.out.println(e);
    }

    public static int getEmployeeCount() {
        return employeeCount;
    }
    
}

public class Department {
    private String deptName;
    private int deptNumber;

    public Department(){}
    public Department(String name, int number){
        this.deptName = name;
        this.deptNumber = number;
    }

    public String getDeptName() {
        return deptName;
    }

    public int getDeptNumber() {
        return deptNumber;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setDeptNumber(int deptNumber) {
        this.deptNumber = deptNumber;
    }   
}

public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;

    public Address(String street, String city, String state, String zipCode){
        this.street = street;
        this.city = city;
        this.state = state; 
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " " + zipCode;
    }
}

```
**2. Write code to instantiate at least two instances of above Employee class, use code snippets to explain how 
these Employee objects are allocated to JVM memory. You may use java relection utilities to demonstrate 
it**

```java
public class App {
    public static void main(String[] args) {
        //Department and Address objects in Heap
        //dept and addr are reference variables in Stack
        Department dept = new Department("Engineering", 01);
        Address addr = new Address("123 ABC", "DEF Rd", "CA", "95050");
        
        //Employeee object in Heap
        //e1 and e2 are reference variables in Stack
        Employee e1 = new Employee("Amy Bill", "03-04-1990", dept, 123456789L, addr);
        Employee e2 = new Employee("Bill Carlson", "04-05-1990", dept, 234567891L, addr);

        //Print Employee info by using static method
        // System.out.println(e1);
        // System.out.println(e2);
        Employee.printEmployeeInfo(e1);
        Employee.printEmployeeInfo(e2);

        //Print Employee count number by using static variable EmployeeCount
        System.out.println("Total number of employees: " + Employee.getEmployeeCount());  
    } 
}

```

 **3. Write static utilities in your Employee class, demostrate how static content differs from others during class instantiation.**

```java

//In Employee class

class Employee{
    rivate String name;
    private String dateOfBirth;
    Department dept;
    private Long ssn;
    private Address address;

    //Static variables
    private static int employeeCount = 0;

    public Employee(){}
    
    public Employee(String name, String dateOfBirth, Department dept, Long ssn, Address addr){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dept = dept;
        this.address = addr;
        this.ssn = ssn;
        employeeCount++;
    }   

    //static Method to get the number of employee
    public static int getEmployeeCount() {
        return employeeCount;
    }
}

// In the main method
        //Print Employee count number by using static variable EmployeeCount
        System.out.println("Total number of employees: " + Employee.getEmployeeCount());
```

 **4. Explain why global variables are NOT recommended, you may use code snippets.**

   Global variables are generally discouraged because they can introduce several issues such as concurrency problems, reduced code readability, debugging difficulties, and unintended changes.

    1. Concurrency Issues:
   In multi-threaded programs, global variables are shared across threads. This can lead to race conditions, where multiple threads simultaneously read and write to the same variable, resulting in unpredictable behavior or inconsistent results.

```java
public class GlobalVariableConcurrency {
    static int sharedCounter = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedCounter++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedCounter++;
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Counter Value: " + sharedCounter); // Value may not be 2000
    }
}

```
2. Reduced Code Readability:

Global variables can be modified from anywhere in the program, making it difficult to trace where and how their values are being updated. This reduces the clarity of the program's logic and makes it harder to understand.

3. Debugging Difficulties:

Since global variables can be accessed and modified by multiple parts of the program, tracking down bugs becomes challenging. It’s often unclear which part of the code is responsible for an unexpected change in the variable's value.

4. Unintended Changes:

Global variables are accessible throughout the program, which increases the risk of accidental modification. This can lead to subtle bugs, especially when different parts of the program have conflicting assumptions about the variable's state.


**5. Explain why Strings in Java are considered "Immutable"?**
    
    1. String Pool Optimization: When a String is created, it is stored in a constant string pool. Before creating a new String, Java first checks if the same String already exists in the pool. If it does, Java simply returns a reference to the existing instance instead of creating a new one. This optimization is possible because Strings are immutable, so sharing references is safe and doesn’t lead to unexpected changes.

    2. Thread Safety: Since Strings are immutable, they are inherently thread-safe. This means that multiple threads can access the same String instance at the same time without needing synchronization, as the String cannot be modified by any thread.

    3. Unchanging State: Once a String is created, its state cannot be changed. Any operation that seems to modify a String (like concatenation) actually creates a new String object instead of altering the original.



 **6. Write code snippets to explain what does "Final" keyword do, and what we need it?**

| Context    | Effect                                                                 | Why do we need it?                                                                   |
| ---------- | ---------------------------------------------------------------------- | ------------------------------------------------------------------------------------ |
| Variables  | Value cannot be changed after initialization.                          | To create constants and ensure values remain unchanged during program execution.     |
| Methods    | Cannot be overridden in subclasses.                                    | To prevent alteration of critical methods in derived classes.                        |
| Classes    | Cannot be inherited.                                                   | To restrict subclassing when a class is complete or for security purposes.           |
| References | Reference cannot be reassigned, but the object's state can be altered. | To ensure the reference remains constant while allowing modifications to the object. |


```java
//Variables
public class FinalVariableExample {
    public static void main(String[] args) {
        final int MAX_VALUE = 100; // Constant
        System.out.println("MAX_VALUE: " + MAX_VALUE);

        // MAX_VALUE = 200; // Error: cannot assign a value to final variable
    }
}
```
```java
//Method
class Parent {
    public final void displayMessage() {
        System.out.println("This is a final method.");
    }
}

class Child extends Parent {
    // @Override
    // public void displayMessage() { // Error: cannot override the final method
    //     System.out.println("Trying to override.");
    // }
}
```


```java
//final class
final class FinalClass {
    public void showMessage() {
        System.out.println("This is a final class.");
    }
}

// class Child extends FinalClass { // Error: cannot inherit from final class
// } 
```


```java
//final reference
class FinalReferenceExample {
    public static void main(String[] args) {
        final StringBuilder sb = new StringBuilder("Hello");
        System.out.println(sb);

        sb.append(", World!"); // Allowed: modifying the object
        System.out.println(sb);

        // sb = new StringBuilder("New Object"); // Error: cannot assign a new reference
    }
}
```


 **7. Write code snippets to explain why Java is "pass-by-value", and why do some people think it might be "pass-by-reference"?**


 **8. Write code snippets to explain overloading in Java, explain how does Java define method signature.**


 **9.  Use Java collection framework datastructures (e.g. Set, Map, List) to solve following Leetcode questions, 
you MUST use Java and you MUST use datastructures provided by Java Collection framwork:**

   **1. Top K Frequent Elements（LeetCode 347）**

   **2. Two Sum（LeetCode 1）**
