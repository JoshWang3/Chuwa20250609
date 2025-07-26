package Question6;

public class AcessDemo {
    public String pubStr = "I am public";
    private String privStr = "I am private";
    String defaultStr = "I am default";
    protected String protectStr = "I am protected";

    public void showAccess() {
        System.out.println(pubStr);      // Accessible
        System.out.println(privStr);   // Accessible
        System.out.println(defaultStr);     // Accessible
        System.out.println(protectStr);     // Accessible
    }
}

