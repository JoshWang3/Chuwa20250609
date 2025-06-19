# 06/18

1. Singleton Pattern
    
    ```java
    // creates the single instance of a class when the class is loaded
    // thread-safe by default
    // may lead to wasted memory if not used
    class EagerSingleton {
    	private static final EagerSingleton es = new EagerSingleton();
    
      private EagerSingleton() {}
    
      public static EagerSingleton getInstance() {
    	  return instance;
      }
    }
    //creates the single instance of a class when the class is first acccessed (only when needed)
    // need special setting for thread-safe
    class LazySingleton {
    	private static volatile LazySingleton ls;
    
      private LazySingleton() {}
    	//double-checked locking
      public static LazySingleton getInstance() {
    	  if (instance == null) {
    		  synchronized (LazySingleton.class) {
    			  if (instance == null) {
    				  instance = new LazySingleton();
    				}
    		   }
         }
         return instance;
       }
    }
    ```
    
    Factory Method Pattern - an interface for creating an object but lets subclasses decide which class to create an instance. 
    
    ```java
    interface File {
        void open();
    }
    
    class WordDocument implements File {
        public void open() {
            System.out.println("Opening Word document.");
        }
    }
    
    class Image implements File {
        public void open() {
            System.out.println("Opening Image document.");
        }
    }
    
    class FileFactory {
        public static File create(String type) {
            return switch (type.toLowerCase()) {
                case "word" -> new WordDocument();
                case "jpg" -> new Image();
                case "png" -> new Image();
                default -> throw new IllegalArgumentException("Unknown type: " + type);
            };
        }
    }
    ```
    
    Abstract Factory Pattern - provides an interface for creating families of related or dependent objects without specifying their concrete classes. Essentially, it’s a “factory of factories”.
    
    ```java
    interface PaymentFactory { // a set of creation methods
        CreditCard createCard();
        PaymentGateway createGateway();
    }
    interface CreditCard { 
    	void validate(); 
    }
    interface PaymentGateway { 
    	void charge(); 
    }
    
    class VisaFactory implements PaymentFactory {
        public CreditCard createCard() { return new VisaCard(); }
        public PaymentGateway createGateway() { return new VisaGateway(); }
    }
    
    class PayPalFactory implements PaymentFactory {
        public CreditCard createCard() { return new PayPalCard(); }
        public PaymentGateway createGateway() { return new PayPalGateway(); }
    }
    ```
    
    Builder Pattern - ideal for constructing complexing or immutable objects with my optional parameters. It improves readability, reduces constructor overload, and aligns with clean code and SOLID principles (single responsibility principle - SRP, open/closed principle, liskov substitution, interface segregation, dependency inversion). 
    
    ```java
    class Employee {
        private String name;
        private String ssn;
        private LocalDate dateOfBirth;
       
    
        public Employee(Builder builder) {
            this.name = builder.name;
            this.ssn = builder.ssn;
            this.dateOfBirth = builder.dateOfBirth;
        }
        
        public static class Builder {
    	    private String name;
    	    private String ssn;
    	    private LocalDate dateOfBirth;
    	    public Builder setName(String name) {
            this.name = name;
            return this;
    	    }
    	    public Builder setSsn(String ssn) {
            this.ssn = ssn;
            return this;
    	    }
    	    public Builder setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
    	    }
    	    
    	    public Employee build() {
    		    return new Employee(this);
    	    }
        }
        public String toString() {
            return "Employee{" +
                    "name: '" + name + '\'' +
                    ", ssn: '" + ssn + '\'' +
                    ", date of birth: " + dateOfBirth +
                    ", address: " + address.toString() +
                    ", department: " + department.toString() +
                    '}';
        }
    }
    ```
    
2. `default` in an interface provides a default implementation for a method, which can be optionally overridden. `static` allows to call methods without initialize an object. 
    
    ```java
    interface Animals {
    	default void eat() {
    		System.out.println("It will eat food.");
    	}
    	static void printInfo() {
    		System.out.prinlnt("The animal info is here");
    	}
    }
    
    public class Dog implements Animals {
    	public Dog() {};
    	@Override 
    	public void eat() { //override default method
    		System.out.println("It will eat bones");
    	}
    	
    	public static void main(String[] args) {
    		Dog d = new Dog();
    		d.eat(); // overriden default method
    		Animals.printInfo(); //static
    	}
    }
    ```
    
3. The anonymous class in Java is a type of inner class without a name used to create a class that implements an interface, extends parent class, or as a method argument without defining a separate class. 
    
    ```java
    abstract class Fish {
    	private String name; 
    	
    	Fish(String name) {
    		this.name = name;
    	}
    	String getName() { 
    		return name;
    	}
    	void setName(String name) { 
    		this.name = name;
    	}
    	
    	void printInfo(String name) {
    		System.out.println("This fish is: " + name);
    	}
    	abstract void eat();
    }
    
    interface Animals {
    	void sound();
    }
    
    class Whale extends Fish implements Animals{ 
    	public Whale(String name) {
    		super(name);
    	}
    	
    	@Override
    	void eat() {
    		System.out.println("eat fish");
    	}
    	
    	@Override
    	public void sound() {
    		System.out.println("hmmm");
    	}
    }
    
    class Main {
    	public static void main(String[] args) {
    		Whale w = new Whale("yoyo") { //anonymous class implements Whale
    			@Override
    			void eat() {
    				System.out.println("eat eat");
    			}
    		};
    		w.eat();
    		
    		Animals a = new Animals() { //anonymous class implements Animals
    			@Override
    			public void sound() {
    				System.out.println("yaya");
    			}
    		};
    		a.sound();
    	}
    }
    ```
    
4. Lambda expression is a shorter way to implement a functional interface. Functional interface is an interface that only has one abstract method. In this example, defined custom functional interface, `CustomComparator` with a method `compare()`. `lenComparator` is a lambda expression to implement the comparator interface. 
    
    ```java
    @FunctionalInterface
    interface CustomComparator<T> {
        int compare(T o1, T o2);
    }
    
    public class CompareStringLength {
        public static void main(String[] args) {
            // Lambda expression 
            CustomComparator<String> lenComparator = (s1, s2) -> s1.length() - s2.length();
    
            System.out.println(lenComparator.compare("sun", "moon")); // -1
            System.out.println(lenComparator.compare("black", "red"));     // 1
        }
    }
    ```
    
5. Code
    
    ```java
    import java.util.function.BiFunction;
    
    public class Calculator {
        public static void main(String[] args) {
            BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
            BiFunction<Integer, Integer, Integer> subtract = (a, b) -> a - b;
            BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
            BiFunction<Integer, Integer, Integer> divide = (a, b) -> {
                if (b == 0) throw new ArithmeticException("Invalid divisor");
                return a / b;
            };
    
            int m = 30;
            int n = 5;
    
            System.out.println("Addition:       " + add.apply(x, y));
            System.out.println("Subtraction:    " + subtract.apply(x, y));
            System.out.println("Multiplication: " + multiply.apply(x, y));
            System.out.println("Division:       " + divide.apply(x, y));
        }
    }
    ```