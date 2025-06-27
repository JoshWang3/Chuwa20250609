class TeaFactory implements  DFactory {
    @Override
    public Drink createDrink() {
        return new MilkTea();
    }
    
}
