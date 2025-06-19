public class SuperVsThisDemo {
        /*
    this  → current class
    super → parent class
    used to access variables or methods
    */

    public static void main(String[] args) {
        Son s = new Son();
        s.showNames();
    }
}

// parent class
class Father {
    String name = "FatherName";
}

// child class
class Son extends Father {
    String name = "SonName";

    void showNames() {
        System.out.println("this.name: " + this.name);   // refers to Son's name
        System.out.println("super.name: " + super.name); // refers to Father's name
    }
}
