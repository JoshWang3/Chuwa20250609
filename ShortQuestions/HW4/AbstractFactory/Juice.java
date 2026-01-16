package AbstractFactory;

// concrete product
public class Juice implements Drink{
    @Override
    public String getDrinkType() {
        return "Juice";
    }
}
