public class Question16 {

}




interface Animals {

    //private void say(); // cannot have access modifier
    void say();

    //public Animals{} // constructor is not allowed


    default void speak() {
        System.out.println("I am an animal");
    }
}

abstract class Vehicle {
    private int speed; // can have access modifier

    abstract void move();

    public Vehicle(int speed) { //constructor allowed
        this.speed = speed;
    }

    void description() {
        System.out.println("I am an vehicle");
    }

}


class cats implements Animals{

    @Override
    public void say() {

    }

    @Override
    public void speak() {
        Animals.super.speak();
    }
}

class SUV extends Vehicle{

    public SUV(int speed) {
        super(speed);
    }

    @Override
    void move() {

    }
}