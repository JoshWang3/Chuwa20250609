package Projects;
import Coding.Example;


class Child extends Example{
    public void testField(){
        System.out.println(this.y);
    }

    public void testMethod() {
        this.doAThing();   // ✅ Allowed
    }
}
public class ClassInOtherPackage {
    public static void main(String[] args) {
        Child example = new Child();
        example.testField();
        example.testMethod();
    }
    
}
