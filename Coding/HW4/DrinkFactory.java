
public class DrinkFactory {
    public static Drink getDrink(String type) {
        if (type == null) {
            return null;
        }
        if (type.equalsIgnoreCase("COFFEE")) {
            return new Coffee();
        } else if (type.equalsIgnoreCase("MILKTEA")) {
            return new MilkTea();
        }
        return null;
    }
}
