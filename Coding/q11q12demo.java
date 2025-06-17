package Coding;

public class q11q12demo {
    //this refer to current object, common use in ccess current class members (fields, methods, constructors)
    class Person {
        String name;

        public Person(String name) {
            this.name = name; // "this.name" refers to the field, "name" is the parameter
        }
    }

    //super refer to Parent class ,Access superclass fields, methods, or constructors
    class Animal {
        public void makeSound() {
            System.out.println("Animal sound");
        }
    }

    class Dog extends Animal {
        public void makeSound() {
            super.makeSound(); // call parent method
            System.out.println("Bark!");
        }
    }
    
    
    //These two methods come from the Object class, and are essential for comparing objects and using them in collections like HashSet, HashMap, etc.
    //quals() tells collections whether two elements are the same, hashCode() tells collections where to store them
    public static void main(String[] args){
    String a = new String("hello");
    String b = new String("hello");
    System.out.println(a == b);       // false — reference comparison
    System.out.println(a.equals(b)); // true  — value comparison
    }  


}
