package Animal;

public class Cat extends Animal {
    private String breed;
    private boolean isIndoorCat;

    public Cat(String name, int age, String breed, boolean isIndoorCat) {
        super(name, age);
        this.breed = breed;
        this.isIndoorCat = isIndoorCat;
    }

    @Override
    public void makeNoise() {
        super.makeNoise();
        System.out.println("Meow! Meow!");
    }

    @Override
    public void displayInfo() {
        System.out.println("I'm a " + breed + " cat, " + "I " + (isIndoorCat ? "am" : "am not") + " an indoor cat");
    }

    public void messAround() {
        System.out.println("I'm messing around!");
    }
}
