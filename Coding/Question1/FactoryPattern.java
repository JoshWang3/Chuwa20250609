package Question1;

public class FactoryPattern {
    public static void main(String[] args){
        System.out.println("=====with factory======");
        DrinkFactory drinkFactory = new DrinkFactory();

        Drink coffee = drinkFactory.createDrink("Question1.Coffee");
        coffee.prepare();
        coffee.drink();


        System.out.println("=====without factory======");
        //instead of doing it:
        Drink coffee2 = new Coffee();
        coffee2.prepare();
        coffee2.drink();
    }
}

interface Drink{
    void prepare();
    void drink();
}

class Coffee implements Drink{
    @Override
    public void prepare() {
        System.out.println("Question1.Coffee prepared");
    }

    @Override
    public void drink(){
        System.out.println("Drinking Question1.Coffee");
    }
}

class MochaCoffee extends Coffee {

    @Override
    public void prepare(){
        System.out.println("Question1.MochaCoffee prepared");
    }

}

class Tea implements Drink{
    @Override
    public void prepare() {
        System.out.println("Question1.Tea prepared");
    }

    @Override
    public void drink(){
        System.out.println("Drinking Question1.Tea");
    }
}

class DrinkFactory {
    public Drink createDrink(String drinkName){
        switch (drinkName){
            case "Question1.Coffee": return new Coffee();
            case "Question1.MochaCoffee": return new MochaCoffee();
            case "Question1.Tea": return new Tea();
            default: throw new IllegalArgumentException("Invalid drink name");
        }
    }
}