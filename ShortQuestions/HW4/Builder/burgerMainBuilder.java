package Builder;

public class burgerMainBuilder {
    public static void main(String[] args) {

        // build a burger with cheese, lettuce, and bacon
        Burger eagerBurger = new Burger.BurgerBuilder("Sesame", "Beef")
                .cheese(true)
                .lettuce(true)
                .bacon(true)
                .build();

        System.out.println(eagerBurger);

        // build a veggie burger
        Burger veggieBurger = new Burger.BurgerBuilder("Wheat", "Veggie")
                .tomato(true)
                .onions(true)
                .build();

        System.out.println(veggieBurger);
    }
}
