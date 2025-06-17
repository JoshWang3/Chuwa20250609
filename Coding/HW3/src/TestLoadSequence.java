class Foo {
    static int S = initS(); //(1)
    static { System.out.println("2"); } // (2) statics field and blocks initialized in their order
    // …
    private static int initS() {
        System.out.println("1");
        return 0;
    }


    Foo(){
        System.out.println("4"); //(4) constructor called
    }
    int R=initR();
    int initR(){
        System.out.println("3"); //(3) initialize the non static field
        return 0;
    }
}
public class TestLoadSequence {
    public static void main(String[] args) {
        Foo foo = new Foo();
    }
}
