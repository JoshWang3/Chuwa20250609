package ThreadSafeSingleton;

public class Burger {
    private String type;
    private boolean hasCheese;

    public Burger(String type, boolean hasCheese) {
        this.type = type;
        this.hasCheese = hasCheese;
    }

    public String getType() {
        return type;
    }
    public boolean hasCheese() {
        return hasCheese;
    }

    @Override
    public String toString() {
        return (hasCheese ? "Cheese " : "") + type + " Burger";
    }
}
