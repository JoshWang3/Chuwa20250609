package AbstractFactory;

// Concrete Factory
public class CheeseMealFactory implements MealFactory {

    @Override
    public Burger createBurger() {
        return new CheeseBurger();
    }

    @Override
    public Drink createDrink() {
        return new Cola();
    }
}
