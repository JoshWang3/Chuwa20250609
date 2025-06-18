package AbstractFactory;

// concrete product
public class VeggieBurger implements Burger {
    @Override
    public String getBurgerType() {
        return "Veggie Burger";
    }
}
