package Singleton;

public class Burger {
    private String type;
    private boolean hasCheese;

    public Burger(String type, boolean hasCheese) {
        this.type = type;
        this.hasCheese = hasCheese;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean hasCheese() { return hasCheese; }
    public void setHasCheese(boolean has) { this.hasCheese = has; }

    @Override
    public String toString() {
        return (hasCheese ? "Cheese " : " ") + type + " Burger";
    }
}
