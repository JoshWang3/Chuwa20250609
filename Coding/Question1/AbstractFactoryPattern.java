package Question1;

public class AbstractFactoryPattern {

    public static void main(String[] args) {
        AfternoonTeaFactory factory;

        String afternoonTeaName = "Chinese";

        if (afternoonTeaName.equals("Chinese")) {
            factory = new ChineseAfternoonTea();
        } else if (afternoonTeaName.equals("Western")) {
            factory = new WesternAfternoonTea();
        } else {
            throw new IllegalArgumentException("Invalid afternoonTeaName");
        }

        Dessert dessert = factory.getDessert();
        dessert.prepare();
        dessert.serve();
        Drink drink = factory.getDrink();
        drink.prepare();
        drink.drink();
    }
}

interface AfternoonTeaFactory{
    Drink  getDrink();
    Dessert getDessert();
}

interface Dessert{
    void prepare();
    void serve();
}

class Cookie implements Dessert{
    @Override
    public void prepare() {
        System.out.println("cookie prepare");
    }
    @Override
    public void serve() {
        System.out.println("cookie serve");
    }
}

class Cake implements Dessert{
    @Override
    public void prepare() {
        System.out.println("cake prepare");
    }
    @Override
    public void serve() {
        System.out.println("cake serve");
    }
}

class DessertFactory {
    public Dessert createDessert(String dessertName){
        switch (dessertName){
            case "cookie": return new Cookie();
            case "cake": return new Cake();
            default: throw new IllegalArgumentException("Invalid dessert name");
        }
    }
}

class ChineseAfternoonTea implements AfternoonTeaFactory{
    @Override
    public Drink getDrink() {
        return new Coffee();
    }
    public Dessert getDessert(){
        return new Cake();
    }

}

class WesternAfternoonTea implements AfternoonTeaFactory{
    @Override
    public Drink getDrink() {
        return new Tea();
    }

    @Override
    public Dessert getDessert(){
        return new Cookie();
    }
}
