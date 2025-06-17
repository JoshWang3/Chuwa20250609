import java.util.Objects;

public class Dog extends Pet implements Animal, Mammal {
    private String name;
    private int age;
    private String color;

    @Override
    public void say() {
        System.out.println("I am a Dog");
    }

    @Override
    public void move() {
        System.out.println("I am a Dog");
    }

    @Override
    public void eat() {
        
    }

    @Override
    public void lactation() {
        int id = super.getId();
        System.out.println(id + "is milking");
        
    }

    public int lifeExpectancy() {
        return 15 - age;
    }

    public String lifeExpectancy(int x){
        int expectancy = x - age;
        return String.valueOf(expectancy);

    }

    @Override
    public void companion() {
        System.out.println("I like go out and run");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return age == dog.age && Objects.equals(name, dog.name) && Objects.equals(color, dog.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, color);
    }
}
