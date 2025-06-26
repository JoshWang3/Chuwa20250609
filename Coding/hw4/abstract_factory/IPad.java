package hw4.abstract_factory;

public class IPad implements Tablet {
    @Override
    public String getBrand() {
        return "Apple";
    }

    @Override
    public void watchVideo() {
        System.out.println("Watching a video on iPad");
    }

    @Override
    public void playGame() {
        System.out.println("Playing a game on iPad");
    }
}
