import java.awt.*;

public class Question3 {
    public static void main(String[] args) {

        //Anonymous class for interface
        Car car1 = new Car(){
            @Override
            public String carBrand(){
                return "Toyota";
            }
        };

        System.out.println(car1.carBrand());
        car1.drive();

        //Anonymous class for abstract class
        Contact contact = new Contact(){
            @Override
            String name(){
                return "John";
            }

            @Override
            String number(){
                return "223";
            }
        };

        System.out.println(contact.name() + contact.number());
    }
}


interface Car{

    public String carBrand();
    default void drive(){
        System.out.println("driving a car");
    };
}


abstract class Contact{
    abstract String name();

    String number(){
        return "N/A";
    }

}