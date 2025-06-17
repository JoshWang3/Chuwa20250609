interface I1{
    void method1();
    //I1(){} not allowed
}
interface I2{
    void method2();
    //void method3(){} Implementation not allowed
}

abstract class A1{
    private int x;
    public abstract void method1();
    public void method2(){
        System.out.println("A1 method2");
    }
    A1(){
        System.out.println("Constructor");
    }
}

abstract class A2{
}

class C extends A1 implements I1, I2{
    public void method1(){
        System.out.println("C method1");
    }
}

//class C2 extends A1,A2{} extend multiple abstract class not allowed
public class TestAbstractAndInterface {
    public static void main(String[] args) {
        C c = new C();
        c.method1();
        c.method2();
    }
}
