1. 
1) Refer to lazySingletonTester and eagerSingletonTester
2) refer to factory
3) refer to abstractFactory
4) refer to builderDemo

2. Before java 8, interface can only have abstract method, after Java 8, default methods are allowed.
```
interface Animal {

    void makeSound();

    // default method: provides implementation, can be inherited or overridden
    default void breathe() {
        System.out.println("Animal is breathing");
    }

    // static method: belongs to the interface itself, cannot be inherited
    static void printType() {
        System.out.println("This is an Animal");
    }
}
```
3. 
```
interface Height {
    int x = 21;
    void getHeight();
}

public void main(String[] args) {
    Height a = new Height() {
        @Override
        public void getHeight() 
        {
            System.out.print("Age is " + x);
        }
    }
}
```
In short anonymous class is a local, unnamed class used to provide a one-time implementation of an interface or abstract class

4. 
```
@FunctionalInterface
interface InstrumentPlayer {
    void play(String instrumentName);
}

public static void main(String[] args) {
    InstrumentPlayer player = (instrumentName) -> {
        System.out.println("Playing: " + instrumentName);
    };

    player.play("Violin");
    player.play("Saxophone");
}
```

5. 
```
BiFunction<Double, Double, Double> add = (a, b) -> a + b;
BiFunction<Double, Double, Double> subtract = (a, b) -> a - b;
BiFunction<Double, Double, Double> multiply = (a, b) -> a * b;
BiFunction<Double, Double, Double> divide = (a, b) -> {
    if (b == 0) {
        throw new ArithmeticException("Division by zero");
    }
    return a / b;
};
```