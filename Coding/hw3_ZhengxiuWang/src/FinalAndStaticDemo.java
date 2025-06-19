public class FinalAndStaticDemo {
        /*
    - static: shared variable
    - final: cannot be changed
    - static final: constant
    */
    public static void main(String[] args) {
        Car c1 = new Car("Toyota");
        Car c2 = new Car("Honda");

        System.out.println("Total cars: " + Car.count);
        System.out.println("Max speed: " + Car.MAX_SPEED);
    }
}

class Car {
    public String name;

    // static variable shared by all objects
    public static int count = 0;

    // static final = constant
    public static final int MAX_SPEED = 120;

    public Car(String name) {
        this.name = name;
        count++;
    }
}
