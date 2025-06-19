Code is at directory Coding/HW4/src

1.  
   1.  Singleton pattern demo code is at TestSingleton.java, for class EagerSingleton and LazySingleton
   2. Factory method pattern demo code is at TestFactoryMethod.java, for method getFish() in class FishFactory and GoldfishFactory
   3. Abstract factory pattern demo code is at TestAbstractFactory.java, for class Factory and interface BasicFactory
   4. Builder pattern demo code is at TestBuilder.java, for class Student.Builder
      6. The thread safety is guaranteed by the instance is declared private static final, and all Singleton class have private constructors. So only one instance will create when the class loading, no other instance is possible to create after that.
2. Code is at TestInterface.java . default keyword is for default implementations, the class implement the interface do not have to implement that. static keyword is for the method belong to the interface type, not the actual class implement the interface. call by InterfaceName.StaticMethod
3. Code is at TestAnonymous.java
4. Code is at TestLambda.java
5. Code is at TestCalculator.java