public class Question11 {

}

class Animal{
    private String name;
    public Animal(String name){
        this.name = name;
    }
    public void speak(){
        System.out.println("Animal.speak()");
    }
}

class Cat extends Animal{

    public Cat(String name) {
        super(name);
    }

    public void speak(){
        super.speak();
        System.out.println("Cat.speak()");
    }
}
