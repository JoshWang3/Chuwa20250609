package AbstractFactory;

// Concrete Factory
public class VeggieMealFactory implements MealFactory {

    @Override
    public Burger createBurger() {
        return new VeggieBurger();
    }

    @Override
    public Drink createDrink() {
        return new Juice();
    }
}
