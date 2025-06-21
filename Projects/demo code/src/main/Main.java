package main;

import singleton.EagerSingleton;
import singleton.LazySingleton;

import factory.BookFactory;
import factory.Product;

import abstractfactory.*;
import bulider.Computer;

import interfacefeatures.Animal;
import interfacefeatures.Dog;

import lambda.StringTransformer;
import bifunction.Calculator;

public class Main {
    public static void main(String[] args) {
        // Singleton Pattern
        System.out.println("== Singleton Pattern ==");
        EagerSingleton.getInstance().sayHello();
        LazySingleton.getInstance().sayHello();

        // Factory Method Pattern
        System.out.println("\n== Factory Method Pattern ==");
        BookFactory factory = new BookFactory();
        Product book = factory.createProduct();
        book.use();

        // Abstract Factory Pattern
        System.out.println("\n== Abstract Factory Pattern ==");
        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        Chair chair = modernFactory.createChair();
        Table table = modernFactory.createTable();
        chair.sit();
        table.use();

        // Builder Pattern
        System.out.println("\n== Builder Pattern ==");
        Computer computer = new Computer.Builder()
                .setCPU("Intel i7")
                .setRAM("16GB")
                .setStorage("512GB SSD")
                .build();
        computer.printSpecs();

        // Interface Default & Static Methods
        System.out.println("\n== Interface Default & Static Methods ==");
        Dog dog = new Dog();
        dog.sound();         // default method
        Animal.info();       // static method

        // Lambda Expression
        System.out.println("\n== Lambda Expression ==");
        StringTransformer toUpper = s -> s.toUpperCase();
        System.out.println(toUpper.transform("lambda test"));

        // BiFunction Calculator
        System.out.println("\n== BiFunction Calculator ==");
        Calculator.main(null); // 直接调用 Calculator 的 main 方法
    }
}
