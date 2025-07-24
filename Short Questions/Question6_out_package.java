import Question6.*;
public class Question6_out_package {
    public static void main(String[] args) {
        AcessDemo acessDemo = new AcessDemo();
        acessDemo.showAccess(); // test within the same class

        System.out.println(acessDemo.pubStr);      // Only public Accessible
        //System.out.println(acessDemo.privStr);   // not Accessible
        //System.out.println(acessDemo.defaultStr);     // not Accessible
        //System.out.println(acessDemo.protectStr);     // not Accessible
    }
}

class Question6_subClass extends AcessDemo{
    public void showAccess() {
        System.out.println(pubStr);      // Accessible
        //System.out.println(privStr);   // not Accessible
        //System.out.println(defaultStr);     // not Accessible
        System.out.println(protectStr);     // Accessible
    }

}