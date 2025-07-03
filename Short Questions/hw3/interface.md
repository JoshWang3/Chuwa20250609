Interface:
1. Defines a contract or a set of behaviors that classes must implement.
2. Can only have method signatures (abstract methods), and default/static methods with implementation.
3. Can only have public, static, and final constants.
4. A class can implement multiple interfaces.
5. Cannot be instantiated directly.
6. Cannot have constructors.

Abstract Class:
1. Provides a common base for related classes, with/without some shared implementation details. It defines an "is-a" relationship.
2. Can have both abstract methods (without implementation) and concrete methods (with implementation).
3. Can have instance variables with any access modifier and are mutable.
4. A class can only extend one abstract class (single inheritance).
5. Cannot be instantiated directly.
6. Can have constructors used by subclasses.