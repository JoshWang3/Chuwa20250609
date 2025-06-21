package Coding.hw5;

import java.util.Optional;

class Demo1 {
    private String name;

    public Optional<String> getName() {return Optional.ofNullable(name);}
}

class Demo2 {
    private String name;

    public Optional<String> getName() {return Optional.of(name);}
}

public class OptionalDemo {
    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        Demo2 demo2 = new Demo2();

        Optional<String> count1 = demo1.getName();
        System.out.println(count1);
        System.out.println("done with count1");


        Optional<String> count2 = demo2.getName();
        System.out.println(count2);
        System.out.println("done with count2");

        // count 1 returns Optional.empty and count2 throws NPE
    }
}
