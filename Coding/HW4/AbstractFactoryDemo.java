public class AbstractFactoryDemo {
    DFactory coffeeFactory = new CoffeeFactory();
    Drink coffee = coffeeFactory.createDrink();
    coffee.serveDrink();

    // Create a tea factory
    DFactory teaFactory = new TeaFactory(); 
    Drink milkTea = teaFactory.createDrink();
    milkTea.serveDrink();
}
