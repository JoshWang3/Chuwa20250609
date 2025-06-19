public class GarbageDemo {
    /*
- Object is created inside method
- After method ends, no reference points to it
- GC will remove it later
- System.gc() only suggests cleanup, not force
*/
    public static void main(String[] args) {
        // create temp object
        createOrder();  // Order is created and then lost

        System.out.println("Order created and gone.");

        // Suggest garbage collection
        System.gc();

        System.out.println("Requested garbage collection.");
    }

    public static void createOrder() {
        Order o = new Order("Apple", 3);
        // o will be garbage after method ends
    }
}

// simple class
class Order {
    String item;
    int quantity;

    Order(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        System.out.println("New order: " + item + " x " + quantity);
    }
}
