package Animal;

public class Dog extends Animal {
    private String breed;
    private boolean likeRunning;

    public Dog(String name, int age, String breed, boolean likeRunning) {
        super(name, age);
        this.breed = breed;
        this.likeRunning = likeRunning;
    }

    @Override
    public void makeNoise() {
        super.makeNoise();
        System.out.println("Woof! Woof!");
    }

    @Override
    public void displayInfo() {
        System.out.println("I'm a " + breed + " dog, " + "I" + (likeRunning ? "" : "do not") + " like running");
    }

    public void fetchBall() {
        System.out.println("I'm fetching a ball!");
    }
}
