package AbstractFactory;

import Factory.BurgerFactory;

public class burgerMainAbstractFactory {
    public static void main(String[] args) {

        // Cheese Meal
        MealFactory cheeseMealFactory = new CheeseMealFactory();
        Burger cheeseBurger = cheeseMealFactory.createBurger();
        Drink cola = cheeseMealFactory.createDrink();
        System.out.println("Eager Meal: " + cheeseBurger.getBurgerType() + " + " + cola.getDrinkType());

        // Veggie Meal
        MealFactory veggieMealFactory = new VeggieMealFactory();
        Burger veggieBurger = veggieMealFactory.createBurger();
        Drink juice = veggieMealFactory.createDrink();
        System.out.println("Veggie Meal: " + veggieBurger.getBurgerType() + " + " + juice.getDrinkType());
    }
}
