public class Question8 {
    public static void main(String[] args){
        //test static inner class:
        //Created inner static class object without creating outer class object
        exampleClass.staticInnerClass innerClass = new exampleClass.staticInnerClass();
        innerClass.greet();

        //test static variable/field
        System.out.println(exampleClass.staticString);

        //test static method
        exampleClass.staticMethod();
    }

}

class exampleClass {
    public static String staticString = "I am a static String";

    public static void staticMethod() {
        System.out.println("I am a static method");
    }

    static class staticInnerClass {
        public void greet(){
            System.out.println("I am a static inner class");
        }
    }
}


