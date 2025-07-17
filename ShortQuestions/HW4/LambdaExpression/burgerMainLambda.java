package LambdaExpression;

@FunctionalInterface
interface BurgerMaker {
    String makeBurger(String ingredients);
}

public class burgerMainLambda {
    public static void main(String[] args) {

        // use lambda expression for functional interface here
        // make bacon cheese burger
        BurgerMaker eagerBurgerMaker = (ingredients)-> "Eager Burger with " + ingredients + " and cheese";
        System.out.println(eagerBurgerMaker.makeBurger("Bacon"));

        // make avocado veggie burger
        BurgerMaker veggieBurgerMaker = (ingredients) -> "Veggie burger with " + ingredients;
        System.out.println(veggieBurgerMaker.makeBurger("avocado"));
    }
}
