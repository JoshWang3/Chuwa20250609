1. Write code to demo
    1. singleton pattern (both lazy loading and eager loading),

        * Lazy Loading (Thread-safe) :[Lazy Loading](../../Coding/HW4/src/LazySingleton.java)


        * Eager Loading: [Eager Loading](../../Coding/HW4/src/EagerSingleton.java)

    2. factory method pattern: [Factory Method](../../Coding/HW4/src/Factory.java)

    3. Abstract factory pattern: [Abstract Factory Method](../../Coding/HW4/src/AbstractFactory.java)

    4. Builder pattern: [Builder](../../Coding/HW4/src/Builder.java)

    5. PLEASE DO NOT USE DEMO CODE USED IN CLASS SESSIONS, PLEASE WRITE YOUR OWN CODE.
    6. Write necessary POJOs together with your core implementation, for your singleton pattern implementation, explain how you would guarantee it is thread-safe (thread-safe in this scenario means: there is truely only one instance in JVM).

	    1. Private Constructor: Prevents external instantiation via new.

	    2. Static Accessor: getInstance() controls access to the singleton.

	    3. Double-Checked Locking:

		    * First check avoids locking once the instance is initialized.

		    * Second check inside the synchronized block ensures only one thread initializes the instance.

	    4. volatile keyword:

		    * Prevents JVM instruction reordering during object construction (which could expose a half-constructed object to other threads).

		    * Ensures all threads see the same instance value once it's assigned.

2. Write code to explain how do default and static keywords work in interfaces since Java 8

Starting from Java 8, interfaces in Java can now have:

* default methods – with a method body, can be overridden by implementing classes.

* static methods – also with a method body, but cannot be overridden, and are accessed using the interface name, not the implementing class.

Code: [Interface](../../Coding/HW4/src/InterfaceDemo.java)

3. Write code to demo Java anonymous class, you may write your own POJOs (e.g. parent abstract classes, child classes, and interfaces)

Code: [Anonymous](../../Coding/HW4/src/AnonymousClassDemo.java)

4. Write code to explain Lambda expression with your own functional interface.

Code: [Lambda](../../Coding/HW4/src/LambdaDemo.java)

5. Write a calculator with BiFunction<T,U,R> (an internal functional interface provided by JDK) and Lambda expression. Your calculator should support two-number addition, subtraction,multiplication,division operations.

Code: [BiFunctionCalculator](../../Coding/HW4/src/BiFunctionCalculator.java)
