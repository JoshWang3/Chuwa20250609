package AbstractFactory;

// concrete product
public class Cola implements Drink{
    @Override
    public String getDrinkType() {
        return "Cola";
    }
}
