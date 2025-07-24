
public class Question7 {
    public static final double PI = 3.1415926;
    public static void main(String[] args) {

        //try to reassigned value for Final value:
        //PI = 2.7;                //Cannot assign a value to final variable 'PI'


        // try to override a final method in its subclass
        class subClasss extends normalClass {
            //public static void finalMethod("")                  //finalMethod()' cannot override 'finalMethod()' in 'normalClass'; overridden method is final
        }


        //try to inheritance a final class
        //class subClass2 extends finalClass {}                         // Cannot inherit from final class 'finalClass'

    }
}


final class finalClass {
    public  void normalMethod() {
        System.out.println("this is a final Class");
    }
}

class normalClass {
    public static final void finalMethod() {
        System.out.println("this is a final method");
    }
}