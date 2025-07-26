package Question6;

public class TestAcess {

    public static void main(String[] args) {
        AcessDemo acessDemo = new AcessDemo();

        //test within the class
        acessDemo.showAccess();

        //test within the package
        System.out.println(acessDemo.pubStr);      // Accessible
        //System.out.println(acessDemo.privStr);   // not Accessible
        System.out.println(acessDemo.defaultStr);     // Accessible
        System.out.println(acessDemo.protectStr);     // Accessible



    }


}
