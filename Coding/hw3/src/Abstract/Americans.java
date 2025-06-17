package Abstract;

public abstract class Americans implements People {
    abstract void park();

    public void speak() {
        System.out.println("Americans speak English");
    }
}
