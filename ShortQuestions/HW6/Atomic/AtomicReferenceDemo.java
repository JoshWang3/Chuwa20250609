package Atomic;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    public static void main(String[] args) {

        // set the initial special burger of the day
        AtomicReference<String> specialBurger = new AtomicReference<>("Black Truffle");

        System.out.println("Initial special burger of the day: " + specialBurger.get());

        // update the special to bbq burger
        specialBurger.set("BBQ Chicken");
        System.out.println("Updated special burger of the day: " + specialBurger.get());

        // change from BBQ chicken to Vegan only it's currently bbq chicken
        boolean updated = specialBurger.compareAndSet("BBQ Chicken", "Vegan");
        System.out.println("Changed BBQ Chicken -> Vegan? " + updated);

        System.out.println("Current special burger of the day: " + specialBurger.get());
    }
}

/*
output:
Initial special burger of the day: Black Truffle
Updated special burger of the day: BBQ Chicken
Changed BBQ Chicken -> Vegan? true
Current special burger of the day: Vegan
 */
