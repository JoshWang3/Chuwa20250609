
class FactoryMethodDemo {
    public static void main(String[] args) {
        // Create a CoffeeFactory
        DrinkFactory factory = new DrinkFactory();;
        // Use the factory to create a Drink
        Drink coffee = factory.getDrink("COFFEE");
        // Serve the drink
        coffee.serveDrink();

        // Use the factory to create a Drink
        Drink milkTea = factory.getDrink("MILKTEA");
        // Serve the drink
        milkTea.serveDrink();

        /*OUTPUT:
         * Serving a hot cup of coffee!
         * Serving a refreshing cup of milk tea!
         */
    }
}
