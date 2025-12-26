1. <p>Write code to demo</p>
<p>singleton pattern (both lazy loading and eager loading)</p>
Coding/hw4/src/DesignPatterns/Singleton

<p>factory method pattern</p>
Coding/hw4/src/DesignPatterns/Factory

<p>Abstract factory pattern</p>
Coding/hw4/src/DesignPatterns/AbstractFactory

<p>Builder pattern</p>
Coding/hw4/src/DesignPatterns/Builder

<p>Write necessary POJOs together with your core implementation, for your singleton pattern implementation, explain how you would guarantee it is thread-safe (thread-safe in this scenario
means: there is truly only one instance in JVM).</p>
For eager loading singleton, the instance is created before getting instance. For lazy loading singleton, the instance
is created only when getting the instance. Both guarantees thread-safe since threads can only access the exact same instance.


<p>2. Write code to explain how do default and static keywords work in interfaces since Java 8</p>
<p>default methods can be overridden, but static methods cannot be overridden.</p>
Coding/hw4/src/interfaces

<p>3. Write code to demo Java anonymous class, you may write your own POJOs (e.g. parent abstract classes,
   child classes, and interfaces)</p>
Coding/hw4/src/Anonymous

<p>4. Write code to explain Lambda expression with your own functional interface.</p>
Coding/hw4/src/Lambda

<p>5. Write a calculator with BiFunction<T,U,R> (an internal functional interface provided by JDK) and Lambda
   expression. Your calculator should support two-number addition, subtraction, multiplication, division
   operations.</p>
Coding/hw4/src/BiFunctionExercise.java