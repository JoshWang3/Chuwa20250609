package Factory;

public abstract class Burger {
    public abstract String getBurgerType();
}

class CheeseBurger extends Burger {
    @Override
    public String getBurgerType() {
        return "Cheese Burger";
    }
}

class VeggieBurger extends Burger {
    @Override
    public String getBurgerType() {
        return "Veggie Burger";
    }
}

class ChickenBurger extends Burger {
    @Override
    public String getBurgerType() {
        return "Chicken Burger";
    }
}
