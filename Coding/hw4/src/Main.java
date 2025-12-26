import Designpatterns.AbstractFactory.*;
import Designpatterns.Builder.Bike;
import Designpatterns.Factory.CircleFactory;
import Designpatterns.Factory.Shape;
import Designpatterns.Factory.ShapeFactory;
import Designpatterns.Factory.SquareFactory;
import Designpatterns.Singleton.EagerSingleton;
import Designpatterns.Singleton.LazySingleton;

public class Main {
    public static void main(String[] args) {
        // eager load
        EagerSingleton.getInstance();

        // lazy load
        LazySingleton.getInstance();

        // factory method
        ShapeFactory circleFactory = new CircleFactory();
        Shape circle = circleFactory.createShape();
        circle.draw();

        ShapeFactory squareFactory = new SquareFactory();
        Shape square = squareFactory.createShape();
        square.draw();

        // abstract factory
        AbstractFactory factory1 = new ConcreteFactory1();
        ProductA productA1 = factory1.createProductA();
        ProductB productB1 = factory1.createProductB();
        productA1.display();
        productB1.show();

        AbstractFactory factory2 = new ConcreteFactory2();
        ProductA productA2 = factory2.createProductA();
        ProductB productB2 = factory2.createProductB();
        productA2.display();
        productB2.show();

        // Builder
        Bike bike = new Bike.Builder().setBrand("Trek").setYear(2024).build();
        System.out.println(bike.getInfo());

    }
}