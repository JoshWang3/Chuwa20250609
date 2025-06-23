# 6.16 HW4 - Design Pattern & Java8

1. Write code to demo the design patterns. Write necessary POJOs together with your core implementation.

   PLEASE DO NOT USE DEMO CODE USED IN CLASS SESSIONS, PLEASE WRITE YOUR OWN CODE.

   1. **Singleton Pattern** (both lazy loading and eager loading). Explain how you would guarantee it is thread-safe (thread-safe in this scenario means: there is truely only one instance in JVM).

      Singleton pattern ensures a class has only one instance, and provide a global access point to it.

      Use case: database connection pool, logger class, configuration manager

      There are ways to implement a singleton pattern:

      - Eager loading: The instance is created once the class is loaded into the JVM. It's simple and thread-safe by nature, because class loading is thread-safe in JVM, but it can waste resources if the instance is never actually used.

        [EagerLoadSingleton.java](../../Coding/singleton/EagerLoadSingleton.java)

      - Lazy loading: The instance is created only if it is needed. t avoids unnecessary resource usage, but raises concerns about thread safety.

        [LazyLoadSingleton.java](../../Coding/singleton/LazyLoadSingleton.java)

        Test code: [SingletonTest.java](../../Coding/singleton/SingletonTest.java)

   2. **Factory Method Pattern**

      Factory method pattern defines an interface for creating **an object**, but let subclasses decide which class to instantiate. It **decouples** object creation from object usage.

       [factory_method](../../Coding/factory_method)

      In the code, the client (PhoneStore) only depends on `PhoneFactory` and `Phone` interface. Concrete object creation is done inside each factory subclass. So new phone types (new concrete factories) can be added without modifying the client code.

   3. **Abstract Factory Pattern** 

      Abstract factory pattern defines an interface to create **families of related objects**.

      [abstract_factory](../../Coding/abstract_factory)

      In the code, the client (DeviceStore) only depends on the `DeviceFactory`, `Phone`, and `Tablet` interfaces. Concrete object creation is done inside each factory subclass (`AppleFactory`, `SamsungFactory`). So when we want to support a new brand like Google, or add new models like iPhone 16, we can simply create new product classes that implement `Phone` and `Tablet` interfaces and create a new factory subclass that implements `DeviceFactory` to produce these products.

   4. **Builder Pattern**

      Builder pattern allows to build complex objects step-by-step. It helps the construction of objects which have many optional fields or complex initialization.

      [builder](../../Coding/builder)

   

2. Write code to explain how do default and static keywords work in interfaces since Java 8.

   `default`: allows interfaces to provide method implementations, and the class implementating the interface can use the default implementation or override it.

   `static`:  belongs to the interface itself and can't be overridden, and can be called using the interface name directly.

   [interface_keywords](../../Coding/interface_keywords)

   

3. Write code to demo Java anonymous class, you may write your own POJOs (e.g. parent abstract classes, child classes, and interfaces)

   Anonymous class is a class without class name and can be declared and instantiated at the same time. Used when we need a quick implementation of an abstract class or interface for **one-time use**.

   [anonymous_class](../../Coding/anonymous_class)

   

4. Write code to explain Lambda expression with your own functional interface.

   [lambda_functional](../../Coding/lambda_functional)

   

5. Write a calculator with BiFunction<T,U,R> (an internal functional interface provided by JDK) and Lambda expression. Your calculator should support two-number addition, subtraction, multiplication,division operations.
   
   [BiFunctionCalculator.java](../../Coding/BiFunctionCalculator.java)

   