package Factory;

public abstract class BurgerFactory {
    // Factory Method
    public abstract Burger createBurger();
}

class CheeseBurgerFactory extends BurgerFactory{
    @Override
    public Burger createBurger() {
        return new CheeseBurger();
    }
}

class VeggieBurgerFactory extends BurgerFactory{
    @Override
    public Burger createBurger() {
        return new VeggieBurger();
    }
}

class ChickenBurgerFactory extends BurgerFactory{
    @Override
    public Burger createBurger() {
        return new ChickenBurger();
    }
}
