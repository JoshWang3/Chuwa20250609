### Short Questions
1. Write code to demo:
    - Singleton pattern:
        - Eager Loading:
          class Singleton{
            private final static Singleton instance = new Singleton();
            
            private Singleton(){
            
            }
      
            public static Singleton getInstance(){
                return instance;
            }
          }
          The uniqueness of singleton is guaranteed by two properties:
            - instance is decorated with keyword final
            - there is no setter function, only on instance is created
            - but the instance is created at the time of declare class Singleton, thus even if the instance may not be used ,it takes up some memory space.
        - Lazy Loading:
          public class Singleton
          {

            private Singleton {
              // private constructor
            }

          // Inner class to provide instance of class
            private static class SingletonWrapperClass
            {
                private static final Singleton INSTANCE = new Singleton();
            }

            public static Singleton getInstance()
            {
                return SingletonWrapperClass.INSTANCE;
            }
          }
            The uniqueness is granted by:
            - privatize Singleton constructor, no user accessible method can create more than one instance of singleton class.
            - the class will instantiate a singleton instance when getInstance() is called for the first time.
            - Afterwards, it always returns the same instance.
    - factory method pattern:
        '''
        //interface
        interface Speakable{
            void speak();
        }
        
        //concrete implementations
        class Bird implements Speakable{
            @Override
            public void speak(){
                  System.out.println("A bird chirps");
            }
        }
                
        class Dog implements Speakable{
              @Override
              public void speak(){
                 System.out.println("A dog barks");
              }
        }
            
        //Factory
        public class SpeakableFactory{
            public static Speakable(String type){
                if("Bird".equalsIgnoreCase(type)) return new Bird();
                if("Dog".equalsIgnoreCase(type)) return new Dog();
            }
                
            throw new IllegalArgumentException("Unknown Type");
        }
          '''
     
    -Abstract factory pattern:
    '''
    //abstract product interfaces:
    interface Speakable{
        void speak();
    }

    interface Walkable{
        void walk();
    }
    
    //concrete products:
    class DogSpeak implements Speakable{
        public void speak(){
            System.out.println("A dog says woof");
        }
    }
    
    class DogWalk implements Walkable{
        public void walk(){
            System.out.println("A dog runs");
        }
    }

   class HumanSpeak implements Speakable{
        public void speak(){
            System.out.println("A human says hi");
        }
   }
    
   class HumanWalk implements Walkable{
        public void walk(){
            System.out.println("A human walks.");
        }
    }

    //abstract factory interface
    
    interface AnimalFactory{
        Speakable createSpeakable();
        Walkable createWalkable();
    }
    
    //Concrete factories
    class DogFactory implements AnimalFactory{
        public Speakable createSpeakable(){
            return new DogSpeak();
        }

        public Walkable createWalkable(){
            return new DogWalk();
        }
    }

    class HumanFactory implements AnimalFactory{
        public Speakable createSpeakable(){
            return new HumanSpeak();
        }

        public Walkable createWalkable(){
            return new HumanWalk();
   
        }
    }
    '''

    - Builder pattern:
   '''
    //product class
   class Car{
      String brand;
      String model;

   public Car(String b, String m){
    this.brand = b;
    this.model = m;
   }

   @Override
    public String toString(){
        return this.brand + " " + this.model;
    }
   }
   //builder class
   class CarBuilder{
        private String brand;
        private String model;

    public CarBuilder(){
        brand = "Default Brand";
        model = "Default Model";
    }
    public CarBuilder setBrand(String brand){
        this.brand = brand;
        return this;
    }
    public CarBuilder setModel(String model){
        this.model = model;
        return this;
    }
    public Car build(){
        return new Car(this.brand, this.model);
    }
   }
   public class Main{
        public static void main(String[] args){
            CarBuilder cb = new CarBuilder();
            Car car = cb.setModel("3").setBrand("Tesla").build();
            System.out.println(car);
        }
   }
   '''
   2. Write code to explain how do default and static keywords work in interfaces since java 8.
       - default: Allows an interface to provide a default implementation for a method,so implementing classes doesn't have to
                  define it unless the want to override it.  
      '''   
      public interface Speakable{
         default void greet(){
           System.out.println("Greet.");
         }

         void speak(String content);
      }

       class Human implements Speakable{
            @Override
            public void speak(String content){
                System.out.println("Human says " + content);
            }
        }

       class Dog implements Speakable{
            @Override
            public void greet(){
                System.out.println("Dog says Hi.");
            }

            @Override
            public void speak(String content){
                System.out.println("woof");
            }
        }

        public class Main{
            public static void main(String[] args){
                Speakable h1 = new Human();
                Speakable d1 = new Dog();
                d1.greet();
                d1.speak("I am dog");
                h1.greet();
                h1.speak("nice to meet you");
            }
        }
        '''
      - static: to define utility/helper methods that belong to the interface itself, not to instances.
       public interface dbcon{
           static void log(String message){
                System.out.println("log: "+ message);
            }
       }

3. Write code to demo java anonymouse class, you may write your own POJOs.
   public interface Speakable{
       void speak();
   }

    public class Main{
        public static void Main(String[] args){
             Speakable humanSpeak = new Speakable(){
             @Override
             public void speak(){
                  System.out.println("Human says hello.");
             }
        };

        humanSpeak.speak();
        }
    }

4. Write code to explain Lambda expression with your own functional interface.
   Functional interface is java interface with one and exact one abstract method.
   interface Function {
        abstract int apply(int x, int y);
   }

   public class Main{
        public static void main(String[] args){
            Function addition = (x,y) -> x+y;
            System.out.println(addition.apply(1,2));
        }
   }
  
5. Write a calculator with BiFuntion<T,U,R> and lambda expression. Your calculator shoud support two-number addition, subtraction, multiplication,division operations.
    '''
   import java.util.function.BiFunction;

    public class Main
    {
      public static void main(String[] args) { 
          BIFunction<Integer,Integer,Integer> addition = (a, b)->{ return a+b;};
          BIFunction<Integer,Integer,Integer> mutiplication = (a,b)->{return a*b;};
          BIFunction<Integer,Integer,Integer> substraction = (a,b)->{return a-b;};
          BIFunction<Integer,Integer,Integer> division = (a,b)->{
                if(b == 0) throw IllegalArgumentException;
                return a/b;
          };

        System.out.println(addition.apply(1,2));
        System.out.println(mutiplication.apply(2,3));
        System.out.println(substraction.apply(5,1));
        System.out.println(division.apply(10,2));
    }
}