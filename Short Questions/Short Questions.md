### Short Questions


### Q2. Write code to instantiate at least two instances of above Employee class, use code snippets to explain how these Employee objects are allocated to JVM memory. You may use java reflection utilities to demonstrate it. 

A2: When we create a new object using the new keyword, 
    Java allocates memory for it in the heap.
    Even if two objects have exactly the same values 
    (i.e., they are logically equal), they are stored 
    at different locations in memory. 

This is why their 
    System.identityHashCode() values are different — 
    they are two separate objects in the heap.
    On the other hand, if we assign object2 = object1, 
    then both object1 and object2 refer to the same memory 
    address in the heap. In this case, their identity hash 
    codes are the same, because they are pointing to the same object.



### Q3. Write static utilities in your Employee class, demonstrate how static content differs from others during class instantiation.

A3. I created a static utilities Employee count. It only increases when new employee are created.
    We can see the test results shows that when we fetch the employee count no matter from which
    employee or from the Object itself, we get the same count. 

Therefore, static method content belongs to the class, not to the instance. This means:
    Static fields and methods are shared by all instances
    Static members are initialized only once, when the class is loaded into JVM
    Instance variables are stored in the heap (inside each object), but static variables are stored in method area (meta space)



### Q4. Explain why global variables are NOT recommended, you may use code snippets.

A4. Global variables are not encapsulated, which means they will be able to access and modify by any one. 
This makes code hard to maintain and may cause bugs. Also, when there are multi-thread programs, threads may have 
conflicts on the global variables, leading to race conditions. 

### Q5. Explain why Strings in Java are considered "Immutable"?

A5. Immutable means once String is created, it cannot be modified. The reason is Java is using a final char array
to store as String. final means cannot be changed or modified. Another reason is to increase efficiency, Java will 
store String in a "literal pool", which is in Heap Area, shared by all threads in the form of a hash map. If String
could be changed, their hash value will also be changed, the hash map will no longer working.

### Q6. Write code snippets to explain what does "Final" keyword do, and what we need it?

A6. "final" key word means the value it defined cannot be modified. This means we cannot modify it once we declare it.
It is used for define constant values. The code snippet is in "Questions.java"

### Q7.Write code snippets to explain why Java is "pass-by-value", and why do some people think it might be
"pass-by-reference"?

A7. Java is strickly pass-by-value, including objects. For primitive values, Java pass the value itself. So changing
value in method won't change the original value. For objects, the value passed is a copy of the reference, this means 
methods can modify the objects' content, but cannot change the object the caller refer to.

### Q8. Write code snippets to explain overloading in Java, explain how does Java define method signature

A8. Method Overloading in Java allows multiple methods with the same name but different parameter lists within the same class.
Java determines which method to invoke based on the method signature, which includes: The method name and The parameter types (and order).
 Return type and exception list are not part of the method signature.
