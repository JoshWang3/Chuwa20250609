package hw4.q2;

public class Car implements Vehicle{
    @Override
    public void drive() {
        System.out.println("Car is driving");
    }

    @Override
    public void start() {
        System.out.println("Car is starting");
    }

}
