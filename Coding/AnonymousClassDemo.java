package Coding;


interface Greeting {
    void sayHello();
}
public class AnonymousClassDemo {
    public static void main(String[] args) {
        // 使用匿名类实现接口
        Greeting greet = new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("Hello from anonymous class!");
            }
        };

        greet.sayHello(); // 输出：Hello from anonymous class!
    }

}
