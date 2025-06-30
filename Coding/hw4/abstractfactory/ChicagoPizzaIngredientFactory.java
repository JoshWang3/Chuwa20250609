package hw4.abstractfactory;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }
    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }
}

