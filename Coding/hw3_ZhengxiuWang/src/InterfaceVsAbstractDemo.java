public class InterfaceVsAbstractDemo {
        /*
    interface:
    - no code inside
    - only method names

    abstract class:
    - can have code
    - can store variables
    */

    public static void main(String[] args) {
        Payable credit = new CreditCard();
        credit.pay();

        Paypal paypal = new Paypal();
        paypal.pay();
        paypal.refund();
    }
}

// interface
interface Payable {
    void pay();
}

// class implementing interface
class CreditCard implements Payable {
    public void pay() {
        System.out.println("Paying with credit card");
    }
}

// abstract class
abstract class OnlinePayment {
    double amount;

    public void refund() {
        System.out.println("Refunding payment");
    }

    abstract void pay(); // must override
}

// class extending abstract class
class Paypal extends OnlinePayment {
    public void pay() {
        System.out.println("Paying with PayPal");
    }
}
