interface I1{
    default void SayHello(){
        System.out.println("Hello,I1");
    }
    String version="1.0";
    static String getVersion(){
        return version;
    }
}

class C1 implements I1{

}
public class TestInterface {
    public static void main(String[] args) {
        C1 c1=new C1();
        c1.SayHello();
        System.out.println(I1.getVersion());
        //System.out.println(c1.getVersion()); not allowed
    }
}
