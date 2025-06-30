package hw4.q2;

public class CarAndVehicleDemo {
    public static void main(String[] args) {
        Vehicle car = new Car();

        car.start();
        car.drive();
        Vehicle.printInfo();
    }
}
