

public class ShortQuestions{

    public static void main(String[] args){

        // Q4 Code Snippets


        //Q6 Code Snippets
        final int finalVal = 200;
        //finalVal++; // the compiler complains the final value cannot be modified.


        //Q7 Code Snippets:

        //primitive types
        int a = 5;
        change(a);
        System.out.println("a = " + a); // Output: a = 5
        // a's value won't be changed, because we pass a copy of a

        //Objects
        Person person = new Person("Original");
        changeName(person);
        System.out.println("person = " + person.name);


        //Q8 Code Snippets:
        Overloading demo = new Overloading();
        demo.print(10);
        demo.print(1.10);
        demo.print("String", 10);
    }

    // Q7 helper methods
    public static void change(int x) {
        x = 100;
    }

    static class Person {
        String name;
        Person(String name) {this.name = name;}
    }

    public static void changeName(Person p) {
        p.name = "Changed Name";
        p = new Person("New Name"); // if passed by reference, the p will be changed to New Name
    }

    //Q8 helper methods
    public class Overloading{
        public void print(int x) {
            System.out.println("printinting int " + x);
        }
        public void print(double x) {
            System.out.println("printdoubleing double " + x);
        }
        public void print(String msg, int x) {
            System.out.println("printString " + msg + "int" + x);
        }
    }

}