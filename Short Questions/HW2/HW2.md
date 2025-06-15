1-3: The code is in file Coding/HW2/src/Employ.java and Coding/HW2/src/TestEmployee.java

2. The class method and data of static field in the class is stored in Method Area, only one instance per class, shared by all objects. The data of non static field stored in Heap, one for each object instance.
3. Static content have only one instance per class, shared by all objects, initiate when creating class, before all constructors. Non-static field is instantiate when object instance created, each object have its own non-static field data.

4. Global variables will access and modified by all threads, which may cause data racing problem. The usage of global variables is hard to track, make the program hard for debugging.

5. a. String literals in Java has String type, Each string literal is stored in String Constant Pool , shared access by all objects and all threads. So it must be immutable.

   b. The inner data field inside String type is declared as final.

6. The code is in file  Coding/HW2/src/TestFinal.java

   final keyword in class declaration: Prevent class extend by another class

   final keyword in object/primitive type variable declaration: For object, prevent reassign that variable to point at a different object. For primitive type, prevent change the value of variable.

7-8. The code is in file Coding/HW2/src/TestParameterAndOverloading.java

 	7. The value of the parameter passed in always not change in the method call, which is pass-by-value. Some think pass-by-reference is because in java all Object like Array is reference type. The content of memory it points to could change, but the variable itself is not changed.
 	8. The method signature is defined by the number, type and order of the method arguments. If one of those is different, the two method have different signature.

10. The code is in file Coding/HW2/src/TopK.java and  Coding/HW2/src/TwoSum.java