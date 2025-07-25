public class AnonymousClassDemo {

    public static void main(String[] args) {
        Animal dog = new Animal(){
            @Override
            void makeSound() {
                System.out.println("Woof!");
            }
        };
        dog.makeSound();
    }
    
}
