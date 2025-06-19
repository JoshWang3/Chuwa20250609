class Fish{
    public void printName(){
        System.out.println("I am Fish");
    }
}

class Goldfish extends Fish{
    @Override
    public void printName(){
        System.out.println("I am Goldfish");
    }
}

class FishFactory{
    public Fish getFish(){
        return new Fish();
    }
}
class GoldfishFactory extends FishFactory{
    public Goldfish getFish(){
        return new Goldfish();
    }
}

public class TestFactoryMethod {
    public static void main(String[] args) {
        FishFactory factory1 = new FishFactory();
        Fish fish1 = factory1.getFish();
        fish1.printName();
        FishFactory factory2 = new GoldfishFactory();
        Fish fish2 = factory2.getFish();
        fish2.printName();
    }
}