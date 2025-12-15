package Coding;

public class Example{
    int y = 5;

    void doAThing(){
        System.out.println("protected method");
    }
}

class Child extends Example{
    public void testField(){
        System.out.println(this.y); // ✅ Allowed
    }

    public void testMethod() {
        this.doAThing();   // ✅ Allowed
    }
}



class Test{
    public static void main(String[] args) {
        Child example = new Child();
        example.testField();
        example.testMethod();
        Example example2 = new Example();
        System.out.println(example2.y);
        example2.doAThing();
    }
}

