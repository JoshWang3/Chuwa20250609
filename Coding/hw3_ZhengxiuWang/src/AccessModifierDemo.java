public class AccessModifierDemo {
    /*
    1. public    → accessible everywhere
    2. private   → only inside the class
    3. protected → same package + child classes
    4. default   → same package only (no keyword)

    Use private to protect data.
    Use public for safe access methods.
    - private: balance can't be touched directly
    - public: deposit() and getBalance() are safe
    */
    public static void main(String[] args) {
        Account myAcc = new Account("Maggie");
        myAcc.deposit(100);
        myAcc.deposit(-20);  // invalid

        System.out.println("Final balance: $" + myAcc.getBalance());

        // myAcc.balance = 9999;  // error: private
    }
}

// class showing access levels
class Account {
    public String owner;       // public
    private double balance;    // private

    public Account(String owner) {
        this.owner = owner;
        this.balance = 0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(owner + " deposited $" + amount);
        } else {
            System.out.println("Invalid amount");
        }
    }

    public double getBalance() {
        return balance;
    }
}
