package hw4.abstract_factory;

public class GalaxyTab implements Tablet {
    @Override
    public String getBrand() {
        return "Samsung";
    }

    @Override
    public void watchVideo() {
        System.out.println("Watching a video on Galaxy Tab");
    }

    @Override
    public void playGame() {
        System.out.println("Playing a game on Galaxy Tab");
    }
}
