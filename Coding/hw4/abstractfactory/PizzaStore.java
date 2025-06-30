package hw4.abstractfactory;

public class PizzaStore {
    public static void main(String[] args) {
        PizzaIngredientFactory nyFactory = new NewYorkPizzaIngredientFactory();
        Pizza nyPizza = new Pizza(nyFactory);
        nyPizza.prepare();
        // Output: Pizza with Thin Crust Dough and Marinara Sauce is preparing

        PizzaIngredientFactory chicagoFactory = new ChicagoPizzaIngredientFactory();
        Pizza chicagoPizza = new Pizza(chicagoFactory);
        chicagoPizza.prepare();
        // Output: Pizza with Thick Crust Dough and Plum Tomato Sauce is preparing
    }
}
