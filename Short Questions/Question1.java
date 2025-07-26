public class Question1 {
    public static void main(String[] args){

        //Encapsulation Example
        BankAccount myAccount = new BankAccount("John's bank account", "1234567");
        myAccount.deposit(500);
        myAccount.withdraw(125);
        System.out.println("Account Name = " + myAccount.getAccountName());
        System.out.println("Account Number = " + myAccount.getAccountNumber());
        System.out.println("Account Balance = " + myAccount.getBalance());

        //Inheritance Example
        BankAccount myCheckingAccount = new CheckingAccount("John's checking account", "0123456");
        BankAccount mySavingAccount = new SavingsAccount("John's saving account", "0123457");

        myCheckingAccount.deposit(500);
        mySavingAccount.deposit(500);
        System.out.println("my Checking Account Balance = " + myCheckingAccount.getBalance());
        System.out.println("my Saving Account Balance = " + mySavingAccount.getBalance());

        //PolyMorphism - Overriding
        myCheckingAccount.withdraw(125);
        mySavingAccount.withdraw(125);
        System.out.println("my Checking Account Balance = " + myCheckingAccount.getBalance());
        System.out.println("my Saving Account Balance = " + mySavingAccount.getBalance());

        //PolyMorphism - Overloading
        SavingsAccount mySavingAccount2 = new SavingsAccount("John's saving account2", "0123457");
        mySavingAccount2.deposit(500);
        mySavingAccount2.withdraw(125, true);
        System.out.println("my Saving Account Balance when Fee waived = " + mySavingAccount2.getBalance());


    }


    //Encapsulation
    public static class BankAccount {
        private String accountName;
        private final String accountNumber;
        private double balance;


        public BankAccount(String accountName, String accountNumber) {
            this.accountName = accountName;
            this.accountNumber = accountNumber;
            this.balance = 0;
        }

        public double getBalance(){return this.balance;}
        public String getAccountName(){return this.accountName;}
        public String getAccountNumber(){return this.accountNumber;}

        public void setAccountName(String accountName){this.accountName = accountName;}
        public void deposit(double amount){
            if(amount > 0){
                this.balance += amount;
            } else {
                System.out.println("Deposit number cannot be negative!");
            }

        }

        public void withdraw(double amount){
            if(amount > 0 && amount <= this.balance){
                this.balance -= amount;
            } else {
                System.out.println("Insufficient fund or Withdraw amount cannot be negative!");
            }
        }

    }

    //Inheritance
    public static class CheckingAccount extends BankAccount {
        public CheckingAccount(String accountName, String accountNumber) {
            super(accountName, accountNumber);
        }
    }

    public static class SavingsAccount extends BankAccount {
        private static final double processingFee = 1.25;
        public SavingsAccount(String accountName, String accountNumber) {
            super(accountName, accountNumber);
        }

        //Polymorphism - Overriding
        @Override
        public void withdraw(double amount) {
            double totalAmount = amount + processingFee;
            super.withdraw(totalAmount);
        }

        //Polymorphism - Overloading

        public void withdraw(double amount, boolean feeWaived) {
            if(feeWaived){
                super.withdraw(amount);
            } else {
                double totalAmount = amount + processingFee;
                super.withdraw(totalAmount);
            }
        }
    }




}