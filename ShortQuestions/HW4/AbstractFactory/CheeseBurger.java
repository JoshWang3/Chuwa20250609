package AbstractFactory;

// concrete product
public class CheeseBurger implements Burger {
    @Override
    public String getBurgerType() {
        return "Cheese Burger";
    }
}
