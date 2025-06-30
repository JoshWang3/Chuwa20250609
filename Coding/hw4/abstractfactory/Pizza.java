package hw4.abstractfactory;

public class Pizza {
    private final Dough dough;
    private final Sauce sauce;

    public Pizza(PizzaIngredientFactory factory) {
        this.dough = factory.createDough();
        this.sauce = factory.createSauce();
    }

    public void prepare() {
        System.out.println("Pizza with " + dough.getName() + " and " + sauce.getName() + " is preparing");
    }
}
