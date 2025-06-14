<p> 1. Write a Java POJO (plain old java object) named "Employee", inside Employ class, you should have
1. Employee's name
2. Employee's Date of Birth
3. Employee's Department (it can be another POJO)
4. Employee's Social Security Number
5. Employee's home address (it can be another POJO)
   Override toString method, so that when an Employee object is being printed, the print out looks
   meaningful and readble.
   Override equals method, so that only when two Employees have identical information, we consider they
   are the same employee. </p>

/Coding/hw2/src/employee/

<p> 2. Write code to instantiate at least two instances of above Employee class, use code snippets to explain how
these Employee objects are allocated to JVM memory. You may use java reflection utilities to demonstrate
it. </p>

The employee objects are stored on heap. The fields can share the same value. 
The department and address objects can point to the same address. 

/Coding/hw2/src/employee/Main.java

<p> 3. Write static utilities in your Employee class, demonstrate how static content differs from others during class
   instantiation.</p>

/Coding/hw2/src/employee/Main.java

static variable: the variable(numOfEmployee) is shared by all instances of the class
static method: we can call the method(addEmployee()) without creating an object

<p> 4. Explain why global variables are NOT recommended, you may use code snippets.</p>

Global variables can be modified by other classes. 

/Coding/hw2/src/Global.java
/Coding/hw2/src/GlobalReassign.java

<p> 5. Explain why Strings in Java are considered "Immutable"?</p>
Java stores String literals in a constant pool. When creating a String literal, the JVM first checks if a String with 
same value already stored in the pool.

<p> 6. Write code snippets to explain what does "Final" keyword do, and what we need it? </p>

final variable: When "Final" is assigned to a variable, its value cannot be modified.
For instance, a final variable companyName is used. All the departments are from this company.

final method: the method cannot be overridden

final class: Class Department cannot be inherited

/Coding/hw2/src/employee/Department.java

<p> Write code snippets to explain why Java is "pass-by-value", and why do some people think it might be
   "pass-by-reference"? </p>

pass-by-value: a copy of the value of the argument is passed to the method's parameter.
pass-by-reference: for objects, the address is copied. The attributes can be assigned new values and 
affect the original object.

<p> 8. Write code snippets to explain overloading in Java, explain how does Java define method signature.</p>
/Coding/hw2/src/Calculator.java

Overload is used when we have same name but different number of paraments and different types of parameter 
and return different types, and the order of parameters.

<p> 10. Use Java collection framework datastructures (e.g. Set, Map, List) to solve following Leetcode questions,
    you MUST use Java and you MUST use datastructures provided by Java Collection framework:
1. Top K Frequent Elements（LeetCode 347）
2. Two Sum（LeetCode 1）
   This is a separate coding task other than daily leetcoding questions, please submit your solutions as part
   of this assignment, rather than in the leetcoding sheet. </p>

/Coding/hw2/src/Main
