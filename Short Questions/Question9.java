public class Question9 {
    public static void main(String[] args) {
        exampleClass2 eC2 = new exampleClass2();
        exampleClass3 eC3 = new exampleClass3();


        //eC3 inheritate from eC2 but Overriding its method, this is overriding
        eC2.method1();
        eC3.method1();

        //eC3 has 2 method2 with different input, this is overloading
        eC3.method2(3);
        eC3.method2("String Input");
    }

}



class exampleClass2 {
    public void method1(){
        System.out.println("exampleClass2.method()");
    }
}


class exampleClass3 extends exampleClass2 {
    @Override
    public void method1(){
        System.out.println("Overriding exampleClass2.method()");
    }

    public void method2(int i){
        System.out.println("exampleClass3.method2() takes int \"" + i + "\" as input");
    }

    public void method2(String s){
        System.out.println("exampleClass3.method2() takes String \"" + s + "\" as input");
    }
}