package Atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanDemo {
    public static void main(String[] args) {
        AtomicBoolean shopOpen = new AtomicBoolean(false);

        System.out.println("Shop initially open? " + shopOpen.get());

        // open the shop
        shopOpen.set(true);
        System.out.println("Burger shop is now open? " + shopOpen.get());

        // close shop if currently open
        boolean closed = shopOpen.compareAndSet(true, false);
        System.out.println("Successfully close burger shop: " + closed);

        System.out.println("Burger shop still open? " + shopOpen.get());
    }
}

/*
output:
Shop initially open? false
Burger shop is now open? true
Successfully close burger shop: true
Burger shop still open? false
 */