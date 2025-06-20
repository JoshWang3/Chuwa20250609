class CoffeeFactory implements DFactory{
    @Override
    public Drink createDrink() {
        return new Coffee();
    }
    
}
